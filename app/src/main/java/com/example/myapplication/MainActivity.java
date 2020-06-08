package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimerKawasaki;
    private CountDownTimer countDownTimerTachikawa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Kawasaki
        TextView dipartureTimeKawasaki = findViewById(R.id.departureTimeKawasaki);
        final TextView remainingTimeKawasaki = findViewById(R.id.remainingTimeKawasaki);
        EditText notifyTimeKawasaki = findViewById(R.id.notifyTimeKawasaki);
        Button buttonNextKawasaki = findViewById(R.id.buttonNextKawasaki);
        Button buttonSetNotifyKawasaki = findViewById(R.id.buttonSetNotifyKawasaki);

        //Tachikawa
        TextView dipartureTimeTachikawa = findViewById(R.id.departureTimeTachikawa);
        final TextView remainingTimeTachikawa = findViewById(R.id.remainingTimeTachikawa);
        EditText notifyTimeTachikawa = findViewById(R.id.notifyTimeTachikawa);
        Button buttonNextTachikawa = findViewById(R.id.buttonNextTachikawa);
        Button buttonSetNotifyTachikawa = findViewById(R.id.buttonSetNotifyTachikawa);

        DipartureTime dipTime = new DipartureTime();

        dipartureTimeKawasaki.setText(dipTime.getDipartureKawasaki());

        countDownTimerKawasaki = new CountDownTimer(36000, 100){
            @Override
            public void onFinish(){
                remainingTimeKawasaki.setText("diparture");
            }

            @Override
            public void onTick(long millisUntilFinished){
                long mm = millisUntilFinished / 1000 / 60;
                long ss = millisUntilFinished / 1000 % 60;
                long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

                String remain = addZero(String.valueOf(mm)) + ":" + addZero(String.valueOf(ss)) + "." + String.valueOf(ms);
                remainingTimeKawasaki.setText(remain);
            }
        };
        countDownTimerKawasaki.start();

        countDownTimerTachikawa = new CountDownTimer(50000, 100){
            @Override
            public void onFinish(){
                remainingTimeTachikawa.setText("diparture");
            }

            @Override
            public void onTick(long millisUntilFinished){
                long mm = millisUntilFinished / 1000 / 60;
                long ss = millisUntilFinished / 1000 % 60;
                long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

                String remain = addZero(String.valueOf(mm)) + ":" + addZero(String.valueOf(ss)) + "." + String.valueOf(ms);
                remainingTimeTachikawa.setText(remain);
            }
        };
        countDownTimerTachikawa.start();
    }

    //頭に０を足す
    public String addZero(String str){
        if(str.length()==1){
            str = "0" + str;
        }
        return str;
    }
}