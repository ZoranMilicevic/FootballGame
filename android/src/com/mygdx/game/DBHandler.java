package com.mygdx.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.badlogic.gdx.Gdx;

/**
 * Created by Zoran Milicevic on 3/17/2018.
 */

public class DBHandler extends SQLiteOpenHelper{

    public static final int DATABASE_VERISON=1;
    public static final String DATABASE_NAME="scores.db";
    public static final String TABLE_NAME="scores";
    public static final String COLUMN_ID="_id";
    public static final String COlUMN_FIRST_PLAYER="_first_player";
    public static final String COLUMN_SECOND_PLAYER="_seconds_player";
    public static final String COLUMN_GOALS_FP="_goals_fp";
    public static final String COLUMN_GOALS_SP="_goals_sp";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERISON);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COlUMN_FIRST_PLAYER + " TEXT," +
                COLUMN_SECOND_PLAYER + " TEXT," +
                COLUMN_GOALS_FP + " INT," +
                COLUMN_GOALS_SP + " INT"
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void add(Result r){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COlUMN_FIRST_PLAYER, r.getNameAI());
        values.put(COLUMN_SECOND_PLAYER, r.getNameHuman());
        values.put(COLUMN_GOALS_FP, r.getAI());
        values.put(COLUMN_GOALS_SP, r.getHuman());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String toString(){
        String str="";
        String pom;
        SQLiteDatabase db= getWritableDatabase();
        String query="SELECT * FROM " + TABLE_NAME;

        Cursor c=db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            pom=c.getString(c.getColumnIndex(COlUMN_FIRST_PLAYER));
            if(pom!=null){
                str=str + pom + " ";
            }
            else str+="Empty Field!!! ";

            c.moveToNext();
            pom=c.getString(c.getColumnIndex(COLUMN_SECOND_PLAYER));
            if(pom!=null){
                str=str + pom + " ";
            }
            else str+="Empty Field!!! ";

            c.moveToNext();
            pom=c.getString(c.getColumnIndex(COLUMN_GOALS_FP));
            if(pom!=null){
                str=str + pom + " ";
            }
            else str+="Empty Field!!! ";

            c.moveToNext();
            pom=c.getString(c.getColumnIndex(COLUMN_GOALS_SP));
            if(pom!=null){
                str=str + pom + " ";
            }
            else str+="Empty Field!!! ";

        }
        return str;
    }
}
