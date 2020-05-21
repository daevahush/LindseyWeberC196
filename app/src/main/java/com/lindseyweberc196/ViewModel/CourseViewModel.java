package com.lindseyweberc196.ViewModel;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.AndroidViewModel;
import com.lindseyweberc196.Database.EducationManagementRepository;
import com.lindseyweberc196.Entity.Course;
import java.util.List;

public class CourseViewModel extends AndroidViewModel{

    private EducationManagementRepository mRepository;
    private LiveData<List<Course>> mAllCourses;

    public CourseViewModel (Application application) {
        super(application);
        mRepository = new EducationManagementRepository(application);
        mAllCourses = mRepository.getAllCourses();
    }

    public LiveData<List<Course>> getAllCourses() {return mAllCourses;}

    public void insert(Course course) {mRepository.insert(course);}
}
