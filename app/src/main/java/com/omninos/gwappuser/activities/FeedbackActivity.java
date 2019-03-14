package com.omninos.gwappuser.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.omninos.gwappuser.R;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView proTitle;
    private ImageView back, edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        initView();
        SetUps();
    }

    private void initView() {
        proTitle = findViewById(R.id.proTitle);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
    }

    private void SetUps() {

        proTitle.setText("Feedback");
        edit.setVisibility(View.GONE);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }
}
