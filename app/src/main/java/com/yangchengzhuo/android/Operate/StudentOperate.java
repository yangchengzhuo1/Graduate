package com.yangchengzhuo.android.Operate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yangchengzhuo.android.Constant.ProjectConstant;
import com.yangchengzhuo.android.Constant.SelectionConstant;
import com.yangchengzhuo.android.Constant.StudentConstant;
import com.yangchengzhuo.android.Constant.TeacherConstant;
import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.Construction.Teacher;
import com.yangchengzhuo.android.DateBase.DateBase;
import com.yangchengzhuo.android.Interface.StudentInterface;

import java.util.ArrayList;

/**
 * Created by oliver_yang .
 */
public class StudentOperate implements StudentInterface {
    private DateBase mDateBase;

    public StudentOperate(Context context) {
        this.mDateBase = new DateBase(context);
    }

    /**
     * 登陆
     */
    @Override
    public boolean login(String sno, String spassword) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + StudentConstant.STUDENT_SNO + " , "
                    + StudentConstant.STUDENT_PASSWORD + " FROM "
                    + StudentConstant.TABLE_NAME_STUDENT + " WHERE "
                    + StudentConstant.STUDENT_SNO + " =? AND "
                    + StudentConstant.STUDENT_PASSWORD + " = ? ;";
            Cursor cursor = db.rawQuery(sql, new String[] { sno, spassword });
            if (!cursor.moveToNext()) {
                db.close();
                return false;
            } else {
                return true;

            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 增加学生
     */
    @Override
    public boolean addStudent(String sno, String sname, String spassword,
                              String ssex, String sdept, String birth, String isselect) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "INSERT INTO " + StudentConstant.TABLE_NAME_STUDENT
                    + " ( " + StudentConstant.STUDENT_SNO + " , "
                    + StudentConstant.STUDENT_SNAME + " , "
                    + StudentConstant.STUDENT_PASSWORD + " , "
                    + StudentConstant.STUDENT_SSEX + " , "
                    + StudentConstant.STUDENT_SDEPT + " , "
                    + StudentConstant.STUDENT_BIRTHDAY + " , "
                    + StudentConstant.STUDENT_ISSELECT
                    + " ) VALUES(?,?,?,?,?,?,?)";
            db.execSQL(sql, new String[] { sno, sname, spassword, ssex, sdept,
                    birth, isselect });
            return true;
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    /**
     * 修改密码
     */
    @Override
    public boolean updateSpassword(String sno, String spassword) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "UPDATE " + StudentConstant.TABLE_NAME_STUDENT
                    + " SET " + StudentConstant.STUDENT_PASSWORD + " =? "
                    + " WHERE " + StudentConstant.STUDENT_SNO + " =? ;";
            db.execSQL(sql, new String[] { spassword, sno });
            return true;
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    /**
     * 查询教师
     */
    @Override
    public ArrayList<Teacher> select_Teacher() {
        SQLiteDatabase db = null;
        ArrayList<Teacher> tArrayList = new ArrayList<Teacher>();
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = " SELECT TNAME FROM "
                    + TeacherConstant.TABLE_TEACHER_NAME + " ;";

            Cursor cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                Teacher teacher = new Teacher();
                teacher.setTname(cursor.getString(cursor
                        .getColumnIndex(TeacherConstant.TEACHER_TNAME)));
                tArrayList.add(teacher);
            }
            cursor.close();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return tArrayList;
    }

