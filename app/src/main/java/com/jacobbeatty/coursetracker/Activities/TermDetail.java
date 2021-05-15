package com.jacobbeatty.coursetracker.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jacobbeatty.coursetracker.Adapters.CourseAdapter;
import com.jacobbeatty.coursetracker.Adapters.TermAdapter;
import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;
import com.jacobbeatty.coursetracker.R;
import com.jacobbeatty.coursetracker.Utilities.AppDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;
import android.widget.TextView;

import java.util.List;

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

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        recyclerView = findViewById(R.id.recycler_view_course);


        
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

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        List<Course> course = db.courseDao().getAllCourses();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CourseAdapter(course);
        recyclerView.setAdapter(adapter);




        FloatingActionButton fab = findViewById(R.id.fab_detail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TermDetail.this, CreateCourse.class));

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