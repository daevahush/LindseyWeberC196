package com.lindseyweberc196.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.CourseAdapter;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.TermViewModel;

import java.util.List;

public class TermDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private TermViewModel mTermViewModel;
    private CourseViewModel mCourseViewModel;
    private TextView mTermName;
    private TextView mTermStartDate;
    private TextView mTermEndDate;
    private int mTermID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get selected term details to populate activity with
        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        mTermName = findViewById(R.id.AssessmentName);
        mTermStartDate = findViewById(R.id.StartDate);
        mTermEndDate = findViewById(R.id.EndDate);

        if(getIntent().getStringExtra("TermName")!=null) {
            mTermName.setText(getIntent().getStringExtra("TermName"));
            mTermStartDate.setText(getIntent().getStringExtra("StartDate"));
            mTermEndDate.setText(getIntent().getStringExtra("EndDate"));
        }

        mTermID = (getIntent().getIntExtra("TermID", 0));


        RecyclerView recyclerView = findViewById(R.id.CourseList);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        mCourseViewModel.getAssociatedCourses(mTermID).observe(this, new Observer<List<Course>>() {
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

}
