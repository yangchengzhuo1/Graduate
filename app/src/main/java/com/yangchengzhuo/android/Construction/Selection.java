package com.yangchengzhuo.android.Construction;

/**
 * Created by oliver_yang .
 */
public class Selection {
    private String sno;
    private String grade;
    private String remark;
    private String schedule;

    public Selection() {

    }

    public Selection(String sno) {
        this.sno = sno;

    }

    public Selection(String sno, String grade) {
        this.sno = sno;
        this.grade = grade;
    }

    public Selection(String sno, String grade, String remark) {
        this.sno = sno;
        this.grade = grade;
        this.remark = remark;
    }

    public Selection(String sno, String grade, String remark, String schedule) {
        this.sno = sno;
        this.grade = grade;
        this.remark = remark;
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Selection [sno=" + sno + ", grade=" + grade + ", remark="
                + remark + ", schedule=" + schedule + "]";
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

}
