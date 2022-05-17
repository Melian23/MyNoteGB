package com.geekbrains.mynotegb;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements ToolbarHolder {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_about:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.listNote, new AboutFragment())
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
                                .replace(R.id.listNote, new FragmentListNotes())
                                .commit();

                        drawerLayout.close();
                        return true;
                }
                return false;
            }
        });

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
}