package com.lindseyweberc196.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.lindseyweberc196.Database.EducationManagementRepository;
import com.lindseyweberc196.Entity.Assessment;


import java.util.List;

public class AssessmentViewModel extends AndroidViewModel {

    private EducationManagementRepository mRepository;
    private LiveData<List<Assessment>> mAllAssessments;

    public AssessmentViewModel (Application application) {
        super(application);
        mRepository = new EducationManagementRepository(application);
        mAllAssessments = mRepository.getAllAssessments();
    }

    public LiveData<List<Assessment>> getAllAssessments() {return mAllAssessments;}
    public LiveData<List<Assessment>> getAssociatedAssessments(int courseID) {return mRepository.getAssociatedAssessments(courseID);}

    public void insert(Assessment assessment) {mRepository.insert(assessment);}
}
