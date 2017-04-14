package com.yangchengzhuo.android.graduate;

import android.app.TabActivity;
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
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.yangchengzhuo.android.Adapter.AdapterTeacherProject1;
import com.yangchengzhuo.android.Adapter.AdapterTeacherProject2;
import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.Construction.Student;
import com.yangchengzhuo.android.Interface.TeacherInterface;

import java.util.ArrayList;

public class TeacherViewActivity extends TabActivity implements View.OnClickListener {
    private Button button_updatePassword;
    private Button button_addProject_add;
    private EditText editText_updatePassWord_old;
    private EditText editText_updatePassword_new;
    private EditText editText_updatePassword_affirm;
    private EditText editText_addProject_topic;
    private EditText editText_addProject_text;

    private TeacherInterface mTeacherInterface;
    private String tno = null;

    private ListView ListView_StudentList;
    private ListView ListView_ProjectList;

    private ArrayList<Project> arrayList_Projects;
    private ArrayList<Student> arrayList_Students;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.createTab();

        tno = getIntent().getStringExtra("no");

        mTeacherInterface = Graduate.getTeacherInterface(this);

        arrayList_Projects = new ArrayList<Project>();
        arrayList_Projects = this.mTeacherInterface.select_Project();

        arrayList_Students = new ArrayList<Student>();
        System.out.println(tno);
        arrayList_Students = this.mTeacherInterface.select_Student(this.mTeacherInterface
                .select_ProjectBySelf(tno));
        for (int i = 0; i < arrayList_Students.size(); i++) {
            System.out.println(arrayList_Students.get(i).getSno());
        }

        button_updatePassword = (Button) this
                .findViewById(R.id.button_updatePassword);
        button_addProject_add = (Button) this
                .findViewById(R.id.button_addProject_add);

        editText_updatePassWord_old = (EditText) this
                .findViewById(R.id.EditText_updatePassword_old);
        editText_updatePassword_new = (EditText) this
                .findViewById(R.id.EditText_updatePassword_new);
        editText_updatePassword_affirm = (EditText) this
                .findViewById(R.id.EditText_updatePassword_affirm);
        editText_addProject_topic = (EditText) this
                .findViewById(R.id.EditText_addProject_Topic);
        editText_addProject_text = (EditText) this
                .findViewById(R.id.EditText_addProject_Text);

        ListView_StudentList = (ListView) this
                .findViewById(R.id.ListView_StudentList);

        ListView_ProjectList = (ListView) this
                .findViewById(R.id.ListView_ProjectList);

        AdapterTeacherProject1 myAdapter1 = new AdapterTeacherProject1(arrayList_Projects,
                getApplicationContext());
        ListView_ProjectList.setAdapter(myAdapter1);
        ListView_ProjectList.setOnItemClickListener(new Click1());

        AdapterTeacherProject2 myAdapter2 = new AdapterTeacherProject2(arrayList_Students,
                getApplicationContext());
        ListView_StudentList.setAdapter(myAdapter2);
        ListView_StudentList.setOnItemClickListener(new Click2());

        button_updatePassword.setOnClickListener(this);
        button_addProject_add.setOnClickListener(this);

    }

    public void createTab() {
        TabHost tabHost = this.getTabHost();
        tabHost.setPadding(0, -20, 0, 0);
        tabHost.setDrawingCacheBackgroundColor(Color.BLUE);

        LayoutInflater layoutInflater = this.getLayoutInflater();
        layoutInflater.inflate(R.layout.activity_teacher_view,
                tabHost.getTabContentView());

        TabHost.TabSpec tabSpec_01 = tabHost.newTabSpec("tab01");
        tabSpec_01.setContent(R.id.LinearLayout_01);
        tabSpec_01.setIndicator("学生列表");
        tabHost.addTab(tabSpec_01);

        TabHost.TabSpec tabSpec_02 = tabHost.newTabSpec("tab02");
        tabSpec_02.setContent(R.id.LinearLayout_02);
        tabSpec_02.setIndicator("题目列表");
        tabHost.addTab(tabSpec_02);


        TabHost.TabSpec tabSpec_03 = tabHost.newTabSpec("tab03");
        tabSpec_03.setContent(R.id.LinearLayout_03);
        tabSpec_03.setIndicator("增加题目");
        tabHost.addTab(tabSpec_03);

        TabHost.TabSpec tabSpec_04 = tabHost.newTabSpec("tab04");
        tabSpec_04.setContent(R.id.LinearLayout_04);
        tabSpec_04.setIndicator("修改密码");
        tabHost.addTab(tabSpec_04);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button_updatePassword:
                if (editText_updatePassWord_old.getText().toString().trim()
                        .equals("")
                        || editText_updatePassword_new.getText().toString().trim()
                        .equals("")
                        || editText_updatePassword_affirm.getText().toString()
                        .trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "请填写完整",
                            Toast.LENGTH_SHORT).show();
                } else {
                    this.mTeacherInterface
                            .updateSpassword(tno, editText_updatePassword_new
                                    .getText().toString().trim());
                    Toast.makeText(getApplicationContext(), "修改成功",
                            Toast.LENGTH_SHORT).show();
                    editText_updatePassWord_old.setText("");
                    editText_updatePassword_new.setText("");
                    editText_updatePassword_affirm.setText("");
                }
                break;
            case R.id.button_addProject_add:

                if (editText_addProject_topic.getText().toString().trim()
                        .equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入题目",
                            Toast.LENGTH_SHORT).show();
                } else if (editText_addProject_text.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入题目简介",
                            Toast.LENGTH_SHORT).show();
                } else if (this.mTeacherInterface.add_Project(editText_addProject_topic
                                .getText().toString().trim(),
                        this.mTeacherInterface.select_teacherName(tno),
                        editText_addProject_text.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "题目添加成功",
                            Toast.LENGTH_SHORT).show();
                    editText_addProject_text.setText("");
                    editText_addProject_topic.setText("");
                }
                break;
        }

    }

    class Click1 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            String message = "题目："
                    + arrayList_Projects.get(position).getPname() + "\n"
                    + "命题老师： " + arrayList_Projects.get(position).getTname()
                    + "\n" + "题目简介： "
                    + arrayList_Projects.get(position).getText();
            new AlertDialog.Builder(TeacherViewActivity.this).setMessage(message)
                    .setPositiveButton("确定", null).create().show();

        }
    }

    class Click2 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            System.out.println(arrayList_Students.get(position).getSno());
            Intent intent = new Intent();
            intent.setClass(TeacherViewActivity.this, StudentProjectActivity.class);
            intent.putExtra("sno", arrayList_Students.get(position).getSno());
            startActivity(intent);
        }

    }

}
