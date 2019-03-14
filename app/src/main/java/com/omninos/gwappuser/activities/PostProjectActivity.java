package com.omninos.gwappuser.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.omninos.gwappuser.R;

public class PostProjectActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView proTitle;
    private ImageView greenBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_project);


        initView();
        SetUps();

    }

    private void SetUps() {
        proTitle.setText("Post Job");
        greenBack.setOnClickListener(this);
    }

    private void initView() {
        proTitle = findViewById(R.id.mainTitle);
        greenBack = findViewById(R.id.greenBack);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.greenBack:
                onBackPressed();
                break;
        }
    }
}
