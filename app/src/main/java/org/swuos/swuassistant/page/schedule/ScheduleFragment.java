package org.swuos.swuassistant.page.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.swuos.swuassistant.R;
import org.swuos.swuassistant.commmon.CommoneUtil;
import org.swuos.swuassistant.commmon.Constant;
import org.swuos.swuassistant.commmon.RenderScriptGaussianBlur;
import org.swuos.swuassistant.commmon.TotalInfos;
import org.swuos.swuassistant.page.BaseFragment;
import org.swuos.swuassistant.page.schedule.model.ScheduleData;
import org.swuos.swuassistant.page.schedule.model.ScheduleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mran on 17-12-17.
 */

public class ScheduleFragment extends BaseFragment implements IScheduleView {
    List<TextView> mDayTextViews;//day of mSchedule_weeksTextView
    List<TextView> mDateTextViews;//日期
    TextView mSchedule_weeksTextView;//学周
    TextView mSchedule_yearsTextView;//学年
    View mRootView;
    ScrollView mScrollView;
    TextView d1TextView;
    SchedulePresenterCompl mSchedulePresenterCompl;
    ContainerLinearLayout mContainerLinearLayout;
    private List<TextView> textViewList;
    private FrameLayout mFrameLayout;
    private int showWeek;
    private TotalInfos totalInfo;
    private TextView mZ1TextView;
    int mClazzWidth;//一节课的宽度
    int mClazzHeight;//一节课的高度
    private FrameLayout mChooseWeeksFrameLayout;
    private RenderScriptGaussianBlur mRenderScriptGaussianBlur;
    float mLastY;

    public class ContainerLinearLayout extends FrameLayout {


        public ContainerLinearLayout(Context context) {
            super(context);
            init(context);
        }

        public ContainerLinearLayout(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init(context);

        }

        public ContainerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

        public ContainerLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            init(context);
        }

        Paint mPaint;

