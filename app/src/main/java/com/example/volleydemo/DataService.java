package com.example.volleydemo;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataService {

    private Context ctx;
    private static final String URL = "http://10.0.2.2:8080/webapifinal_war/api/person";
    //private static final String URL2 = "https://fruityvice.com/api/fruit/";

    public DataService(Context ctx) {
        this.ctx = ctx;
    }

    public void getPerson(int id, DataListener listener){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL +"/"+ id,
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

    public void Createperson(JSONObject json, DataListener listener){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onDataReady(response);
                        Log.d("PSL_LOGdata",response.toString());

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

    public void deletePerson(int id, DataListener listener){
        StringRequest request = new StringRequest(
                Request.Method.DELETE,
                URL + "/"+ id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onDataReady(response);
                        Log.d("PSL_LOGdata",response.toString());

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



    public void getAllperson(DataListener listener){

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
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

    public void updatedPerson(int id ,JSONObject json,DataListener listener){

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                URL + "/" +id,
                json,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
