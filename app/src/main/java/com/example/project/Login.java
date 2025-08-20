package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    EditText userName, password;
    TextView LgnMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.userNameLogin);
        password = findViewById(R.id.passwordLgn);
        LgnMsg = findViewById(R.id.LgnMsg);

    }
    public void loginUser(View view){

        Intent intent = new Intent(this,Options.class);
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref_file", Context.MODE_PRIVATE);

        String registeredUserName = sharedPreferences.getString("userName", "");
        String registeredPassword = sharedPreferences.getString("password", "");

        if(registeredUserName.equals(userName.getText().toString()) &&
                registeredPassword.equals(password.getText().toString())){

            startActivity(intent);
        }else{
            LgnMsg.setText("Incorrect UserName or Password");
        }
    }

}