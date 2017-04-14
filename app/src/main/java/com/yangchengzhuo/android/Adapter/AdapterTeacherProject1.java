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
public class AdapterTeacherProject1 extends BaseAdapter{
    private ArrayList<Project> arrayList_Projects;
    private Context context;

    public AdapterTeacherProject1(ArrayList<Project> arrayList_Projects, Context context) {
        this.arrayList_Projects = arrayList_Projects;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrayList_Projects.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.activity_teacher_project_item, null);
            holder.textView = (TextView) convertView
                    .findViewById(R.id.textview_project);
            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView.setText(arrayList_Projects.get(position).getPname());
        return convertView;
    }

    private class Holder {
        TextView textView;
    }

}
