package com.example.truyencuoi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.adapter.StoryAdapter;
import com.example.truyencuoi.model.Story;

import java.util.ArrayList;
import java.util.List;

public class ListSingleStoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private OnActionClick actionClick;
    private StoryAdapter storyAdapter;
    private List<Story> stories;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_story,container,false);
        recyclerView = view.findViewById(R.id.recycleview_list_single_story);
        if(storyAdapter == null){
            storyAdapter = new StoryAdapter(stories,container.getContext());
            storyAdapter.setActionClick(actionClick);
        }else{
            storyAdapter.notifyDataSetChanged();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(!actionClick.isReturnFromSVPF()){
            recyclerView.smoothScrollToPosition(0);
        }
        recyclerView.setAdapter(storyAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        return view;
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }

    public void setStories(List<Story> stories) {
        if(this.stories != null){
            this.stories.clear();
            this.stories.addAll(stories);
        }else{
            this.stories = new ArrayList<>(stories);
        }
    }

    public List<Story> getStories() {
        return stories;
    }

    public StoryAdapter getStoryAdapter() {
        return storyAdapter;
    }
}
