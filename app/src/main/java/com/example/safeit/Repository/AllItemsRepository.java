package com.example.safeit.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.safeit.Dao.NotesDao;
import com.example.safeit.Dao.PasswordDao;
import com.example.safeit.Database.AllItemDatabase;
import com.example.safeit.Model.Notes;
import com.example.safeit.Model.Password;

import java.util.List;

public class AllItemsRepository {

    public NotesDao notesDao;
    public PasswordDao passwordDao;

    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Password>> getAllPasswords;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public AllItemsRepository(Application application) {
        AllItemDatabase database = AllItemDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        passwordDao = database.passwordDao();
        hightolow = notesDao.highToLow();
        lowtohigh = notesDao.lowToHigh();
        getAllNotes = notesDao.getAllNotes();
        getAllPasswords = passwordDao.getAllPasswords();

    }

    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }

    public void insertPasswords(Password passwords){
        passwordDao.insertPassword(passwords);
    }

    public void deletePasswords(int id){
        passwordDao.deletePassword(id);
    }

    public void updatePasswords(Password passwords){
        passwordDao.updatePassword(passwords);
    }

}
