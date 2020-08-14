package com.lindseyweberc196.Activity.Course;

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
import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.AssessmentAdapter;
import com.lindseyweberc196.UI.CourseAdapter;
import com.lindseyweberc196.ViewModel.AssessmentViewModel;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.TermViewModel;

import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private CourseViewModel mCourseViewModel;
    private AssessmentViewModel mAssessmentViewModel;
    private TextView mCourseName;
    private TextView mStartDate;
    private TextView mEndDate;
    private int mCourseID;
    private TextView mMentorName;
    private TextView mMentorPhone;
    private TextView mMentorEmail;

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
        mMentorName = findViewById(R.id.MentorName);
        mMentorPhone = findViewById(R.id.MentorPhone);
        mMentorEmail = findViewById(R.id.MentorEmail);

        if(getIntent().getStringExtra("CourseName")!=null) {
            mCourseName.setText(getIntent().getStringExtra("CourseName"));
            mStartDate.setText(getIntent().getStringExtra("StartDate"));
            mEndDate.setText(getIntent().getStringExtra("EndDate"));
            mMentorName.setText(getIntent().getStringExtra("MentorName"));
            mMentorPhone.setText(getIntent().getStringExtra("MentorPhone"));
            mMentorEmail.setText(getIntent().getStringExtra("MentorEmail"));
        }

        mCourseID = (getIntent().getIntExtra("CourseID", 0));

        RecyclerView recyclerView = findViewById(R.id.AssessmentList);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAssessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        mAssessmentViewModel.getAssociatedAssessments(mCourseID).observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(List<Assessment> assessments) {
                adapter.setAssessments(assessments);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetailsActivity.this, EditCourseActivity.class);
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
