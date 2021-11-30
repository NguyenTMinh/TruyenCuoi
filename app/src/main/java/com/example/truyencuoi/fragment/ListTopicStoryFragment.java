package com.example.truyencuoi.fragment;

import android.content.Context;
import android.os.Bundle;

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
import com.example.truyencuoi.adapter.TopicStoryAdapter;


public class ListTopicStoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private OnActionClick actionClick;
    private TopicStoryAdapter topicStoryAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_story,container,false);
        recyclerView = view.findViewById(R.id.recycleview_list_topic_story);

        topicStoryAdapter = new TopicStoryAdapter(actionClick.getStory(),container.getContext());
        topicStoryAdapter.setActionClick(actionClick);
        recyclerView.setAdapter(topicStoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(container.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }

}
