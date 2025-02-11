package com.nima.mafia_game;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

public class MainActivity extends MenuOptions {

    TextToSpeech textToSpeech;
    public static MediaPlayer mediaPlayer;
    public static boolean can_pause;
    public static boolean creating;
    public static boolean can_click_btn;

    public static String status;
    public static boolean des_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = "";
        des_status = false;
        creating = true;
        MenuOptions.no_music = false;

        try {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mafia_song);
            mediaPlayer.setVolume(100, 100);
            mediaPlayer.setLooping(true);
            //mediaPlayer.start();
        }catch (Exception e) {

        }

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        System.out.println("main destroy");
    }

    @Override
    public void onBackPressed() {

        if (can_click_btn == false) return;
        can_click_btn = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("آیا می خواهید خارج شوید؟");
        builder.setMessage("از بازی خارج می شوید؟");
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("خیر", null);
        builder.create().show();
        can_click_btn = true;

    }

    public void start(View view){


        try {
            if (can_click_btn == false) return;
            can_click_btn = false;
            can_pause = false;
            textToSpeech.speak("Well Come To, Mafia God", TextToSpeech.QUEUE_FLUSH, null);
            startActivity(new Intent(MainActivity.this, PlayerNameActivity.class));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void help(View view) {
        try {
            if (can_click_btn == false) return;
            can_click_btn = false;
            MainActivity.status = "help";
            startActivity(new Intent(MainActivity.this, CaractersInformationActivity.class));
        }catch (Exception e) {

        }
    }
}
//android:icon="@mipmap/ic_launcher"
//android:roundIcon="@mipmap/ic_launcher_round"
//"Nima, Naweed, Paarsaa, Naazin, Negeen, Neeloofaar, Mahiar, Naarin, Arween, Aazeen, Omeed"
//android:label="@string/app_name"