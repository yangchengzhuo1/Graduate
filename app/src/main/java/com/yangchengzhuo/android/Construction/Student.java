package com.yangchengzhuo.android.Construction;

/**
 * Created by oliver_yang .
 */
public class Student {
    private String sno;
    private String sname;
    private String spassword;
    private String ssex;
    private String sdept;
    private String birth;

    public Student() {
    }

    public Student(String sno, String spassword) {
        this.sno = sno;
        this.spassword = spassword;

    }

    public Student(String sno, String sname, String spassword, String ssex,
                   String sdept, String birth) {
        this.sno = sno;
        this.sname = sname;
        this.spassword = spassword;
        this.ssex = ssex;
        this.sdept = sdept;
        this.birth = birth;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Student [sno=" + sno + ", sname=" + sname + ", spassword="
                + spassword + ", ssex=" + ssex + ", sdept=" + sdept
                + ", birth=" + birth + "]";
    }
}
