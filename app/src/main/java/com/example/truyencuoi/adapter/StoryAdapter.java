package com.example.truyencuoi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.Constant;
import com.example.truyencuoi.ItemListener;
import com.example.truyencuoi.R;
import com.example.truyencuoi.model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder>{
    private List<Story> listStory;
    private Context context;
    private ItemListener listener;

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
        holder.pos = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return listStory.size();
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;
        int pos;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_smile);
            linearLayout = itemView.findViewById(R.id.layout_single_item);
            textView = itemView.findViewById(R.id.textview_title_story);
            linearLayout.setOnClickListener(v -> {
                listener.onClick(Constant.KEY_STORY,pos);
            });
        }
    }
}
