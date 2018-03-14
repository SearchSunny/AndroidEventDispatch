package com.androideventdispen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * View分发
 */

public class ViewActivity extends AppCompatActivity {

    private Button btnView;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        btnView = findViewById(R.id.btn_view_test);
        textView = findViewById(R.id.textView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("onClick execute");
                Log.d("TAG", "onClick execute");
            }
        });

        btnView.setOnTouchListener(new View.OnTouchListener() {
           //onTouch方法里能做的事情比onClick要多一些，比如判断手指按下、抬起、移动等事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textView.setText("onTouch execute, action " + event.getAction());
                Log.d("TAG", "onTouch execute, action " + event.getAction());
                //默认返回false
                //return false;
                //返回true则onClick不执行(onTouch方法返回true就认为这个事件被onTouch消费掉了，因而不会再继续向下传递。)
                return true;
            }
        });

        //View中dispatchTouchEvent方法的源码
        /*public boolean dispatchTouchEvent(MotionEvent event) {
        //第一个条件mOnTouchListener正是在setOnTouchListener方法里赋值的，也就是说只要我们给控件注册了touch事件，mOnTouchListener就一定被赋值了
        //第二个条件(mViewFlags & ENABLED_MASK) == ENABLED是判断当前点击的控件是否是enable的，按钮默认都是enable的，因此这个条件恒定为true。
        //第三个条件就比较关键了，mOnTouchListener.onTouch(this, event)，其实也就是去回调控件注册touch事件时的onTouch方法。也就是说如果我们在onTouch方法里返回true，就会让这三个条件全部成立，从而整个方法直接返回true。如果我们在onTouch方法里返回false，就会再去执行onTouchEvent(event)方法。
            if (mOnTouchListener != null && (mViewFlags & ENABLED_MASK) == ENABLED &&
                    mOnTouchListener.onTouch(this, event)) {
                return true;
            }
            return onTouchEvent(event);
        }
*/
        //1. onTouch和onTouchEvent有什么区别，又该如何使用？
        //从源码中可以看出，这两个方法都是在View的dispatchTouchEvent中调用的，onTouch优先于onTouchEvent执行。如果在onTouch方法中通过返回true将事件消费掉，onTouchEvent将不会再执行。

        //另外需要注意的是，onTouch能够得到执行需要两个前提条件
        //1第一mOnTouchListener的值不能为空
        //2第二当前点击的控件必须是enable的
        //因此如果你有一个控件是非enable的，那么给它注册onTouch事件将永远得不到执行。对于这一类控件，如果我们想要监听它的touch事件，就必须通过在该控件中重写onTouchEvent方法来实现。

    }

    //activity的onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        textView.setText("activity onTouch execute, action " + event.getAction());
        return super.onTouchEvent(event);
    }

}
