package com.geekbrains.mynotegb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import domain.Notes;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final List<Notes> data = new ArrayList<>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd LLLL, HH:mm", Locale.getDefault());

    private Fragment fragment;
    private OnNoteClicked noteClicked;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public OnNoteClicked getNoteClicked() {
        return noteClicked;
    }

    public void setNoteClicked(OnNoteClicked noteClicked) {
        this.noteClicked = noteClicked;
    }

    public void setData(Collection<Notes> notes) {
        data.addAll(notes);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        NotesViewHolder holder = new NotesViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        Notes note = data.get(position);

        holder.title.setText(note.getName());
        holder.details.setText(note.getDescription());
        holder.date.setText(simpleDateFormat.format(note.getDate()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public int addNote(Notes note) {
        data.add(note);

        return data.size() - 1;
    }

    public void removeNote(Notes selectedNote) {
        data.remove(selectedNote);
    }

    interface OnNoteClicked {
        void onNoteClicked(Notes notes);

        void onNoteLongClicked(Notes notes, int position);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView details;
        TextView date;

        public NotesViewHolder(@NonNull View itemView) {

            super(itemView);

            title = itemView.findViewById(R.id.note_name);
            details = itemView.findViewById(R.id.note_details);
            date = itemView.findViewById(R.id.note_date);

            CardView cardView = itemView.findViewById(R.id.card_view);

            fragment.registerForContextMenu(cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (noteClicked != null) {
                        int clickedPosition = getAdapterPosition();
                        noteClicked.onNoteClicked(data.get(clickedPosition));
                    }
                }
            });

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    cardView.showContextMenu();

                    if (noteClicked != null) {
                        int clickedPosition = getAdapterPosition();
                        noteClicked.onNoteLongClicked(data.get(clickedPosition), clickedPosition);
                    }
                    return true;
                }
            });
        }
    }
}
