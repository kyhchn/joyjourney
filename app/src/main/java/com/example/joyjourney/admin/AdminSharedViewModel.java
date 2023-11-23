package com.example.joyjourney.admin;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.joyjourney.model.Wahana;
import com.example.joyjourney.repository.FirestoreRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminSharedViewModel extends ViewModel {
    private FirestoreRepository firestoreRepository = new FirestoreRepository();
    private MutableLiveData<List<Wahana>> listAllWahana= new MutableLiveData<>();
    private MutableLiveData<List<Wahana>> listQueriedWahana = new MutableLiveData<>();
    private MutableLiveData<String>errMessage = new MutableLiveData<>();

    public LiveData<List<Wahana>> getListWahana(){
        return listAllWahana;
    }

    public LiveData<List<Wahana>> getQueriedListWahana(){return  listQueriedWahana;}

    public LiveData<String> getErrorMessage(){
        return errMessage;
    }

    void getAllWahana(){
        firestoreRepository.getAllWahana(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if(task.isSuccessful()){
                   List<Wahana> result = new ArrayList<>();
                   QuerySnapshot querySnapshot = task.getResult();

                   for(DocumentSnapshot doc:querySnapshot.getDocuments()){
                       Wahana wahana = doc.toObject(Wahana.class);
                       result.add(wahana);
                   }

                   listAllWahana.postValue(result);
               }else{
                   errMessage.postValue(task.getException().getMessage());
               }
            }
        });
    }

    void getWahanaByName(String name){
        firestoreRepository.getWahanaByName(name,new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    List<Wahana> result = new ArrayList<>();
                    QuerySnapshot querySnapshot = task.getResult();

                    for(DocumentSnapshot doc:querySnapshot.getDocuments()){
                        Wahana wahana = doc.toObject(Wahana.class);
                        result.add(wahana);
                    }

                    listQueriedWahana.postValue(result);
                }else{
                    errMessage.postValue(task.getException().getMessage());
                }
            }
        });
    }
}
