package com.jacobbeatty.coursetracker.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jacobbeatty.coursetracker.Adapters.CourseAdapter;
import com.jacobbeatty.coursetracker.Adapters.TermAdapter;
import com.jacobbeatty.coursetracker.DAO.CourseDao;
import com.jacobbeatty.coursetracker.DAO.TermDao;
import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;
import com.jacobbeatty.coursetracker.R;
import com.jacobbeatty.coursetracker.Utilities.AppDatabase;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TermDetail extends AppCompatActivity {
    int termID;
    String termName;
    String termStart;
    String termEnd;
    EditText termNameDetail;
    EditText termStartDetail;
    EditText termEndDetail;
    Term currentTerm;
    int numberOfCourses;

//    int termID = getIntent().getIntExtra("termID",-1);

    public void setTermID(int termID) {
        this.termID = termID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_detail);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        recyclerView = findViewById(R.id.recycler_view_course);


        termID = getIntent().getIntExtra("termID",-1);
        Log.d("termID in detail", String.valueOf(termID));

//        Intent intent = new Intent(TermDetail.this,CreateCourse.class);
//        intent.putExtra("termID", termID);


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
        List<Course> courseTerm = db.courseDao().getCoursesByTermId(termID);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CourseAdapter(course);
        recyclerView.setAdapter(adapter);
        ((CourseAdapter) adapter).setCourse(courseTerm);

        List<Term> allTerms= db.termDao().getAllTerms();

        for (Term term:allTerms){
            if(term.getId()==termID)currentTerm=term;
        }

        Button saveTermButton = findViewById(R.id.saveTermButton);
        saveTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTerm.setTermName(termNameDetail.getText().toString());
                currentTerm.setTermStart(termStartDetail.getText().toString());
                currentTerm.setTermEnd(termEndDetail.getText().toString());
                db.termDao().update(currentTerm);
                startActivity(new Intent(TermDetail.this, MainActivity.class));
            }
        });
        Button deleteTermButton = findViewById(R.id.deleteTermButton);
        deleteTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfCourses = courseTerm.size();
                if (numberOfCourses > 0 ){
                    Toast.makeText(getApplicationContext(),"Term has courses, can't delete.",Toast.LENGTH_SHORT).show();
                    Log.d("number of courses IF", String.valueOf(numberOfCourses));
                }else{
                    Log.d("number of courses ELSE", String.valueOf(numberOfCourses));
                    db.termDao().delete(currentTerm);
                }
                startActivity(new Intent(TermDetail.this, MainActivity.class));
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab_detail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermDetail.this,CreateCourse.class);
                intent.putExtra("termID", termID);
                startActivity(intent);


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