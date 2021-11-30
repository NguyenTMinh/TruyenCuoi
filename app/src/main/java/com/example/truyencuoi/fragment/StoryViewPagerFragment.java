package com.example.truyencuoi.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyencuoi.ITranData;
import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.adapter.ViewPagerAdapter;
import com.example.truyencuoi.model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryViewPagerFragment extends Fragment {
    private OnActionClick actionClick;
    private boolean isReturnFrom = false;
    private List<Story> stories;
    private  ViewPagerAdapter adapter;
    private  ViewPager2 viewPager2;
    private ITranData tranData;
    private ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager_read_story,container,false);
        viewPager2 = view.findViewById(R.id.viewpager_story);
        if(adapter == null){
            adapter = new ViewPagerAdapter(stories,getContext());
            adapter.setActionClick(actionClick);
        }else{
            adapter.notifyDataSetChanged();
        }
        viewPager2.setAdapter(adapter);
        viewPager2.setCurrentItem(tranData.getPos());
        isReturnFrom = true;

        imageView = view.findViewById(R.id.imageview_icon_back);
        imageView.setOnClickListener(v -> getActivity().onBackPressed());

        return view;
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }

    public boolean isReturnFrom() {
        return isReturnFrom;
    }

    public void setReturnFrom(boolean returnFrom) {
        isReturnFrom = returnFrom;
    }

    public void setStories(List<Story> stories) {
        if(this.stories != null){
            this.stories.clear();
            this.stories.addAll(stories);
        }else{
            this.stories = new ArrayList<>(stories);
        }
    }

    public void setTranData(ITranData tranData) {
        this.tranData = tranData;
    }
}
