package com.example.blackdictionary;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.system.ErrnoException;
import android.text.PrecomputedText;
import android.view.LayoutInflater;
import android.view.View;
import com.example.blackdictionary.DBHelper.DatabaseLogic;

import java.io.IOException;

public class LoadDataBaseAsync extends AsyncTask<Void,Void, Boolean> {
    private Context context;
    private AlertDialog alertDialog;
    private DatabaseLogic myDbHelper;
    public LoadDataBaseAsync(Context context){
   this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        AlertDialog.Builder d=new AlertDialog.Builder(context,R.style.MyDialogTheme);
        LayoutInflater inflater=LayoutInflater.from(context);
        View dialogView=inflater.inflate(R.layout.activity_front_display,null);
        d.setTitle("Loading database...........");
        d.setView(dialogView);
        alertDialog=d.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        myDbHelper=new DatabaseLogic(context);
        try {
            myDbHelper.createDataBase();
        }
        catch (IOException e){
            throw new Error("Database was not created");
        }
        myDbHelper.close();
        return null;
    }

    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean b) {
        super.onPostExecute(b);
        alertDialog.dismiss();
        FrontDisplay.openDatabase();
    }
}
