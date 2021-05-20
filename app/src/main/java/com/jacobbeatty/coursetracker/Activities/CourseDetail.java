package com.jacobbeatty.coursetracker.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jacobbeatty.coursetracker.Adapters.AssessmentAdapter;
import com.jacobbeatty.coursetracker.Adapters.CourseAdapter;
import com.jacobbeatty.coursetracker.Adapters.CourseAdapter;
import com.jacobbeatty.coursetracker.DAO.CourseDao;
import com.jacobbeatty.coursetracker.DAO.CourseDao;
import com.jacobbeatty.coursetracker.Entity.Assessment;
import com.jacobbeatty.coursetracker.Entity.Course;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CourseDetail extends AppCompatActivity {
    int courseID;
    int assessmentID;


    String courseName;
    String courseStart;
    String courseEnd;
    String courseInstructorName;
    String courseInstructorPhone;
    String courseInstructorEmail;
    String courseStatus;
    String courseNote;

    EditText courseNameDetail;
    EditText courseStartDetail;
    EditText courseEndDetail;
    EditText courseInstructorNameDetail;
    EditText courseInstructorPhoneDetail;
    EditText courseInstructorEmailDetail;
    EditText courseStatusDetail;
    EditText courseNoteDetail;
    Course currentCourse;
//    int courseID = getIntent().getIntExtra("courseID",-1);

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        recyclerView = findViewById(R.id.recycler_view_course_detail);


        courseID = getIntent().getIntExtra("courseId",-1);
        Log.d("courseID in detail for assement purposes", String.valueOf(courseID));

//        Intent intent = new Intent(CourseDetail.this,CreateCourse.class);
//        intent.putExtra("courseID", courseID);


        courseName = getIntent().getStringExtra("courseName");
        courseStart = getIntent().getStringExtra("courseStart");
        courseEnd = getIntent().getStringExtra("courseEnd");
        courseInstructorName = getIntent().getStringExtra("courseInstructorName");
        courseInstructorPhone = getIntent().getStringExtra("courseInstructorPhone");
        courseInstructorEmail = getIntent().getStringExtra("courseInstructorEmail");
        courseStatus = getIntent().getStringExtra("courseStatus");
        courseNote = getIntent().getStringExtra("courseNote");


        courseNameDetail = findViewById(R.id.course_name_detail);
        courseStartDetail = findViewById(R.id.course_start_detail);
        courseEndDetail = findViewById(R.id.course_end_detail);
        courseInstructorNameDetail = findViewById(R.id.course_instructor_name_detail);
        courseInstructorPhoneDetail = findViewById(R.id.course_instructor_phone_detail);
        courseInstructorEmailDetail = findViewById(R.id.course_instructor_email_detail);
        courseStatusDetail = findViewById(R.id.course_status_detail);
        courseNoteDetail = findViewById(R.id.course_note_detail);


        courseNameDetail.setText(courseName);
        courseStartDetail.setText(courseStart);
        courseEndDetail.setText(courseEnd);
        courseInstructorNameDetail.setText(courseInstructorName);
        courseInstructorPhoneDetail.setText(courseInstructorPhone);
        courseInstructorEmailDetail.setText(courseInstructorEmail);
        courseStatusDetail.setText(courseStatus);
        courseNoteDetail.setText(courseNote);






        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        List<Assessment> assessment = db.assessmentDao().getAllAssessments();
        List<Assessment> assessmentCourse = db.assessmentDao().getAssessmentsByCourseId(courseID);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AssessmentAdapter(assessment);
        recyclerView.setAdapter(adapter);
        ((AssessmentAdapter) adapter).setAssessment(assessmentCourse);

        List<Course> allCourses = db.courseDao().getAllCourses();

        for (Course course:allCourses){
            if(course.getCourseID()==courseID)currentCourse = course;
        }

        Button saveCourseButton = findViewById(R.id.saveCourseButton);
        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String termName = termNameDetail.getText().toString();
//                String start = termStartDetail.getText().toString();
//                String end = termEndDetail.getText().toString();

                currentCourse.setCourseName(courseNameDetail.getText().toString());
                currentCourse.setCourseStart(courseStartDetail.getText().toString());
                currentCourse.setCourseEnd(courseEndDetail.getText().toString());
                currentCourse.setInstructorName(courseInstructorNameDetail.getText().toString());
                currentCourse.setInstructorPhone(courseInstructorPhoneDetail.getText().toString());
                currentCourse.setInstructorEmail(courseInstructorEmailDetail.getText().toString());
                currentCourse.setCourseStatus(courseStatusDetail.getText().toString());
                currentCourse.setCourseNote(courseNoteDetail.getText().toString());



                db.courseDao().update(currentCourse);
                startActivity(new Intent(CourseDetail.this, MainActivity.class));




            }
        });


        FloatingActionButton fab = findViewById(R.id.fab_detail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetail.this,CreateAssessment.class);
                intent.putExtra("courseID", courseID);
                intent.putExtra("assessmentID", assessmentID);
                startActivity(intent);


            }
        });

        FloatingActionButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseDetail.this, MainActivity.class));

            }
        });
    }

}