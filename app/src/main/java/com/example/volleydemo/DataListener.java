package com.example.volleydemo;

import org.json.JSONArray;
import org.json.JSONObject;

public interface DataListener {
     void onDataReady(JSONArray jsonArray);
     void onDataError(String err);
     void onDataReady(String string);
     void onDataReady(JSONObject jsonObject);
}
