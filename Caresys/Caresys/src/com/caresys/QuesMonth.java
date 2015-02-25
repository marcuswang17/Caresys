package com.caresys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class QuesMonth extends Activity{
	Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quesmonth); 
		ImageButton btn_psqi = (ImageButton)findViewById(R.id.btn_psqi);
		ImageButton btn_epworth = (ImageButton)findViewById(R.id.btn_epworth);
		ImageButton btn_berlin = (ImageButton)findViewById(R.id.btn_berlin);
		ImageButton btn_nnesq = (ImageButton)findViewById(R.id.btn_nnesq);
		ImageButton btn_re_month = (ImageButton)findViewById(R.id.btn_re_month);
		
		btn_psqi.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesMonth.this, QuesPSQI.class);
				startActivity(intent);
			}
		});
		btn_epworth.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesMonth.this, QuesEpworth.class);
				startActivity(intent);
			}
		});
		btn_berlin.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesMonth.this, QuesBerlin.class);
				startActivity(intent);
			}
		});
		btn_nnesq.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesMonth.this,QuesNNESQ.class);
				startActivity(intent);
			}
		});
		btn_re_month.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesMonth.this, Disease_v1.class);
				startActivity(intent);
			}
		});
		
	}
}