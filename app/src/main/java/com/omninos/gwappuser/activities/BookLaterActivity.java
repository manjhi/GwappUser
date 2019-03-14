package com.omninos.gwappuser.activities;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.omninos.gwappuser.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookLaterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pickTime;
    private DatePicker datePicker;
    private String format = "";
    private ImageView back, edit;
    private TextView proTitle;
    private Button bookButton;
    private BookLaterActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_later);

        activity=BookLaterActivity.this;

        initView();
        SetUps();
    }

    @SuppressLint("NewApi")
    private void initView() {
        pickTime = findViewById(R.id.pickTime);
        datePicker = findViewById(R.id.datePicker);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
        proTitle = findViewById(R.id.proTitle);
        bookButton=findViewById(R.id.bookButton);


        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(BookLaterActivity.this, dayOfMonth + "/" + monthOfYear + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void SetUps() {
        pickTime.setOnClickListener(this);
        back.setOnClickListener(this);
        edit.setVisibility(View.GONE);
        proTitle.setText("Book Later");
        bookButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pickTime:
                OpenTimePicker();
                break;

            case R.id.back:
                onBackPressed();
                break;

            case R.id.bookButton:
                startActivity(new Intent(activity,HomeActivity.class));
                finishAffinity();
                break;
        }
    }

    private void OpenTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            format = "AM";
                        } else if (hourOfDay == 12) {
                            format = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }

                        if (minute<10){
                            pickTime.setText(hourOfDay + ":0" + minute + " " + format);
                        }else {
                            pickTime.setText(hourOfDay + ":" + minute + " " + format);
                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
}
