package com.lindseyweberc196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.lindseyweberc196.Activity.Course.CourseDetailsActivity;
import com.lindseyweberc196.Activity.Note.NoteDetailsActivity;
import com.lindseyweberc196.Entity.Course;
import com.lindseyweberc196.Entity.Note;
import com.lindseyweberc196.R;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView NoteItemView;

        private NoteViewHolder(View itemView) {
            super(itemView);
            NoteItemView = itemView.findViewById(R.id.NoteTextView);
            itemView.setOnClickListener((v) -> {
                int position = getAdapterPosition();
                final Note current = mNotes.get(position);
                Intent intent = new Intent(context, NoteDetailsActivity.class);
                intent.putExtra("NoteID", current.getNoteID());
                intent.putExtra("NoteTitle", current.getNoteTitle());
                intent.putExtra("Note", current.getNote());
                intent.putExtra("CourseID", current.getCourseID());
                intent.putExtra("Position", position);
                context.startActivity(intent);
            });
        }
    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Note> mNotes;

    public NoteAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_note, parent, false);
        return new NoteAdapter.NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            holder.NoteItemView.setText(current.getNoteTitle());
        } else {
            holder.NoteItemView.setText("No Course");
        }
    }


    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();
        } else {
            return 0;
        }
    }
}
