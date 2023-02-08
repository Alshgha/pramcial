package com.example.alshaqahaapharmacies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alshaqahaapharmacies.controller.FirebaseFireStoreController;
import com.example.alshaqahaapharmacies.databinding.ActivityMainBinding;
import com.example.alshaqahaapharmacies.databinding.ActivityReservationConfirmationBinding;
import com.example.alshaqahaapharmacies.interfaces.ListCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ReservationConfirmation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityReservationConfirmationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationConfirmationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.branches,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.planetsSpinner.setAdapter(adapter);
        binding.planetsSpinner.setOnItemSelectedListener(this);

        binding.payingRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.credit_card:
                        binding.cardNumber.setVisibility(View.VISIBLE);
                        binding.date.setVisibility(View.VISIBLE);
                        binding.cvv.setVisibility(View.VISIBLE);
                        break;
                    case R.id.cash:
                        binding.cardNumber.setVisibility(View.GONE);
                        binding.date.setVisibility(View.GONE);
                        binding.cvv.setVisibility(View.GONE);
                        break;
                }
            }
        });

        binding.payingOffs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.name.getText().toString().trim().isEmpty()
                        && !binding.phoneNumber.getText().toString().trim().isEmpty()
                        && binding.creditCard.isChecked() || binding.cash.isChecked()) {

                    Toast.makeText(ReservationConfirmation.this, "تم", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else
                    Toast.makeText(getBaseContext(), "تأكد من البيانات المدخلة", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}