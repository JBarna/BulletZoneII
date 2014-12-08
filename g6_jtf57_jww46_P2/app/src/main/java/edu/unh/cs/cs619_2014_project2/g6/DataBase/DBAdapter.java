package edu.unh.cs.cs619_2014_project2.g6.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jeep on 12/5/14.
 */
public class DBAdapter {

    public static final String KEY_ROWID = "_id";
    public static final int COL_ROWID = 0;

    public static final String KEY_DATA = "data";
    public static final String KEY_TANKID = "tankid";

    public static final int COL_DATA = 1;
    public static final int COL_TANKID = 2;


    public final Context context;
    public static final String DATABASE_NAME = "MyDB";
    public static final String DATABASE_TABLE = "mainTable";
    public static final int DATABASE_VERSION = 2;
    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_DATA, KEY_TANKID};
    public static final String DATABASE_CREATE_SQL = "create table " + DATABASE_TABLE + " (" +
            KEY_ROWID + " integer primary key autoincrement, " + KEY_DATA + " text not null, "  +
            KEY_TANKID + " text not null" + ");";
    //private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;
    private DataBaseHelper myDBHelper;
    public int numrows=0;
    public DBAdapter(Context c) {
        this.context = c;
        myDBHelper = new DataBaseHelper(context);
    }

    public DBAdapter open()
    {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        myDBHelper.close();
    }

    public long insertRow(String data, String tankID)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DATA, data);
        initialValues.put(KEY_TANKID, tankID);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }



    public int getNumRows()
    {
        return numrows;
    }
    public Cursor getAllRows()
    {
       String where = null;
       Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
       if(c != null)
       {
          c.moveToFirst();
       }
        return c;
    }

    public void deleteTable()
    {
        db.delete(DATABASE_TABLE, null, null);
        myDBHelper.close();
    }

    public boolean deleteRow(long rowId)
    {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATABASE_TABLE, where, null) != 0;
    }

    public void clear()
    {
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
        if(c.moveToFirst())
        {
            while(c.moveToNext())
            {
                deleteRow(c.getLong((int)rowId));
            }
        }
        c.close();
    }

    public boolean isOpen()
    {
        return db.isOpen();
    }


    private static class DataBaseHelper extends SQLiteOpenHelper {

        DataBaseHelper(Context c) {
            super(c, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            onCreate(db);
        }
    }

}
