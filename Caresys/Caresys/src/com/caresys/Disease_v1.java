package com.caresys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class Disease_v1 extends Activity{
	ImageButton btn_d1,btn_d2,btn_d3,btn_re_disease;
	Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_v1);
        btn_d1 = (ImageButton)findViewById(R.id.btn_day);
        btn_d2 = (ImageButton)findViewById(R.id.btn_twoweek);
        btn_d3 = (ImageButton)findViewById(R.id.btn_month);
        btn_re_disease= (ImageButton)findViewById(R.id.btn_re_disease);
        
        btn_d1.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		intent.setClass(Disease_v1.this,QuesGoodSleepLive.class);
        		startActivity(intent);
        	}
        });
        btn_d2.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		intent.setClass(Disease_v1.this, QuesLCQ.class);
        		startActivity(intent);
        	}
        });
        btn_d3.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		intent.setClass(Disease_v1.this,QuesMonth.class);
        		startActivity(intent);
        	}
        });
        btn_re_disease.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(Disease_v1.this, Menu_v1.class);
				startActivity(intent);
			}
		});
    }
}