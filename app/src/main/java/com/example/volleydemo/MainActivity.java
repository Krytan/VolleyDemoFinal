package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText Fruitname;
    private Button btn1;
    private Button btn2;
    private Spinner dropdawn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fruitname = findViewById(R.id.fruitname);
        dropdawn = findViewById(R.id.dropdown_menu);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);



        btn1.setOnClickListener( v -> {
            DataService dataservice = new DataService(this);
            dataservice.getAllFruits(new DataListener() {
                @Override
                public void onDataReady(JSONArray jsonArray) {
                    for(int i = 0; i < jsonArray.length(); i++){
                        try {
                            dropdawn = new Spinner(MainActivity.this);
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            //dropdawn.autofill(jsonObject);
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