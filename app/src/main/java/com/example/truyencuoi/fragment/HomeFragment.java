package com.example.truyencuoi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.Constant;
import com.example.truyencuoi.ItemListener;
import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.adapter.StoryAdapter;
import com.example.truyencuoi.adapter.TopicAdapter;
import com.example.truyencuoi.model.Story;
import com.example.truyencuoi.model.TopicStory;
import com.example.truyencuoi.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemListener {
    private OnActionClick actionClick;
    private RecyclerView recyclerViewTopic;
    private TopicAdapter adapterTopic;
    private List<TopicStory> listTopic;
    private DrawerLayout drawerLayout;

    private RecyclerView recyclerViewSinlge;
    private StoryAdapter adapterSingle;
    private List<Story> listSingle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //create view navigation
        listTopic = new ArrayList<>();
        FileUtil.readTopic(getContext(),listTopic);
        adapterTopic = new TopicAdapter(listTopic,getContext());
        adapterTopic.setItemListener(this);
        recyclerViewTopic = view.findViewById(R.id.recycleview_list_topic_story);
        recyclerViewTopic.setAdapter(adapterTopic);
        recyclerViewTopic.setLayoutManager(new LinearLayoutManager(getContext()));
        drawerLayout = view.findViewById(R.id.drawer_layout);
        ImageView toggle = view.findViewById(R.id.bt_toggle);
        toggle.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        //create view list
        if(listSingle == null){
            listSingle = new ArrayList<>();
        }
        recyclerViewSinlge = view.findViewById(R.id.recycle_view_story);
        adapterSingle = new StoryAdapter(listSingle,getContext());
        adapterSingle.setListener(this);
        recyclerViewSinlge.setAdapter(adapterSingle);
        recyclerViewSinlge.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSinlge.setHasFixedSize(true);
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }

    @Override
    public void onClick(int key,Object data) {
        switch (key){
            case Constant.KEY_GET_STORY:{
                listSingle.clear();
                FileUtil.readStory(getContext(),listSingle, (String) data);
                adapterSingle.notifyDataSetChanged();
                recyclerViewSinlge.scrollToPosition(0);
                drawerLayout.closeDrawers();
                break;
            }
            case Constant.KEY_STORY:{
                actionClick.callBack(key,listSingle,data);
            }
        }
    }

}
