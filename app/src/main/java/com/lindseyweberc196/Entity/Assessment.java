package com.lindseyweberc196.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.lindseyweberc196.Database.AssessmentConverter;

@Entity(tableName = "assessment_table")
public class Assessment {

    @PrimaryKey(autoGenerate = true)
    private int assessmentID;

    @TypeConverters(AssessmentConverter.class)
    private AssessmentType assessmentType;

    private int courseID;
    private String name;
    private String date;

    public enum AssessmentType {
        OBJECTIVE {
            @Override
            public String toString() {return "Objective Assessment";}
        },
        PERFORMANCE {
            @Override
            public String toString() {return "Performance Assessment";}
        };
    }

    public Assessment(AssessmentType assessmentType, int courseID, String name, String date) {
        this.assessmentType = assessmentType;
        this.courseID = courseID;
        this.name = name;
        this.date = date;
    }

    @Ignore
    public Assessment(int assessmentID, int courseID, String name, String date, AssessmentType assessmentType) {
        this.assessmentID = assessmentID;
        this.courseID = courseID;
        this.name = name;
        this.date = date;
        this.assessmentType = assessmentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssessmentType getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(AssessmentType assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

}


