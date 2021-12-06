package com.example.truyencuoi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.R;
import com.example.truyencuoi.model.Story;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
    private List<Story> list;
    private Context context;

    public ViewPagerAdapter(List<Story> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_detail_story,parent,false);
        ViewPagerViewHolder holder = new ViewPagerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        Story story = list.get(position);
        holder.title.setText(story.getStoryName());
        holder.content.setText(story.getStoryContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewPagerViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textview_title);
            content = itemView.findViewById(R.id.textview_content_story);
        }
    }
}
