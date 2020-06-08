package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Kawasaki
        TextView dipartureTimeKawasaki = findViewById(R.id.departureTimeKawasaki);
        TextView remainingTimeKawasaki = findViewById(R.id.remainingTimeKawasaki);
        EditText notifyTimeKawasaki = findViewById(R.id.notifyTimeKawasaki);
        Button buttonNextKawasaki = findViewById(R.id.buttonNextKawasaki);
        Button buttonSetNotifyKawasaki = findViewById(R.id.buttonSetNotifyKawasaki);

        //Tachikawa
        TextView dipartureTimeTachikawa = findViewById(R.id.departureTimeTachikawa);
        TextView remainingTimeTachikawa = findViewById(R.id.remainingTimeTachikawa);
        EditText notifyTimeTachikawa = findViewById(R.id.notifyTimeTachikawa);
        Button buttonNextTachikawa = findViewById(R.id.buttonNextTachikawa);
        Button buttonSetNotifyTachikawa = findViewById(R.id.buttonSetNotifyTachikawa);


    }
}