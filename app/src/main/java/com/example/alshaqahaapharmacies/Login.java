package com.example.alshaqahaapharmacies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alshaqahaapharmacies.controller.FirebaseAuthController;
import com.example.alshaqahaapharmacies.databinding.ActivityLoginBinding;
import com.example.alshaqahaapharmacies.databinding.ActivitySplashBinding;
import com.example.alshaqahaapharmacies.interfaces.ProcessCallback;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });

        binding.createNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.email.getText().toString().trim().isEmpty()
                        && !binding.password.getText().toString().trim().isEmpty()) {
                    logIn();
                }
                else
                    Toast.makeText(getBaseContext(), "ادخل بيانات", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void logIn(){
        FirebaseAuthController.getInstance().logIn(binding.email.getText().toString(),
                binding.password.getText().toString(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}