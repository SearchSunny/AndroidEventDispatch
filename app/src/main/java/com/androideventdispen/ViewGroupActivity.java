package com.androideventdispen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.widget.MyLayout;

/**
 * viewGroup分发
 * ViewGroup中有一个onInterceptTouchEvent方法
 * Android中touch事件的传递，是先传递到ViewGroup，再传递到View的。
 */

public class ViewGroupActivity extends AppCompatActivity {

    private TextView textView;
    private MyLayout myLayout;
    private Button btnViewGroupTest1,btnViewGroupTest2;

    private StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup);
        textView = findViewById(R.id.txt_viewgroup);
        myLayout = findViewById(R.id.ll_mylayout);
        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textView.setText("myLayout on touch");
                sb.append("myLayout on touch"+"\n");
                Log.d("TAG", "myLayout on touch");
                return false;
            }
        });
        btnViewGroupTest1 = findViewById(R.id.btn_viewgroup_test1);
        btnViewGroupTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("You clicked button1");
                sb.append("You clicked button1"+"\n");
                Log.d("TAG", "You clicked button1");
            }
        });

        btnViewGroupTest2 = findViewById(R.id.btn_viewgroup_test2);
        btnViewGroupTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("You clicked button2");
                sb.append("You clicked button2"+"\n");
                Log.d("TAG", "You clicked button2");
            }
        });
        //textView.setText(sb.toString());


        //只要触摸了任何控件，就一定会调用该控件的dispatchTouchEvent方法。这个说法没错，只不过还不完整而已。
        // 实际情况是，当你点击了某个控件，首先会去调用该控件所在布局的dispatchTouchEvent方法，然后在布局的dispatchTouchEvent方法中找到被点击的相应控件，再去调用该控件的dispatchTouchEvent方法。
        // 如果我们点击了MyLayout中的按钮，会先去调用MyLayout的dispatchTouchEvent方法，可是你会发现MyLayout中并没有这个方法。那就再到它的父类LinearLayout中找一找，发现也没有这个方法。
        // 那只好继续再找LinearLayout的父类ViewGroup，你终于在ViewGroup中看到了这个方法，按钮的dispatchTouchEvent方法就是在这里调用的




        //1. Android事件分发是先传递到ViewGroup，再由ViewGroup传递到View的。

        //2. 在ViewGroup中可以通过onInterceptTouchEvent方法对事件传递进行拦截，onInterceptTouchEvent方法返回true代表不允许事件继续向子View传递，返回false代表不对事件进行拦截，默认返回false。

        //3. 子View中如果将传递的事件消费掉，ViewGroup中将无法接收到任何事件。
    }

}
