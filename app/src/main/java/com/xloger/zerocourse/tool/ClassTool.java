package com.xloger.zerocourse.tool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.format.Time;
import android.util.Log;

import com.xloger.zerocourse.entity.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * 真正Clear的实现，上面的果然都是傻逼
     */
    public static List<Class> addClasses(List<Class> classes){
        List<Class> classList=new ArrayList<>();
        Class lastClass=null;
        for (int i = 0; i < classes.size(); i++) {
            Class iClass=classes.get(i);
            //判断早上第一节课前是否有空白
            if (lastClass==null||iClass.getWeek()>lastClass.getWeek()){
                if (iClass.getPark()!=1){
                    Class temp=new Class();
                    temp.setWeek(iClass.getWeek());
                    temp.setPark(1);
                    temp.setSize(iClass.getPark()-temp.getPark());
                    temp.setName(null);
                    classList.add(temp);
                }
            }

            if (lastClass!=null){
                if (iClass.getWeek()==lastClass.getWeek()){
                    if (iClass.getPark()>(lastClass.getPark()+lastClass.getSize())){
                        Class temp=new Class();
                        temp.setWeek(iClass.getWeek());
                        temp.setPark(lastClass.getPark()+lastClass.getSize());
                        temp.setSize(iClass.getPark()-temp.getPark());
                        temp.setName(null);
                        classList.add(temp);
                    }
                }else {
                    if (lastClass.getPark()!=9){
                        Class temp=new Class();
                        temp.setWeek(lastClass.getWeek());
                        temp.setPark(lastClass.getPark()+lastClass.getSize());
                        temp.setSize(9-temp.getPark());
                        temp.setName(null);
                        classList.add(temp);
                    }
                }
            }

            classList.add(iClass);

            lastClass=iClass;
        }

        return classList;
    }

    /**
     * 加载预设的Color列表
     */
    private static int[] getColorRes(Context context){
        int color[]=new int[10];
        for(int i=0;i<color.length;i++){
            int temp=context.getResources().getIdentifier("class"+(i+1), "color", context.getPackageName());
            color[i]= ContextCompat.getColor(context,temp);
        }
        return color;
    }

    private static Map<String,Integer> colorMap=null;

    public static void initClassColor(Context context,List<Class> classList){
        int color[]= getColorRes(context);
        Set<String> md5Set=new HashSet<>();
        for (int i = 0; i < classList.size(); i++) {
            Class iClass=classList.get(i);
            md5Set.add(iClass.getName().replaceAll("实验",""));
        }
        String[] md5List=md5Set.toArray(new String[md5Set.size()]);

        colorMap=new Hashtable<>();
        for (int i = 0; i < md5List.length; i++) {
            colorMap.put(md5List[i],color[i]);
        }
    }

    /**
     * 随机配置背景颜色
     */
    public static int getClassColor(Class cl){
        if (colorMap==null){
            return Color.BLUE;
        }
        return colorMap.get(cl.getName().replaceAll("实验",""));
    }

    public static int getNowWeek(){
        int nowWeek;
        String begin_date = Config.newInstance().getConfig("begin_date");
        if (begin_date == null) {
            Config.newInstance().setConfig("begin_date","20170213");
            begin_date="20170213";
        }

        Time begintime=new Time();
        begintime.parse(begin_date);
        Time nowtime=new Time();
        nowtime.setToNow();
        if(nowtime.year==begintime.year){
            nowWeek=nowtime.getWeekNumber()-begintime.getWeekNumber();
        }else{
            Time yearendtime=new Time();
            yearendtime.parse(begintime.year+"1231");
            nowWeek=yearendtime.getWeekNumber()-begintime.getWeekNumber()+nowtime.getWeekNumber();
        }

        return nowWeek;
    }

    public static boolean isStudy(Class cl){
        int nowWeek=getNowWeek();
        String[] s=cl.getLength().split("-");
        if(s.length==2){
            int beginWeek=Integer.parseInt(s[0]);
            int endWeek=Integer.parseInt(s[1]);
            if(nowWeek>=beginWeek&&nowWeek<=endWeek){
                return true;
            }
        }else{
            Log.e("show", "解析classWeek长度出错");
        }
        return false;
    }
}
