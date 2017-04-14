package com.yangchengzhuo.android.ServiceOperate;

import android.content.Context;

import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.Construction.Teacher;
import com.yangchengzhuo.android.Interface.StudentInterface;
import com.yangchengzhuo.android.ServiceInterface.StudentServiceInterface;
import com.yangchengzhuo.android.graduate.Graduate;

import java.util.ArrayList;

/**
 * Created by oliver_yang .
 */
public class StudentServiceOperate implements StudentServiceInterface {
    private StudentInterface mStudentInterface;

    public StudentServiceOperate(Context context) {
        this.mStudentInterface = Graduate.getStudentInterface(context);
    }

    @Override
    public boolean login(String sno, String spassword) {
        if (this.mStudentInterface.login(sno, spassword)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addStudent(String sno, String sname, String spassword,
                              String ssex, String sdept, String birth, String isselect) {
        if (this.mStudentInterface.addStudent(sno, sname, spassword, ssex, sdept,
                birth, isselect)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSpassword(String sno, String spassword) {
        if (this.mStudentInterface.updateSpassword(sno, spassword)) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Teacher> select_Teacher() {
        ArrayList<Teacher> arrayList = new ArrayList<Teacher>();
        arrayList = this.mStudentInterface.select_Teacher();
        return arrayList;
    }

    @Override
    public ArrayList<Project> select_Project() {
        ArrayList<Project> arrayList = new ArrayList<Project>();
        arrayList = this.mStudentInterface.select_Project();
        return arrayList;
    }

    @Override
    public Project select_ProjectByTeacher(String tname) {
        Project project = new Project();
        project = this.mStudentInterface.select_ProjectByTeacher(tname);
        return project;
    }

    @Override
    public Project select_ProjectBySelf(String sno) {
        Project project = new Project();
        project = this.mStudentInterface.select_ProjectBySelf(sno);
        return project;
    }

    @Override
    public boolean insert_Project(String sno, String pno) {
        if (this.mStudentInterface.insert_Project(sno, pno)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSelect(String sno) {
        if (this.mStudentInterface.isSelect(sno)) {
            return true;
        }
        return false;
    }

    @Override
    public String selectRemark(String sno) {
        String remark = this.mStudentInterface.selectRemark(sno);
        return remark;
    }

    @Override
    public void addSchedule(String sno, String text) {
        this.mStudentInterface.addSchedule(sno, text);
    }

    @Override
    public void upIsSelect(String sno) {
        this.mStudentInterface.upIsSelect(sno);
    }

    @Override
    public String selectGrade(String sno) {
        String grade = this.mStudentInterface.selectGrade(sno);
        return grade;
    }

    @Override
    public Project selectByPname(String pname) {
        Project project = this.mStudentInterface.selectByPname(pname);
        return project;
    }

    @Override
    public String getSname(String sno) {
        String sname = this.mStudentInterface.getSname(sno);
        return sname;
    }

    @Override
    public String selectSchedule(String sno) {
        String schedule = this.mStudentInterface.selectSchedule(sno);
        return schedule;
    }

    @Override
    public String selectPname(String sno) {
        String pname = this.mStudentInterface.selectPname(sno);
        return pname;
    }
}
