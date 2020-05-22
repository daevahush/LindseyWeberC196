package com.lindseyweberc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.lindseyweberc196.Activity.CourseDetailsActivity;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.R;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private final TextView CourseItemView;

        private CourseViewHolder(View itemView) {
            super(itemView);
            CourseItemView = itemView.findViewById(R.id.CourseTextView);
            itemView.setOnClickListener((v) -> {
                int position = getAdapterPosition();
                final Course current = mCourses.get(position);

                Intent intent = new Intent(context, CourseDetailsActivity.class);
                intent.putExtra("CourseID", current.getCourseID());
                intent.putExtra("TermID", current.getTermID());
                intent.putExtra("CourseName", current.getTitle());
                intent.putExtra("Status", current.getStatus());
                intent.putExtra("StartDate", current.getStartDate());
                intent.putExtra("EndDate", current.getEndDate());
                intent.putExtra("MentorName", current.getMentorName());
                intent.putExtra("MentorPhone", current.getMentorPhone());
                intent.putExtra("MentorEmail", current.getMentorEmail());
                intent.putExtra("Note", current.getNote());
                intent.putExtra("Position", position);
                context.startActivity(intent);


            });
        }
    }



    private final LayoutInflater mInflater;
    private final Context context;
    private List<Course> mCourses;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseAdapter.CourseViewHolder holder, int position) {
        if(mCourses != null) {
            Course current = mCourses.get(position);
            holder.CourseItemView.setText(current.getTitle());
        } else {
            holder.CourseItemView.setText("No Course");
        }

    }


    public void setCourses(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(mCourses != null) {
            return mCourses.size();
        } else {
            return 0;
        }
    }
}
