package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText Fruitname;
    private Button btn1;
    private Button btn2;
    private Spinner dropdawn;
    private ArrayList<String> Fruitnames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fruitnames = new ArrayList<>();
        Fruitname = findViewById(R.id.fruitname);
        dropdawn = (Spinner)findViewById(R.id.dropdown_menu);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);




        dropdawn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country=   dropdawn.getItemAtPosition(dropdawn.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        btn1.setOnClickListener( v -> {

            DataService dataservice = new DataService(this);
            dataservice.getAllFruits(new DataListener() {
                @Override
                public void onDataReady(JSONArray jsonArray) {
                    for(int i = 0; i < jsonArray.length(); i++){
                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            Fruitnames.add(name);
                            {

                            }



                            dropdawn.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Fruitnames));

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onDataError(String err) {

                }

                @Override
                public void onDataReady(JSONObject jsonObject) {

                }
            });

        });



        btn2.setOnClickListener( v->{
            String fruitName = Fruitname.getText().toString();
            DataService dataService = new DataService(this);
            dataService.getFruit(fruitName, new DataListener() {
                @Override
                public void onDataReady(JSONArray jsonArray) {

                }

                @Override
                public void onDataError(String err) {

                }

                @Override
                public void onDataReady(JSONObject jsonObject) {

                    try {
                        String data = jsonObject.getString("genus") + " " + jsonObject.getString("name");
                        //dropdawn.setText(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        });


    }
}