        void init(Context context) {
            setWillNotDraw(false);
            mPaint = new Paint();
            mPaint.setStrokeWidth(12);
            mPaint.setAntiAlias(true);
            mPaint.setColor(0xff333333);

        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);
//            int startY = d1TextView.getBottom();
//            for (int i = 0; i < 13; i++) {
//                canvas.drawLine(0, startY+i * CommoneUtil.dp2px(getActivity(),40), 1080, startY+i * CommoneUtil.dp2px(getActivity(),40), mPaint);
//            }
        }

        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);


        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                if (mChooseWeeksFrameLayout != null && mChooseWeeksFrameLayout.getParent() != null) {
                mContainerLinearLayout.removeView(mChooseWeeksFrameLayout);
//                    return true;
//                }
            }
            return super.dispatchTouchEvent(event);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = View.inflate(getActivity(), R.layout.f_schedule, null);
        bindView();
        initData();
        ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
        mContainerLinearLayout = new ContainerLinearLayout(getActivity());
        mContainerLinearLayout.setLayoutParams(layoutParams);
        mContainerLinearLayout.addView(mRootView);
        mContainerLinearLayout.setDrawingCacheEnabled(true);

        container.addView(mContainerLinearLayout);
        return new TextView(getActivity());
    }

    @Override
    protected void bindView() {
        mScrollView = mRootView.findViewById(R.id.schedule_table_ScrollView);
        mFrameLayout = mRootView.findViewById(R.id.class_table);
        mZ1TextView = mRootView.findViewById(R.id.z1);
        mDayTextViews = new ArrayList<>();
        mDayTextViews.add((TextView) mRootView.findViewById(R.id.z1));
        mDayTextViews.add((TextView) mRootView.findViewById(R.id.z2));
        mDayTextViews.add((TextView) mRootView.findViewById(R.id.z3));
        mDayTextViews.add((TextView) mRootView.findViewById(R.id.z4));
        mDayTextViews.add((TextView) mRootView.findViewById(R.id.z5));
        mDayTextViews.add((TextView) mRootView.findViewById(R.id.z6));
        mDayTextViews.add((TextView) mRootView.findViewById(R.id.z7));

        mDateTextViews = new ArrayList<>();
        mDateTextViews.add((TextView) mRootView.findViewById(R.id.d1));
        mDateTextViews.add((TextView) mRootView.findViewById(R.id.d2));
        mDateTextViews.add((TextView) mRootView.findViewById(R.id.d3));
        mDateTextViews.add((TextView) mRootView.findViewById(R.id.d4));
        mDateTextViews.add((TextView) mRootView.findViewById(R.id.d5));
        mDateTextViews.add((TextView) mRootView.findViewById(R.id.d6));
        mDateTextViews.add((TextView) mRootView.findViewById(R.id.d7));

        mSchedule_weeksTextView = mRootView.findViewById(R.id.schedule_weeks);
        mSchedule_yearsTextView = mRootView.findViewById(R.id.schedule_years);
        d1TextView = mRootView.findViewById(R.id.d1);
        mRootView.setBackgroundResource(R.mipmap.bg);

        mSchedule_weeksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initWeeksPopuWindow();
                update();
            }
        });
        super.bindView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        update();

    }


    @Override
    protected void initData() {
        super.initData();
        mRenderScriptGaussianBlur = new RenderScriptGaussianBlur(getActivity());
        totalInfo = TotalInfos.getInstance();
        mSchedulePresenterCompl = new SchedulePresenterCompl(getActivity(), this);
        textViewList = new ArrayList<>();


    }

    @SuppressLint({"ObjectAnimatorBinding", "ResourceType"})
    private void initWeeksPopuWindow() {


        if (mChooseWeeksFrameLayout != null) {
            mChooseWeeksFrameLayout.setBackground(new BitmapDrawable(getResources(), mRenderScriptGaussianBlur.gaussianBlur(15, CommoneUtil.convertViewToBitmap(mRootView))));
            ((ViewGroup) (getActivity().getWindow().getDecorView().getRootView())).addView(mChooseWeeksFrameLayout);

            return;
        }
        mChooseWeeksFrameLayout = new FrameLayout(getActivity());
        mChooseWeeksFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mChooseWeeksFrameLayout.setBackground(new BitmapDrawable(getResources(), mRenderScriptGaussianBlur.gaussianBlur(10, mContainerLinearLayout.getDrawingCache().copy(Bitmap.Config.ARGB_8888, false))));
        mContainerLinearLayout.destroyDrawingCache();
        mChooseWeeksFrameLayout.setElevation(20);
        ScrollView scrollView = new ScrollView(getActivity());
        final LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setLayoutParams(new ScrollView.LayoutParams((int) (mContainerLinearLayout.getWidth() * 0.6), mContainerLinearLayout.getHeight() / 2, Gravity.CENTER));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 20; i++) {
            TextView textView = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            int[][] states = new int[][]{
                    new int[]{android.R.attr.state_enabled}, // enabled

            };

            int[] colors = new int[]{
                    Constant.backgroundcolor[i % 7]
            };

            final ColorStateList myList = new ColorStateList(states, colors);
            RippleDrawable rippleDrawable = new RippleDrawable(myList, null, ContextCompat.getDrawable(getActivity(), R.drawable.textview_ripple_bg));
            textView.setBackground(rippleDrawable);
            textView.setPadding(0, 10, 0, 0);
            textView.setText("第 " + (i + 1) + " 周");
            textView.setLetterSpacing(0.3f);
            textView.setTextSize(20);
            textView.setTextColor(0xffffffff);
            textView.setTag(i + 1);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showWeek = (int) v.getTag();
                    mSchedule_weeksTextView.setText("第" + v.getTag() + "周");
                    update();
                    mContainerLinearLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            mChooseWeeksFrameLayout.setBackground(new BitmapDrawable(getResources(), mRenderScriptGaussianBlur.gaussianBlur(15, CommoneUtil.convertViewToBitmap(mContainerLinearLayout))));

                        }
                    });

                }
            });
            linearLayout.addView(textView);
        }
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mChooseWeeksFrameLayout.animate().alpha(0.0f).setDuration(100).start();

                    mLastY = event.getY();
                    return false;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mScrollView.smoothScrollBy(0, (int) (-event.getY() + mLastY));
                    mLastY = event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mChooseWeeksFrameLayout.setBackground(new BitmapDrawable(getResources(), mRenderScriptGaussianBlur.gaussianBlur(15, CommoneUtil.convertViewToBitmap(mContainerLinearLayout))));
                    mChooseWeeksFrameLayout.animate().alpha(1).setDuration(100).start();
