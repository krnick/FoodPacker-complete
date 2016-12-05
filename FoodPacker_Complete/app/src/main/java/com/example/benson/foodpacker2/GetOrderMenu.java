package com.example.benson.foodpacker2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Benson on 2016/11/21.
 */
public class GetOrderMenu extends AppCompatActivity {
    ArrayList<String> mylist = new ArrayList<String>();
    ArrayAdapter<String> listAdapter;
    String facebookname;
    TextView RestaurantName;
    ListView foodlist;
    SparseBooleanArray sparseBooleanArray;
    //listview 名單
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_order_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent getFromMap=getIntent();
        final String title=  getFromMap.getStringExtra("title");
        String snippet=getFromMap.getStringExtra("snippet");
        final String lat=getFromMap.getStringExtra("lat");
        final String lng=getFromMap.getStringExtra("lng");


        facebookname=getFromMap.getStringExtra("facebookname");
        Toast.makeText(GetOrderMenu.this, title+"\n"+snippet, Toast.LENGTH_SHORT).show();

        RestaurantName = (TextView) findViewById(R.id.RestaurantName);
        RestaurantName.setText(title);

        final String[] food = {
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
        final String [] food2 = {
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
        final String [] food3 = {
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
        final String [] food4 = {
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
        final String [] food5 = {
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
        switch (title){
            case "超好吃餐廳":
                listAdapter = new ArrayAdapter<String>(GetOrderMenu.this, android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, food );
                break;
            case "樂活早午餐":
                listAdapter = new ArrayAdapter<String>(GetOrderMenu.this, android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, food2 );
                break;
            case "遠得要命餐廳-羊寶寶":
                listAdapter = new ArrayAdapter<String>(GetOrderMenu.this, android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, food3 );
                break;
            case "阿茶便當":
                listAdapter = new ArrayAdapter<String>(GetOrderMenu.this, android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, food4 );
                break;
            case "蓋飯先生":
                listAdapter = new ArrayAdapter<String>(GetOrderMenu.this, android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, food5 );
                break;
        }

        foodlist = (ListView)findViewById(R.id.foodlist);

        foodlist.setAdapter(listAdapter);

        Button order_btn = (Button) findViewById(R.id.order_btn);

        order_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mylist.clear();
                int cntChoice = foodlist.getCount();
                sparseBooleanArray = foodlist.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++){

                    if(sparseBooleanArray.get(i)) {

                        mylist.add(foodlist.getItemAtPosition(i).toString());

                    }

                }
                Log.d("mylist",mylist.toString());

                Intent gotofinalorder = new Intent();
                gotofinalorder.setClass(getApplicationContext(), FinalOrder.class);


                Bundle bundle = new Bundle();
                bundle.putStringArrayList("order", mylist);
                bundle.putString("facebookname",facebookname);
                bundle.putString("lat",lat);
                bundle.putString("lng",lng);
                bundle.putString("rest_name",title);
                //將Bundle物件assign給intent
                gotofinalorder.putExtras(bundle);

                //切換Activity
                startActivity(gotofinalorder);
                finish();




            }
        });
//        ll.addView(bn);


    }
}
