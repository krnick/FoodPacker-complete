package com.example.benson.foodpacker2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Continus_Order extends AppCompatActivity {
    List<GetDataAdapter> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

    String GET_JSON_DATA_HTTP_URL = "http://140.127.218.212/php/jsonData.php";
    String JSON_ID = "id";
    String JSON_NAME = "name";
    String JSON_SUBJECT = "subject";
    String JSON_PHONE_NUMBER = "phone_number";

    Button button;

    JsonArrayRequest jsonArrayRequest;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continus_acticity);



        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        progressBar.setVisibility(View.VISIBLE);

        JSON_DATA_WEB_CALL();


    }

    //處理json
    public void JSON_DATA_WEB_CALL() {
//給url
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        progressBar.setVisibility(View.GONE);

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);


                GetDataAdapter2.setId(json.getString(JSON_ID).toString().substring(0, 19));

                GetDataAdapter2.setName(json.getString(JSON_NAME));

                GetDataAdapter2.setSubject(json.getString(JSON_SUBJECT));

                GetDataAdapter2.setPhone_number(json.getString(JSON_PHONE_NUMBER));

            } catch (JSONException e) {

                e.printStackTrace();
            }


            //宣告SharedPreferences紀錄的name
            SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
            //第一個參數是欲讀取的資料名稱，第二個參數是沒讀到的回傳值
            String FBname = pref.getString("FBname", "NO_VALUE");



            try {
                if (json.getString(JSON_NAME).toString().equals(FBname))
                    GetDataAdapter1.add(GetDataAdapter2);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        recyclerViewadapter = new Continus_OrderAdapter(GetDataAdapter1, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }

}
