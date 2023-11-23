package com.example.joyjourney.model;

import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Pesanan {
    public String name;

    public Pesanan(String name) {
        this.name = name;
    }

    @Exclude
    public Map<String, Object> toMap(){
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("nama", name);

        return userMap;
    }
}
