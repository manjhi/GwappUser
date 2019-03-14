package com.omninos.gwappuser.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.adapter.PaymentAdapter;

public class PaymentHistoryActivity extends AppCompatActivity implements OnClickListener {

    private TextView proTitle;
    private ImageView back, edit;
    private RecyclerView recyclerView;
    private PaymentHistoryActivity activity;
    private PaymentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        activity=PaymentHistoryActivity.this;
        initView();
        SetUps();

    }

    private void SetUps() {
        proTitle.setText("Payment History");
        edit.setVisibility(View.GONE);
        back.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter=new PaymentAdapter(activity);
        recyclerView.setAdapter(adapter);
    }

    private void initView() {
        proTitle = findViewById(R.id.proTitle);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);
        recyclerView=findViewById(R.id.recyclerView);

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
