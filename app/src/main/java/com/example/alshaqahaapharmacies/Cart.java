package com.example.alshaqahaapharmacies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alshaqahaapharmacies.controller.FirebaseFireStoreController;
import com.example.alshaqahaapharmacies.databinding.ActivityCartBinding;
import com.example.alshaqahaapharmacies.interfaces.ListCallback;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    ActivityCartBinding binding;
    CartAdapter cartAdapter;
    private List<Cars> carsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cartAdapter = new CartAdapter(carsList);
        binding.rv.setAdapter(cartAdapter);
        FirebaseFireStoreController.getInstance().readCart(new ListCallback<Cars>() {
            @Override
            public void onSuccess(ArrayList<Cars> list) {
                carsList.clear();
                carsList.addAll(list);
                cartAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure() {
                Toast.makeText(Cart.this, "dd", Toast.LENGTH_SHORT).show();
            }
        });


        binding.reservationConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, ReservationConfirmation.class);
                startActivity(intent);
            }
        });
    }
}