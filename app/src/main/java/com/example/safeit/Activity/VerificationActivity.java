package com.example.safeit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.safeit.Fragments.AllItemFrag;
import com.example.safeit.Fragments.PasswordFrag;
import com.example.safeit.MainActivity;
import com.example.safeit.R;

import java.util.concurrent.Executor;

public class VerificationActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {
            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Please Verify")
                    .setDescription("User Authentication is required to proceed").setNegativeButtonText("Cancel").build();
            getPromt().authenticate(promptInfo);
        });

    }

    public BiometricPrompt getPromt(){
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("Authentication suceed!!");
                Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("Authentication Failed!!");
            }
        };

        BiometricPrompt biometricPrompt = new BiometricPrompt(this,executor,callback);
        return biometricPrompt;

    }

    private  void notifyUser(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}