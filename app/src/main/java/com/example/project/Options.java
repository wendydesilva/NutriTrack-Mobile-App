package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_options);

    }

    public void openBMICalculator(View view) {
        Intent intent = new Intent(this,BMICalculator.class);
        startActivity(intent);


    }
    public void openCalorySearch(View view) {
        Intent intent = new Intent(this, CalorySearch.class);
        startActivity(intent);


    }
}