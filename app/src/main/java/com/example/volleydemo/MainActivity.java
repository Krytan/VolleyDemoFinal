package com.example.volleydemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private TextView txt;
    private ArrayList<String> Fruitnames;
    private ActivityResultLauncher<Intent> launcher;
    boolean CheckFirstTime = true;
    private int test = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fruitnames = new ArrayList<>();
        Fruitname = findViewById(R.id.fruitname);
        dropdawn = findViewById(R.id.dropdown_menu);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        txt = findViewById(R.id.normaltxt);


        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == AppConstants.RESULT_CODE_SECOND) {
                            Intent intent = result.getData();
                            //txt_msg.setText(intent.getStringExtra("msg"));
                            Toast.makeText(MainActivity.this, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(result.getResultCode() == AppConstants.RESULT_CODE_THIRD) {
                            //txt_msg.setText(result.getData().getStringExtra("msg"));
                            Toast.makeText(MainActivity.this, "*******", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });




        dropdawn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                    String name = dropdawn.getItemAtPosition(dropdawn.getSelectedItemPosition()).toString();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("fromMain", name);
                    launcher.launch(intent);
                    //Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


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
            CheckFirstTime = false;

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
                        txt.setText(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        });


    }
}