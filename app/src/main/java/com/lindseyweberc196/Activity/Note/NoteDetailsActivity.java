package com.lindseyweberc196.Activity.Note;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.lindseyweberc196.Activity.Term.EditTermActivity;
import com.lindseyweberc196.Activity.Term.TermDetailsActivity;
import com.lindseyweberc196.Entity.Course;
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

        //Get selected term details to populate activity with
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteTitle = findViewById(R.id.NoteTitle);
        mCourseName = findViewById(R.id.CourseName);
        mNote = findViewById(R.id.Note);

        if(getIntent().getStringExtra("NoteName")!=null) {
            mNoteTitle.setText(getIntent().getStringExtra("NoteName"));
            mCourseName.setText(getIntent().getStringExtra("CourseName"));
            mNote.setText(getIntent().getStringExtra("Note"));
        }


        //Get the course title somehow
        mCourseID = (getIntent().getIntExtra("CourseID", 0));

//        RecyclerView recyclerView = findViewById(R.id.CourseList);
//        final CourseAdapter adapter = new CourseAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
//
//        mCourseViewModel.getAssociatedCourses(mTermID).observe(this, new Observer<List<Course>>() {
//            @Override
//            public void onChanged(List<Course> courses) {
//                adapter.setCourses(courses);
//            }
//        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteDetailsActivity.this, EditNoteActivity.class);
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


}