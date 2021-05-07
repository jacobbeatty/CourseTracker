package com.jacobbeatty.coursetracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;
    ArrayList<Term> terms;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        terms = new ArrayList<>();

        for (int i = 0; i < 10 ; i++) {
            Term term = new Term("Term " + i,"04-01-2021","10-01-2021");
            terms.add(term);
            Log.d(TAG, "i is incremented to " + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TermAdapter(terms);
        recyclerView.setAdapter(adapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick: clicked");
                startActivity(new Intent(MainActivity.this, CreateTerm.class));
            }
        });
    }

}