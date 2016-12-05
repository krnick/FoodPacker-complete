
	}ckage com.example.nick.socketio;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://140.127.218.212:3000");
            //進去主機後,cd android..../ node index.js


        } catch (URISyntaxException e) {}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSocket.connect();
        //自己名字
        mSocket.emit("user","ben");

        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(this);



        mSocket.on("new message", onNewMessage);
    }

    @Override
    public void onClick(View view) {
        //對方名字
        mSocket.emit("not","nick");
    }


    private Emitter.Listener onNewMessage=new Emitter.Listener() {
        @Override
        public void call(Object... args) {



            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.drawable.test)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.test))
                    .setColor(getResources().getColor(R.color.colorPrimaryDark))
                    .setContentTitle("FooPacker 訂單成立")
                    .setContentText("接單")
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(Notification.PRIORITY_HIGH);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(0, builder.build());

        }
    };
}
)
