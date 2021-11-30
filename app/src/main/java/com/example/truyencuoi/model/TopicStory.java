package com.example.truyencuoi.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;

import java.util.ArrayList;
import java.util.List;

public class TopicStory {
    private String topic;
    private Drawable icon;
    private List<Story> listStory;

    public TopicStory(String topic, Drawable icon){
        this.topic = topic;
        this.icon = icon;
        listStory = new ArrayList<>();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public List<Story> getListStory() {
        return listStory;
    }

    public void setListStory(List<Story> listStory) {
        this.listStory = listStory;
    }

    public void addStory(Story story){
        listStory.add(story);
    }
}
