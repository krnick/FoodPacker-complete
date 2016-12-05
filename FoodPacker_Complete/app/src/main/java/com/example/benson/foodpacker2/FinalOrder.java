package com.example.benson.foodpacker2;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Benson on 2016/11/21.
 */
public class FinalOrder extends AppCompatActivity implements View.OnClickListener {
    String fee,address;
    Button bn, sendout;
    String rest_name;
    String lat, lng;
    String all = "", facebookname;
    private List<Product> datas; //數據源
    private List<Product> my_picked; //數據源2
    private ShopAdapter adapter; //自定義適配器
    private ListView listView; //ListView控件
    private Button checkbn;
    private TextView money;
    int all_money;

    private final String[] titleArr = new String[]{
            "燒肉珍珠堡",
            "海洋珍珠堡",
            "好吃火雞堡",
            "薑燒珍珠堡",
            "厚切培根和牛堡",
            "蜜汁烤雞堡",
            "好吃鱈魚堡",
            "黃金炸蝦堡",
            "百香果熱狗堡",
            "辣味吉利熱狗堡",
            "辛味章魚堡",
            "柚香大阪燒珍珠堡",
            "雙倍吉士漢堡",
    };

    private final int[] moneyArr = new int[]{
            70,
            75,
            90,
            65,
            100,
            65,
            70,
            75,
            55,
            70,
            90,
            90,
            70,
    };

    private final String [] titleArr2 = new String[] {
            "美式燻雞",
            "醬燒肉排",
            "OREO鮮奶油鬆餅",
            "藍莓厚片",
            "酸菜蔥抓餅",
            "火腿蔥抓餅",
            "咖哩章魚潛艇堡",
            "莎莎魚排潛艇堡",
            "花生酥皮可頌",
            "香腸玉子燒總匯",
            "巧克力貝果",
    };

    private final int [] moneyArr2 = new int[] {
            33,
            35,
            43,
            25,
            30,
            30,
            53,
            68,
            28,
            48,
            25,
    };
    private final String [] titleArr3 = new String[] {
            "豬肉煎餃",
            "牛肉煎餃",
            "花素煎餃",
            "鍋貼",
            "豬肉捲餅",
            "牛肉捲餅",
            "蔥油餅",
            "烙餅",
            "清燉雞肉湯",
            "清燉牛肉湯",
            "酸辣湯",
            "玉米濃湯",
    };
    private final int [] moneyArr3 = new int[] {
            45,
            55,
            55,
            45,
            55,
            55,
            25,
            35,
            35,
            35,
            25,
            25,
    };
    private final String [] titleArr4 = new String[] {
            "傳統豬排飯",
            "滷雞腿飯",
            "照燒豬排飯",
            "綜合雙拼飯",
            "香雞排飯",
            "椒麻雞飯",
            "控肉飯",
            "咖哩豬排飯",
            "香酥鱈魚排飯",
            "酥炸雞腿飯",
            "宮保雞丁飯",
            "醬爆肉絲飯",
            "油蔥雞飯",
            "番茄起士豬排飯",
    };
    private final int [] moneyArr4 = new int[] {
            50,
            60,
            60,
            60,
            60,
            65,
            65,
            65,
            70,
            70,
            70,
            70,
            80,
            75,
    };
    private final String [] titleArr5 = new String[] {
            "黃金豬排蓋飯",
            "黃金雞柳蓋飯",
            "和風豬排蓋飯",
            "和風牛肉蓋飯",
            "蔬菜蓋飯",
            "黃金豬排咖哩飯",
            "黃金雞柳咖哩飯",
            "豬肉咖哩飯",
            "牛肉咖哩飯",
            "雞肉咖哩飯",
            "黃金豬排",
            "黃金雞柳",
            "香酥手工黑輪",
    };
    private final int [] moneyArr5 = new int[] {
            80,
            80,
            70,
            70,
            70,
            80,
            80,
            70,
            70,
            70,
            55,
            55,
            20,
    };

    private int[] fuck =new int[]{};

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bundle bundle = getIntent().getExtras();
        facebookname = bundle.getString("facebookname");
        lat = bundle.getString("lat");
        lng = bundle.getString("lng");
        rest_name = bundle.getString("rest_name");
        ArrayList<String> order = bundle.getStringArrayList("order");
        /*--- 保留 ---*/

        listView = (ListView) findViewById(R.id.listView);
        money = (TextView) findViewById(R.id.money_all);

