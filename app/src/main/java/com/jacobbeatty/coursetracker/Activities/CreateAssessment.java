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

import com.jacobbeatty.coursetracker.Entity.Assessment;
import com.jacobbeatty.coursetracker.R;
import com.jacobbeatty.coursetracker.Utilities.AppDatabase;

public class CreateAssessment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "CreateAssessment";

    int assessmentID;
    int courseID;
    EditText assessmentName;
    EditText assessmentStart;
    EditText assessmentEnd;
    Spinner spinner;



    Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_assessment);

        assessmentID = getIntent().getIntExtra("assessmentID", -1);
        courseID = getIntent().getIntExtra("courseID", -1);
        Log.d("courseID in createassessment", String.valueOf(courseID));
        assessmentName = findViewById(R.id.assessment_name);
        assessmentStart = findViewById(R.id.assessment_start);
//        assessmentEnd = findViewById(R.id.assessment_end);

        button = findViewById(R.id.button_assessment);
        spinner = findViewById(R.id.type_spinner);
        //Spinner stuff
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type, android.R.layout.simple_spinner_item);
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

                Assessment assessment = new Assessment( courseID ,assessmentName.getText().toString(), assessmentStart.getText().toString(), spinner.getSelectedItem().toString());
                db.assessmentDao().insertAll(assessment);


                startActivity(new Intent(CreateAssessment.this, MainActivity.class));
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