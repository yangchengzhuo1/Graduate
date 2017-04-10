package com.yangchengzhuo.android.Construction;

/**
 * Created by oliver_yang on .
 */
public class Project {
    private String pno;
    private String tname;
    private String pname;
    private String text;

    public Project() {

    }

    public Project(String pame, String text) {
        this.pname = pame;
        this.text = text;
    }

    public Project(String pname, String tname, String text) {
        this.pname = pname;
        this.tname = tname;
        this.text = text;
    }

    public Project(String pno, String pname, String tname, String text) {
        this.pno = pno;
        this.pname = pname;
        this.tname = tname;
        this.text = text;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Project [pno=" + pno + ", tname=" + tname + ", pname=" + pname
                + ", text=" + text + "]";
    }
}
