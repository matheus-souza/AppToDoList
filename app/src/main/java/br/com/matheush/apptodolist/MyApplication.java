package br.com.matheush.apptodolist;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by matheush on 20/02/17.
 */

public class MyApplication extends Application {
    public static final String MSG_VAZIO = "Preencha este campo!";

    public static Realm REALM;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
        REALM = Realm.getDefaultInstance();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
