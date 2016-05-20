package com.qubular.networking;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import General.Entry;
import General.Vocabulary;

/**
 * Created by sentosh1ne on 17.05.2016.
 */
public class LocalStorageRequestController {

    public static Entry getEntry(Context context, int id) throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader(context.getFilesDir() + "entries.json"));
        Vocabulary jsonElements = RequestController.gson.fromJson(jsonReader,Vocabulary.class);
        Entry result = null;
        for (Entry entry : jsonElements.getEntries()){
            if (entry.id == id){
                result = entry;
            }
        }

        return result;
    }

    public static Vocabulary getVocabulary(Context context) throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader(context.getFilesDir() + "entries.json"));
        Vocabulary jsonElements = RequestController.gson.fromJson(jsonReader,Vocabulary.class);
        return jsonElements;
    }

}
