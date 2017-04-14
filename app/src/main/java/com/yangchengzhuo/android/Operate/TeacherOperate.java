package com.yangchengzhuo.android.Operate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yangchengzhuo.android.Constant.ProjectConstant;
import com.yangchengzhuo.android.Constant.SelectionConstant;
import com.yangchengzhuo.android.Constant.StudentConstant;
import com.yangchengzhuo.android.Constant.TeacherConstant;
import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.Construction.Selection;
import com.yangchengzhuo.android.Construction.Student;
import com.yangchengzhuo.android.DateBase.DateBase;
import com.yangchengzhuo.android.Interface.TeacherInterface;

import java.util.ArrayList;

/**
 * Created by oliver_yang .
 */
public class TeacherOperate implements TeacherInterface {
    private DateBase mDateBase;

    public TeacherOperate(Context context) {
        this.mDateBase = new DateBase(context);
    }

    /**
     * 教师登录
     */
    @Override
    public boolean login(String tno, String tpassword) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + TeacherConstant.TEACHER_TNO + " , "
                    + TeacherConstant.TEACHER_TPASSWORD + " FROM "
                    + TeacherConstant.TABLE_TEACHER_NAME + " WHERE "
                    + TeacherConstant.TEACHER_TNO + " =? " + " AND "
                    + TeacherConstant.TEACHER_TPASSWORD + " =?;";
            Cursor cursor = db.rawQuery(sql, new String[] { tno, tpassword });
            if (!cursor.moveToNext()) {
                db.close();
                cursor.close();
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

    @Override
    public boolean updateSpassword(String tno, String tpassword) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "UPDATE " + TeacherConstant.TABLE_TEACHER_NAME
                    + " SET " + TeacherConstant.TEACHER_TPASSWORD
                    + " =? WHERE " + TeacherConstant.TEACHER_TNO + " =?;";
            db.execSQL(sql, new String[] { tpassword, tno });
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return false;
    }

