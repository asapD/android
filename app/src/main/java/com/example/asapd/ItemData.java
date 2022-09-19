package com.example.asapd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemData {
    int resId; //이미지 데이터
    String name;
    String content;

    public ItemData(int resId, String title, String content) {
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

    public static ArrayList<ItemData> createList(int num){
        ArrayList<ItemData> list = new ArrayList<ItemData>();

        for(int i = 1; i <= num; i++){
            list.add(new ItemData(R.drawable.item,"item" + i, "content"));
        }
        return list;
    }
}