package com.example.asapd;

import java.util.ArrayList;

public class ItemData {

    String name;
    String content;

    public ItemData(String title, String content) {
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

    public static ArrayList<ItemData> createList(int num){
        ArrayList<ItemData> list = new ArrayList<ItemData>();

        for(int i = 1; i <= num; i++){
            list.add(new ItemData("item", "content"));
        }
        return list;
    }
}
