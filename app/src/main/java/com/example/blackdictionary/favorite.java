package com.example.blackdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class favorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
    }
    public void showBack(android.view.View button){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}