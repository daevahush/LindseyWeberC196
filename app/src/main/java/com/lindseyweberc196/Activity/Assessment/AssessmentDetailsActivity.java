package com.lindseyweberc196.Activity.Assessment;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private TextView mDate;
    private TextView mAssessmentType;

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
        mDate = findViewById(R.id.Date);
        mAssessmentType = findViewById(R.id.AssessmentType);

        if(getIntent().getStringExtra("AssessmentName")!=null) {
            mAssessmentName.setText(getIntent().getStringExtra("AssessmentName"));
            mDate.setText(getIntent().getStringExtra("AssessmentDate"));
            mAssessmentType.setText(getIntent().getStringExtra("Type"));
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssessmentDetailsActivity.this, EditAssessmentActivity.class);
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

}
