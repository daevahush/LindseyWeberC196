package com.lindseyweberc196.Activity.Assessment;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.Entity.Term;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.CourseAdapter;
import com.lindseyweberc196.UI.TermAdapter;
import com.lindseyweberc196.ViewModel.AssessmentViewModel;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.TermViewModel;

import java.util.List;

public class AddAssessmentActivity extends AppCompatActivity {
    private EditText mEditAssessmentName;
    private EditText mEditDate;
    private EditText mEditAssessmentType;
    private RecyclerView mEditAssociatedCourse;
    private AssessmentViewModel mAssessmentViewModel;
    private CourseViewModel mCourseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAssessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        mEditAssessmentName = findViewById(R.id.AssessmentName);
        mEditDate = findViewById(R.id.Date);
        mEditAssessmentType = findViewById(R.id.AssessmentType);
        mEditAssociatedCourse = findViewById(R.id.CourseList);

        //Populate courses to select from
        RecyclerView recyclerView = findViewById(R.id.CourseList);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        mCourseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) { adapter.setCourses(courses); }
        });


        final Button button = findViewById(R.id.SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String name = mEditAssessmentName.getText().toString();
                String type = mEditAssessmentType.getText().toString();
                String date = mEditDate.getText().toString();
                int course = 1;


                replyIntent.putExtra("AssessmentName", name);
                replyIntent.putExtra("AssessmentType", type);
                replyIntent.putExtra("Date", date);


                if(getIntent().getStringExtra("AssessmentName")!=null) {
//                    Assessment assessment = new Assessment(type, course, name, date);
//                    mAssessmentViewModel.insert(assessment);
                }
                setResult(RESULT_OK, replyIntent);
                finish();

                if(TextUtils.isEmpty(mEditAssessmentName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
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