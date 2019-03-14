package com.omninos.gwappuser.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.Retrofit.Api;
import com.omninos.gwappuser.Retrofit.ApiClient;
import com.omninos.gwappuser.Utils.App;
import com.omninos.gwappuser.Utils.CommonUtils;
import com.omninos.gwappuser.Utils.ConstantData;
import com.omninos.gwappuser.modelClasses.UserLoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signup;
    LoginActivity activity;
    private TextView tv_signup;
    private EditText userEmail, userPass;
    private String S_userEmail, S_userPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activity = LoginActivity.this;


        initView();
        SetUps();
    }

    private void initView() {
        signup = findViewById(R.id.signin);
        tv_signup = findViewById(R.id.tv_signup);
        userPass = findViewById(R.id.userPass);
        userEmail = findViewById(R.id.userEmail);

    }


    private void SetUps() {
        signup.setOnClickListener(this);
        tv_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin:
                validate();
                break;

            case R.id.tv_signup:
                startActivity(new Intent(activity, RegisterActivity.class));
                break;
        }
    }

    private void validate() {
        S_userEmail = userEmail.getText().toString().trim();
        S_userPass = userPass.getText().toString().trim();
        if (S_userEmail.isEmpty()) {
            userEmail.setError("enter email");
        } else if (S_userPass.isEmpty()) {
            userPass.setError("enter password");
        } else {
            UserLogin();
//            startActivity(new Intent(activity,HomeActivity.class));
        }
    }

    private void UserLogin() {
        if (CommonUtils.isNetworkConnected(activity)) {

            CommonUtils.showProgress(activity, "");
            Api api = ApiClient.getApiClient().create(Api.class);

            api.login(S_userEmail, S_userPass, "123456", "Android").enqueue(new Callback<UserLoginModel>() {
                @Override
                public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                    CommonUtils.dismissProgress();
                    if (response.body() != null) {

                        if (response.body().getSuccess().equalsIgnoreCase("1")) {

                            CommonUtils.SaveString(activity, ConstantData.TOKEN, "1");
                            CommonUtils.SaveString(activity, ConstantData.USERID, response.body().getDetails().getId());
                            startActivity(new Intent(activity, HomeActivity.class));

                        } else if (response.body().getSuccess().equalsIgnoreCase("2")) {
                            CommonUtils.SaveString(activity, ConstantData.USERID, response.body().getDetails().getId());
                            Toast.makeText(activity, response.body().getDetails().getOtp(), Toast.LENGTH_SHORT).show();
                            App.getAppPreferences().setOtp(response.body().getDetails().getOtp());
                            startActivity(new Intent(activity, OtpVerifyActivity.class));

                        } else {
                            Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {

                    }
                }
                @Override
                public void onFailure(Call<UserLoginModel> call, Throwable t) {
                    CommonUtils.dismissProgress();
                }
            });


        } else {
            Toast.makeText(activity, "Network Issue", Toast.LENGTH_SHORT).show();
        }
    }
}
