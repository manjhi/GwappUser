package com.omninos.gwappuser.Retrofit;

import com.omninos.gwappuser.modelClasses.MatchTokenModel;
import com.omninos.gwappuser.modelClasses.ResentOtp;
import com.omninos.gwappuser.modelClasses.UserLoginModel;
import com.omninos.gwappuser.modelClasses.UserRegisterModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("userRegister")
    Call<UserRegisterModel> userRegister(@Field("name") String name,
                                         @Field("email") String email,
                                         @Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("device_type") String device_type,
                                         @Field("reg_id") String  reg_id);


    @FormUrlEncoded
    @POST("matchVerificationToken")
    Call<MatchTokenModel> matchToken(@Field("id") String id,
                                     @Field("token") String token);


    @FormUrlEncoded
    @POST("userLogin")
    Call<UserLoginModel> login(@Field("email") String email,
                               @Field("password") String password,
                               @Field("reg_id") String reg_id,
                               @Field("device_type") String device_type);

    @FormUrlEncoded
    @POST("resendVerificationToken")
    Call<ResentOtp> resendOtp(@Field("id") String id);
}
