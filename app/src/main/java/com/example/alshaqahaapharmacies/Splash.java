package com.example.alshaqahaapharmacies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.alshaqahaapharmacies.controller.FirebaseAuthController;
import com.example.alshaqahaapharmacies.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), FirebaseAuthController.getInstance().isSignedIn() ? MainActivity.class : Login.class);
                startActivity(intent);
            }
        }, 3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}