package com.lindseyweberc196.Activity.Note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.R;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.NoteViewModel;

import java.util.List;

public class EditNoteActivity extends AppCompatActivity {
    private EditText mNoteTitle;
    private EditText mNote;
    private NoteViewModel mNoteViewModel;
    private CourseViewModel mCourseViewModel;
    private LinearLayout mAvailableCourses;
    private Context mContext;
    private int mSelectedCourseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Get selected note details to populate activity with
        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteTitle = findViewById(R.id.NoteTitle);
        mNote = findViewById(R.id.Note);

        if(getIntent().getStringExtra("NoteTitle")!=null) {
            mNoteTitle.setText(getIntent().getStringExtra("NoteTitle"));
            mNote.setText(getIntent().getStringExtra("Note"));
        }

        //Populate courses to select from
        mAvailableCourses = findViewById(R.id.CoursesList);
        mContext = this;
        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        mCourseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                RadioGroup tRadioGroup = new RadioGroup(mContext);

                for(Course course: courses) {
                    RadioButton tRadioButton = new RadioButton(mContext);
                    tRadioButton.setText(course.getTitle());
                    tRadioButton.setId(course.getCourseID());
                    tRadioGroup.addView(tRadioButton);
                }

                tRadioGroup.check(getIntent().getIntExtra("CourseID", 0));
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

                String title = mNoteTitle.getText().toString();
                String noteBody = mNote.getText().toString();

                replyIntent.putExtra("NoteTitle", title);
                replyIntent.putExtra("Note", noteBody);
                replyIntent.putExtra("CourseID", mSelectedCourseID);

                setResult(RESULT_OK, replyIntent);
                finish();

                if(TextUtils.isEmpty(mNoteTitle.getText())) {
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