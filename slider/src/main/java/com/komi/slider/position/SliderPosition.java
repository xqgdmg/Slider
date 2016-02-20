package com.komi.slider.position;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by Komi on 2016/2/20.
 */
public abstract class SliderPosition {


    public static SliderPosition LEFT=LeftPosition.getInstance();
    public static SliderPosition RIGHT=RightPosition.getInstance();
    public static SliderPosition TOP=TopPosition.getInstance();
    public static SliderPosition BOTTOM=BottomPosition.getInstance();
    public static SliderPosition HORIZONTAL=HorizontalPosition.getInstance();
    public static SliderPosition VERTICAL=VerticalPosition.getInstance();
    public static SliderPosition ALL=AllPosition.getInstance();

    public static final SliderPosition[] sPositionChildren = {LEFT, RIGHT, TOP, BOTTOM, HORIZONTAL, VERTICAL, ALL};
    private SliderPosition slidingPosition;

    private boolean edgeOnly;

    private int childInitialLeft;

    private int childInitialTop;


    /**
     * @return 当position为如下情况时, 判断当前的滑动方向
     * {@link AllPosition}
     * {@link HorizontalPosition}
     * {@link VerticalPosition}
     */
    public SliderPosition getSlidingPosition() {
        return slidingPosition;
    }

    public void setSlidingPosition(SliderPosition slidingPosition) {
        this.slidingPosition = slidingPosition;
    }

    public boolean isEdgeOnly() {
        return edgeOnly;
    }

    public void setEdgeOnly(boolean edgeOnly) {
        this.edgeOnly = edgeOnly;
    }

    public boolean tryCaptureView(boolean initialTouched, boolean edgeTouched) {
        return false;
    }


    /**
     * @param maxWidth  宽度可移动到的最大值
     * @param left      sliderChild移动时的left
     * @param childLeft sliderChild初始状态的left
     * @param hWrapped  sliderChild的宽度是否被包裹在slider
     * @return sliderChild可移动到的宽度值
     */
    public int clampViewPositionHorizontal(int maxWidth, int left, int childLeft, boolean hWrapped) {
        return childLeft;
    }

    /**
     * @param maxHeight 高度可移动最大值
     * @param top       sliderChild移动时的top
     * @param childTop  sliderChild初始状态的top
     * @param vWrapped  sliderChild的高度是否被包裹在slider
     * @return sliderChild可移动到的高度值
     */
    public int clampViewPositionVertical(int maxHeight, int top, int childTop, boolean vWrapped) {
        return childTop;
    }

    /**
     * 水平方向返回一个滑动范围
     *
     * @param contentViewWidth 最大滑动范围
     * @return 滑动范围
     */
    public int getViewHorizontalDragRange(int contentViewWidth) {
        return 0;
    }

    /**
     * 垂直方向返回一个滑动范围
     *
     * @param contentViewHeight 最大滑动范围
     * @return 滑动范围
     */
    public int getViewVerticalDragRange(int contentViewHeight) {
        return 0;
    }


    /**
     * 获取滑动松开手后，获取横向坐标状态的判断
     *
     * @param hWrapped               sliderChild的宽度是否被包裹在slider
     * @param maxWidth               slider的宽度
     * @param left                   sliderChild松手时的left
     * @param childLeft              sliderChild初始状态的left
     * @param xvel                   x轴的滑动速度
     * @param hThreshold             横向划开的宽度的阀值
     * @param hOverVelocityThreshold 是否达到设定的横向划开速度
     * @param velocityThreshold      横向划开速度阀值
     * @return 松开手后sliderChild要移动到终点的Left位置
     */
    public int onViewReleasedHorizontal(boolean hWrapped, int maxWidth, int left, int childLeft, float xvel, int hThreshold, boolean hOverVelocityThreshold, float velocityThreshold) {
        return childLeft;
    }

