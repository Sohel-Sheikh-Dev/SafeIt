package com.example.safeit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safeit.MainActivity;
import com.example.safeit.Model.Notes;
import com.example.safeit.Model.Password;
import com.example.safeit.R;
import com.example.safeit.ViewModel.AllItemsViewModel;
import com.example.safeit.databinding.ActivityReadPasswordBinding;
import com.example.safeit.databinding.ActivityUpdatePasswordBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class ReadPassword extends AppCompatActivity {

    ActivityReadPasswordBinding binding;
    int iid;
    String slogin,spassword,swebsite;
    AllItemsViewModel readPasswordViewModel;
    TextView iphintname,ipname;
    EditText editTextPassword;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        readPasswordViewModel = ViewModelProviders.of(this).get(AllItemsViewModel.class);

        iid = getIntent().getIntExtra("id", 0);
        slogin = getIntent().getStringExtra("login");
        spassword = getIntent().getStringExtra("password");
        swebsite = getIntent().getStringExtra("website");

        updatePasswordStrengthView(spassword);

        binding.IPLoginInfo.setText(slogin);
        binding.IPPassInfo.setText(spassword);
        binding.RPWebInfo.setText(swebsite);


        binding.donePassbtn.setOnClickListener(v -> {
            String login = binding.IPLoginInfo.getText().toString();
            String password = binding.IPPassInfo.getText().toString();
            String website = binding.RPWebInfo.getText().toString();

            if(TextUtils.isEmpty(binding.RPWebInfo.getText())){
                binding.RPWebInfo.setError("This field is mandatory");
            }
            else {
                ReadPassword(login, password, website);
            }
        });

        Button button1 = this.findViewById(R.id.IPLoginCopy);
//        button1.setOnClickListener(this);
        button1.setOnClickListener(v -> {
            setClipboard(this,binding.IPLoginInfo.getText().toString());
        });


        Button button2 = this.findViewById(R.id.IPPassCopy);
        button2.setOnClickListener(v -> {
            setClipboard(this,binding.IPPassInfo.getText().toString());
        });

        iphintname = findViewById(R.id.IPHintName);
        ipname = findViewById(R.id.IPName);

        iphintname.setText(String.valueOf(swebsite.charAt(0)));
        ipname.setText(String.valueOf(swebsite));


        editTextPassword= findViewById(R.id.IPPassInfo);

        // Attach TextWatcher to EditText
        editTextPassword.addTextChangedListener(mTextEditorWatcher);

    }

    private void setClipboard(Context context, String text){
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(),"Copied",Toast.LENGTH_SHORT).show();
    }

    private void ReadPassword(String login, String password, String website) {

        Password updatePassword = new Password();
        updatePassword.id = iid;
        updatePassword.LoginID = login;
        updatePassword.Password = password;
        updatePassword.WebsiteURL = website;
        readPasswordViewModel.updatePassword(updatePassword);

        Toast.makeText(this,"Password Updated Sucessfully",Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.ic_delete){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(ReadPassword.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(ReadPassword.this).inflate(R.layout.delete_bottom_shift, findViewById(R.id.bottom_sheet));
            sheetDialog.setContentView(view);

            TextView yes,no;
            yes = view.findViewById(R.id.yes_delete);
            no = view.findViewById(R.id.no_delete);

            yes.setOnClickListener(v -> {
                readPasswordViewModel.deletePassword(iid);
                finish();
            });

            no.setOnClickListener(v -> {
                sheetDialog.dismiss();
            });

            sheetDialog.show();

        }
        return true;
    }


    public void ShowHidePass(View view) {

        EditText ippassinfo = findViewById(R.id.IPPassInfo);

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



    private void updatePasswordStrengthView(String password) {

        ProgressBar progressBar = findViewById(R.id.progressBar1);
        TextView strengthView = findViewById(R.id.IPStrengthInfo);
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


}