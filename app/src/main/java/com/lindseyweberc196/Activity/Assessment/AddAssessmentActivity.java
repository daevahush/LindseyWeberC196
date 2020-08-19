package com.lindseyweberc196.Activity.Assessment;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lindseyweberc196.Database.AssessmentConverter;
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
    private RadioGroup mAssessmentType;
    private AssessmentViewModel mAssessmentViewModel;
    private CourseViewModel mCourseViewModel;
    private LinearLayout mAvailableCourses;
    private int mSelectedCourseID;

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
        mAssessmentType = findViewById(R.id.AssessmentType);

        //Populate courses to select from
        mAvailableCourses = findViewById(R.id.CourseList);
        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        mCourseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                RadioGroup tRadioGroup = new RadioGroup(AddAssessmentActivity.this);

                for(Course course: courses) {
                    RadioButton tRadioButton = new RadioButton(AddAssessmentActivity.this);
                    tRadioButton.setText(course.getTitle());
                    tRadioButton.setId(course.getCourseID());
                    tRadioGroup.addView(tRadioButton);
                }

                tRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        mSelectedCourseID = checkedId;
                    }
                });
                mAvailableCourses.addView(tRadioGroup, 0);
            }
        });


        final Button button = findViewById(R.id.SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String name = mEditAssessmentName.getText().toString();
                String date = mEditDate.getText().toString();
                int typeID = mAssessmentType.getCheckedRadioButtonId();
                Assessment.AssessmentType type = AssessmentConverter.toTypeFromID(typeID);
                String typeString = AssessmentConverter.toString(type);

                replyIntent.putExtra("AssessmentName", name);
                replyIntent.putExtra("Date", date);
                replyIntent.putExtra("CourseID", mSelectedCourseID);
                replyIntent.putExtra("Type", typeString);

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