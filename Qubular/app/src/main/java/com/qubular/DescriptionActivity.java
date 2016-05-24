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

import com.qubular.adapters.AlternativesAdapter;
import com.qubular.networking.RequestController;
import com.qubular.util.DataUtils;

import java.util.Arrays;

import General.Entry;

public class DescriptionActivity extends AppCompatActivity {

    TextView lemma,origin,synonims,explanation,publisher;
    RecyclerView recyclerView;
    View indicator;

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
        indicator = findViewById(R.id.circle_descr);
        recyclerView = (RecyclerView) findViewById(R.id.descr_recyclerview);
        setSupportActionBar(toolbar);




        Bundle bundle = this.getIntent().getExtras();
        Entry entry = RequestController.gson.fromJson((String)bundle.get("entry"),Entry.class);

        DataUtils.setUpTextView(lemma,entry.foreign.lemma.getString(),MainActivity.typefaceBold);
        DataUtils.setUpTextView(explanation,entry.foreign.meaning,MainActivity.typefaceNormal);
        DataUtils.setUpTextView(origin,entry.foreign.origin.getString(),MainActivity.typefaceNormal);
        DataUtils.setUpTextView(synonims,DataUtils.getFormsString(entry),MainActivity.typefaceItalic);
        if (entry.user != null) {
            DataUtils.setUpTextView(publisher,entry.user.userName, MainActivity.typefaceNormal);
        }else{
            publisher.setText("placeholder");
        }

        AlternativesAdapter.setupRecycler(recyclerView, Arrays.asList(entry.natives),getApplicationContext(),R.layout.form_card);
        indicator.setBackgroundColor(DataUtils.getUiColor(entry));
    }



}
