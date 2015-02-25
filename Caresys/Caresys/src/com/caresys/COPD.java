package com.caresys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class COPD extends Activity {
	
	ImageButton btn_sgro,btn_cat;
	ImageButton btn_re_disease;
	Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_copd);
		
		btn_sgro = (ImageButton)findViewById(R.id.sgrq);
        btn_cat = (ImageButton)findViewById(R.id.cat);
        btn_re_disease = (ImageButton)findViewById(R.id.btn_re_disease);
        
        btn_sgro.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		intent.setClass(COPD.this,QuesCOPDSGRQ.class);
        		startActivity(intent);
        	}
        });
        btn_cat.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		intent.setClass(COPD.this,QuesCAT.class);
        		startActivity(intent);
        	}
        });
        btn_re_disease.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				intent.setClass(COPD.this, Menu_v1.class);
				startActivity(intent);
			}
		});
	}
}
