package com.example.benson.foodpacker2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {



    String facebookname;

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
    String MENU_PAGE2="menu_page2";
    Button button;

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);

        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        progressBar.setVisibility(View.VISIBLE);



        JSON_DATA_WEB_CALL();







        return  rootView;
    }
    //處理json
    public void JSON_DATA_WEB_CALL(){
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

        requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);



                GetDataAdapter2.setId(json.getString(JSON_ID).toString().substring(0,19));


                GetDataAdapter2.setMenu_page2(json.getString(MENU_PAGE2));

                Log.d("menu",json.getString(MENU_PAGE2));

                GetDataAdapter2.setName(json.getString(JSON_NAME));

                GetDataAdapter2.setSubject(json.getString(JSON_SUBJECT));

                GetDataAdapter2.setPhone_number(json.getString(JSON_PHONE_NUMBER));



            } catch (JSONException e) {

                e.printStackTrace();
            }
            //FB
            //宣告SharedPreferences紀錄的name
            SharedPreferences pref = this.getActivity().getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
            //第一個參數是欲讀取的資料名稱，第二個參數是沒讀到的回傳值
            String FBname = pref.getString("FBname", "NO_VALUE");
            String FBlink = pref.getString("FBlink", "NO_VALUE");
            String FBid = pref.getString("FBid", "NO_VALUE");
            String FBmail=pref.getString("FBmail","NO_VALUE");


            try {
                if (!json.getString(JSON_NAME).toString().equals(FBname))
                GetDataAdapter1.add(GetDataAdapter2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);
    }

}
