package com.library.libraryoccupyseat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public class SpringScrollView extends ScrollView {

    /*private static final String TAG = "ElasticScrollView";

    //移动因子, 是一个百分比, 比如手指移动了100px, 那么View就只移动50px
    //目的是达到一个延迟的效果
    private static final float MOVE_FACTOR = 0.5f;

    //松开手指后, 界面回到正常位置需要的动画时间
    private static final int ANIM_TIME = 300;

    //ScrollView的子View， 也是ScrollView的唯一一个子View
    private View contentView;

    //手指按下时的Y值, 用于在移动时计算移动距离
    //如果按下时不能上拉和下拉， 会在手指移动时更新为当前手指的Y值
    private float startY;

    //用于记录正常的布局位置
    private Rect originalRect = new Rect();

    //手指按下时记录是否可以继续下拉
    private boolean canPullDown = false;

    //手指按下时记录是否可以继续上拉
    private boolean canPullUp = false;

    //在手指滑动的过程中记录是否移动了布局
    private boolean isMoved = false;

    public SpringScrollView(Context context) {
        super(context);
    }

    public SpringScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if(contentView == null) return;

        //ScrollView中的唯一子控件的位置信息, 这个位置信息在整个控件的生命周期中保持不变
        originalRect.set(contentView.getLeft(), contentView.getTop(), contentView
                .getRight(), contentView.getBottom());
    }

    *//**
     * 在触摸事件中, 处理上拉和下拉的逻辑
     *//*
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (contentView == null) {
            return super.dispatchTouchEvent(ev);
        }

        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:

                //判断是否可以上拉和下拉
                canPullDown = isCanPullDown();
                canPullUp = isCanPullUp();

                //记录按下时的Y值
                startY = ev.getY();
                break;

            case MotionEvent.ACTION_UP:

                if(!isMoved) break;  //如果没有移动布局， 则跳过执行

                // 开启动画
                TranslateAnimation anim = new TranslateAnimation(0, 0, contentView.getTop(),
                        originalRect.top);
                anim.setDuration(ANIM_TIME);

                contentView.startAnimation(anim);

                // 设置回到正常的布局位置
                contentView.layout(originalRect.left, originalRect.top,
                        originalRect.right, originalRect.bottom);

                //将标志位设回false
                canPullDown = false;
                canPullUp = false;
                isMoved = false;

                break;
            case MotionEvent.ACTION_MOVE:

                //在移动的过程中， 既没有滚动到可以上拉的程度， 也没有滚动到可以下拉的程度
               *//* if(!canPullDown && !canPullUp) {
                    startY = ev.getY();
                    canPullDown = isCanPullDown();
                    canPullUp = isCanPullUp();

                    break;
                }
*//*
                //计算手指移动的距离
                float nowY = ev.getY();
                int deltaY = (int) (nowY - startY);

                //是否应该移动布局
                boolean shouldMove =
                        (canPullDown && deltaY > 0)    //可以下拉， 并且手指向下移动
                                || (canPullUp && deltaY< 0)    //可以上拉， 并且手指向上移动
                                || (canPullUp && canPullDown); //既可以上拉也可以下拉（这种情况出现在ScrollView包裹的控件比ScrollView还小）

                if(shouldMove){
                    //计算偏移量
                    int offset = (int)(deltaY * MOVE_FACTOR);

                    //随着手指的移动而移动布局
                    contentView.layout(originalRect.left, originalRect.top + offset,
                            originalRect.right, originalRect.bottom + offset);

                    isMoved = true;  //记录移动了布局
                }

                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    *//**
     * 判断是否滚动到顶部
     *//*
    private boolean isCanPullDown() {
        return getScrollY() == 0 ||
                contentView.getHeight() < getHeight() + getScrollY();
    }

    *//**
     * 判断是否滚动到底部
     *//*
    private boolean isCanPullUp() {
        return  contentView.getHeight() <= getHeight() + getScrollY();
    }
*/

    private static final String TAG = "BounceScrollView";

    private View inner;// 孩子View

    private float y;// 点击时y坐标

    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)

    private boolean isCount = false;// 是否开始计算
    private float lastX = 0;
    private float lastY = 0;
    private float currentX = 0;
    private float currentY = 0;
    private float distanceX = 0;
    private float distanceY = 0;
    private boolean upDownSlide = false; //判断上下滑动的flag

    public SpringScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
            View view;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub

        currentX = ev.getX();
        currentY = ev.getY();
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                distanceX = currentX - lastX;
                distanceY = currentY - lastY;
                if(Math.abs(distanceX)<Math.abs(distanceY) && Math.abs(distanceY)>12){

                    upDownSlide = true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        lastX = currentX;
        lastY = currentY;
        if (upDownSlide && inner != null) commOnTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.onInterceptTouchEvent(ev);
    }

    /***
     * 监听touch
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


    /***
     * 触摸事件
     *
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                // 手指松开.
                if (isNeedAnimation()) {
                    animation();
                    isCount = false;
                }
                clear0();
                break;
            
            case MotionEvent.ACTION_MOVE:
                final float preY = y;// 按下时的y坐标
                float nowY = ev.getY();// 时时y坐标
                int deltaY = (int) (preY - nowY);// 滑动距离
                if (!isCount) {
                    deltaY = 0; // 在这里要归0.
                }

                y = nowY;
                // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                if (isNeedMove()) {
                    // 初始化头部矩形
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                    }
                    // 移动布局
                    inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
                            inner.getRight(), inner.getBottom() - deltaY / 2);
                }
                isCount = true;
                break;

            default:
                break;
        }
    }

    /***
     * 回缩动画
     */
    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);

        normal.setEmpty();

    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
     *
     * getHeight()：获取的是屏幕的高度
     *
     * @return
     */
    public boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 0是顶部，后面那个是底部
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

    private void clear0(){
        lastX = 0;
        lastY = 0;
        distanceX = 0;
        distanceY = 0;
        upDownSlide = false;
    }


}