        // 模擬數據，將餐點與價錢對應之後放到datas中
        datas = new ArrayList<>();
        Product product = null;
        switch (rest_name){
            case "超好吃餐廳":
                for (int i = 0; i < titleArr.length; i++) {
                    product = new Product();
                    product.setName("title", titleArr[i]);
                    product.setNum(1);
                    product.setPrice(moneyArr[i]);
                    datas.add(product);
                }
                break;
            case "樂活早午餐":
                for (int i = 0; i < titleArr2.length; i++) {
                    product = new Product();
                    product.setName("title", titleArr2[i]);
                    product.setNum(1);
                    product.setPrice(moneyArr2[i]);
                    datas.add(product);
                }
                break;
            case "遠得要命餐廳-羊寶寶":
                for (int i = 0; i < titleArr3.length; i++) {
                    product = new Product();
                    product.setName("title", titleArr3[i]);
                    product.setNum(1);
                    product.setPrice(moneyArr3[i]);
                    datas.add(product);
                }
                break;
            case "阿茶便當":
                for (int i = 0; i < titleArr4.length; i++) {
                    product = new Product();
                    product.setName("title", titleArr4[i]);
                    product.setNum(1);
                    product.setPrice(moneyArr4[i]);
                    datas.add(product);
                }
                break;
            case "蓋飯先生":
                for (int i = 0; i < titleArr5.length; i++) {
                    product = new Product();
                    product.setName("title", titleArr5[i]);
                    product.setNum(1);
                    product.setPrice(moneyArr5[i]);
                    datas.add(product);
                }
                break;
        }

        ArrayList<String> picked_name = new ArrayList<>();
        ArrayList<Integer> picked_price = new ArrayList<>();

        for (int i = 0; i < order.size(); i++) {

            String order_string = order.get(i).toString();

            for (int j = 0; j < datas.size(); j++) {

                String datas_string = datas.get(j).getName();

                if (order_string.equals(datas_string)) {

//                    my_picked.add(datas.get(j).toString());
                    picked_name.add(datas.get(j).getName());
                    picked_price.add(datas.get(j).getPrice());
                }
            }
        }

        my_picked = new ArrayList<>();
        Product product2 = null;
        for (int i = 0; i < picked_name.size(); i++) {
            product2 = new Product();
            product2.setName("title", picked_name.get(i).toString());
            product2.setNum(1);
            product2.setPrice(picked_price.get(i));
            my_picked.add(product2);
        }
//        Log.d("picked_name", picked_name.toString());
        fuck =new int[picked_price.size()];
        for (int i =0; i<picked_price.size(); i++){
//            price2[i]=picked_price.get(i).intValue();
            fuck[i]=picked_price.get(i).intValue();
            Log.d("picked_price","fuck "+picked_price.get(i).intValue());
        }
//        Log.d("urPickedPrice",urPickedPrice.toString());

        adapter = new ShopAdapter(my_picked, this);
        listView.setAdapter(adapter);

        adapter.setOnAddNum(this);
        adapter.setOnSubNum(this);


        //結帳按鈕
        checkbn = (Button) findViewById(R.id.checkout);
        checkbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all_money=0;

                ArrayList<String> put = new ArrayList<>();
                ListAdapter listAdapter = listView.getAdapter();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    View v = listAdapter.getView(i, null, null);

                    TextView nameview = (TextView) v.findViewById(R.id.item_product_name);
                    TextView priceview = (TextView) v.findViewById(R.id.item_product_price);
                    TextView numview = (TextView) v.findViewById(R.id.item_product_num);

                    all_money = all_money + Integer.parseInt(priceview.getText().toString());
                    String output = nameview.getText().toString() + " x " + numview.getText().toString();
//                    Log.d("output", numview.getText().toString());
                    put.add(output);
                }

                for (int i = 0; i < put.size(); i++) {
                    all += (put.get(i) + "\n");
                }
//                Log.d("all", all);
//                Toast.makeText(FinalOrder.this, Integer.toString(all_money), Toast.LENGTH_SHORT).show();
                money.setText(Integer.toString(all_money));
//                checkbn.setEnabled(false);
                sendout = (Button) findViewById(R.id.sendout);
                sendout.setVisibility(View.VISIBLE);

