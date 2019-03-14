package com.omninos.gwappuser.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.Window;

import com.omninos.gwappuser.R;

public class CommonUtils {
    private static Dialog progressDialog;
    public static void showProgress(Activity activity, String message) {
        progressDialog = new Dialog(activity);
        progressDialog.setTitle("Please wait...");
        progressDialog.setContentView(R.layout.spinkitanimation);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window dialogWindow = progressDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dismissProgress() {
        progressDialog.dismiss();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static void SaveString(Activity activity,String key,String value){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyGwappUser",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String GetString(Activity activity,String key){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyGwappUser",0);
        return sharedPreferences.getString(key,"");
    }

    public static void Logout(Activity activity){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyGwappUser",0);
        sharedPreferences.edit().clear().commit();
    }
}
