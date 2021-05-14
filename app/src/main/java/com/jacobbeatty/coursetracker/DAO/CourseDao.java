package com.jacobbeatty.coursetracker.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM courses")
    List<Term> getAllCourses();

    @Query("SELECT * FROM courses WHERE term_id= :termId")
    List<Course>getCoursesbyTerm(int termId);

    @Insert
    void insertAll(Course... courses);
}
