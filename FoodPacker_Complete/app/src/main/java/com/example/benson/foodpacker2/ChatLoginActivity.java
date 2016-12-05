package com.example.benson.foodpacker2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class ChatLoginActivity extends Activity {


    private String targetname ;

    private String username;

    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_login);


        Intent getFromChatFragment=getIntent();

        username=getFromChatFragment.getStringExtra("myname");
        targetname=getFromChatFragment.getStringExtra("target");




        //取得socket
        ChatApplication app = (ChatApplication) getApplication();
        mSocket = app.getSocket();


        mSocket.emit("add user", username, targetname);

        mSocket.on("login", onLogin);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSocket.off("login", onLogin);
    }


    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];

            int numUsers;
            try {
                numUsers = data.getInt("numUsers");
            } catch (JSONException e) {
                return;
            }


            Intent intent = new Intent();
            intent.putExtra("username", username);
            intent.putExtra("numUsers", numUsers);
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}



