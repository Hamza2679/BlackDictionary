package com.example.blackdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blackdictionary.DBHelper.DatabaseLogic;
import com.example.blackdictionary.Models.wordModel;

import java.util.List;

public class searchSimilarity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_similarity);
        context=this;
    }
    public void showBack(android.view.View button){
        Intent intent=new Intent(this,search.class);
        startActivity(intent);

    }
    public void searchSimilarityWord(android.view.View button){
        try{
        EditText txtSearchWord=(EditText) findViewById(R.id.txtSearchWord);
        ListView lstResult=(ListView) findViewById(R.id.lstResult);
        String searchWord=txtSearchWord.getText().toString();
        if(!searchWord.isEmpty()){

            DatabaseLogic myDbHelper=new DatabaseLogic(this);
            Cursor c=myDbHelper.getMining(searchWord);
            final List<wordModel> wordModel= (List<com.example.blackdictionary.Models.wordModel>) myDbHelper.getSuggestions(searchWord);

            WordListAdapter wordListAdapter=new WordListAdapter(this,wordModel);
            lstResult.setAdapter(wordListAdapter);
        }}
        catch (Exception e){
            Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show();
        }


    }
}