package com.example.cs4520_inclass_samin3837;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InClass04 extends AppCompatActivity {

    private ExecutorService threadPool;
    private Button generateNumber;
    private Handler messageQueue;
    private SeekBar complexityBar;
    private ProgressBar progressBar;
    private TextView complexity;
    private TextView minResult;
    private TextView maxResult;
    private TextView avgResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class04);
        setTitle("Number Generator");
        threadPool = Executors.newFixedThreadPool(2);
        generateNumber = findViewById(R.id.generate_number);
        complexityBar = findViewById(R.id.complexity_bar);
        complexity = findViewById(R.id.complexity);
        progressBar = findViewById(R.id.progressBar);
        minResult = findViewById(R.id.min_result);
        maxResult = findViewById(R.id.max_result);
        avgResult = findViewById(R.id.avg_result);

        complexityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                complexity.setText(i + " times");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });

        generateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (complexityBar.getProgress() == 0) {
                    // do nothing
                }
                else {
                    threadPool.execute(new HeavyWork(complexityBar.getProgress(), messageQueue));
                }
            }
        });

        messageQueue = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch(message.what) {
                    case HeavyWork.STATUS_START:
                        progressBar.setMax(complexityBar.getProgress());
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case HeavyWork.STATUS_PROGRESS:
                        int progress = message.getData().getInt("progress") + 1;
                        progressBar.setProgress(progress);
                        break;
                    case HeavyWork.STATUS_END:
                        progressBar.setVisibility(View.INVISIBLE);
                        break;
                    case HeavyWork.STATUS_DATA_SENT:
                        minResult.setText(message.getData().getDouble("min") + "");
                        maxResult.setText(message.getData().getDouble("max") + "");
                        avgResult.setText(message.getData().getDouble("avg") + "");
                        break;
                }
                return false;
            }
        });

    }
}