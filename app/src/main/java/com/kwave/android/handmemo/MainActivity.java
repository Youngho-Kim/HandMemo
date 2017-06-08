package com.kwave.android.handmemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Paint paint= new Paint();
    RadioGroup radioGroup;
    RadioButton red, green, blue;
    SeekBar seekBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        red = (RadioButton) findViewById(R.id.btnRed);
        green = (RadioButton) findViewById(R.id.btnGreen);
        blue = (RadioButton) findViewById(R.id.btnBlue);


//        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
//        Board board = new Board(getBaseContext());

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.btnRed:
                        paint.setColor(Color.RED);
                        break;
                    case R.id.btnGreen:
//                        Paint paint1 = new Paint();
                        paint.setColor(Color.GREEN);
                        break;
                    case R.id.btnBlue:
//                        Paint paint2 = new Paint();
                        paint.setColor(Color.BLUE);
                        break;
                }
            }
        });
        radioGroup.check(R.id.btnRed);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        paint.setStrokeWidth(seekBar.getProgress());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
//                paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


//                // 1. 보드를 새로 생성한다.
//                 Board board = new Board(getBaseContext());
//                 // 2. 붓을 만들어서 보드에 담는다
//                 Paint paint = new Paint();
//                 paint.setColor(Color.MAGENTA);
//                 paint.setStyle(Paint.Style.STROKE);
//                 paint.setStrokeWidth(10);
//                 board.setPaint(paint);
//                 // 3. 생성된 보드를 화면에 세팅한다.
//                 frameLayout.addView(board);

//        // 1. 보드를 새로 생성한다.
//        paint.setStrokeWidth(seekBar.getWidth());
//        board.setPaint(paint);
//        // 3. 생성된 보드를 화면에 세팅한다.
//        frameLayout.addView(board);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "어떤때 나오는 것일까?", Toast.LENGTH_SHORT).show();
            }
        }, 5000);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}


