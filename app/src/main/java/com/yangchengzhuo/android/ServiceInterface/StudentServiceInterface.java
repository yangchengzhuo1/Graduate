package com.yangchengzhuo.android.ServiceInterface;

import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.Construction.Teacher;

import java.util.ArrayList;

/**
 * Created by oliver_yang .
 */
public interface StudentServiceInterface {
    /**
     * 学生登陆
     *
     * @param sno
     *            学号
     * @param spassword
     *            密码
     * @return
     */
    boolean login(String sno, String spassword);

    /**
     * 增加学生
     *
     * @param sno
     *            学号
     * @param sname
     *            姓名
     * @param spassword
     *            密码
     * @param ssex
     *            性别
     * @param sdept
     *            院系
     * @param birth
     *            生日
     */
    boolean addStudent(String sno, String sname, String spassword, String ssex,
                       String sdept, String birth, String iselect);

    /**
     * 修改密码
     *
     * @param sno
     * @param spassword
     * @return
     */
    boolean updateSpassword(String sno, String spassword);

    /**
     * 查询老师
     */
    ArrayList<Teacher> select_Teacher();

    /**
     * 查询题目
     *
     * @return
     */
    ArrayList<Project> select_Project();

    /**
     * 查询老师有的题目
     *
     * @param tname
     *            教师姓名
     * @return
     */
    Project select_ProjectByTeacher(String tname);

    /**
     * 根据自己的学号查看自己已选的题目
     *
     * @param sno
     * @return
     */
    Project select_ProjectBySelf(String sno);

    /**
     * 学生选择题目
     *
     * @param sno
     *            学号
     * @param pno
     *            题目编号
     */
    boolean insert_Project(String sno, String pno);

    /**
     * 学生是否已经选题
     *
     * @param sno
     *            学号
     * @return
     */
    boolean isSelect(String sno);

    /**
     * 查看教师评语
     *
     * @param sno
     *            学号
     * @return
     */
    String selectRemark(String sno);

    /**
     * 增加完成进度
     *
     * @param sno
     *            学号
     * @return
     */
    void addSchedule(String sno, String text);

    /**
     * 修改选题状态
     *
     * @param sno
     * @return
     */
    void upIsSelect(String sno);

    /**
     * 学生查看成绩
     *
     * @param sno
     *            学号
     * @return
     */
    String selectGrade(String sno);

    /**
     * 根据课题名称得到课题的详细信息
     *
     * @param pname
     *            课题名称
     * @return
     */
    Project selectByPname(String pname);

    /**
     * 根据学号找到姓名
     *
     * @param sno
     *            学号
     * @return
     */
    String getSname(String sno);

    /**
     * 根据学号找到对应的进度
     *
     * @param sno
     *            学号
     * @return
     */
    String selectSchedule(String sno);

    /**
     * 根据学号找到题目名称
     *
     * @param sno
     *            学号
     * @return
     */
    String selectPname(String sno);
}
