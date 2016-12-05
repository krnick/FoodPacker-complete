package com.example.benson.foodpacker2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

import info.hoang8f.widget.FButton;

/**
 * Created by Benson on 2016/11/3.
 */
public class Fblogin extends Activity {
    CallbackManager callbackManager;
    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //初始化FacebookSdk，記得要放第一行，不然setContentView會出錯
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_login);





        //宣告callback Manager
        callbackManager = CallbackManager.Factory.create();

        //找到button
        FButton loginButton = (FButton) findViewById(R.id.fb_login);
        loginButton.setButtonColor(getResources().getColor(R.color.fb));
        loginButton.setShadowColor(getResources().getColor(R.color.fb_shadow));
        loginButton.setShadowEnabled(true);
        loginButton.setShadowHeight(10);
        loginButton.setCornerRadius(10);

        loginButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(Fblogin.this, Arrays.asList("public_profile", "user_friends"));
            }
        });

        //幫 LoginManager 增加callback function
        //這邊為了方便 直接寫成inner class
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            //登入成功
            @Override
            public void onSuccess(LoginResult loginResult) {

                //accessToken之後或許還會用到 先存起來
                accessToken = loginResult.getAccessToken();

                Log.d("FB", "access token got.");

                //send request and call graph api
                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {

                            //當RESPONSE回來的時候
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                //讀出姓名 ID FB個人頁面連結
                                Log.d("FB", "complete");
                                Log.d("FB", object.optString("name"));
                                Log.d("FB", object.optString("link"));
                                Log.d("FB", object.optString("id"));






//宣告SharedPreferences紀錄的name
                                SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
//必須有一個edit來存
                                SharedPreferences.Editor preEdt = pref.edit();
//所要記錄的資料 (也可以是int), 第一個參數是該筆資料的name,後面是value
                                preEdt.putString("FBname", object.optString("name"));
                                preEdt.putString("FBlink", object.optString("link"));
                                preEdt.putString("FBid", object.optString("id"));
                                preEdt.putString("FBmail",object.optString("email"));
//最後要commit
                                preEdt.commit();




                                Intent toMainPage=new Intent();
                                toMainPage.setClass(getApplicationContext(),MainActivity.class);
                                toMainPage.putExtra("name",object.optString("name"));
                                toMainPage.putExtra("link",object.optString("link"));
                                toMainPage.putExtra("id",object.optString("id"));
                                toMainPage.putExtra("email",object.optString("email"));
                                startActivity(toMainPage);

                            }
                        });

                //包入你想要得到的資料 送出request
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            //登入取消
            @Override
            public void onCancel() {
                // App code
                Log.d("FB", "CANCEL");
            }

            //登入失敗
            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("FB", exception.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
