package com.lindseyweberc196.DAO;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.Entity.Note;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Note note);

    @Query("DELETE FROM note_table WHERE noteID = :ID")
    void deleteNote (int ID);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY noteID ASC")
    LiveData<List<Note>> getAllNotes();
}
