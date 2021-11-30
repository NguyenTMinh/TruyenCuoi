package com.example.truyencuoi.model;

import android.content.Context;

import com.example.truyencuoi.model.TopicStory;
import com.example.truyencuoi.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class Stories {
    private List<TopicStory> topicStoryList;

    public Stories(Context context){
        topicStoryList = FileUtil.readData(context);
    }

    public List<TopicStory> getTopicStoryList() {
        return topicStoryList;
    }

    public void setTopicStoryList(List<TopicStory> topicStoryList) {
        this.topicStoryList = topicStoryList;
    }
}
