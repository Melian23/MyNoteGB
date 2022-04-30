package com.geekbrains.mynotegb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import domain.InMemoryNotesRepository;
import domain.Notes;

public class FragmentListNotes extends Fragment {

    public static FragmentListNotes newInstance() {
        FragmentListNotes fragment = new FragmentListNotes();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout container = view.findViewById(R.id.container_list);

        List<Notes> notes = InMemoryNotesRepository.getINSTANCE(requireContext()).getAll();

        for (Notes note : notes) {
            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);
            container.addView(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            TextView title = itemView.findViewById(R.id.note_name);
            title.setText(note.getName());
        }
    }
}
