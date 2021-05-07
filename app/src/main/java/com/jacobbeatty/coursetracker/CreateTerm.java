package com.jacobbeatty.coursetracker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class CreateTerm extends AppCompatActivity {

    private static final String TAG = "CreateTerm";

    EditText termName;
    DatePicker termStart;
    DatePicker termEnd;
    Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);

        termName = findViewById(R.id.term_name);
        termStart = findViewById(R.id.term_start);
        termEnd = findViewById(R.id.term_end);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 5/7/2021 Save to database

                int startMonth = termStart.getMonth();
                int startYear = termStart.getYear();

                Log.d(TAG, "onClick: termName: " + termName.getText().toString());
                Log.d(TAG, "onClick: startMonth: " + startMonth);
                Log.d(TAG, "onClick: startYear: " + startYear);

            }
        });
    }
}
