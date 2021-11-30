package com.example.truyencuoi;

import com.example.truyencuoi.fragment.ListSingleStoryFragment;
import com.example.truyencuoi.model.Story;
import com.example.truyencuoi.model.TopicStory;

import java.util.List;

public interface OnActionClick {
    void callBack();
    List<TopicStory> getStory();
    ListSingleStoryFragment getListSingleFrag();
    void changeListSingleFrag(int pos);
    void changeListViewpager(List<Story> list);
    boolean isReturnFromSVPF();
    void addToFav(int pos);
    void removeFromFav(Story story);
}
