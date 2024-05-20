package com.example.miniproject_prm392;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_main extends AppCompatActivity {
    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin, btnSignUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUsername.getText().toString().equals("user") && txtPassword.getText().toString().equals("1234")) {
                    Toast.makeText(Login_main.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login_main.this, Activity_game.class);
                    startActivity(intent);
                    finish(); // Optional: To finish the login activity so the user cannot go back to it
                } else {
                    Toast.makeText(Login_main.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_main.this, Activity_register.class);
                startActivity(intent);
            }
        });
    }
}
