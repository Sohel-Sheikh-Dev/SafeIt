package com.example.safeit.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.safeit.Activity.InsertPassword;
import com.example.safeit.Adapter.PasswordAdapter;
import com.example.safeit.R;
import com.example.safeit.ViewModel.AllItemsViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class PasswordFrag extends Fragment {

    AllItemsViewModel viewModel;
    RecyclerView passwordRecyclerView;
    PasswordAdapter adapter;
    ExtendedFloatingActionButton addPasswordFragFab;
    RelativeLayout emptyView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_password, container, false);
        viewModel = ViewModelProviders.of(this).get(AllItemsViewModel.class);

        passwordRecyclerView = rootView.findViewById(R.id.password_Recycler_View);
        addPasswordFragFab = rootView.findViewById(R.id.add_password_frag_fab);

        addPasswordFragFab.setOnClickListener(
                v -> {
                    Intent intent = new Intent(getActivity(), InsertPassword.class);
                    startActivity(intent);
                });

        emptyView2 = rootView.findViewById(R.id.empty_view2);

        viewModel.getAllPasswords.observe(getViewLifecycleOwner(), passwords -> {
            LinearLayoutManager layoutManager;
            passwordRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new PasswordAdapter(PasswordFrag.this, passwords);
            passwordRecyclerView.setAdapter(adapter);

            if (passwords.isEmpty()){
                passwordRecyclerView.setVisibility(View.GONE);
                emptyView2.setVisibility(View.VISIBLE);
            } else {
                passwordRecyclerView.setVisibility(View.VISIBLE);
                emptyView2.setVisibility(View.GONE);
            }

        });

        return rootView;

    }

}