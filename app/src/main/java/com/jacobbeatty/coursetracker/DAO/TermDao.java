package com.jacobbeatty.coursetracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;

import java.util.List;

@Dao
public interface TermDao {
    @Query("SELECT * FROM term")
    List<Term> getAllTerms();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Term term);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Term term);

    @Delete
    void delete(Term term);


    @Insert
    void insertAll(Term... terms);

    @Query("DELETE FROM term")
    public void nukeTermTable();
}
