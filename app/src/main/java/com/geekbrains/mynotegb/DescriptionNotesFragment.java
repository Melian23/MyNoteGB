package com.geekbrains.mynotegb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Date;

import domain.Notes;

public class DescriptionNotesFragment extends Fragment {
    public static final String ARG_NOTES = "notes";
    private Notes notes;

    public static DescriptionNotesFragment newInstance(Notes notes) {
        DescriptionNotesFragment fragment = new DescriptionNotesFragment();
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

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        if (requireActivity() instanceof ToolbarHolder)
            ((ToolbarHolder) requireContext()).setToolbar(toolbar);

        ((MainActivity) requireContext()).setToolbar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        //работает только в вертикальной ориентации
                        new BottomSheetDialogFragment().show(getParentFragmentManager(), "");
                        return true;
                    case R.id.action_share:
                        Toast.makeText(requireContext(), "Поделиться", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_add:
                        Toast.makeText(requireContext(), "Добавить фото", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getParentFragmentManager()
//                        .popBackStack();
//            }
//        });

        TextView etName = view.findViewById(R.id.textName);
        notes = getArguments().getParcelable(ARG_NOTES);
        etName.setText(notes.getName());

        etName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(requireContext(), view);

                requireActivity()
                        .getMenuInflater()
                        .inflate(R.menu.menu_pop_details, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_pop_copy:
                                Toast.makeText(requireContext(), "Копировать", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.action_pop_past:
                                Toast.makeText(requireContext(), "Вставить", Toast.LENGTH_SHORT).show();
                                return true;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        TextView etDescription = view.findViewById(R.id.textDescription);
        notes = getArguments().getParcelable(ARG_NOTES);
        etDescription.setText(notes.getDescription());

        TextView etDate = view.findViewById(R.id.textData);
        notes = getArguments().getParcelable(ARG_NOTES);
        etDate.setText (notes.getDate(new Date()).toString());


    }
}
