package com.qubular;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.qubular.networking.RequestController;
import com.qubular.util.DataUtils;

import General.Entry;

public class DescriptionActivity extends AppCompatActivity {

    TextView lemma,origin,synonims,explanation,publisher;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lemma = (TextView) findViewById(R.id.word_descr);
        origin = (TextView) findViewById(R.id.origin);
        synonims = (TextView) findViewById(R.id.synonims_descr);
        explanation = (TextView) findViewById(R.id.explanation);
        recyclerView = (RecyclerView) findViewById(R.id.descr_recyclerview);
        publisher = (TextView) findViewById(R.id.publisher);
        setSupportActionBar(toolbar);


        Typeface typefaceNormal = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Bitter-Regular.otf");
        Typeface typefaceBold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Bitter-Bold.otf");
        Typeface typefaceItalic = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Bitter-Italic.otf");

        Bundle bundle = this.getIntent().getExtras();
        Entry entry = RequestController.gson.fromJson((String)bundle.get("entry"),Entry.class);

        DataUtils.setUpTextView(lemma,entry.foreign.lemma.getString(),typefaceBold);
        DataUtils.setUpTextView(explanation,entry.foreign.meaning,typefaceNormal);
        DataUtils.setUpTextView(origin,entry.foreign.origin.getString(),typefaceBold);
        DataUtils.setUpTextView(synonims,DataUtils.getFormsString(entry),typefaceItalic);
        if (entry.user != null) {
            DataUtils.setUpTextView(publisher,entry.user.userName, typefaceNormal);
        }else{
            publisher.setText("placeholder");
        }
    }



}
