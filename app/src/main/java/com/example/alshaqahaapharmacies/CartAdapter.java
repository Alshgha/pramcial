package com.example.alshaqahaapharmacies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alshaqahaapharmacies.controller.FirebaseFireStoreController;
import com.example.alshaqahaapharmacies.databinding.CustomCartItamBinding;
import com.example.alshaqahaapharmacies.interfaces.ProcessCallback;

import java.util.ArrayList;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    List<Cars> carsList = new ArrayList<>();

    public CartAdapter(List<Cars> cars) {
        this.carsList = cars;
    }

    public void setPharmaceuticalList(List<Cars> cars) {
        this.carsList = cars;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomCartItamBinding binding = CustomCartItamBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CartHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        holder.setData(carsList.get(position));
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = carsList.get(position).getId();
                FirebaseFireStoreController.getInstance().deleteCart(id, new ProcessCallback() {
                    @Override
                    public void onSuccess(String message) {
                        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        CustomCartItamBinding binding;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomCartItamBinding.bind(itemView);
        }
        public void setData(Cars cars){
            binding.names.setText(cars.getName());
            binding.prices.setText(cars.getPrice());

        }
    }

}

