package com.lindseyweberc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lindseyweberc196.Activity.Assessment.AssessmentDetailsActivity;
import com.lindseyweberc196.Entity.Assessment;
import com.lindseyweberc196.R;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {

        private final TextView AssessmentItemView;

        private AssessmentViewHolder(View itemView) {
            super(itemView);
            AssessmentItemView = itemView.findViewById(R.id.AssessmentTextView);
            itemView.setOnClickListener((v) -> {
                int position = getAdapterPosition();
                final Assessment current = mAssessments.get(position);
                Intent intent = new Intent(context, AssessmentDetailsActivity.class);
                intent.putExtra("AssessmentID", current.getAssessmentID());
                intent.putExtra("Type", current.getAssessmentType().toString());
                intent.putExtra("AssessmentName", current.getName());
                intent.putExtra("CourseID", current.getCourseID());
                intent.putExtra("AssessmentDate", current.getDate());
                intent.putExtra("Position", position);
                context.startActivity(intent);
            });
        }
    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Assessment> mAssessments;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_assessment, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssessmentViewHolder holder, int position) {
        if(mAssessments != null) {
            Assessment current = mAssessments.get(position);
            holder.AssessmentItemView.setText(current.getName());
        } else {
            holder.AssessmentItemView.setText("No Assessment");
        }

    }


    public void setAssessments(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(mAssessments != null) {
            return mAssessments.size();
        } else {
            return 0;
        }
    }
}
