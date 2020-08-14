package com.lindseyweberc196.Entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.lindseyweberc196.Database.StatusConverter;

@Entity(tableName = "course_table")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;

    @TypeConverters(StatusConverter.class)
    private Status status;

    private int termID;
    private String title;
    private String startDate;
    private String endDate;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;

    public enum Status {
        INPROGRESS {
            @Override
            public String toString() {return "In Progress";}
        },
        COMPLETED {
            @Override
            public String toString() {return "Completed";}},
        DROPPED {
            @Override
            public String toString() {return "Dropped";}
        },
        PLANTOTAKE {
            @Override
            public String toString() {return "Plan to Take";}
        };
    }

    public Course(Status status, int termID, String title, String startDate, String endDate, String mentorName, String mentorPhone, String mentorEmail) {
        this.status = status;
        this.termID = termID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
    }

    @Ignore
    public Course(int courseID, int termID, String title, String startDate, String endDate, Status status, String mentorName, String mentorPhone, String mentorEmail) {
        this.courseID = courseID;
        this.termID = termID;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

}