    @Override
    public ArrayList<Project> select_Project() {
        SQLiteDatabase db = null;
        ArrayList<Project> arrayList = new ArrayList<Project>();
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + ProjectConstant.PROJECT_PNAME + " , "
                    + ProjectConstant.PROJECT_TNAME + " , "
                    + ProjectConstant.PROJECT_TEXT + " FROM "
                    + ProjectConstant.TABLE_PROJECT_NAME;
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                Project project = new Project();
                project.setPname(cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_PNAME)));
                project.setTname(cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_TNAME)));
                project.setText(cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_TEXT)));
                arrayList.add(project);

            }
            cursor.close();
            return arrayList;
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    @Override
    public Project select_ProjectByTeacher(String tname) {
        SQLiteDatabase db = null;
        Project project = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + ProjectConstant.PROJECT_PNAME + " , "
                    + ProjectConstant.PROJECT_TEXT + " FROM "
                    + ProjectConstant.TABLE_PROJECT_NAME + " WHERE "
                    + ProjectConstant.PROJECT_TNAME + " =?;";
            Cursor cursor = db.rawQuery(sql, new String[] { tname });
            while (cursor.moveToNext()) {
                project = new Project(cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_PNAME)),
                        cursor.getString(cursor
                                .getColumnIndex(ProjectConstant.PROJECT_TEXT)));

            }
            cursor.close();
            return project;
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    @Override
    public Project select_ProjectBySelf(String sno) {
        SQLiteDatabase db = null;
        Project project = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + ProjectConstant.PROJECT_PNAME + " , "
                    + ProjectConstant.PROJECT_TNAME + " , "
                    + ProjectConstant.PROJECT_TEXT + " FROM "
                    + ProjectConstant.TABLE_PROJECT_NAME + " WHERE "
                    + ProjectConstant.PROJECT_PNO + " IN (" + " SELECT "
                    + SelectionConstant.SELECTION_PNO + " FROM "
                    + SelectionConstant.TABLE_SELECTION_NAME + " WHERE "
                    + SelectionConstant.SELECTION_SNO + " =?);";
            System.out.println(sql);
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                project = new Project(
                        cursor.getString(cursor
                                .getColumnIndex(ProjectConstant.PROJECT_PNAME)),
                        cursor.getString(cursor
                                .getColumnIndex(ProjectConstant.PROJECT_TNAME)),
                        cursor.getString(cursor
                                .getColumnIndex(ProjectConstant.PROJECT_TEXT)));
            }
            cursor.close();
            return project;
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    @Override
    public boolean insert_Project(String sno, String pno) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "INSERT INTO "
                    + SelectionConstant.TABLE_SELECTION_NAME + " ( "
                    + SelectionConstant.SELECTION_SNO + " , "
                    + SelectionConstant.SELECTION_PNO + " , "
                    + SelectionConstant.SELECTION_GRADE + " , "
                    + SelectionConstant.SELECTION_SCHEDULE + " , "
                    + SelectionConstant.SELECTION_REMARK + " ) "
                    + "VALUES (?,?,'','','') ;";
            db.execSQL(sql, new String[] { sno, pno });
            return true;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public boolean isSelect(String sno) {
        SQLiteDatabase db = null;
        String isSelect = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + StudentConstant.STUDENT_ISSELECT
                    + " FROM " + StudentConstant.TABLE_NAME_STUDENT + " WHERE "
                    + StudentConstant.STUDENT_SNO + " =?";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                isSelect = cursor.getString(cursor
                        .getColumnIndex(StudentConstant.STUDENT_ISSELECT));
            }
            if (isSelect.equals("已选")) {
                System.out.println("已选");
                return true;
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    @Override
    public String selectRemark(String sno) {
        SQLiteDatabase db = null;
        String remark = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + SelectionConstant.SELECTION_REMARK
                    + " FROM " + SelectionConstant.TABLE_SELECTION_NAME
                    + " WHERE " + SelectionConstant.SELECTION_SNO + " =?";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                remark = cursor.getString(cursor
                        .getColumnIndex(SelectionConstant.SELECTION_REMARK));
            }
            cursor.close();
            return remark;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public void addSchedule(String sno, String text) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "UPDATE " + SelectionConstant.TABLE_SELECTION_NAME
                    + " SET " + SelectionConstant.SELECTION_SCHEDULE + "=?"
                    + " WHERE " + SelectionConstant.SELECTION_SNO + "=?";
            db.execSQL(sql, new String[] { text, sno });
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public void upIsSelect(String sno) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "UPDATE " + StudentConstant.TABLE_NAME_STUDENT
                    + " SET " + StudentConstant.STUDENT_ISSELECT + "='已选' "
                    + " WHERE " + StudentConstant.STUDENT_SNO + " =?";
            db.execSQL(sql, new String[] { sno });

        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    @Override
    public String selectGrade(String sno) {
        SQLiteDatabase db = null;
        String grade = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + SelectionConstant.SELECTION_GRADE
                    + " FROM " + SelectionConstant.TABLE_SELECTION_NAME
                    + " WHERE " + SelectionConstant.SELECTION_SNO + "=?";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                grade = cursor.getString(cursor
                        .getColumnIndex(SelectionConstant.SELECTION_GRADE));
            }
            cursor.close();
            return grade;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public Project selectByPname(String pname) {
        Project project = new Project();
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + ProjectConstant.PROJECT_PNO + " , "
                    + ProjectConstant.PROJECT_TNAME + " , "
                    + ProjectConstant.PROJECT_TEXT + " FROM "
                    + ProjectConstant.TABLE_PROJECT_NAME + " WHERE "
                    + ProjectConstant.PROJECT_PNAME + "=?;";
            Cursor cursor = db.rawQuery(sql, new String[] { pname });
            while (cursor.moveToNext()) {
                project.setPname(pname);
                project.setPno(cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_PNO)));
                project.setTname(cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_TNAME)));
                project.setText(cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_TEXT)));
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return project;
    }

    @Override
    public String getSname(String sno) {
        String sname = null;
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + StudentConstant.STUDENT_SNAME + " FROM "
                    + StudentConstant.TABLE_NAME_STUDENT + " WHERE "
                    + StudentConstant.STUDENT_SNO + "=?";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                sname = cursor.getString(cursor
                        .getColumnIndex(StudentConstant.STUDENT_SNAME));
            }
            cursor.close();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return sname;
    }

    @Override
    public String selectSchedule(String sno) {
        String Schedule = null;
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + SelectionConstant.SELECTION_SCHEDULE
                    + " FROM " + SelectionConstant.TABLE_SELECTION_NAME
                    + " WHERE " + SelectionConstant.SELECTION_SNO + " =?";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                Schedule = cursor.getString(cursor
                        .getColumnIndex(SelectionConstant.SELECTION_SCHEDULE));
            }
            cursor.close();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return Schedule;
    }

    @Override
    public String selectPname(String sno) {
        String pname = null;
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + ProjectConstant.PROJECT_PNAME + " FROM "
                    + ProjectConstant.TABLE_PROJECT_NAME + " WHERE "
                    + ProjectConstant.PROJECT_PNO + " IN ( SELECT "
                    + SelectionConstant.SELECTION_PNO + " FROM "
                    + SelectionConstant.TABLE_SELECTION_NAME + " WHERE "
                    + SelectionConstant.SELECTION_SNO + " =?);";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                pname = cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_PNAME));
            }
            cursor.close();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return pname;
    }
}
