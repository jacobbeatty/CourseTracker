package com.jacobbeatty.coursetracker.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import com.jacobbeatty.coursetracker.Utilities.BroadcastReceiver;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseDetail extends AppCompatActivity {
    int courseID;
    int assessmentID;
    Date startDate;
    Date endDate;


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
    public static int alertID;
//    int courseID = getIntent().getIntExtra("courseID",-1);

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

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


        DateFormat formatter = new SimpleDateFormat("MM/dd/yy", Locale.US);



        try {
            String startDateText = courseStartDetail.getText().toString();
            String endDateText = courseEndDetail.getText().toString();
            startDate = formatter.parse(startDateText);
            endDate = formatter.parse(endDateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Button startCourseNotifyButton = findViewById(R.id.startCourseNotifyButton);
        startCourseNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent setStartReminderIntent = new Intent(CourseDetail.this, BroadcastReceiver.class);
                setStartReminderIntent.putExtra("key", currentCourse.getCourseName() + " has started.");
                PendingIntent startSender = PendingIntent.getBroadcast(CourseDetail.this,++alertID,setStartReminderIntent,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, startDate.getTime() ,startSender);
                Log.d("course start date", String.valueOf(startDate.getTime()));

            }
        });

        Button endCourseNotifyButton = findViewById(R.id.endCourseNotifyButton);
        endCourseNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent setEndReminderIntent = new Intent(CourseDetail.this, BroadcastReceiver.class);
                setEndReminderIntent.putExtra("key", currentCourse.getCourseName() + " is ending.");
                PendingIntent startSender = PendingIntent.getBroadcast(CourseDetail.this,++alertID,setEndReminderIntent,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, endDate.getTime() ,startSender);
                Log.d("course end date", String.valueOf(endDate.getTime()));
            }
        });

        Button deleteCourseButton = findViewById(R.id.deleteCourseButton);
        deleteCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.courseDao().delete(currentCourse);
                startActivity(new Intent(CourseDetail.this, MainActivity.class));
            }
        });
        Button shareNoteButton = findViewById(R.id.shareNoteButton);
        shareNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String number = "4434159415";
//                String sms = courseNoteDetail.toString();
//                try {
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(number, null, sms, null, null);
//                    Toast.makeText(getApplicationContext(), "SMS Sent!",
//                            Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(),
//                            "SMS failed to send, please try again later!",
//                            Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//
//                }

                Intent sendNote = new Intent();
                sendNote.setAction(Intent.ACTION_SEND);
                sendNote.putExtra(Intent.EXTRA_TEXT, "You have created a course note which is:" +currentCourse.getCourseNote());
                sendNote.setType("text/plain");
                Intent share = Intent.createChooser(sendNote, null);
                startActivity(share);
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