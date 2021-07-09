package com.example.safeit.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.safeit.Model.Notes;
import com.example.safeit.Model.Password;
import com.example.safeit.Repository.AllItemsRepository;

import java.util.List;

public class AllItemsViewModel extends AndroidViewModel {

    AllItemsRepository repository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Password>> getAllPasswords;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public AllItemsViewModel(Application application) {
        super(application);

        repository = new AllItemsRepository(application);
        getAllNotes = repository.getAllNotes;
        getAllPasswords = repository.getAllPasswords;
        hightolow = repository.hightolow;
        lowtohigh = repository.lowtohigh;

    }

    public void insertNote(Notes notes){
        repository.insertNotes(notes);
    }

    public void deleteNote(int id){
        repository.deleteNotes(id);
    }

    public void updateNote(Notes notes){
        repository.updateNotes(notes);
    }

    public void insertPassword(Password passwords){
        repository.insertPasswords(passwords);
    }

    public void deletePassword(int id){
        repository.deletePasswords(id);
    }

    public void updatePassword(Password passwords){
        repository.updatePasswords(passwords);
    }

}
