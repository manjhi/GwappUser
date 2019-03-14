package com.omninos.gwappuser.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.adapter.ResponseAdapter;

public class ProviderResponseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProviderResponseActivity activity;
    private ResponseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_response);

        activity = ProviderResponseActivity.this;


        initView();
        SetUps();
    }

    private void SetUps() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        adapter = new ResponseAdapter(activity);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);


    }
}
