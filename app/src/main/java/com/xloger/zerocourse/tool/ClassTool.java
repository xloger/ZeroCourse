package com.xloger.zerocourse.tool;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.xloger.zerocourse.entity.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16/5/24 下午4:35.
 * Author: xloger
 */
public class ClassTool {

    /**
     * 将一个紧密的 List 根据课程排序转换成一个7*5的 List,多余部分为 null.
     */
    public static List<Class> formatList(List<Class> classes){
        List<Class> classList=new ArrayList<>(35);
        int pointer=0;
        for (int i = 0; i < 35; i++) {
            int nowWeek=(i+1)%7;
            int nowPark=(i+1)/7;
            Log.e("xloger","week:"+nowWeek+",park:"+nowPark);
            if (classes.get(pointer).getWeek()==nowWeek&&classes.get(pointer).getPark()==nowPark){
                classList.add(classes.get(pointer));
                pointer++;
                Log.e("xloger","添加了"+classes.get(pointer).getName());
            }else {
                classList.add(null);
                Log.e("xloger","跳过了"+classes.get(pointer).getName());
            }
        }

        return classList;
    }

    /**
     * 上面的那个实现是傻逼,用我的
     */
    public static List<Class> sortList(List<Class> classes){
        List<Class> classList=new ArrayList<>(35);
        for (int i = 0; i < 35; i++) {
            classList.add(null);
        }
        for (Class cl:
             classes) {
            int pointer=7*(cl.getPark()-1)+cl.getWeek()-1;
            classList.set(pointer,cl);
        }
        return classList;
    }

    /**
     * 加载预设的Color列表
     */
    private static int[] initClassColor(Context context){
        int color[]=new int[10];
        for(int i=0;i<color.length;i++){
            int temp=context.getResources().getIdentifier("class"+(i+1), "color", context.getPackageName());
            color[i]= ContextCompat.getColor(context,temp);
        }
        return color;
    }
    /**
     * 随机配置背景颜色
     * TODO 算法待优化
     */
    public static int getClassColor(Context context,Class cl){
        int color[]=initClassColor(context);
        int hash=cl.getName().hashCode();
        int temp=(Math.abs(hash))%(color.length);
        return color[temp];
    }
}
