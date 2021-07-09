package com.example.safeit.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safeit.Activity.ReadPassword;
import com.example.safeit.Activity.UpdateNotes;
import com.example.safeit.Fragments.NotesFrag;
import com.example.safeit.Fragments.PasswordFrag;
import com.example.safeit.Model.Notes;
import com.example.safeit.Model.Password;
import com.example.safeit.R;
import com.example.safeit.databinding.ItemPasswordBinding;

import java.util.List;

import static com.example.safeit.Adapter.ItemClass.ItemNotes;
import static com.example.safeit.Adapter.ItemClass.ItemPassword;

public class AllItemAdapter extends RecyclerView.Adapter{

    private final List<ItemClass> itemClassList;
    List<Password> passwords;
    List<Notes> notes;
    PasswordFrag passwordFrag;
    NotesFrag notesFrag;

    // public constructor for this class
    public AllItemAdapter(List<ItemClass> itemClassList)
    {
        this.itemClassList = itemClassList;
    }

    // Override the getItemViewType method.
    // This method uses a switch statement
    // to assign the layout to each item
    // depending on the viewType passed

    @Override
    public int getItemViewType(int position)
    {
        switch (itemClassList.get(position).getViewType()) {
            case 0:
                return ItemPassword;
            case 1:
                return ItemNotes;
            default:
                return -1;
        }
    }

    // Create classes for each layout ViewHolder.

    public static class allItemsViewHolder extends RecyclerView.ViewHolder {

        TextView login,URL,ipcardview;

        public allItemsViewHolder(View itemView) {
            super(itemView);

            ipcardview = itemView.findViewById(R.id.IPcardView);

            login = itemView.findViewById(R.id.Plogin);
            URL = itemView.findViewById(R.id.PWebsite);

        }
    }

    // similarly a class for the second layout is also
    // created.

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        //        TextView login,password,URL;
        TextView title,subtitle,notesDate;
        View notesPriority;

        public NotesViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);


        }
    }



    public class NativAdViewHolder extends RecyclerView.ViewHolder {
        TextView title,subtitle,notesDate;
        View notesPriority;
        public NativAdViewHolder(View view) {
            super(view);
            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView login,URL,ipcardview;

        public ViewHolder(final View view) {
            super(view);
            ipcardview = itemView.findViewById(R.id.IPcardView);

            login = itemView.findViewById(R.id.Plogin);
            URL = itemView.findViewById(R.id.PWebsite);


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ItemPassword:
                return new ViewHolder(from.inflate(R.layout.item_password, parent, false));
            case ItemNotes:
                return new NativAdViewHolder(from.inflate(R.layout.item_notes, parent, false));
                
            default:
                return null;
        }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position)
    {


        Log.d("Tag",""+itemClassList.size());


        if (viewHolder instanceof ViewHolder) {


//            ((ViewHolder) viewHolder).login.setText(itemClassList.get(position).);


    } else if (viewHolder instanceof NativAdViewHolder)

    {

    }





    }


    // This method returns the count of items present in the
    // RecyclerView at any given time.

    @Override
    public int getItemCount()
    {
        return itemClassList.size();

    }
}