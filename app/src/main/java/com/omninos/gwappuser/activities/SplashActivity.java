package com.omninos.gwappuser.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.omninos.gwappuser.R;
import com.omninos.gwappuser.Utils.CommonUtils;
import com.omninos.gwappuser.Utils.ConstantData;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        permissions();

    }

    private void permissions() {
        if (ActivityCompat.checkSelfPermission(SplashActivity.this,Manifest.permission.ACCESS_FINE_LOCATION+Manifest.permission.ACCESS_COARSE_LOCATION+Manifest.permission.CAMERA+Manifest.permission.WRITE_EXTERNAL_STORAGE+Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SplashActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},100);
        }else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100:
                boolean location1=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                boolean location2=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                boolean camera=grantResults[2]==PackageManager.PERMISSION_GRANTED;
                boolean write=grantResults[3]==PackageManager.PERMISSION_GRANTED;
                boolean read=grantResults[4]==PackageManager.PERMISSION_GRANTED;

                if (grantResults.length>0 && location1 && location2 && camera && write && read){
                    init();
                }else if (Build.VERSION.SDK_INT>=23 && !shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION+Manifest.permission.ACCESS_COARSE_LOCATION+Manifest.permission.CAMERA+Manifest.permission.WRITE_EXTERNAL_STORAGE+Manifest.permission.READ_EXTERNAL_STORAGE)){
                    OpenSetting();
//                }else if (Build.VERSION.SDK_INT>=23 && !shouldShowRequestPermissionRationale(permissions[1])){
//                    OpenSetting();
//                }else if (Build.VERSION.SDK_INT>=23 && !shouldShowRequestPermissionRationale(permissions[2])){
//                    OpenSetting();
//                }else if (Build.VERSION.SDK_INT>=23 && !shouldShowRequestPermissionRationale(permissions[3])){
//                    OpenSetting();
//                }else if (Build.VERSION.SDK_INT>=23 && !shouldShowRequestPermissionRationale(permissions[4])){
//                    OpenSetting();
                }else {
                    ActivityCompat.requestPermissions(SplashActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},100);
                }
        }
    }

    private void OpenSetting() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Permission");
        builder.setMessage("Permissions are required");
        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(SplashActivity.this, "Go to Settings to Grant the Storage Permissions and restart application", Toast.LENGTH_LONG).show();
//                sentToSettings = true;
                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", SplashActivity.this.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        })
                .create()
                .show();
    }


    private void init() {

        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    if (CommonUtils.GetString(SplashActivity.this,ConstantData.TOKEN).equalsIgnoreCase("1")){
                        startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                        finishAffinity();
                    }else {
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finishAffinity();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }


}
