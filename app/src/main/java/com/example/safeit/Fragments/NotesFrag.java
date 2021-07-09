package com.example.safeit.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.safeit.Activity.InsertNotes;
import com.example.safeit.Adapter.NotesAdapter;
import com.example.safeit.MainActivity;
import com.example.safeit.Model.Notes;
import com.example.safeit.R;
import com.example.safeit.ViewModel.AllItemsViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesFrag extends Fragment {
    AllItemsViewModel viewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter adapter;
    ExtendedFloatingActionButton addNotesFragFab;
    RelativeLayout emptyview;
    LinearLayout filter;


    TextView nofilter, hightolow, lowtohigh;
    List<Notes> filternotesalllist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        viewModel = ViewModelProviders.of(this).get(AllItemsViewModel.class);

        notesRecyclerView = rootView.findViewById(R.id.notes_recycler_view);
        addNotesFragFab = rootView.findViewById(R.id.add_notes_frag_fab);


        nofilter = rootView.findViewById(R.id.nofilter);
        hightolow = rootView.findViewById(R.id.highToLow);
        lowtohigh = rootView.findViewById(R.id.lowToHigh);

        filter = rootView.findViewById(R.id.filter);

        emptyview = rootView.findViewById(R.id.empty_view);

        nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        nofilter.setOnClickListener(v -> {
            loadData(0);
            nofilter.setBackgroundResource(R.drawable.filter_selected_shape);
            hightolow.setBackgroundResource(R.drawable.filter_ui_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_ui_shape);
        });

        hightolow.setOnClickListener(v -> {
            loadData(1);
            nofilter.setBackgroundResource(R.drawable.filter_ui_shape);
            hightolow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_ui_shape);
        });

        lowtohigh.setOnClickListener(v -> {
            loadData(2);
            nofilter.setBackgroundResource(R.drawable.filter_ui_shape);
            hightolow.setBackgroundResource(R.drawable.filter_ui_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_shape);
        });

        viewModel = ViewModelProviders.of(this).get(AllItemsViewModel.class);


        addNotesFragFab.setOnClickListener(
                v -> {
                    Intent intent = new Intent(getActivity(), InsertNotes.class);
                    startActivity(intent);
                });

        viewModel.getAllNotes.observe(getViewLifecycleOwner(), notes -> {
            LinearLayoutManager layoutManager;
            notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new NotesAdapter(NotesFrag.this, notes);
            notesRecyclerView.setAdapter(adapter);


            if (notes.isEmpty()){
                notesRecyclerView.setVisibility(View.GONE);
                emptyview.setVisibility(View.VISIBLE);
                filter.setVisibility(View.GONE);
            } else {
                notesRecyclerView.setVisibility(View.VISIBLE);
                emptyview.setVisibility(View.GONE);
                filter.setVisibility(View.VISIBLE);
            }

        });

//        viewModel.getAllNotes.observe(getViewLifecycleOwner(), new Observer<List<Notes>>() {
//            @Override
//            public void onChanged(List<Notes> notes) {
//                setAdapter(notes);
//                filternotesalllist = notes;
//            }
//        });

        return rootView;

    }


    public void loadData(int i) {
        if (i == 0) {
            viewModel.getAllNotes.observe(getViewLifecycleOwner(), new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        } else if (i == 1) {
            viewModel.hightolow.observe(getViewLifecycleOwner(), new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        } else if (i == 2) {
            viewModel.lowtohigh.observe(getViewLifecycleOwner(), new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        }

    }


    public void setAdapter(List<Notes> notes) {
        LinearLayoutManager layoutManager;
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NotesAdapter(NotesFrag.this, notes);
        notesRecyclerView.setAdapter(adapter);
    }


    private void NotesFilter(String newText) {
        ArrayList<Notes> FilterNames = new ArrayList<>();

        for(Notes notes:this.filternotesalllist){
            if(notes.notesTitle.contains(newText)||notes.notesSubtitle.contains(newText)){
                FilterNames.add(notes);
            }
        }
    }

}