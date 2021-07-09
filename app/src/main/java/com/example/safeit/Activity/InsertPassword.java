package com.example.safeit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safeit.ExampleDialog;
import com.example.safeit.MainActivity;
import com.example.safeit.Model.Password;
import com.example.safeit.R;
import com.example.safeit.ViewModel.AllItemsViewModel;
import com.example.safeit.databinding.ActivityInsertPasswordBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InsertPassword extends AppCompatActivity {

    ActivityInsertPasswordBinding binding;
    String login,password,website;
    AllItemsViewModel viewModel;
    ImageButton doneButton;

    EditText editTextPassword;
    TextView textViewPasswordStrengthIndiactor;
    EditText webEditText;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_password);
        binding = ActivityInsertPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(InsertPassword.this).get(AllItemsViewModel.class);

        doneButton = findViewById(R.id.IPDoneButton);
        webEditText = findViewById(R.id.IPWebInfo);


        // Get the Reference of EditText
        editTextPassword= findViewById(R.id.IPPassInfoss);
        updatePasswordStrengthView(editTextPassword.getText().toString());
        textViewPasswordStrengthIndiactor= findViewById(R.id.textViewPasswordStrength);

        // Attach TextWatcher to EditText
        editTextPassword.addTextChangedListener(mTextEditorWatcher);

        binding.IPDoneButton.setOnClickListener(v -> {
            login = binding.IPLoginInfo.getText().toString();
            password = binding.IPPassInfoss.getText().toString();
            website = binding.IPWebInfo.getText().toString();

            if(TextUtils.isEmpty(binding.IPWebInfo.getText())){
                binding.IPWebInfo.setError("This field is mandatory");

            }
            else{
                CreatePassword(login, password, website);
            }

        });

    }

    private void updatePasswordStrengthView(String password) {

        ProgressBar progressBar = findViewById(R.id.progressBar1);
        TextView strengthView = findViewById(R.id.textViewPasswordStrength);
        progressBar.setProgress(0);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.not_entered), PorterDuff.Mode.SRC_IN);
        if (password.length() == 0) {
            strengthView.setText("Not Entered");
            strengthView.setTextColor(getResources().getColor(R.color.not_entered));
            progressBar.setProgress(0);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.not_entered), PorterDuff.Mode.SRC_IN);
        } else if (password.length() < 5) {
            strengthView.setText("WEAK");
            strengthView.setTextColor(getResources().getColor(R.color.easy));
            progressBar.setProgress(20);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.easy), PorterDuff.Mode.SRC_IN);
        } else if (password.length() < 8) {
            strengthView.setText("NOT SAFE");
            strengthView.setTextColor(getResources().getColor(R.color.medium));
            progressBar.setProgress(45);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.medium), PorterDuff.Mode.SRC_IN);
        } else if (password.length() < 14) {
            strengthView.setText("SAFE");
            strengthView.setTextColor(getResources().getColor(R.color.strong));
            progressBar.setProgress(70);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.strong), PorterDuff.Mode.SRC_IN);
        } else if(password.length() <19 ) {
            strengthView.setText("SUPER SAFE");
            strengthView.setTextColor(getResources().getColor(R.color.strongest));
            progressBar.setProgress(95);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.strongest), PorterDuff.Mode.SRC_IN);
        }else {
            strengthView.setText("Password Max Length Reached");
            strengthView.setTextColor(getResources().getColor(R.color.max_limit));
            progressBar.setProgress(100);
            progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.max_limit), PorterDuff.Mode.SRC_IN);
        }
    }


    private final TextWatcher mTextEditorWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
            updatePasswordStrengthView(s.toString());
        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            updatePasswordStrengthView(s.toString());
        }

        public void afterTextChanged(Editable s) {

            updatePasswordStrengthView(s.toString());

        }
    };

    private void CreatePassword(String login, String password, String website) {

        Password password1 = new Password();
        password1.Password = password;
        password1.LoginID = login;
        password1.WebsiteURL = website;

        viewModel.insertPassword(password1);
        Toast.makeText(this,"Password added",Toast.LENGTH_SHORT).show();
        finish();
    }

        public void ShowHidePass(View view) {

            EditText ippassinfo = findViewById(R.id.IPPassInfoss);

            if(view.getId()==R.id.IPEyeButton){
                if(ippassinfo.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    ((ImageView)(view)).setImageResource(R.drawable.ic_visibility_off);
                    //Show Password
                    ippassinfo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    ((ImageView)(view)).setImageResource(R.drawable.ic_visibility);
                    //Hide Password
                    ippassinfo.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        }

}