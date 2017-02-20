package br.com.matheush.apptodolist;

import android.app.Application;

/**
 * Created by matheush on 20/02/17.
 */

public class MyApplication extends Application {
    public static final String MSG_VAZIO = "Preencha este campo!";

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
