package com.xloger.zerocourse.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xloger.zerocourse.entity.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16/5/24 上午10:35.
 * Author: xloger
 */
public class TimeTableManager {

    private Context context;
    private DBHelper dbHelper;
    private final SQLiteDatabase db;

    public TimeTableManager(Context context){
        this.context = context;
        dbHelper=new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        Log.e("xloger","isNull:"+isNull());
        if(isNull()){
            initData();
        }
    }

    public void insert(Class cl){
        db.beginTransaction();
        try {
            db.execSQL("insert into "+DBHelper.TABLE_NAME+"(week,park,name,place,teacher,length,friend,isDan)"+
            " values(?,?,?,?,?,?,?,?)",
                    new Object[]{cl.getWeek(),cl.getPark(),cl.getName(),cl.getPlace(),cl.getTeacher(),cl.getLength(),cl.getFriend(),cl.getIsDan()});
        db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    public void insert(List<Class> classList){
        for (Class cl :
                classList) {
            insert(cl);
        }
    }

    public List<Class> getAllClass(String where){
        List<Class> classList=new ArrayList<>();
        String sql = "select * from " + DBHelper.TABLE_NAME;
        if (where != null) {
            sql=sql+where;
        }
        Cursor cursor=db.rawQuery(sql,null);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Class cl=new Class();
            cl.setWeek(cursor.getInt(cursor.getColumnIndex("week")));
            cl.setPark(cursor.getInt(cursor.getColumnIndex("park")));
            cl.setName(cursor.getString(cursor.getColumnIndex("name")));
            cl.setPlace(cursor.getString(cursor.getColumnIndex("place")));
            cl.setTeacher(cursor.getString(cursor.getColumnIndex("teacher")));
            cl.setLength(cursor.getString(cursor.getColumnIndex("length")));
            cl.setFriend(cursor.getString(cursor.getColumnIndex("friend")));
            cl.setIsDan(cursor.getInt(cursor.getColumnIndex("isDan")));
            classList.add(cl);
        }
        return classList;
    }

    public List<Class> getWeekClass(int week){
        return getAllClass(" where week = "+week);
    }

    public List<Class> getParkClass(int park){
        return getAllClass(" where park = "+park);
    }

    private void initData(){
        List<Class> classList=new ArrayList<>();
        classList.add(new Class(1, 1, "多媒体技术及其应用", "N405卓越班机房", "张元英", "01-16", "12计科卓越班",0));
        classList.add(new Class(1, 2, "听力与口语", "主教楼N103", "张夏", "01-14", "12计科卓越班",0));
        classList.add(new Class(1, 3, "软件框架与软件体系结构", "N405卓越班机房", "张连福", "09-16", "12计科卓越班",0));

        classList.add(new Class(2, 3, "听力与口语", "N407N408", "张夏", "01-14", "12计科卓越班",0));

        classList.add(new Class(3, 2, "软件测试技术", "N405卓越班机房", "方光伟", "01-16", "12计科卓越班",0));
        classList.add(new Class(3, 3, "富界面编程技术", "N405卓越班机房", "万伟", "01-16", "12计科卓越班",0));

        classList.add(new Class(4, 1, "多媒体技术及其应用", "N405卓越班机房", "张元英", "09-16", "12计科卓越班",0));
        classList.add(new Class(4, 2, "多媒体技术及其应用", "N405卓越班机房", "张元英", "01-16", "12计科卓越班",0));
        classList.add(new Class(4, 4, "XML与WEB Service技术", "主教楼N103", "雷小园", "01-14", "12计科卓越班",0));
        classList.add(new Class(4, 5, "富界面编程技术", "主教楼S205", "万伟", "01-16", "12计科卓越班",0));

        classList.add(new Class(5, 1, "软件测试技术", "主教楼S603", "方光伟", "01-16", "12计科卓越班",0));
        classList.add(new Class(5, 2, "软件框架与软件体系结构", "主教楼S611", "张连福", "01-16", "12计科卓越班",0));
        classList.add(new Class(5, 3, "XML与WEB Service技术", "5楼6机房", "雷小园", "01-14", "12软件工程，12计科卓越班",0));
        classList.add(new Class(5, 4, "软件框架与软件体系结构", "N405卓越班机房", "张连福", "09-16", "12计科卓越班",0));

        insert(classList);
    }

    /**
     * 判断数据库里面是否没有数据
     * @return 没有返回true，有返回false
     */
    public boolean isNull(){
        Cursor cursor=db.rawQuery("select ID from "+DBHelper.TABLE_NAME, null);
        return cursor.getCount() == 0;
    }

    public void closeDB() {
        db.close();
    }
}
