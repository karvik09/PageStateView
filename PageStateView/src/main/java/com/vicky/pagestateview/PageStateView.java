package com.vicky.pagestateview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Vikas on 6/24/2018.
 */

/**
 * PageStateView indicates the current status of scrolling page inside viewpager
 */
public class PageStateView extends View {

    private Paint primaryPaint, secondaryPaint;
    private int secondaryCirclePosition, size, movement;
    private float secondaryCircleOffset;
    int  primaryCircleCount = 3;

    public PageStateView(Context context) {
        super(context);
        init(context, null);
    }

    public PageStateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PageStateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * initialise paints and local params
     *
     * @param context
     * @param attributeSet
     */
    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.PageStateView);
        int primaryColor = typedArray.getColor(R.styleable.PageStateView_primary_color, Color.parseColor("#d3d3d3"));
        int secondaryColor = typedArray.getColor(R.styleable.PageStateView_secondary_color, Color.parseColor("#50348e"));
        size = typedArray.getDimensionPixelSize(R.styleable.PageStateView_size, 10);
        movement = typedArray.getInt(R.styleable.PageStateView_movement, MOVEMENT_TYPE_STEPWISE);
        typedArray.recycle();

        primaryPaint = new Paint();
        primaryPaint.setAntiAlias(true);
        primaryPaint.setStyle(Paint.Style.FILL);
        primaryPaint.setColor(primaryColor);

        secondaryPaint = new Paint();
        secondaryPaint.setAntiAlias(true);
        secondaryPaint.setStyle(Paint.Style.FILL);
        secondaryPaint.setColor(secondaryColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measuredWidth(), 2 * size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPrimaryCircle(canvas);
        drawSecondaryCircle(canvas);
    }

    /**
     * drawing primary-static circle
     *
     * @param canvas
     */
    private void drawPrimaryCircle(Canvas canvas) {
        for (int i = 0; i < primaryCircleCount; i++) {
            int centreX = size + i * 3 * size;
            canvas.drawCircle(centreX, size, size, primaryPaint);
        }
    }

    /**
     * drawing secondary-moving circle
     *
     * @param canvas
     */
    private void drawSecondaryCircle(Canvas canvas) {
        int centreX = (int) (size + 3 * size * (secondaryCirclePosition + secondaryCircleOffset));
        canvas.drawCircle(centreX, size, size, secondaryPaint);
    }

    public void setupWithViewPager(@NonNull ViewPager viewPager) {
        if (viewPager.getAdapter() != null) {
            primaryCircleCount = viewPager.getAdapter().getCount();
            viewPager.addOnPageChangeListener(mPageChangeListener);
        }
    }

    /**
     * return the calculated with of circle
     *
     * @return
     */
    private int measuredWidth() {
        return 2 * size + 3 * size * (primaryCircleCount - 1);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (movement == MOVEMENT_TYPE_TRANSIENT) {
                secondaryCirclePosition = position;
                secondaryCircleOffset = positionOffset;
                invalidate();
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (movement == MOVEMENT_TYPE_STEPWISE) {
                secondaryCirclePosition = position;
                invalidate();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public static final int MOVEMENT_TYPE_STEPWISE = 1;//page state change occur in a complete step
    public static final int MOVEMENT_TYPE_TRANSIENT = 2;//page state change occur under transient action

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MOVEMENT_TYPE_STEPWISE, MOVEMENT_TYPE_TRANSIENT})
    @interface MovementType {
    }
}
