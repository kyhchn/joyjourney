package com.example.joyjourney.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.joyjourney.MainActivity;
import com.example.joyjourney.R;
import com.example.joyjourney.databinding.ActivityLoginBinding;
import com.example.joyjourney.databinding.ActivitySignupBinding;
import com.example.joyjourney.login.LoginActivity;
import com.example.joyjourney.utils.Utils;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;

    private SignupViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);

        binding.signupButton.setOnClickListener(view->{
            String email = binding.editTextTextEmailAddress.getEditText().getText().toString().trim();
            String password = binding.editPassword.getEditText().getText().toString().trim();

            if(Utils.isValidEmail(email)){
                viewModel.registerUser(email, password);
            }else{
                binding.editTextTextEmailAddress.getEditText().setError("Email tidak valid");
            }
        });

        binding.LoginButton.setOnClickListener(view->{
            finish();
        });

        viewModel.getNavigateToNextScreen().observe(this, navigate->{
            if(navigate){
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        viewModel.getErrorLiveData().observe(this, error->{
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}