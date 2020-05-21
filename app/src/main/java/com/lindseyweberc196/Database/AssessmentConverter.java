package com.lindseyweberc196.Database;

import androidx.room.TypeConverter;

import com.lindseyweberc196.Entity.Assessment;

public class AssessmentConverter {
    @TypeConverter
    public String toString (Assessment.AssessmentType assessmentType){
        switch (assessmentType) {
            case OBJECTIVE:
                return "Objective Assessment";
            case PERFORMANCE:
                return "Performance Assessment";
        }
        return null;
    }

    @TypeConverter
    public Assessment.AssessmentType toAssessmentType (String assessmentType){
        switch (assessmentType) {
            case "Objective Assessment":
                return Assessment.AssessmentType.OBJECTIVE;
            case "Performance Assessment":
                return Assessment.AssessmentType.PERFORMANCE;
        }
        return null;
    }
}
