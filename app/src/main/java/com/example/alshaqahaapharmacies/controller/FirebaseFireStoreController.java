package com.example.alshaqahaapharmacies.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.alshaqahaapharmacies.Cars;
import com.example.alshaqahaapharmacies.Cart;
import com.example.alshaqahaapharmacies.Pharmaceutical;
import com.example.alshaqahaapharmacies.interfaces.ListCallback;
import com.example.alshaqahaapharmacies.interfaces.ProcessCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

public class FirebaseFireStoreController {

    private FirebaseFireStoreController() {
    }

    private static FirebaseFireStoreController instance;

    private FirebaseFirestore fireStore = FirebaseFirestore.getInstance();


    public static synchronized FirebaseFireStoreController getInstance() {
        if (instance == null) {
            instance = new FirebaseFireStoreController();
        }
        return instance;
    }


    public void read(ListCallback<Pharmaceutical> callback) {
        fireStore.collection("pharmaceutical").orderBy("name").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error == null) {
                    ArrayList<Pharmaceutical> notes = new ArrayList<>();
                    if (!value.getDocuments().isEmpty()) {
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            Pharmaceutical note = snapshot.toObject(Pharmaceutical.class);
                            note.setId(snapshot.getId());
                            notes.add(note);
                        }
                    }
                    callback.onSuccess(notes);
                }
            }
        });
    }

    public void readCart(ListCallback<Cars> callback) {
        fireStore.collection("Cart").orderBy("name").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error == null) {
                    ArrayList<Cars> cars = new ArrayList<>();
                    if (!value.getDocuments().isEmpty()) {
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            Cars cars1 = snapshot.toObject(Cars.class);
                            cars1.setId(snapshot.getId());
                            cars.add(cars1);
                        }
                    }
                    callback.onSuccess(cars);
                }
            }
        });
    }

    public void createCart(Cars cars, ProcessCallback callback) {
        fireStore.collection("Cart").add(cars.toMap()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                callback.onSuccess("تم الاضافة الى السلة");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure("Failed to create note");
            }
        });
    }

    public void deleteCart(String path, ProcessCallback callback) {
        fireStore.collection("Cart").document(path).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                callback.onSuccess("تم الحذف");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onFailure("Delete failed");
            }
        });
    }

}
