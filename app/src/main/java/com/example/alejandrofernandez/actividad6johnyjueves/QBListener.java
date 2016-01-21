package com.example.alejandrofernandez.actividad6johnyjueves;

/**
 * Created by alejandro.fernandez on 21/01/2016.
 */

public interface QBListener {

    void simpleSessionCreated(boolean isCreated);

    void registrar(boolean isCreated);

    void logear(boolean isCreated);
}