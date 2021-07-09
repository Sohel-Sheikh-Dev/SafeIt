package com.example.safeit.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.safeit.Dao.NotesDao;
import com.example.safeit.Dao.PasswordDao;
import com.example.safeit.Model.Notes;
import com.example.safeit.Model.Password;

@Database(entities = {Notes.class, Password.class},version = 1)
public abstract class AllItemDatabase extends RoomDatabase{

    public abstract NotesDao notesDao();
    public abstract PasswordDao passwordDao();

    public static AllItemDatabase INSTANCE;

    public static AllItemDatabase getDatabaseInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AllItemDatabase.class,
                    "MyDatabase").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
