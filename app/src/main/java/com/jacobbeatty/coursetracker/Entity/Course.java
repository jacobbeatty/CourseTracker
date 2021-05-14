package com.jacobbeatty.coursetracker.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

//@Entity(tableName = "courses",
//        indices = {@Index(value = "course_id",unique = true),@Index(value = "term_id"),@Index(value = "instructor_id")},
//        foreignKeys = {@ForeignKey(entity = TermEntity.class,
//                parentColumns = "term_id",childColumns = "term_id",onDelete = ForeignKey.CASCADE)})
@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private int courseId;
    @ColumnInfo(name = "term_id")
    private int termId;
    @ColumnInfo(name = "instructor_id")
    private int instructorId;
    @ColumnInfo(name = "course_name")
    private String courseName;
    @ColumnInfo(name = "course_start")
    private String courseStart;
    @ColumnInfo(name = "course_end")
    private String courseEnd;
    @ColumnInfo(name = "course_status")
    private String courseStatus;
    @ColumnInfo(name = "course_note")
    private String courseNote;

    public Course(int courseId, int termId, int instructorId, String courseName, String courseStart, String courseEnd, String courseStatus, String courseNote) {
        this.courseId = courseId;
        this.termId = termId;
        this.instructorId = instructorId;
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.courseNote = courseNote;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }
    @Override
    public String toString() {
        return courseName;
    }
}
