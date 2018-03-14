package com.androideventdispen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Android事件分发机制
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private Button buttonView,buttonViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        buttonView = findViewById(R.id.button);
        buttonViewGroup = findViewById(R.id.button2);

        buttonView.setOnClickListener(this);
        buttonViewGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                textView.setText("You clicked button="+buttonView.getText().toString());
                startActivity(new Intent(MainActivity.this,ViewActivity.class));
                break;
            case R.id.button2:
                textView.setText("You clicked button="+buttonViewGroup.getText().toString());
                startActivity(new Intent(MainActivity.this,ViewGroupActivity.class));
                break;
        }
    }

    //activity的onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        textView.setText("activity onTouch execute, action " + event.getAction());
        return super.onTouchEvent(event);
    }
}
