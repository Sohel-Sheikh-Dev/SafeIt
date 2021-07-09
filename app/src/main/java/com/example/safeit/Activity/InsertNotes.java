package com.example.safeit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.safeit.Model.Notes;
import com.example.safeit.Model.Password;
import com.example.safeit.R;
import com.example.safeit.ViewModel.AllItemsViewModel;
import com.example.safeit.databinding.ActivityInsertNotesBinding;
import com.example.safeit.databinding.ActivityInsertPasswordBinding;

import java.util.Date;

public class InsertNotes extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    String title,subtitle,notes;
    AllItemsViewModel viewModel;
    ImageButton doneButton;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_notes);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(InsertNotes.this).get(AllItemsViewModel.class);

        doneButton = findViewById(R.id.doneNotesbtn);

            binding.doneNotesbtn.setOnClickListener(v -> {
            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();

                if(TextUtils.isEmpty(binding.notesTitle.getText())){
                    binding.notesTitle.setError("This field is mandatory");

                }
                else {
                    CreateNotes(title, subtitle, notes);
                }
        });


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


    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesDate = sequence.toString();
        notes1.notesPriority = priority;

        viewModel.insertNote(notes1);
        Toast.makeText(this,"Notes added",Toast.LENGTH_SHORT).show();
        finish();
    }
}