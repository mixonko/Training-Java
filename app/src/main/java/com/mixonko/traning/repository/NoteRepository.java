package com.mixonko.traning.repository;

import android.content.Context;
import android.os.AsyncTask;

import com.mixonko.traning.dao.NoteDao;
import com.mixonko.traning.database.NoteDataBase;
import com.mixonko.traning.entity.Note;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Context context){
        NoteDataBase noteDataBase = NoteDataBase.getInstance(context);
        noteDao = noteDataBase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void update(Note note){
        new UpdateNoteAsuncTask(noteDao).execute(note);
    }

    public void insert(Note note){
        new InsertNoteAsuncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsuncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsuncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private InsertNoteAsuncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsuncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private UpdateNoteAsuncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsuncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private DeleteNoteAsuncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
