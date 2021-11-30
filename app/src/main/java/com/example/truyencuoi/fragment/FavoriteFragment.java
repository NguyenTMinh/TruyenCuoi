package com.example.truyencuoi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.adapter.StoryAdapter;
import com.example.truyencuoi.model.Story;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Story> stories;
    private StoryAdapter adapter;
    private OnActionClick actionClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_story,container,false);
        recyclerView = view.findViewById(R.id.recycleview_list_single_story);
        if(adapter == null){
            if(stories == null){
                stories = new ArrayList<>();
            }
            adapter = new StoryAdapter(stories,container.getContext());
            adapter.setActionClick(actionClick);
        }else{
            adapter.notifyDataSetChanged();
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    public void addFavStory(Story story){
        if(stories == null){
            stories = new ArrayList<>();
            stories.add(story);
        }else{
            stories.add(story);
        }
    }

    public void removeFavStory(Story story){
        stories.remove(story);
    }

    public StoryAdapter getAdapter() {
        return adapter;
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }
}
