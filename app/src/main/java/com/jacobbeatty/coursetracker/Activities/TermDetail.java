package com.jacobbeatty.coursetracker.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jacobbeatty.coursetracker.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class TermDetail extends AppCompatActivity {
    int termID;
    String termName;
    String termStart;
    String termEnd;
    TextView termNameDetail;
    TextView termStartDetail;
    TextView termEndDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        termID = getIntent().getIntExtra("termID",0);
        termName = getIntent().getStringExtra("termName");
        termStart = getIntent().getStringExtra("termStart");
        termEnd = getIntent().getStringExtra("termEnd");
        termNameDetail = findViewById(R.id.term_name_detail);
        termStartDetail = findViewById(R.id.term_start_detail);
        termEndDetail = findViewById(R.id.term_end_detail);
        termNameDetail.setText(termName);
        termStartDetail.setText(termStart);
        termEndDetail.setText(termEnd);




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;

            }
        });

        FloatingActionButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TermDetail.this, MainActivity.class));
            }
        });
    }
}