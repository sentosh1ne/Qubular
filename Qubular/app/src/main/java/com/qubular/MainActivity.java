package com.qubular;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.qubular.networking.LocalStorageRequestController;
import com.qubular.networking.RequestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import General.Entry;
import Lexeme.ForeignLexeme;
import Lexeme.NativeLexeme;


public class MainActivity extends AppCompatActivity {

    TextView explanation, word, synonims;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        explanation = (TextView) findViewById(R.id.explanation);
        word = (TextView) findViewById(R.id.wordTitle);
        synonims = (TextView) findViewById(R.id.synonims);
        RequestController.getAllEntries(getApplicationContext());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                try {
                    Entry e = LocalStorageRequestController.getEntry(getApplicationContext(),1);
                    word.setText(e.foreign.lemma.getString());
                    String synons = "(";
                    for (NativeLexeme s : e.natives){
                        synons += s.lemma.getString() + ",";
                    }
                    synons += ")";

                    synonims.setText(synons);
                    String str = "";
                    //for (Morpheme m: e.foreign.forms)

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
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
}
