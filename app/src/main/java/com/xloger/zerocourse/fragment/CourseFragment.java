package com.xloger.zerocourse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;

import com.xloger.zerocourse.R;
import com.xloger.zerocourse.adapter.ClassAdapter;
import com.xloger.zerocourse.entity.Class;
import com.xloger.zerocourse.sql.TimeTableManager;
import com.xloger.zerocourse.tool.ClassTool;

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

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),7);
        gridView.setLayoutManager(gridLayoutManager);

        TimeTableManager timeTableManager=new TimeTableManager(getContext());
        List<Class> classList= timeTableManager.getAllClass(null);
        classList= ClassTool.sortList(classList);
        ClassAdapter classAdapter=new ClassAdapter(getContext(),classList,null);
        gridView.setAdapter(classAdapter);

        return view;
    }

}
