package com.qubular.util;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import General.Entry;
import Lexeme.ForeignLexeme;
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

    public static String getFormsString(Entry entry){
        String result = "";
        for (Morpheme m : entry.foreign.forms){
            result = result.concat(m.getString() + ",");
        }
        result = result.substring(0,result.length() - 1);
        return  result;
    }
}
