package com.qubular;


import android.app.SearchManager;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.qubular.adapters.WordSearchAdapter;
import com.qubular.networking.LocalStorageRequestController;
import com.qubular.networking.RequestController;
import com.qubular.util.DataUtils;

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
    public static Typeface typefaceNormal;
    public static  Typeface typefaceBold;
    public static  Typeface typefaceItalic ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (isOnline()){
            RequestController.getAllEntries(getApplicationContext());
        }
        setSupportActionBar(toolbar);




//        explanation = (TextView) findViewById(R.id.explanation);
//        word = (TextView) findViewById(R.id.wordTitle);
//        synonims = (TextView) findViewById(R.id.synonims);
        recyclerView = (RecyclerView) findViewById(R.id.main_recview);

        typefaceNormal = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Bitter-Regular.otf");
        typefaceBold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Bitter-Bold.otf");
        typefaceItalic = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Bitter-Italic.otf");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                try {
                    Entry[] entries = LocalStorageRequestController.getVocabulary(getApplicationContext()).getEntries();
                    WordSearchAdapter.setupWordRecycler(recyclerView,
                            DataUtils.searchForeign(entries,query),
                                    R.layout.wordcardlayout,
                                    getApplicationContext());
                    return true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    Entry[] entries = LocalStorageRequestController.getVocabulary(getApplicationContext()).getEntries();
                    WordSearchAdapter.setupWordRecycler(recyclerView,
                            DataUtils.searchForeign(entries,newText),
                            R.layout.wordcardlayout,
                            getApplicationContext());
                    return true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                }
                return false;
            }
        });
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
