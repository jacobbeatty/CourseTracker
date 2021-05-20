package com.jacobbeatty.coursetracker.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    public int assessmentID;
    @ColumnInfo(name = "course_id")
    public int courseID;
    @ColumnInfo(name = "assessment_name")
    private String assessmentName;
    @ColumnInfo(name = "assessment_start")
    private String assessmentStart;
    @ColumnInfo(name = "assessment_end")
    private String assessmentEnd;
    @ColumnInfo(name = "assessment_type")
    private String assessmentType;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int courseID) {
        this.courseID = courseID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentStart() {
        return assessmentStart;
    }

    public void setAssessmentStart(String assessmentStart) {
        this.assessmentStart = assessmentStart;
    }

    public String getAssessmentEnd() {
        return assessmentEnd;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        this.assessmentEnd = assessmentEnd;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Assessment(int courseID,String assessmentName, String assessmentStart, String assessmentEnd, String assessmentType) {
        this.courseID = courseID;
        this.assessmentName = assessmentName;
        this.assessmentStart = assessmentStart;
        this.assessmentEnd = assessmentEnd;
        this.assessmentType = assessmentType;
    }
}
