package com.problemsolvers.christmascountdown;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;
import com.problemsolvers.christmascountdown.ui.Count;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView
        .OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    MediaPlayer player1;
    MediaPlayer player2;
    MediaPlayer player3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_count)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

//        if(savedInstanceState == null){
//
//            getSupportFragmentManager().beginTransaction().replace(
//                    R.id.nav_host_fragment,new Count()).commit();
//            navigationView.setCheckedItem(R.id.nav_Count);
//        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        RelativeLayout relativeLayout = findViewById(R.id.activity_main);
        switch (item.getItemId()) {

            case R.id.nav_count:
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.nav_host_fragment, new Count()).commit();
                break;

            case R.id.image1:

                relativeLayout.setBackgroundResource(R.drawable.a);
                break;
            case R.id.image2:

                relativeLayout.setBackgroundResource(R.drawable.bells);
                break;
            case R.id.image3:
                relativeLayout.setBackgroundResource(R.drawable.u);
                break;


            case R.id.sound1:
                if (player2 != null) {
                    player2.pause();
                    play();
                }
                if (player3 != null) {
                    player3.pause();
                    play();
                }
                play();
                break;

            case R.id.sound2:
                if (player1 != null) {
                    player1.pause();
                    play2();
                }
                if (player3 != null) {
                    player3.pause();
                    play2();
                }
                play2();
                break;


            case R.id.sound3:
                if (player1 != null) {
                    player1.pause();
                    play3();
                }
                if (player2 != null) {
                    player2.pause();
                    play3();
                }
                play3();
                break;
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void play() {
        if (player1 == null) {
            player1 = MediaPlayer.create(this, R.raw.merry);
            player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player1.start();
                }
            });
        }
        player1.start();
    }

    private void play2() {
        if (player2 == null) {
            player2 = MediaPlayer.create(this, R.raw.mingle);
            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player2.start();
                }
            });
        }
        player2.start();
    }

    private void play3() {
        if (player3 == null) {
            player3 = MediaPlayer.create(this, R.raw.ringle);
            player3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player3.start();
                }
            });
        }
        player3.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player1 != null) {
            player1.release();
            player1 = null;
        }
        if (player2 != null) {
            player2.release();
            player2 = null;
        }
        if (player3 != null) {
            player3.release();
            player3 = null;
        }
    }


    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
        play2();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        play2();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        play2();


    }
}