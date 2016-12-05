package com.example.benson.foodpacker2;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Person_Page extends AppCompatActivity {

    private String JSON_STRING;
    ListView person_list;
    float total_score=0;
    float count_loop = 0;
    TextView scoreview ,name;
    String FBname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person__page);
        person_list = (ListView) findViewById(R.id.person_list);
        scoreview = (TextView) findViewById(R.id.total);
        name=(TextView)findViewById(R.id.name);





        //宣告SharedPreferences紀錄的name
        SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
        //第一個參數是欲讀取的資料名稱，第二個參數是沒讀到的回傳值
        FBname = pref.getString("FBname", "NO_VALUE");
        final String FBid = pref.getString("FBid", "NO_VALUE");
        String score=pref.getString("score","0");
        name.setText(FBname);
        scoreview.setText(score);
        ProfilePictureView proview=(ProfilePictureView)findViewById(R.id.friendProfilePicture);
        proview.setProfileId(FBid);


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
                String Myname = jo.getString("Myname");
                String Cname = jo.getString("Cname");
                String value = jo.getString("value");
                String comment = jo.getString("comment");


                if (Myname.equals(FBname)) {

                    //算總分
                    total_score = total_score + Float.parseFloat(value);
                    count_loop++;
                    HashMap<String, String> employees = new HashMap<>();
                    employees.put("Myname", Myname);
                    employees.put("Cname", Cname);
                    employees.put("value", value);
                    employees.put("comment", comment);


                    list.add(employees);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListAdapter adapter = new SimpleAdapter(
                this, list, R.layout.person_page,
                new String[]{"Cname", "value", "comment"},
                new int[]{R.id.name, R.id.desg, R.id.salary});

        person_list.setAdapter(adapter);

    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Person_Page.this, " loading..", "Wait...", false, false);
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
                String s = rh.sendGetRequest("http://140.127.218.212/getPerson.php");
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();

    }
}
