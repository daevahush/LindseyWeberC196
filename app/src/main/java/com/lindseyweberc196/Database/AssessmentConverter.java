package com.lindseyweberc196.Database;

import androidx.room.TypeConverter;

import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.R;

public class AssessmentConverter {
    @TypeConverter
    public static String toString(Assessment.AssessmentType assessmentType){
        switch (assessmentType) {
            case OBJECTIVE:
                return "Objective Assessment";
            case PERFORMANCE:
                return "Performance Assessment";
        }
        return null;
    }

    @TypeConverter
    public static Assessment.AssessmentType toTypeFromID(int typeID){
        switch (typeID) {
            case R.id.ObjectiveAssessment:
                return Assessment.AssessmentType.OBJECTIVE;
            case R.id.PerformanceAssessment:
                return Assessment.AssessmentType.PERFORMANCE;
        }
        return null;
    }

    @TypeConverter
    public static Assessment.AssessmentType toAssessmentType(String assessmentType){
        switch (assessmentType) {
            case "Objective Assessment":
                return Assessment.AssessmentType.OBJECTIVE;
            case "Performance Assessment":
                return Assessment.AssessmentType.PERFORMANCE;
        }
        return null;
    }

//    @TypeConverter
//    public static Assessment.AssessmentType toTypeFromID(String assessmentTypeID){
//        switch (assessmentTypeID) {
//            case "ObjectiveAssessment":
//                return Assessment.AssessmentType.OBJECTIVE;
//            case "PerformanceAssessment":
//                return Assessment.AssessmentType.PERFORMANCE;
//        }
//        return null;
//    }

    @TypeConverter
    public static int toIDFromType(Assessment.AssessmentType assessmentType){
        switch (assessmentType) {
            case OBJECTIVE:
                return R.id.ObjectiveAssessment;
            case PERFORMANCE:
                return R.id.PerformanceAssessment;
        }
        return -1;
    }
}
