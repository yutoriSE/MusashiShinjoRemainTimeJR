package com.example.myapplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.time.LocalTime;
import java.time.Duration;

public class DipartureTime {

    private int dipartureKawasaki;
    private int dipartureTachikawa;

    public DipartureTime(){
        this.dipartureKawasaki = 0;
        this.dipartureTachikawa = 0;
    }

    private ArrayList<String> timeTableKawasaki = new ArrayList<>(Arrays.asList(
            "00:10","00:33","00:50","02:29","02:31"
            ,"05:06","05:25", "05:39","05:52"
            ,"06:01","06:10","06:20","06:27","06:32","06:37","06:42","06:49","06:52"
            ,"07:00","07:04","07:08","07:13","07:18","07:22","07:27","07:30","07:30"
            ,"07:32","07:35","07:37","07:40","07:42","07:45","07:47","07:50","07:52","07:55","07:57"
            ,"08:00","08:02","08:05","08:07","08:10","08:12","08:15","08:17","08:20","08:23","08:25"
            ,"08:28","08:32","08:34","08:37","08:41","08:46","08:51","08:54"
            ,"09:00","09:03","09:08","09:12","09:21","09:26","09:31","09:37","09:45","09:54"
            ,"10:04","10:14","10:20","10:27","10:33","10:42","10:50","10:55"
            ,"11:03","11:13","11:20","11:25","11:33","11:43","11:51","11:55"
            ,"12:01","12:13","12:20","12:25","12:33","12:43","12:50","12:55"
            ,"13:03","13:13","13:20","13:25","13:33","13:43","13:50","13:55"
            ,"14:03","14:13","14:20","14:25","14:33","14:43","14:50","14:55"
            ,"15:03","15:13","15:20","15:25","15:34","15:42","15:48","15:59"
            ,"16:03","16:13","16:20","16:26","16:34","16:42","16:48","16:59"
            ,"17:07","17:10","17:26","17:35","17:40","17:45","17:52","17:57"
            ,"18:02","18:08","18:12","18:17","18:22","18:26","18:33","18:39","18:42","18:48","18:51","18:57"
            ,"19:03","19:09","19:12","19:18","19:23","19:31","19:36","19:40","19:43","19:48","19:54"
            ,"20:02","20:10","20:15","20:22","20:31","20:37","20:46","20:52"
            ,"21:02","21:10","21:16","21:23","21:28","21:37","21:47","21:51","21:56"
            ,"22:03","22:12","22:19","22:24","22:34","22:45","22:56"
            ,"23:06","23:14","23:20","23:26","23:34","23:48","23:54"
            )) ;

    private ArrayList<String> timeTableTachikawa = new ArrayList<>(Arrays.asList(
            "00:10","00:16","00:38"
            ,"04:39","04:45","05:05","05:23", "05:32","05:36","05:43","05:47","05:56"
            ,"06:01","06:05","06:10","06:13","06:17","06:23","06:27","06:30","06:34"
            ,"06:38","06:41","06:44","06:47","06:51","06:56","06:59"
            ,"07:03","07:09","07:13","07:16","07:20","07:24","07:27","07:30","07:33"
            ,"07:36","07:42","07:45","07:50","07:54"
            ,"08:00","08:03","08:07","08:10","08:12","08:15","08:20","08:22","08:25","08:28","08:33"
            ,"08:38","08:41","08:45","08:49","08:53","08:59"
            ,"09:03","09:09","09:17","09:27","09:36","09:45","09:52"
            ,"10:01","10:10","10:17","10:26","10:35","10:41","10:46","10:56"
            ,"11:05","11:11","11:16","11:26","11:35","11:41","11:46","11:56"
            ,"12:05","12:11","12:16","12:36","12:35","12:41","12:46","12:56"
            ,"13:05","13:11","13:16","13:36","13:35","13:41","13:46","13:56"
            ,"14:05","14:11","14:16","14:36","14:35","14:41","14:46","14:56"
            ,"15:05","15:11","15:16","15:36","15:35","15:41","15:46","15:56"
            ,"16:05","16:11","16:16","16:27","16:34","16:40","16:49","16:56"
            ,"17:02","17:08","17:14","17:21","17:27","17:32","17:37","17:42","17:46","17:50","17:55","17:57"
            ,"18:04","18:10","18:15","18:20","18:25","18:27","18:34","18:40","18:45","18:50","18:55","18:57"
            ,"19:04","19:10","19:15","19:20","19:25","19:27","19:34","19:40","19:45","19:50","19:55"
            ,"20:00","20:06","20:13","20:22","20:27","20:34","20:40","20:46","20:53"
            ,"21:00","21:05","21:13","21:19","21:28","21:34","21:43","21:51","21:57"
            ,"22:04","22:11","22:18","22:27","22:36","22:43","22:50","22:59"
            ,"23:08","23:22","23:30","23:38","23:49","23:58"
            ));

