package com.qubular.util;

import java.util.ArrayList;
import java.util.List;

import General.Entry;
import Morpheme.Morpheme;

/**
 * Created by sentosh1ne on 23.05.2016.
 */
public class DataUtils {
    public Entry[] searchForeign(Entry[] entries,String lemma){
        List<Entry> result = new ArrayList<>();
        for (Entry e : entries ){
            if (e.foreign.lemma.getString().contains(lemma)){
                result.add(e);
            }
        }
        return (Entry[]) result.toArray();
    }
}
