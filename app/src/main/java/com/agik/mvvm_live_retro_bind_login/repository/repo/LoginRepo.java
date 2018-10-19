package com.agik.mvvm_live_retro_bind_login.repository.repo;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.agik.mvvm_live_retro_bind_login.models.requestmodel.LoginRequestModel;
import com.agik.mvvm_live_retro_bind_login.models.responsemodel.LoginResponseModel;
import com.agik.mvvm_live_retro_bind_login.repository.retrofit.RetrofitClass;
import com.agik.mvvm_live_retro_bind_login.repository.serviceAPI.LoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepo {
    private MutableLiveData<LoginResponseModel> loginResponseModelLiveData = new MutableLiveData<>();

    public LoginRepo() {
    }

    public MutableLiveData<LoginResponseModel> getLoginRequest(final LoginRequestModel loginRequestModel, final boolean initial){

        Retrofit retrofit = RetrofitClass.getInstance();
        LoginAPI loginAPI = retrofit.create(LoginAPI.class);
        Call<LoginResponseModel> call = loginAPI.login(loginRequestModel);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if(200==response.code()) {
                    if(!initial) {
                        loginResponseModelLiveData.setValue(response.body());
                    }
                    else{
                        initial();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                if(!initial) {
                    LoginResponseModel loginResponseModel = new LoginResponseModel(0, "Internal server error");
                    loginResponseModelLiveData.setValue(loginResponseModel);
                }
                else{
                    initial();
                }
            }
        });
        return loginResponseModelLiveData;
    }

    public void initial(){
        LoginResponseModel loginResponseModel = new LoginResponseModel(2, "Internal server error");
        loginResponseModelLiveData.setValue(loginResponseModel);
    }
}
