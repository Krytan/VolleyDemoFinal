package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList info;
    private Button btnBack;
    private TextView txt;
    private String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnBack = findViewById(R.id.btnback);
        txt = findViewById(R.id.txt1);

        Intent intent = getIntent();
         name = intent.getStringExtra("fromMain");
        txt.setText(name);

        onload();
        btnBack.setOnClickListener(v ->{
            intent.putExtra("msg","to main from second");
            setResult(AppConstants.RESULT_CODE_SECOND, intent);
            finish();
        });

    }
    public void onload(){

        DataService dataService = new DataService(this);
        dataService.getFruit(name, new DataListener() {
            @Override
            public void onDataReady(JSONArray jsonArray) {

            }

            @Override
            public void onDataError(String err) {

            }

            @Override
            public void onDataReady(JSONObject jsonObject) {

                try {
                    String data = "Name: "+ jsonObject.getString("firstName") + "\nGenus: " + jsonObject.getString("lastName") + "\nFamily: " + jsonObject.getString("family");
                    //dropdawn.setText(data);
                    txt.setText(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}