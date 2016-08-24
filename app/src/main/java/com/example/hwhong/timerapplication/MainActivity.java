package com.example.hwhong.timerapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start, stop;
    private TextView textView;
    Counter timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        textView = (TextView)findViewById(R.id.textView);

        textView.setText("00:20:00");

        timer = new Counter(1200000, 1000);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.start:
                timer.start();
                break;
            case R.id.stop:
                timer.cancel();
                break;
        }
    }

    public class Counter extends CountDownTimer {

        public Counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

            long count = l;
            String string = String.format("%02d:%02d:%02d"
                    , TimeUnit.MILLISECONDS.toHours(count)
                    , TimeUnit.MILLISECONDS.toMinutes(count) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(count))
                    , TimeUnit.MILLISECONDS.toSeconds(count) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(count)));

            textView.setText(string);
        }

        @Override
        public void onFinish() {
            Toast.makeText(getApplicationContext(), "Timer Completes", Toast.LENGTH_SHORT).show();
        }
    }
}
