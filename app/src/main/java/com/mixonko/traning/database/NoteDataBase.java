package com.mixonko.traning.database;

import android.content.Context;

import com.mixonko.traning.myAppContext.MyApplication;
import com.mixonko.traning.dao.NoteDao;
import com.mixonko.traning.entity.Note;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(MyApplication.getAppContext(),
                    NoteDataBase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
