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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //BTN ONE
        Button buttonToIntents = findViewById(R.id.BtntoIntentsActivity);
        buttonToIntents.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               //Intent to the Second Activity
               Intent intent = new Intent(MainActivity.this, Hextree_IntentsActivity.class);
               startActivity(intent);
            }
        });

        /// /////////////////////////////////


        //BTN TWO
        Button buttonToDeeplinks = findViewById(R.id.BtntoDeeplinksActivity);
        buttonToDeeplinks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent to the Second Activity
                Intent intent = new Intent(MainActivity.this, DeeplinksActivity.class);
                startActivity(intent);
            }
        });

        /// /////////////////////////////////

        //BTN Three
        Button buttonToBroadcastReceiversActivity = findViewById(R.id.BtntoBroadcastReceiversActivity);
        buttonToBroadcastReceiversActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent to the Second Activity
                Intent intent = new Intent(MainActivity.this, BroadcastReceiversActivity.class);
                startActivity(intent);
            }
        });

/// /////////////////////////////////

        //BTN Three
        Button buttonToServicesActivity = findViewById(R.id.BtntoServicesActivity);
        buttonToServicesActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent to the Second Activity
                Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
                startActivity(intent);
            }
        });

/// /////////////////////////////////

        //BTN Three
        Button buttonToContentProvidersActivity = findViewById(R.id.BtntoContentProvidersActivity);
        buttonToContentProvidersActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent to the Second Activity
                Intent intent = new Intent(MainActivity.this, ContentProvidersActivity.class);
                startActivity(intent);
            }
        });


/// /////////////////////////////////

        //BTN Three
        Button buttonToWebviewsActivity = findViewById(R.id.BtntoWebviewsActivity);
        buttonToWebviewsActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent to the Second Activity
                Intent intent = new Intent(MainActivity.this, WebviewsActivity.class);
                startActivity(intent);
            }
        });

    }
}