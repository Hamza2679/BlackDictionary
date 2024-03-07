package com.example.blackdictionary;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.blackdictionary.Models.wordModel;

import java.util.List;

public class WordListAdapter extends BaseAdapter {
    private Activity context_1;
    private List<wordModel> wordModels;
    public WordListAdapter(Activity context,List<wordModel> wordModels){
        context_1=context;
       this.wordModels=wordModels;
    }

    @Override
    public int getCount() {
        return wordModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView lblEnglish,lblEnglish2;
        if(convertView==null){
            convertView= LayoutInflater.from(context_1).inflate(R.layout.word_list,null);
            lblEnglish2=(TextView) convertView.findViewById(R.id.lblEnglish2);
            lblEnglish=(TextView) convertView.findViewById(R.id.lblEnglish);
            lblEnglish2.setText("Meaning  "+wordModels.get(position).getEnglishWord2());
            lblEnglish.setText("Word   "+wordModels.get(position).getEnglishWord());
        }
        return convertView;
    }
}
