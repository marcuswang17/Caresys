package com.caresys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.ImageButton;

public class ChooseDisease extends Activity {
	
	
	ImageButton btn_sapnea,btn_copd,btn_re_disease;
	Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_disease);
		
		btn_sapnea = (ImageButton)findViewById(R.id.sleepapnea);
        btn_copd = (ImageButton)findViewById(R.id.copd);
        btn_re_disease = (ImageButton)findViewById(R.id.btn_re_disease);
        
        btn_sapnea.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		intent.setClass(ChooseDisease.this,Disease_v1.class);
        		startActivity(intent);
        	}
        });
        btn_copd.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		intent.setClass(ChooseDisease.this,COPD.class);
        		startActivity(intent);
        	}
        });
        btn_re_disease.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(ChooseDisease.this, Menu_v1.class);
				startActivity(intent);
			}
		});
	}
}
