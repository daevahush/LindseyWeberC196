package com.lindseyweberc196.Activity.Course;

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

import com.lindseyweberc196.Database.StatusConverter;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.Entity.Term;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.TermAdapter;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.TermViewModel;

import java.util.List;

public class AddCourseActivity extends AppCompatActivity {
    private EditText mEditCourseName;
    private EditText mEditStartDate;
    private EditText mEditEndDate;
    private EditText mEditMentorName;
    private EditText mEditMentorPhone;
    private EditText mEditMentorEmail;
    private RadioGroup mEditStatus;
    private CourseViewModel mCourseViewModel;
    private TermViewModel mTermViewModel;
    private LinearLayout mAvailableTerms;
    private int mSelectedTermID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        mEditCourseName = findViewById(R.id.CourseName);
        mEditStartDate = findViewById(R.id.StartDate);
        mEditEndDate = findViewById(R.id.EndDate);
        mEditMentorName = findViewById(R.id.MentorName);
        mEditMentorPhone = findViewById(R.id.MentorPhone);
        mEditMentorEmail = findViewById(R.id.MentorEmail);
        mEditStatus = findViewById(R.id.CourseStatus);

        //Populate terms to select from
        mAvailableTerms = findViewById(R.id.TermsList);
        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        mTermViewModel.getAllTerms().observe(this, new Observer<List<Term>>() {
            @Override
            public void onChanged(List<Term> terms) {
                RadioGroup tRadioGroup = new RadioGroup(AddCourseActivity.this);

                for(Term term: terms) {
                    RadioButton tRadioButton = new RadioButton(AddCourseActivity.this);
                    tRadioButton.setText(term.getName());
                    tRadioButton.setId(term.getTermID());
                    tRadioGroup.addView(tRadioButton);
                }

                tRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        mSelectedTermID = checkedId;
                    }
                });
                mAvailableTerms.addView(tRadioGroup, 0);
            }
        });

        final Button button = findViewById(R.id.SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String name = mEditCourseName.getText().toString();
                String startDate = mEditStartDate.getText().toString();
                String endDate = mEditEndDate.getText().toString();
                String mentorName = mEditMentorName.getText().toString();
                String mentorPhone = mEditMentorPhone.getText().toString();
                String mentorEmail = mEditMentorEmail.getText().toString();
                int status = mEditStatus.getCheckedRadioButtonId();

                replyIntent.putExtra("CourseName", name);
                replyIntent.putExtra("StartDate", startDate);
                replyIntent.putExtra("EndDate", endDate);
                replyIntent.putExtra("MentorName", mentorName);
                replyIntent.putExtra("MentorPhone", mentorPhone);
                replyIntent.putExtra("MentorEmail", mentorEmail);
                replyIntent.putExtra("TermID", mSelectedTermID);
                replyIntent.putExtra("Status", StatusConverter.toString(status));

                setResult(RESULT_OK, replyIntent);
                finish();

                if(TextUtils.isEmpty(mEditCourseName.getText())) {
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