package com.jacobbeatty.coursetracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.jacobbeatty.coursetracker.DAO.CourseDao;
import com.jacobbeatty.coursetracker.Utilities.AppDatabase;
import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.R;

import java.util.List;

public class CreateCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "CreateCourse";

    int courseID;
    int termID;
    EditText courseName;
    EditText courseStart;
    EditText courseEnd;
    EditText courseNote;
    EditText instructorName;
    EditText instructorEmail;
    EditText instructorPhone;
    Spinner spinner;



    Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_course);

        courseID = getIntent().getIntExtra("courseID", -1);
        termID = getIntent().getIntExtra("termID", -1);
        Log.d("termID in createcourse", String.valueOf(termID));
        courseName = findViewById(R.id.course_name);
        courseStart = findViewById(R.id.course_start);
        courseEnd = findViewById(R.id.course_end);
        instructorName = findViewById(R.id.instructor_name);
        instructorEmail = findViewById(R.id.instructor_email);
        instructorPhone = findViewById(R.id.instructor_phone);
        courseNote = findViewById(R.id.course_note);

        button = findViewById(R.id.button_course);
        spinner = findViewById(R.id.status_spinner);
        //Spinner stuff
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();

                Course course = new Course( termID, instructorName.getText().toString(), instructorPhone.getText().toString(), instructorEmail.getText().toString(),courseName.getText().toString(), courseStart.getText().toString(), courseEnd.getText().toString(),  spinner.getSelectedItem().toString(), courseNote.getText().toString());
                db.courseDao().insertAll(course);


                startActivity(new Intent(CreateCourse.this, TermDetail.class));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, selected,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}