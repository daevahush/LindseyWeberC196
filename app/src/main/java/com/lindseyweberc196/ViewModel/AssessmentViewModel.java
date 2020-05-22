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
    private LiveData<List<Assessment>> mAssociatedAssessments;
    private int courseID;

    public AssessmentViewModel (Application application) {
        super(application);
        mRepository = new EducationManagementRepository(application);
        mAllAssessments = mRepository.getAllAssessments();
        mAssociatedAssessments = mRepository.getAssociatedAssessments(courseID);
    }

    public LiveData<List<Assessment>> getAllAssessments() {return mAllAssessments;}
    public LiveData<List<Assessment>> getAssociatedAssessments(int courseID) {return mAssociatedAssessments;}

    public void insert(Assessment assessment) {mRepository.insert(assessment);}
}
