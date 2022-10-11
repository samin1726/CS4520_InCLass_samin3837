package com.example.cs4520_inclass_samin3837;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs4520_inclass_samin3837.inClass01.InClass01;
import com.example.cs4520_inclass_samin3837.inClass02.EditProfile;

public class MainActivity extends AppCompatActivity {

    private Button inClass1;
    private Button inClass2;
    private Button inClass3;
    private Button inClass4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inClass1 = findViewById(R.id.inClass01);
        inClass2 = findViewById(R.id.inClass02);
        inClass3 = findViewById(R.id.inClass03);
        inClass4 = findViewById(R.id.inClass04);

        inClass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass01.class);
                startActivity(intent);
            }
        });

        inClass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditProfile.class);
                startActivity(intent);
            }
        });

        inClass3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditProfile.class);
                startActivity(intent);
            }
        });

        inClass4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass04.class);
                startActivity(intent);
            }
        });



    }
}