//                all_money=0;
            }
        });

        sendout = (Button) findViewById(R.id.sendout);
        sendout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowDialog();

            }
        });
    }

    //加減
    @Override
    public void onClick(View view) {
        Object tag = view.getTag();
        switch (view.getId()) {
            case R.id.item_btn_add: //點擊添加數量按鈕，執行相應的處理
                //獲取Adapter中設置的Tag
                if (tag != null && tag instanceof Integer) {

                    //解決問題：如何知道你點擊的按鈕是哪一個列表項中的，通過Tag的position
                    int position = (Integer) tag;
                    // 更改集合的數據
                    int num = my_picked.get(position).getNum();
//                    final int price =my_picked.get(position).getPrice();
                    num++;
//                    Log.d("num","price"+price);
                    my_picked.get(position).setNum(num); // 修改集合中商品數量
                    my_picked.get(position).setPrice(fuck[position] * num); // 修改集合中該商品總價數量*單價
                    //解決問題：點擊某個按鈕的時候，如果列表項所需的數據改變了，如何更新UI
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.item_btn_sub: //點擊減少數量按鈕，執行相應的處理
                // 獲取Adapter中設置的Tag
                if (tag != null && tag instanceof Integer) {
                    int position = (Integer) tag;
                    // 更改集合的數據
                    int num = my_picked.get(position).getNum();
//                    int price =my_picked.get(position).getPrice();
                    if (num > 0) {
                        num--;
                        my_picked.get(position).setNum(num); // 修改集合中商品數量
                        my_picked.get(position).setPrice(fuck[position] * num); // 修改集合中該商品總價數量*單價
                        adapter.notifyDataSetChanged();
                    }
                }
                break;


        }
    }

    //Adding an employee   page3
    public void addEmployee() {

        //宣告SharedPreferences紀錄的name
        SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
        //第一個參數是欲讀取的資料名稱，第二個參數是沒讀到的回傳值
        final String FBname = pref.getString("FBname", "NO_VALUE");
        String FBlink = pref.getString("FBlink", "NO_VALUE");
        final String FBid = pref.getString("FBid", "NO_VALUE");
        String FBmail = pref.getString("FBmail", "NO_VALUE");
        final String score=pref.getString("score","0");


        final String desg = all;
        final String sal = Integer.toString(all_money) + "元";

        class AddEmployee extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                Toast.makeText(FinalOrder.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME, FBname);
                params.put(Config.KEY_EMP_DESG, desg);
                params.put(Config.KEY_EMP_SAL, sal);
                params.put(Config.Tag_IMAGE, FBid);
                params.put("lat", lat);
                params.put("lng", lng);
                params.put("restaurant",rest_name);
                params.put("address",address);
                params.put("fee",fee);
                params.put("score",score);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


    //Adding an Dynamic   page2
    public void addDy() {

        //宣告SharedPreferences紀錄的name
        SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
        //第一個參數是欲讀取的資料名稱，第二個參數是沒讀到的回傳值
        String FBid = pref.getString("FBid", "NO_VALUE");


        final String FBname = pref.getString("FBname", "NO_VALUE");



        final String desg = FBid;
        final String sal = rest_name;
        final String menu_page2 = all;

        class Dy extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(FinalOrder.this,"成功下單", Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME, FBname);
                params.put(Config.KEY_EMP_DESG, desg);
                params.put(Config.KEY_EMP_SAL, sal);
                params.put("menu_page2", menu_page2);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://140.127.218.212/test.php", params);
                return res;
            }

        }

        Dy ae = new Dy();
        ae.execute();
    }




    //客製化 提交
    public void ShowDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_addesss_fee);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));




        TextView text = (TextView) dialog.findViewById(R.id.txtDiaTitle);
        TextView image = (TextView) dialog.findViewById(R.id.txtDiaMsg);
        final EditText topic = ( EditText) dialog.findViewById(R.id.topic);
        final EditText topic1 = ( EditText) dialog.findViewById(R.id.topic1);


        Button dialogButton = (Button) dialog.findViewById(R.id.btnOk);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 fee = topic.getText().toString();
                 address = topic1.getText().toString();



                addEmployee();
                addDy();
                DataCopy();
//                Intent turn_to_main = new Intent();
//                turn_to_main.setClass(getApplicationContext(), MainActivity.class);
//                startActivity(turn_to_main);
                finish();


                dialog.dismiss();

            }
        });
        dialog.show();


    }

    //資料分析
    public void DataCopy() {

        //宣告SharedPreferences紀錄的name
        SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
        //第一個參數是欲讀取的資料名稱，第二個參數是沒讀到的回傳值
        final String FBname = pref.getString("FBname", "NO_VALUE");
        String FBlink = pref.getString("FBlink", "NO_VALUE");
        final String FBid = pref.getString("FBid", "NO_VALUE");
        String FBmail = pref.getString("FBmail", "NO_VALUE");
        final String score=pref.getString("score","0");


        final String desg = all;
        final String sal = Integer.toString(all_money) + "元";

        class Data extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                Toast.makeText(FinalOrder.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME, FBname);
                params.put(Config.KEY_EMP_DESG, desg);
                params.put(Config.KEY_EMP_SAL, sal);
                params.put(Config.Tag_IMAGE, FBid);
                params.put("lat", lat);
                params.put("lng", lng);
                params.put("restaurant",rest_name);
                params.put("address",address);
                params.put("fee",fee);
                params.put("score",score);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://140.127.218.212/Android/CRUD/Data.php", params);
                return res;
            }
        }

        Data ae = new Data();
        ae.execute();
    }


}

