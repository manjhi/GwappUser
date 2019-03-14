package com.omninos.gwappuser.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.omninos.gwappuser.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private ProfileActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        activity=ProfileActivity.this;
        initView();
        SetUp();

    }

    private void initView() {
        back=findViewById(R.id.back);

    }

    private void SetUp() {
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
        }
    }
}
