package com.geekbrains.mynotegb;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements ToolbarHolder {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_bottom_new:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.listNote, NewNoteFragment.newInstance())
                                .addToBackStack("")
                                .commit();
                        return true;

                    case R.id.action_bottom_search:
                        showDialogFragment();
                        return true;
                }
                return false;
            }
        });

        drawerLayout = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_about:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.listNote, new InfoFragment())
                                .commit();

                        drawerLayout.close();
                        return true;

                    case R.id.action_setting:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.listNote, new SettingFragment())
                                .commit();

                        drawerLayout.close();
                        return true;

                    case R.id.action_notes:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.listNote, new ListNotesFragment())
                                .commit();

                        drawerLayout.close();
                        return true;

                    case R.id.action_exit:
                        dialogExit();

                        drawerLayout.close();
                        return true;
                }
                return false;
            }
        });

        if (savedInstanceState == null) {

            ListNotesFragment listNotes = ListNotesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.listNote, listNotes)
                    .commit();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            }
        }
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void dialogExit() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.exit)
                .setNegativeButton("Отмена", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    private void showDialogFragment() {
        new MyDialogFragment().show(getSupportFragmentManager(), MyDialogFragment.TAG);
    }
}