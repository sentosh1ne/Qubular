package com.qubular.networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import General.Entry;
import General.Vocabulary;

/**
 * Created by sentosh1ne on 16.05.2016.
 */
public class RequestController {

    public static Gson gson = new Gson();
    public static final String DOMEN = "http://qubular.org";

    public static void getAllEntries(final Context context){
        String url =  DOMEN + "/api/entries/";

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null){} //TODO Request check
                Log.i("RESPONDED",response.toString());
                Vocabulary vocabulary = gson.fromJson(response.toString(),Vocabulary.class);
                try {
                    try (Writer writer = new FileWriter(context.getFilesDir() + "entries.json")) {
                        Gson localg = new GsonBuilder().create();
                        localg.toJson(vocabulary, writer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("ENTRYYO",vocabulary.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ERRORRESPONSE",error.getMessage().toString());
            }
        });

        VolleyController.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    public static void getEntryById(Context context,String id){
        String url = DOMEN + "/api/entries/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response == null) {
                    //TODO
                }

                Entry entry = gson.fromJson(response.toString(),Entry.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ERRORRESPONSE",error.getMessage().toString());
            }
        });

        VolleyController.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
