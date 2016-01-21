package com.example.alejandrofernandez.actividad6johnyjueves;

/**
 * Created by alejandro.fernandez on 20/01/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    Button btnLogin;

    EditText txtUsername, txtPassword;

    // Controlador de la session del usuario
    UserSessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Controlador de la session del usuario de la aplicacion actual (LoginActivity)
        session = new UserSessionManager(getApplicationContext());

        // Referencias de los EditText
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

//        Toast.makeText(getApplicationContext(),
//                "User Login Status: " + session.isUserLoggedIn(),
//                Toast.LENGTH_LONG).show();


        // Boton Login referenciado
        btnLogin = (Button) findViewById(R.id.btnLogin);


        // Accion del botton login
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Cogemos los datos de username y password
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                /*
                    Ponemos un trim(), para quitar los espacios null y un length tanto para
                    Username como para Password para comprobar que no está vacio
                 */
                if(username.trim().length() > 0 && password.trim().length() > 0){

                    if(username.equals("1234") && password.equals("1234")){

                        session.createUserLoginSession("1234",
                                "1234");

                        // Starting MainActivity
                        Intent mapa = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(mapa);

                        finish();

                    }else{

                        // username / password doesn't match&
                        Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();

                    }
                }else{

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username and password",
                            Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}