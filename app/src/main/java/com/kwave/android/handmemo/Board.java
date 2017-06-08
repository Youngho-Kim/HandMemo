package com.kwave.android.handmemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Board extends View {
//        Paint paint;
//        Path path;
    private ArrayList<Pair<Path, Paint>> pathList = new ArrayList<>();

    public Board(Context context) {
        super(context);
//            path = new Path();
    }

    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {      // onDraw는 what에  해당   // canvas는 how에 해당
        super.onDraw(canvas);
        for(Pair<Path, Paint> pair : pathList){
            canvas.drawPath(pair.first,pair.second);
        }
    }

    MainActivity getPaintMemo(){
        return (MainActivity) getContext();
    }
    Paint getPaint(){
        return getPaintMemo().getPaint();
    }

    Pair<Path, Paint> pair = null;
    Path path = null;
    Paint paint = null;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        // 내가 터치한 좌표를 꺼낸다.
//            float x = event.getX();
//            float y = event.getY();

//            Log.e("LOG", "onTouchEvent=============x");
//            Log.e("LOG", "onTouchEvent=============y");

        switch(event.getAction()){
            case MotionEvent.ACTION_POINTER_DOWN :
            case MotionEvent.ACTION_DOWN :
//                    path.moveTo(x,y);       // 이전 점과 현재 점 사이를 그리지 않고 이동한다.
                path = new Path();
                Log.e("LOG", "onTouchEvent=============down");
                path.moveTo(event.getX(), event.getY());
                paint = new Paint();
                paint.setColor(getPaint().getColor());
                paint.setStrokeWidth(getPaint().getStrokeWidth());
                paint.setStyle(Paint.Style.STROKE);
                pair = new Pair<>(path, paint);
                pathList.add(pair);
                return true;
            case MotionEvent.ACTION_MOVE :
//                    path.lineTo(x,y);       // 바로 이전 점과 현재 점 사이에 줄을 그어준다.
                if(pair != null){
                    path.lineTo(event.getX(), event.getY());
                    Log.e("LOG", "onTouchEvent=============move");
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP :
                Log.e("LOG", "onTouchEvent=============up");
//                    path.lineTo(x,y);       // 바로 이전 점과 현재 점 사이에 줄을 그어준다.
                break;
            case MotionEvent.ACTION_POINTER_UP :

                break;

        }

        // Path를 그린 후 화면을 갱신해서 반영해준다.
        invalidate();       // 메인쓰레드를 갱신할때는 invalidate를 쓰고 서브 쓰레드를 갱신 할 때는 PostInvalidate를 사용한다.

        // 리턴이 false가 되면 touch 이벤트를 연속해서 발생시키지 않는다.
        // 즉, 드래그 시 onTouchEvent가 호출되지 않는다.
//        invalidate();
        return true;
    }
}
