package com.nima.mafia_game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ShufflingActivity extends MenuOptions implements AdapterView.OnItemLongClickListener {

    public static String roll_message;
    public static String game_summery;
    public static boolean first_time;

    public TextToSpeech textToSpeech;
    public Timer timer_holder;

    public List<String> names_list;
    public List<String> rolls_list;
    public List<String> chose_names;
    public List<String> days_name_list;
    public List<String> nights_name_list;
    public List<String> names_in_game_list;

    public String[] shuffled_names;
    public String[] shuffled_rolls;
    public String[] names_in_game;
    public String[] rolls_out_of_game;

    public String what_happened_last_night;
    // Targets
    public String god_father_target;
    public String sylenser_target;
    public String doctor_target;
    public String detective_target;
    public String sniper_target;
    public String saghi_target;
    public String pop_target;
    // Targets
    private String saghi_target_last_night;
    private String sylenser_target_last_night;
    private String silenced_person;

    public int players_number;
    public int mafias_number;
    public int citizens_number;
    public int joker_number;
    public int days_nights_counter;
    public int knowing_left;
    public int out_mafias;
    public int out_citizens;
    public int countdown;
    public int calling_index;
    public int sniper_bullets;
    public int secret_detective_code_true;
    public int secret_detective_code_false;
    public int show_detective_code;

    public boolean first_night;
    public boolean can_click_names_list_night;
    public boolean can_click_player_called;
    public boolean night_just_started;
    public boolean rointan_can_die;
    public boolean change_rointan_can_die;
    public boolean rointan_can_die_from_before;
    private boolean doctor_saved_himself;
    private boolean pop_saved_himself;
    private boolean bombalot_out;
    private boolean already_told;
    private boolean detective_in_game;


    ListView shuffled_names_listView;
    ArrayAdapter<String> names_adapter;

    RelativeLayout shuffling_layout;
    RelativeLayout days_layout;
    RelativeLayout nights_layout;

    ListView chose_names_list_view_day;
    ArrayAdapter<String> days_name_adapter;

    ListView chose_names_list_view_nights;
    ArrayAdapter<String> nights_name_adapter;

    TextView days_tv;
    TextView knowing_counter_btn;
    TextView nights_tv;
    public TextView player_called_btn = null;
    TextView extra_tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffling);
        inItComponents();

        shuffling();

        first_time = true;

        PlayerNameActivity.activity.finish();
        MafiaCitizenStatisticsActivity.activity.finish();
        MafiasActivity.activity.finish();
        CitizensActivity.activity.finish();


    }

    @Override
    public void onBackPressed() {

        if (MainActivity.can_click_btn == false) return;
        MainActivity.can_click_btn = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("آیا می خواهید این صفحه را ببندید؟");
        builder.setMessage("از این دست بازی خارج می شوید؟");
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("خیر", null);
        builder.create().show();
        MainActivity.can_click_btn = true;

    }

    private void inItComponents() {


        players_number = PlayerNameActivity.players_number;
        mafias_number = MafiaCitizenStatisticsActivity.mafia_number;
        citizens_number = MafiaCitizenStatisticsActivity.citizen_number;
        joker_number = MafiaCitizenStatisticsActivity.joker_number;

        what_happened_last_night = "";
        game_summery = "\n\n" + "گزارش بازی : " + "\n\n";

        doctor_saved_himself = false;
        pop_saved_himself = false;
        rointan_can_die = false;
        change_rointan_can_die = false;
        rointan_can_die_from_before = false;
        bombalot_out = false;
        already_told = false;
        detective_in_game = false;
        saghi_target_last_night = " ";
        sylenser_target_last_night = " ";
        silenced_person = " ";
        sniper_bullets = mafias_number;

        god_father_target = " ";
        sylenser_target = " ";
        doctor_target = " ";
        detective_target = " ";
        sniper_target = " ";
        saghi_target = " ";
        pop_target = " ";

        out_citizens = 0;
        out_mafias = 0;
        secret_detective_code_true = 0;
        secret_detective_code_false = 0;
        show_detective_code = 0;

        shuffled_names = new String[players_number];
        shuffled_rolls = new String[players_number];

        names_list = new ArrayList<>();
        rolls_list = new ArrayList<>();

        shuffled_names_listView = (ListView) findViewById(R.id.shuffled_names_list);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }

            }
        });


    }

    private void shuffling() {

        for (int i = 0; i < players_number; i++) {
            names_list.add(PlayerNameActivity.players_names[i]);
        }

        for (int i = 0; i < mafias_number; i++) {
            rolls_list.add(MafiasActivity.mafia_type[i]);
        }
        for (int i = 0; i < citizens_number; i++) {
            rolls_list.add(CitizensActivity.shahrvand_type[i]);
            if (CitizensActivity.shahrvand_type[i].equals("کاراگاه" )) detective_in_game = true;
        }
        for (int i = 0; i < joker_number; i++) {
            rolls_list.add("جوکر");
        }

        int same_index = 0;
        while (!names_list.isEmpty() && !rolls_list.isEmpty()) {
            shuffled_names[same_index] = names_list.get(new Random().nextInt(names_list.size()));
            names_list.remove(shuffled_names[same_index]);
            shuffled_rolls[same_index] = rolls_list.get(new Random().nextInt(rolls_list.size()));
            rolls_list.remove(shuffled_rolls[same_index]);
            same_index++;
        }

        for (int i = 0; i < players_number; i++) {
            names_list.add(shuffled_names[i]);
            game_summery = game_summery + "\t\t" + "بازیکن " + shuffled_names[i] +
                    " در نقش " + shuffled_rolls[i] + " بود.\n";
        }
        game_summery = game_summery + "\n\t\t";

        chose_names = names_list;

        names_adapter = new ArrayAdapter<String>(this, R.layout.simple_list_text_view, chose_names);
        shuffled_names_listView.setAdapter(names_adapter);
        shuffled_names_listView.setOnItemLongClickListener(this);

        for (int i = 0; i < players_number; i++) {
            System.out.println(shuffled_names[i] + " : " + shuffled_rolls[i]);
        }


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        try {
            if (MainActivity.can_click_btn == false) return true;
            MainActivity.can_click_btn = false;
            for (int j = 0; j < players_number; j++) {
                if (chose_names.get(i) == shuffled_names[j]) {

                    MainActivity.status = shuffled_rolls[j];
                    MainActivity.des_status = false;
                    roll_message = "شما " + shuffled_names[j] + " در نقش " + shuffled_rolls[j] + " هستید.";
                    if (isMafia(shuffled_rolls[j])) {
                        roll_message = roll_message + "\n\n" + showMafiaFriends(shuffled_names[j]);
                    }

                    startActivity(new Intent(ShufflingActivity.this, CaractersInformationActivity.class));

                    chose_names.remove(i);
                    names_adapter.notifyDataSetChanged();

                    if (chose_names.isEmpty()) days();

                    return true;
                }
            }
            return true;
        }catch (Exception e) {

        }
        return true; // short click will not work
    }

    private String showMafiaFriends(String shuffled_name) {

        String mafia_friends = "همدستان : ";
        for (int i = 0; i < players_number; i++) {
            if (shuffled_names[i] != shuffled_name && isMafia(shuffled_rolls[i])) {
                mafia_friends = mafia_friends + shuffled_names[i] +  " : " + shuffled_rolls[i] + "، ";
            }
        }
        return mafia_friends;
    }

    private boolean isMafia(String shuffled_roll) {

        if (shuffled_roll == "مافیا" || shuffled_roll == "گادفادر" || shuffled_roll == "سایلنتر"
            || shuffled_roll == "بمبلات") {
            return true;
        }else {
            return false;
        }

    }

    private boolean isCitizen(String roll) {

        if (roll == "شهروند" || roll == "دکتر" ||
                roll == "کاراگاه" || roll == "اسنایپر" ||
                roll == "روئینتن" || roll == "ساقی" || roll == "کشیش") {
            return true;
        }else {
            return false;
        }

    }

    private void days() {

        days_nights_counter = 1;
        knowing_left = mafias_number;

        days_tv = (TextView) findViewById(R.id.days_tv);
        days_tv.setText("روز : " + days_nights_counter + "\n"
                + "در رای گیری چه کسی بیرون انداخته می شود؟");

        first_night = true;

        rolls_out_of_game = new String[players_number];

        knowing_counter_btn = (TextView) findViewById(R.id.knowing_counter_btn);
        knowing_counter_btn.setText(" استعلام های باقیمانده : " + knowing_left + " ");
        knowing_counter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int out_players = getOutPlayersNumber();
                out_mafias = 0;
                out_citizens = 0;
                if (out_players > 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ShufflingActivity.this);
                    builder.setTitle("آیا مطمئن هستید؟");
                    final TextView textView = new TextView(ShufflingActivity.this);
                    textView.setTextSize(20f);
                    textView.setText("استعلام افراد خارج از بازی داده شود؟");
                    builder.setView(textView);
                    builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int another_i) {

                            for (int i = 0; i < out_players; i++) {
                                if (isMafia(rolls_out_of_game[i])) out_mafias++;
                                else if (isCitizen(rolls_out_of_game[i])) out_citizens++;
                            }
                            Toast.makeText(ShufflingActivity.this, "مافیا : " + out_mafias +
                                    "\nشهروند : " + out_citizens, Toast.LENGTH_LONG).show();
                            knowing_left--;
                            knowing_counter_btn.setText(" استعلام های باقیمانده : " + knowing_left + " ");
                            if (knowing_left == 0) {
                                knowing_counter_btn.setVisibility(TextView.GONE);
                            }

                            game_summery = game_summery + "در روز " + days_nights_counter +
                                    " : استعلام افراد خارج از بازی داده شد : \n" +
                                    "مافیا : " + out_mafias +
                                    "\nشهروند : " + out_citizens + "\n\n";

                        }
                    });
                    builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    builder.create().show();

                }else {
                    Toast.makeText(ShufflingActivity.this, "هنوز هیچ کس بیرون نرفته است", Toast.LENGTH_SHORT).show();
                }
            }
        });

        days_name_list = new ArrayList<>();
        names_in_game_list = new ArrayList<>();
        names_in_game = new String[players_number];
        if (mafias_number + citizens_number > 3) days_name_list.add("کسی بیرون نمیرود");
        for (int i = 0; i < players_number; i++) {
            days_name_list.add(shuffled_names[i]);
            names_in_game_list.add(shuffled_names[i]);
            names_in_game[i] = shuffled_names[i];
        }

        days_name_adapter =  new ArrayAdapter<String>(this, R.layout.simple_list_text_view, days_name_list);
        chose_names_list_view_day = (ListView) findViewById(R.id.chose_names_list_day);
        chose_names_list_view_day.setAdapter(days_name_adapter);
        chose_names_list_view_day.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (mafias_number + citizens_number + joker_number == 3){
                    days_name_list.remove("کسی بیرون نمیرود");
                    days_name_adapter.notifyDataSetChanged();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ShufflingActivity.this);
                builder.setTitle("آیا مطمئن هستید؟");
                final TextView textView = new TextView(ShufflingActivity.this);
                textView.setTextSize(20f);
                if (days_name_list.get(i).equals(" ")) return;

                if (days_name_list.get(i) == "کسی بیرون نمیرود") {
                    textView.setText("کسی بیرون نرود؟");
                }else {
                    for (int i1 = 0; i1 < players_number; i1++) {
                        textView.setText("آیا " + days_name_list.get(i) + " بیرون رود؟");
                    }
                }
                builder.setView(textView);
                builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int another_i) {

                        String holder = days_name_list.get(i).toString();
                        String name = days_name_list.get(i).toString();
                        String bombalot_name_holder = "";

                        if (isMafia(getRoll(name)) && bombalot_out == true) {
                            Toast.makeText(ShufflingActivity.this, "نمی توانید همدست خود را بیرون ببرید", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (holder != "کسی بیرون نمیرود") {
                            names_in_game_list.remove(holder);
                        }

                        for (int j = 0; j < players_number; j++) {
                            if (holder == shuffled_names[j]) {
                                holder = shuffled_rolls[j];
                                break;
                            }
                        }

//                        System.out.println("this is the holder : " + holder);
                        if (holder == "کسی بیرون نمیرود") {

                            game_summery = game_summery + "\t\t" + "در روز " + days_nights_counter +
                                    " : کسی با رای گیری از بازی خارج نشد." + "\n\n";

                            if (first_night) {
                                nights();
                                days_layout.setVisibility(TextView.INVISIBLE);
                                nights_layout.setVisibility(TextView.VISIBLE);
                                nights_tv.setText("شب : " + days_nights_counter);
                                first_night = false;
                            }else {
                                days_layout.setVisibility(TextView.INVISIBLE);
                                nights_layout.setVisibility(TextView.VISIBLE);
                                nights_tv.setText("شب : " + days_nights_counter);
                            }

//                            System.out.println("this is the holder : " + holder);

                        }
                        else if (holder == "جوکر"){
                            if (!bombalot_out) {
                                //game over
                                game_summery = game_summery + "\t\t" + "در روز : " + days_nights_counter +
                                " : جوکر " + name + " با رای گیری از بازی خارج شد و بازی را برد.";
                                winner("The Joker");
                            }
                            joker_number = 0;
                            days_name_list.remove(i);

                            if (bombalot_out == true) {
                                game_summery = game_summery + "\t\t" + "در روز : " +
                                        days_nights_counter + " : جوکر " + name +
                                        " به وسیله بمبلات " + bombalot_name_holder +
                                        " از بازی بیرون افتاد." + "\n\n";
                            }

                            if (mafias_number + citizens_number + joker_number == 3){
                                days_name_list.remove("کسی بیرون نمیرود");
                                days_name_list.remove(" ");
                                bombalot_out = false;
                                days_tv.setText("روز : " + days_nights_counter + "\n"
                                        + "در رای گیری چه کسی بیرون انداخته می شود؟");
                                days_name_adapter.notifyDataSetChanged();
                                return;
                            }

                            bombalot_out = false;
                            if (days_name_list.contains(" ")) {
                                days_name_list.add(days_name_list.indexOf(" ") , "کسی بیرون نمیرود");
                                days_name_list.remove(" ");
                            }
                            days_tv.setText("روز : " + days_nights_counter + "\n"
                                    + "در رای گیری چه کسی بیرون انداخته می شود؟");
                            if (first_night) {
                                nights();
                                days_layout.setVisibility(TextView.INVISIBLE);
                                nights_layout.setVisibility(TextView.VISIBLE);
                                nights_tv.setText("شب : " + days_nights_counter);
                                first_night = false;
                            }else {
                                days_layout.setVisibility(TextView.INVISIBLE);
                                nights_layout.setVisibility(TextView.VISIBLE);
                                nights_tv.setText("شب : " + days_nights_counter);
                            }

                            days_name_adapter.notifyDataSetChanged();

                        }else{

                            if (isMafia(holder)) {
                                rolls_out_of_game[getOutPlayersNumber()] = "مافیا";
                                mafias_number--;
                            }else if (isCitizen(holder)){
                                rolls_out_of_game[getOutPlayersNumber()] = "شهروند";
                                citizens_number--;
                            }

                            if (bombalot_out == true) {
                                game_summery = game_summery + "\t\t" + "در روز : " +
                                        days_nights_counter + " : " + holder + " " + name +
                                        " به وسیله بمبلات " + bombalot_name_holder + " از بازی بیرون افتاد.\n\n";
                            }else {
                                game_summery = game_summery + "\t\t" + "در روز : " +
                                        days_nights_counter + " : " + holder + " " + name +
                                        " در رای گیری بیرون انداخته شد.\n\n";
                            }

                            if (mafias_number == citizens_number) {
                                game_summery = game_summery + "\t\t" + "در روز : " +
                                        days_nights_counter + " : " +  " تعداد شهروندان و مافیا ها برابر شد و مافیا ها بازی را بردند.";
                                        winner("مافیاها");
                            }else if (mafias_number == 0){
                                game_summery = game_summery + "\t\t" + "در روز : " +
                                        days_nights_counter + " : " + " تمام مافیا ها از بازی بیرون انداخته شدند و شهروندان بازی را بردند.";
                                winner("شهروندان");
                            }

                            days_name_list.remove(i);

                            if (mafias_number + citizens_number + joker_number == 3){
                                days_name_list.remove("کسی بیرون نمیرود");
                                days_name_list.remove(" ");
                                bombalot_out = false;
                                System.out.println("bombalot out = " + bombalot_out);
                                days_tv.setText("روز : " + days_nights_counter + "\n"
                                        + "در رای گیری چه کسی بیرون انداخته می شود؟");
                                days_name_adapter.notifyDataSetChanged();
                                return;
                            }



                            if (holder != "بمبلات") {
                                bombalot_out = false;
                                System.out.println("bombalot out = " + bombalot_out);
                                if (days_name_list.contains(" ")) {
                                    days_name_list.add(days_name_list.indexOf(" ") , "کسی بیرون نمیرود");
                                    days_name_list.remove(" ");
                                }
                                days_tv.setText("روز : " + days_nights_counter + "\n"
                                        + "در رای گیری چه کسی بیرون انداخته می شود؟");
                                if (first_night) {
                                    nights();
                                    days_layout.setVisibility(TextView.INVISIBLE);
                                    nights_layout.setVisibility(TextView.VISIBLE);
                                    nights_tv.setText("شب : " + days_nights_counter);
                                    first_night = false;
                                }else {
                                    days_layout.setVisibility(TextView.INVISIBLE);
                                    nights_layout.setVisibility(TextView.VISIBLE);
                                    nights_tv.setText("شب : " + days_nights_counter);
                                }
                            }else {
                                days_name_list.add(days_name_list.indexOf("کسی بیرون نمیرود") , " ");
                                days_name_list.remove("کسی بیرون نمیرود");
                                if (mafias_number > 0) Toast.makeText(ShufflingActivity.this, "بمبلات بیرون انداخته شد", Toast.LENGTH_LONG).show();
                                textToSpeech.speak("Attention, BombALot is out, Give the phone to " +
                                        name + ", and " + name + " should choose a player to take out", TextToSpeech.QUEUE_FLUSH, null);
                                bombalot_out = true;
                                bombalot_name_holder = name;
                                days_tv.setText("روز : " + days_nights_counter + "\n"
                                        + "بمبلات چه کسی را همراه خود بیرون می برد؟");
                            }

                            days_name_adapter.notifyDataSetChanged();

                        }
                    }
                });
                builder.setNegativeButton("برگشت", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                builder.create().show();

            }
        });

        shuffling_layout = (RelativeLayout) findViewById(R.id.shuffling_layout);
        shuffling_layout.setVisibility(ListView.GONE);
        days_layout = (RelativeLayout) findViewById(R.id.days_layout);
        days_layout.setVisibility(ListView.VISIBLE);


    }

    private void winner(String witch_roll) {

        try {
            if (MainActivity.can_click_btn == false) return;
            MainActivity.can_click_btn = false;
            MainActivity.status = witch_roll;
            MainActivity.can_pause = false;
            startActivity(new Intent(ShufflingActivity.this, CaractersInformationActivity.class));
            finish();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private int getOutPlayersNumber() {
        return players_number - (mafias_number + citizens_number + joker_number);
    }

    private void nights() {

        can_click_names_list_night = true;
        can_click_player_called = true;

        nights_tv = (TextView) findViewById(R.id.nights_tv);
        nights_tv.setText("شب : " + days_nights_counter);
        player_called_btn = (TextView) findViewById(R.id.player_called_btn);
        extra_tv = (TextView) findViewById(R.id.extra_tv);

        nights_name_list = new ArrayList<>();

        // am am antart trmi pech khst
        //nights_name_list = names_in_game_list;
        // trn

        nights_name_adapter = new ArrayAdapter<String>(this, R.layout.simple_list_text_view, nights_name_list);
        chose_names_list_view_nights = (ListView) findViewById(R.id.chose_names_list_night);
        chose_names_list_view_nights.setAdapter(nights_name_adapter);
        chose_names_list_view_nights.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!can_click_names_list_night) return;

                targetingSystem(names_in_game[calling_index - 1], getRoll(names_in_game[calling_index - 1]), nights_name_list.get(i).toString());

            }
        });


        player_called_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!can_click_player_called) return;

                extra_tv.setVisibility(TextView.INVISIBLE);

                if (player_called_btn.getText().toString().equals(" شروع شب ")) {

                    game_summery = game_summery + "\n\n\t\t" + "شب : " + days_nights_counter +
                            " : \n\n";

                    if (detective_in_game) {
                        show_detective_code = new Random().nextInt(9) + 1;
                    }

                    night_just_started = true;

                    can_click_player_called = false;

                    textToSpeech.speak("night starting in T-Minus, " , TextToSpeech.QUEUE_FLUSH, null);

                    countdown = 5;
                    timer_holder = new Timer();

                    timer_holder.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if (countdown > 0) {
                                        textToSpeech.speak("" + countdown, TextToSpeech.QUEUE_FLUSH, null);
                                        player_called_btn.setText(" شروع در : " + countdown + " ");
                                        countdown--;
                                    }else{

                                        callOneByOne();

                                        can_click_player_called = true;
                                        timer_holder.cancel();
                                    }


                                }
                            });

                        }
                    }, 2000, 1000);


                }else if (player_called_btn.getText().toString().equals(" ادامه ")) {

                    resume();

                }else if (player_called_btn.getText().toString().equals(" دیدن نتایج ")) {

                    // say dead people, secret code and bla bla bla

                    calculatingNightEvents();

                    night_just_started = true;
                    days_nights_counter++;
                    days_tv.setText("روز : " + days_nights_counter + "\nدر رای گیری چه کسی بیرون انداخته می شود؟");



                    //System.out.println(god_father_target);

                    /// IF JOKER OUT (JOKER_NUMBER = 0;) DON'T FORGET THIS OR
                    /// YOU ARE REALLY IN A BIIIIIIB


                }else {

                    if (isNoneNight(getRoll(names_in_game[calling_index])) ||
                            (getRoll(names_in_game[calling_index]).equals("اسنایپر") && sniper_bullets == 0) ) {

                        if (getRoll(names_in_game[calling_index]).equals("اسنایپر")) {
                            Toast.makeText(ShufflingActivity.this, "تیر های شما تمام شده است", Toast.LENGTH_LONG).show();
                        }
                        countdown = 10;

                        timer_holder = new Timer();
                        timer_holder.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                can_click_player_called = false;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        if (countdown > 0) {
                                            player_called_btn.setText(" ادامه در : " + countdown + " ");
                                            countdown--;
                                        }else {
                                            player_called_btn.setText(" ادامه ");
                                            can_click_player_called = true;
                                            timer_holder.cancel();
                                        }

                                    }
                                });
                            }
                        }, 0, 1000);

                    }else {// night caracters that will do something in the night

                        String roll_in_here = getRoll(names_in_game[calling_index]);
                        extra_tv.setVisibility(TextView.INVISIBLE);
                        nights_name_list.clear();

                        nights_name_list = fillNightsNameList(nights_name_list);

                        if (roll_in_here.equals("گادفادر")) {
                            nights_tv.setText(nights_tv.getText() + "\n" + "گادفادر، به چه کسی شلیک می کنید؟");
                            nights_name_list.remove(names_in_game[calling_index]);
                        }else if (roll_in_here.equals("سایلنتر")) {
                            nights_tv.setText(nights_tv.getText() + "\n" + "سایلنتر، چه کسی را ساکت می کنید؟");
                            nights_name_list.remove(names_in_game[calling_index]);
                            if (!sylenser_target_last_night.equals(" ")) {
                                nights_name_list.remove(sylenser_target_last_night);
                            }
                        }else if (roll_in_here.equals("دکتر")) {
                            nights_tv.setText(nights_tv.getText() + "\n" + "دکتر، چه کسی را نجات می دهید؟");
                            extra_tv.setVisibility(TextView.VISIBLE);
                            extra_tv.setText("تعداد نجات های خود(مانده) : 1");
                            if (doctor_saved_himself) {
                                nights_name_list.remove(names_in_game[calling_index]);
                                extra_tv.setText("تعداد نجات های خود(مانده) : 0");
                            }
                        }else if (roll_in_here.equals("کاراگاه")) {
                            nights_tv.setText(nights_tv.getText() + "\n" + "کاراگاه، استعلام چه کسی را می گیرید؟");
                            nights_name_list.remove(names_in_game[calling_index]);
                        }else if (roll_in_here.equals("اسنایپر") && sniper_bullets > 0) {
                            nights_tv.setText(nights_tv.getText() + "\n" + "اسنایپر، به چه کسی شلیک می کنید؟");
                            extra_tv.setVisibility(TextView.VISIBLE);
                            extra_tv.setText("تعداد تیر های باقیمانده : " + sniper_bullets);
                            nights_name_list.remove(names_in_game[calling_index]);
                            nights_name_list.add(0, "شلیک نمی کنم");
                        }else if (roll_in_here.equals("ساقی")) {
                            nights_tv.setText(nights_tv.getText() + "\n" + "ساقی، چه کسی را مست می کنید؟");
                            nights_name_list.remove(names_in_game[calling_index]);
                            if (!saghi_target_last_night.equals(" ")) {
                                nights_name_list.remove(saghi_target_last_night);
                            }
                        }else if (roll_in_here.equals("کشیش")) {
                            nights_tv.setText(nights_tv.getText() + "\n" + "کشیش، چه کسی را از ساکت بودن رها می کنید؟");
                            extra_tv.setVisibility(TextView.VISIBLE);
                            extra_tv.setText("تعداد رهایی از ساکت شدن خود(مانده) : 1");
                            if (pop_saved_himself) {
                                nights_name_list.remove(names_in_game[calling_index]);
                                extra_tv.setText("تعداد رهایی از ساکت شدن خود(مانده) : 0");
                            }
                        }

                        nights_name_adapter.notifyDataSetChanged();
                        player_called_btn.setVisibility(TextView.INVISIBLE);
                        chose_names_list_view_nights.setVisibility(ListView.VISIBLE);


                    }

                    calling_index++;

                }


            }
        });

        nights_layout = (RelativeLayout) findViewById(R.id.night_layout);

    }

    private void resume() {
        can_click_player_called = false;

        countdown = 5;
        timer_holder = new Timer();

        timer_holder.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (countdown > 0) {
                            player_called_btn.setText(" " + countdown + " ");
                            countdown--;
                        }else{

                            callOneByOne();

                            can_click_player_called = true;
                            timer_holder.cancel();
                        }


                    }
                });

            }
        }, 0, 1000);
    }

    private void calculatingNightEvents() {

        rointan_can_die_from_before = rointan_can_die;

        already_told = false;

        what_happened_last_night = "";
        game_summery = game_summery + "\n\n\t\t" + "در شب : " + days_nights_counter + " : ";

        if (!detective_target.equals(" ")) {

            if (getRoll(detective_target).equals("گادفادر")) {
                show_detective_code = secret_detective_code_false;
            }else if (isMafia(getRoll(detective_target))) {
                show_detective_code = secret_detective_code_true;
            }else if (isCitizen(getRoll(detective_target)) || getRoll(detective_target) == "جوکر") {
                show_detective_code = secret_detective_code_false;
            }

        }

        if (!saghi_target.equals(" ")) {
            String saghi_target_roll = getRoll(saghi_target);
            game_summery = game_summery + "\n\t\t" + "ساقی، " + saghi_target_roll +
                    " " + saghi_target + " را مست کرد." + "\n";
            if (saghi_target_roll.equals("گادفادر")) god_father_target = " ";
            else if (saghi_target_roll.equals("سایلنتر")) sylenser_target = saghi_target;
            else if (saghi_target_roll.equals("دکتر")) doctor_target = " ";
            else if (saghi_target_roll.equals("کاراگاه")) {

                if (getRoll(detective_target).equals("گادفادر")) {
                    show_detective_code = secret_detective_code_false;
                }else if (show_detective_code == secret_detective_code_true) {
                    show_detective_code = secret_detective_code_false;
                }else if ((show_detective_code == secret_detective_code_false)) {
                    show_detective_code = secret_detective_code_true;
                }

            }
            else if (saghi_target_roll.equals("اسنایپر")) sniper_target = " ";
            else if (saghi_target_roll.equals("روئینتن")) {
                rointan_can_die = true;
            }
            else if (saghi_target_roll.equals("کشیش")) pop_target = " ";

        }

        if (!pop_target.equals(" ")) {

            if (pop_target.equals(sylenser_target)) {
                sylenser_target = " ";
                game_summery = game_summery + "\n\t\t" + "کشیش، " + getRoll(pop_target) +
                        " " + pop_target + " از ساکت بودن نجات داد." + "\n";
            }

        }

        if (!sylenser_target.equals(" ")) {

            silenced_person = sylenser_target;
            what_happened_last_night = what_happened_last_night + silenced_person + ", you are silenced." + "\n";
            game_summery = game_summery + "\n\t\t" + "سایلنتر، " + getRoll(sylenser_target) +
                    " " + sylenser_target + " را ساکت کرد." + "\n";

        }

        if (!doctor_target.equals(" ")) {

             if (doctor_target.equals(god_father_target)) {
                 god_father_target = " ";
                 game_summery = game_summery + "\n\t\t" + "دکتر، " + getRoll(doctor_target) +
                         " " + doctor_target + " را نجات داد." + "\n";
             }

            else if (doctor_target.equals(sniper_target)) {

                if (!isCitizen(getRoll(sniper_target))) {
                    sniper_target = " ";
                    game_summery = game_summery + "\n\t\t" + "دکتر، " + getRoll(doctor_target) +
                            " " + doctor_target + " را نجات داد." + "\n";
                }

            }

        }

        if (!sniper_target.equals(" ")) {

            if (getRoll(sniper_target).equals("گادفادر")) {
                game_summery = game_summery + "\n\t\t" + "تیر اسنایپر به " + getRoll(sniper_target) +
                        " " + sniper_target + " برخورد کرد." + "\n";
            }else if (isMafia(getRoll(sniper_target)) || getRoll(sniper_target).equals("جوکر")) {

                calculateStatistics(sniper_target, getRoll(sniper_target));

                names_in_game_list.remove(sniper_target);
                days_name_list.remove(sniper_target);

                what_happened_last_night = what_happened_last_night + sniper_target + ", is out." + "\n";
                game_summery = game_summery + "\n\t\t" + "تیر اسنایپر به " + getRoll(sniper_target) +
                        " " + sniper_target + " برخورد کرد و او را بیرون انداخت." + "\n";

                already_told = true;



            }else if (isCitizen(getRoll(sniper_target))) {

                for (int s = 0; s < players_number; s++) {
                    if (shuffled_rolls[s].equals("اسنایپر")) {

                        calculateStatistics(shuffled_names[s], "اسنایپر");

                        names_in_game_list.remove(shuffled_names[s]);
                        days_name_list.remove(shuffled_names[s]);

                        what_happened_last_night = what_happened_last_night + shuffled_names[s] + ", is out." + "\n";
                        game_summery = game_summery + "\n\t\t" + "تیر اسنایپر به " + getRoll(sniper_target) +
                                " " + sniper_target + " برخورد کرد و در نتیجه اسنایپر از بازی بیرون افتاد." + "\n";

                        sniper_target = shuffled_names[s];
                        already_told = true;

                    }
                }

            }

        }

        if (!god_father_target.equals(" ")) {



            if (getRoll(god_father_target).equals("روئینتن") && rointan_can_die == false) {
                rointan_can_die_from_before = true;
                game_summery = game_summery + "\n\t\t" + "تیر گادفادر به " + getRoll(god_father_target) +
                        " " + god_father_target + " برخورد کرد." + "\n";
            }else {

                calculateStatistics(god_father_target, getRoll(god_father_target));

                names_in_game_list.remove(god_father_target);
                days_name_list.remove(god_father_target);
                game_summery = game_summery + "\n\t\t" + "تیر گادفادر به " + getRoll(god_father_target) +
                        " " + god_father_target + " برخورد کرد و او را بیرون انداخت." + "\n";

                if (god_father_target == sniper_target && already_told) {

                }else {
                    what_happened_last_night = what_happened_last_night + god_father_target + ", is out." + "\n";
                }


            }

        }

        rointan_can_die = rointan_can_die_from_before;

        days_name_adapter.notifyDataSetChanged();

        if (detective_in_game) {
            game_summery = game_summery + "\n\t\t" + "کد سری " + show_detective_code + " بود." + "\n";
            what_happened_last_night = what_happened_last_night + "secret code," + show_detective_code;
        }

        game_summery = game_summery + "\n\t\t" + "اتمام شب : " + days_nights_counter + "\n\n";

        god_father_target = " ";
        sylenser_target = " ";
        doctor_target = " ";
        detective_target = " ";
        sniper_target = " ";
        saghi_target = " ";
        pop_target = " ";


        AlertDialog.Builder builder = new AlertDialog.Builder(ShufflingActivity.this);
        builder.setTitle("اتفاقات دیشب");
        final TextView detective_tv = new TextView(ShufflingActivity.this);
        detective_tv.setTextSize(20f);
        if (what_happened_last_night.equals("")) {
            what_happened_last_night = "Nothing happened last night";
        }
            textToSpeech.speak(what_happened_last_night, TextToSpeech.QUEUE_FLUSH, null);

        detective_tv.setText(what_happened_last_night);
        builder.setView(detective_tv);
        builder.setCancelable(false);
        builder.setPositiveButton("فهمیدیم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (mafias_number + citizens_number + joker_number == 3){
                    days_name_list.remove("کسی بیرون نمیرود");
                    days_name_adapter.notifyDataSetChanged();
                }
                nights_layout.setVisibility(TextView.INVISIBLE);
                days_layout.setVisibility(TextView.VISIBLE);
                player_called_btn.setText(" شروع شب ");

                if (mafias_number == citizens_number) {
                    game_summery = game_summery + "\t\t" + "در شب : " +
                            days_nights_counter + " : " +  " تعداد شهروندان و مافیا ها برابر شد و مافیا ها بازی را بردند.";
                    winner("مافیاها");
                }else if (mafias_number == 0){
                    game_summery = game_summery + "\t\t" + "در شب : " +
                            days_nights_counter + " : " + " تمام مافیا ها از بازی بیرون انداخته شدند و شهروندان بازی را بردند.";
                    winner("شهروندان");
                }

            }
        });

        builder.create().show();

    }

    private void calculateStatistics(String name, String roll) {

        if (!names_in_game_list.contains(name)) return;

        if (roll.equals("جوکر")) {
            joker_number = 0;
        }
        else if (isMafia(roll)) {
            rolls_out_of_game[getOutPlayersNumber()] = "مافیا";
            mafias_number--;
        }else if (isCitizen(roll)){
            rolls_out_of_game[getOutPlayersNumber()] = "شهروند";
            citizens_number--;
        }

    }

    private List<String> fillNightsNameList(List<String> fill_nights_name_list) {
        for (int i = 0; i < (mafias_number + citizens_number + joker_number); i++) {
            fill_nights_name_list.add(names_in_game[i]);
        }
        return fill_nights_name_list;
    }

    private void targetingSystem(String targeter_name, String targeter, String target) {


        AlertDialog.Builder builder = new AlertDialog.Builder(ShufflingActivity.this);
        builder.setTitle("آیا مطمئن هستید؟");
        final TextView textView = new TextView(ShufflingActivity.this);
        textView.setTextSize(20f);
        textView.setText("آیا " + target + " انتخاب شود؟");
        builder.setView(textView);
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (targeter.equals("گادفادر")) {
                    god_father_target = target;
                    game_summery = game_summery + "\t\t" + " گادفادر " + targeter_name +
                            " به " + getRoll(target) + " " + target + " شلیک کرد." + "\n";
                }else if (targeter.equals("سایلنتر")) {
                    sylenser_target = target;
                    sylenser_target_last_night = target;
                    game_summery = game_summery + "\t\t" + " سایلنتر " + targeter_name +
                            " قصد ساکت کردن " + getRoll(target) + " " + target + " را داشت." + "\n";
                }else if (targeter.equals("دکتر")) {
                    if (target.equals(names_in_game[calling_index - 1])) {
                        doctor_saved_himself = true;
                    }
                    doctor_target = target;
                    game_summery = game_summery + "\t\t" + " دکتر " + targeter_name +
                            " قصد نجات دادن " + getRoll(target) + " " + target + " را داشت." + "\n";
                }else if (targeter.equals("کاراگاه")) {
                    detective_target = target;

                    List<String> detective_code = new ArrayList<>();

                    detective_code.clear();

                    for (int j = 1; j < 11; j++) {
                        detective_code.add("" + j);
                    }

                    String num_holder = detective_code.get(new Random().nextInt(detective_code.size()));
                    secret_detective_code_true = Integer.parseInt(num_holder);
                    detective_code.remove(num_holder);

                    num_holder = detective_code.get(new Random().nextInt(detective_code.size()));
                    secret_detective_code_false = Integer.parseInt(num_holder);
                    System.out.println(secret_detective_code_true + " : " + secret_detective_code_false);
                    detective_code.remove(num_holder);

                    game_summery = game_summery + "\t\t" + " کاراگاه " + targeter_name +
                            " استعلام " + getRoll(target) + " " + target + " را با کد سری " + secret_detective_code_true +
                            " گرفت." + "\n";

                    AlertDialog.Builder detective_builder = new AlertDialog.Builder(ShufflingActivity.this);
                    detective_builder.setTitle("کد سری : " + secret_detective_code_true);
                    final TextView detective_tv = new TextView(ShufflingActivity.this);
                    detective_tv.setTextSize(20f);
                    detective_tv.setText(" اگر استعلامتان درست باشد، فردا کد سری بالا را می شنوید");
                    detective_builder.setView(detective_tv);
                    detective_builder.setCancelable(false);
                    detective_builder.setPositiveButton("فهمیدم", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            chose_names_list_view_nights.setVisibility(ListView.INVISIBLE);
                            player_called_btn.setVisibility(TextView.VISIBLE);

                            nights_tv.setText("شب : " + days_nights_counter);

                            extra_tv.setVisibility(TextView.INVISIBLE);

                            resume();
                        }
                    });

                    detective_builder.create().show();


                }else if (targeter.equals("اسنایپر")) {
                    sniper_target = target;
                    if (target.equals("شلیک نمی کنم")) {
                        sniper_target = " ";
                        game_summery = game_summery + "\t\t" + " اسنایپر " + targeter_name +
                                " به کسی شلیک نکرد." + "\n";
                    }else {
                        sniper_bullets--;
                        game_summery = game_summery + "\t\t" + " اسنایپر " + targeter_name +
                                " به " + getRoll(target) + " " + target + " شلیک کرد";
                        if (sniper_bullets == 0) {
                            game_summery = game_summery + " و تیر هایش تمام شد." + "\n";
                        }else {
                            game_summery = game_summery + "." + "\n";
                        }
                    }
                }else if (targeter.equals("ساقی")) {
                    saghi_target = target;
                    saghi_target_last_night = target;
                    game_summery = game_summery + "\t\t" + " ساقی " + targeter_name +
                            " قصد مست کردن " + getRoll(target) + " " + target + " را داشت." + "\n";
                }else if (targeter.equals("کشیش")) {
                    if (target.equals(names_in_game[calling_index - 1])) {
                        pop_saved_himself = true;
                    }
                    pop_target = target;
                    game_summery = game_summery + "\t\t" + " کشیش " + targeter_name +
                            " قصد رها کردن از ساکت بودن " + getRoll(target) + " " + target + " را داشت." + "\n";
                }

                if (targeter.equals("کاراگاه")) {
                    return;
                }

                chose_names_list_view_nights.setVisibility(ListView.INVISIBLE);
                player_called_btn.setVisibility(TextView.VISIBLE);

                nights_tv.setText("شب : " + days_nights_counter);

                extra_tv.setVisibility(TextView.INVISIBLE);

                resume();

            }
        });
        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builder.create().show();



    }

    private boolean isNoneNight(String roll) {
        if (roll == "مافیا" || roll == "بمبلات" || roll == "شهروند" || roll == "روئینتن" || roll == "جوکر") {
            return true;
        }else {
            return false;
        }
    }

    private String getRoll(String name){
        for (int i = 0; i < players_number; i++) {
            if (name.equals(shuffled_names[i])) return shuffled_rolls[i];
        }
        return "there was no roll!";
    }

    private void callOneByOne() {

        if (night_just_started) {
            calling_index = 0;
            night_just_started = false;
            for (int i = 0; i < (mafias_number + citizens_number + joker_number); i++) {
                System.out.println(i + " : " + names_in_game[i] + " : " + names_in_game_list.get(i).toString());
                names_in_game[i] = names_in_game_list.get(i).toString();
            }
        }

        if (calling_index < (mafias_number + citizens_number + joker_number)) {

            player_called_btn.setText(" " + names_in_game[calling_index] + " ");
            textToSpeech.speak(names_in_game[calling_index] + ", Wake Up", TextToSpeech.QUEUE_FLUSH, null);
            System.out.println(calling_index + "    " + (mafias_number + citizens_number + joker_number));
        }else {

            textToSpeech.speak("Every One Wake Up and see what happened last night", TextToSpeech.QUEUE_FLUSH, null);
            player_called_btn.setText(" دیدن نتایج ");

        }


    }

}