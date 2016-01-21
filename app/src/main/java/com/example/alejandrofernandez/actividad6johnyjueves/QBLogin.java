package com.example.alejandrofernandez.actividad6johnyjueves;

/**
 * Created by alejandro.fernandez on 21/01/2016.
 */
import android.os.Bundle;
import android.util.Log;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;
import java.util.List;

public class QBLogin {
    private QBListener listener;
    private boolean isSessionCreated;


    public QBLogin(String id, String ids, String secret) {
        QBSettings.getInstance().fastConfigInit(id, ids, secret);
    }

    public void addQbAdminLisntener(QBListener listener) {
        this.listener = listener;
    }

    public void simpleSessionCreated() {
        QBAuth.createSession(new QBEntityCallbackImpl<QBSession>() {
            @Override
            public void onSuccess(QBSession session, Bundle params) {
                isSessionCreated = true;
                listener.simpleSessionCreated(isSessionCreated);
            }

            @Override
            public void onError(List<String> errors) {
                isSessionCreated = false;
                listener.simpleSessionCreated(isSessionCreated);
            }
        });

    }

    public void login(String usuario, String con) {
        QBUser logUser = new QBUser(usuario, con);
        QBUsers.signIn(logUser, new QBEntityCallbackImpl<QBUser>() {
            @Override
            public void onSuccess(QBUser user, Bundle args) {
                isSessionCreated = true;
                Log.v("qbuser", "entra");
                listener.logear(isSessionCreated);
            }

            @Override
            public void onError(List<String> errors) {
                isSessionCreated = false;
                Log.v("qbuser", "no entra");
                listener.logear(isSessionCreated);
            }
        });
    }

}
