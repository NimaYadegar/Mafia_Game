package com.nima.mafia_game;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MenuOptions extends AppCompatActivity {

    protected Intent intent;
    protected Class witchContext;
    protected String title;
    protected static boolean no_music;
    protected MenuItem menuItem;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (witchContext.equals(CaractersInformationActivity.class)) {
            getMenuInflater().inflate(R.menu.menu_layout, menu);
            menuItem = menu.getItem(1);
            menu.clear();
            menu.add(menuItem.getTitle());
            menu.getItem(0).setActionView(menuItem.getActionView());
            menu.getItem(0).setCheckable(true);
            menuItem = menu.getItem(0);

            return super.onCreateOptionsMenu(menu);
        }
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        menuItem = menu.getItem(1);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (MainActivity.mediaPlayer.isPlaying() == true) {
            menuItem.setChecked(true);
        }else {
            menuItem.setChecked(false);
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        try {
            title = item.getTitle().toString();
            if (title.equals("موسیقی زمینه")) {
                if (MainActivity.mediaPlayer.isPlaying() == true) {
                    MainActivity.mediaPlayer.pause();
                    item.setChecked(false);
                    no_music = true;
                }else {
                    MainActivity.mediaPlayer.start();
                    item.setChecked(true);
                    no_music = false;
                }
                return super.onOptionsItemSelected(item);
            } else if (title.equals("نقش ها") || title.equals("شهروندان") || title.equals("مافیا ها")) {
                return super.onOptionsItemSelected(item);
            } else if (title.equals("راهنمای بازی")) {

                if (MainActivity.can_click_btn == false) return super.onOptionsItemSelected(item);
                MainActivity.can_click_btn = false;
                MainActivity.status = "help";
                MainActivity.can_pause = false;
                intent = new Intent(this, CaractersInformationActivity.class);
                startActivity(intent);
                return super.onOptionsItemSelected(item);
            }

            if (MainActivity.can_click_btn == false) return super.onOptionsItemSelected(item);
            MainActivity.can_click_btn = false;
            MainActivity.status = title;
            MainActivity.des_status = true;
            MainActivity.can_pause = false;

            intent = new Intent(this, CaractersInformationActivity.class);
            startActivity(intent);
            return super.onOptionsItemSelected(item);
        }catch (Exception e) {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (MainActivity.can_pause == true) {
            MainActivity.mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        MainActivity.can_pause = true;
        witchContext = super.getClass();

        if (no_music == false) {
            MainActivity.mediaPlayer.start();
        }
        if (witchContext.equals(ShufflingActivity.class) && ShufflingActivity.first_time == true) {
            MainActivity.mediaPlayer.pause();
            ShufflingActivity.first_time = false;
            no_music = true;
        }

        try {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    MainActivity.can_click_btn = true;
                    //System.out.println("MainActivity.can_click_btn = true;");
                    cancel();
                }
            },1000, 1000);
        }catch (Exception e) {

        }

    }


}
