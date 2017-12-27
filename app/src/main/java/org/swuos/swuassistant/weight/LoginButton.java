package org.swuos.swuassistant.weight;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mran on 17-12-17.
 */

public class LoginButton extends View {
    private Context mContext;
    private Paint mPaint;
    private ValueAnimator mValueAnimator;
    private int state;
    private float width;
    private float height;
    float radiusl;

    public LoginButton(Context context) {
        super(context);
        init(context);
    }

    public LoginButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoginButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LoginButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(0xffffffff);
        canvas.drawCircle(width / 2, height / 2, radiusl, mPaint);
    }
}
