package org.swuos.swuassistant.utils;

import android.util.Log;

import org.swuos.swuassistant.app.App;

/**
 *
 * Created by mran on 17-12-10.
 */

public class Logger {

    public static int d(String s, String content) {
        return App.isDebug() ? Log.d(s, content) : -1;
    }

    public static int e(String s, String content) {
        return App.isDebug() ? Log.e(s, content) : -1;
    }

    public static int w(String s, String content) {
        return App.isDebug() ? Log.w(s, content) : -1;
    }

    public static int v(String s, String content) {
        return App.isDebug() ? Log.v(s, content) : -1;
    }

    public static int i(String s, String content) {
        return App.isDebug() ? Log.i(s, content) : -1;
    }


}
