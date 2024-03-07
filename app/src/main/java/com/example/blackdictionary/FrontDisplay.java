package com.example.blackdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.blackdictionary.DBHelper.DatabaseLogic;

public class FrontDisplay extends AppCompatActivity {
    private ProgressBar progressBar;
    static DatabaseLogic myDbHelper;
    static boolean databaseOpened=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDbHelper=new DatabaseLogic(this);
        if(myDbHelper.checkDataBase()){
            openDatabase();
        }else {
            LoadDataBaseAsync task=new LoadDataBaseAsync(FrontDisplay.this);
            task.execute();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_display);
        progressBar=findViewById(R.id.progressBar);
        final Context context=this;
        Thread timeThread=new Thread(){
            public void run(){
                try{sleep(4000);
            }
                catch (InterruptedException e){
                e.printStackTrace();}
                finally {
                   // setupWords(context);
                    finish();
                    Intent intent=new Intent(context,MainActivity.class);
                    startActivity(intent);
                }
            }
        };timeThread.start();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    progressBar.incrementProgressBy(10);
                    SystemClock.sleep(500);
                }
            }
        });thread.start();
    }
   protected static void openDatabase(){
        try {
            myDbHelper.OpenDataBase();
            databaseOpened=true;
        }catch (SQLException e){
            e.printStackTrace();
        }
   }
}