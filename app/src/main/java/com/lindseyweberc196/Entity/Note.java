package com.lindseyweberc196.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {


    @PrimaryKey(autoGenerate = true)
    private int noteID;

    private String noteTitle;
    private String note;
    private int courseID;

    public Note(String noteTitle, String note, int courseID) {
        this.noteTitle = noteTitle;
        this.note = note;
        this.courseID = courseID;
    }

    @Ignore
    public Note(int noteID, String noteTitle, String note, int courseID) {
        this.noteID = noteID;
        this.noteTitle = noteTitle;
        this.note = note;
        this.courseID = courseID;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
