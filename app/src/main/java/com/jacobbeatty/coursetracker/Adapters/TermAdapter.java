package com.jacobbeatty.coursetracker.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jacobbeatty.coursetracker.R;
import com.jacobbeatty.coursetracker.Entity.Term;
import com.jacobbeatty.coursetracker.Activities.TermDetail;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {
//    private final Context context;
    List<Term> terms;
//    private final Context context;


    public TermAdapter( List<Term> terms) {
        this.terms = terms;
//        this.context = context;
    }

    @Override
    public TermAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TermAdapter.ViewHolder holder, int position) {
        Term term = terms.get(position);
        holder.termName.setText(terms.get(position).getTermName());
//        holder.termStart.setText((CharSequence) terms.get(position).getTermStart());
//        holder.termEnd.setText((CharSequence) terms.get(position).getTermEnd());
        holder.term = term;

    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView termName;
        public TextView termStart;
        public TextView termEnd;
        LinearLayout toDetail;
        int position;
        Term term;
        public ViewHolder(View itemView) {
            super(itemView);
            termName = itemView.findViewById(R.id.term_name);
            termStart = itemView.findViewById(R.id.term_start);
            termEnd = itemView.findViewById(R.id.term_end);
            toDetail = itemView.findViewById(R.id.row);
            toDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("detail","onClick:Clicked detail for " + position + " term " + term.getTermName());
                    int position = getAdapterPosition();
                    final Term current = terms.get(position);
                    Intent intent = new Intent(v.getContext(), TermDetail.class);
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("termStart", current.getTermStart());
                    intent.putExtra("termEnd", current.getTermEnd());
                    intent.putExtra("termID", current.getId());
                    intent.putExtra("position", position);
                    v.getContext().startActivity(new Intent(intent));

                }
            });
        }
    }
}


