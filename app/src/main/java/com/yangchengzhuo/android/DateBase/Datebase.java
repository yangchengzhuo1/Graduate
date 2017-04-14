package com.yangchengzhuo.android.DateBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.yangchengzhuo.android.Constant.*;

/**
 * Created by oliver_yang .
 */
public class DateBase extends SQLiteOpenHelper {
    public DateBase(Context context) {

        super(context, DBConstant.DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建学生表
        StringBuffer sb1 = new StringBuffer();
        sb1.append(" CREATE TABLE IF NOT EXISTS ")
                .append(StudentConstant.TABLE_NAME_STUDENT).append(" ( ")
                .append(StudentConstant.STUDENT_SNO)
                .append(" INTEGER PRIMARY KEY , ")
                .append(StudentConstant.STUDENT_SNAME)
                .append(" CHAR(10) NOT NULL , ")
                .append(StudentConstant.STUDENT_PASSWORD)
                .append(" CHAR(40) NOT NULL , ")
                .append(StudentConstant.STUDENT_SSEX)
                .append(" CHAR(8) NOT NULL ,")
                .append(StudentConstant.STUDENT_SDEPT)
                .append(" CHAR(30) NOT NULL , ")
                .append(StudentConstant.STUDENT_BIRTHDAY).append(" CHAR(20) ,")
                .append(StudentConstant.STUDENT_ISSELECT)
                .append(" CHAR(20) NOT NULL").append(" ) ;");
        System.out.println(sb1.toString());
        db.execSQL(sb1.toString());
        // 插入学生数据
        String sql1 = "INSERT INTO " + StudentConstant.TABLE_NAME_STUDENT
                + " ( " + StudentConstant.STUDENT_SNO + " , "
                + StudentConstant.STUDENT_SNAME + " , "
                + StudentConstant.STUDENT_PASSWORD + " , "
                + StudentConstant.STUDENT_SSEX + " , "
                + StudentConstant.STUDENT_SDEPT + " , "
                + StudentConstant.STUDENT_BIRTHDAY + " , "
                + StudentConstant.STUDENT_ISSELECT + " ) " + " VALUES "
                + " ('130410220','杨承卓','130410220','男','计算机科学与技术','','未选');";
        db.execSQL(sql1);

        // 创建教师表
        StringBuffer sb2 = new StringBuffer();
        sb2.append(" CREATE TABLE IF NOT EXISTS ")
                .append(TeacherConstant.TABLE_TEACHER_NAME).append(" ( ")
                .append(TeacherConstant.TEACHER_TNO)
                .append(" INTEGER PRIMARY KEY , ")
                .append(TeacherConstant.TEACHER_TNAME)
                .append(" CHAR(10) NOT NULL , ")
                .append(TeacherConstant.TEACHER_TPASSWORD)
                .append(" CHAR(40) NOT NULL , ")
                .append(TeacherConstant.TEACHER_TSEX)
                .append(" CHAR(8) NOT NULL , ")
                .append(TeacherConstant.TEACHER_TDEPT)
                .append(" CHAR(30) NOT NULL , ")
                .append(TeacherConstant.TEACHER_TNUM)
                .append(" CHAR(4) NOT NULL ").append(" ) ;");
        System.out.println(sb2.toString());
        db.execSQL(sb2.toString());
        // 插入数据
        String sql2 = "INSERT INTO " + TeacherConstant.TABLE_TEACHER_NAME
                + " ( " + TeacherConstant.TEACHER_TNO + " , "
                + TeacherConstant.TEACHER_TNAME + " , "
                + TeacherConstant.TEACHER_TPASSWORD + " , "
                + TeacherConstant.TEACHER_TSEX + " , "
                + TeacherConstant.TEACHER_TDEPT + " , "
                + TeacherConstant.TEACHER_TNUM + " ) " + " VALUES "
                + "('1','孟凡超','1','男','计算机科学与技术','0')";
        db.execSQL(sql2);
        String sql3 = "INSERT INTO " + TeacherConstant.TABLE_TEACHER_NAME
                + " ( " + TeacherConstant.TEACHER_TNO + " , "
                + TeacherConstant.TEACHER_TNAME + " , "
                + TeacherConstant.TEACHER_TPASSWORD + " , "
                + TeacherConstant.TEACHER_TSEX + " , "
                + TeacherConstant.TEACHER_TDEPT + " , "
                + TeacherConstant.TEACHER_TNUM + " ) " + " VALUES "
                + "('2','何燕萍','2','女','计算机科学与技术','0')";
        db.execSQL(sql3);

        // 创建题目表
        StringBuffer sb3 = new StringBuffer();
        sb3.append(" CREATE TABLE IF NOT EXISTS ")
                .append(ProjectConstant.TABLE_PROJECT_NAME).append(" ( ")
                .append(ProjectConstant.PROJECT_PNO)
                .append(" INTEGER PRIMARY KEY , ")
                .append(ProjectConstant.PROJECT_PNAME)
                .append(" CHAR(40) NOT NULL , ")
                .append(ProjectConstant.PROJECT_TNAME)
                .append(" CHAR(20) NOT NULL ,")
                .append(ProjectConstant.PROJECT_TEXT).append(" CHAR(400) ,")
                .append(" FOREIGN KEY (").append(ProjectConstant.PROJECT_TNAME)
                .append(" )").append(" REFERENCES ")
                .append(TeacherConstant.TABLE_TEACHER_NAME).append(" ( ")
                .append(TeacherConstant.TEACHER_TNAME).append(" ) ")
                .append(" ) ;");
        System.out.println(sb3.toString());
        db.execSQL(sb3.toString());

        // 创建关系表
        StringBuffer sb4 = new StringBuffer();
        sb4.append(" CREATE TABLE IF NOT EXISTS ")
                .append(SelectionConstant.TABLE_SELECTION_NAME).append(" ( ")
                .append(SelectionConstant.SELECTION_SNO)
                .append(" INTEGER NOT NULL , ")
                .append(SelectionConstant.SELECTION_PNO)
                .append(" INTEGER NOT NULL , ")
                .append(SelectionConstant.SELECTION_GRADE)
                .append(" CHAR(20)  ,")
                .append(SelectionConstant.SELECTION_SCHEDULE)
                .append(" CHAR(200)  ,")
                .append(SelectionConstant.SELECTION_REMARK)
                .append(" CHAR(200) ,").append(" PRIMARY KEY ").append(" ( ")
                .append(SelectionConstant.SELECTION_SNO).append(" , ")
                .append(SelectionConstant.SELECTION_PNO).append(" ) ,")
                .append(" FOREIGN KEY ").append(" ( ")
                .append(SelectionConstant.SELECTION_SNO).append(" ) ")
                .append(" REFERENCES ")
                .append(StudentConstant.TABLE_NAME_STUDENT).append(" ( ")
                .append(StudentConstant.STUDENT_SNO).append(" ) , ")
                .append(" FOREIGN KEY ").append("(")
                .append(SelectionConstant.SELECTION_PNO).append(" ) ")
                .append(" REFERENCES ")
                .append(ProjectConstant.TABLE_PROJECT_NAME).append(" ( ")
                .append(ProjectConstant.PROJECT_PNO).append(" )  ")
                .append(" );");

        System.out.println(sb4.toString());
        db.execSQL(sb4.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
