package com.lindseyweberc196.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.lindseyweberc196.Database.EducationManagementRepository;
import com.lindseyweberc196.Entity.Note;
import com.lindseyweberc196.Entity.Term;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private EducationManagementRepository mRepository;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel (Application application) {
        super(application);
        mRepository = new EducationManagementRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {return mAllNotes;}

    public void insert(Note note) {mRepository.insert(note);}

    public void delete(Note note) {mRepository.delete(note);}

}
