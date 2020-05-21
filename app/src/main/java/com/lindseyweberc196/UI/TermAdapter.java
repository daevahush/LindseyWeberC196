package com.lindseyweberc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lindseyweberc196.Activity.TermsActivity;
import com.lindseyweberc196.Entity.Term;
import com.lindseyweberc196.R;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {


    class TermViewHolder extends RecyclerView.ViewHolder {

        private final TextView TermItemView;

        private TermViewHolder(View itemView) {
            super(itemView);
            TermItemView = itemView.findViewById(R.id.TermTextView);
            itemView.setOnClickListener((v) -> {
                int position = getAdapterPosition();
                final Term current = mTerms.get(position);
                Intent intent = new Intent(context, TermsActivity.class);
                intent.putExtra("Term ID", current.getTermID());
                intent.putExtra("Term Name", current.getName());
                intent.putExtra("Term Start Date", current.getStartDate());
                intent.putExtra("Term End Date", current.getEndDate());
                intent.putExtra("Position", position);
                context.startActivity(intent);
            });
        }
    }



    private final LayoutInflater mInflater;
    private final Context context;
    private List<Term> mTerms;

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        if(mTerms != null) {
            Term current = mTerms.get(position);
            holder.TermItemView.setText(current.getName());
        } else {
            holder.TermItemView.setText("No Term");
        }

    }


    public void setTerms(List<Term> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(mTerms != null) {
            return mTerms.size();
        } else {
            return 0;
        }
    }


}
