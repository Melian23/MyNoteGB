package com.geekbrains.mynotegb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import domain.Notes;

public class FragmentDescriptionNotes extends Fragment {
    public static final String ARG_NOTES = "notes";
    public static final String LIST_NOTES = "LIST_NOTES";
    private Notes notes;

    public static FragmentDescriptionNotes newInstance(Notes notes) {
        FragmentDescriptionNotes fragment = new FragmentDescriptionNotes();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTES, notes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView etName = view.findViewById(R.id.textName);
        notes = getArguments().getParcelable(ARG_NOTES);
        etName.setText(notes.getName());
    }
}
