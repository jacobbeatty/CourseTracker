package com.jacobbeatty.coursetracker.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Term {

    public Term(String termName, String termStart, String termEnd) {
        this.termName = termName;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }
    @PrimaryKey(autoGenerate = true)
    public int termID;
    @ColumnInfo(name="term_start")
    private String termStart;
    @ColumnInfo(name="term_end")
//    @TypeConverters(Converter.class)
    private String termEnd;
    @ColumnInfo(name="term_name")
    private String termName;

    public int getId() {
        return termID;
    }

    public void setId(int id) {
        this.termID = id;
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
