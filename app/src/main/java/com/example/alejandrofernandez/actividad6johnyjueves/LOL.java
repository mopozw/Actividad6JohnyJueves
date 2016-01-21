package com.example.alejandrofernandez.actividad6johnyjueves;

/**
 * Created by alejandro.fernandez on 21/01/2016.
 */
public class LOL {

    /*import android.location.Location;
    import android.location.LocationListener;
    import android.os.Bundle;
    import android.support.v4.app.FragmentActivity;
    import android.text.Html;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

    import com.google.android.gms.maps.CameraUpdateFactory;
    import com.google.android.gms.maps.GoogleMap;
    import com.google.android.gms.maps.OnMapReadyCallback;
    import com.google.android.gms.maps.SupportMapFragment;
    import com.google.android.gms.maps.model.LatLng;
    import com.google.android.gms.maps.model.MarkerOptions;
    import com.quickblox.core.QBEntityCallbackImpl;
    import com.quickblox.location.QBLocations;
    import com.quickblox.location.model.QBLocation;
    import com.quickblox.users.QBUsers;
    import com.quickblox.users.model.QBUser;

    import java.util.HashMap;
    import java.util.List;

    public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMyLocationButtonClickListener {

        private QBLocation location;

        private QBLogin qbLogin;

        private GoogleMap mMap;

        UserSessionManager session;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            qbLogin = new QBLogin("33980", "TstV6H4XpPOz4jn", "pzDAFhvb5LjpvHM");
            login();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            session = new UserSessionManager(getApplicationContext());

            Button buttonLogOut = (Button) findViewById(R.id.ButtonLogOut);

            Button buttonSave = (Button) findViewById(R.id.ButtonSavePin);

            TextView Username = (TextView) findViewById(R.id.Username);

            // get user data from session
            HashMap<String, String> user = session.getUserDetails();

            // get name
            String name = user.get(UserSessionManager.KEY_NAME);

            // Show user data on activity
            Username.setText(Html.fromHtml("Nombre session: <b>" + name + "</b>"));

            if(session.checkLogin())
                finish();

            buttonLogOut.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg1) {

                    // Clear the User session data
                    // and redirect user to LoginActivity
                    session.logoutUser();
                }
            });

            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    grabar_datos();
                }
            });

        }


        *//**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         *//*
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
        public void onLocationChanged(Location location) {

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
    }*/

}
