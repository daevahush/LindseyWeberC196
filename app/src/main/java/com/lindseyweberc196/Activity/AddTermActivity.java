package com.lindseyweberc196.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lindseyweberc196.Entity.Term;
import com.lindseyweberc196.R;
import com.lindseyweberc196.ViewModel.TermViewModel;

public class AddTermActivity extends AppCompatActivity {
    private EditText mEditTermName;
    private EditText mEditStartDate;
    private EditText mEditEndDate;
    private TermViewModel mTermViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTermViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        mEditTermName = findViewById(R.id.AssessmentName);
        mEditStartDate = findViewById(R.id.StartDate);
        mEditEndDate = findViewById(R.id.EndDate);

        //Back button in toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button button = findViewById(R.id.SaveButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                String name = mEditTermName.getText().toString();
                String startDate = mEditStartDate.getText().toString();
                String endDate = mEditEndDate.getText().toString();

                replyIntent.putExtra("TermName", name);
                replyIntent.putExtra("StartDate", startDate);
                replyIntent.putExtra("EndDate", endDate);

                if(getIntent().getStringExtra("TermName")!=null) {
                    int id = getIntent().getIntExtra("TermID",0);
                    Term term = new Term(id, name, startDate, endDate);
                    mTermViewModel.insert(term);
                }
                setResult(RESULT_OK, replyIntent);
                finish();

                if(TextUtils.isEmpty(mEditTermName.getText())) {
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

    public void onSave(View view) {
        //Return back to Terms list
        Intent intent = new Intent(AddTermActivity.this, TermsActivity.class);
        startActivity(intent);
    }

}
