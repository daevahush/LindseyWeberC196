package com.lindseyweberc196.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.TextView;

import com.lindseyweberc196.R;
import com.lindseyweberc196.ViewModel.TermViewModel;

public class TermDetailsActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private TermViewModel mTermViewModel;
    private TextView mTermName;
    private TextView mTermStartDate;
    private TextView mTermEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        //Get selected term details to populate activity with
        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        mTermName = findViewById(R.id.TermName);
        mTermStartDate = findViewById(R.id.StartDate);
        mTermEndDate = findViewById(R.id.EndDate);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Back button in toolbar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
