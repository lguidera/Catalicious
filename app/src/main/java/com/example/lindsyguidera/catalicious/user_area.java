package com.example.lindsyguidera.catalicious;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class user_area extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        final android.widget.EditText etUsername = (android.widget.EditText) findViewById(R.id.etUsername);
        final android.widget.EditText etAge = (android.widget.EditText) findViewById(R.id.etAge);
        final android.widget.TextView welcomeMessage = (android.widget.TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

        String message = name + " welcome to your user area";
        welcomeMessage.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");
    }
}
