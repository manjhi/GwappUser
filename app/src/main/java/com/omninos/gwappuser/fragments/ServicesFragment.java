package com.omninos.gwappuser.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.activities.AvailableServicesActivity;
import com.omninos.gwappuser.activities.HomeActivity;
import com.omninos.gwappuser.adapter.ServiceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    RecyclerView recyclerView;
    HomeActivity activity;

    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_services, container, false);
        activity= (HomeActivity) getActivity();
        initView(view);
        SetUps(view);

        return view;
    }

    private void SetUps(View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));

        ServiceAdapter adapter=new ServiceAdapter(activity, new ServiceAdapter.MyAvaility() {
            @Override
            public void Click(int position) {
                startActivity(new Intent(activity,AvailableServicesActivity.class));

            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        recyclerView=view.findViewById(R.id.recyclerView);
    }

}
