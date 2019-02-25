package com.example.gymbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gymbuddy.data.GymContract.NewUser;
import com.example.gymbuddy.data.ScheduleContract.userSchedule;

/**
 * Created by Rich on 2/27/2018.
 */

public class DBHandler extends SQLiteOpenHelper {

    public Context context;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GymBuddy.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + NewUser.TABLE_NAME + " (" + NewUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NewUser.column_username + " TEXT, " + NewUser.column_password + " TEXT, " + NewUser.column_fname + " TEXT, " + NewUser.column_lname + " TEXT, "
            + NewUser.column_gym + " TEXT, " + NewUser.column_fitness + " TEXT, " + NewUser.column_sched + " INTEGER DEFAULT NULL, " + "FOREIGN KEY (" + NewUser.column_sched + ") REFERENCES " + userSchedule.TABLE_NAME + "(" + userSchedule._ID + "));";

    public static final String SQL_CREATE_TIME = "CREATE TABLE " + userSchedule.TABLE_NAME + " ( " + userSchedule._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + userSchedule.column_startHour + " INTEGER DEFAULT 0, " + userSchedule.column_startMin + " INTEGER DEFAULT 0, " + userSchedule.column_endHour + " INTEGER DEFAULT 0, " + userSchedule.column_endMin + " INTEGER DEFAULT 0, " + userSchedule.column_sDay + " INTEGER DEFAULT 0, " +
            userSchedule.column_mDay + " INTEGER DEFAULT 0," + userSchedule.column_tDay + " INTEGER DEFAULT 0, " + userSchedule.column_wDay + " INTEGER DEFAULT 0, " + userSchedule.column_thDay + " INTEGER DEFAULT 0, " + userSchedule.column_fDay + " INTEGER DEFAULT 0, " + userSchedule.column_saDay + " INTEGER DEFAULT 0 " + ");";

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_TIME);

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NewUser.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + userSchedule.TABLE_NAME);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addUsers(Member info) {
        SQLiteDatabase db = this.getWritableDatabase();

        String insert = "INSERT or replace INTO " + NewUser.TABLE_NAME + "(" + NewUser.column_username + ", " + NewUser.column_password + ", " + NewUser.column_fname + ", " + NewUser.column_lname + ", " +
                NewUser.column_gym + ", " + NewUser.column_fitness + ", " + NewUser.column_sched + ") " + "VALUES('"
                + info.getUsername() + "','"
                + info.getPassword() + "','"
                + info.getFname() + "','"
                + info.getLname() + "','"
                + info.getGym() + "','"
                + info.getFitness() + "','"
                + info.getSched_id() + "')";

        db.execSQL(insert);

        db.close();
    }

    public void addSchedule (Schedule info) {
        SQLiteDatabase db = this.getWritableDatabase();

        String insert = "INSERT or replace INTO " + userSchedule.TABLE_NAME + "(" + userSchedule.column_startHour + ", " + userSchedule.column_startMin + ", " + userSchedule.column_endHour + ", " + userSchedule.column_endMin + ", " + userSchedule.column_sDay + ", " + userSchedule.column_mDay + ", " + userSchedule.column_tDay + ", " + userSchedule.column_wDay + ", " + userSchedule.column_thDay
                + ", " + userSchedule.column_fDay + ", " + userSchedule.column_saDay + ")" + "VALUES('"
                + info.getStartHour() + "','"
                + info.getStartMin() + "','"
                + info.getEndHour() + "','"
                + info.getEndMin() + "','"
                + info.getsDay() + "','"
                + info.getmDay() + "','"
                + info.gettDay() + "','"
                + info.getwDay() + "','"
                + info.getThDay() + "','"
                + info.getfDay() + "','"
                + info.getSaDay() + "')";

        db.execSQL(insert);

        db.close();
    }

    public Member getMember(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(NewUser.TABLE_NAME, new String[] {NewUser._ID, NewUser.column_username, NewUser.column_password, NewUser.column_fname, NewUser.column_lname, NewUser.column_gym, NewUser.column_fitness, NewUser.column_sched}, NewUser._ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        Member member = new Member (Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
        db.close();
        cursor.close();

        return member;
    }

    public int getUserId (String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        int id;
        Cursor cursor = db.query(NewUser.TABLE_NAME, new String[] {NewUser._ID, NewUser.column_username, NewUser.column_password, NewUser.column_fname,
                        NewUser.column_lname, NewUser.column_gym, NewUser.column_fitness, NewUser.column_sched},
                NewUser.column_username + "=? AND " + NewUser.column_password + " =?", new String[] {username,
                        password}, null, null, null, null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        Member member = new Member (Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
        id = member.get_id();
        db.close();
        cursor.close();
        return id;
    }

    public int updateSchedule(Schedule schedule) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(userSchedule.column_startHour, schedule.getStartHour());
        values.put(userSchedule.column_startMin, schedule.getStartMin());
        values.put(userSchedule.column_endHour, schedule.getEndHour());
        values.put(userSchedule.column_endMin, schedule.getEndMin());
        values.put(userSchedule.column_sDay, schedule.getsDay());
        values.put(userSchedule.column_mDay, schedule.getmDay());
        values.put(userSchedule.column_tDay, schedule.gettDay());
        values.put(userSchedule.column_wDay, schedule.getwDay());
        values.put(userSchedule.column_thDay, schedule.getThDay());
        values.put(userSchedule.column_fDay, schedule.getfDay());
        values.put(userSchedule.column_saDay, schedule.getSaDay());

        int rowsAffected = db.update(userSchedule.TABLE_NAME, values, userSchedule._ID + "=?", new String[] {String.valueOf(schedule.get_id())});
        db.close();

        return rowsAffected;
    }

    public Schedule getSchedule(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(userSchedule.TABLE_NAME, new String[] {userSchedule._ID, userSchedule.column_startHour, userSchedule.column_startMin, userSchedule.column_endHour, userSchedule.column_endMin,
                userSchedule.column_sDay, userSchedule.column_mDay, userSchedule.column_tDay, userSchedule.column_wDay, userSchedule.column_thDay, userSchedule.column_fDay, userSchedule.column_saDay},
                userSchedule._ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor!=null) {
            cursor.moveToFirst();
        }
        Schedule sched = new Schedule(Integer.parseInt(cursor.getString(0)), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8),
                cursor.getInt(9), cursor.getInt(10), cursor.getInt(11));
        db.close();
        return sched;
    }

    public int getUserCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NewUser.TABLE_NAME, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        return count;
    }

    /*public int findUser(Member info, Schedule schedule) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + NewUser.TABLE_NAME + " , " + userSchedule.TABLE_NAME + " WHERE " + NewUser.)
    }*/

}
