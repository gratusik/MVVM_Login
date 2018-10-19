package com.agik.mvvm_live_retro_bind_login.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.agik.mvvm_live_retro_bind_login.R;
import com.agik.mvvm_live_retro_bind_login.models.requestmodel.LoginRequestModel;
import com.agik.mvvm_live_retro_bind_login.models.responsemodel.LoginResponseModel;
import com.agik.mvvm_live_retro_bind_login.repository.repo.LoginRepo;


public class LoginViewModel extends ViewModel {
    LoginRequestModel loginRequestModel =  new LoginRequestModel();
    private MutableLiveData<LoginRequestModel> buttonClickRequest = new MutableLiveData<>();
    public MutableLiveData<LoginResponseModel> responseModelMutableLiveData = new MutableLiveData<>();
    private LoginRepo loginRepo = new LoginRepo();

    public void init(/*String IMEI_Number*/) {
        loginRequestModel.getLoginUsername(false);
        loginRequestModel.getLoginPassword(false);
        buttonClickRequest.setValue(loginRequestModel);
        responseModelMutableLiveData = loginRepo.getLoginRequest(loginRequestModel,true);
    }
    public void hit_Login(){
        buttonClickRequest.setValue(loginRequestModel);
        if(loginRequestModel.getLoginUsername(true) && loginRequestModel.getLoginPassword(true)){
            responseModelMutableLiveData = loginRepo.getLoginRequest(loginRequestModel,false);
        }
    }

    public MutableLiveData<LoginResponseModel> getResponseModelMutableLiveData(){
        return responseModelMutableLiveData;
    }

    public LoginRequestModel getLogin() {
        return loginRequestModel;
    }



    @BindingAdapter("error")
    public static void setError(EditText editText, Object strOrResId) {
        if (strOrResId instanceof Integer) {
            editText.setError(
                    editText.getContext().getString((Integer) strOrResId));
        } else {
            editText.setError((String) strOrResId);
        }
    }

    @BindingAdapter("errorAnimation")
    public static void setAnimationError(EditText view,boolean animationError) {
        if(animationError) {
            Animation shake = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
            view.startAnimation(shake);
        }
    }
}
