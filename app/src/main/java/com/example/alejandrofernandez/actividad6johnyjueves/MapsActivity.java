package com.example.alejandrofernandez.actividad6johnyjueves;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.location.QBLocations;
import com.quickblox.location.model.QBLocation;
import com.quickblox.location.request.QBLocationRequestBuilder;
import com.quickblox.location.request.SortField;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    private QBLocation location;
    private LocationManager locationm;
    private String provider;
    private Marker posact;
    UserSessionManager session;
    private QBLogin qbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        qbLogin = new QBLogin("33980", "TstV6H4XpPOz4jn", "pzDAFhvb5LjpvHM");
        //qbLogin.login("mopoz", "12345678");
        //qbLogin.addQbAdminLisntener();
        //qbLogin.simpleSessionCreated();
        login();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationm = (LocationManager) getSystemService(LOCATION_SERVICE);
        provider = LocationManager.GPS_PROVIDER;
        session = new UserSessionManager(getApplicationContext());
        Button buttonLogOut = (Button) findViewById(R.id.ButtonLogOut);
        Button buttonDescargar = (Button) findViewById(R.id.ButtonDownload);
        Button buttonSave = (Button) findViewById(R.id.ButtonSavePin);

        buttonLogOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg1) {

                // Clear the User session data
                // and redirect user to LoginActivity
                session.logoutUser();
            }
        });

        buttonDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargar();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabar_datos();
            }
        });

    }

    public void login(){

        QBUser user = new QBUser("mopoz", "12345678");

        QBUsers.signIn(user, new QBEntityCallbackImpl<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle params) {
                Log.v("Bien", "Login Correcto!");
            }

            @Override
            public void onError(List<String> errors) {
                Log.v("Error", "Login Incorrecto!");
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission to access the location is missing.", Toast.LENGTH_SHORT).show();
            return;
        }
        mMap.setMyLocationEnabled(true);

        try {

            locationm.requestLocationUpdates(provider, 3000, 0, this);

        } catch (SecurityException e) {
            Log.v("Error", "Error Location Manager");
        }

        if (location != null) {
            final double lat = location.getLatitude();
            final double lng = location.getLongitude();

            posact = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Estás aquí­"));
            LatLng latlng = new LatLng(lat, lng);
            CameraUpdate center = CameraUpdateFactory.newLatLng(latlng);
            mMap.moveCamera(center);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15.0f));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    public void grabar_datos() {

        final double lat = location.getLatitude();
        final double lng = location.getLongitude();

        location = new QBLocation(lat, lng, "Coordenadas");
        QBLocations.createLocation(location, new QBEntityCallbackImpl<QBLocation>() {
            @Override
            public void onSuccess(QBLocation qbLocation, Bundle args) {
                Log.v("Bien", "Posición grabada en las coordenadas " + lat + "---" + lng);
            }

            @Override
            public void onError(List<String> errors) {
                Log.v("Error", "No se ha podido grabar la posición en las coordenadas " + lat + "---" + lng);
            }
        });
    }

    public void descargar() {
        QBLocationRequestBuilder getLocationsBuilder = new QBLocationRequestBuilder();
        getLocationsBuilder.setPerPage(100);
        getLocationsBuilder.setHasStatus();
        getLocationsBuilder.setSort(SortField.CREATED_AT);
        QBLocations.getLocations(getLocationsBuilder, new QBEntityCallbackImpl<ArrayList<QBLocation>>() {
            @Override
            public void onSuccess(ArrayList<QBLocation> locations, Bundle params) {
                getPines(locations);
            }

            @Override
            public void onError(List<String> errors) {

            }
        });
    }

    public void getPines(ArrayList<QBLocation> locations) {
        double latitud;
        double longitud;

        for (int i = 0; i < locations.size(); i++) {
            latitud = locations.get(i).getLatitude();
            longitud = locations.get(i).getLongitude();

            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitud, longitud))
                    .title("QB Location " + i));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }



}
