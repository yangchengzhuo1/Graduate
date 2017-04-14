package com.yangchengzhuo.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yangchengzhuo.android.Construction.Project;
import com.yangchengzhuo.android.graduate.R;

import java.util.ArrayList;

/**
 * Created by oliver_yang .
 */
public class AdapterStudentProject extends BaseAdapter{
    private ArrayList<Project> arrayList;
    private Context context;

    public AdapterStudentProject(ArrayList<Project> arrayList, Context context) {
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
                    R.layout.activity_student_project_item, null);
            holder.textView = (TextView) convertView
                    .findViewById(R.id.student_textView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView.setText(arrayList.get(position).getPname());
        return convertView;
    }

    class Holder {
        TextView textView;
    }
}
