package com.lindseyweberc196.Activity.Term;

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

import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.Entity.Term;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.CourseAdapter;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.TermViewModel;

import java.util.List;

public class EditTermActivity extends AppCompatActivity {
    private TermViewModel mTermViewModel;
    private EditText mTermName;
    private EditText mTermStartDate;
    private EditText mTermEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get selected term details to populate activity with
        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        mTermName = findViewById(R.id.TermName);
        mTermStartDate = findViewById(R.id.StartDate);
        mTermEndDate = findViewById(R.id.EndDate);

        if(getIntent().getStringExtra("TermName")!=null) {
            mTermName.setText(getIntent().getStringExtra("TermName"));
            mTermStartDate.setText(getIntent().getStringExtra("StartDate"));
            mTermEndDate.setText(getIntent().getStringExtra("EndDate"));
        }

        final Button button = findViewById(R.id.SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String name = mTermName.getText().toString();
                String startDate = mTermStartDate.getText().toString();
                String endDate = mTermEndDate.getText().toString();

                replyIntent.putExtra("TermName", name);
                replyIntent.putExtra("StartDate", startDate);
                replyIntent.putExtra("EndDate", endDate);

                setResult(RESULT_OK, replyIntent);
                finish();

                if(TextUtils.isEmpty(mTermName.getText())) {
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

