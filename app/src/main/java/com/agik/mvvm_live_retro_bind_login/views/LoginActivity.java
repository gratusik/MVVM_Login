package com.agik.mvvm_live_retro_bind_login.views;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.agik.mvvm_live_retro_bind_login.R;
import com.agik.mvvm_live_retro_bind_login.databinding.ActivityLoginBinding;
import com.agik.mvvm_live_retro_bind_login.models.responsemodel.LoginResponseModel;
import com.agik.mvvm_live_retro_bind_login.viewModels.LoginViewModel;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding activityLoginBinding;
    private LoginViewModel loginViewModel;
    Context context;
    public String IMEI_Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login);
        context = LoginActivity.this;
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        final TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //getDeviceId() is Deprecated so for android O we can use getImei() method
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            IMEI_Number = telephonyManager.getImei();
        }
        else {
            IMEI_Number = telephonyManager.getDeviceId();
        }

        if (savedInstanceState == null) {
            loginViewModel.init();
        }

        activityLoginBinding.setLoginviewmodel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);
        setupLoginChange();
   }

    private void setupLoginChange() {
        loginViewModel.responseModelMutableLiveData.observe(this, new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(@Nullable LoginResponseModel loginResponseModel) {
                if(loginResponseModel.getSuccess()==1) {
                    Toast.makeText(context,loginResponseModel.getSuccess_msg(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, NextActivity.class);
                    startActivity(intent);
                }
                else if(loginResponseModel.getSuccess()==0) {
                    Toast.makeText(context,loginResponseModel.getSuccess_msg(),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context,loginResponseModel.getSuccess_msg(),Toast.LENGTH_LONG);
                }

            }
        });
    }
}