package com.example.volleydemo;

import org.json.JSONArray;
import org.json.JSONObject;

public interface DataListener {
    public void onDataReady(JSONArray jsonArray);
    public void onDataError(String err);
    public void onDataReady(JSONObject jsonObject);
}
