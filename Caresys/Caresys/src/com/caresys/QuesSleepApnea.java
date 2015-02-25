package com.caresys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuesSleepApnea extends Activity{
	Button btn_quesgoodsleeplive,btn_queslcq,btn_quesepworth,btn_quesberlin,btn_quesnnesq,btn_quesselfestimate,btn_quespsqi;
	Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quessleepapnea); 
		btn_quesgoodsleeplive = (Button)findViewById(R.id.btn_quesgoodsleeplive);
		btn_queslcq = (Button)findViewById(R.id.btn_queslcq);
		btn_quespsqi = (Button)findViewById(R.id.btn_quespsqi);
		btn_quesepworth = (Button)findViewById(R.id.btn_quesepworth);
		btn_quesberlin = (Button)findViewById(R.id.btn_quesberlin);
		btn_quesnnesq = (Button)findViewById(R.id.btn_quesnnesq);
		btn_quesselfestimate = (Button)findViewById(R.id.btn_quesselfestimate); 
		btn_quesgoodsleeplive.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesSleepApnea.this, QuesGoodSleepLive.class);
				startActivity(intent);
			}
		});
		btn_queslcq.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesSleepApnea.this, QuesLCQ.class);
				startActivity(intent);
			}
		});
		btn_quespsqi.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesSleepApnea.this, QuesPSQI.class);
				startActivity(intent);
			}
		});
		btn_quesberlin.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesSleepApnea.this, QuesBerlin.class);
				startActivity(intent);
			}
		});
		btn_quesepworth.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesSleepApnea.this, QuesEpworth.class);
				startActivity(intent);
			}
		});
		btn_quesselfestimate.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesSleepApnea.this, QuesSelfEstimate.class);
				startActivity(intent);
			}
		});
		btn_quesnnesq.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(QuesSleepApnea.this, QuesNNESQ.class);
				startActivity(intent);
			}
		});
	}
}