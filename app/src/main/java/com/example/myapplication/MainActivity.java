package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.util.Timer;
import java.util.TimerTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimerKawasaki;
    private CountDownTimer countDownTimerTachikawa;
    private int timeBiasKawasaki;
    private int timeBiasTachikawa;
    public long remainKawasaki;
    public long remainTachikawa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Kawasaki
        final TextView dipartureTimeKawasaki = findViewById(R.id.departureTimeKawasaki);
        final TextView remainingTimeKawasaki = findViewById(R.id.remainingTimeKawasaki);
        EditText notifyTimeKawasaki = findViewById(R.id.notifyTimeKawasaki);
        Button buttonNextKawasaki = findViewById(R.id.buttonNextKawasaki);
        Button buttonSetNotifyKawasaki = findViewById(R.id.buttonSetNotifyKawasaki);

        //Tachikawa
        final TextView dipartureTimeTachikawa = findViewById(R.id.departureTimeTachikawa);
        final TextView remainingTimeTachikawa = findViewById(R.id.remainingTimeTachikawa);
        EditText notifyTimeTachikawa = findViewById(R.id.notifyTimeTachikawa);
        Button buttonNextTachikawa = findViewById(R.id.buttonNextTachikawa);
        Button buttonSetNotifyTachikawa = findViewById(R.id.buttonSetNotifyTachikawa);

        //初期時間処理（川崎）
        final DipartureTime dipTimeKawasaki = new DipartureTime();
        dipTimeKawasaki.serchTimeKawasaki();
        //dipartureTimeKawasaki.setText(dipTimeKawasaki.getDipartureKawasaki());
        remainKawasaki = dipTimeKawasaki.calcRemainKawasaki();

        //川崎方面のカウントダウンタイマー
        countDownTimerKawasaki = new CountDownTimer(remainKawasaki, 100){
            @Override
            public void onFinish(){
                countDownTimerKawasaki.start();
            }

            @Override
            public void onTick(long millisUntilFinished){
                long mm = millisUntilFinished / 1000 / 60;
                long ss = millisUntilFinished / 1000 % 60;
                long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

                String remainStr = addZero(String.valueOf(mm)) + ":" + addZero(String.valueOf(ss)) + "." + String.valueOf(ms);
                remainingTimeKawasaki.setText(remainStr);
            }
        };

        //初期時間処理（立川）
        final DipartureTime dipTimeTachikawa = new DipartureTime();
        dipTimeTachikawa.serchTimeTachikawa();
        //dipartureTimeTachikawa.setText(dipTimeTachikawa.getDipartureTachikawa());
        remainTachikawa = dipTimeTachikawa.calcRemainTachikawa();

        //立川方面のカウントダウンタイマー
        countDownTimerTachikawa = new CountDownTimer(remainTachikawa, 100){
            @Override
            public void onFinish() {
                countDownTimerTachikawa.start();
            }

            @Override
            public void onTick(long millisUntilFinished){
                long mm = millisUntilFinished / 1000 / 60;
                long ss = millisUntilFinished / 1000 % 60;
                long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

                String remainStr = addZero(String.valueOf(mm)) + ":" + addZero(String.valueOf(ss)) + "." + addZero(String.valueOf(ms));
                remainingTimeTachikawa.setText(remainStr);
            }
        };

        countDownTimerKawasaki.start();
        countDownTimerTachikawa.start();

        //timer
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //System.out.println(dipTimeKawasaki.getDipartureKawasaki());
                dipTimeKawasaki.serchTimeKawasaki();
                dipartureTimeKawasaki.setText(dipTimeKawasaki.getDipartureKawasaki());
                remainKawasaki = dipTimeKawasaki.calcRemainKawasaki();

                dipTimeTachikawa.serchTimeTachikawa();
                dipartureTimeTachikawa.setText(dipTimeTachikawa.getDipartureTachikawa());
                remainTachikawa = dipTimeTachikawa.calcRemainTachikawa();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0,100);

    }

    //時間記述の０パディング
    public String addZero(String str){
        if(str.length()==1){
            str = "0" + str;
        }
        return str;
    }

}