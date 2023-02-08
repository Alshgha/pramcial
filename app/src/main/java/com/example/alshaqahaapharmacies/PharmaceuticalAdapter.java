package com.example.alshaqahaapharmacies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alshaqahaapharmacies.controller.FirebaseFireStoreController;
import com.example.alshaqahaapharmacies.databinding.CustomPharmaceuticalItamBinding;
import com.example.alshaqahaapharmacies.interfaces.ProcessCallback;

import java.util.ArrayList;
import java.util.List;


public class PharmaceuticalAdapter extends RecyclerView.Adapter<PharmaceuticalAdapter.PharmaceuticalHolder> {
    List<Pharmaceutical> pharmaceuticalList = new ArrayList<>();

    Cars cars;
    public PharmaceuticalAdapter(List<Pharmaceutical> pharmaceuticalList) {
        this.pharmaceuticalList = pharmaceuticalList;
    }

    public void setPharmaceuticalList(List<Pharmaceutical> pharmaceuticalList) {
        this.pharmaceuticalList = pharmaceuticalList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PharmaceuticalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomPharmaceuticalItamBinding binding = CustomPharmaceuticalItamBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PharmaceuticalHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PharmaceuticalHolder holder, int position) {
        holder.setData(pharmaceuticalList.get(position));

        holder.binding.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = pharmaceuticalList.get(position).getId();
                FirebaseFireStoreController.getInstance().createCart(holder.getCars(), new ProcessCallback() {
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
        return pharmaceuticalList.size();
    }

    class PharmaceuticalHolder extends RecyclerView.ViewHolder {
        CustomPharmaceuticalItamBinding binding;
        public PharmaceuticalHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomPharmaceuticalItamBinding.bind(itemView);
        }
        public void setData(Pharmaceutical pharmaceutical){
            binding.names.setText(pharmaceutical.getName());
            binding.condition.setText(pharmaceutical.getCondition());
            binding.prices.setText(pharmaceutical.getPrice());
            binding.branch.setText(pharmaceutical.getBranch());
            binding.use.setText(pharmaceutical.getDescription());
        }

        public Cars getCars(){
            Cars cars1 = isNewNote() ? new Cars() : cars;
            cars1.setName(binding.names.getText().toString());
            cars1.setCondition(binding.condition.getText().toString());
            cars1.setPrice(binding.prices.getText().toString());
            cars1.setBranch(binding.branch.getText().toString());
            cars1.setDescription(binding.use.getText().toString());

            return cars1;
        }

        private boolean isNewNote() {
            return cars  == null;
        }


    }

}

