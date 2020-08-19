package com.lindseyweberc196.Database;

import androidx.room.TypeConverter;

import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.R;

public class StatusConverter {
    @TypeConverter
    public static String toString(Course.Status status){
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
    public static String toString(int statusID){
        switch (statusID) {
            case R.id.InProgress:
                return "In Progress";
            case R.id.Completed:
                return "Completed";
            case R.id.Dropped:
                return "Dropped";
            case R.id.PlanToTake:
                return "Plan to Take";
        }
        return null;
    }

    @TypeConverter
    public static Course.Status toStatus(String status){
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

    @TypeConverter
    public static Course.Status toStatus(int statusID){
        switch (statusID) {
            case R.id.InProgress:
                return Course.Status.INPROGRESS;
            case R.id.Completed:
                return Course.Status.COMPLETED;
            case R.id.Dropped:
                return Course.Status.DROPPED;
            case R.id.PlanToTake:
                return Course.Status.PLANTOTAKE;
        }
        return null;
    }

    @TypeConverter
    public static int toID(Course.Status status) {
        switch (status) {
            case INPROGRESS:
                return R.id.InProgress;
            case COMPLETED:
                return R.id.Completed;
            case DROPPED:
                return R.id.Dropped;
            case PLANTOTAKE:
                return R.id.PlanToTake;
        }
        return -1;
    }

    @TypeConverter
    public static int toID(String status) {
        switch (status) {
            case "In Progress":
                return R.id.InProgress;
            case "Completed":
                return R.id.Completed;
            case "Dropped":
                return R.id.Dropped;
            case "Plan to Take":
                return R.id.PlanToTake;
        }
        return -1;
    }

}

