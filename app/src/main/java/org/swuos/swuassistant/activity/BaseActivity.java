package org.swuos.swuassistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

import org.swuos.swuassistant.commmon.Logger;

/**
 * Created by mran on 17-12-10.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
    }

    protected void bindView() {

    }
    protected void initData()
    {
    }
    @Override
    protected void onStart() {
        super.onStart();
        Logger.d("BaseActivity", "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d("BaseActivity", "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("BaseActivity", "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d("BaseActivity", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d("BaseActivity", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d("BaseActivity", "onDestroy: ");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Logger.d("BaseActivity", "onContentChanged: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
