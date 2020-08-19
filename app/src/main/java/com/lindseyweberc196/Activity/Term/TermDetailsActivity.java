package com.lindseyweberc196.Activity.Term;

import android.content.Intent;
import android.os.Bundle;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.Entity.Term;
import com.lindseyweberc196.R;
import com.lindseyweberc196.UI.CourseAdapter;
import com.lindseyweberc196.ViewModel.CourseViewModel;
import com.lindseyweberc196.ViewModel.TermViewModel;

import java.util.List;

public class TermDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private TermViewModel mTermViewModel;
    private CourseViewModel mCourseViewModel;
    private TextView mTermName;
    private TextView mTermStartDate;
    private TextView mTermEndDate;
    private int mTermID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
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

        //Get associated courses for Term
        mTermID = (getIntent().getIntExtra("TermID", 0));

        RecyclerView recyclerView = findViewById(R.id.CourseList);
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCourseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        mCourseViewModel.getAssociatedCourses(mTermID).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                adapter.setCourses(courses);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetailsActivity.this, EditTermActivity.class);
                intent.putExtra("TermName", mTermName.getText());
                intent.putExtra("StartDate", mTermStartDate.getText());
                intent.putExtra("EndDate", mTermEndDate.getText());
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
                mTermViewModel.delete(mTermID);
                finish();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }


    //Save edited term details to database
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra("TermName");
            String startDate = data.getStringExtra("StartDate");
            String endDate = data.getStringExtra("EndDate");

            Term term = new Term(mTermID, name, startDate, endDate);
            mTermViewModel.insert(term);

            mTermName.setText(name);
            mTermStartDate.setText(startDate);
            mTermEndDate.setText(endDate);
        }
    }
}
