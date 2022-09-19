package com.example.asapd;

import java.util.ArrayList;

public class StoreData {
    int resId; //이미지 데이터
    String name;
    String content;

    public StoreData(int resId, String title, String content) {
        this.resId = resId;
        this.name= title;
        this.content = content;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public static ArrayList<StoreData> createList(int num){
        ArrayList<StoreData> list = new ArrayList<StoreData>();

        for(int i = 1; i <= num; i++){
            list.add(new StoreData(R.drawable.store,"item", "content"));
        }
        return list;
    }
}
