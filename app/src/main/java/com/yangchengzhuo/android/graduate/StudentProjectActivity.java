package com.yangchengzhuo.android.graduate;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yangchengzhuo.android.Construction.Selection;
import com.yangchengzhuo.android.Interface.TeacherInterface;

import java.util.ArrayList;

public class StudentProjectActivity extends AppCompatActivity implements View.OnClickListener {
    private String sno;
    private TextView student_textview_sno;
    private TextView student_textview_sname;
    private TextView student_textview_schedule;
    private TextView student_textview_grade;
    private TextView student_textview_remark;

    private EditText student_editText_remark;

    private Button student_button_addremark;
    private Button student_button_addGrade;

    private TeacherInterface mTeacherInterface;

    private ArrayList<Selection> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_project);
        sno = getIntent().getStringExtra("sno");

        mTeacherInterface = Graduate.getTeacherInterface(this);

        arrayList = this.mTeacherInterface.SelectSelectioin(sno);

        student_textview_sno = (TextView) findViewById(R.id.student_textView_sno);
        student_textview_sname = (TextView) findViewById(R.id.student_textView_name);
        student_textview_schedule = (TextView) findViewById(R.id.student_textView_Schedule);
        student_textview_grade = (TextView) findViewById(R.id.student_textview_grade);
        student_textview_remark = (TextView) findViewById(R.id.student_textView_remark);

        student_editText_remark = (EditText) findViewById(R.id.student_edittext_remark);

        student_button_addremark = (Button) findViewById(R.id.student_button_addremark);
        student_button_addGrade = (Button) findViewById(R.id.student_button_addGrade);

        student_textview_sno.setText(sno);

        student_textview_sname.setText(this.mTeacherInterface.getSname(sno));

        if (arrayList.get(0).getGrade().equals("")) {
            student_textview_grade.setText("无");
            student_button_addGrade.setVisibility(View.VISIBLE);
        } else {
            student_textview_grade.setText(arrayList.get(0).getGrade());
            student_button_addGrade.setVisibility(View.INVISIBLE);
        }
        if (arrayList.get(0).getSchedule().equals("")) {
            student_textview_schedule.setText("未完成");
        } else {
            student_textview_schedule.setText(arrayList.get(0).getSchedule());
        }

        if (arrayList.get(0).getRemark().equals("")) {
            student_textview_remark.setText("无");
        } else {
            student_textview_remark.setText(arrayList.get(0).getRemark());
        }

        student_button_addremark.setOnClickListener(this);
        student_button_addGrade.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.student_button_addremark:
                if (student_editText_remark.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "内容不能为空，请输入内容",
                            Toast.LENGTH_SHORT).show();
                    student_editText_remark.requestFocus();
                } else if (!student_editText_remark.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "已有评语，不能重复评论",
                            Toast.LENGTH_SHORT).show();
                } else {
                    this.mTeacherInterface.addRemark(sno, student_editText_remark
                            .getText().toString());
                    student_editText_remark.setText("");
                    Toast.makeText(getApplicationContext(), "评论成功",
                            Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.student_button_addGrade:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                LinearLayout linearLayout = (LinearLayout) getLayoutInflater()
                        .inflate(R.layout.addgrade, null);
                final EditText student_editText_addGrade = (EditText) linearLayout
                        .findViewById(R.id.student_edittext_addgrade);
                alertDialog.setView(linearLayout);

                alertDialog
                        .setTitle("打分")
                        .setPositiveButton("打分",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        mTeacherInterface.addGrade(sno,
                                                student_editText_addGrade.getText()
                                                        .toString());

                                    }
                                }).create().show();
                break;

        }

    }
}
