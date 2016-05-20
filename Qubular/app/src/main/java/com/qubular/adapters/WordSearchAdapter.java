package com.qubular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qubular.R;

import java.util.List;

import General.Entry;

/**
 * Created by sentosh1ne on 20.05.2016.
 */
public class WordSearchAdapter extends RecyclerView.Adapter<WordSearchAdapter.ViewHolder>{

    List<Entry> entries;
    int itemLayout;
    Context context;

    public WordSearchAdapter(List<Entry> entries, int itemLayout) {
        this.entries = entries;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO creation logic
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView wordText;
        public ViewHolder(View itemView) {
            super(itemView);
            wordText = (TextView) itemView.findViewById(R.id.wordCard);
        }
    }
}
