package com.example.cxf.mytest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cxf.mytest.domain.Course;
import com.example.cxf.mytest.domain.Student;
import com.example.cxf.mytest.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxf on 2016/8/19.
 */
public class SQLiteUtil {

    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private String dbName = "chooseCourse.db";
    //单例
    private static SQLiteUtil util;

    private SQLiteUtil(Context context, int version) {
        helper = new MySQLiteOpenHelper(context, dbName, null, version);
        db = helper.getReadableDatabase();
    }

    public static SQLiteUtil getInstance(Context context, int version) {
        if (util == null) {
            synchronized (SQLiteUtil.class) {
                if (util == null) {
                    util = new SQLiteUtil(context, version);
                }
            }
        }
        return util;
    }

    //添加学生到学生表,并且返回新添加的学生对象
    private Student insertStudent(String name,int sex,int age) {
        ContentValues cv = new ContentValues();
        cv.put("s_name", name);
        cv.put("s_sex", sex);
        cv.put("s_age", age);
        long id=db.insert("student", null, cv);
        if(id!=-1){
            Student student=new Student((int)id,name,sex,age);
            return student;
        }else{
            return null;
        }
    }
    //根据姓名查询，返回学生对象
    public Student queryStudent(String name,int sex,int age){
        String sql="select * from student where s_name='"+name+"'";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToNext()){
            //有查询结果
            int sid=cursor.getInt(cursor.getColumnIndex("s_id"));
            String sname=cursor.getString(cursor.getColumnIndex("s_name"));
            int ssex=cursor.getInt(cursor.getColumnIndex("s_sex"));
            int sage=cursor.getInt(cursor.getColumnIndex("s_age"));
            return new Student(sid,sname,ssex,sage);
        }else{
            //没有查询结果
            return insertStudent(name,sex,age);
        }

    }

    //查询课程表
    public List<Course> queryCourseForList() {
        List<Course> clist = new ArrayList<>();
        String sql = "select c.c_id,c.c_name,t.t_id,t.t_name,count(sc.s_id) count from course c left join teacher t on c.t_id=t.t_id left join sc on c.c_id=sc.c_id group by c.c_id";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            //老师的id
            int tid = cursor.getInt(cursor.getColumnIndex("t_id"));
            //老师的name
            String tname=cursor.getString(cursor.getColumnIndex("t_name"));
            Teacher teacher=new Teacher(tid,tname);
            //课程的id
            int cId = cursor.getInt(cursor.getColumnIndex("c_id"));
            //课程的name
            String cName = cursor.getString(cursor.getColumnIndex("c_name"));
            //课程的人数
            int count=cursor.getInt(cursor.getColumnIndex("count"));
            Course course=new Course(cId,cName,teacher,count);
            clist.add(course);
        }
        return clist;
    }
    //查询学生是否选了该课，已选返回true,未选返回false
    public boolean isChoosed(int sid,int cid){
        String sql="select c_id from sc where s_id="+sid+" and c_id="+cid;
        Cursor cursor=db.rawQuery(sql,null);
        if (cursor.moveToNext()){
            return true;
        }else {
            return false;
        }
    }
    //把学生的选课信息添加到选课表里
    public void insertStudentCourse(int sid,int cid){
        String sql="insert into sc values("+sid+","+cid+")";
        db.execSQL(sql);
    }
    //根据学生姓名获取选课名称
    public List<String> getCourseInfo(String stuName){
        List<String> list=new ArrayList<>();
        String sql="select c.c_name from course c join sc on c.c_id=sc.c_id join student s on sc.s_id=s.s_id where s.s_name='"+stuName+"'";
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            String courseName=cursor.getString(0);
            list.add(courseName);
        }
        return list;
    }
}
