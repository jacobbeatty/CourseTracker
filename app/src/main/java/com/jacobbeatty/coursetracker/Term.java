package com.jacobbeatty.coursetracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Term {


    public Term(String termName, String termStart, String termEnd) {
        this.termName = termName;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }
    private int id;
    private String termStart;
    private String termEnd;
    private String termName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }



}
