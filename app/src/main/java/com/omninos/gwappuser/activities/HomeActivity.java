package com.omninos.gwappuser.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.Utils.CommonUtils;
import com.omninos.gwappuser.adapter.MainPagerAdapter;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private DrawerLayout drawer;
    private ImageView menuButton;
    private HomeActivity activity;
    TextView navi_profile,navi_Terms,navi_Payment,navi_track,navi_logout,navi_chat;

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            finish();
            finishAffinity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activity=HomeActivity.this;

        initView();
        SetUps();
    }

    private void initView() {

        tabLayout=findViewById(R.id.TabLayout);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuButton=findViewById(R.id.menuButton);
        navi_profile=findViewById(R.id.navi_profile);
        navi_Terms=findViewById(R.id.navi_Terms);
        navi_Payment=findViewById(R.id.navi_Payment);
        navi_track=findViewById(R.id.navi_track);
        navi_logout=findViewById(R.id.logout);
        navi_chat=findViewById(R.id.navi_chat);

    }

    private void SetUps() {
        tabLayout.addTab(tabLayout.newTab().setText("Service"));
        tabLayout.addTab(tabLayout.newTab().setText("Jobs"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        LinearLayout linearLayout = (LinearLayout)tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.GREEN);
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);
        menuButton.setOnClickListener(this);
        navi_profile.setOnClickListener(this);
        navi_Terms.setOnClickListener(this);
        navi_Payment.setOnClickListener(this);
        navi_track.setOnClickListener(this);
        navi_logout.setOnClickListener(this);
        navi_chat.setOnClickListener(this);


        setUpFragment();
    }

    private void setUpFragment() {
        final ViewPager viewPager=findViewById(R.id.viewPager);

        MainPagerAdapter pagerAdapter=new MainPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void DrawerMenuBar() {
        drawer.openDrawer(Gravity.START);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menuButton:
                DrawerMenuBar();
                break;

            case R.id.navi_profile:
                startActivity(new Intent(activity,ProfileActivity.class));
                break;

            case R.id.navi_Terms:
                startActivity(new Intent(activity,TermsAndConditionActivity.class));
                break;
            case R.id.navi_Payment:
                startActivity(new Intent(activity,PaymentHistoryActivity.class));
                break;

            case R.id.navi_track:
                startActivity(new Intent(activity,TrackProviderActivity.class));
                break;
            case R.id.logout:
                CommonUtils.Logout(activity);
                startActivity(new Intent(activity,LoginActivity.class));
                finishAffinity();
                break;

            case R.id.navi_chat:
                startActivity(new Intent(activity,ChatActivity.class));
                break;
        }
    }
}
