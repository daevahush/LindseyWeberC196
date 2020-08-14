package com.lindseyweberc196.Database;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.lindseyweberc196.DAO.AssessmentDAO;
import com.lindseyweberc196.DAO.CourseDAO;
import com.lindseyweberc196.DAO.NoteDAO;
import com.lindseyweberc196.DAO.TermDAO;
import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.Entity.Note;
import com.lindseyweberc196.Entity.Term;
import java.util.List;

public class EducationManagementRepository {

    private TermDAO mTermDAO;
    private LiveData<List<Term>> mAllTerms;

    private CourseDAO mCourseDAO;
    private LiveData<List<Course>> mAllCourses;

    private AssessmentDAO mAssessmentDAO;
    private LiveData<List<Assessment>> mAllAssessments;

    private NoteDAO mNoteDAO;
    private LiveData<List<Note>> mAllNotes;

    public EducationManagementRepository(Application application) {
        EducationManagementDatabase db = EducationManagementDatabase.getDatabase(application);
        mTermDAO = db.termDAO();
        mAllTerms = mTermDAO.getAllTerms();

        mCourseDAO = db.courseDAO();
        mAllCourses = mCourseDAO.getAllCourses();

        mAssessmentDAO = db.assessmentDAO();
        mAllAssessments = mAssessmentDAO.getAllAssessments();

        mNoteDAO = db.noteDAO();
        mAllNotes = mNoteDAO.getAllNotes();
    }

    public LiveData<List<Term>> getAllTerms() { return mAllTerms;}
    public LiveData<List<Course>> getAssociatedCourses(int termID) {return mCourseDAO.getAssociatedCourses(termID);}
    public LiveData<List<Course>> getAllCourses() {return mAllCourses;}
    public LiveData<List<Assessment>> getAssociatedAssessments(int courseID) {return mAssessmentDAO.getAssociatedAssessments(courseID);}
    public LiveData<List<Assessment>> getAllAssessments() {return mAllAssessments;}
    public LiveData<List<Note>> getAllNotes() {return mAllNotes;}

    public void insert(Term term) {
        EducationManagementDatabase.databaseWriteExecutor.execute(() -> {
            mTermDAO.insert(term);
        });
    }

    public void insert(Course course) {
        EducationManagementDatabase.databaseWriteExecutor.execute(() -> {
            mCourseDAO.insert(course);
        });
    }

    public void insert(Assessment assessment) {
        EducationManagementDatabase.databaseWriteExecutor.execute(() -> {
            mAssessmentDAO.insert(assessment);
        });
    }

    public void insert(Note note) {
        EducationManagementDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDAO.insert(note);
        });
    }

    public void delete(Term term) {
        EducationManagementDatabase.databaseWriteExecutor.execute(() -> {
            mTermDAO.deleteTerm(term);
        });
    }

    public void delete(Course course) {
        EducationManagementDatabase.databaseWriteExecutor.execute(() -> {
            mCourseDAO.deleteCourse(course);
        });
    }

    public void delete(Assessment assessment) {
        EducationManagementDatabase.databaseWriteExecutor.execute(() -> {
            mAssessmentDAO.deleteAssessment(assessment);
        });
    }

    public void delete(Note note) {
        EducationManagementDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDAO.deleteNote(note);
        });
    }

}
