package com.agik.mvvm_live_retro_bind_login.models.requestmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.agik.mvvm_live_retro_bind_login.BR;
import com.agik.mvvm_live_retro_bind_login.R;
import com.agik.mvvm_live_retro_bind_login.viewModels.LoginViewModel;

import java.util.regex.Pattern;

public class LoginRequestModel extends BaseObservable {

    @NonNull
    private String username,password;
    private Integer userErrorMessage,passwordErrorMessage;
    private boolean animationUsername =false,animationPassword=false;
   /* public ObservableBoolean animationUserNameError=new ObservableBoolean(false);
    public ObservableBoolean animationPasswordError=new ObservableBoolean(false);*/
    public LoginRequestModel() {
    }

    public LoginRequestModel(@NonNull String user_name, @NonNull String password) {
        this.username = user_name;
        this.password = password;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String user_name) {
        this.username = user_name;
    }

    @NonNull
    public String getPassword() {
        return password;

    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public Integer getUserErrorMessage() {
        return userErrorMessage;
    }

    public void setUserErrorMessage(Integer userErrorMessage) {
        this.userErrorMessage = userErrorMessage;
    }

    public Integer getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public void setPasswordErrorMessage(Integer passwordErrorMessage) {
        this.passwordErrorMessage = passwordErrorMessage;
    }

    public boolean isAnimationUsername() {
        return animationUsername;
    }

    public void setAnimationUsername(boolean animationUsername) {
        this.animationUsername = animationUsername;
    }

    public boolean isAnimationPassword() {
        return animationPassword;
    }

    public void setAnimationPassword(boolean animationPassword) {
        this.animationPassword = animationPassword;
    }

    public boolean getLoginUsername(boolean message){
        if(TextUtils.isEmpty(getUsername())){
            if(message) {
                setUserErrorMessage(R.string.app_name);
                setAnimationUsername(true);
                notifyPropertyChanged(BR.usernameError);
                notifyPropertyChanged(BR.animationUserNameError);
            }
            return false;
        }
        else{
            return true;
        }
    }

    public boolean getLoginPassword(boolean message){
        if(TextUtils.isEmpty(getPassword())) {
            if(message) {
                setPasswordErrorMessage(R.string.app_name);
                setAnimationPassword(true);
                notifyPropertyChanged(BR.passwordError);
                notifyPropertyChanged(BR.animationPasswordError);
            }
            return false;
        }
        else{
            return true;
        }
    }

    @Bindable
    public boolean getAnimationPasswordError() {
        return isAnimationPassword();
    }
    @Bindable
    public boolean getAnimationUserNameError() {
        return isAnimationUsername();
    }

    @Bindable
    public Integer getUsernameError() {
        return getUserErrorMessage();
    }

    @Bindable
    public Integer getPasswordError() {
        return getPasswordErrorMessage();
    }

}
