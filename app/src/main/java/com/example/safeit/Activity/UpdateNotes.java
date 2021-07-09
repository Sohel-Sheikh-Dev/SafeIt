package com.example.safeit.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safeit.Model.Notes;
import com.example.safeit.R;
import com.example.safeit.ViewModel.AllItemsViewModel;
import com.example.safeit.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotes extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String priority = "1";
    int iid;
    String stitle, ssubtitle, snotes, spriority;
    AllItemsViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(AllItemsViewModel.class);

        iid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("notes");
        spriority = getIntent().getStringExtra("priority");

        binding.upnotesTitle.setText(stitle);
        binding.upnotesSubtitle.setText(ssubtitle);
        binding.upnotesData.setText(snotes);

        switch (spriority) {
            case "1":
                binding.greenPriority.setImageResource(R.drawable.ic_baseline_check_24);
                break;

            case "2":
                binding.yellowPriority.setImageResource(R.drawable.ic_baseline_check_24);
                break;

            case "3":
                binding.redPriority.setImageResource(R.drawable.ic_baseline_check_24);
                break;
        }


        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(R.drawable.ic_baseline_check_24);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority = "1";
            }
        });
        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(R.drawable.ic_baseline_check_24);
                binding.redPriority.setImageResource(0);
                priority = "2";
            }
        });
        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(R.drawable.ic_baseline_check_24);
                priority = "3";
            }
        });

        binding.updoneNotesbtn.setOnClickListener(v -> {
            String title = binding.upnotesTitle.getText().toString();
            String subtitle = binding.upnotesSubtitle.getText().toString();
            String notes = binding.upnotesData.getText().toString();

            if(TextUtils.isEmpty(binding.upnotesTitle.getText())){
                binding.upnotesTitle.setError("This field is mandatory");

            }
            else {
                UpdateNotes(title, subtitle, notes);
            }
        });

    }

    private void UpdateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        Notes updateNotes = new Notes();
        updateNotes.id = iid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesDate = sequence.toString();
        updateNotes.notesPriority = priority;
        notesViewModel.updateNote(updateNotes);

        Toast.makeText(this,"Notes Updated Sucessfully",Toast.LENGTH_SHORT).show();
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
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotes.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateNotes.this).inflate(R.layout.delete_bottom_shift, findViewById(R.id.bottom_sheet));
            sheetDialog.setContentView(view);

            TextView yes,no;
            yes = view.findViewById(R.id.yes_delete);
            no = view.findViewById(R.id.no_delete);

            yes.setOnClickListener(v -> {
                notesViewModel.deleteNote(iid);
                finish();
            });

            no.setOnClickListener(v -> {
                sheetDialog.dismiss();
            });

            sheetDialog.show();

        }
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.delete_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == R.id.ic_delete){
//            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotes.this,R.style.BottomSheetStyle);
//            View view = LayoutInflater.from(UpdateNotes.this).inflate(R.layout.delete_bottom_shift,(LinearLayout) findViewById(R.id.bottom_sheet));
//            sheetDialog.setContentView(view);
//
//            TextView yes,no;
//            yes = view.findViewById(R.id.yes_delete);
//            no = view.findViewById(R.id.no_delete);
//
//            yes.setOnClickListener(v -> {
//                notesViewModel.deleteNote(iid);
//                finish();
//            });
//
//            no.setOnClickListener(v -> {
//                sheetDialog.dismiss();
//            });
//
//            sheetDialog.show();
//
//        }
//        return true;
//    }
}