package com.nima.mafia_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class CitizensActivity extends MenuOptions implements View.OnLongClickListener{

    public static String[] shahrvand_type;
    public static Activity activity;

    public int simple_shahrvand_number;
    public int shahrvandan_number;

    public boolean pause_music;

    TextView simple_shahrvand_counter_tv;
    ImageView simple_shahrvand_img, doctor_img, karagah_img;
    ImageView taktirandaz_img, rointan_img, saghi_img, keshish_img;
    CheckBox doctor_cb, karagah_cb, taktirandaz_cb;
    CheckBox rointan_cb, saghi_cb, keshish_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizens);
        activity = this;
        pause_music = true;
        inItComponents();

    }

    private void inItComponents() {

        simple_shahrvand_number = MafiaCitizenStatisticsActivity.citizen_number;
        shahrvandan_number = MafiaCitizenStatisticsActivity.citizen_number;
        simple_shahrvand_counter_tv = (TextView) findViewById(R.id.simple_shahrvand_counter_tv);
        simple_shahrvand_counter_tv.setText("تعداد شهروندان ساده : " + simple_shahrvand_number);

        shahrvand_type = new String[shahrvandan_number];

        simple_shahrvand_img = (ImageView) findViewById(R.id.shahrvand_img);
        simple_shahrvand_img.setOnLongClickListener(this);
        doctor_img = (ImageView) findViewById(R.id.doctor_img);
        doctor_img.setOnLongClickListener(this);
        karagah_img = (ImageView) findViewById(R.id.karagah_img);
        karagah_img.setOnLongClickListener(this);
        taktirandaz_img = (ImageView) findViewById(R.id.taktirandaz_img);
        taktirandaz_img.setOnLongClickListener(this);
        rointan_img = (ImageView) findViewById(R.id.rointan_img);
        rointan_img.setOnLongClickListener(this);
        saghi_img = (ImageView) findViewById(R.id.saghi_img);
        saghi_img.setOnLongClickListener(this);
        keshish_img = (ImageView) findViewById(R.id.keshish_img);
        keshish_img.setOnLongClickListener(this);
        doctor_cb = (CheckBox) findViewById(R.id.doctor_CB);
        karagah_cb = (CheckBox) findViewById(R.id.karagah_CB);
        taktirandaz_cb = (CheckBox) findViewById(R.id.taktirandaz_CB);
        rointan_cb = (CheckBox) findViewById(R.id.rointan_CB);
        saghi_cb = (CheckBox) findViewById(R.id.saghi_CB);
        keshish_cb = (CheckBox) findViewById(R.id.keshish_CB);

    }



    public void next_page(View view) {

        try {

            if (MainActivity.can_click_btn == false) return;
            MainActivity.can_click_btn = false;

            int i = 0;
            if (doctor_cb.isChecked()) shahrvand_type[i++] = "دکتر";
            if (karagah_cb.isChecked()) shahrvand_type[i++] = "کاراگاه";
            if (taktirandaz_cb.isChecked()) shahrvand_type[i++] = "اسنایپر";
            if (rointan_cb.isChecked()) shahrvand_type[i++] = "روئینتن";
            if (saghi_cb.isChecked()) shahrvand_type[i++] = "ساقی";
            if (keshish_cb.isChecked()) shahrvand_type[i++] = "کشیش";
            for (int j = i; j < shahrvandan_number; j++) {
                shahrvand_type[j] = "شهروند";
            }

            MainActivity.can_pause = false;
            startActivity(new Intent(CitizensActivity.this, ShufflingActivity.class));

        /*for (int k = 0; k < shahrvandan_number; k++) {
            System.out.println("i = " + k + " = " + shahrvand_type[k]);
        }*/
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void prev_step(View view) {

        finish();

    }

    public void checkChange(View view) {

        int id = view.getId();

        if (id == R.id.doctor_img){

            if (simple_shahrvand_number == 0 && !doctor_cb.isChecked()) return;
            doctor_cb.setChecked(!doctor_cb.isChecked());
            if (doctor_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.doctor_CB) {

            if (simple_shahrvand_number == 0 && doctor_cb.isChecked()) {
                doctor_cb.setChecked(!doctor_cb.isChecked());
                return;
            }
            if (doctor_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.karagah_img) {

            if (simple_shahrvand_number == 0 && !karagah_cb.isChecked()) return;
            karagah_cb.setChecked(!karagah_cb.isChecked());
            if (karagah_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.karagah_CB) {

            if (simple_shahrvand_number == 0 && karagah_cb.isChecked()) {
                karagah_cb.setChecked(!karagah_cb.isChecked());
                return;
            }
            if (karagah_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.taktirandaz_img) {

            if (simple_shahrvand_number == 0 && !taktirandaz_cb.isChecked()) return;
            taktirandaz_cb.setChecked(!taktirandaz_cb.isChecked());
            if (taktirandaz_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.taktirandaz_CB) {

            if (simple_shahrvand_number == 0 && taktirandaz_cb.isChecked()) {
                taktirandaz_cb.setChecked(!taktirandaz_cb.isChecked());
                return;
            }
            if (taktirandaz_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.rointan_img) {

            if (simple_shahrvand_number == 0 && !rointan_cb.isChecked()) return;
            rointan_cb.setChecked(!rointan_cb.isChecked());
            if (rointan_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.rointan_CB) {

            if (simple_shahrvand_number == 0 && rointan_cb.isChecked()) {
                rointan_cb.setChecked(!rointan_cb.isChecked());
                return;
            }
            if (rointan_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.saghi_img) {

            if (simple_shahrvand_number == 0 && !saghi_cb.isChecked()) return;
            saghi_cb.setChecked(!saghi_cb.isChecked());
            if (saghi_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.saghi_CB) {

            if (simple_shahrvand_number == 0 && saghi_cb.isChecked()) {
                saghi_cb.setChecked(!saghi_cb.isChecked());
                return;
            }
            if (saghi_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.keshish_img) {

            if (simple_shahrvand_number == 0 && !keshish_cb.isChecked()) return;
            keshish_cb.setChecked(!keshish_cb.isChecked());
            if (keshish_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }else if (id == R.id.keshish_CB) {

            if (simple_shahrvand_number == 0 && keshish_cb.isChecked()) {
                keshish_cb.setChecked(!keshish_cb.isChecked());
                return;
            }
            if (keshish_cb.isChecked()) simpleMafiaNumberChanged(-1);
            else simpleMafiaNumberChanged(1);

        }

    }

    private void simpleMafiaNumberChanged(int n) {

        simple_shahrvand_number += n;
        simple_shahrvand_counter_tv.setText("تعداد شهروندان ساده : " + simple_shahrvand_number);

    }

    @Override
    public boolean onLongClick(View view) {

        try {

            if (MainActivity.can_click_btn == false) return true;
            MainActivity.can_click_btn = false;

            int id = view.getId();

            if (id == R.id.shahrvand_img) MainActivity.status = "شهروند";
            else if (id == R.id.doctor_img) MainActivity.status = "دکتر";
            else if (id == R.id.karagah_img) MainActivity.status = "کاراگاه";
            else if (id == R.id.taktirandaz_img) MainActivity.status = "اسنایپر";
            else if (id == R.id.rointan_img) MainActivity.status = "روئینتن";
            else if (id == R.id.saghi_img) MainActivity.status = "ساقی";
            else if (id == R.id.keshish_img) MainActivity.status = "کشیش";

            MainActivity.des_status = true;

            startActivity(new Intent(CitizensActivity.this, CaractersInformationActivity.class));
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true; // short click will not work
    }

}