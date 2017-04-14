package com.yangchengzhuo.android.graduate;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yangchengzhuo.android.Interface.StudentInterface;

public class ProjectdetailActivity extends AppCompatActivity implements View.OnClickListener {
    private String pname = null;
    private String sno = null;
    private TextView project_detail_textviewtopic;
    private TextView project_detail_textviewtname;
    private TextView project_detail_textviewtext;
    private Button project_detail_buttonselect;

    private StudentInterface mStudentInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetail);
        mStudentInterface = Graduate.getStudentInterface(this);

        pname = getIntent().getStringExtra("pname");
        sno = getIntent().getStringExtra("sno");
        System.out.println(pname);
        project_detail_textviewtopic = (TextView) this
                .findViewById(R.id.project_detail_textviewtopic);
        project_detail_textviewtname = (TextView) this
                .findViewById(R.id.project_detail_textviewtname);
        project_detail_textviewtext = (TextView) this
                .findViewById(R.id.project_detail_textviewtext);

        project_detail_buttonselect = (Button) this
                .findViewById(R.id.project_detail_buttonselect);

        project_detail_textviewtopic.setText(pname);
        project_detail_textviewtname.setText(mStudentInterface.selectByPname(pname)
                .getTname().toString());
        project_detail_textviewtext.setText(mStudentInterface.selectByPname(pname)
                .getText().toString());
        project_detail_buttonselect.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.project_detail_buttonselect:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("选题");
                builder.setMessage("确定要选" + pname + "吗？");
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (mStudentInterface.isSelect(sno)) {
                                    Toast.makeText(getApplicationContext(),
                                            "已经选择过题目，不能重复选题！", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    mStudentInterface.insert_Project(sno, mStudentInterface
                                            .selectByPname(pname).getPno());
                                    Toast.makeText(getApplicationContext(), "选题成功",
                                            Toast.LENGTH_SHORT).show();
                                   mStudentInterface.upIsSelect(sno);
                                }
                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create().show();
                break;
        }

    }
}
