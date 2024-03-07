package com.example.blackdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blackdictionary.DBHelper.DatabaseLogic;
import com.example.blackdictionary.Models.wordModel;

public class search extends AppCompatActivity {
  Context context;
    SearchView txtSearchWord;
    String enWord;
    Cursor c=null;
    private String enDefinition;
    TextView lblEnglish2 = (TextView) findViewById(R.id.lblEnglish2);
    DatabaseLogic myDbHelper;
    SimpleCursorAdapter suggestionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context=this;
        final String[] from=new String[]{"en_word"};
        final int[] to=new int[]{R.id.suggestion_txt};
        suggestionAdapter =new SimpleCursorAdapter(search.this,R.layout.suggestion_row,null,from,to,0){
            public void changeCursor(Cursor cursor){
                super.swapCursor(cursor);
            }
        };
        txtSearchWord.setSuggestionsAdapter(suggestionAdapter);
        txtSearchWord.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                CursorAdapter ca=txtSearchWord.getSuggestionsAdapter();
                Cursor cursor=ca.getCursor();
                cursor.moveToPosition(position);
                @SuppressLint("Range") String clicked_word = cursor.getString(cursor.getColumnIndex("en_word"));
                txtSearchWord.setQuery(clicked_word,false);
                txtSearchWord.clearFocus();
                txtSearchWord.setFocusable(false);
                Intent intent=new Intent(search.this,search.class);
                Bundle bundle=new Bundle();
                bundle.putString("en_word",clicked_word);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });

         txtSearchWord.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 String text=txtSearchWord.getQuery().toString();
                 Cursor c=myDbHelper.getMining(text);
                 if(c.getCount()==0){
                     txtSearchWord.setQuery("",false);
                     Toast.makeText(context, "Word Not Found", Toast.LENGTH_LONG).show();
                 }
                 else {
              txtSearchWord.clearFocus();
              txtSearchWord.setFocusable(false);
                     lblEnglish2.setText(enDefinition);
                 }

                 return false;
             }

             @Override
             public boolean onQueryTextChange(final String s) {
                 txtSearchWord.setIconifiedByDefault(false);
                 Cursor cursorSuggestions=myDbHelper.getSuggestions(s);
                 suggestionAdapter.changeCursor(cursorSuggestions);
                 return false;
             }
         });
    }
    public void showBack(android.view.View button){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void searchSpecificWord(android.view.View button){
        Bundle bundle=getIntent().getExtras();
        enWord=bundle.getString("en_word");
        myDbHelper=new DatabaseLogic(this);
        try {
            myDbHelper.OpenDataBase();
        }catch (SQLException e){
            throw e;
        }
        c=myDbHelper.getMining(enWord);
        if(c.moveToFirst()){

            //enDefinition=c.getString(c.getColumnIndex("en_definition"));


        }}}