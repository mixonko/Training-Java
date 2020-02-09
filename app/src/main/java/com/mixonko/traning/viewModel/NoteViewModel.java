package com.mixonko.traning.viewModel;

import android.app.Application;

import com.mixonko.traning.entity.Note;
import com.mixonko.traning.repository.NoteRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void Insert(Note note){
        repository.insert(note);
    }

    public void Update(Note note){
        repository.update(note);
    }

    public void Delete(Note note){
        repository.delete(note);
    }

    public void DeleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

}
