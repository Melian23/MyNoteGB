package com.geekbrains.mynotegb;

import static com.geekbrains.mynotegb.FragmentDescriptionNotes.LIST_NOTES;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import domain.Notes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Notes note = getIntent().getParcelableExtra(LIST_NOTES);

        if (savedInstanceState == null) {

            FragmentListNotes listNotes = FragmentListNotes.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.listNote, listNotes)
                    .commit();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            }
        }
    }
}