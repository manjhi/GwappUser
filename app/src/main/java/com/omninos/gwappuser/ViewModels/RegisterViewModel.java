package com.omninos.gwappuser.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.omninos.gwappuser.Retrofit.Api;
import com.omninos.gwappuser.Retrofit.ApiClient;
import com.omninos.gwappuser.Utils.CommonUtils;
import com.omninos.gwappuser.modelClasses.UserRegisterModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<UserRegisterModel> userRegisterModelMutableLiveData;

    public LiveData<UserRegisterModel> userRegister(final Activity activity, String name, String email, String phone, String Pass, String device_type, String reg_id){
        userRegisterModelMutableLiveData=new MutableLiveData<>();

        Api api=ApiClient.getApiClient().create(Api.class);

        if (CommonUtils.isNetworkConnected(activity)){

            CommonUtils.showProgress(activity,"");

            api.userRegister(name,email,phone,Pass,device_type,reg_id).enqueue(new Callback<UserRegisterModel>() {
                @Override
                public void onResponse(Call<UserRegisterModel> call, Response<UserRegisterModel> response) {
                    CommonUtils.dismissProgress();
                    if (response.isSuccessful()){
                        userRegisterModelMutableLiveData.setValue(response.body());
                    }else {

                    }
                }

                @Override
                public void onFailure(Call<UserRegisterModel> call, Throwable t) {
                    CommonUtils.dismissProgress();
                    Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }else {
            Toast.makeText(activity, "Network Issue", Toast.LENGTH_SHORT).show();
        }

        return userRegisterModelMutableLiveData;
    }
}
