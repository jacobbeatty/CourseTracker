package com.jacobbeatty.coursetracker;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TermDao {
    @Query("SELECT * FROM term")
    List<Term> getAllTerms();

    @Insert
    void instertAll(Term... terms);

    @Query("DELETE FROM term")
    public void nukeTable();
}
