package com.example.blackdictionary.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.blackdictionary.Models.wordModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.Synchronized;

public class DatabaseLogic extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "end_dictionary.db";
    private String DB_PATH = "null";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseLogic(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "database/";
        Log.e("path 1", DB_PATH);

    }

    public void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutPut = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutPut.write(buffer, 0, length);
        }
        myOutPut.flush();
        myOutPut.close();
        myInput.close();
        Log.i("copyDataBase", "Database copied");
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

        }
        if (checkDB != null) {
            checkDB.close();

        }
        return checkDB != null ? true : false;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    public void OpenDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
            super.close();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            this.getReadableDatabase();
            myContext.deleteDatabase(DB_NAME);
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cursor getMining(String text) {
        Cursor c = myDataBase.rawQuery(" SELECT en_definition From words WHERE en_word==UPPER('" + text + "')", null);
        return c;
    }

    public Cursor getSuggestions(String text) {
        Cursor c = myDataBase.rawQuery(" SELECT _id, en_word From words WHERE en_word LIKE'" + text + "%' LIMIT 60", null);
        return c;

    }
}