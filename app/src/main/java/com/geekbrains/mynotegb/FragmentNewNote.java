package com.geekbrains.mynotegb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.Date;
import java.util.UUID;

import domain.Notes;

public class FragmentNewNote extends Fragment {

    public static final String ARG_NOTES = "ARG_NOTES";
    Notes notes;

    public static FragmentNewNote newInstance() {
        FragmentNewNote fragment = new FragmentNewNote();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTES, new Notes(UUID.randomUUID().toString(), "Заметка 1", "note", new Date()));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbarNewNote);

        if (requireActivity() instanceof ToolbarHolder)
            ((ToolbarHolder) requireContext()).setToolbar(toolbar);

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getParentFragmentManager()
//                        .popBackStack();
//            }
//        });

        TextView etNewName = view.findViewById(R.id.NewName);
        etNewName.setText("");

        TextView etNewData = view.findViewById(R.id.NewData);
        etNewData.setText("");

        TextView etNewDescription = view.findViewById(R.id.NewDescription);
        etNewDescription.setText("");

    }
}
