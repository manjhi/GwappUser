package com.omninos.gwappuser.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.omninos.gwappuser.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EstimatePaymentActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView proTitle;
    private ImageView back, edit;
    private EstimatePaymentActivity activity;
    private CardView locationName;
    private EditText et_loc, pickDate, serviceTime;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    String TAG = "Activity";
    private String format = "";
    private Button proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_payment);

        activity = EstimatePaymentActivity.this;

        initView();
        SetUps();
    }

    private void SetUps() {
        proTitle.setText("Estimate Payment");
        edit.setVisibility(View.GONE);
        back.setOnClickListener(this);
        et_loc.setFocusable(false);
        et_loc.setOnClickListener(this);
        pickDate.setFocusable(false);
        pickDate.setOnClickListener(this);
        serviceTime.setFocusable(false);
        serviceTime.setOnClickListener(this);
        proceed.setOnClickListener(this);
    }

    private void initView() {
        proTitle = findViewById(R.id.proTitle);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
        locationName = findViewById(R.id.locationCard);
        et_loc = findViewById(R.id.et_loc);
        pickDate = findViewById(R.id.pickDate);
        serviceTime = findViewById(R.id.serviceTime);
        proceed=findViewById(R.id.proceed);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.et_loc:
                openLocationActivity();
                break;

            case R.id.pickDate:
                PickDate();
                break;

            case R.id.serviceTime:
                PickTime();
                break;
            case R.id.proceed:
                startActivity(new Intent(activity,PaymentActivity.class));
                break;
        }
    }

    private void PickTime() {
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
                        serviceTime.setText(hourOfDay + ":" + minute+" "+format);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    private void PickDate() {
        final Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(
                activity, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker,
                                  int selectedyear, int selectedmonth,
                                  int selectedday) {

                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH,
                        selectedday);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                pickDate.setText(sdf.format(mcurrentDate
                        .getTime()));
            }
        }, mYear, mMonth, mDay);

        mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
        mDatePicker.setTitle("Date of Birth");
        mDatePicker.show();
    }

    private void openLocationActivity() {

        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place: " + place.getName());
                et_loc.setText(place.getName().toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}
