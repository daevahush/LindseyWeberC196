package com.lindseyweberc196.Activity.Note;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lindseyweberc196.Activity.Term.EditTermActivity;
import com.lindseyweberc196.Activity.Term.TermDetailsActivity;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.Entity.Note;
import com.lindseyweberc196.Entity.Term;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.CourseAdapter;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.NoteViewModel;
import com.lindseyweberc196.ViewModel.TermViewModel;

import java.util.List;

public class NoteDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private NoteViewModel mNoteViewModel;
    private CourseViewModel mCourseViewModel;
    private TextView mNoteTitle;
    private TextView mCourseName;
    private TextView mNote;
    private int mNoteID;
    private int mCourseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get selected note details to populate activity with
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteTitle = findViewById(R.id.NoteTitle);
        mCourseName = findViewById(R.id.CourseName);
        mNote = findViewById(R.id.Note);

        if(getIntent().getStringExtra("NoteTitle")!=null) {
            mNoteTitle.setText(getIntent().getStringExtra("NoteTitle"));
            mNote.setText(getIntent().getStringExtra("Note"));
        }

        mNoteID = (getIntent().getIntExtra("NoteID", 0));

        //Get the course title
        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        mCourseID = (getIntent().getIntExtra("CourseID", 0));
        LiveData<Course> tCourse = mCourseViewModel.getCourseByID(mCourseID);

        tCourse.observe(this, new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                mCourseName.setText(course.getTitle());
            }
        });

        //Edit note button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteDetailsActivity.this, EditNoteActivity.class);
                intent.putExtra("NoteTitle", mNoteTitle.getText());
                intent.putExtra("Note", mNote.getText());
                intent.putExtra("CourseID", mCourseID);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }


    //Back button in toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //Options menu in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Delete button in toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.DeleteButton:
                mNoteViewModel.delete(mNoteID);
                finish();
                return true;

            case R.id.ShareButton:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is a note");
                // (Optional) Here we're setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Note Title");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }


    //Save edited note details to database
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra("NoteTitle");
            String noteBody = data.getStringExtra("Note");
            int courseID = data.getIntExtra("CourseID", -1);

            Note note = new Note(mNoteID, title, noteBody, courseID);
            mNoteViewModel.insert(note);

            mNoteTitle.setText(title);
            mNote.setText(noteBody);
        }
    }


}