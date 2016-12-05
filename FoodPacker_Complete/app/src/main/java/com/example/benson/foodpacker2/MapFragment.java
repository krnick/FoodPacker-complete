package com.example.benson.foodpacker2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends SupportMapFragment implements
        OnMapReadyCallback {
    private final LatLng HAMBURG = new LatLng(53.558, 9.927);
    private final LatLng KIEL = new LatLng(53.551, 9.993);

    private static final String ARG_SECTION_NUMBER = "section_number";

    private GoogleMap mMap;
    private Marker marker;

    //String user_photo_url = "R.drawable.burger";
    LatLng latLng = new LatLng(22.734355, 120.284497);//高大
    LatLng far_far_away = new LatLng(22.727507, 120.306939);//羊寶寶
    LatLng life_brunch = new LatLng(22.732163,120.285291);//樂活早午餐
    LatLng tea = new LatLng(22.723899,120.297627); //阿茶
    LatLng GaiFan= new LatLng(22.727122,120.292013); //蓋飯

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("MyMap", "onResume");
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        if (mMap == null) {

            Log.d("MyMap", "setUpMapIfNeeded");

            getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("MyMap", "onMapReady");
        mMap = googleMap;
        setUpMap();


    }

    private void setUpMap() {

        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


        UiSettings ui = mMap.getUiSettings();
        ui.setZoomControlsEnabled(true);

        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        mMap.setMyLocationEnabled(true);



        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.burger_icon))
                .title("超好吃餐廳")
                .snippet("高雄市楠梓區大學南路700號"));


        mMap.addMarker(new MarkerOptions()
                .position(far_far_away)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.burger_icon))
                .title("遠得要命餐廳-羊寶寶")
                .snippet("高雄市楠梓區朝明路106號"));

        mMap.addMarker(new MarkerOptions()
                .position(life_brunch)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.burger_icon))
                .title("樂活早午餐")
                .snippet("高雄市楠梓區大學南路613號"));

        mMap.addMarker(new MarkerOptions()
                .position(tea)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.burger_icon))
                .title("阿茶便當")
                .snippet("高雄市楠梓區益群路18號"));

        mMap.addMarker(new MarkerOptions()
                .position(GaiFan)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.burger_icon))
                .title("蓋飯先生")
                .snippet("高雄市楠梓區藍昌路398-15號"));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
//                Toast.makeText(getActivity(), "餐廳："+marker.getTitle()+"地點:"+marker.getSnippet()+"position"+marker.getPosition(), Toast.LENGTH_SHORT).show();

                openOptionsDialog(marker);


            }
        });

//        Picasso.with(getActivity())
//                .load(user_photo_url)
//                .resize(250, 250)
//                .centerCrop()
//                .transform(new BubbleTransformation(20))
//                .into(mTarget);


    }

    Target mTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            //LatLng location=new LatLng(22,120);

//            Marker driver_marker = mMap.addMarker(new MarkerOptions()
//                    .position(latLng)
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.burger))
//                    .title("超好吃餐廳")
//                    .snippet("高雄大學路1000號")
//            );


        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            Log.d("picasso", "onBitmapFailed");
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


    private void openOptionsDialog(final Marker marker) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());





        dialog.setTitle(marker.getTitle());
        // 承接傳過來的字串，顯示在對話框之中
        dialog.setMessage("要下訂" + marker.getTitle() + "的餐點嗎?");
        // 設定 PositiveButton 也就是一般 確定 或 OK 的按鈕
        dialog.setPositiveButton("前往下訂", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {


                //new一個intent物件，並指定Activity切換的class
                Intent intent = new Intent();
                intent.setClass(getActivity(), GetOrderMenu.class);

                //new一個Bundle物件，並將要傳遞的資料傳入
                Bundle bundle = new Bundle();
                bundle.putString("title", marker.getTitle());
                bundle.putString("snippet",marker.getSnippet());
                bundle.putString("lat",  Double.toString(marker.getPosition().latitude) );
                bundle.putString("lng",  Double.toString(marker.getPosition().longitude) );




                //FB
                Intent getlogin = getActivity().getIntent();
                String name = getlogin.getStringExtra("name");
                String email = getlogin.getStringExtra("email");
                String id = getlogin.getStringExtra("id");


                bundle.putString("facebookname",name);

                //將Bundle物件assign給intent
                intent.putExtras(bundle);

                //切換Activity
                getActivity().startActivity(intent);
//                getActivity().onBackPressed();


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
