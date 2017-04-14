package com.yangchengzhuo.android.ServiceOperate;

import android.content.Context;

import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.Construction.Selection;
import com.yangchengzhuo.android.Construction.Student;
import com.yangchengzhuo.android.Interface.TeacherInterface;
import com.yangchengzhuo.android.ServiceInterface.TeacherServiceInterface;
import com.yangchengzhuo.android.graduate.Graduate;

import java.util.ArrayList;

/**
 * Created by oliver_yang .
 */
public class TeacherServiceOperate implements TeacherServiceInterface {
    private TeacherInterface mTeacherInterface;

    public TeacherServiceOperate(Context context) {
        this.mTeacherInterface = Graduate.getTeacherInterface(context);
    }

    @Override
    public boolean login(String sno, String spassword) {
        if (this.mTeacherInterface.login(sno, spassword)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addTeacher(String tno, String tname, String tpassword,
                              String tsex, String tdept, String tnum) {
        if (this.mTeacherInterface
                .addTeacher(tno, tname, tpassword, tsex, tdept, tnum)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSpassword(String tno, String tpassword) {
        if (this.mTeacherInterface.updateSpassword(tno, tpassword)) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Student> select_Student(String pno) {
        ArrayList<Student> arrayList = new ArrayList<Student>();
        arrayList = this.mTeacherInterface.select_Student(pno);
        return arrayList;
    }

    @Override
    public ArrayList<Project> select_Project() {
        ArrayList<Project> arrayList = new ArrayList<Project>();
        arrayList = this.mTeacherInterface.select_Project();
        return arrayList;
    }

    @Override
    public void select_ProjectByTeacher(String tname) {

    }

    @Override
    public String select_ProjectBySelf(String tno) {
        String pno = this.mTeacherInterface.select_ProjectBySelf(tno);
        return pno;
    }

    @Override
    public boolean add_Project(String project, String tname, String text) {
        if (this.add_Project(project, tname, text)) {
            return true;
        }
        return false;
    }

    @Override
    public int selectSnum(String tno) {
        int count = 0;
        count = this.mTeacherInterface.selectSnum(tno);
        return count;
    }

    @Override
    public String select_teacherName(String tno) throws Exception {
        // TODO Auto-generated method stub
        String name = null;
        name = this.mTeacherInterface.select_teacherName(tno);
        return name;
    }

    @Override
    public void addRemark(String sno, String text) {
        this.mTeacherInterface.addRemark(sno, text);
    }

    @Override
    public void addGrade(String sno, String grade) {
        this.mTeacherInterface.addGrade(sno, grade);
    }

    @Override
    public String SelectSchedule(String sno) {
        String schedule = null;
        schedule = this.mTeacherInterface.SelectSchedule(sno);
        return schedule;
    }

    @Override
    public ArrayList<Selection> SelectSelectioin(String sno) {
        ArrayList<Selection> arrayList = new ArrayList<Selection>();
        arrayList = this.mTeacherInterface.SelectSelectioin(sno);
        return arrayList;
    }

    @Override
    public String getSname(String sno) {
        String sname = this.mTeacherInterface.getSname(sno);
        return sname;
    }

}
