package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList info;
    private Button btnBack, btnDelete, btnUpdate;
    private TextView txt, lastUpdated;
    private String name;
    private EditText firstName, lastName;
    private CheckBox student;
    private int Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnDelete = findViewById(R.id.btnDelete);
        lastUpdated = findViewById(R.id.lastupdated);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnback);
        firstName = findViewById(R.id.editfirstname);
        lastName = findViewById(R.id.editlastname);
        student = findViewById(R.id.studentbox);
        txt = findViewById(R.id.txt1);

        Intent intent = getIntent();
        name = intent.getStringExtra("fromMain");
        Id = Integer.parseInt(name.replaceAll("[^0-9]", ""));
        txt.setText(name);

        onload();
        btnBack.setOnClickListener(v -> {
            intent.putExtra("msg", "to main from second");
            setResult(AppConstants.RESULT_CODE_SECOND, intent);
            finish();
        });

        DataService dataservice = new DataService(this);

        btnUpdate.setOnClickListener(v -> {
            JSONObject json = new JSONObject();
            try {

                json.put("firstName", firstName.getText().toString());
                json.put("lastName", lastName.getText().toString());
                json.put("student", student.isChecked());

                dataservice.updatedPerson(Id,json, new DataListener() {
                    @Override
                    public void onDataReady(JSONArray jsonArray) {


                    }

                    @Override
                    public void onDataError(String err) {
                        Toast.makeText(MainActivity2.this, "Error:  " + err, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDataReady(String string) {

                    }

                    @Override
                    public void onDataReady(JSONObject jsonObject) {

                        String Stringfname = firstName.getText().toString();
                        String Stringlname = lastName.getText().toString();
                        String name = null;

                        if (Stringfname.matches("") || Stringlname.matches("")) {
                            Toast.makeText(MainActivity2.this, "You NEEED to type firstname and lastname", Toast.LENGTH_SHORT).show();
                        } else {

                            try {

                                String data = " ID: " + jsonObject.getString("persId") + "\n Name: " + jsonObject.getString("firstName") + "\n Last name : " + jsonObject.getString("lastName") + "\n Student: " + jsonObject.getString("student") + "\n Last Updated : " + jsonObject.getString("lastUpdated");
                                name = jsonObject.getString("firstName");
                                lastUpdated.setText(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //recreate();
                            Toast.makeText(MainActivity2.this, "Person: " + name + " updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (JSONException e) {
                Log.d("Err_LOG", e.getMessage());
            }


        });


        btnDelete.setOnClickListener(v -> {

            dataservice.deletePerson(Id, new DataListener() {
                @Override
                public void onDataReady(JSONArray jsonArray) {


                }

                @Override
                public void onDataError(String err) {
                    Toast.makeText(MainActivity2.this, "Error" + err, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDataReady(String string) {
                    Toast.makeText(MainActivity2.this, "Person deleted: " + Id, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onDataReady(JSONObject jsonObject) {


                    //recreate();
                    //Toast.makeText(MainActivity2.this, "Person: " + name +" Deleted", Toast.LENGTH_SHORT).show();

                }
            });


        });

    }


    public void onload() {

        DataService dataService = new DataService(this);
        dataService.getPerson(Id, new DataListener() {
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


                    txt.setText("ID: " + jsonObject.getString("persId") + " | Person information");
                    firstName.setHint(jsonObject.getString("firstName"));
                    lastName.setHint(jsonObject.getString("lastName"));
                    lastUpdated.setText("Last updated: " + jsonObject.getString("lastUpdated"));

                    if (jsonObject.getString("student") == "true") {
                        student.setChecked(true);
                    } else {
                        student.setChecked(false);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}