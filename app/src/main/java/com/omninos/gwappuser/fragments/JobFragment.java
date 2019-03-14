package com.omninos.gwappuser.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.activities.BidProjectActivity;
import com.omninos.gwappuser.activities.HomeActivity;
import com.omninos.gwappuser.activities.PostProjectActivity;
import com.omninos.gwappuser.activities.SingleJobActivity;
import com.omninos.gwappuser.adapter.JobAdapter;
import com.omninos.gwappuser.adapter.ServiceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    HomeActivity activity;
    ImageView addBid;

    public JobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_job, container, false);

        activity= (HomeActivity) getActivity();
        initView(view);
        SetUps(view);

        return view;
    }


    private void SetUps(View view) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        JobAdapter adapter=new JobAdapter(activity, new JobAdapter.ClickItems() {
            @Override
            public void OnSelectItem(int position) {
                startActivity(new Intent(activity,SingleJobActivity.class));
            }
        });
        recyclerView.setAdapter(adapter);

        addBid.setOnClickListener(this);
    }

    private void initView(View view) {
        addBid=view.findViewById(R.id.addBid);
        recyclerView=view.findViewById(R.id.recyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBid:
                startActivity(new Intent(activity,PostProjectActivity.class));
                Toast.makeText(activity, "Click", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
