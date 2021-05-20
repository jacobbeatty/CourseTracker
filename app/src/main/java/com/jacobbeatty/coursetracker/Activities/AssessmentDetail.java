package com.jacobbeatty.coursetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jacobbeatty.coursetracker.Adapters.AssessmentAdapter;
import com.jacobbeatty.coursetracker.Adapters.CourseAdapter;
import com.jacobbeatty.coursetracker.Entity.Assessment;
import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;
import com.jacobbeatty.coursetracker.R;
import com.jacobbeatty.coursetracker.Utilities.AppDatabase;

import java.util.List;

public class AssessmentDetail extends AppCompatActivity {
    int assessmentID;
    String assessmentName;
    String assessmentStart;
    String assessmentEnd;
    String assessmentStatus;

    EditText assessmentNameDetail;
    EditText assessmentStartDetail;
    EditText assessmentEndDetail;

    TextView assessmentStatusDetail;
    Assessment currentAssessment;

    int currentCourse;
//    int assessmentID = getIntent().getIntExtra("assessmentID",-1);

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        recyclerView = findViewById(R.id.recycler_view_assessment_detail);


        assessmentID = getIntent().getIntExtra("assessmentID",-1);
        Log.d("assessmentID in detail", String.valueOf(assessmentID));


        assessmentName = getIntent().getStringExtra("assessmentName");
        assessmentStart = getIntent().getStringExtra("assessmentStart");
        assessmentEnd = getIntent().getStringExtra("assessmentEnd");
        assessmentStatus = getIntent().getStringExtra("assessmentStatus");



        assessmentNameDetail = findViewById(R.id.assessment_name_detail);
        assessmentStartDetail = findViewById(R.id.assessment_start_detail);
        assessmentEndDetail = findViewById(R.id.assessment_end_detail);
        assessmentStatusDetail = findViewById(R.id.assessment_status_detail);


        assessmentNameDetail.setText(assessmentName);
        assessmentStartDetail.setText(assessmentStart);
        assessmentEndDetail.setText(assessmentEnd);

        assessmentStatusDetail.setText(assessmentStatus);






        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
//        List<Assessment> assessment = db.assessmentDao().getAllAssessments();
//
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new AssessmentAdapter(assessment);
//        recyclerView.setAdapter(adapter);

//        FloatingActionButton fab = findViewById(R.id.fab_detail);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                startActivity(new Intent(AssessmentDetail.this, CreateAssessment.class));
//                Intent intent = new Intent(AssessmentDetail.this,CreateAssessment.class);
//                intent.putExtra("assessmentID", assessmentID);
//                startActivity(intent);
//
//
//            }
//        });


        List<Assessment> allAssessments= db.assessmentDao().getAllAssessments();

        for (Assessment assessment:allAssessments){
            if(assessment.getAssessmentID()== assessmentID) currentAssessment = assessment;
        }

        Button saveAssessmentButton = findViewById(R.id.saveAssessmentButton);
        saveAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentAssessment != null){

                    currentAssessment.setAssessmentName(assessmentNameDetail.getText().toString());
                    currentAssessment.setAssessmentStart(assessmentStartDetail.getText().toString());
                    currentAssessment.setAssessmentEnd(assessmentEndDetail.getText().toString());
                    currentAssessment.setAssessmentType(assessmentStatusDetail.getText().toString());
                }else Log.d("currentAssessment","is null");





                db.assessmentDao().update(currentAssessment);
                startActivity(new Intent(AssessmentDetail.this, MainActivity.class));




            }
        });

        FloatingActionButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AssessmentDetail.this, MainActivity.class));

            }
        });
    }

}