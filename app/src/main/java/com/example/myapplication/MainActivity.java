package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.text.BreakIterator;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int timeBiasKawasaki;
    private int timeBiasTachikawa;
    private long remainKawasaki;
    private long remainTachikawa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final long tempKawasaki = 0;
        //final long tempTachikawa = 0;

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

        final MainActivity update = new MainActivity();

        //初期時間処理（川崎）
        final DipartureTime dtK = new DipartureTime();
        dtK.serchTimeKawasaki();
        //dipartureTimeKawasaki.setText(dipTimeKawasaki.getDipartureKawasaki());
        remainKawasaki = dtK.calcRemainKawasaki();

        //川崎方面のカウントダウンタイマー
        new CountDownTimer(remainKawasaki, 100){
            @Override
            public void onFinish(){
                this.cancel();
                System.out.println(remainKawasaki);
                this.start();
            }

            @Override
            public void onTick(long millisUntilFinished){
                long mm = millisUntilFinished / 1000 / 60;
                long ss = millisUntilFinished / 1000 % 60;
                long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

                String remainStr = addZero(String.valueOf(mm)) + ":" + addZero(String.valueOf(ss)) + "." + String.valueOf(ms);
                remainingTimeKawasaki.setText(remainStr);
            }
        }.start();

        //初期時間処理（立川）
        final DipartureTime dtT = new DipartureTime();
        dtT.serchTimeTachikawa();
        //dipartureTimeTachikawa.setText(dipTimeTachikawa.getDipartureTachikawa());
        remainTachikawa = dtT.calcRemainTachikawa();

        //立川方面のカウントダウンタイマー
        new CountDownTimer(remainTachikawa, 100){
            @Override
            public void onFinish() {
                this.cancel();
                System.out.println(remainKawasaki);
                this.start();
            }

            @Override
            public void onTick(long millisUntilFinished){
                long mm = millisUntilFinished / 1000 / 60;
                long ss = millisUntilFinished / 1000 % 60;
                long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

                String remainStr = addZero(String.valueOf(mm)) + ":" + addZero(String.valueOf(ss)) + "." + addZero(String.valueOf(ms));
                remainingTimeTachikawa.setText(remainStr);
            }
        }.start();

        //timerで次の時刻を更新
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //System.out.println(remainKawasaki);
                dtK.serchTimeKawasaki();//現在時刻から次の発車時刻インデックスを更新
                dipartureTimeKawasaki.setText(dtK.getDipartureKawasaki());
                remainKawasaki = dtK.calcRemainKawasaki();
                //update.updateK(dtK.calcRemainKawasaki());
                //update.watch();

                dtT.serchTimeTachikawa();
                dipartureTimeTachikawa.setText(dtT.getDipartureTachikawa());
                remainTachikawa = dtT.calcRemainTachikawa();
                //update.updateT(dtT.calcRemainTachikawa());
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 0,1000);

    }

    //時間記述の０パディング
    public String addZero(String str){
        if(str.length()==1){
            str = "0" + str;
        }
        return str;
    }

    public CountDownTimer createTimer(long time, final TextView textView){

        CountDownTimer cdt = new CountDownTimer(time, 100){
            @Override
            public void onFinish(){
                this.cancel();

            }

            @Override
            public void onTick(long millisUntilFinished){
                long mm = millisUntilFinished / 1000 / 60;
                long ss = millisUntilFinished / 1000 % 60;
                long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

                String remainStr = addZero(String.valueOf(mm)) + ":" + addZero(String.valueOf(ss)) + "." + String.valueOf(ms);
                textView.setText(remainStr);
            }
        };

        return cdt;
    }



}