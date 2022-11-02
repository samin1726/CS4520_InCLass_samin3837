package com.example.cs4520_inclass_samin3837.inClass07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs4520_inclass_samin3837.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class inClass07 extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    Button loginButton;
    Button registerButton;
    int clicksRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class07);
        setTitle("Notes");

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        clicksRegisterButton = 0;

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicksRegisterButton == 0 ) {
                    name.setVisibility(View.VISIBLE);
                    clicksRegisterButton += 1;
                }
                else if (clicksRegisterButton == 1) {
                    name.setVisibility(View.INVISIBLE);
                    OkHttpClient client = new OkHttpClient();
                    RequestBody formBody = new FormBody.Builder()
                            .add("name", name.getText().toString())
                            .add("email", email.getText().toString())
                            .add("password", password.getText().toString())
                            .build();
                    Request request = new Request.Builder()
                            .url("http://dev.sakibnm.space:3000/api/auth/register")
                            .post(formBody)
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        }
                    });
                    clicksRegisterButton = 0;
                }
            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("email", email.getText().toString())
                        .add("password", password.getText().toString())
                        .build();
                Request request = new Request.Builder()
                        .url("http://dev.sakibnm.space:3000/api/auth/login")
                        .post(formBody)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            Reader charStream = response.body().charStream();
                            Gson gsonData = new Gson();
                            Authorization auth = gsonData.fromJson(charStream, Authorization.class);
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url("http://dev.sakibnm.space:3000/api/note/getall")
                                    .addHeader("x-access-token", auth.getToken())
                                    .build();
                            client.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    e.printStackTrace();
                                    Log.d("demo", "onResponse: onFailure ");
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        ResponseBody responseBody = response.body();
                                        Gson gsonData = new Gson();
                                        Log.d("demo", "onResponse: Response Successful");
                                        Notes notes = gsonData.fromJson(responseBody.charStream(), Notes.class);
                                        Log.d("demo", "onResponse: " + notes.toString());
                                        Intent intent = new Intent(inClass07.this, NotesDisplayActivity.class);
                                        intent.putExtra("Notes", notes);
                                        intent.putExtra("Access Token", auth.getToken());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                    else {
                                        Log.d("demo", "onResponse: Failure " + response.code());
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(inClass07.this, response.body().string(), Toast.LENGTH_LONG);
                        }
                    }
                });
            }
        });

    }
}