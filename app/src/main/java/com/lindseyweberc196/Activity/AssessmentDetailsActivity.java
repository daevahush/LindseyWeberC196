package com.lindseyweberc196.Activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;

import com.lindseyweberc196.R;
import com.lindseyweberc196.ViewModel.AssessmentViewModel;

public class AssessmentDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private AssessmentViewModel mAssessmentViewModel;
    private TextView mAssessmentName;
    private TextView mStartDate;
    private TextView mEndDate;

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

        //Get selected term details to populate activity with
        mAssessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        mAssessmentName = findViewById(R.id.AssessmentName);
        mStartDate = findViewById(R.id.StartDate);
        mEndDate = findViewById(R.id.EndDate);

        if(getIntent().getStringExtra("AssessmentName")!=null) {
            mAssessmentName.setText(getIntent().getStringExtra("AssessmentName"));
            mStartDate.setText(getIntent().getStringExtra("StartDate"));
            mEndDate.setText(getIntent().getStringExtra("EndDate"));
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
