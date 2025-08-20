package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BMICalculator extends AppCompatActivity {

    EditText height, weight;
    TextView bmiValueTxv, comment;
    String heightString, weightString;
    float heightValue, weightValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmicalculator);

        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight1);
        bmiValueTxv = findViewById(R.id.bmiTxv);
        comment = findViewById(R.id.commentTxv);

    }

    public void calculateBMI(View view){

        heightString = height.getText().toString();
        weightString = weight.getText().toString();

        if(heightString.equals("")){
            heightValue = 0;
            comment.setText("Please enter the height");
        }
        else{
            heightValue = Float.parseFloat(heightString);
        }
        if(weightString.equals("")){
            weightValue = 0;
            comment.setText("Please enter the weight");
        }
        else{
            weightValue = Float.parseFloat(weightString);
        }

        float heightInM = heightValue / 100;

        float bmiValue = weightValue / (heightInM * heightInM);


        bmiValueTxv.setText("BMI: " + bmiValue);

        if((bmiValue > 0) && (bmiValue < 18.5)){
            comment.setText("Your are Underweight");
        } else if (bmiValue >= 18.5 && bmiValue <= 24.9) {
            comment.setText("Your are in Normal weight");
        } else if (bmiValue >= 25 && bmiValue <= 29.9) {
            comment.setText("Your are Overweight");
        }else if(bmiValue > 29.9){
            comment.setText("Your are Obese");
        }

    }
}