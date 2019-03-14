package com.omninos.gwappuser.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.adapter.AvailableAdapter;

public class AvailableServicesActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private AvailableServicesActivity activity;
    private AvailableAdapter availableAdapter;

    private TextView proTitle;
    private ImageView back, edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_services);
        activity=AvailableServicesActivity.this;

        initView();
        SetUps();

    }

    private void SetUps() {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        availableAdapter=new AvailableAdapter(activity, new AvailableAdapter.SelectBook() {
            @Override
            public void Select(int position) {
                startActivity(new Intent(activity,ProviderProfileActivity.class));
            }

            @Override
            public void BookLater(int position) {
                startActivity(new Intent(activity,BookLaterActivity.class));
            }
        });
        recyclerView.setAdapter(availableAdapter);

        proTitle.setText("Available Electrician");
        edit.setVisibility(View.GONE);
        back.setOnClickListener(this);
    }

    private void initView() {
        recyclerView=findViewById(R.id.recyclerView);
        proTitle=findViewById(R.id.proTitle);
        back=findViewById(R.id.back);
        edit=findViewById(R.id.edit);
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
