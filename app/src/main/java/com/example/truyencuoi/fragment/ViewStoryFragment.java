package com.example.truyencuoi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.truyencuoi.R;
import com.example.truyencuoi.adapter.ViewPagerAdapter;
import com.example.truyencuoi.model.Story;

import java.util.ArrayList;
import java.util.List;

public class ViewStoryFragment extends Fragment {
    private ViewPager2 viewPager2;
    private ViewPagerAdapter adapter;
    private List<Story> list;
    private int currentStoryPos;

    public ViewStoryFragment(List<Story> story,int pos){
        super();
        currentStoryPos = pos;
        list = new ArrayList<>(story);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewpager_read_story,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView = view.findViewById(R.id.iv_back_viewpage);
        imageView.setOnClickListener(v -> requireActivity().onBackPressed());
        viewPager2 = view.findViewById(R.id.viewpager_story);
        adapter = new ViewPagerAdapter(list,getContext());
        viewPager2.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager2.setCurrentItem(currentStoryPos);
            }
        },100);
        TextView pageNum = view.findViewById(R.id.tv_num_page);
        String titleNum = currentStoryPos+1 + "/" + list.size();
        pageNum.setText(titleNum);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                String titleNum = position+1 + "/" + list.size();
                pageNum.setText(titleNum);
            }
        });

    }
}
