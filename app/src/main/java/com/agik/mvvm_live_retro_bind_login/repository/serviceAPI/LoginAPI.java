package com.agik.mvvm_live_retro_bind_login.repository.serviceAPI;

import com.agik.mvvm_live_retro_bind_login.models.requestmodel.LoginRequestModel;
import com.agik.mvvm_live_retro_bind_login.models.responsemodel.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {
    // @FormUrlEncoded
    @POST("Logintest1.php")
   // Observable<Response<Login_Response>> createUser(@Body LoginRequest loginRequest);
    Call<LoginResponseModel> login(@Body LoginRequestModel loginRequestModel);
}
