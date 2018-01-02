package org.swuos.swuassistant.commmon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by mran on 17-12-27.
 */

public class RenderScriptGaussianBlur {
    private RenderScript mRenderScript;

    public RenderScriptGaussianBlur(Context context) {
        this.mRenderScript = RenderScript.create(context);
    }

    public Bitmap gaussianBlur(Bitmap bitmap) {

        float scaleFactor = 8;

        Bitmap overlay = Bitmap.createBitmap((int)(bitmap.getWidth()/scaleFactor), (int)(bitmap.getHeight()/scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        Allocation input = Allocation.createFromBitmap(mRenderScript, overlay);
        Allocation output = Allocation
                .createTyped(mRenderScript
                        , input.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(mRenderScript, Element.U8_4(mRenderScript));
        scriptIntrinsicBlur.setRadius(10);
        scriptIntrinsicBlur.setInput(input);
        scriptIntrinsicBlur.forEach(output);
        output.copyTo(overlay);
        return overlay;
    }
}
