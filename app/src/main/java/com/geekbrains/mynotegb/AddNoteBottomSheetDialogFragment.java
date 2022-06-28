package com.geekbrains.mynotegb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import di.Dependencies;
import domain.Callback;
import domain.Notes;

public class AddNoteBottomSheetDialogFragment extends BottomSheetDialogFragment {

    public static final String ARG_NOTE = "ARG_NOTE";
    public static final String ADD_KEY_RESULT = "AddNoteBottomSheetDialogFragment_ADD_KEY_RESULT";
    public static final String UPDATE_KEY_RESULT = "AddNoteBottomSheetDialogFragment_UPDATE_KEY_RESULT";

    public static AddNoteBottomSheetDialogFragment addInstance() {
        return new AddNoteBottomSheetDialogFragment();
    }

    public static AddNoteBottomSheetDialogFragment editInstance(Notes notes) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, notes);
        AddNoteBottomSheetDialogFragment fragment = new AddNoteBottomSheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note_bottom_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Notes noToEdit = null;

        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {
            noToEdit = getArguments().getParcelable(ARG_NOTE);
        }

        EditText title = view.findViewById(R.id.title);
        EditText details = view.findViewById(R.id.details);

        if (noToEdit != null) {
            title.setText(noToEdit.getName());
            details.setText(noToEdit.getDescription());
        }

        Button btnSave = view.findViewById(R.id.save);

        Notes finalNoToEdit = noToEdit;
        Notes finalNoToEdit1 = noToEdit;
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSave.setEnabled(false); // блокировка ввода, нажатия на кнопку

                if (finalNoToEdit != null) {
                    Dependencies.NOTES_REPOSITORY.upDateNote(finalNoToEdit, title.getText().toString(),
                            details.getText().toString(), new Callback<Notes>() {
                                @Override
                                public void onSuccess(Notes result) {

                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(ARG_NOTE, result);
                                    getParentFragmentManager().setFragmentResult(UPDATE_KEY_RESULT, bundle);

                                    btnSave.setEnabled(true);
                                    dismiss();
                                }

                                @Override
                                public void onError(Throwable exception) {

                                    btnSave.setEnabled(true);
                                }
                            });

                } else {

                    Dependencies.NOTES_REPOSITORY.addNote(title.getText().toString(),
                            details.getText().toString(), new Callback<Notes>() {
                                @Override
                                public void onSuccess(Notes result) {

                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable(ARG_NOTE, result);

                                    getParentFragmentManager().setFragmentResult(ADD_KEY_RESULT, bundle);

                                    btnSave.setEnabled(true);
                                    dismiss();
                                }

                                @Override
                                public void onError(Throwable exception) {
                                    btnSave.setEnabled(true);
                                }
                            });
                }
            }
        });

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
