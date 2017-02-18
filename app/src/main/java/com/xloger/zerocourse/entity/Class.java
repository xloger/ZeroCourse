package com.xloger.zerocourse.entity;

/**
 * Created on 16/5/24 上午10:11.
 * Author: xloger
 */
public class Class {
    //课程的ID
    private int ID;
    //星期几的课
    private int week;
    //当天第几节的课
    private int park;
    //课程的长度
    private int size;
    //课程的名称
    private String name;
    //上课地点
    private String place;
    //老师名字
    private String teacher;
    //课程从第几周到第几周
    private String length;
    //同上课的其他班
    private String friend;
    //是否单双周.0为全,1为单周,2为双周
    private int isDan;

    public Class(){

    }

    public Class( int week, int park,int size, String name, String place, String teacher, String length, String friend, int isDan) {
        this.week = week;
        this.park = park;
        this.size = size;
        this.name = name;
        this.place = place;
        this.teacher = teacher;
        this.length = length;
        this.friend = friend;
        this.isDan = isDan;
    }

    public Class(int week, int park, int size, String name, String place, String teacher) {
        this.week = week;
        this.park = park;
        this.size = size;
        this.name = name;
        this.place = place;
        this.teacher = teacher;
        this.length="01-18";
        this.friend="";
        this.isDan=0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getPark() {
        return park;
    }

    public void setPark(int park) {
        this.park = park;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public int getIsDan() {
        return isDan;
    }

    public void setIsDan(int isDan) {
        this.isDan = isDan;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
