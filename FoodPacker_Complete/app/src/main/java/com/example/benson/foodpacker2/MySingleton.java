package com.example.benson.foodpacker2;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static  MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private  MySingleton(Context context){
        mCtx = context;
        requestQueue = getrequestQueue();
    }

    public  static  synchronized  MySingleton getmInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }
        return  mInstance;
    }

    public RequestQueue getrequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return  requestQueue;
    }
    public<T>  void  addTorequestque(Request<T> request){
        requestQueue.add(request);
    }
}
