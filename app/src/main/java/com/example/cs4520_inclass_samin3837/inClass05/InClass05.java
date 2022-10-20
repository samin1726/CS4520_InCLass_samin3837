package com.example.cs4520_inclass_samin3837.inClass05;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs4520_inclass_samin3837.R;
import com.example.cs4520_inclass_samin3837.R.string;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InClass05 extends AppCompatActivity {

    private TextView view1;
    private Button go;
    private EditText search;
    private String[] places;
    private String[] imageURLS;
    private ImageView scrollLeft;
    private ImageView scrollRight;
    private ImageView placeImage;
    private int currentImageIndex = 0;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class05);
        setTitle(string.image_search);

        view1 = findViewById(R.id.textView);
        go = findViewById(R.id.goButton);
        search = findViewById(R.id.search);
        scrollLeft = findViewById(R.id.scrollLeft);
        scrollRight = findViewById(R.id.scrollRight);
        placeImage = findViewById(R.id.placeImage);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://dev.sakibnm.space/apis/images/keywords")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String body = response.body().string();
                                places = body.split(",");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected()) {
                    if (places == null) {
                        Toast.makeText(InClass05.this, "No Internet Connection! Try Again", Toast.LENGTH_LONG).show();
                    } else if (Arrays.asList(places).contains(search.getText().toString())) {
                        String keyword = search.getText().toString();
                        HttpUrl url = HttpUrl.parse("http://dev.sakibnm.space/apis/images/retrieve").newBuilder()
                                .addQueryParameter("keyword", keyword)
                                .build();
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(url)
                                    .build();
                            client.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        String body = response.body().string();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                view1.setVisibility(View.VISIBLE);
                                                view1.setText("Loading...");
                                                progressBar.setVisibility(View.VISIBLE);
                                                imageURLS = body.split("\n");
                                                String urlString = Arrays.asList(imageURLS).get(0);
                                                Picasso.get()
                                                        .load(urlString)
                                                        .into(placeImage, new com.squareup.picasso.Callback() {
                                                            @Override
                                                            public void onSuccess() {
                                                                view1.setVisibility(View.INVISIBLE);
                                                                progressBar.setVisibility(View.GONE);
                                                            }

                                                            @Override
                                                            public void onError(Exception e) {

                                                            }
                                                        });
                                            }
                                        });
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(InClass05.this, "please enter a valid place!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(InClass05.this, "No Internet Connection! Try Again", Toast.LENGTH_LONG).show();
                }
            }

        });

        scrollLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageURLS == null) {
                    Toast.makeText(InClass05.this, "No images found!", Toast.LENGTH_LONG).show();
                }
                else if (currentImageIndex == 0) {
                    currentImageIndex = imageURLS.length - 1;
                    String urlString = Arrays.asList(imageURLS).get(currentImageIndex);
                    Picasso.get()
                            .load(urlString)
                            .into(placeImage);
                }
                else if (imageURLS.length == 1) {
                    Toast.makeText(InClass05.this, "No more images!", Toast.LENGTH_LONG).show();
                }
                else {
                    currentImageIndex -= 1;
                    String urlString = Arrays.asList(imageURLS).get(currentImageIndex);
                    Picasso.get()
                            .load(urlString)
                            .into(placeImage);
                }
            }
        });

        scrollRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageURLS == null) {
                    Toast.makeText(InClass05.this, "No images found!", Toast.LENGTH_LONG).show();
                }
                else if (currentImageIndex == imageURLS.length - 1) {
                    currentImageIndex = 0;
                    String urlString = Arrays.asList(imageURLS).get(currentImageIndex);
                    Picasso.get()
                            .load(urlString)
                            .into(placeImage);
                }
                else if (imageURLS.length == 1) {
                    Toast.makeText(InClass05.this, "No images found!", Toast.LENGTH_LONG).show();
                }
                else {
                    currentImageIndex += 1;
                    String urlString = Arrays.asList(imageURLS).get(currentImageIndex);
                    Picasso.get()
                            .load(urlString)
                            .into(placeImage);
                }
            }
        });
    }

    boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            if (networkInfo.isConnected()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

}

