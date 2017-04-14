package com.yangchengzhuo.android.graduate;

import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.yangchengzhuo.android.Adapter.AdapterStudentProject;
import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.Interface.StudentInterface;

import java.util.ArrayList;

public class StudentViewActivity extends TabActivity implements View.OnClickListener {
    private StudentInterface mStudentInterface;
    private String sno = null;

    private TextView project_student_name;
    private TextView project_student_project_name;
    private TextView project_student_schedule;
    private TextView project_student_grade;
    private TextView project_student_remark;

    private EditText student_EditText_updatePassword_old;
    private EditText student_EditText_updatePassword_new;
    private EditText Student_EditText_updatePassword_affirm;

    private Button student_button_updatePassword;
    private Button project_student_button;

    private ListView listView_student_project;

    private ArrayList<Project> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mStudentInterface = Graduate.getStudentInterface(this);
        super.onCreate(savedInstanceState);

        arrayList = this.mStudentInterface.select_Project();
        sno = getIntent().getStringExtra("no");

        this.createTab();

        project_student_name = (TextView) this
                .findViewById(R.id.project_student_name);
        project_student_project_name = (TextView) this
                .findViewById(R.id.project_student_project_name);
        project_student_schedule = (TextView) this
                .findViewById(R.id.project_student_schedule);
        project_student_grade = (TextView) this
                .findViewById(R.id.project_student_grade);
        project_student_remark = (TextView) this
                .findViewById(R.id.project_student_remark);

        student_EditText_updatePassword_old = (EditText) this
                .findViewById(R.id.student_EditText_updatePassword_old);
        student_EditText_updatePassword_new = (EditText) this
                .findViewById(R.id.studnet_EditText_updatePassword_new);
        Student_EditText_updatePassword_affirm = (EditText) this
                .findViewById(R.id.Student_EditText_updatePassword_affirm);

        listView_student_project = (ListView) this
                .findViewById(R.id.ListView_student_project);
        AdapterStudentProject adapter_Student_Project = new AdapterStudentProject(
                arrayList, getApplicationContext());
        listView_student_project.setAdapter(adapter_Student_Project);
        listView_student_project
                .setOnItemClickListener(new listview_project_click());

        student_button_updatePassword = (Button) this
                .findViewById(R.id.student_button_updatePassword);
        project_student_button = (Button) this
                .findViewById(R.id.project_student_button);
        project_student_button.setOnClickListener(this);

        student_button_updatePassword.setOnClickListener(this);

        project_student_name.setText(mStudentInterface.getSname(sno));
        project_student_project_name.setText(mStudentInterface.selectPname(sno));

        project_student_schedule.setText(mStudentInterface.selectSchedule(sno));

        project_student_grade.setText(mStudentInterface.selectGrade(sno));
        project_student_remark.setText(mStudentInterface.selectRemark(sno));
    }

    public void createTab() {
        TabHost tabHost = this.getTabHost();
        tabHost.setPadding(0, -20, 0, 0);
        tabHost.setDrawingCacheBackgroundColor(Color.BLUE);

        LayoutInflater layoutInflater = this.getLayoutInflater();
        layoutInflater.inflate(R.layout.activity_student_view,
                tabHost.getTabContentView());

        TabHost.TabSpec spec_01 = tabHost.newTabSpec("tab01");
        spec_01.setContent(R.id.Student_LinearLayout_01);
        spec_01.setIndicator("基本信息");
        tabHost.addTab(spec_01);

        TabHost.TabSpec spec_02 = tabHost.newTabSpec("tab02");
        spec_02.setContent(R.id.Student_LinearLayout_02);
        spec_02.setIndicator("查看题目");
        tabHost.addTab(spec_02);

        TabHost.TabSpec spec_04 = tabHost.newTabSpec("tab04");
        spec_04.setContent(R.id.Student_LinearLayout_04);
        spec_04.setIndicator("修改密码");
        tabHost.addTab(spec_04);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.student_button_updatePassword:
                if (student_EditText_updatePassword_old.getText().toString().trim()
                        .equals("")
                        || student_EditText_updatePassword_new.getText().toString()
                        .trim().equals("")
                        || Student_EditText_updatePassword_affirm.getText()
                        .toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "请填写完整",
                            Toast.LENGTH_SHORT).show();
                } else {
                    this.mStudentInterface.updateSpassword(sno,
                            student_EditText_updatePassword_new.getText()
                                    .toString().trim());
                    Toast.makeText(getApplicationContext(), "修改成功",
                            Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.project_student_button:
                if (project_student_schedule.getText().toString().equals("完成")) {
                    Toast.makeText(getApplicationContext(), "进度已经完成不能修改",
                            Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    LinearLayout layout = (LinearLayout) this.getLayoutInflater()
                            .inflate(R.layout.updateschedule, null);
                    final EditText edittext_updateSchedule = (EditText) layout
                            .findViewById(R.id.edittext_updateSchedule);
                    builder.setView(layout);
                    builder.setTitle("修改进度")
                            .setPositiveButton("修改",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            mStudentInterface.addSchedule(sno,
                                                    edittext_updateSchedule
                                                            .getText().toString()
                                                            .trim());
                                            Toast.makeText(getApplicationContext(),
                                                    "修改成功", Toast.LENGTH_SHORT)
                                                    .show();

                                        }
                                    }).create().show();
                }
                break;
            default:
                break;
        }

    }

    class listview_project_click implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent();
            intent.setClass(StudentViewActivity.this, ProjectdetailActivity.class);
            intent.putExtra("pname", arrayList.get(position).getPname());
            intent.putExtra("sno", sno);
            startActivity(intent);
        }

    }
}
