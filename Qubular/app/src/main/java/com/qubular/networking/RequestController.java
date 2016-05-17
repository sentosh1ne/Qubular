package com.qubular.networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import General.Entry;
import General.Vocabulary;

/**
 * Created by sentosh1ne on 16.05.2016.
 */
public class RequestController {

    private static Gson gson = new Gson();
    public static final String DOMEN = "http://qubular.org";

    public static void getEntryById(Context context,String id){
        String url =  DOMEN + "/api/entries/" + id;

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null){} //TODO Request check
                Log.i("RESPONDED",response.toString());
                Vocabulary vocabulary = gson.fromJson(response.toString(),Vocabulary.class);
                Log.i("ENTRYYO",vocabulary.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ERRORRESPONSE",error.getMessage());
            }
        });

        VolleyController.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
