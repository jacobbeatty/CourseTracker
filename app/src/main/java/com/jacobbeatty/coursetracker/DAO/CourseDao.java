package com.jacobbeatty.coursetracker.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jacobbeatty.coursetracker.Entity.Course;
import com.jacobbeatty.coursetracker.Entity.Term;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM course")
    List<Course> getAllCourses();

    @Query("SELECT * FROM course")
    List<Course> getAllCourse();

    @Insert
    void insertAll(Course... courses);
}
