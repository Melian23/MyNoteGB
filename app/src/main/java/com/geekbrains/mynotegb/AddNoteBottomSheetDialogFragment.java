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
    public static final String KEY_RESULT = "AddNoteBottomSheetDialogFragment_KEY_RESULT";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_note_bottom_sheet_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText title = view.findViewById(R.id.title);
        EditText details = view.findViewById(R.id.details);


        Button btnSave = view.findViewById(R.id.save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSave.setEnabled(false);

                Dependencies.NOTES_REPOSITORY.addNote(title.getText().toString(),
                        details.getText().toString(), new Callback<Notes>() {
                            @Override
                            public void onSuccess(Notes result) {

                                Bundle bundle = new Bundle();
                                bundle.putParcelable(ARG_NOTE, result);

                                getParentFragmentManager().setFragmentResult(KEY_RESULT, bundle);

                                btnSave.setEnabled(true);
                                dismiss();
                            }

                            @Override
                            public void onError(Throwable exception) {
                                btnSave.setEnabled(true);
                            }
                        });
            }
        });

        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
