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
public interface CourseDao {
    @Query("SELECT * FROM course ORDER BY courseID ASC")
    List<Course> getAllCourses();

    @Query("SELECT * FROM course")
    public List<Course> getAllCourse();

    @Insert
    void insertAll(Course... courses);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Course course);

    @Delete
    void delete(Course course);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Course course);

    @Query("DELETE FROM course")
    public void nukeCourseTable();

    @Query("SELECT * from course where term_id= :term_id")
    List<Course> getCoursesByTermId(int term_id);
}
