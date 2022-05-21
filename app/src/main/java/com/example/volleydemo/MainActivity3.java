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

public class MainActivity3 extends AppCompatActivity {

    private EditText fName, lName;
    private CheckBox studentCheck;
    private Button CreateBtn,btnBack2;
    private TextView txtcreate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        fName = findViewById(R.id.firstNamebtn);
        lName = findViewById(R.id.lastNamebtn);
        studentCheck = findViewById(R.id.studentbtn);
        CreateBtn = findViewById(R.id.createbtn);
        txtcreate = findViewById(R.id.createtxt);
        btnBack2 = findViewById(R.id.btnBack2);


        DataService dataservice = new DataService(this);
        Intent intent = getIntent();

        btnBack2.setOnClickListener(v ->{
            setResult(AppConstants.RESULT_CODE_THIRD, intent);
            finish();
        });

        CreateBtn.setOnClickListener(v -> {



            JSONObject json = new JSONObject();
            try {
                json.put("firstName", fName.getText().toString());
                json.put("lastName", lName.getText().toString());
                json.put("student", studentCheck.isChecked());

                dataservice.Createperson(json, new DataListener() {
                    @Override
                    public void onDataReady(JSONArray jsonArray) {


                    }

                    @Override
                    public void onDataError(String err) {
                        Toast.makeText(MainActivity3.this, "Error:  " + err, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDataReady(String string) {

                    }

                    @Override
                    public void onDataReady(JSONObject jsonObject) {

                        String Stringfname = fName.getText().toString();
                        String Stringlname = lName.getText().toString();
                        String name = null;

                        if (Stringfname.matches("") || Stringlname.matches("")) {
                            Toast.makeText(MainActivity3.this, "You NEEED to type firstname and lastname", Toast.LENGTH_SHORT).show();
                        } else {

                            try {

                                String data = " ID: " + jsonObject.getString("persId") + "\n Name: " + jsonObject.getString("firstName") + "\n Last name : " + jsonObject.getString("lastName") + "\n Student: " + jsonObject.getString("student") + "\n Last Updated : " + jsonObject.getString("lastUpdated");
                                name = jsonObject.getString("firstName");
                                txtcreate.setText(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            fName.getText().clear();
                            lName.getText().clear();
                            studentCheck.setChecked(false);
                            //recreate();
                            Toast.makeText(MainActivity3.this, "Person: " + name +" Created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (JSONException e) {
                Log.d("Err_LOG", e.getMessage());
            }




        });


    }
}