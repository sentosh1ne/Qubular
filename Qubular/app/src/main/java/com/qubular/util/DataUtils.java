package com.qubular.util;

import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import General.Entry;
import Lexeme.ForeignLexeme;
import Lexeme.NativeLexeme;
import Morpheme.Morpheme;

/**
 * Created by sentosh1ne on 23.05.2016.
 */
public class DataUtils {

    public static List<Entry> searchForeign(Entry[] entries,String lemma){
        List<Entry> result = new ArrayList<>();
        for (Entry e : entries ){
            if (e.foreign.lemma.getString().toLowerCase().contains(lemma.toLowerCase())){
                result.add(e);
            }
        }
        return result;
    }

    public static int getUiColor(Entry entry){
        Color localColor = new Color();
        switch (entry.foreign.permissibility.getPriority()){
            case 1:
                return localColor.argb(255,63,195,128);
            case 2:
                return localColor.argb(255,245,215,110);
            case 3:
                return localColor.argb(255,211,84,0);
            case 4:
                return localColor.argb(255,128,0,0);
        }
        return localColor.argb(255,0,0,0);
    }

    public static int getUiColor(NativeLexeme nativeLexeme){
        Color localColor = new Color();
        switch (nativeLexeme.usage.getPriority()){
            case 1:
                return localColor.argb(255,63,195,128);
            case 2:
                return localColor.argb(255,245,215,110);
            case 3:
                return localColor.argb(255,211,84,0);
            case 4:
                return localColor.argb(255,128,0,0);
        }
        return localColor.argb(255,0,0,0);
    }


    public static String getFormsString(Entry entry){
        String result = "";
        for (Morpheme m : entry.foreign.forms){
            result = result.concat(m.getString() + ", ");
        }
        result = result.substring(0,result.length() - 2);
        return  result;
    }

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase();
    }
    public static String getFormattedString(String[] strings){
        String result = "";
        for (String m : strings){
            result = result.concat(m + ",");
        }
        result = result.substring(0,result.length() - 1);
        return  result;
    }

    public static String getFormsString(Morpheme[] morphemes){
        String result = "";
        for (Morpheme m : morphemes){
            result = result.concat(m.getString() + ", ");
        }
        result = result.substring(0,result.length() - 2);
        return  result;
    }

    public static String[] getNativeStrings(NativeLexeme[] natives){
        String[] result = new String[natives.length];
        int i = 0;
        for (NativeLexeme n : natives){
            result[i] = n.getLemma().getString();
            i++;
        }
        return result;
    }

    public static void setUpTextView(TextView textView, String text, Typeface typeface){
        textView.setText(text);
        textView.setTypeface(typeface);
    }
}
