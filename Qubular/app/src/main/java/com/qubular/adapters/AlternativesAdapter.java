package com.qubular.adapters;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qubular.MainActivity;
import com.qubular.R;
import com.qubular.util.DataUtils;
import com.qubular.util.DividerItemDecoration;

import java.util.List;

import General.Entry;
import Lexeme.NativeLexeme;
import Morpheme.Morpheme;

/**
 * Created by sentosh1ne on 24.05.2016.
 */
public class AlternativesAdapter extends RecyclerView.Adapter<AlternativesAdapter.ViewHolder> {

    List<NativeLexeme> forms;
    int itemLayout;
    Context context;

    public AlternativesAdapter(List<NativeLexeme> forms, int itemLayout) {
        this.forms = forms;
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
        NativeLexeme nativeLexeme = forms.get(position);
        holder.lemma.setText((position+1) + ". " + nativeLexeme.getLemma().getString());
        holder.lemma.setTypeface(MainActivity.typefaceNormal);
        holder.indicator.setBackgroundColor(DataUtils.getUiColor(nativeLexeme));
    }

    @Override
    public int getItemCount() {
        return forms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lemma;
        View indicator;
        public ViewHolder(View itemView) {
            super(itemView);
            lemma = (TextView) itemView.findViewById(R.id.formLemma);
            indicator = itemView.findViewById(R.id.circle_form);
        }
    }

    public static void setupRecycler(RecyclerView recyclerView,List<NativeLexeme> forms,Context context, int layout){
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AlternativesAdapter(forms, layout));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context,R.drawable.divider));
    }
}
