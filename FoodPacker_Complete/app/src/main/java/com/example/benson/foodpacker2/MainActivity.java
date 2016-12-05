package com.example.benson.foodpacker2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private String JSON_STRING;
    TextView Textemail, Textname;
    FButton logoutBtn;
    float total_score=0;
    float count_loop = 0;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    String name;
    String FBname;
    private int[] imageResId = {
            R.drawable.utility_selector,
            R.drawable.friend_selector,
            R.drawable.receive_selector,
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent getlogin = getIntent();
        name = getlogin.getStringExtra("name");
        String email = getlogin.getStringExtra("email");
        String id = getlogin.getStringExtra("id");

        //宣告SharedPreferences紀錄的name
        SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
        //第一個參數是欲讀取的資料名稱，第二個參數是沒讀到的回傳值
         FBname = pref.getString("FBname", "NO_VALUE");
        String FBlink = pref.getString("FBlink", "NO_VALUE");
        String FBid = pref.getString("FBid", "NO_VALUE");
        String FBmail=pref.getString("FBmail","NO_VALUE");



        /*toolbar*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        /*drawer toggle*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*navigation*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*viewpager*/
        viewPager = (ViewPager) findViewById(R.id.viewPager);// Get the ViewPager and set it's PagerAdapter so that it can display items
        setupViewPager(viewPager);

        /*tablayout*/
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewPager);
        setTabIcons();

        /*---button test---*/
        logoutBtn = (FButton) findViewById(R.id.logoutBtn);
        logoutBtn.setButtonColor(getResources().getColor(R.color.darkpink));
        logoutBtn.setShadowColor(getResources().getColor(R.color.shadowpink));
        logoutBtn.setShadowEnabled(true);
        logoutBtn.setShadowHeight(10);
        logoutBtn.setCornerRadius(10);
        logoutBtn.setOnClickListener(this);

        /*---FB user profile---*/
        ProfilePictureView profilePictureView;
        profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);
        profilePictureView.setProfileId(FBid);

        Textname = (TextView) findViewById(R.id.nav_usr_name);
        Textemail = (TextView) findViewById(R.id.nav_usr_email);
        Textname.setText(FBname);
        Textemail.setText(FBmail);


        //取得評價分數
        getJSON();



//        連上 server 接收通知

        String myname = name;

        Socket mSocket = null;
        {
            try {
                mSocket = IO.socket("http://140.127.218.212:3000");
                //進去主機後,cd android..../ node index.js


            } catch (URISyntaxException e) {
            }
        }

        mSocket.connect();
        //自己名字
        mSocket.emit("user", myname);

        mSocket.on("new message", onNewMessage);
        mSocket.on("deal", ondeal);

//        連上 server 接收通知





    }

    ///接收到通知
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            JSONObject data = (JSONObject) args[0];
            String catch_order = null;
            String delete_id = null;
            String order_food=null;
            String money_for_order=null;
            String score=null;
            try {
                catch_order = data.getString("hello");
                delete_id = data.getString("id");
                order_food=data.getString("desg");
                money_for_order=data.getString("money");
                score=data.getString("score");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Intent to_message_when_onclick_notification = new Intent(getApplicationContext(), StartChat.class);
            to_message_when_onclick_notification.putExtra("target", catch_order);
            to_message_when_onclick_notification.putExtra("myname", FBname);
            to_message_when_onclick_notification.putExtra("id", delete_id);  //拿去刪除id

            to_message_when_onclick_notification.putExtra("order_food",order_food);//菜單
            to_message_when_onclick_notification.putExtra("money_for_order",money_for_order);//價格


            to_message_when_onclick_notification.putExtra("ButtonShow", "yes");
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),
                    0, to_message_when_onclick_notification,
                    PendingIntent.FLAG_CANCEL_CURRENT);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.drawable.smallicon)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.foodpacker_icon))
                    .setContentTitle("FoodPacker 訂單成立")
                    .setContentText(catch_order + "接單"+"\n"+"對方平均評價:"+score)//對方
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(contentIntent);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(0, builder.build());

        }
    };   //EOF NOTIFICATION
    ///接收到通知
    private Emitter.Listener ondeal = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            JSONObject data = (JSONObject) args[0];
            String catch_order = null;
            try {
                catch_order = data.getString("hello");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Intent to_message_when_onclick_notification = new Intent(getApplicationContext(), MainActivity.class);

            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),
                    0, to_message_when_onclick_notification,
                    PendingIntent.FLAG_CANCEL_CURRENT);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.drawable.smallicon)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.foodpacker_icon))
                    .setContentTitle("FoodPacker 訂單成立")
                    .setContentText(catch_order + "交易已成功")//對方
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(contentIntent);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(0, builder.build());

        }
    };   //EOF NOTIFICATION

    protected void setTabIcons() {
        tabLayout.getTabAt(0).setIcon(imageResId[0]);
        tabLayout.getTabAt(1).setIcon(imageResId[1]);
        tabLayout.getTabAt(2).setIcon(imageResId[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MapFragment(), "Map");
        viewPagerAdapter.addFragment(new FriendFragment(), "Friend");
        viewPagerAdapter.addFragment(new ReceiveFragment(), "Receive");
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_first) {

            Intent to_continus = new Intent();
            to_continus.setClass(this, Continus_Order.class);
            to_continus.putExtra("facebookname", name);
            startActivity(to_continus);

        } else if (id == R.id.nav_second) {


            Intent to_History = new Intent();
            to_History.setClass(this, History_person.class);
            to_History.putExtra("facebookname", name);
            startActivity(to_History);


        } else if (id == R.id.nav_third) {

            Intent to_person = new Intent();
            to_person.setClass(this, Person_Page.class);
            startActivity(to_person);

        }else if(id==R.id.nav_four){
            Intent to_hot = new Intent();
            to_hot.setClass(this, HotRest.class);
            startActivity(to_hot);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == logoutBtn) {
            if (view == logoutBtn) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(MainActivity.this, splashscreen.class);
                startActivity(login);
                finish();
            }
        }
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
//                    Log.d("T", Myname + Cname + value + comment);

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

            String score= Float.toString(total_score/count_loop);


            //宣告SharedPreferences紀錄的name
            SharedPreferences pref = getSharedPreferences("PREF_SESSION", MODE_PRIVATE);
//必須有一個edit來存
            SharedPreferences.Editor preEdt = pref.edit();
//所要記錄的資料 (也可以是int), 第一個參數是該筆資料的name,後面是value
            if(score.length()>2){
                preEdt.putString("score",score.substring(0,3));
            }else {
                preEdt.putString("score",score);
            }

            preEdt.commit();




        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListAdapter adapter = new SimpleAdapter(
                this, list, R.layout.person_page,
                new String[]{"Cname", "value", "comment"},
                new int[]{R.id.name, R.id.desg, R.id.salary});


    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, " loading..", "Wait...", false, false);
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
