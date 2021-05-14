package com.jacobbeatty.coursetracker.Utilities;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jacobbeatty.coursetracker.DAO.TermDao;
import com.jacobbeatty.coursetracker.Entity.Term;

@Database(entities = {Term.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TermDao termDao();
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "term tracker").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
