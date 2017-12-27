package org.swuos.swuassistant;

import android.app.Application;
import android.content.Context;

/**
 * Created by mran on 17-12-10.
 */

public class App extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public App() {
    }

    public static Context getmContext() {
        return  mContext;
    }
}
