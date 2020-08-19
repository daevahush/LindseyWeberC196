package com.lindseyweberc196.Activity.Assessment;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lindseyweberc196.Database.AssessmentConverter;
import com.lindseyweberc196.Database.StatusConverter;
import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.AssessmentAdapter;
import com.lindseyweberc196.UI.CourseAdapter;
import com.lindseyweberc196.ViewModel.AssessmentViewModel;
import com.lindseyweberc196.ViewModel.CourseViewModel;

import java.util.List;

public class AssessmentDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private AssessmentViewModel mAssessmentViewModel;
    private CourseViewModel mCourseViewModel;
    private TextView mAssessmentName;
    private TextView mDate;
    private TextView mAssessmentType;
    private int mCourseID;
    private int mAssessmentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get selected assessment details to populate activity with
        mAssessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        mAssessmentName = findViewById(R.id.AssessmentName);
        mDate = findViewById(R.id.Date);
        mAssessmentType = findViewById(R.id.AssessmentType);

        if(getIntent().getStringExtra("AssessmentName")!=null) {
            mAssessmentName.setText(getIntent().getStringExtra("AssessmentName"));
            mDate.setText(getIntent().getStringExtra("Date"));
            mAssessmentType.setText(getIntent().getStringExtra("Type"));
        }

        //Get associated course for assessment
        mCourseID = (getIntent().getIntExtra("CourseID", 0));
        mAssessmentID = (getIntent().getIntExtra("AssessmentID", 0));

        RecyclerView recyclerView = findViewById(R.id.CourseList);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        mCourseViewModel.getAssociatedCourses(mCourseID).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                adapter.setCourses(courses);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentDetailsActivity.this, EditAssessmentActivity.class);
                intent.putExtra("AssessmentName", mAssessmentName.getText());
                intent.putExtra("Date", mDate.getText());
                intent.putExtra("AssessmentType", mAssessmentType.getText());
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
        getMenuInflater().inflate(R.menu.detail_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Delete button in toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.DeleteButton:
                mAssessmentViewModel.delete(mAssessmentID);
                finish();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    //Save edited assessment details to database
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra("AssessmentName");
            String date = data.getStringExtra("Date");
            String assessmentType = data.getStringExtra("Type");
            Assessment.AssessmentType type = AssessmentConverter.toAssessmentType(assessmentType);
            int courseID = data.getIntExtra("CourseID", 0);

            Assessment assessment = new Assessment(mAssessmentID, courseID, name, date, type);
            mAssessmentViewModel.insert(assessment);

            mAssessmentName.setText(name);
            mDate.setText(date);
            mAssessmentType.setText(assessmentType);
        }
    }
}
