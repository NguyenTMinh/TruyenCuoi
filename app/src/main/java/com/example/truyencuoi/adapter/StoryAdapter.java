package com.example.truyencuoi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.ITranData;
import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> implements ITranData {
    private List<Story> listStory;
    private Context context;
    private OnActionClick actionClick;
    private int pos;

    public StoryAdapter(List<Story> listStory, Context context) {
        this.listStory = listStory;
        this.context = context;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewStory = layoutInflater.inflate(R.layout.item_single_story,parent,false);
        StoryViewHolder viewHolder = new StoryViewHolder(viewStory);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = listStory.get(position);
        holder.textView.setText(story.getStoryName());
        holder.imageView.setImageResource(R.drawable.icon_smile);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = holder.getAdapterPosition();
                actionClick.changeListViewpager(listStory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listStory.size();
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }

    @Override
    public int getPos() {
        return pos;
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public TextView textView;
        LinearLayout linearLayout;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_smile);
            linearLayout = itemView.findViewById(R.id.layout_single_item);
            textView = itemView.findViewById(R.id.textview_title_story);
        }
    }

    public void setListStory(List<Story> listStory) {
        this.listStory = listStory;
    }
}
