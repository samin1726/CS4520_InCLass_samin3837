package com.example.cs4520_inclass_samin3837;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button logCat;
    Button toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logCat = findViewById(R.id.button);
        toast = findViewById(R.id.button2);

        logCat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Log.d("logcat", "“Practice!Practice!!Practice!!!”");
            }
        });

        toast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Now push to GitHub and Submit!", Toast.LENGTH_LONG).show();
            }
        });
    }
}