    @Override
    public ArrayList<Student> select_Student(String pno) {
        SQLiteDatabase db = null;
        ArrayList<Student> arrayList = new ArrayList<Student>();
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + StudentConstant.STUDENT_SNO + " , "
                    + StudentConstant.STUDENT_SNAME + " , "
                    + StudentConstant.STUDENT_SDEPT + " FROM "
                    + StudentConstant.TABLE_NAME_STUDENT + " WHERE "
                    + StudentConstant.STUDENT_SNO + " IN (" + "SELECT "
                    + SelectionConstant.SELECTION_SNO + " FROM "
                    + SelectionConstant.TABLE_SELECTION_NAME + " WHERE "
                    + SelectionConstant.SELECTION_PNO + " =?);";
            Cursor cursor = db.rawQuery(sql, new String[] { pno });
            while (cursor.moveToNext()) {
                Student student = new Student();
                student.setSno(cursor.getString(cursor
                        .getColumnIndex(StudentConstant.STUDENT_SNO)));
                student.setSname(cursor.getString(cursor
                        .getColumnIndex(StudentConstant.STUDENT_SNAME)));
                student.setSdept(cursor.getString(cursor
                        .getColumnIndex(StudentConstant.STUDENT_SDEPT)));
                arrayList.add(student);
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
    public ArrayList<Project> select_Project() {
        SQLiteDatabase db = null;
        ArrayList<Project> arrayList = new ArrayList<Project>();
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + ProjectConstant.PROJECT_PNAME + " , "
                    + ProjectConstant.PROJECT_TEXT + " , "
                    + ProjectConstant.PROJECT_TNAME + " FROM "
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
    public void select_ProjectByTeacher(String tname) {

    }

    @Override
    public String select_ProjectBySelf(String tno) {
        SQLiteDatabase db = null;
        String pno = "0";
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + ProjectConstant.PROJECT_PNO + " FROM "
                    + ProjectConstant.TABLE_PROJECT_NAME + " WHERE "
                    + ProjectConstant.PROJECT_TNAME + " IN ( SELECT "
                    + TeacherConstant.TEACHER_TNAME + " FROM "
                    + TeacherConstant.TABLE_TEACHER_NAME + " WHERE "
                    + TeacherConstant.TEACHER_TNO + " =?);";
            Cursor cursor = db.rawQuery(sql, new String[] { tno });
            while (cursor.moveToNext()) {
                pno = cursor.getString(cursor
                        .getColumnIndex(ProjectConstant.PROJECT_PNO));
            }
            cursor.close();
            return pno;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public boolean add_Project(String project, String tname, String text) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "INSERT INTO " + ProjectConstant.TABLE_PROJECT_NAME
                    + " ( " + ProjectConstant.PROJECT_PNAME + " , "
                    + ProjectConstant.PROJECT_TNAME + " , "
                    + ProjectConstant.PROJECT_TEXT + " ) VALUES (?,?,?);";
            db.execSQL(sql, new String[] { project, tname, text });
            return true;
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    /**
     * 增加教师方法
     */
    @Override
    public boolean addTeacher(String tno, String tname, String tpassword,
                              String tsex, String tdept, String tnum) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "INSERT INTO " + TeacherConstant.TABLE_TEACHER_NAME
                    + " (" + TeacherConstant.TEACHER_TNO + " , "
                    + TeacherConstant.TEACHER_TNAME + " , "
                    + TeacherConstant.TEACHER_TPASSWORD + " , "
                    + TeacherConstant.TEACHER_TSEX + " , "
                    + TeacherConstant.TEACHER_TDEPT + " , "
                    + TeacherConstant.TEACHER_TNUM + " ) VALUES (?,?,?,?,?,?)";
            db.execSQL(sql, new String[] { tno, tname, tpassword, tsex, tdept,
                    tnum });
            return true;
        } finally {
            if (db != null) {
                db.close();
            }
        }
        // return false;
    }

    @Override
    public int selectSnum(String tno) {
        // TODO Auto-generated method stub
        String count = null;
        int num = 5;
        String SNUM = "snum";
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT COUNT( " + SelectionConstant.SELECTION_PNO
                    + " ) " + SNUM + " FROM "
                    + SelectionConstant.TABLE_SELECTION_NAME + " WHERE "
                    + SelectionConstant.SELECTION_PNO + " IN ( SELECT "
                    + ProjectConstant.PROJECT_PNO + " FROM "
                    + ProjectConstant.TABLE_PROJECT_NAME + " WHERE "
                    + ProjectConstant.PROJECT_TNAME + " IN ( SELECT "
                    + TeacherConstant.TEACHER_TNAME + " FROM "
                    + TeacherConstant.TABLE_TEACHER_NAME + " WHERE "
                    + TeacherConstant.TEACHER_TNO + " =?));";
            Cursor cursor = db.rawQuery(sql, new String[] { tno });
            while (cursor.moveToNext()) {
                count = cursor.getString(cursor.getColumnIndex(SNUM));
            }
            num = Integer.valueOf(count);
            cursor.close();
            return num;
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    @Override
    public String select_teacherName(String tno) {
        String name = null;
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + TeacherConstant.TEACHER_TNAME + " FROM "
                    + TeacherConstant.TABLE_TEACHER_NAME + " WHERE "
                    + TeacherConstant.TEACHER_TNO + " =?;";
            Cursor cursor = db.rawQuery(sql, new String[] { tno });
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor
                        .getColumnIndex(TeacherConstant.TEACHER_TNAME));
            }
            cursor.close();
            return name;
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    @Override
    public void addRemark(String sno, String text) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "UPDATE " + SelectionConstant.TABLE_SELECTION_NAME
                    + " SET " + SelectionConstant.SELECTION_REMARK + " =? "
                    + " WHERE " + SelectionConstant.SELECTION_SNO + " =?";
            db.execSQL(sql, new String[] { text, sno });
        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    @Override
    public void addGrade(String sno, String grade) {
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "UPDATE " + SelectionConstant.TABLE_SELECTION_NAME
                    + " SET " + SelectionConstant.SELECTION_GRADE + " =?"
                    + " WHERE " + SelectionConstant.SELECTION_SNO + " =?";
            db.execSQL(sql, new String[] { grade, sno });

        } finally {
            if (db != null) {
                db.close();
            }
        }

    }

    @Override
    public String SelectSchedule(String sno) {
        SQLiteDatabase db = null;
        String Scheudule = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + SelectionConstant.SELECTION_SCHEDULE
                    + " FROM " + SelectionConstant.TABLE_SELECTION_NAME
                    + " WHERE " + SelectionConstant.SELECTION_SNO + "=?;";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                Scheudule = cursor.getString(cursor
                        .getColumnIndex(SelectionConstant.SELECTION_SCHEDULE));
            }
            cursor.close();
            return Scheudule;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public ArrayList<Selection> SelectSelectioin(String sno) {
        ArrayList<Selection> arrayList = new ArrayList<Selection>();
        SQLiteDatabase db = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + SelectionConstant.SELECTION_SNO + " , "
                    + SelectionConstant.SELECTION_GRADE + " , "
                    + SelectionConstant.SELECTION_SCHEDULE + " , "
                    + SelectionConstant.SELECTION_REMARK + " FROM "
                    + SelectionConstant.TABLE_SELECTION_NAME + " WHERE "
                    + SelectionConstant.SELECTION_SNO + "=?";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                Selection selection = new Selection();
                selection.setSno(cursor.getString(cursor
                        .getColumnIndex(SelectionConstant.SELECTION_SNO)));
                selection.setGrade(cursor.getString(cursor
                        .getColumnIndex(SelectionConstant.SELECTION_GRADE)));
                selection.setSchedule(cursor.getString(cursor
                        .getColumnIndex(SelectionConstant.SELECTION_SCHEDULE)));

                selection.setRemark(cursor.getString(cursor
                        .getColumnIndex(SelectionConstant.SELECTION_REMARK)));
                arrayList.add(selection);

            }
            cursor.close();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return arrayList;
    }

    @Override
    public String getSname(String sno) {
        SQLiteDatabase db = null;
        String sname = null;
        try {
            db = this.mDateBase.getWritableDatabase();
            String sql = "SELECT " + StudentConstant.STUDENT_SNAME + " FROM "
                    + StudentConstant.TABLE_NAME_STUDENT + " WHERE "
                    + StudentConstant.STUDENT_SNO + "=?;";
            Cursor cursor = db.rawQuery(sql, new String[] { sno });
            while (cursor.moveToNext()) {
                sname = cursor.getString(cursor
                        .getColumnIndex(StudentConstant.STUDENT_SNAME));
            }
            cursor.close();
            return sname;

        } finally {
            if (db != null) {

                db.close();
            }
        }

    }

}
