package com.example.blackdictionary.Models;

import java.security.PrivateKey;

public class wordModel {
    private String EnglishWord;
    private String EnglishWord2;
    public wordModel(){


    }
    public wordModel(String englishWord,String englishWord2){
        this.EnglishWord=englishWord;
        this.EnglishWord2=englishWord2;
    }
    public String getEnglishWord() {
        return EnglishWord;
    }

    public String getEnglishWord2() {
        return EnglishWord2;
    }



    public void setEnglishWord(String englishWord) {
        EnglishWord = englishWord;
    }

    public void setEnglishWord2(String englishWord2) {
        EnglishWord2= englishWord2;
    }


}