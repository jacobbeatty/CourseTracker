package com.jacobbeatty.coursetracker.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jacobbeatty.coursetracker.Adapters.TermAdapter;
import com.jacobbeatty.coursetracker.DAO.TermDao;
import com.jacobbeatty.coursetracker.Utilities.AppDatabase;
import com.jacobbeatty.coursetracker.Entity.Term;
import com.jacobbeatty.coursetracker.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
    ArrayList<Term> terms;
    Context context;
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






        recyclerView = findViewById(R.id.recycler_view);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
//        db.courseDao().nukeCourseTable();
//        db.termDao().nukeTermTable();
//        db.assessmentDao().nukeAssessmentTable();
        List<Term> terms = db.termDao().getAllTerms();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TermAdapter( terms);
        recyclerView.setAdapter(adapter);



        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick: clicked");
                startActivity(new Intent(MainActivity.this, CreateTerm.class));
//                db.termDao().nukeTermTable();
//                db.courseDao().nukeCourseTable();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_db) {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
            db.courseDao().nukeCourseTable();
            db.termDao().nukeTermTable();
            db.assessmentDao().nukeAssessmentTable();
            Log.d("onselect", "if ran");
            startActivity(new Intent(MainActivity.this, MainActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

}