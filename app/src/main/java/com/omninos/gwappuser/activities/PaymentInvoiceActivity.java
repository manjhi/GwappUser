package com.omninos.gwappuser.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.omninos.gwappuser.R;

public class PaymentInvoiceActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView proTitle;
    private ImageView back, edit;
    private Button shareFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_invoice);

        initView();
        SetUps();

    }

    private void initView() {
        proTitle = findViewById(R.id.proTitle);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
        shareFeedback=findViewById(R.id.shareFeedback);
    }

    private void SetUps() {

        proTitle.setText("Payment");
        edit.setVisibility(View.GONE);
        back.setOnClickListener(this);
        shareFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.shareFeedback:
                startActivity(new Intent(PaymentInvoiceActivity.this,FeedbackActivity.class));
                break;
        }
    }

}