//                    mChooseWeeksFrameLayout.setAlpha(1f);
                    return false;
                }
                return false;
            }
        });
        scrollView.addView(linearLayout);

        mChooseWeeksFrameLayout.addView(scrollView);
        ((ViewGroup) (getActivity().getWindow().getDecorView().getRootView())).addView(mChooseWeeksFrameLayout);

//        AnimatorSet animationSet = new AnimatorSet();
//        mChooseWeeksFrameLayout.setPivotX(mChooseWeeksFrameLayout.getWidth() / 2);
//        mChooseWeeksFrameLayout.setPivotY(0);
//        animationSet.playTogether(ObjectAnimator.ofFloat(mChooseWeeksFrameLayout, "scaleY", 0f, 1f), ObjectAnimator.ofInt(mChooseWeeksFrameLayout, "translationY", -100, 10));
//        animationSet.setDuration(150);
//        animationSet.setInterpolator(new OvershootInterpolator());
//        animationSet.start();
    }

    @Override
    public void update() {
        super.update();
        mSchedulePresenterCompl.getSchedule(totalInfo.getUserName(), totalInfo.getPassword(), "2016", "3");
        Log.d("ScheduleFragment", "update: ");
    }

    @Override
    public void showDialog(Boolean isShow) {

    }

    @Override
    public void showResult() {
        textViewList.clear();
        mFrameLayout.removeAllViews();

        mClazzWidth = (getActivity().getResources().getDisplayMetrics().widthPixels - getResources().getDimensionPixelSize(R.dimen.table_width)) / 7;
        mClazzHeight = getResources().getDimensionPixelSize(R.dimen.each_class_hight);


        int i = 0;

        for (ScheduleItem scheduleItem : totalInfo.getScheduleItemList()) {

            /*判断该课本周是否有课*/
            if (!scheduleItem.getClassweek()[showWeek]) {
                continue;
            }
            /*设置新的布局参数*/
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams
                    .WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            /*建一个新的textview*/
            //            TextView textView = new TextView(scheduleTableLayout.getContext());
            TextView textView = new TextView(getActivity());
            if (showWeek == 0) {
                textView.setText(scheduleItem.getTextShowAll());
            } else {
                textView.setText(scheduleItem.getTextShow());
            }

               /*设置高度,用节数乘以一节课的高度*/
            textView.setHeight(mClazzHeight * scheduleItem.getClassCount() - 4);
                /*设置宽度*/
            textView.setWidth(mClazzWidth - 4);
                /*设置距离上边的距离,用一节课的固定高度乘以开始的节次*/
            layoutParams.topMargin = mClazzHeight * (scheduleItem.getStart() - 1);
            //                layoutParams.setMargins(width * (scheduleItem.getXqj()-1),hight * scheduleItem
            // .getEnd(),0,0);
                /*设置距离左边的距离,用固定宽度乘以该课的上课日*/
            layoutParams.leftMargin = mClazzWidth * (scheduleItem.getXqj() - 1);

            textView.setLayoutParams(layoutParams);
                /*设置背景色*/
            textView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.shape_class_bg));
            textView.getBackground().setColorFilter(Constant.backgroundcolor[i % 6], PorterDuff.Mode.ADD);

            textView.setTextColor(0xffffffff);
            textView.setTextSize(12);
            textView.setPadding(10, 0, 10, 0);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            textView.setId(i);
            ScheduleData.ScheduleDetail scheduleDetail = new ScheduleData.ScheduleDetail();
            scheduleDetail.setScheduleItem(scheduleItem);
            scheduleDetail.setTextView(textView);
            scheduleDetail.setColor(Constant.backgroundcolor[i % 8]);
                /*将新建的textview加入列表*/
//            textViewList.add(scheduleDetail);
                /*将新建的textview加入布局*/
            mFrameLayout.addView(textView);

            i++;
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public boolean onBackPressed() {
        if (mChooseWeeksFrameLayout.getParent() != null) {
            ((ViewGroup) (getActivity().getWindow().getDecorView().getRootView())).removeView(mChooseWeeksFrameLayout);
            return true;
        }
        return super.onBackPressed();
    }
}
