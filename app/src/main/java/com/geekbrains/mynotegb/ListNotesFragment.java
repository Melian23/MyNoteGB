package com.geekbrains.mynotegb;

/**
 * Как сделать чтоб снек бар отображался поверх bottom Navigation
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import di.Dependencies;
import domain.Callback;
import domain.Notes;

public class ListNotesFragment extends Fragment {


    public static ListNotesFragment newInstance() {
        ListNotesFragment fragment = new ListNotesFragment();
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

        if (requireActivity() instanceof ToolbarHolder)
            ((ToolbarHolder) requireContext()).setToolbar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_sort:
                        Toast.makeText(requireContext(), "Сортировка", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_info:
                        Snackbar.make(view, "Информация", Snackbar.LENGTH_SHORT).setAnchorView(view).show();
                        return true;
                }
                return false;
            }
        });
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(requireContext(), "Меню", Toast.LENGTH_SHORT).show();
//            }
//        });

        RecyclerView notesList = view.findViewById(R.id.container_list);
        notesList.setLayoutManager(new GridLayoutManager(requireContext(),
                2));

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
//        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_divider));
//        notesList.addItemDecoration(dividerItemDecoration);

        NotesAdapter adapter = new NotesAdapter();
        notesList.setAdapter(adapter);
        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Notes notes) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.listNote, DescriptionNotesFragment.newInstance(notes))
                        .addToBackStack("details")
                        .commit();
            }
        });

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        Dependencies.NOTES_REPOSITORY.getAll(new Callback<List<Notes>>() {
            @Override
            public void onSuccess(List<Notes> result) {
                adapter.setData(result);

                adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable exception) {

                progressBar.setVisibility(View.GONE);
            }
        });

//        LinearLayout container = view.findViewById(R.id.container_list);
//        for (Notes note : notes) {
//
//            container.addView(itemView);
//            itemView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    getParentFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.listNote, DescriptionNotesFragment.newInstance(note))
//                            .addToBackStack("details")
//                            .commit();
//                }
//            });
//            Button btnDelete = view.findViewById(R.id.delete);
//            btnDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
    }
}

