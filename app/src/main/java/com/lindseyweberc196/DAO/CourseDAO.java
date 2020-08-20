package com.lindseyweberc196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.lindseyweberc196.Entity.Course;
import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Course course);

    @Query("DELETE FROM course_table WHERE courseID = :ID")
    void deleteCourse (int ID);

    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    @Query("SELECT * FROM course_table ORDER BY courseID ASC")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course_table WHERE termID = :termID")
    LiveData<List<Course>> getAssociatedCourses(int termID);

    @Query("SELECT * FROM course_table WHERE courseID = :courseID LIMIT 1")
    LiveData<Course> getCourseByID(int courseID);

    @Query("SELECT COUNT(termID) FROM course_table WHERE termID = :termID")
    int countAssociatedCourses(int termID);
}
