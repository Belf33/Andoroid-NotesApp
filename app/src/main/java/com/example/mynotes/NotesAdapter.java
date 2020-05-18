package com.example.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter  extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> noteArrayList;
    private OnNoteClickListener onNoteClickListener;

    public NotesAdapter(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    interface OnNoteClickListener {
        void onNoteClick(int position);

        void onLongClick(int position);
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = noteArrayList.get(position);

        holder.title.setText(note.getName());
        holder.desc.setText(note.getDescription());
        holder.dayOfWeek.setText(Note.getDayAsString(note.getDayOfWeek()));

        int colorId;
        int priority = note.getPriority();

        switch (priority) {
            case 1:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_red_light);
                break;
            case 2:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_orange_light);
                break;
            default:
                colorId = holder.itemView.getResources().getColor(android.R.color.holo_green_light);
        }

        holder.title.setBackgroundColor(colorId);

    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;
        private TextView dayOfWeek;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTextView);
            desc = itemView.findViewById(R.id.descriptionTextView);
            dayOfWeek = itemView.findViewById(R.id.dayOfWeekTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onLongClick(getAdapterPosition());
                    }
                    return true;
                }
            });

        }
    }

    public void setNotes(List<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
        notifyDataSetChanged();
    }

    public List<Note> getNoteArrayList() {
        return noteArrayList;
    }
}
