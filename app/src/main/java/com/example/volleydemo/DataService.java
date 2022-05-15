package com.example.volleydemo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataService {

    private Context ctx;
    private static final String URL = "https://fruityvice.com/api/fruit/";
    //private static final String URL2 = "https://reqres.in/api/users/";

    public DataService(Context ctx) {
        this.ctx = ctx;
    }

    public void getFruit(String fruitname, DataListener listener){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL + fruitname,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onDataReady(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onDataError(error.getMessage());
                    }
                }
        );
        VolleySingleton.getInstance(ctx).getQueue().add(request);

    }
/*
    public void PostData(String Name, String Job, DataListener listener){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onDataReady(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onDataError(error.getMessage());
                    }
                }
        );
        VolleySingleton.getInstance(ctx).getQueue().add(request);

    }

 */

    public void getAllFruits(DataListener listener){

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL + "all",
                null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listener.onDataReady(response);
                        Log.d("PSL_LOGdata",response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onDataError(error.getMessage());
                        Log.d("PSL_LOGerr",error.toString());
                    }
                }

        );
       VolleySingleton.getInstance(ctx).getQueue().add(request);
    }

}
