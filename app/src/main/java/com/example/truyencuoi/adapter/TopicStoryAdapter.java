package com.example.truyencuoi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.fragment.ListSingleStoryFragment;
import com.example.truyencuoi.model.TopicStory;

import java.util.List;

public class TopicStoryAdapter extends RecyclerView.Adapter<TopicStoryAdapter.TopicStoryViewHolder>{
    private List<TopicStory> topicStoryList;
    private Context context;
    private OnActionClick actionClick;

    public TopicStoryAdapter(List<TopicStory> topicStoryList, Context context) {
        this.topicStoryList = topicStoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public TopicStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_topic_story,parent,false);
        TopicStoryViewHolder holder = new TopicStoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicStoryViewHolder holder, int position) {
        TopicStory topicStory = topicStoryList.get(position);
        holder.imageView.setImageDrawable(topicStory.getIcon());
        holder.textViewTopic.setText(topicStory.getTopic());
        holder.textViewNum.setText(String.valueOf(topicStory.getListStory().size()));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick.changeListSingleFrag(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicStoryList.size();
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }

    public class TopicStoryViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView textViewTopic;
        TextView textViewNum;

        public TopicStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layout_topic_item);
            imageView = itemView.findViewById(R.id.imageview_icon_topic);
            textViewTopic = itemView.findViewById(R.id.textview_topic_story);
            textViewNum = itemView.findViewById(R.id.textview_topic_story_num);
        }

    }
}
