package com.example.alshaqahaapharmacies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alshaqahaapharmacies.controller.FirebaseAuthController;
import com.example.alshaqahaapharmacies.databinding.ActivityForgotPasswordBinding;
import com.example.alshaqahaapharmacies.databinding.ActivityLoginBinding;
import com.example.alshaqahaapharmacies.interfaces.ProcessCallback;

public class ForgotPassword extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.email.getText().toString().trim().isEmpty()) {
                    forgotPassword();
                }
                else
                    Toast.makeText(getBaseContext(), "ادخل بيانات", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void forgotPassword() {
        FirebaseAuthController.getInstance().forgetPassword(binding.email.getText().toString(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(ForgotPassword.this, message, Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(ForgotPassword.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}