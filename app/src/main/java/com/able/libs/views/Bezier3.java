package com.able.libs.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Able on 2017/6/30.
 * 三阶的贝塞尔曲线
 */

public class Bezier3 extends View {
    private Paint mPaint;
    private int mCenterX, mCenterY;
    private PointF mStart, mEnd, mControl1, mControl2;

    /*
    -1:点击点位于两个控制点太远，不进行拖动
    0:拖动控制点1
    1:拖动控制点2
     */
    private int mState = -1;

    public Bezier3(Context context) {
        super(context);
        init();
    }


    public Bezier3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        mStart = new PointF(0, 0);
        mEnd = new PointF(0, 0);
        mControl1 = new PointF(0, 0);
        mControl2 = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;

        // 初始化数据点和控制点的位置
        mStart.x = mCenterX - 200;
        mStart.y = mCenterY;
        mEnd.x = mCenterX + 200;
        mEnd.y = mCenterY;
        mControl1.x = mCenterX - 100;
        mControl1.y = mCenterY - 100;
        mControl2.x = mCenterX + 100;
        mControl2.y = mCenterY - 100;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //按下时决定了是否拖动，以及拖动的是哪个控制点
            int control1_distance = getTwoPointsDistance(event.getX(), event.getY(), mControl1.x, mControl1.y);
            int control2_distance = getTwoPointsDistance(event.getX(), event.getY(), mControl2.x, mControl2.y);
            if (Math.min(control1_distance, control2_distance) < 40) {
                if (control1_distance < control2_distance) {
                    mState = 0;
                    mControl1.x = event.getX();
                    mControl1.y = event.getY();
                } else {
                    mState = 1;
                    mControl2.x = event.getX();
                    mControl2.y = event.getY();
                }
            } else {
                //点击位置距离两个点都太远，不进行拖动操作
                mState = -1;
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (mState == 0) {
                mControl1.x = event.getX();
                mControl1.y = event.getY();
            } else if (mState == 1) {
                mControl2.x = event.getX();
                mControl2.y = event.getY();
            }
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawCoordinateSystem(canvas);

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(mStart.x, mStart.y, mPaint);
        canvas.drawPoint(mEnd.x, mEnd.y, mPaint);
        canvas.drawPoint(mControl1.x, mControl1.y, mPaint);
        canvas.drawPoint(mControl2.x, mControl2.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStart.x, mStart.y, mControl1.x, mControl1.y, mPaint);
        canvas.drawLine(mControl1.x, mControl1.y, mControl2.x, mControl2.y, mPaint);
        canvas.drawLine(mControl2.x, mControl2.y, mEnd.x, mEnd.y, mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();

        path.moveTo(mStart.x, mStart.y);
        path.cubicTo(mControl1.x, mControl1.y, mControl2.x, mControl2.y, mEnd.x, mEnd.y);

        canvas.drawPath(path, mPaint);
    }

    /*
    读取两个点的距离，获取的是int型的正数
     */
    private int getTwoPointsDistance(float x1, float y1, float x2, float y2) {
        float abs_x = Math.abs(x1 - x2);
        float abs_y = Math.abs(y1 - y2);
        return (int) Math.sqrt(abs_x * abs_x + abs_y * abs_y);
    }
}
