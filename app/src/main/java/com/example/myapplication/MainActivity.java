package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int timeBiasKawasaki;
    private int timeBiasTachikawa;
    private long remainKawasaki;
    private long remainTachikawa;

    //Kawasaki
    TextView dipartureTimeKawasaki;
    TextView remainingTimeKawasaki;
    EditText notifyTimeKawasaki;
    Button buttonNextKawasaki;
    Button buttonSetNotifyKawasaki;

    //Tachikawa
    TextView dipartureTimeTachikawa;
    TextView remainingTimeTachikawa;
    EditText notifyTimeTachikawa;
    Button buttonNextTachikawa;
    Button buttonSetNotifyTachikawa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Kawasaki
        dipartureTimeKawasaki = findViewById(R.id.departureTimeKawasaki);
        remainingTimeKawasaki = findViewById(R.id.remainingTimeKawasaki);
        notifyTimeKawasaki = findViewById(R.id.notifyTimeKawasaki);
        buttonNextKawasaki = findViewById(R.id.buttonNextKawasaki);
        buttonSetNotifyKawasaki = findViewById(R.id.buttonSetNotifyKawasaki);

        //Tachikawa
        dipartureTimeTachikawa = findViewById(R.id.departureTimeTachikawa);
        remainingTimeTachikawa = findViewById(R.id.remainingTimeTachikawa);
        notifyTimeTachikawa = findViewById(R.id.notifyTimeTachikawa);
        buttonNextTachikawa = findViewById(R.id.buttonNextTachikawa);
        buttonSetNotifyTachikawa = findViewById(R.id.buttonSetNotifyTachikawa);

        //初回処理（川崎）
        final DipartureTime dtK = new DipartureTime();
        dtK.serchTimeKawasaki();
        dipartureTimeKawasaki.setText(dtK.getDipartureKawasaki());
        remainKawasaki = dtK.calcRemainKawasaki();

        //初回処理（立川）
        final DipartureTime dtT = new DipartureTime();
        dtT.serchTimeTachikawa();
        dipartureTimeTachikawa.setText(dtT.getDipartureTachikawa());
        remainTachikawa = dtT.calcRemainTachikawa();

        //川崎方面のカウントダウンタイマー
        new MyCountDownTimer(remainKawasaki, 100){

            @Override
            public void onFinish(){
                dtK.serchTimeKawasaki();//現在時刻から次の発車時刻インデックスを更新
                dipartureTimeKawasaki.setText(dtK.getDipartureKawasaki());//次の発車時刻をセット
                remainKawasaki = dtK.calcRemainKawasaki();//次の発車時刻までの残り時間を計算
                this.setMillisInFuture(remainKawasaki);//タイマーに再セットして再実行
                this.start();
            }

            @Override
            public void onTick(long millisUntilFinished){
                long mm = millisUntilFinished / 1000 / 60;
                long ss = millisUntilFinished / 1000 % 60;
                long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

                String remainStr = addZero(String.valueOf(mm)) + ":" + addZero(String.valueOf(ss)) + "." + String.valueOf(ms);
                remainingTimeKawasaki.setText(remainStr);//タイマーの残り時間を再セット
            }

        }.start();

        //立川方面のカウントダウンタイマー
        new MyCountDownTimer(remainTachikawa, 100){
            @Override
            public void onFinish() {
                dtT.serchTimeTachikawa();
                dipartureTimeTachikawa.setText(dtT.getDipartureTachikawa());
                remainTachikawa = dtT.calcRemainTachikawa();
                this.setMillisInFuture(remainTachikawa);
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


    }

    //時間記述の０パディング
    public String addZero(String str){
        if(str.length()==1){
            str = "0" + str;
        }
        return str;
    }


}
