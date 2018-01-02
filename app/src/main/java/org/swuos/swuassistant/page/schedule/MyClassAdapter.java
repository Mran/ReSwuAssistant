package org.swuos.swuassistant.page.schedule;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.swuos.swuassistant.R;
import org.swuos.swuassistant.commmon.Constant;
import org.swuos.swuassistant.page.schedule.model.ScheduleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mran on 18-1-1.
 */

public class MyClassAdapter extends PagerAdapter {
    List<ScheduleItem> mScheduleItems;
    Context mContext;
    List<View> mViews;

    public MyClassAdapter(List<ScheduleItem> scheduleItems, Context context) {
        mScheduleItems = scheduleItems;
        mContext = context;
        mViews = new ArrayList<>();
        for (int i = 0; i < scheduleItems.size(); i++) {
            mViews.add(new View(mContext));
        }
    }

    public void setScheduleItems(List<ScheduleItem> scheduleItems) {
        this.mScheduleItems = scheduleItems;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setBackgroundResource(R.drawable.shape_class_bg);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.getBackground().setColorFilter(Constant.backgroundcolor[position % 7], PorterDuff.Mode.ADD);
//        linearLayout.setBackgroundColor(Constant.backgroundcolor[position % 7]);

        linearLayout.setPadding(100, 100, 100, 100);
        TextView classNameTextView = new TextView(mContext);
        classNameTextView.setTextColor(0xffffffff);
        classNameTextView.setText(mScheduleItems.get(position).getKcmc());
        classNameTextView.setTextSize(20);

        TextView teacherNameTextView = new TextView(mContext);
        teacherNameTextView.setTextColor(0xffffffff);
        teacherNameTextView.setText(mScheduleItems.get(position).getXm());
        teacherNameTextView.setTextSize(18);

        TextView classRoomTextView = new TextView(mContext);
        classRoomTextView.setTextColor(0xffffffff);
        classRoomTextView.setText(mScheduleItems.get(position).getCdmc());
        classRoomTextView.setTextSize(18);

        TextView timeTextView = new TextView(mContext);
        timeTextView.setTextColor(0xffffffff);
        timeTextView.setText(mScheduleItems.get(position).getJc());
        timeTextView.setTextSize(18);

        TextView weekTextView = new TextView(mContext);
        weekTextView.setTextColor(0xffffffff);
        weekTextView.setText(mScheduleItems.get(position).getZcd());
        weekTextView.setTextSize(18);

        linearLayout.addView(classNameTextView);
        linearLayout.addView(teacherNameTextView);
        linearLayout.addView(classRoomTextView);
        linearLayout.addView(timeTextView);
        linearLayout.addView(weekTextView);
        container.addView(linearLayout);
        linearLayout.setElevation(10);
        mViews.remove(position);
        mViews.add(position, linearLayout);
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getCount() {
        return mScheduleItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
