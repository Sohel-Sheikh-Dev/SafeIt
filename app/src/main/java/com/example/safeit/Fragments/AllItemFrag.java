package com.example.safeit.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.safeit.Activity.InsertNotes;
import com.example.safeit.Activity.InsertPassword;
import com.example.safeit.Adapter.NotesAdapter;
import com.example.safeit.Adapter.PasswordAdapter;
import com.example.safeit.R;
import com.example.safeit.ViewModel.AllItemsViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllItemFrag extends Fragment {
    ConcatAdapter concatAdapter;
    FloatingActionButton mAddNote, mPasswordFab;
    ExtendedFloatingActionButton mAddFab;
    TextView addNotesActionTextView, addPasswordActionText;
    Boolean isAllFabsVisible;
    PasswordAdapter passadapter;
    NotesAdapter notesAdapter;
    RecyclerView allrecyclerView;
    AllItemsViewModel viewModel;
//    LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_item, container, false);
//        viewModel = ViewModelProviders.of(this).get(AllItemsViewModel.class);

        mAddFab = rootView.findViewById(R.id.add_fab);

        mAddNote = rootView.findViewById(R.id.add_notes_fab);
        mPasswordFab = rootView.findViewById(R.id.add_password_fab);

        addNotesActionTextView = rootView.findViewById(R.id.add_notes_action_text);
        addPasswordActionText = rootView.findViewById(R.id.add_password_action_text);

        allrecyclerView = rootView.findViewById(R.id.all_Recycler_View);

//        concatAdapter = new ConcatAdapter(passadapter, notesAdapter);
//
//        allrecyclerView.setAdapter(concatAdapter);
//        allrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        allrecyclerView.setHasFixedSize(true);
//        viewModel.getAllPasswords.observe((LifecycleOwner) requireContext(), passwords -> {
//            Log.e("error", "error");
//        });
//
//        viewModel.getAllNotes.observe((LifecycleOwner) requireContext(), notes -> {
//
//        });

        mAddNote.setVisibility(View.GONE);
        mPasswordFab.setVisibility(View.GONE);
        addNotesActionTextView.setVisibility(View.GONE);
        addPasswordActionText.setVisibility(View.GONE);

        isAllFabsVisible = false;

        mAddFab.shrink();

        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {

                            mAddNote.show();
                            mPasswordFab.show();
                            addNotesActionTextView.setVisibility(View.VISIBLE);
                            addPasswordActionText.setVisibility(View.VISIBLE);
                            mAddFab.extend();

                            isAllFabsVisible = true;
                        } else {
                            mAddNote.hide();
                            mPasswordFab.hide();
                            addNotesActionTextView.setVisibility(View.GONE);
                            addPasswordActionText.setVisibility(View.GONE);

                            mAddFab.shrink();

                            isAllFabsVisible = false;
                        }
                    }
                });

        mAddNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(getContext(), "Notes Added", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), InsertNotes.class);
                        startActivity(intent);

                    }
                });

        mPasswordFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(getContext(), "Password Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), InsertPassword.class);
                        startActivity(intent);
                    }
                });


        return rootView;
    }

}