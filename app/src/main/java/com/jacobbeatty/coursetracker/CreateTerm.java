package com.jacobbeatty.coursetracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Date;

public class CreateTerm extends AppCompatActivity {

    private static final String TAG = "CreateTerm";

    EditText termName;
    EditText termStart;
    EditText termEnd;
    Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_term);

        termName = findViewById(R.id.term_name);
        termStart = findViewById(R.id.term_start);
        termEnd = findViewById(R.id.term_end);
        button = findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                        .allowMainThreadQueries()
                        .build();

//                db.termDao().nukeTable();
                Term term = new Term(termName.getText().toString(),termStart.getText().toString(),termEnd.getText().toString());
                db.termDao().instertAll(term);


                startActivity(new Intent(CreateTerm.this, MainActivity.class));
            }
        });
    }
}
