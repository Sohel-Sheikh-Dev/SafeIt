package com.example.safeit.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.safeit.Activity.UpdateNotes;
import com.example.safeit.Fragments.NotesFrag;
import com.example.safeit.Model.Notes;
import com.example.safeit.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    NotesFrag notesFrag;
    List<Notes> notes;

    public NotesAdapter(NotesFrag notesFrag, List<Notes> notes) {
        this.notesFrag = notesFrag;
        this.notes = notes;
    }

    public void searchNotes(List<Notes> filterredNotes){
        this.notes = filterredNotes;
        notifyDataSetChanged();
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes,parent,false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.NotesViewHolder holder, int position) {

        Notes note = notes.get(position);

        switch (note.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                break;
        }

        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(notesFrag.getActivity(), UpdateNotes.class);
            intent.putExtra("id", note.id);
            intent.putExtra("title", note.notesTitle);
            intent.putExtra("subtitle", note.notesSubtitle);
            intent.putExtra("priority", note.notesPriority);
            intent.putExtra("notes", note.notes);
            notesFrag.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

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
}
