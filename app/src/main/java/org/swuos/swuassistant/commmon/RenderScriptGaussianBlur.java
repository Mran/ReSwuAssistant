package org.swuos.swuassistant.commmon;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by mran on 17-12-27.
 */

public class RenderScriptGaussianBlur {
    private  RenderScript mRenderScript;

    public RenderScriptGaussianBlur(Context context) {
        this.mRenderScript = RenderScript.create(context);
    }

    public Bitmap gaussianBlur(int radius, Bitmap bitmap) {

        Allocation input = Allocation.createFromBitmap(mRenderScript, bitmap);
        Allocation output = Allocation
                .createTyped(mRenderScript
                        , input.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(mRenderScript, Element.U8_4(mRenderScript));
        scriptIntrinsicBlur.setRadius(radius);
        scriptIntrinsicBlur.setInput(input);
        scriptIntrinsicBlur.forEach(output);
        output.copyTo(bitmap);
        return bitmap;
    }
}
