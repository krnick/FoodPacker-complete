package com.example.benson.foodpacker2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Benson on 2016/11/18.
 */
public class splashscreen extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    protected static final String TAG = "Test";

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    protected String mLatitudeLabel;
    protected String mLongitudeLabel;


    private ProgressBar mProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the splash screen
        setContentView(R.layout.splash_screen);
        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);


        //取得位置
        mLatitudeLabel = "緯度";
        mLongitudeLabel = "經度";

        buildGoogleApiClient();
        //


        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    // 當 GoogleApiClient 連上 Google Play Service 後要執行的動作
    @Override
    public void onConnected(Bundle connectionHint) {
        // 這行指令在 IDE 會出現紅線，不過仍可正常執行，可不予理會
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

            //宣告SharedPreferences紀錄的name
            SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
//必須有一個edit來存
            SharedPreferences.Editor preEdt = pref.edit();
            preEdt.putString("lat",  Double.toString(mLastLocation.getLatitude()) );
            preEdt.putString("lng",  Double.toString(mLastLocation.getLongitude()) );
            preEdt.commit();

            Log.d("Loc", (String.format("%s: %f", mLatitudeLabel, mLastLocation.getLatitude())));
            Log.d("Loc", (String.format("%s: %f", mLatitudeLabel, mLastLocation.getLongitude())));
        } else {
            Toast.makeText(this, "偵測不到定位，請確認定位功能已開啟。", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }


    private void doWork() {
        for (int progress = 0; progress < 100; progress += 20) {
            try {
                Thread.sleep(500);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
                //Timber.e(e.getMessage());
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(splashscreen.this, Fblogin.class);
        startActivity(intent);
    }
}
