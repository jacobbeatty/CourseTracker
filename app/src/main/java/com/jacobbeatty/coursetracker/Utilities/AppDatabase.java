package com.jacobbeatty.coursetracker.Utilities;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jacobbeatty.coursetracker.DAO.CourseDao;
import com.jacobbeatty.coursetracker.DAO.TermDao;
import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;

import java.sql.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Term.class, Course.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TermDao termDao();
    public abstract CourseDao courseDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "term tracker").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }
//    private static final int NUMBER_OF_THREADS = 4;
//
//    static final ExecutorService databaseWriteExecutor =
//            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//    @Override
//    public void onOpen(@NonNull SupportSQLiteDatabase db) {
//        super.onOpen(db);
//    databaseWriteExecutor.execute(() ->
//
//    {
//    TermDao TermDao = instance.termDao();
//    CourseDao CourseDao = instance.courseDao();
//
//    Term term = null;
//
//    term = new Term(1, "Term 1", "2021-01-01", "2021-01-31");
//                TermDao.insert(term);
//    term = new Term(2, "Term 2", "2021-02-01", "2021-02-28");
//                TermDao.insert(term);
//    term = new Term(3, "Term 3", "2021-03-01", "2021-03-31");
//                TermDao.insert(term);
//
//    Course course = null;
//    course = new Course(1,1, "Mobile App",  "2020-12-01", "2020-12-30",
//                        "Carolyn", "4171234567", "carolyn@wgu.edu", "dropped", "Have to use Anroid Studio");
//                CourseDao.insert(course);
//    course = new Course(2,1, "Software 2",  "2020-12-01", "2020-12-30",
//                        "Wanda", "8881239874", "wanda@wgu.edu", "plan to take", "OA takes some timeto complete");
//                CourseDao.insert(course);
//    course = new Course(3,3, "Capstone",  "2020-12-01", "2020-12-30",
//                        "Candice", "3853335555", "candice@wgu.edu", "completed", "last class!");
//                CourseDao.insert(course);
//    });
//    }
}
