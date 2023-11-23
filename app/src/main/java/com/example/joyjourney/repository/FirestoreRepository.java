package com.example.joyjourney.repository;

import com.example.joyjourney.model.User;
import com.example.joyjourney.model.Wahana;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FirestoreRepository {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void addUser(User user, OnSuccessListener<Void> onSuccessListener) {
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {

            DocumentReference userRef = db.collection("users").document(currentUser.getUid());

            userRef.set(user).addOnSuccessListener(onSuccessListener);
        }
    }

    public void getUser(String userId, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        db.collection("users").document(userId).get()
                .addOnCompleteListener(onCompleteListener);
    }

    public void addOrUpdateWahana(Wahana wahana, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener) {
        db.collection("wahana").document(wahana.getId()).set(wahana).addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
    }

    public void deleteWahana(Wahana wahana, OnSuccessListener<Void> onSuccessListener, OnFailureListener onFailureListener){
        db.collection("wahana").document(wahana.getId()).delete().addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
    }


    public void getAllWahana(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection("wahana").get()
                .addOnCompleteListener(onCompleteListener);
    }

    public void getWahanaByName (String name, OnCompleteListener<QuerySnapshot> onCompleteListener){
        db.collection("wahana")
                .orderBy("name")
                .startAt(name)
                .endAt(name + "\uf8ff")
                .get()
                .addOnCompleteListener(onCompleteListener);
    }

}
