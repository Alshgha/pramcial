package com.example.alshaqahaapharmacies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.alshaqahaapharmacies.controller.FirebaseAuthController;
import com.example.alshaqahaapharmacies.controller.FirebaseFireStoreController;
import com.example.alshaqahaapharmacies.databinding.ActivityMainBinding;
import com.example.alshaqahaapharmacies.interfaces.ListCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private PharmaceuticalAdapter adapter;
    private ArrayList<Pharmaceutical> pharmaceuticalArrayList = new ArrayList<>();
    Pharmaceutical pharmaceutical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        adapter = new PharmaceuticalAdapter(pharmaceuticalArrayList);

        binding.rv.setAdapter(adapter);
        getData();

        binding.searchView.clearFocus();
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return true;
            }

            private void fileList(String newText) {
                List<Pharmaceutical> pharmaceuticals = new ArrayList<>();
                for (Pharmaceutical pharmaceutical1 : pharmaceuticalArrayList){
                    if (pharmaceutical1.getName().toLowerCase().contains(newText.toLowerCase())){
                        pharmaceuticals.add(pharmaceutical1);
                    }
                }

                if (pharmaceuticals.isEmpty()){
                    Toast.makeText(MainActivity.this, "غير موجود", Toast.LENGTH_SHORT).show();
                    
                }
                else {
                    adapter.setPharmaceuticalList(pharmaceuticals);
                }
            }
        });

        binding.WhoAreWe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WhoAreWe.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            logout();
        }else if (item.getItemId() == R.id.cart) {
            Intent intent = new Intent(this, Cart.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        FirebaseFireStoreController.getInstance().read(new ListCallback<Pharmaceutical>() {
            @Override
            public void onSuccess(ArrayList<Pharmaceutical> list) {
                pharmaceuticalArrayList.clear();
                pharmaceuticalArrayList.addAll(list);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "dd", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void logout() {
        FirebaseAuthController.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}