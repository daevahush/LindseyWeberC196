package com.lindseyweberc196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.Entity.Course;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Assessment assessment);

    @Delete
    void deleteAssessment (Assessment assessment);

    @Query("DELETE FROM assessment_table")
    void deleteAllAssessments();

    @Query("SELECT * FROM assessment_table ORDER BY assessmentID ASC")
    LiveData<List<Assessment>> getAllAssessments();

    @Query("SELECT * FROM assessment_table WHERE courseID = :courseID")
    LiveData<List<Assessment>> getAssociatedAssessments(int courseID);
}
