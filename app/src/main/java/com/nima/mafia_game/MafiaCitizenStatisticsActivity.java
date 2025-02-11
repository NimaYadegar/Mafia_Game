package com.nima.mafia_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MafiaCitizenStatisticsActivity extends MenuOptions implements View.OnLongClickListener {

    public static int players_number;
    public static int mafia_number;
    public static int citizen_number;
    public static int joker_number;

    public static Activity activity;


    TextView citizen_tv;
    TextView mafia_tv;
    CheckBox joker_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mafia_citizen_statistics);
        activity = this;

        joker_number = 0;

        citizen_tv = (TextView) findViewById(R.id.citizen_counter_tv);
        citizen_tv.setOnLongClickListener(this);
        mafia_tv = (TextView) findViewById(R.id.mafia_counter_tv);
        mafia_tv.setOnLongClickListener(this);
        joker_checkbox = (CheckBox) findViewById(R.id.joker_checkbox);
        joker_checkbox.setOnLongClickListener(this);
        joker_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    joker_number += 1;
                    citizen_number -=1;
                }else {
                    joker_number -=1;
                    citizen_number += 1;
                }
                show(citizen_number, mafia_number);
            }
        });

        dividing_players();


    }




    private void dividing_players() {

        players_number = PlayerNameActivity.players_number;
        mafia_number = (int) Math.round((0.3 * players_number) - 0.01);
        citizen_number = players_number - mafia_number;

        //System.out.println(players_number + "\n" + mafia_number + "\n" + citizen_number);

        show(citizen_number, mafia_number);

    }

    private void show(int citizen_number, int mafia_number) {

        citizen_tv.setText("تعداد شهروندان : " + citizen_number);
        mafia_tv.setText("تعداد مافیاها : " + mafia_number);

    }

    public void next_page(View view) {

        try {
            if (MainActivity.can_click_btn == false) return;
            MainActivity.can_click_btn = false;
            MainActivity.can_pause = false;
            startActivity(new Intent(MafiaCitizenStatisticsActivity.this, MafiasActivity.class));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean onLongClick(View view) {

        if (MainActivity.can_click_btn == false) return true;
        MainActivity.can_click_btn = false;

        if (view.getId() == citizen_tv.getId()) MainActivity.status = "citizen";
        else if (view.getId() == mafia_tv.getId()) MainActivity.status = "mafia";
        else if (view.getId() == joker_checkbox.getId()) MainActivity.status = "joker";

        startActivity(new Intent(MafiaCitizenStatisticsActivity.this, CaractersInformationActivity.class));

        return true; // short click will not work
    }

    public void  prev_step(View view) {

        finish();


    }
}