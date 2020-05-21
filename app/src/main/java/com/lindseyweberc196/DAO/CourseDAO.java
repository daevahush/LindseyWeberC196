package com.lindseyweberc196.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.lindseyweberc196.Entity.Course;
import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Course course);

    @Delete
    void deleteCourse (Course course);

    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    @Query("SELECT * FROM course_table ORDER BY courseID ASC")
    LiveData<List<Course>> getAllCourses();
}
