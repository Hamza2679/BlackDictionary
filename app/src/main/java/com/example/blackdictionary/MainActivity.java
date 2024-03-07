package com.example.blackdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.blackdictionary.DBHelper.DatabaseLogic;
import com.example.blackdictionary.Models.wordModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lstWords;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        try {
            DatabaseLogic databaseLogic=new DatabaseLogic(this);
            lstWords=(ListView)findViewById(R.id.listWords);
        }
        catch (Exception e){
            Toast.makeText(context, "Error occurred restart the app", Toast.LENGTH_SHORT).show();
        }

    }
    public void showSearch(android.view.View button){
        Intent intent=new Intent(this,search.class);
        startActivity(intent);

    }
    public void showHistory(android.view.View button){
        Intent intent=new Intent(this,Resent.class);
        startActivity(intent);
    }
    public void showFavorite(android.view.View button){
        Intent intent=new Intent(this,favorite.class);
        startActivity(intent);

    }
    public void showDeveloper(android.view.View button){
        Intent intent=new Intent(this,develop.class);
        startActivity(intent);

    }
    public void showAbout(android.view.View button){
        Intent intent=new Intent(this,about.class);
        startActivity(intent);

    }

}