    public String getDipartureKawasaki(){
        return timeTableKawasaki.get(this.dipartureKawasaki);
    }

    public String getDipartureTachikawa(){
        return timeTableTachikawa.get(this.dipartureTachikawa);
    }
    

    //現時刻に該当する時間のインデックスをセット
    public void serchTimeKawasaki(){
        Calendar now = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
        SimpleDateFormat sdf = new SimpleDateFormat(("HH:mm"));
        sdf.setTimeZone(tz);
        String nowStr = sdf.format(now.getTime());

        for(int i=0; i<timeTableKawasaki.size(); i++){

            LocalTime nowLocal = LocalTime.parse(nowStr);
            LocalTime timeLocal = LocalTime.parse(timeTableKawasaki.get(i));
            long delta = Duration.between(timeLocal, nowLocal).toMinutes();

            if(delta < -1){
                if(i == 0){
                    this.dipartureKawasaki = 0;
                    break;
                }
                this.dipartureKawasaki = i;
                break;
            }
        }
    }

    //現時刻に該当する時間のインデックスをセット
    public void serchTimeTachikawa(){
        Calendar now = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
        SimpleDateFormat sdf = new SimpleDateFormat(("HH:mm"));
        sdf.setTimeZone(tz);
        String nowStr = sdf.format(now.getTime());

        for(int i=0; i<timeTableTachikawa.size(); i++){

            LocalTime nowLocal = LocalTime.parse(nowStr);
            LocalTime timeLocal = LocalTime.parse(timeTableTachikawa.get(i));
            long delta = Duration.between(timeLocal, nowLocal).toMinutes();

            if(delta <= 1){
                if(i == 0){
                    this.dipartureTachikawa = 0;
                    break;
                }
                this.dipartureTachikawa = i;
                break;
            }
        }
    }

    //残り時間計算
    public long calcRemainKawasaki(){
        Calendar now = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
        SimpleDateFormat sdf = new SimpleDateFormat(("HH:mm"));
        sdf.setTimeZone(tz);
        String nowStr = sdf.format(now.getTime());

        LocalTime nowLocal = LocalTime.parse(nowStr);
        LocalTime timeLocal = LocalTime.parse(timeTableKawasaki.get(this.dipartureKawasaki));
        return Duration.between(nowLocal, timeLocal).toMillis();
    }

    //残り時間計算
    public long calcRemainTachikawa(){
        Calendar now = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
        SimpleDateFormat sdf = new SimpleDateFormat(("HH:mm"));
        sdf.setTimeZone(tz);
        String nowStr = sdf.format(now.getTime());

        LocalTime nowLocal = LocalTime.parse(nowStr);
        LocalTime timeLocal = LocalTime.parse(timeTableTachikawa.get(this.dipartureTachikawa));
        return Duration.between(nowLocal, timeLocal).toMillis();
    }
}
