package com.example.wishlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class Mydb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="MyDatabase.db";
    public static  String TABLE_NAME = null;

    public Mydb(Context context) {
        super(context, DATABASE_NAME, null, 3);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tv1 = "CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "'  (id INTEGER PRIMARY KEY AUTOINCREMENT ,item Text,price INTEGER,color Text)";
        db.execSQL(tv1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            String tv1 = "CREATE TABLE IF NOT EXISTS '" + TABLE_NAME + "'  (id INTEGER PRIMARY KEY AUTOINCREMENT ,item Text,price INTEGER,color Text)";
            db.execSQL(tv1);

    }

    static public void setTableName (String tn)
    {
        TABLE_NAME=tn;
    }

    void insert(String item, int price)
    {

        ContentValues contentValues=new ContentValues();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        onCreate(sqLiteDatabase);

        contentValues.put("item",item);
        contentValues.put("price",price);
        contentValues.put("color","white");

        sqLiteDatabase.insert(TABLE_NAME ,null ,contentValues);
    }

    void update(String item,int price,String id){
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        contentValues.put("item",item);
        contentValues.put("price",price);

        sqLiteDatabase.update(TABLE_NAME,contentValues,"id='"+id+"'",null);
    }

    boolean UpdateColor(String id){
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        contentValues.put("color","red");

        return sqLiteDatabase.update(TABLE_NAME,contentValues,"id='"+id+"'",null)>0;
    }

    boolean delete (String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

       return sqLiteDatabase.delete(TABLE_NAME,"id='"+id+"'",null)>0;
    }

    Object[] search ()
    {

            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM '" + TABLE_NAME + "' ", null);

            String[] i = new String[cursor.getCount()];
            int[] p = new int[cursor.getCount()];
            String[] ps = new String[cursor.getCount()];

            int[] id=new int[cursor.getCount()];
            String[] ids=new String[cursor.getCount()];
            String[] RedColor=new String[cursor.getCount()];

            Object[] objects = new Object[4];
            int count = 0;

            while (cursor.moveToNext()){
                    i[count] = cursor.getString(1);
                    p[count] = cursor.getInt(2);
                    ps[count] = String.valueOf(p[count]);

                    id[count]=cursor.getInt(0);
                    ids[count]=String.valueOf(id[count]);

                    RedColor[count]=cursor.getString(3);

                    count++;
            }

            objects[0] = i;
            objects[1] = ps;

            objects[2]=ids;
            objects[3]=RedColor;
            Log.d("CURSOR", String.valueOf(cursor.getCount()));
            return objects;
    }

    String[] tablelist ()
    {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master order by rootpage asc", null);
        cursor.moveToFirst();
        String[] i=new String[cursor.getCount()];
        int count = 0;
        while (!cursor.isAfterLast()) {
            i[count] = cursor.getString(0);
            count++;
            cursor.moveToNext();
        }

        return i;
    }
}
