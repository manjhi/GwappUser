package com.omninos.gwappuser.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.omninos.gwappuser.modelClasses.MatchTokenModel;
import com.omninos.gwappuser.modelClasses.ResentOtp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerifyActivity extends AppCompatActivity implements View.OnClickListener {

    private OtpVerifyActivity activity;
    private EditText first, second, third, fourth;
    private TextView resend;
    private Button varify;
    private String otp_1, otp_2, otp_3, otp_4, compeleteOtp, OTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        activity = OtpVerifyActivity.this;
        initView();


        first.addTextChangedListener(generalTextWatcher);
        second.addTextChangedListener(generalTextWatcher);
        third.addTextChangedListener(generalTextWatcher);
        fourth.addTextChangedListener(generalTextWatcher);


    }

    private void initView() {

        if (App.getAppPreferences().getOtp() != null) {
            OTP = App.getAppPreferences().getOtp();
        }

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        varify = findViewById(R.id.submit);
        resend = findViewById(R.id.resend);


        varify.setOnClickListener(this);
        resend.setOnClickListener(this);
    }


    TextWatcher generalTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (first.getText().hashCode() == editable.hashCode()) {
                otp_1 = first.getText().toString().trim();
                if (!otp_1.equalsIgnoreCase("")) {
                    second.requestFocus();
                }

            } else if (second.getText().hashCode() == editable.hashCode()) {
                otp_2 = second.getText().toString().trim();
                if (!otp_2.equalsIgnoreCase("")) {
                    third.requestFocus();
                } else {
                    first.requestFocus();
                }

            } else if (third.getText().hashCode() == editable.hashCode()) {
                otp_3 = third.getText().toString().trim();
                if (!otp_3.equalsIgnoreCase("")) {
                    fourth.requestFocus();
                } else {
                    second.requestFocus();
                }
            } else if (fourth.getText().hashCode() == editable.hashCode()) {
                otp_4 = fourth.getText().toString().trim();
                if (!otp_4.equalsIgnoreCase("")) {
                    hideKeyboard(activity);
                } else {
                    third.requestFocus();
                }

            }

        }
    };


    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                CheckOtp();
                break;
            case R.id.resend:
                ResendOtp();
                break;
        }
    }

    private void ResendOtp() {
        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "");
            Api api = ApiClient.getApiClient().create(Api.class);
            api.resendOtp(CommonUtils.GetString(activity, ConstantData.USERID)).enqueue(new Callback<ResentOtp>() {
                @Override
                public void onResponse(Call<ResentOtp> call, Response<ResentOtp> response) {
                    CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        if (response.body().getSuccess().equalsIgnoreCase("1")) {
                            Toast.makeText(activity, String.valueOf(response.body().getDetails().getOtp()), Toast.LENGTH_SHORT).show();
                            compeleteOtp = String.valueOf(response.body().getDetails().getOtp());
                        } else {
                            Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                    }
                }
                @Override
                public void onFailure(Call<ResentOtp> call, Throwable t) {
                    CommonUtils.dismissProgress();
                    Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show();

                }
            });

        } else {
            Toast.makeText(activity, "Network Issue", Toast.LENGTH_SHORT).show();
        }
    }


    private void CheckOtp() {
        otp_1 = first.getText().toString().trim();
        otp_2 = second.getText().toString().trim();
        otp_3 = third.getText().toString().trim();
        otp_4 = fourth.getText().toString().trim();
        compeleteOtp = otp_1 + otp_2 + otp_3 + otp_4;
        if (compeleteOtp.length() != 4) {
            Toast.makeText(this, "enter values", Toast.LENGTH_SHORT).show();
        } else {
//            if (App.getAppPreferences().getOtp() != null) {
//                CheckOtpData();
//            } else {
                CheckOtpvalidation();
//            }
        }
    }

    private void CheckOtpvalidation() {

        if (CommonUtils.isNetworkConnected(activity)) {

            CommonUtils.showProgress(activity, "");

            Api api = ApiClient.getApiClient().create(Api.class);

            api.matchToken(CommonUtils.GetString(activity, ConstantData.USERID), compeleteOtp).enqueue(new Callback<MatchTokenModel>() {
                @Override
                public void onResponse(Call<MatchTokenModel> call, Response<MatchTokenModel> response) {
                    CommonUtils.dismissProgress();
                    if (response.body() != null) {
                        if (response.body().getSuccess().equalsIgnoreCase("1")) {

                            OpendialogBox();
                        } else {
                            Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MatchTokenModel> call, Throwable t) {
                    CommonUtils.dismissProgress();
                    Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show();

                }
            });


        } else {
            Toast.makeText(activity, "Network Issue", Toast.LENGTH_SHORT).show();
        }
    }

    private void CheckOtpData() {
        if (compeleteOtp.equalsIgnoreCase(OTP)) {
            OpendialogBox();
        } else {
            Toast.makeText(activity, "Invalid OTP", Toast.LENGTH_SHORT).show();
        }
    }

    private void OpendialogBox() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.verify_popup, null);

        Button verify = view.findViewById(R.id.done);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                CommonUtils.SaveString(activity, ConstantData.TOKEN, "1");
                startActivity(new Intent(activity, HomeActivity.class));
                finishAffinity();

            }
        });
        alertDialog.show();
    }
}
