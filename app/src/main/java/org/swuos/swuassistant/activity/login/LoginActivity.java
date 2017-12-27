package org.swuos.swuassistant.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.swuos.swuassistant.R;
import org.swuos.swuassistant.activity.BaseActivity;
import org.swuos.swuassistant.activity.login.presenter.LoginPresenterComp;
import org.swuos.swuassistant.activity.login.view.ILoginView;
import org.swuos.swuassistant.commmon.Constant;

/**
 * Created by mran on 17-12-15.
 */

public class LoginActivity extends BaseActivity implements ILoginView {
    EditText mUserNameEditText;
    EditText mPasswordEditText;
    Button mConfirmButton;
    LoginPresenterComp mLoginPresenterComp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_login);
        bindView();
        initData();
    }

    @Override
    protected void bindView() {
        super.bindView();
        mUserNameEditText = findViewById(R.id.user_id);
        mPasswordEditText = findViewById(R.id.user_password);
        mConfirmButton = findViewById(R.id.confirm_button);

    }

    @Override
    protected void initData() {
        super.initData();
        mLoginPresenterComp = new LoginPresenterComp(this, this);
        mConfirmButton.setEnabled(false);
        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mUserNameEditText.getText().toString()) && !TextUtils.isEmpty(mPasswordEditText.getText().toString())) {
                    mConfirmButton.setEnabled(true);
                } else {
                    mConfirmButton.setEnabled(false);
                }
            }
        });
        mUserNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mUserNameEditText.getText().toString()) && !TextUtils.isEmpty(mPasswordEditText.getText().toString())) {
                    mConfirmButton.setEnabled(true);
                } else {
                    mConfirmButton.setEnabled(false);
                }
            }
        });
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenterComp.doLogin(mUserNameEditText.getText().toString(), mPasswordEditText.getText().toString());
            }
        });
    }

    @Override
    public void onLoginResult(String result) {
        if ("success".equals(result)) {
            /*回到主页面*/
            Intent intent = new Intent();
            intent.putExtra("username",mUserNameEditText.getText().toString());
            intent.putExtra("password", mPasswordEditText.getText().toString());
            setResult(Constant.LOGIN_RESULT_CODE, intent);
            finish();
        } else {
//            alertDialog.setMessage(result);
//            alertDialog.show();
        }
    }
}
