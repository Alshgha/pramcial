package com.example.alshaqahaapharmacies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alshaqahaapharmacies.controller.FirebaseAuthController;
import com.example.alshaqahaapharmacies.databinding.ActivityLoginBinding;
import com.example.alshaqahaapharmacies.databinding.ActivityRegisterBinding;
import com.example.alshaqahaapharmacies.interfaces.ProcessCallback;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String Password = binding.password.getText().toString();
        String confirmPassword = binding.confirmPassword.getText().toString();

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.email.getText().toString().trim().isEmpty()
                        && !binding.password.getText().toString().trim().isEmpty()
                        && confirmPassword.equals(Password)) {

                    register();

                }
                else
                    Toast.makeText(getBaseContext(), "تأكد من البيانات المدخلة", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void register() {
        FirebaseAuthController.getInstance().createAccount(
                binding.email.getText().toString(),
                binding.password.getText().toString(),
                new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}