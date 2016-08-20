package com.example.cxf.mytest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cxf on 2016/8/19.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建学生表
        String sql1="create table student(s_id integer primary key autoincrement,s_name varchar(20) not null unique ,s_sex int(1),s_age int(5))";
        //创建教师表
        String sql2="create table teacher(t_id integer primary key autoincrement,t_name varchar(20) not null)";
        //创建课程表
        String sql3="create table course(c_id integer primary key autoincrement,c_name varchar(20),t_id integer)";
        //创建学生选课表,记录每个学生所选的课
        String sql4="create table sc (s_id integer, c_id integer,primary key(s_id,c_id))";

        //给teacher表中添加数据
        String insertTeacher="insert into teacher(t_name) values('张三'),('李四'),('王五'),('赵六'),('赵七')";
        //给课程表中添加数据
        String insertCourse="insert into course(c_name,t_id) values('java',1),('mysql',2),('html',3),('javascript',4),('android',5)";

        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(insertTeacher);
        db.execSQL(insertCourse);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
