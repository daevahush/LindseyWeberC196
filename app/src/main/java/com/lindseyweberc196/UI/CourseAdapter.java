package com.lindseyweberc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lindseyweberc196.Activity.CoursesActivity;
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
                Intent intent = new Intent(context, CoursesActivity.class);
                intent.putExtra("Course ID", current.getCourseID());
                intent.putExtra("Term ID", current.getTermID());
                intent.putExtra("Course Name", current.getTitle());
                intent.putExtra("Status", current.getStatus());
                intent.putExtra("Course Start Date", current.getStartDate());
                intent.putExtra("Course End Date", current.getEndDate());
                intent.putExtra("Mentor Name", current.getMentorName());
                intent.putExtra("Mentor Phone", current.getMentorPhone());
                intent.putExtra("Mentor Email", current.getMentorEmail());
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
