package com.nima.mafia_game;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayerNameActivity extends MenuOptions {

    public List<String> names_array;
    public static String [] players_names;
    public static int players_number;
    public static Activity activity;


    SharedPreferences players_preferences;
    SharedPreferences.Editor players_editor;
    int existing_names_number = 0;

    ListView names_list;
    NamesAdapter namesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activity = this;

        setContentView(R.layout.activity_player_name);
        names_array = new ArrayList<>();
        names_list = (ListView) findViewById(R.id.names_list);
        namesAdapter = new NamesAdapter(this, names_array);
        names_list.setAdapter(namesAdapter);

        players_preferences = getSharedPreferences("Players Names", MODE_MULTI_PROCESS);
        players_editor = players_preferences.edit();

        try {
            existing_names_number = players_preferences.getInt("Players_number", 0);

            for (int i = 0; i < existing_names_number; i++) {
                if (players_preferences.getString("n" + i, "") != "") names_array.add(players_preferences.getString("n" + i, ""));
            }
            namesAdapter.notifyDataSetChanged();
        }catch (Exception e) {

        }


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();


        players_number = names_array.size();
        players_editor.clear();
        for (int i = 0; i < players_number; i++){
            players_editor.putString("n" + i, names_array.get(i));
        }
        players_editor.putInt("Players_number", players_number);

        players_editor.apply();

    }

    public void add_player(View view){

        if (MainActivity.can_click_btn == false) return;
        MainActivity.can_click_btn = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("اسم را وارد کنید");
        EditText input = new EditText(this);
        input.setInputType(1);
        input.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(input, 0);
                MainActivity.can_click_btn = true;
            }
        },100);
        builder.setView(input);
        builder.setPositiveButton("ثبت", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (input.getText().toString().trim().equals("")) {
                    Toast.makeText(PlayerNameActivity.this, "اسم نباید خالی باشد", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int j = 0; j < names_array.size(); j++) {
                    if (input.getText().toString().trim().toLowerCase().equals(names_array.get(j).toString().trim().toLowerCase())) {
                        Toast.makeText(PlayerNameActivity.this, "اسم نباید تکراری باشد", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                names_array.add(input.getText().toString().trim());
                namesAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("برگشت", null);
        builder.create().show();

    }

    public void next_page(View view){

        try {
            if (MainActivity.can_click_btn == false) return;
            MainActivity.can_click_btn = false;
            players_number = names_array.size();
            if (players_number < 6) {
                Toast.makeText(PlayerNameActivity.this, "تعداد بازیکنان باید حداقل 6 نفر باشد", Toast.LENGTH_LONG).show();
                MainActivity.can_click_btn = true;
                return;
            }else {
                players_names = new String[players_number];
                players_editor.clear();
                for (int i = 0; i < players_number; i++){
                    players_editor.putString("n" + i, names_array.get(i));
                    players_names[i] = names_array.get(i);
                }
                players_editor.putInt("Players_number", players_number);

                players_editor.apply();


                MainActivity.can_pause = false;
                startActivity(new Intent(PlayerNameActivity.this, MafiaCitizenStatisticsActivity.class));


            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void prev_step(View view) {

        finish();

    }


    private class NamesAdapter extends ArrayAdapter {

        private List<String> names_a;

        public NamesAdapter(@NonNull Context context, @NonNull List names) {
            super(context, R.layout.names_list_item, names);
            this.names_a = names;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            try {
                String name = names_a.get(position);
                ViewHolder viewHolder;

                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater)
                            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.names_list_item, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.tv_name = (TextView) convertView.findViewById(R.id.name);
                    viewHolder.edit = (ImageView) convertView.findViewById(R.id.edit_btn);
                    viewHolder.delete = (ImageView) convertView.findViewById(R.id.delete_btn);
                    convertView.setTag(viewHolder);

                }else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.fill(name, position);
                return convertView;
            }catch (Exception e) {

            }
            return convertView;
        }

        private class ViewHolder implements View.OnClickListener {

            TextView tv_name;
            ImageView edit;
            ImageView delete;
            int position;

            public void fill(String name, int position) {

                try {
                    tv_name.setText(name);
                    edit.setOnClickListener(this);
                    delete.setOnClickListener(this);
                    this.position = position;
                }catch (Exception e){

                }

            }

            @Override
            public void onClick(View view) {

                try {

                    if (MainActivity.can_click_btn == false) return;
                    MainActivity.can_click_btn = false;
                    if (view.getId() == edit.getId()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(PlayerNameActivity.this);
                        builder.setTitle("اسم جدید را وارد کنید");
                        EditText input = new EditText(PlayerNameActivity.this);
                        input.setInputType(1);
                        input.setText(names_array.get(position).toString().trim());
                        input.selectAll();
                        input.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager keyboard = (InputMethodManager)
                                        getSystemService(Context.INPUT_METHOD_SERVICE);
                                keyboard.showSoftInput(input, 0);
                                MainActivity.can_click_btn = true;
                            }
                        },100);
                        builder.setView(input);
                        builder.setPositiveButton("تغییر", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (input.getText().toString().trim().equals("")) {
                                    Toast.makeText(PlayerNameActivity.this, "اسم نباید خالی باشد", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                for (int j = 0; j < names_array.size(); j++) {

                                    if (input.getText().toString().trim().toLowerCase().equals(names_array.get(position).toString().trim().toLowerCase())) {

                                    }else if (input.getText().toString().trim().toLowerCase().equals(names_array.get(j).toString().trim().toLowerCase())) {
                                        Toast.makeText(PlayerNameActivity.this, "اسم نباید تکراری باشد", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                                names_array.remove(position);
                                names_array.add(position, input.getText().toString().trim());
                                namesAdapter.notifyDataSetChanged();

                            }
                        });
                        builder.setNegativeButton("برگشت", null);
                        builder.create().show();



                    }else if(view.getId() == delete.getId()) {

                        names_array.remove(position);
                        namesAdapter.notifyDataSetChanged();
                        MainActivity.can_click_btn = true;

                    }
                }catch (Exception e) {

                }

            }
        }


    }

}