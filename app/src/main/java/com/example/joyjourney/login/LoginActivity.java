package com.example.joyjourney.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.joyjourney.MainActivity;
import com.example.joyjourney.databinding.ActivityLoginBinding;
import com.example.joyjourney.signup.SignupActivity;
import com.example.joyjourney.utils.Utils;


public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.loginButton.setOnClickListener(view->{
            String email = binding.editTextTextEmailAddress.getEditText().getText().toString().trim();
            String password = binding.editPassword.getEditText().getText().toString().trim();

            if(Utils.isValidEmail(email)){
                loginViewModel.loginUser(email, password);
            }else{
                binding.editTextTextEmailAddress.getEditText().setError("Email tidak valid");
            }
        });

        binding.signUpButton.setOnClickListener(view->{
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent);
        });

        loginViewModel.getNavigateToNextScreen().observe(this, navigate->{
            if(navigate){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginViewModel.getErrorLiveData().observe(this, error->{
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}