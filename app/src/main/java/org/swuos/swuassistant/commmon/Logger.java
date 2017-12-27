package org.swuos.swuassistant.commmon;

import android.os.Build;
import android.util.Log;

/**
 * Created by mran on 17-12-10.
 */

public class Logger {
    private static boolean DEBUG = true;

    public static int d(String s, String content) {
        return DEBUG ? Log.d(s, content) : -1;
    }

    public static int e(String s, String content) {
        return DEBUG ? Log.e(s, content) : -1;
    }

    public static int w(String s, String content) {
        return DEBUG ? Log.w(s, content) : -1;
    }

    public static int v(String s, String content) {
        return DEBUG ? Log.v(s, content) : -1;
    }

    public static int i(String s, String content) {
        return DEBUG ? Log.i(s, content) : -1;
    }


}