    /**
     * 获取滑动松开手后，获取纵向坐标状态的判断
     *
     * @param vWrapped               sliderChild的高度是否被包裹在slider
     * @param maxHeight              slider的宽度
     * @param top                    sliderChild松手时的top
     * @param childTop               sliderChild初始状态的top
     * @param yvel                   y轴的滑动速度
     * @param vThreshold             纵向划开的高度的阀值
     * @param vOverVelocityThreshold 是否达到设定的纵向划开速度
     * @param velocityThreshold      纵向划开速度阀值
     * @return 松开手后sliderChild要移动到终点的top位置
     */
    public int onViewReleasedVertical(boolean vWrapped, int maxHeight, int top, int childTop, float yvel, int vThreshold, boolean vOverVelocityThreshold, float velocityThreshold) {
        return childTop;
    }


    public float onViewPositionChanged(int contentViewWidth, int contentViewHeight, int left, int top) {
        return 0;
    }

    public boolean onViewDragStateChanged(int contentViewLeft, int contentViewTop) {
        return false;
    }

    /**
     * @return 获取当前position对应的Flags
     */
    public int getEdgeFlags() {
        return 0;
    }

    protected final int getViewWidthOrHeight(int size) {
        return size;
    }

    /**
     * 根据来返回可划起的SliderChild的范围
     *
     * @param x      x落点坐标值
     * @param y      y落点坐标值
     * @param width  可触摸宽度最大值
     * @param height 可触摸高度最大值
     * @return 设定的可触摸划起sliderChild长或宽的大小
     * @see SliderPosition
     */
    public int getViewSize(float x, float y, int width, int height, int childLeft, int childTop) {
        return 0;
    }


    /**
     * @param childLeft sliderChild的初始left
     * @param childTop  sliderChild的初始top
     * @param x         当前触摸点的x坐标
     * @param y         当前触摸点的x坐标
     * @param edgeRange 可触摸划起sliderChild的范围
     * @param edgeSize  设定的可触摸划起sliderChild长或宽的大小
     * @return 当前方向是否可滑动
     */
    public boolean canDragFromEdge(int childLeft, int childTop, float x, float y, float edgeRange, float edgeSize) {
        return false;
    }


    /**
     * 设置背景阴影区域
     *
     * @param child     sliderChild
     * @param childLeft sliderChild初始的Left
     * @param childTop  sliderChild初始的Top
     * @param rect      阴影区域
     */
    public void setScrimRect(View child, int childLeft, int childTop, Rect rect) {
    }

    /**
     * @return 当ui为activity时，获取当前方向的动画
     */
    public int[] getActivitySlidingAmins() {
        return new int[]{android.R.anim.fade_in, android.R.anim.fade_out};
    }


    /**
     * 加入sliderChild时要达到的坐标
     *
     * @param decorView sliderChild
     * @return 目标坐标值
     */
    public Rect getSlidingInRect(View decorView) {
        return null;
    }

    /**
     * 移除sliderChild时要达到的坐标
     *
     * @param decorView sliderChild
     * @return 目标坐标值
     */
    public Rect getSlidingOutRect(View decorView) {
        return null;
    }


    /**
     * @param value 要判断的值
     * @param min   value不能超过的最小值
     * @param max   value不能超过的最大值
     * @return 返回一个不大于max和不小于min的值，否则返回max或min
     */
    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static float percent(float progress, float range) {
        return Math.max(Math.min(1, 1f - (Math.abs(progress) / range)), 0);
    }

    public static SliderPosition getTouchPosition(int left, int childLeft, int top, int childTop) {
        SliderPosition position = null;
        if (left != childLeft && top == childTop) {
            position = left > childLeft ? sPositionChildren[0] : sPositionChildren[1];
        } else if (top != childTop && left == childLeft) {
            position = top > childTop ? sPositionChildren[2] : sPositionChildren[3];
        }
        return position;
    }

    public static SliderPosition getSliderPosition(int edgeFlag) {
        for (SliderPosition position : sPositionChildren) {
            if (position.getEdgeFlags() == edgeFlag) {
                return position;
            }
        }
        return LeftPosition.getInstance();
    }

}
