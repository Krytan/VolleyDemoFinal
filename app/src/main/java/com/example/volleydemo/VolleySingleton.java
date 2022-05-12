package com.example.volleydemo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue queue;
    private Context ctx;

    private VolleySingleton(Context ctx) {
        this.ctx = ctx;

    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }
    public RequestQueue getQueue(){
        if(queue == null){
            queue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return queue;
    }
}
