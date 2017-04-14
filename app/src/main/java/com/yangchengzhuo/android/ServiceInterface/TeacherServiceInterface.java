package com.yangchengzhuo.android.ServiceInterface;

import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.Construction.Selection;
import com.yangchengzhuo.android.Construction.Student;

import java.util.ArrayList;

/**
 * Created by oliver_yang on 2017/4/11.
 */
public interface TeacherServiceInterface {
    /**
     * 教师登陆
     *
     * @param sno
     *            教师
     * @param spassword
     *            密码
     * @return
     */
    boolean login(String sno, String spassword) throws Exception;

    /**
     * 增加教师
     *
     * @param tno
     *            教师编号啊
     * @param tname
     *            姓名
     * @param tpassword
     *            密码
     * @param tsex
     *            性别
     * @param tdept
     *            院系
     * @param tnum
     *            选题学生数
     */
    boolean addTeacher(String tno, String tname, String tpassword, String tsex,
                       String tdept, String tnum) throws Exception;

    /**
     * 修改密码
     *
     * @param tno
     * @param tpassword
     * @return
     */
    boolean updateSpassword(String tno, String tpassword) throws Exception;

    /**
     * 查询学生
     */
    ArrayList<Student> select_Student(String pno) throws Exception;

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
     */
    void select_ProjectByTeacher(String tname) throws Exception;

    /**
     * 根据自己的编号查看自己的题目
     *
     * @param tno
     * @return
     */
    String select_ProjectBySelf(String tno) throws Exception;

    /**
     * 增加题目
     *
     * @param project
     *            题目名称
     * @param text
     *            题目内容
     * @return
     */
    boolean add_Project(String project, String tname, String text)
            throws Exception;

    /**
     * 查看选自己题的学生个数
     *
     * @param tno
     * @return
     */
    int selectSnum(String tno) throws Exception;

    /**
     * 根据教师编号找到教师姓名
     *
     * @param tno
     * @return
     */
    String select_teacherName(String tno) throws Exception;

    /**
     * 教师增加评论
     *
     * @param text
     *            评语
     * @param sno
     *            学生编号
     * @return
     */
    void addRemark(String sno, String text);

    /**
     * 教师给学生打分
     *
     * @param grade
     *            评分
     * @param sno
     *            学生编号
     * @return
     */
    void addGrade(String sno, String grade);

    /**
     * 教师查看完成进度
     *
     * @param sno
     *            学生编号
     * @return
     */
    String SelectSchedule(String sno);

    /**
     * 根据学号得到关系
     *
     * @param sno
     *            学号
     * @return
     */
    ArrayList<Selection> SelectSelectioin(String sno);

    /**
     * 根据学号找到学生姓名
     *
     * @param sno 学号
     * @return
     */
    String getSname(String sno);
}
