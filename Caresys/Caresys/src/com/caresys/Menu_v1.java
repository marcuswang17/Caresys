package com.caresys;

import android.app.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu_v1 extends Activity {
	
	private TextView info;
	private MyReceiver receiver;
    private IntentFilter filter;

    ImageButton btn_ques,btn_setting,btn_exit;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_v1);
                
        btn_ques = (ImageButton)findViewById(R.id.btn_question);
        btn_setting = (ImageButton)findViewById(R.id.btn_set);
        btn_ques.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent();
        		intent.setClass(Menu_v1.this, ChooseDisease.class);
        		startActivity(intent);
        	}
        });
        
        btn_setting.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent();
        		intent.setClass(Menu_v1.this, Setting_v1.class);
        		startActivity(intent);
        	}
        });
                
        receiver = new MyReceiver();
        filter = new IntentFilter("Infomation");
        registerReceiver(receiver, filter);
   		info = (TextView) findViewById(R.id.infomation);
   	}
           
    private class MyReceiver extends BroadcastReceiver {
  	  @Override
  	  public void onReceive(Context context, Intent intent) {
  		  String H_24R = intent.getStringExtra("H_24R");
  		  String HUMD = intent.getStringExtra("HUMD");
  		  String PSI = intent.getStringExtra("PSI");
  		  String Major = intent.getStringExtra("Major");
  		  String Info = intent.getStringExtra("Info");
  		  String warning = intent.getStringExtra("warning");
  	   
  	      info.setText("雨量: " + H_24R + "\r\n" +
  	      "濕度: " + HUMD + "\r\n" +
  	      "空氣汙染指標(PSI): " + PSI + "\r\n" +
  	      "主要汙染物: " + Major + "\r\n\r\n" +
  	      "天氣資訊: " + Info + "\r\n\r\n" +
  	      "警告資訊:\r\n" + warning
  	      );
  	  }     
  }
}
