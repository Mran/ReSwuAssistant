package org.swuos.swuassistant.app;

import android.app.Application;

import org.swuos.swuassistant.BuildConfig;
import org.swuos.swuassistant.app.exception.ExceptionCaughtAdapter;

/**
 * App
 * Created by mran on 17-12-10.
 */

public class App extends Application {

    private static App instance;

    private static boolean debugMode;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance != null) {
            instance = this;
            return;
        }
        instance = this;
        debugMode = setDebugMode();
        initExceptionCatcher();
    }

    protected boolean setDebugMode() {
        return BuildConfig.DEBUG;
    }

    private void initExceptionCatcher() {
        if (isDebug()) {
            Thread.UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
            ExceptionCaughtAdapter exceptionCaughtAdapter = new ExceptionCaughtAdapter(handler);
            Thread.setDefaultUncaughtExceptionHandler(exceptionCaughtAdapter);
        }
    }

    public static App getInstance() {
        return instance;
    }

    /**
     * 是否处于调试模式
     */
    public static boolean isDebug() {
        return debugMode;
    }
}
