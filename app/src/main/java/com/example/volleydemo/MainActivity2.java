package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList info;
    private Button btnBack,btnDelete,btnUpdate;
    private TextView txt;
    private String name;
    private int Id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnback);
        txt = findViewById(R.id.txt1);

        Intent intent = getIntent();
         name = intent.getStringExtra("fromMain");
         Id = Integer.parseInt(name.replaceAll("[^0-9]", ""));
        txt.setText(name);

        onload();
        btnBack.setOnClickListener(v ->{
            intent.putExtra("msg","to main from second");
            setResult(AppConstants.RESULT_CODE_SECOND, intent);
            finish();
        });

        DataService dataservice = new DataService(this);
        btnDelete.setOnClickListener(v ->{

                dataservice.deletePerson(Id, new DataListener() {
                    @Override
                    public void onDataReady(JSONArray jsonArray) {


                    }

                    @Override
                    public void onDataError(String err) {
                        Toast.makeText(MainActivity2.this, "Person Deleted" + err, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDataReady(String string) {

                    }

                    @Override
                    public void onDataReady(JSONObject jsonObject) {






                            //recreate();
                            //Toast.makeText(MainActivity2.this, "Person: " + name +" Deleted", Toast.LENGTH_SHORT).show();

                    }
                });


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
            public void onDataReady(String string) {

            }

            @Override
            public void onDataReady(JSONObject jsonObject) {

                try {

                    Id = jsonObject.getInt("persId");
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