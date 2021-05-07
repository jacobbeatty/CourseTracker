package com.jacobbeatty.coursetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {
    ArrayList<String> terms;

    public TermAdapter(ArrayList<String> terms) {
        this.terms = terms;
    }

    @Override
    public TermAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TermAdapter.ViewHolder holder, int position) {
        holder.termName.setText(terms.get(position));
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView termName;
        public ViewHolder(View itemView) {
            super(itemView);
            termName = itemView.findViewById(R.id.term_name);
        }
    }
}


