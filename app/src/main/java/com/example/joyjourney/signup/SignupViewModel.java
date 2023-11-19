package com.example.joyjourney.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.joyjourney.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class SignupViewModel extends ViewModel {
    private AuthRepository authRepository = new AuthRepository();

    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    private MutableLiveData<Boolean> navigateToNextScreen = new MutableLiveData<>();

    public LiveData<Boolean> getNavigateToNextScreen() {
        return navigateToNextScreen;
    }
    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void registerUser(String email, String password) {
        authRepository.registerUser(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                navigateToNextScreen.postValue(true);
            }

            @Override
            public void onError(String errorMessage) {
                errorLiveData.setValue(errorMessage);
            }
        });

    }
}
