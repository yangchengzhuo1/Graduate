package com.yangchengzhuo.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yangchengzhuo.android.Construction.Student;
import com.yangchengzhuo.android.graduate.R;

import java.util.ArrayList;

/**
 * Created by oliver_yang .
 */
public class AdapterTeacherProject2 extends BaseAdapter {
    private ArrayList<Student> arrayList;
    private Context context;

    public AdapterTeacherProject2(ArrayList<Student> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.activity_teacher_student_item, null);
            holder.textView = (TextView) convertView
                    .findViewById(R.id.textview_student);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView.setText(arrayList.get(position).getSname());
        return convertView;
    }

    private class Holder {
        TextView textView;
    }
}
