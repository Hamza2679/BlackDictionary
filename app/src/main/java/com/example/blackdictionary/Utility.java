package com.example.blackdictionary;

import android.content.Context;
import android.widget.Toast;

import com.example.blackdictionary.DBHelper.DatabaseLogic;
import com.example.blackdictionary.Models.wordModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility {
    private String ReadFromFile(Context context,String filename){
//        BufferedReader reader=null;
//        StringBuilder returnString= new StringBuilder();
//        try
//        {
//            reader=new BufferedReader(new InputStreamReader(context.getAssets().open(filename),"UTF-16LE"));
//            String mLine;
//            while ((mLine=reader.readLine())!=null){
//                returnString.append(mLine);
//            }
//            return returnString.toString();
//        }
//        catch (IOException e){
//            return returnString.toString();
//        }
//        finally {
//            if (reader!=null){
//                try {
//                    reader.close();
//                }
//                catch (IOException e){
//
//                }
//            }
//        }
//    }
//    public void saveWordsFromFile(Context context,String filename){
//        try {
//            String words=ReadFromFile(context,filename);
//            String [] AllWords=words.split("@@");
//            DatabaseLogic dbHelper=new DatabaseLogic(context);
//            for (int i=0;i<AllWords.length;i++){
//                String[] singleWord=AllWords[i].split("@");
//                if(!singleWord[0].trim().equals("")){
//                    wordModel wordModel=new wordModel();
//                    wordModel.setEnglishWord(singleWord[0]);
//                    wordModel.setEnglishWord2(singleWord[1]);
//                    dbHelper.addNewWord(wordModel);
//                }
//            }
//        }
//        catch (Exception e){
//            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//        }
        return null;
    }
}
