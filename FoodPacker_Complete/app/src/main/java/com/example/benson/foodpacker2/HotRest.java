package com.example.benson.foodpacker2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HotRest extends AppCompatActivity {

    private String JSON_STRING;
    ListView hot_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_rest);
        hot_list=(ListView)findViewById(R.id.hot_list);
        getJSON();
    }


    private void showEmployee() {
        JSONObject jsonObject = null;
        final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String restaurant = jo.getString("restaurant");
                String num = jo.getString("num");

                HashMap<String, String> employees = new HashMap<>();
                employees.put("restaurant", restaurant);
                employees.put("num", num);
                employees.put("rank",Integer.toString(i+1));


                list.add(employees);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListAdapter adapter = new SimpleAdapter(
                this, list, R.layout.hotrest_list_item,
                new String[]{"restaurant", "num","rank"},
                new int[]{R.id.name, R.id.time, R.id.id});

        hot_list.setAdapter(adapter);

    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(HotRest.this, " loading..", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();

            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest("http://140.127.218.212/getHot.php");
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();

    }
}
