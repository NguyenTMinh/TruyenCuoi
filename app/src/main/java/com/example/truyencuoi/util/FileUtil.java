package com.example.truyencuoi.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.truyencuoi.model.Position;
import com.example.truyencuoi.model.Story;
import com.example.truyencuoi.model.TopicStory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileUtil {
    public static final String PATH_DATA_FILE = "data/vn/";
    public static final String PATH_ICON_FILE = "icon/vn/";
    public static final String PATH_FAV_FILE = "data/vn/fav/fav.txt";
    public static final String[] NAME_TOPIC = {"Con gái","Công sở","Con nít","Con trai","Cực hài","Cười 18+","Dân gian","Gia đình","Giao thông",
                                                "Học sinh","Hỏi cho cười","Khoa học","Nghề nghiệp","Người lớn","Nhà hàng","Say xỉn","Tam quốc",
                                                "Tây du kí chế","Thế giới","Thơ ca cười","Thơ vui","Tiếu lâm","Tình yêu","Tôn giáo","Trạng quỳnh",
                                                "Truyện bựa","Việt Nam và thế giới","Vova","Y học"};

    public static final List<TopicStory> readData(Context context){
        List<TopicStory> list = new ArrayList<>();
        Position[] posArr = new Position[NAME_TOPIC.length];
        for (int i = 0; i < posArr.length; i++) {
            posArr[i] = new Position();
        }
        InputStream inputStream;
        File file = context.getExternalFilesDir("fav.txt");
        try {
            file.createNewFile();
            RandomAccessFile reader = new RandomAccessFile(file,"r");
            String line = "";
            String s = "0123456789_";
            while ((line = reader.readLine()) != null) {
                String pos = "";
                for (int i = 0; i < line.length(); i++) {
                    if (s.contains(String.valueOf(line.charAt(i)))) {
                        pos += String.valueOf(line.charAt(i));
                    }
                }
                String[] fav = pos.split("_");
                posArr[Integer.parseInt(fav[0])].putToMap(Integer.parseInt(fav[1]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int singlePos = 0;

        for (int i = 0; i < NAME_TOPIC.length; i++) {
            try {
                try{
                    inputStream = context.getAssets().open(PATH_ICON_FILE+NAME_TOPIC[i]+".PNG");
                }catch (IOException e){
                    try{
                        inputStream = context.getAssets().open(PATH_ICON_FILE+NAME_TOPIC[i]+".png");
                    }catch (IOException e1){
                        inputStream = context.getAssets().open(PATH_ICON_FILE+"vova"+".png");
                    }
                }
                Drawable drawable = Drawable.createFromStream(inputStream,null);
                TopicStory topicStory = new TopicStory(NAME_TOPIC[i],drawable);

                ArrayList<String> arrayList = new ArrayList<>();
                String line = "";

                inputStream = context.getAssets().open(PATH_DATA_FILE+NAME_TOPIC[i]+".txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null){
                    if(!line.equals("','0');")){
                        arrayList.add(line);
                    }else{
                        String content = "";
                        for (int j = 1; j < arrayList.size(); j++) {
                            content+=arrayList.get(j)+"\n";
                        }
                        Story story = new Story(arrayList.get(0),content);
                        if(posArr[i].isContainsKey(singlePos)){
                            story.setFavorite(true);
                        }
                        topicStory.addStory(story);
                        arrayList.clear();
                        singlePos += 1;
                    }
                }
                list.add(topicStory);
            } catch (IOException e) {
                e.printStackTrace();
            }
            singlePos = 0;
        }

        return list;
    }

    public static void saveFavorite(Context context,List<TopicStory> list){
        String s = context.getExternalFilesDir("fav.txt").getAbsolutePath();
        File file = context.getExternalFilesDir("fav.txt");
        file.delete();
        try {
            file.createNewFile();
            RandomAccessFile raf = new RandomAccessFile(file,"rw");
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).getListStory().size(); j++) {
                    if(list.get(i).getListStory().get(j).isFavorite()){
                        raf.writeChars(String.valueOf(i)+"_"+String.valueOf(j)+"\n");
                    }
                }
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
