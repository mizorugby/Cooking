package com.example.cooking;


//AndroidX
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private Handler handler = new Handler();

    private TextView timerText;
    private long delay, period;
    private int count;

    private SimpleDateFormat dataFormat =
            new SimpleDateFormat("mm:ss.S", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        delay = 0;
        period = 100;

        Button startButton = findViewById(R.id.start_button);
        Button stopButton = findViewById(R.id.stop_button);
        Button resetButton = findViewById(R.id.reset_button);

        timerText = findViewById(R.id.timer);
        timerText.setText(dataFormat.format(0));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }

                // Timer インスタンスを生成
                timer = new Timer();

                // カウンター
                //count = 0;
                //timerText.setText(dataFormat.format(0));

                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        // handlerdを使って処理をキューイングする
                        handler.post(new Runnable() {
                            public void run() {
                                count++;
                                timerText.setText(dataFormat.
                                        format(count*period));
                            }
                        });
                    }
                }, delay, period);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // timer がnullでない、起動しているときのみcancleする
                if (timer != null) {
                    // Cancel
                    timer.cancel();
                    timer = null;
                    //timerText.setText(dataFormat.format(0));

                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    // Cancel
                    timer.cancel();
                    timer = null;
                    count = 0;
                    timerText.setText(dataFormat.format(0));
                } else {
                    count = 0;
                    timerText.setText(dataFormat.format(0));
                }
            }
        });
    }
}