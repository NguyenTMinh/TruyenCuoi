package com.example.truyencuoi.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.model.Story;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder> {
    private List<Story> storyList;
    private Context context;
    private OnActionClick actionClick;

    public ViewPagerAdapter(List<Story> storyList, Context context) {
        this.storyList = storyList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fragment_story,parent,false);
        ViewPagerHolder holder = new ViewPagerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {
        Story story = storyList.get(position);
        holder.title.setText(story.getStoryName());
        holder.content.setText(story.getStoryContent());
        if(story.isFavorite()){
            holder.imageViewFav.setImageResource(R.drawable.ic_fav_choose);
        }else{
            holder.imageViewFav.setImageResource(R.drawable.ic_fav_not_choose);
        }
        holder.imageViewFav.setOnClickListener(v -> {
            if(!story.isFavorite()){
                actionClick.addToFav(holder.getAdapterPosition());
                holder.imageViewFav.setImageResource(R.drawable.ic_fav_choose);
                story.setFavorite(true);
            }else{
                actionClick.removeFromFav(story);
                holder.imageViewFav.setImageResource(R.drawable.ic_fav_not_choose);
                story.setFavorite(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }

    public class ViewPagerHolder extends RecyclerView.ViewHolder {
        ImageView imageViewFav;
        TextView title;
        TextView content;

        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFav = itemView.findViewById(R.id.imageview_fav);
            title = itemView.findViewById(R.id.textview_title);
            content = itemView.findViewById(R.id.textview_content_story);
        }
    }
}
