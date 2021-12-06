package com.example.truyencuoi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.Constant;
import com.example.truyencuoi.ItemListener;
import com.example.truyencuoi.R;
import com.example.truyencuoi.model.TopicStory;

import org.w3c.dom.Text;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    private List<TopicStory> list;
    private Context context;
    private ItemListener itemListener;

    public TopicAdapter(List<TopicStory> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nav_item,parent,false);
        TopicViewHolder holder = new TopicViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        TopicStory topicStory = list.get(position);
        holder.imageView.setImageDrawable(topicStory.getIcon());
        holder.textView.setText(topicStory.getTopic());
        holder.name = topicStory.getTopic();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TableRow tableRow;
        String name;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            name = "";
            imageView = itemView.findViewById(R.id.category_img);
            textView = itemView.findViewById(R.id.category_text);
            tableRow = itemView.findViewById(R.id.category_container);
            tableRow.setOnClickListener(v -> {
                itemListener.onClick(Constant.KEY_GET_STORY,name);
            });
        }
    }
}
