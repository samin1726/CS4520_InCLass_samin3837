package com.example.cs4520_inclass_samin3837.inClass01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs4520_inclass_samin3837.R;

public class InClass01 extends AppCompatActivity implements TextWatcher {

    EditText weight, feet, inches;
    Button calculateBMI;
    TextView textViewInstruct, textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class01);

        weight = findViewById(R.id.editTextNumber1);
        feet = findViewById(R.id.editTextNumber2);
        inches = findViewById(R.id.editTextNumber3);
        calculateBMI = findViewById(R.id.buttonCalculateBMI);
        textViewInstruct = findViewById(R.id.textViewInstruct);
        textViewResult = findViewById(R.id.textViewResult);

        weight.addTextChangedListener(this);
        feet.addTextChangedListener(this);
        inches.addTextChangedListener(this);

        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float weight = new Float(InClass01.this.weight.getText().toString());
                float feet = new Float(InClass01.this.feet.getText().toString());
                float inches = new Float(InClass01.this.inches.getText().toString());
                if (weight < 0 || feet < 0 || inches < 0) {
                    Toast.makeText(InClass01.this, "Invalid Inputs!", Toast.LENGTH_LONG).show();
                    InClass01.this.textViewInstruct.setText("Click on Calculate BMI to find your BMI");
                    InClass01.this.textViewResult.setText("");
                }
                else{
                    float height = (feet * 12) + inches;
                    float BMI = (weight / (height * height)) * 703;
                    InClass01.this.textViewInstruct.setText("Your BMI: " + BMI);
                    if (BMI < 18.5) {
                        InClass01.this.textViewResult.setText("You are underweight!");
                    }
                    else if (BMI >= 18.5 && BMI <= 24.9) {
                        InClass01.this.textViewResult.setText("You are normal weight");
                    }
                    else if (BMI >= 25 && BMI <= 29.9) {
                        InClass01.this.textViewResult.setText("You are overweight");
                    }
                    else {
                        InClass01.this.textViewResult.setText("You are obese");
                    }
                    Toast.makeText(InClass01.this, "BMI Calculated!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // start is the position or index before the first character that is to be changed.
        // s contains the whole text in the editText
        // after is the length of the new set of characters that are to be added
        // count gives the number of characters that are about to be changed/replaced

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // start is the position or index before the first character that is to be changed.
        // s contains the whole text in the editText
        // before contains the length of characters that have been replaced after the edit has been done.


    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}