package com.lindseyweberc196.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lindseyweberc196.DAO.AssessmentDAO;
import com.lindseyweberc196.DAO.CourseDAO;
import com.lindseyweberc196.DAO.NoteDAO;
import com.lindseyweberc196.DAO.TermDAO;
import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.Entity.Note;
import com.lindseyweberc196.Entity.Term;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Term.class, Course.class, Assessment.class, Note.class}, version = 2, exportSchema = false)
@TypeConverters({StatusConverter.class, AssessmentConverter.class})
public abstract class EducationManagementDatabase extends RoomDatabase {

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract NoteDAO noteDAO();

    private static volatile  EducationManagementDatabase INSTANCE;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(1);

    static EducationManagementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EducationManagementDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EducationManagementDatabase.class, "Education_Management_Database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {

                // Populate the database in the background.
                // If you want to start with more things, just add them.
                TermDAO mTermDao = INSTANCE.termDAO();
                mTermDao.deleteAllTerms();
                CourseDAO mCourseDao = INSTANCE.courseDAO();
                mCourseDao.deleteAllCourses();
                AssessmentDAO mAssessmentDao = INSTANCE.assessmentDAO();
                mAssessmentDao.deleteAllAssessments();
                NoteDAO mNoteDao = INSTANCE.noteDAO();
                mNoteDao.deleteAllNotes();

                Term term = new Term(1,"Term 1","05/01/2020","12/01/2020");
                mTermDao.insert(term);
                term = new Term(2,"Term 2","12/01/2020","05/01/2021");
                mTermDao.insert(term);
                term = new Term(3,"Term 3","05/01/2021","12/01/2021");
                mTermDao.insert(term);

                Course course = new Course(1,1,"Mobile Application", "05/01/2020", "08/20/2020", Course.Status.INPROGRESS, "Tim", "727=867-5309", "email@email.com");
                mCourseDao.insert(course);
                course = new Course(2,2,"Software 1", "05/01/2020", "06/01/2020", Course.Status.INPROGRESS, "Tim", "727=867-5309", "email@email.com");
                mCourseDao.insert(course);

                Assessment assessment = new Assessment(1, 1, "ABM1", "05/14/2020", Assessment.AssessmentType.PERFORMANCE);
                mAssessmentDao.insert(assessment);

                Note note = new Note(1, "Note 1","This is a note", 1);
                mNoteDao.insert(note);
                note = new Note(2, "Note 2","This is a note", 2);
                mNoteDao.insert(note);

            });
        }
    };

}