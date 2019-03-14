package com.omninos.gwappuser.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.adapter.SkillAdapter;

import java.util.ArrayList;
import java.util.List;

public class SingleJobActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler;
    private SingleJobActivity activity;
    List<String> list=new ArrayList<>();
    private TextView mainTitle;
    private ImageView greenBack;
    private Button bid_on_this_project;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_job);

        activity=SingleJobActivity.this;

        initView();
        SetUps();
    }

    private void SetUps() {
        mainTitle.setText("View details");
        recycler.setLayoutManager(new GridLayoutManager(activity, 3));

        list.add("Graphics designer");
        list.add("UI & UX designer");
        list.add("Android");
        list.add("iOS");

        SkillAdapter adapter=new SkillAdapter(list,activity);
        recycler.setAdapter(adapter);

        greenBack.setOnClickListener(this);
        bid_on_this_project.setOnClickListener(this);

    }

    private void initView() {
        mainTitle=findViewById(R.id.mainTitle);
        recycler=findViewById(R.id.recycler);
        greenBack=findViewById(R.id.greenBack);
        bid_on_this_project=findViewById(R.id.bid_on_this_project);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.greenBack:
                onBackPressed();
                break;
            case R.id.bid_on_this_project:
                startActivity(new Intent(activity,BidProjectActivity.class));
                break;
        }
    }
}
