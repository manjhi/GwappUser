package com.omninos.gwappuser.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.omninos.gwappuser.R;

public class BidProjectActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mainTitle;
    private ImageView greenBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_project);

        initView();
        SetUps();
    }

    private void SetUps() {
        mainTitle.setText("Bid Project");
        greenBack.setOnClickListener(this);
    }

    private void initView() {
        mainTitle=findViewById(R.id.mainTitle);
        greenBack=findViewById(R.id.greenBack);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.greenBack:
                onBackPressed();
                break;
        }
    }
}
