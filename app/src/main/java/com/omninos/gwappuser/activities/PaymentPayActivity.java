package com.omninos.gwappuser.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.adapter.CustomSpinnerAdapter;

import java.util.Calendar;

public class PaymentPayActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView proTitle;
    private ImageView back, edit;

    PaymentPayActivity activity;
    CardView cardpayment, typeOfPayment;
    private Button bt_verify;
    Spinner spCard;
    private String card="Select Card";
    private EditText card_number, expiryDate, et_ccv, firstName, lastname;
    int cardMonth, cardYear, currentYear, currentMonth;
    String[] cardList = {"Select Card", "Debit Card", "Credit Card", "Master Card"};
    int[] cardImageList = {R.drawable.ic_visa, R.drawable.ic_visa, R.drawable.ic_credit_card, R.drawable.ic_mastercard};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pay);

        activity=PaymentPayActivity.this;

        initView();
        SetUps();



        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(activity, cardList, cardImageList);
        spCard.setAdapter(customSpinnerAdapter);
        spCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    card = cardList[i];
                    Toast.makeText(activity, card, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void SetUps() {
        proTitle.setText("Payment");
        edit.setVisibility(View.GONE);
        back.setOnClickListener(this);
        bt_verify.setOnClickListener(this);
        expiryDate.setFocusable(false);
        expiryDate.setOnClickListener(this);
    }
    private void initView() {
        proTitle = findViewById(R.id.proTitle);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
        spCard = findViewById(R.id.sp_card);
        bt_verify = findViewById(R.id.bt_verify);
        card_number = findViewById(R.id.card_number);
        expiryDate = findViewById(R.id.expiryDate);
        et_ccv = findViewById(R.id.et_ccv);
        firstName = findViewById(R.id.cardFirstName);
        lastname = findViewById(R.id.cardLastName);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_verify:
                GetCardData();
                break;

            case R.id.expiryDate:
                OpenDialog();
                break;
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    private void GetCardData() {
        startActivity(new Intent(PaymentPayActivity.this,PaymentInvoiceActivity.class));
    }

    private void OpenDialog() {

        final Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        currentMonth = mMonth;
        currentYear = mYear;
        DatePickerDialog monthDatePickerDialog = new DatePickerDialog(activity,
                AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                expiryDate.setText((month + 1) + "/" + year);
                cardMonth = month + 1;
                cardYear = year;
            }
        }, mYear, mMonth, mDay) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getDatePicker().findViewById(getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
            }
        };
        monthDatePickerDialog.setTitle("select_month");
        monthDatePickerDialog.show();
    }
}
