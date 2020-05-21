package com.lindseyweberc196.Database;

import androidx.room.TypeConverter;

import com.lindseyweberc196.Entity.Course;

public class StatusConverter {
    @TypeConverter
    public String toString (Course.Status status){
        switch (status) {
            case INPROGRESS:
                return "In Progress";
            case COMPLETED:
                return "Completed";
            case DROPPED:
                return "Dropped";
            case PLANTOTAKE:
                return "Plan to Take";
        }
        return null;
    }

    @TypeConverter
    public Course.Status toStatus (String status){
        switch (status) {
            case "In Progress":
                return Course.Status.INPROGRESS;
            case "Completed":
                return Course.Status.COMPLETED;
            case "Dropped":
                return Course.Status.DROPPED;
            case "Plan to Take":
                return Course.Status.PLANTOTAKE;
        }
        return null;
    }
}

