package com.xloger.zerocourse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.xloger.zerocourse.R;
import com.xloger.zerocourse.adapter.ClassAdapter;
import com.xloger.zerocourse.entity.Class;
import com.xloger.zerocourse.sql.TimeTableManager;
import com.xloger.zerocourse.tool.ClassTool;
import com.xloger.zerocourse.tool.Config;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends BaseFragment {


    public CourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_course, null);
        RecyclerView gridView= (RecyclerView) view.findViewById(R.id.course_grid_view);
        TextView dateText= (TextView) view.findViewById(R.id.course_date);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),9,LinearLayoutManager.HORIZONTAL,false);
        gridView.setLayoutManager(gridLayoutManager);

        TimeTableManager timeTableManager=new TimeTableManager(getContext());
        List<Class> primaryList= timeTableManager.getAllClass(null);
        ClassTool.initClassColor(getContext(),primaryList);
        final List<Class> classList= ClassTool.addClasses(primaryList);
        ClassAdapter classAdapter=new ClassAdapter(getContext(),classList,null);
        gridView.setAdapter(classAdapter);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return classList.get(position).getSize();
            }
        });

        dateText.setText("当前第"+ClassTool.getNowWeek()+"周");

        return view;
    }

}
