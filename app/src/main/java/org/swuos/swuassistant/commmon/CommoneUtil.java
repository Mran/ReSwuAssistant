package org.swuos.swuassistant.commmon;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;

import org.swuos.swuassistant.R;

/**
 * Created by mran on 17-12-17.
 */


public class CommoneUtil {
    public static float density = 1;

    public static boolean isLogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.sharePreferencesName), Context.MODE_PRIVATE);
        return !TextUtils.isEmpty(sharedPreferences.getString("userName", null));

    }

    public static void initTotalInfo(Context context, TotalInfos totalInfos) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.sharePreferencesName), Context.MODE_PRIVATE);
        totalInfos.setUserName(sharedPreferences.getString("userName", null));
        totalInfos.setName(sharedPreferences.getString("name", null ));
        totalInfos.setPassword(sharedPreferences.getString("password", null ));
        totalInfos.setSwuID(sharedPreferences.getString("swuID", ""));
        totalInfos.setScheduleDataJson(sharedPreferences.getString("scheduleDataJson", null));
    }

    public CommoneUtil() {

    }

    public static Bitmap convertViewToBitmap(View view) {
//        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//        view.buildDrawingCache();
//        Bitmap bitmap = view.getDrawingCache();

        // 创建对应大小的bitmap
        Bitmap bitmap1 = Bitmap.createBitmap(view.getWidth(), view.getHeight(),     Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        view.draw(canvas);
        return bitmap1;
    }

    public static float getDensity(Context context) {
        return density = context.getResources().getDisplayMetrics().density;
    }

    public static int dp2px(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp);

    }
}
