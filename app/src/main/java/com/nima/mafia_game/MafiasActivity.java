package com.nima.mafia_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MafiasActivity extends MenuOptions implements View.OnLongClickListener {

    public static String[] mafia_type;
    public static Activity activity;

    public int simple_mafia_number;
    public int mafias_number;

    public boolean pause_music;

    TextView simple_mafia_counter_tv;
    ImageView simple_mafia_img, god_father_img, marde_khavi_img;
    ImageView bombalot_img, sylenser_img;
    CheckBox god_father_cb, bombalot_cb;
    CheckBox sylenser_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mafias);
        activity = this;
        pause_music = true;
        inItComponents();

    }


    private void inItComponents() {

        simple_mafia_number = MafiaCitizenStatisticsActivity.mafia_number;
        mafias_number = MafiaCitizenStatisticsActivity.mafia_number;
        simple_mafia_counter_tv = (TextView) findViewById(R.id.simple_mafia_counter_tv);
        simple_mafia_counter_tv.setText("تعداد مافیاهای ساده : " + simple_mafia_number);

        mafia_type = new String[mafias_number];

        simple_mafia_img = (ImageView) findViewById(R.id.mafia_img);
        simple_mafia_img.setOnLongClickListener(this);
        god_father_img = (ImageView) findViewById(R.id.god_father_img);
        god_father_img.setOnLongClickListener(this);
        marde_khavi_img = (ImageView) findViewById(R.id.marde_khavi_img);
        marde_khavi_img.setOnLongClickListener(this);
        bombalot_img = (ImageView) findViewById(R.id.bombalot_img);
        bombalot_img.setOnLongClickListener(this);
        sylenser_img = (ImageView) findViewById(R.id.sylenser_img);
        sylenser_img.setOnLongClickListener(this);
        god_father_cb = (CheckBox) findViewById(R.id.god_father_CB);
        bombalot_cb = (CheckBox) findViewById(R.id.bombalot_CB);
        sylenser_cb = (CheckBox) findViewById(R.id.sylenser_CB);

    }


    public void next_page(View view) {

        try {

            if (MainActivity.can_click_btn == false) return;
            MainActivity.can_click_btn = false;

            int i = 0;
            if (god_father_cb.isChecked()) mafia_type[i++] = "گادفادر";
            if (bombalot_cb.isChecked()) mafia_type[i++] = "بمبلات";
            if (sylenser_cb.isChecked()) mafia_type[i++] = "سایلنتر";
            for (int j = i; j < mafias_number; j++) {
                mafia_type[j] = "مافیا";
            }

        /*for (int i1 = 0; i1 < mafias_number; i1++) {
            System.out.println("i = " + i1 + mafia_type[i1]);
        }
        System.out.println("");*/

            MainActivity.can_pause = false;
            startActivity(new Intent(MafiasActivity.this, CitizensActivity.class));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void prev_step(View view) {

        finish();

    }

    public void checkChange(View view) {

        int id = view.getId();

        if (id == R.id.god_father_img){

            if (simple_mafia_number == 0 && !god_father_cb.isChecked()) return;
            god_father_cb.setChecked(!god_father_cb.isChecked());
            if (god_father_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.god_father_CB) {

            if (simple_mafia_number == 0 && god_father_cb.isChecked()) {
                god_father_cb.setChecked(!god_father_cb.isChecked());
                return;
            }
            if (god_father_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.bombalot_img) {

            if (simple_mafia_number == 0 && !bombalot_cb.isChecked()) return;
            bombalot_cb.setChecked(!bombalot_cb.isChecked());
            if (bombalot_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.bombalot_CB) {

            if (simple_mafia_number == 0 && bombalot_cb.isChecked()) {
                bombalot_cb.setChecked(!bombalot_cb.isChecked());
                return;
            }
            if (bombalot_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.sylenser_img) {

            if (simple_mafia_number == 0 && !sylenser_cb.isChecked()) return;
            sylenser_cb.setChecked(!sylenser_cb.isChecked());
            if (sylenser_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.sylenser_CB) {

            if (simple_mafia_number == 0 && sylenser_cb.isChecked()) {
                sylenser_cb.setChecked(!sylenser_cb.isChecked());
                return;
            }
            if (sylenser_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }

    }

    private void simpleMafiaNumberChanged(int n) {

        simple_mafia_number += n;
        simple_mafia_counter_tv.setText("تعداد مافیاهای ساده : " + simple_mafia_number);

    }

    @Override
    public boolean onLongClick(View view) {

        try {
            if (MainActivity.can_click_btn == false) return true;
            MainActivity.can_click_btn = false;

            int id = view.getId();

            if (id == R.id.mafia_img) MainActivity.status = "مافیا";
            else if (id == R.id.god_father_img) MainActivity.status = "گادفادر";
            else if (id == R.id.marde_khavi_img) MainActivity.status = "marde_khavi";
            else if (id == R.id.bombalot_img) MainActivity.status = "بمبلات";
            else if (id == R.id.sylenser_img) MainActivity.status = "سایلنتر";

            MainActivity.des_status = true;

            startActivity(new Intent(MafiasActivity.this, CaractersInformationActivity.class));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return true; // short click will not work
    }
}