package com.example.train_stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTime();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        super.onSaveInstanceState(savedInstanceState);
    }
   /* @Override
    protected void onStop(){
        super.onStop();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }*/
    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }
    //Запустить секундомер
    public void onClickStart(View view){
        running = true;
    }
    //Остановить секундомер
    public void onClickStop(View view){
        running = false;
    }
    //Сбросить секундомер
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }
    private void runTime(){
        final TextView timeView = (TextView)findViewById((R.id.time_view));
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%02d:%02d:%02d", hours,minutes,secs);
                timeView.setText(time);
                if (running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }


        });
    }
}
