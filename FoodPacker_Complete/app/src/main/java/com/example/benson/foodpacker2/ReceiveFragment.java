package com.example.benson.foodpacker2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static android.content.Context.MODE_PRIVATE;

public class ReceiveFragment extends Fragment {


    private String JSON_STRING;


    private ListView listView;

    String score;

    public ReceiveFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getJSON();//去抓資料
        return inflater.inflate(R.layout.fragment_receive, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) this.getView().findViewById(R.id.lis);
    }





    private void showEmployee() {
        JSONObject jsonObject = null;
        final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME);
                String desg = jo.getString(Config.TAG_DESG);
                String sal = jo.getString(Config.TAG_SAL);
                String fbid = jo.getString(Config.Tag_IMAGE);
                String lat_rest = jo.getString("lat");
                String lng_rest = jo.getString("lng");
                String restaurant=jo.getString("restaurant");
                String address=jo.getString("address");
                String fee=jo.getString("fee");
                String scoredata=jo.getString("score");


//                計算距離
                //FB
                //宣告SharedPreferences紀錄的name
                SharedPreferences pref = this.getActivity().getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
                //第一個參數是欲讀取的資料名稱，第二個參數是沒讀到的回傳值
                String melat = pref.getString("lat", "22.7344");
                String melng = pref.getString("lng", "120.284");
                score=pref.getString("score","0");

                Log.d("Fmap", lat_rest);
                Log.d("Fmap", lng_rest);
                Log.d("Fmap", melat);
                Log.d("Fmap", melng);
                Double distance= getDistance(Double.parseDouble(melat), Double.parseDouble(melng), Double.parseDouble(lat_rest), Double.parseDouble(lng_rest));


                HashMap<String, String> employees = new HashMap<>();
                employees.put(Config.TAG_ID, id);
                employees.put(Config.TAG_NAME, name);
                employees.put(Config.TAG_DESG, desg);
                employees.put(Config.TAG_SAL, sal);
                employees.put(Config.Tag_IMAGE, fbid);
                employees.put("lat_rest", lat_rest);
                employees.put("lng_rest", lng_rest);
                employees.put("restaurant",restaurant);
                employees.put("address",address);
                employees.put("fee",fee);
                employees.put("scoredata",scoredata);


                if (distance <=1000.0){
                    Log.d("Fmap", Double.toString(distance));

                    list.add(employees);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new Page3Adapter(
                getActivity(), list, R.layout.page3_list_item,
                new String[]{Config.TAG_ID, Config.TAG_NAME, Config.TAG_DESG, Config.TAG_SAL, Config.Tag_IMAGE},
                new int[]{R.id.id, R.id.name, R.id.desg, R.id.salary});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(), list.get(i).get(Config.TAG_ID)+"\n"+list.get(i).get(Config.TAG_DESG)+"\n", Toast.LENGTH_SHORT).show();


                openOptionsDialog(list.get(i).get(Config.TAG_NAME)
                        , list.get(i).get(Config.TAG_DESG),
                        list.get(i).get(Config.TAG_SAL),
                        list.get(i).get(Config.TAG_ID),
                        list.get(i).get("address"),
                         list.get(i).get("restaurant")   );


            }
        });
    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {


        float[] results = new float[1];


        Location.distanceBetween(lat1, lon1, lat2, lon2, results);

        return results[0];



    }
    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Fetching Data", "Wait...", false, false);
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
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();

    }


    private void openOptionsDialog(final String name, final String desg, final String money, final String id_for_order,String address,String restaurant) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(restaurant + "的訂單");
        // 承接傳過來的字串，顯示在對話框之中
        dialog.setMessage("要接收" + name + "的餐點嗎?");
        // 設定 PositiveButton 也就是一般 確定 或 OK 的按鈕

        final TextView input = new TextView(getActivity());
        input.setText("餐點：" +"\n"+ desg+"\n"+"對方地址:"+address);
        dialog.setView(input);

        dialog.setPositiveButton("幫忙跑腿", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                //通知對方

                Socket mSocket = null;
                {
                    try {
                        mSocket = IO.socket("http://140.127.218.212:3000");
                        //進去主機後,cd android..../ node index.js


                    } catch (URISyntaxException e) {
                    }
                }


                mSocket.emit("not", name, id_for_order, desg, money,score);


                //取得自己名字
                Intent getlogin = getActivity().getIntent();
                String myname = getlogin.getStringExtra("name");


                Intent to_messag = new Intent();
                to_messag.setClass(getActivity(), StartChat.class);
                to_messag.putExtra("myname", myname);
                to_messag.putExtra("target", name);
                to_messag.putExtra("ButtonShow", "no");
                startActivity(to_messag);


            }

        });
        //設定 NegativeButton 也就是一般 取消 或 Cancel 的按鈕
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                // 當使用者按下 取消鈕 後所執行的動作
                Toast.makeText(getActivity(), "取消訂單", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    } //EOF openOptionsDialog
}


//初始化參數
class Config {
    //Address of our scripts of the CRUD
    public static final String Dynamic_ADD_URL = "http://140.127.218.212/addJson.php";


    public static final String URL_ADD = "http://140.127.218.212/Android/CRUD/addEmp.php";
    public static final String URL_GET_ALL = "http://140.127.218.212/Android/CRUD/getAllEmp.php";
    public static final String URL_GET_EMP = "http://140.127.218.212/Android/CRUD/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://140.127.218.212/Android/CRUD/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://140.127.218.212/Android/CRUD/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "desg";
    public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String Tag_IMAGE = "fbid";
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESG = "desg";
    public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}


//請求連線
class RequestHandler {


    //Method to send httpPostRequest
    //This method is taking two arguments
    //First argument is the URL of the script to which we will send the request
    //Other is an HashMap with name value pairs containing the data to be send with the request
    public String sendPostRequest(String requestURL,
                                  HashMap<String, String> postDataParams) {
        //Creating a URL
        URL url;

        //StringBuilder object to store the message retrieved from the server
        StringBuilder sb = new StringBuilder();
        try {
            //Initializing Url
            url = new URL(requestURL);

            //Creating an httmlurl connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Configuring connection properties
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //Creating an output stream
            OutputStream os = conn.getOutputStream();

            //Writing parameters to the request
            //We are using a method getPostDataString which is defined below
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String response;
                //Reading server response
                while ((response = br.readLine()) != null) {
                    sb.append(response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String sendGetRequest(String requestURL) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public String sendGetRequestParam(String requestURL, String id) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestURL + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s + "\n");
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }



}



