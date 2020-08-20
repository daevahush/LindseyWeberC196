package com.lindseyweberc196.Activity.Course;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lindseyweberc196.Activity.Receiver;
import com.lindseyweberc196.Database.StatusConverter;
import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.AssessmentAdapter;
import com.lindseyweberc196.ViewModel.AssessmentViewModel;
import com.lindseyweberc196.ViewModel.CourseViewModel;

import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private CourseViewModel mCourseViewModel;
    private AssessmentViewModel mAssessmentViewModel;
    private TextView mCourseName;
    private TextView mStartDate;
    private TextView mEndDate;
    private int mCourseID;
    private int mTermID;
    private TextView mMentorName;
    private TextView mMentorPhone;
    private TextView mMentorEmail;
    private TextView mStatus;

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
        mStatus = findViewById(R.id.Status);

        if(getIntent().getStringExtra("CourseName")!=null) {
            mCourseName.setText(getIntent().getStringExtra("CourseName"));
            mStartDate.setText(getIntent().getStringExtra("StartDate"));
            mEndDate.setText(getIntent().getStringExtra("EndDate"));
            mMentorName.setText(getIntent().getStringExtra("MentorName"));
            mMentorPhone.setText(getIntent().getStringExtra("MentorPhone"));
            mMentorEmail.setText(getIntent().getStringExtra("MentorEmail"));
            mStatus.setText(getIntent().getStringExtra("Status"));
        }

        //Get associated assessments for Course
        mCourseID = (getIntent().getIntExtra("CourseID", 0));
        mTermID = (getIntent().getIntExtra("TermID", 0));

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
                intent.putExtra("CourseName", mCourseName.getText());
                intent.putExtra("StartDate", mStartDate.getText());
                intent.putExtra("EndDate", mEndDate.getText());
                intent.putExtra("MentorName", mMentorEmail.getText());
                intent.putExtra("MentorPhone", mMentorPhone.getText());
                intent.putExtra("MentorEmail", mMentorEmail.getText());
                intent.putExtra("Status", mStatus.getText());
                intent.putExtra("TermID", mTermID);
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
        getMenuInflater().inflate(R.menu.alert_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Delete button in toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.DeleteButton:
                mCourseViewModel.delete(mCourseID);
                finish();
                return true;

            case R.id.AlertButton:
                Intent intent=new Intent(CourseDetailsActivity.this, Receiver.class);
                intent.putExtra("content","Your course is ending today");
                intent.putExtra("title", mCourseName.getText());
                PendingIntent sender= PendingIntent.getBroadcast(CourseDetailsActivity.this,0,intent,0);
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                String dateToConvert = (getIntent().getStringExtra("EndDate"));
                Toast.makeText(getApplicationContext(), "Alarm has been set for 8:00AM EST " + dateToConvert, Toast.LENGTH_LONG).show();
                long date = Receiver.dateToMillis(dateToConvert);
                alarmManager.set(AlarmManager.RTC_WAKEUP, date, sender);
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }

    //Save edited course details to database
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra("CourseName");
            String startDate = data.getStringExtra("StartDate");
            String endDate = data.getStringExtra("EndDate");
            String mentorName = data.getStringExtra("MentorName");
            String mentorPhone = data.getStringExtra("MentorPhone");
            String mentorEmail = data.getStringExtra("MentorEmail");
            String statusString = data.getStringExtra("Status");
            int termID = data.getIntExtra("TermID", 0);
            Course.Status status = StatusConverter.toStatus(statusString);

            Course course = new Course(mCourseID, termID, name, startDate, endDate, status, mentorName, mentorPhone, mentorEmail);
            mCourseViewModel.insert(course);

            mCourseName.setText(name);
            mStartDate.setText(startDate);
            mEndDate.setText(endDate);
            mMentorName.setText(mentorName);
            mMentorPhone.setText(mentorPhone);
            mMentorEmail.setText(mentorEmail);
            mStatus.setText(statusString);
        }
    }

}
