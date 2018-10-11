package com.kvikesh800gmail.relativlayoutjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyQuestion.db";
   //table name
   public static final String TABLE_NAME = "question";
    public static final String COLUMN_ID = "id";
    //for table column
    public static final String COLUMN_QUEDTION = "question";
    public static final String COLUMN_OPTIONA = "optionA";
    public static final String COLUMN_OPTIONB= "optionB";
    public static final String CONTACTS_COLUMN_OPTIONC = "optionC";
    public static final String CONTACTS_COLUMN_OPTOIND = "optionD";
    public static final String CONTACTS_COLUMN_ANSWER = "answer";
    private HashMap hp;

    public QuestionDatabase(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " +TABLE_NAME+
                        "("+COLUMN_ID+" integer primary key, "+COLUMN_QUEDTION+" text,"+COLUMN_OPTIONA+" text,"+COLUMN_OPTIONB+" text,"+CONTACTS_COLUMN_OPTIONC+" text, "+CONTACTS_COLUMN_OPTOIND+" text,"+CONTACTS_COLUMN_ANSWER+" text)"
        );
        insertQuestion("2 + 2 = ?","2","4","8","10","B");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertQuestion (String question, String optionA, String optionB, String optionC,String optionD, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUEDTION, question);
        contentValues.put(COLUMN_OPTIONA, optionA);
        contentValues.put(COLUMN_OPTIONB, optionB);
        contentValues.put(CONTACTS_COLUMN_OPTIONC, optionC);
        contentValues.put(CONTACTS_COLUMN_OPTOIND, optionD);
        contentValues.put(CONTACTS_COLUMN_ANSWER,answer);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
/*
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }
*/


    public ArrayList<Question> getAllQuestion() {
        ArrayList<Question> array_list = new ArrayList<Question>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();
//table sy utha kr obect me save kry ga
        while(res.isAfterLast() == false){
            Question q=new Question();
            q.setQuestion(res.getString(res.getColumnIndex(COLUMN_QUEDTION)));
            q.setOptionA(res.getString(res.getColumnIndex(COLUMN_OPTIONA)));
            q.setOptionB(res.getString(res.getColumnIndex(COLUMN_OPTIONB)));
            q.setOptionC(res.getString(res.getColumnIndex(CONTACTS_COLUMN_OPTIONC)));
            q.setOptionD(res.getString(res.getColumnIndex(CONTACTS_COLUMN_OPTOIND)));
            q.setAnswer(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ANSWER)));

            array_list.add(q);
            res.moveToNext();
        }
        return array_list;
    }
}