package com.yangchengzhuo.android.Construction;

/**
 * Created by oliver_yang .
 */
public class Teacher {
    /**
     * tno  教师编号
     * tname 教师姓名
     * tpassword 教师密码
     * tsex 教师性别
     * tdept 教师院系
     */
    private String tno;
    private String tname;
    private String tpassword;
    private String tsex;
    private String tdept;

    public Teacher() {

    }

    public Teacher(String tno, String tpassword) {
        this.tno = tno;
        this.tpassword = tpassword;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public String getTdept() {
        return tdept;
    }

    public void setTdept(String tdept) {
        this.tdept = tdept;
    }

    @Override
    public String toString() {
        return "Teacher [tno=" + tno + ", tname=" + tname + ", tpassword="
                + tpassword + ", tsex=" + tsex + ", tdept=" + tdept + "]";
    }

}
