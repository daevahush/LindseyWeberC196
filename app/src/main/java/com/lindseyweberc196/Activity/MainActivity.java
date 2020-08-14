package com.lindseyweberc196.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lindseyweberc196.Activity.Assessment.AssessmentsActivity;
import com.lindseyweberc196.Activity.Course.CoursesActivity;
import com.lindseyweberc196.Activity.Note.NotesActivity;
import com.lindseyweberc196.Activity.Term.TermsActivity;
import com.lindseyweberc196.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToTerms(View view) {
        Intent intent = new Intent(MainActivity.this, TermsActivity.class);
        startActivity(intent);
    }

    public void goToCourses(View view) {
        Intent intent = new Intent(MainActivity.this, CoursesActivity.class);
        startActivity(intent);
    }

    public void goToAssessments(View view) {
        Intent intent = new Intent(MainActivity.this, AssessmentsActivity.class);
        startActivity(intent);
    }

    public void goToNotes(View view) {
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        startActivity(intent);
    }

}
