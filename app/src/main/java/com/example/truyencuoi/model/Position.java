package com.example.truyencuoi.model;

import java.util.HashMap;

public class Position {
    private HashMap<Integer,Integer> map;

    public Position(){
        map = new HashMap<>();
    }

    public void putToMap(int k){
        map.put(k,0);
    }

    public boolean isContainsKey(int k){
        if(map.containsKey(k)){
            return true;
        }
        return false;
    }
}
