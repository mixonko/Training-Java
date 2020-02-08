package com.mixonko.traning.database;

import android.content.Context;
import android.os.AsyncTask;

import com.mixonko.traning.myAppContext.MyApplication;
import com.mixonko.traning.dao.NoteDao;
import com.mixonko.traning.entity.Note;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(MyApplication.getAppContext(),
                    NoteDataBase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new pupelateDbAsyncTask(instance).execute();
        }
    };

    private static class pupelateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao;

        private pupelateDbAsyncTask(NoteDataBase db){
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title 1", "description 1", 1));
            noteDao.insert(new Note("title 2", "description 2", 2));
            noteDao.insert(new Note("title 3", "description 3", 3));
            return null;
        }
    }
}
