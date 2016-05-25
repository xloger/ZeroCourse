package com.xloger.zerocourse.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.xloger.zerocourse.R;
import com.xloger.zerocourse.entity.Class;
import com.xloger.zerocourse.tool.ClassTool;

/**
 * Created on 16/5/25 上午11:18.
 * Author: xloger
 */
public class ClassDetailsDialog extends Dialog {
    private Context context;
    private Class cl;
    private TextView title;
    private TextView place;
    private TextView teacher;
    private TextView length;
    private TextView friend;
    private TextView dan;

    public ClassDetailsDialog(Context context,Class cl) {
        super(context, R.style.ClassDetailsDialog);
        this.context=context;
        this.cl=cl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_class_details);
        initView();
        initColor();

        title.setText(cl.getName());
        place.setText(cl.getPlace());
        teacher.setText(cl.getTeacher());
        length.setText(cl.getLength());
        friend.setText(cl.getFriend());
        dan.setText(cl.getIsDan()+"");
    }

    private void initView(){
        title = (TextView) findViewById(R.id.details_title);
        place = (TextView) findViewById(R.id.details_place);
        teacher = (TextView) findViewById(R.id.details_teacher);
        length = (TextView) findViewById(R.id.details_length);
        friend = (TextView) findViewById(R.id.details_friend);
        dan = (TextView) findViewById(R.id.details_dan);
    }

    private void initColor(){
        int color= ClassTool.getClassColor(context,cl);
        title.setTextColor(color);
        place.setTextColor(color);
        teacher.setTextColor(color);
        length.setTextColor(color);
        friend.setTextColor(color);
        dan.setTextColor(color);
    }
}
