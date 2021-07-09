package com.example.safeit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safeit.Fragments.AllItemFrag;
import com.example.safeit.Fragments.PasswordFrag;
import com.example.safeit.MainActivity;
import com.example.safeit.Model.Notes;
import com.example.safeit.Model.Password;
import com.example.safeit.R;
import com.example.safeit.ViewModel.AllItemsViewModel;
import com.example.safeit.databinding.ActivityReadPasswordBinding;
import com.example.safeit.databinding.ActivityUpdateNotesBinding;
import com.example.safeit.databinding.ActivityUpdatePasswordBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdatePassword extends AppCompatActivity {

    ActivityUpdatePasswordBinding binding;
    int iid;
    String slogin,spassword,swebsite;
    AllItemsViewModel updatePasswordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updatePasswordViewModel = ViewModelProviders.of(this).get(AllItemsViewModel.class);

        iid = getIntent().getIntExtra("id", 0);
        slogin = getIntent().getStringExtra("login");
        spassword = getIntent().getStringExtra("password");
        swebsite = getIntent().getStringExtra("website");

        binding.UPLoginInfo.setText(slogin);
        binding.UPPassInfo.setText(spassword);
        binding.UPWebInfo.setText(swebsite);


        binding.UPDoneButton.setOnClickListener(v -> {
            String login = binding.UPLoginInfo.getText().toString();
            String password = binding.UPPassInfo.getText().toString();
            String website = binding.UPWebInfo.getText().toString();
            ReadPassword(login, password, website);
        });

    }

    private void ReadPassword(String login, String password, String website) {

        Password updatePassword = new Password();
        updatePassword.id = iid;
        updatePassword.LoginID = login;
        updatePassword.Password = password;
        updatePassword.WebsiteURL = website;
        updatePasswordViewModel.updatePassword(updatePassword);

        Toast.makeText(this,"Password Updated Sucessfully",Toast.LENGTH_SHORT).show();
        finishAffinity();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.ic_delete){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdatePassword.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdatePassword.this).inflate(R.layout.delete_bottom_shift, findViewById(R.id.bottom_sheet));
            sheetDialog.setContentView(view);

            TextView yes,no;
            yes = view.findViewById(R.id.yes_delete);
            no = view.findViewById(R.id.no_delete);

            yes.setOnClickListener(v -> {
                updatePasswordViewModel.deleteNote(iid);
                finishAffinity();
                startActivity(new Intent(this, MainActivity.class));
            });

            no.setOnClickListener(v -> {
                sheetDialog.dismiss();
            });

            sheetDialog.show();

        }
        return true;
    }
}