package com.xloger.zerocourse.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xloger.zerocourse.R;
import com.xloger.zerocourse.entity.Class;
import com.xloger.zerocourse.tool.ClassTool;

import java.util.List;

/**
 * Created on 16/5/24 下午1:51.
 * Author: xloger
 */
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private Context context;
    private List<Class> classList;
    private ClassCallBack callBack;

    public ClassAdapter(Context context, List<Class> classList, ClassCallBack callBack) {
        this.context = context;
        this.classList = classList;
        this.callBack = callBack;
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_class,parent,false);
        ClassViewHolder viewHolder=new ClassViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        Class cl=classList.get(position);
        if (cl != null) {
            holder.name.setText(cl.getName());
            holder.place.setText(cl.getPlace());
            holder.layout.setBackgroundColor(ClassTool.getClassColor(context,cl));
        }
    }

    @Override
    public int getItemCount() {
        return classList==null?0:classList.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layout;
        private TextView name;
        private TextView place;

        public ClassViewHolder(View itemView) {
            super(itemView);
            layout= (LinearLayout) itemView.findViewById(R.id.item_class);
            name= (TextView) itemView.findViewById(R.id.class_name);
            place= (TextView) itemView.findViewById(R.id.class_place);
        }
    }

    public interface ClassCallBack{
        void onClick();
    }
}