package com.xloger.zerocourse.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xloger.zerocourse.R;
import com.xloger.zerocourse.entity.Class;
import com.xloger.zerocourse.tool.ClassTool;
import com.xloger.zerocourse.view.ClassDetailsDialog;

import java.util.List;

/**
 * Created on 16/5/24 下午1:51.
 * Author: xloger
 */
public class ClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Class> classList;
    private ClassCallBack callBack;

    public ClassAdapter(Context context, List<Class> classList, ClassCallBack callBack) {
        this.context = context;
        this.classList = classList;
        this.callBack = callBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1){
            View view= LayoutInflater.from(context).inflate(R.layout.item_class,parent,false);
            ClassViewHolder viewHolder=new ClassViewHolder(view);
            return viewHolder;
        }else {
            View view= LayoutInflater.from(context).inflate(R.layout.item_null,parent,false);
            NullViewHolder viewHolder=new NullViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
        if (h instanceof NullViewHolder){
            return;
        }
        ClassViewHolder holder=(ClassViewHolder) h;
        final Class cl=classList.get(position);
        if (cl != null) {
            holder.name.setText(cl.getName());
            holder.place.setText(cl.getPlace());
            holder.layout.setBackgroundColor(ClassTool.getClassColor(cl));
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClassDetailsDialog dialog=new ClassDetailsDialog(context,cl);
//                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                }
            });

            if (!ClassTool.isStudy(cl)){
                int temp=context.getResources().getIdentifier("unStudy", "color", context.getPackageName());
                int unStudyColor=ContextCompat.getColor(context,temp);
                holder.layout.setBackgroundColor(unStudyColor);
                holder.place.setText("上课时间："+cl.getLength()+"周");
                holder.place.setTextSize(12);
            }
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

    public class NullViewHolder extends RecyclerView.ViewHolder{

        public NullViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (classList.get(position).getName()==null){
            return 0;
        }else {
            return 1;
        }
    }


    public interface ClassCallBack{
        void onClick();
    }
}
