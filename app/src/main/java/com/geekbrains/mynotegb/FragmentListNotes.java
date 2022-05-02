package com.geekbrains.mynotegb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
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

        Toolbar toolbar = view.findViewById(R.id.toolbarMenu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(requireContext(), "search", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_sort:
                        Toast.makeText(requireContext(), "Сортировка", Toast.LENGTH_SHORT).show();
                    case R.id.action_info:
                        Toast.makeText(requireContext(), "info", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "Меню", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnNew = view.findViewById(R.id.newNote);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.listNote, FragmentNewNote.newInstance())
                        .addToBackStack("")
                        .commit();
            }
        });

        LinearLayout container = view.findViewById(R.id.container_list);

        List<Notes> notes = InMemoryNotesRepository.getINSTANCE(requireContext()).getAll();

        for (Notes note : notes) {
            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);
            container.addView(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.listNote, FragmentDescriptionNotes.newInstance(note))
                            .addToBackStack("details")
                            .commit();
                }

            });

            Button btnDelete = view.findViewById(R.id.delete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            TextView title = itemView.findViewById(R.id.note_name);
            title.setText(note.getName());
        }
    }
}
