package com.lindseyweberc196.Activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;

import com.lindseyweberc196.R;
import com.lindseyweberc196.ViewModel.AssessmentViewModel;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.TermViewModel;

public class CourseDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private CourseViewModel mCourseViewModel;
    private AssessmentViewModel mAssessmentViewModel;
    private TextView mCourseName;
    private TextView mStartDate;
    private TextView mEndDate;
    private TextView mNote;
    private int mCourseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get selected term details to populate activity with
        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        mCourseName = findViewById(R.id.CourseName);
        mStartDate = findViewById(R.id.StartDate);
        mEndDate = findViewById(R.id.EndDate);
        mNote = findViewById(R.id.Note);

        if(getIntent().getStringExtra("CourseName")!=null) {
            mCourseName.setText(getIntent().getStringExtra("CourseName"));
            mStartDate.setText(getIntent().getStringExtra("StartDate"));
            mEndDate.setText(getIntent().getStringExtra("EndDate"));
            mNote.setText(getIntent().getStringExtra("Note"));
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
