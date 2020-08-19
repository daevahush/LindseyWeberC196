package com.lindseyweberc196.Activity.Course;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.lindseyweberc196.Database.StatusConverter;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.CourseAdapter;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {
    private CourseViewModel mCourseViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoursesActivity.this, AddCourseActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.CoursesList);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        mCourseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                adapter.setCourses(courses);
            }
        });
    }

    //Back button in toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //Add new course to database
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String status = data.getStringExtra("Status");
            Course.Status courseStatus = StatusConverter.toStatus(status);
            int termID = data.getIntExtra("TermID", -1);
            String courseName = data.getStringExtra("CourseName");
            String startDate = data.getStringExtra("StartDate");
            String endDate = data.getStringExtra("EndDate");
            String mentorName = data.getStringExtra("MentorName");
            String mentorPhone = data.getStringExtra("MentorPhone");
            String mentorEmail = data.getStringExtra("MentorEmail");

            Course course = new Course(courseStatus, termID, courseName, startDate, endDate, mentorName, mentorPhone, mentorEmail);
            mCourseViewModel.insert(course);
        }
    }
}
