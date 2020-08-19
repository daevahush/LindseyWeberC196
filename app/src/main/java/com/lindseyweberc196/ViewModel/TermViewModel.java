package com.lindseyweberc196.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.lindseyweberc196.Database.EducationManagementRepository;
import com.lindseyweberc196.Entity.Term;
import java.util.List;

public class TermViewModel extends AndroidViewModel {

    private EducationManagementRepository mRepository;
    private LiveData<List<Term>> mAllTerms;

    public TermViewModel (Application application) {
        super(application);
        mRepository = new EducationManagementRepository(application);
        mAllTerms = mRepository.getAllTerms();
    }

    public LiveData<List<Term>> getAllTerms() {return mAllTerms;}

    public void insert(Term term) {mRepository.insert(term);}

    public void delete(int ID) {mRepository.deleteTerm(ID);}

}
