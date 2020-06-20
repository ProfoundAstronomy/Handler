package com.android.study.handlerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_text;
    private Button btn_send_message;
   //创建主线程
    private Handler mHandler = new Handler(){

        //线程消息
       @Override
       public void handleMessage(@NonNull Message msg) {
           //super.handleMessage(msg);
           //循环，发送消息
           switch (msg.what){
               case 1 :
                   tv_text.setText("文本在子线程中被修改了");
                   break;
                   default:
                       break;
            }
       }
   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化UI
        initUI();

        //为发送按钮注册点击事件
        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //按钮开启子线程，处理事务
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //创建Message消息对象
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                    //执行子线程
                }).start();
            }
        });
        
    }

    private void initUI() {
        tv_text = findViewById(R.id.tv_text);
        btn_send_message = findViewById(R.id.btn_send_message);
    }
}
