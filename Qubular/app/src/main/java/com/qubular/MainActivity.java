package com.qubular;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.qubular.adapters.WordSearchAdapter;
import com.qubular.networking.LocalStorageRequestController;
import com.qubular.networking.RequestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import General.Entry;
import General.Vocabulary;
import Lexeme.ForeignLexeme;
import Lexeme.NativeLexeme;


public class MainActivity extends AppCompatActivity {

    TextView explanation, word, synonims;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RequestController.getAllEntries(getApplicationContext());

//        explanation = (TextView) findViewById(R.id.explanation);
//        word = (TextView) findViewById(R.id.wordTitle);
//        synonims = (TextView) findViewById(R.id.synonims);
        recyclerView = (RecyclerView) findViewById(R.id.main_recview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Vocabulary voc = null;
                try {
                    voc = LocalStorageRequestController.getVocabulary(getApplicationContext());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                WordSearchAdapter.setupWordRecycler(recyclerView, Arrays.asList(voc.getEntries()),R.layout.wordcardlayout,getApplicationContext());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
