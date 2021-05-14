package com.jacobbeatty.coursetracker.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jacobbeatty.coursetracker.Entity.Term;

import java.util.List;

@Dao
public interface TermDao {
    @Query("SELECT * FROM term")
    List<Term> getAllTerms();


    @Insert
    void insertAll(Term... terms);

    @Query("DELETE FROM term")
    public void nukeTable();
}
