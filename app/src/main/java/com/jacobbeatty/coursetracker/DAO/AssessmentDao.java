package com.jacobbeatty.coursetracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jacobbeatty.coursetracker.Entity.Assessment;
import com.jacobbeatty.coursetracker.Entity.Course;

import java.util.List;

@Dao
public interface AssessmentDao {
    @Query("SELECT * FROM assessment ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();


    @Insert
    void insertAll(Assessment... assessments);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Assessment assessment);


    @Delete
    void delete(Assessment assessment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Assessment assessment);

    @Query("DELETE FROM assessment")
    public void nukeAssessmentTable();

    @Query("SELECT * from assessment where course_id= :course_id")
    List<Assessment> getAssessmentsByCourseId(int course_id);
}
