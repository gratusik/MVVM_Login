package com.agik.mvvm_live_retro_bind_login.models.responsemodel;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {
    @SerializedName("success")
    @NonNull
    @Expose
    private Integer success;
    @SerializedName("success_msg")
    @NonNull
    @Expose
    private String success_msg;


    public LoginResponseModel() {
    }

    public LoginResponseModel(@NonNull Integer success, @NonNull String success_msg) {
        this.success = success;
        this.success_msg = success_msg;
    }

    @NonNull
    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(@NonNull Integer success) {
        this.success = success;
    }

    @NonNull
    public String getSuccess_msg() {
        return success_msg;
    }

    public void setSuccess_msg(@NonNull String success_msg) {
        this.success_msg = success_msg;
    }
}
