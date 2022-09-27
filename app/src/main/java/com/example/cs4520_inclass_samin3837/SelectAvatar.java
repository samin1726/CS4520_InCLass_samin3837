package com.example.cs4520_inclass_samin3837;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectAvatar extends AppCompatActivity {

    private int selectedImage;
    private ImageView female1;
    private ImageView female2;
    private ImageView female3;
    private ImageView male1;
    private ImageView male2;
    private ImageView male3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        setTitle("Select Avatar");
        female1 = findViewById(R.id.female_1);
        female2 = findViewById(R.id.female_2);
        female3 = findViewById(R.id.female_3);
        male1 = findViewById(R.id.male_1);
        male2 = findViewById(R.id.male_2);
        male3 = findViewById(R.id.male_3);

        female1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_f_1;
                Intent toInClass02 = new Intent();
                toInClass02.putExtra("selectedImage", selectedImage);
                setResult(RESULT_OK, toInClass02);
                finish();
            }
        });

        female2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_f_2;
                Intent toInClass02 = new Intent();
                toInClass02.putExtra("selectedImage", selectedImage);
                setResult(RESULT_OK, toInClass02);
                finish();
            }
        });

        female3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_f_3;
                Intent toInClass02 = new Intent();
                toInClass02.putExtra("selectedImage", selectedImage);
                setResult(RESULT_OK, toInClass02);
                finish();
            }
        });

        male1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_m_1;
                Intent toInClass02 = new Intent();
                toInClass02.putExtra("selectedImage", selectedImage);
                setResult(RESULT_OK, toInClass02);
                finish();
            }
        });

        male2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_m_2;
                Intent toInClass02 = new Intent();
                toInClass02.putExtra("selectedImage", selectedImage);
                setResult(RESULT_OK, toInClass02);
                finish();
            }
        });

        male3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_m_3;
                Intent toInClass02 = new Intent();
                toInClass02.putExtra("selectedImage", selectedImage);
                setResult(RESULT_OK, toInClass02);
                finish();
            }
        });
    }
}