package com.omninos.gwappuser.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.omninos.gwappuser.ViewModels.RegisterViewModel;
import com.omninos.gwappuser.modelClasses.UserRegisterModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RegisterActivity activity;
    private TextView tv_signin;
    private Button signUp;
    private RegisterViewModel viewModel;
    private EditText userName, userEmail, userPass, userConfirmPass, userNumber;
    private String S_username, S_userEmail, S_userPass, S_userConfirmPass, S_Number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        activity = RegisterActivity.this;
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        initView();
        SetUps();
    }

    private void initView() {
        tv_signin = findViewById(R.id.tv_signin);
        signUp = findViewById(R.id.signUp);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPassword);
        userConfirmPass = findViewById(R.id.ConfirmPass);
        userNumber = findViewById(R.id.Number);

    }

    private void SetUps() {
        tv_signin.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_signin:
                startActivity(new Intent(activity, LoginActivity.class));
                break;
            case R.id.signUp:
                validate();

                break;
        }
    }

    private void validate() {
        S_username = userName.getText().toString().trim();
        S_userEmail = userEmail.getText().toString().trim();
        S_userPass = userPass.getText().toString().trim();
        S_userConfirmPass = userConfirmPass.getText().toString().trim();
        S_Number = userNumber.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}";
        if (S_username.isEmpty()) {
            userName.setError("enter username");
        } else if (S_userEmail.isEmpty() || !S_userEmail.matches(emailPattern)) {
            userEmail.setError("enter valid email pattern");
        } else if (S_userPass.isEmpty() || S_userPass.length() < 7) {
            userPass.setError("enter minimum 7 character password");
        } else if (S_userConfirmPass.isEmpty() || !S_userConfirmPass.equals(S_userPass)) {
            userConfirmPass.setError("password mismatch");
        } else if (S_Number.isEmpty() || S_Number.length() < 9) {
            userNumber.setError("enter valid number length");
        } else {
            UserRegister();
        }
    }

    private void UserRegister() {

        viewModel.userRegister(activity, S_username, S_userEmail, S_Number, S_userPass, "Android", "123456").observe(this, new Observer<UserRegisterModel>() {
            @Override
            public void onChanged(@Nullable UserRegisterModel userRegisterModel) {
                if (userRegisterModel.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(activity, String.valueOf(userRegisterModel.getDetails().getOtp()), Toast.LENGTH_SHORT).show();
                    CommonUtils.SaveString(activity, ConstantData.USERID, userRegisterModel.getDetails().getId());
                    App.getAppPreferences().setOtp(String.valueOf(userRegisterModel.getDetails().getOtp()));
                    startActivity(new Intent(activity, OtpVerifyActivity.class));
                } else {
                    Toast.makeText(activity, userRegisterModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
