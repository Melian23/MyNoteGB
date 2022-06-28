package com.geekbrains.mynotegb;

/**
 * Как сделать чтоб снек бар отображался поверх bottom Navigation
 */

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import di.Dependencies;
import domain.Callback;
import domain.Notes;

public class ListNotesFragment extends Fragment {

    ProgressBar progressBar;
    private Notes selectedNote;
    private int selectedPosition;
    private NotesAdapter adapter;

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
                        Snackbar.make(view, "Поделиться", Snackbar.LENGTH_SHORT).setAnchorView(view).show();
                        return true;
                }
                return false;
            }
        });

        RecyclerView notesList = view.findViewById(R.id.container_list);
        notesList.setLayoutManager(new GridLayoutManager(requireContext(),
                2));

        adapter = new NotesAdapter(this);
        notesList.setAdapter(adapter);

        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(2000L);
        defaultItemAnimator.setRemoveDuration(2000L);
        notesList.setItemAnimator(defaultItemAnimator);

        getParentFragmentManager()
                .setFragmentResultListener(AddNoteBottomSheetDialogFragment.ADD_KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Notes note = result.getParcelable(AddNoteBottomSheetDialogFragment.ARG_NOTE);

                        int index = adapter.addNote(note);

                        adapter.notifyItemInserted(index);

                        notesList.smoothScrollToPosition(index);
                    }
                });

        getParentFragmentManager()
                .setFragmentResultListener(AddNoteBottomSheetDialogFragment.UPDATE_KEY_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Notes note = result.getParcelable(AddNoteBottomSheetDialogFragment.ARG_NOTE);

                        adapter.replaceNote(note, selectedPosition);

                        adapter.notifyItemChanged(selectedPosition);
                    }
                });

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNoteBottomSheetDialogFragment.addInstance()
                        .show(getParentFragmentManager(), "AddNoteBottomSheetDialogFragment");
            }
        });

        adapter.setNoteClicked(new NotesAdapter.OnNoteClicked() {
            @Override
            public void onNoteClicked(Notes notes) {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.listNote, DescriptionNotesFragment.newInstance(notes))
                        .addToBackStack("details")
                        .commit();
            }

            @Override
            public void onNoteLongClicked(Notes notes, int position) {
                selectedNote = notes;
                selectedPosition = position;
            }
        });

        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        Dependencies.getNotesRepository().getAll(new Callback<List<Notes>>() {
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
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_notes_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                AddNoteBottomSheetDialogFragment.editInstance(selectedNote)
                        .show(getParentFragmentManager(), "AddNoteBottomSheetDialogFragment");
                return true;

            case R.id.action_delete:

                progressBar.setVisibility(View.VISIBLE);

                Dependencies.getNotesRepository().removeNote(selectedNote, new Callback<Void>() {
                    @Override
                    public void onSuccess(Void result) {

                        progressBar.setVisibility(View.GONE);
                        adapter.removeNote(selectedNote);
                        adapter.notifyItemRemoved(selectedPosition);
                    }

                    @Override
                    public void onError(Throwable exception) {
                        progressBar.setVisibility(View.GONE);
                    }
                });

                return true;
        }

        return super.onContextItemSelected(item);
    }
}

