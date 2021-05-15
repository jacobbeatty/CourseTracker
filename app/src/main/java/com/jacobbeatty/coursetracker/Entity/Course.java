package com.jacobbeatty.coursetracker.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity(tableName = "courses",
//        indices = {@Index(value = "course_id",unique = true),@Index(value = "term_id"),@Index(value = "instructor_id")},
//        foreignKeys = {@ForeignKey(entity = TermEntity.class,
//                parentColumns = "term_id",childColumns = "term_id",onDelete = ForeignKey.CASCADE)})
@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    @ColumnInfo(name = "term_id")
    private int termID;
    @ColumnInfo(name = "instructor_name")
    private String instructorName;
    @ColumnInfo(name = "instructor_email")
    private String instructorEmail;
    @ColumnInfo(name = "instructor_phone")
    private String instructorPhone;
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

    public Course(String instructorName,String instructorEmail, String instructorPhone, String courseName, String courseStart, String courseEnd, String courseStatus, String courseNote) {
//        this.courseID = courseID;
//        this.termID = termID;
        this.instructorName = instructorName;        this.instructorEmail = instructorEmail;        this.instructorPhone = instructorPhone;
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseStatus = courseStatus;
        this.courseNote = courseNote;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
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
