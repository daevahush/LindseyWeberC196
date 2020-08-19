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
    public LiveData<List<Course>> getAssociatedCourses(int termID) {return mRepository.getAssociatedCourses(termID);}
    public LiveData<Course> getCourseByID(int courseID) {return mRepository.getCourseByID(courseID);}

    public void insert(Course course) {mRepository.insert(course);}

    public void delete(int ID) {mRepository.deleteCourse(ID);}

}
