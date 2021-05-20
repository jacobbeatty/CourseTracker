package com.jacobbeatty.coursetracker.Utilities;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jacobbeatty.coursetracker.DAO.AssessmentDao;
import com.jacobbeatty.coursetracker.DAO.CourseDao;
import com.jacobbeatty.coursetracker.DAO.TermDao;
import com.jacobbeatty.coursetracker.Entity.Assessment;
import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;

import java.sql.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TermDao termDao();
    public abstract CourseDao courseDao();
    public abstract AssessmentDao assessmentDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "term tracker").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
