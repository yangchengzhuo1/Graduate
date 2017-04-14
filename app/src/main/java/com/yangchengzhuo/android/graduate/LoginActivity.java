package com.yangchengzhuo.android.graduate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yangchengzhuo.android.Interface.StudentInterface;
import com.yangchengzhuo.android.Interface.TeacherInterface;

public class LoginActivity extends AppCompatActivity {
    private EditText editText_username;// 用户名输入区
    private EditText editText_password;// 密码输入区
    private Button button_login;// 登录按钮
    private Button button_reset;// 重置按钮
    private Spinner spinner;// 用户选择
    private StudentInterface mStudentInterface;
    private TeacherInterface mTeacherInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mStudentInterface =Graduate.getStudentInterface(this);
        mTeacherInterface=Graduate.getTeacherInterface(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_username = (EditText) this
                .findViewById(R.id.edittext_username);
        editText_password = (EditText) this
                .findViewById(R.id.edittext_password);
        button_login = (Button) this.findViewById(R.id.Button_login);
        button_reset = (Button) this.findViewById(R.id.Button_reset);
        spinner = (Spinner) this.findViewById(R.id.spinner);
        button_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItem().equals("学生")) {
                    if (mStudentInterface.login(editText_username.getText().toString()
                            .trim(), editText_password.getText().toString()
                            .trim())) {
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, StudentViewActivity.class);
                        intent.putExtra("no", editText_username.getText()
                                .toString().trim());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "用户与密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        editText_password.requestFocus();
                    }
                } else if (spinner.getSelectedItem().equals("老师")) {
                    if (mTeacherInterface.login(editText_username.getText().toString()
                            .trim(), editText_password.getText().toString()
                            .trim())) {
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, TeacherViewActivity.class);
                        intent.putExtra("no", editText_username.getText()
                                .toString().trim());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "用户与密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        editText_password.requestFocus();
                    }
                }

            }
        });
        button_reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editText_username.setText("");
                editText_password.setText("");
                editText_username.requestFocus();

            }
        });
    }
}
