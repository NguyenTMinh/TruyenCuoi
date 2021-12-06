package com.example.truyencuoi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.truyencuoi.Constant;
import com.example.truyencuoi.model.Story;
import com.example.truyencuoi.model.TopicStory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static final void readTopic(Context context,List<TopicStory> list){
        try {
            String[] files = context.getAssets().list(Constant.PATH_DATA_FILE);
            for (int i = 0; i < files.length; i++) {
                files[i] = files[i].replace(".txt","");
                InputStream inputStream = context.getAssets().open(Constant.PATH_ICON_FILE+"/"+files[i]+".png");
                Drawable drawable = Drawable.createFromStream(inputStream,null);
                TopicStory temp = new TopicStory(files[i],drawable);
                list.add(temp);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void readStory(Context context, List<Story> list, String fileName) {
        try {
            InputStream inputStream = context.getAssets().open(Constant.PATH_DATA_FILE + "/" + fileName + ".txt");
            Log.e("e", "a");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            ArrayList<String> array = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.equals("','0');")) {
                    String content = "";
                    for (int j = 1; j < array.size(); j++) {
                        content += array.get(j) + "\n";
                    }
                    Story story = new Story(array.get(0), content);
                    //boolean state fav of item

                    list.add(story);
                    array.clear();
                } else {
                    array.add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
