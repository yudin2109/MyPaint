package com.trololesha.mypaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.TreeMap;

public class PaintView extends View {
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private PointF oldPosition;
    private TreeMap<Integer, PointF> oldPositions;
    private int paintSize = 10;

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(paintSize);
        canvas.drawColor(Color.GREEN);

        oldPositions = new TreeMap<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX(0);
                float y = event.getY(0);
                int ID = event.getPointerId(0);
                oldPositions.put(ID, new PointF(x, y));

                canvas.drawCircle(x, y, paintSize / 2, paint);
            } break;

            case MotionEvent.ACTION_POINTER_DOWN: {
                int index = event.getActionIndex();
                float x = event.getX(index);
                float y = event.getY(index);
                int ID = event.getPointerId(index);
                oldPositions.put(ID, new PointF(x, y));

                canvas.drawCircle(x, y, paintSize / 2, paint);
            } break;

            case MotionEvent.ACTION_MOVE: {
                int count = event.getPointerCount();
                for (int i = 0; i < count; i++) {
                    float x = event.getX(i);
                    float y = event.getY(i);
                    int ID = event.getPointerId(i);
                    PointF old = oldPositions.get(ID);
                    canvas.drawLine(old.x, old.y, x, y, paint);

                    oldPositions.put(ID, new PointF(x, y));
                }
            } break;

            case MotionEvent.ACTION_POINTER_UP: {
                int index = event.getActionIndex();
                int ID = event.getPointerId(index);
                oldPositions.remove(ID);
            } break;

            case MotionEvent.ACTION_UP: {
                oldPositions.remove(event.getPointerId(0));
            } break;
        }
        this.postInvalidate();
        return true;
    }

    public void Clear() {
        canvas.drawColor(Color.GREEN);
        oldPosition = null;
    }

    //@Костыль
    public void ChangeColor(int red, int green, int blue) {
        paint.setARGB(255, red, green, blue);
    }
}
