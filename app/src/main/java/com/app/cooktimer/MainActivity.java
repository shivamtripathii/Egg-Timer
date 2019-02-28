package com.app.cooktimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button button;
    Boolean aBoolean;
    CountDownTimer countDownTimer;
    public void resetTimer()
    {
        button.setText("START");
        textView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        seekBar.setEnabled(true);
        aBoolean=false;
    }
    public void updateTimer(int progress)
    {

        int minutes=(int)progress/60;
        int sec=progress-minutes*60;
        if(sec<10)
            textView.setText(minutes+":0"+sec);
        else
            textView.setText(minutes+":"+sec);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView2);
        button=findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        aBoolean=false;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (aBoolean==false) {
                    aBoolean = true;
                    seekBar.setEnabled(false);
                    button.setText("STOP");
                    countDownTimer=new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            updateTimer((int) millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {
                            resetTimer();
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                            mediaPlayer.start();
                        }
                    }.start();
                }else
                {
                    resetTimer();
                }
            }
        });
    }
}
