package com.example.safeit.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.safeit.Activity.ReadPassword;
import com.example.safeit.Activity.UpdatePassword;
import com.example.safeit.Fragments.PasswordFrag;
import com.example.safeit.Model.Password;
import com.example.safeit.R;

import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder> {

    PasswordFrag passwordFrag;
    List<Password> passwords;

    public PasswordAdapter(PasswordFrag passwordFrag, List<Password> passwords) {
        this.passwordFrag = passwordFrag;
        this.passwords = passwords;
    }

    @Override
    public PasswordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PasswordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_password,parent,false));
    }

    @Override
    public void onBindViewHolder(PasswordAdapter.PasswordViewHolder holder, int position) {

        Password password = passwords.get(position);

        holder.login.setText(password.LoginID);
//        holder.password.setText(password.Password);
        holder.URL.setText(password.WebsiteURL);

        holder.ipcardview.setText(String.valueOf(password.WebsiteURL.charAt(0)));


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(passwordFrag.getActivity(), ReadPassword.class);
            intent.putExtra("id", password.id);
            intent.putExtra("login",password.LoginID);
            intent.putExtra("password", password.Password);
            intent.putExtra("website", password.WebsiteURL);
            passwordFrag.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return passwords.size();
    }

    public static class PasswordViewHolder extends RecyclerView.ViewHolder {

        TextView login,URL,ipcardview;

        public PasswordViewHolder(View itemView) {
            super(itemView);

             ipcardview = itemView.findViewById(R.id.IPcardView);

            login = itemView.findViewById(R.id.Plogin);
            URL = itemView.findViewById(R.id.PWebsite);

        }
    }
}
