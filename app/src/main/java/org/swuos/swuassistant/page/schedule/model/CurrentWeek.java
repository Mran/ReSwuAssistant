package org.swuos.swuassistant.page.schedule.model;


import org.swuos.swuassistant.app.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 张孟尧 on 2016/3/20.
 * 用于获得当前为第几周
 */
public class CurrentWeek {
    public static int getweek() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date smdate = null;
        String openStudyDate;
        openStudyDate = App.getInstance().getSharedPreferences("com.swuos.swuassistant_preferences", MODE_PRIVATE).getString("open_study_date", "20170227");
        try {
            smdate = sdf.parse(openStudyDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        long time2 = System.currentTimeMillis();
        int weeks = (int) ((time2 - time1) / (1000 * 3600 * 24) / 7 + 1);

        return weeks;


    }

    public static int getDayofWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
    }
}
