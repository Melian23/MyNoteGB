package com.geekbrains.mynotegb;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    public static final String TAG = "MyDialogFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View customTitle = getLayoutInflater().inflate(R.layout.dialog_custom_title, null);

        View customView = getLayoutInflater().inflate(R.layout.dialog_custom_view, null);

        EditText editText = customView.findViewById(R.id.dialog_view_edit_text);

        return new AlertDialog.Builder(requireContext())
                .setCustomTitle(customTitle)
                .setView(customView)
                .setPositiveButton(R.string.find, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(requireContext(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
    }
}
