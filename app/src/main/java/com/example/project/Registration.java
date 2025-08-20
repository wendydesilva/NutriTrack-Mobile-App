package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
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

public class Registration extends AppCompatActivity {

    private EditText userName, password, confirmPassword;
    private Button regButton;
    private TextView message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        regButton = findViewById(R.id.regButton);
        message = findViewById(R.id.regMsg);

    }
    public void createAccount(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(userName.getText().toString().isEmpty()){
            message.setText("Please Enter the User Name");
        } else if (password.getText().toString().isEmpty()) {
            message.setText("Please Enter a Password");
        } else if (confirmPassword.getText().toString().isEmpty()) {
            message.setText("Please Confirm the Password");
        } else if (!(password.getText().toString().equals(confirmPassword.getText().toString()))) {
            message.setText("Password Mismatch");
        } else{
            editor.putString("userName",userName.getText().toString());
            editor.putString("password",password.getText().toString());
            editor.putString("confirmPassword",confirmPassword.getText().toString());

            message.setText("Acount Created Successfully");
            editor.apply();
        }

    }
}