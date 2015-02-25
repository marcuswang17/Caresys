package com.caresys;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView; 

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;
import android.view.View.OnClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuesNNESQ extends Activity implements OnClickListener{
	
	private Button sendBtn;
	private String uriAPI = "http://140.116.39.44/nnesqPostTest.php";
	private RadioGroup group0,group1,group2,group3,group4,group5,group6,group7,group8;
	private RadioGroup group10,group12,group13,group14,group15,group16,group18,group19;
	private String q0,q1,q2,q3,q4,q5,q6,q7,q8,q9;
	private String q10,q11,q12,q13,q14,q15,q16,q17,q18,q19;
	private SeekBar seekBar1,seekBar2,seekBar3;
	private TextView seekBarValue1,seekBarValue2,seekBarValue3 ;
	
	SharedPreferences sharedPreferences;
	private String ID;
	
	protected static final int REFRESH_DATA = 0x00000001;
	
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg1) {
			switch (msg1.what) {
			// 顯示網路上抓取的資料
			case REFRESH_DATA:
				String result = null;
				if (msg1.obj instanceof String)
					result = (String) msg1.obj;
				if (result != null)
					// 印出網路回傳的文字
					Toast.makeText(QuesNNESQ.this, result, Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ques_nnesq);
		
		SetObjFunction();
		SetMyOnClick();
		
		sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
		ID = sharedPreferences.getString("id", "0000000000");
		
		seekBarValue1.setText(seekBar1.getProgress() + "/" + seekBar1.getMax());
		seekBarValue2.setText(seekBar2.getProgress() + "/" + seekBar2.getMax());
		seekBarValue3.setText(seekBar3.getProgress() + "/" + seekBar3.getMax());
		
		seekBar1.setOnSeekBarChangeListener( new OnSeekBarChangeListener() { 
			int progress = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) { progress = progresValue; }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
            // Do something here,if you want to do anything at the start of touching the seekbar
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value in textview
				seekBarValue1.setText(progress + "/" + seekBar1.getMax());
				q9 = String.valueOf(progress);
			}
		});
		
		seekBar2.setOnSeekBarChangeListener( new OnSeekBarChangeListener() { 
			int progress2 = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) { progress2 = progresValue; }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
            // Do something here,if you want to do anything at the start of touching the seekbar
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value in textview
				seekBarValue2.setText(progress2 + "/" + seekBar1.getMax());
				q11 = String.valueOf(progress2);
			}
		});
		
		seekBar3.setOnSeekBarChangeListener( new OnSeekBarChangeListener() { 
			int progress3 = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) { progress3 = progresValue; }
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
            // Do something here,if you want to do anything at the start of touching the seekbar
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// Display the value in textview
				seekBarValue3.setText(progress3 + "/" + seekBar1.getMax());
				q17 = String.valueOf(progress3);
			}
		});
	}
	
	private void SetMyOnClick() {
		// TODO Auto-generated method stub
		sendBtn.setOnClickListener(this);
	}
	
	private void SetObjFunction() {
		sendBtn = (Button) findViewById(R.id.button1);
	    group0 = (RadioGroup) findViewById(R.id.radioGroup1);
	    group1 = (RadioGroup) findViewById(R.id.radioGroup2);
	    group2 = (RadioGroup) findViewById(R.id.radioGroup3);
	    group3 = (RadioGroup) findViewById(R.id.radioGroup4);
	    group4 = (RadioGroup) findViewById(R.id.radioGroup5);
	    group5 = (RadioGroup) findViewById(R.id.radioGroup6);
	    group6 = (RadioGroup) findViewById(R.id.radioGroup7);
	    group7 = (RadioGroup) findViewById(R.id.radioGroup8);
	    group8 = (RadioGroup) findViewById(R.id.radioGroup9);
	    group10 = (RadioGroup) findViewById(R.id.radioGroup11);
	    group12 = (RadioGroup) findViewById(R.id.radioGroup13);
	    group13 = (RadioGroup) findViewById(R.id.radioGroup14);
	    group14 = (RadioGroup) findViewById(R.id.radioGroup15);
	    group15 = (RadioGroup) findViewById(R.id.radioGroup16);
	    group16 = (RadioGroup) findViewById(R.id.radioGroup17);
	    group18 = (RadioGroup) findViewById(R.id.radioGroup19);
	    group19 = (RadioGroup) findViewById(R.id.radioGroup20);
	    
	    seekBar1 = (SeekBar)findViewById(R.id.seekBar1b); 
	    seekBar2 = (SeekBar)findViewById(R.id.seekBar2b); 
	    seekBar3 = (SeekBar)findViewById(R.id.seekBar4e);
	    
	    seekBarValue1 = (TextView)findViewById(R.id.textView1b);
	    seekBarValue2 = (TextView)findViewById(R.id.textView2b);
	    seekBarValue3 = (TextView)findViewById(R.id.textView4e);

	       
	    group0.setOnCheckedChangeListener(listener0);
	    group1.setOnCheckedChangeListener(listener1);
	    group2.setOnCheckedChangeListener(listener2);
	    group3.setOnCheckedChangeListener(listener3);
	    group4.setOnCheckedChangeListener(listener4);
	    group5.setOnCheckedChangeListener(listener5);
	    group6.setOnCheckedChangeListener(listener6);
	    group7.setOnCheckedChangeListener(listener7);
	    group8.setOnCheckedChangeListener(listener8);
	    group10.setOnCheckedChangeListener(listener10);
	    group12.setOnCheckedChangeListener(listener12);
	    group13.setOnCheckedChangeListener(listener13);
	    group14.setOnCheckedChangeListener(listener14);
	    group15.setOnCheckedChangeListener(listener15);
	    group16.setOnCheckedChangeListener(listener16);
	    group18.setOnCheckedChangeListener(listener18);
	    group19.setOnCheckedChangeListener(listener19);
	}
	

	
private RadioGroup.OnCheckedChangeListener listener0 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio10:
				q0 = "yes";
				break;	
			case R.id.radio11:
				q0 = "no";
				break;
			default :
				
				break;
			
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio20:
				q1 = "yes";
				break;	
			case R.id.radio21:
				q1 = "no";
				break;
			default :
				
				break;
			
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio30:
				q2 = "yes";
				break;	
			case R.id.radio31:
				q2 = "no";
				break;
			default :
				
				break;
			}
		}
	};

private RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio40:
				q3 = "yes";
				break;	
			case R.id.radio41:
				q3 = "no";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener4 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio50:
				q4 = "yes";
				break;	
			case R.id.radio51:
				q4 = "no";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener5 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio60:
				q5 = "yes";
				break;	
			case R.id.radio61:
				q5 = "no";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener6 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio70:
				q6 = "yes";
				break;	
			case R.id.radio71:
				q6 = "no";
				break;	
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener7 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio80:
				q7 = "yes";
				break;	
			case R.id.radio81:
				q7 = "no";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener8 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio90:
				q8 = "yes";
				break;	
			case R.id.radio91:
				q8 = "no";
				break;
			default :
				
				break;
			}
		}
	};
	

	
private RadioGroup.OnCheckedChangeListener listener10 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio110:
				q10 = "幾乎沒有過";
				break;	
			case R.id.radio111:
				q10 = "大約一次";
				break;
			case R.id.radio112:
				q10 = "兩次";
				break;
			case R.id.radio113:
				q10 = "三次";
				break;
			case R.id.radio114:
				q10 = "四次以上";
				break;
			default :
				
				break;
			
			}
		}
	};
	

	
private RadioGroup.OnCheckedChangeListener listener12 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio130:
				q12 = "yes";
				break;	
			case R.id.radio131:
				q12 = "no";
				break;
			default :
				
				break;
			}
		}
	};

private RadioGroup.OnCheckedChangeListener listener13 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio140:
				q13 = "從未發生";
				break;	
			case R.id.radio141:
				q13 = "一週一次或更少";
				break;
			case R.id.radio142:
				q13 = "一週兩到三次";
				break;
			case R.id.radio143:
				q13 = "一週四到六次";
				break;
			case R.id.radio144:
				q13 = "每天發生";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener14 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio150:
				q14 = "從未發生";
				break;	
			case R.id.radio151:
				q14 = "不到一年";
				break;
			case R.id.radio152:
				q14 = "一到五年";
				break;
			case R.id.radio153:
				q14 = "超過五年";
				break;
			case R.id.radio154:
				q14 = "從兒童時期持續至今";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener15 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio160:
				q15 = "從未使用過";
				break;	
			case R.id.radio161:
				q15 = "有時候使用";
				break;
			case R.id.radio162:
				q15 = "大部分時間使用";
				break;
			case R.id.radio163:
				q15 = "經常地使用";
				break;
			
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener16 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio170:
				q16 = "從未有過";
				break;	
			case R.id.radio171:
				q16 = "一週一次或更少";
				break;	
			case R.id.radio172:
				q16 = "一週兩到三次";
				break;	
			case R.id.radio173:
				q16 = "一週四到六次";
				break;	
			case R.id.radio174:
				q16 = "每天發生";
				break;	
				
			default :
				
				break;
			}
		}
	};
	

	
private RadioGroup.OnCheckedChangeListener listener18 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio190:
				q18 = "從未發生";
				break;	
			case R.id.radio191:
				q18 = "有時候發生";
				break;
			case R.id.radio192:
				q18 = "常發生";
				break;
			case R.id.radio193:
				q18 = "總是發生";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener19 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio200:
				q19 = "yes";
				break;	
			case R.id.radio201:
				q19 = "no";
				break;
			default :
				
				break;
			}
		}
	};
	
	@Override
	   public void onClick(View v)
	   {
			if (v == sendBtn)
			{
				 String msg0 = ID;
				 String msg1 = q0;
	             String msg2 = q1;
	             String msg3 = q2;
	             String msg4 = q3;
	             String msg5 = q4;
	             String msg6 = q5;
	             String msg7 = q6;
	             String msg8 = q7;
	             String msg9 = q8;
	             String msg10 = q9;
	             String msg11 = q10;
	             String msg12 = q11;
	             String msg13 = q12;
	             String msg14 = q13;
	             String msg15 = q14;
	             String msg16 = q15;
	             String msg17 = q16;
	             String msg18 = q17;
	             String msg19 = q18;
	             String msg20 = q19;
	             
	             Thread t = new Thread(new sendPostRunnable(msg0,msg1,msg2,msg3,msg4,msg5,msg6,msg7,msg8,msg9,msg10,msg11,msg12,msg13,msg14,msg15,msg16,msg17,msg18,msg19,msg20));
	             t.start();
	             
	             Intent intent = new Intent();
	             intent.setClass(QuesNNESQ.this, Menu_v1.class);
	             startActivity(intent);
			}
	   }
	
	private String sendPostDataToInternet(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt10,String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15,String strTxt16,String strTxt17,String strTxt18,String strTxt19,String strTxt20)
	   {
		/* 建立HTTP Post連線 */
	      HttpPost httpRequest = new HttpPost(uriAPI);
	      /*
	       * Post運作傳送變數必須用NameValuePair[]陣列儲存
	       */
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair("ID", strTxt0));
	      params.add(new BasicNameValuePair("Q1a-1", strTxt01));
	      params.add(new BasicNameValuePair("Q1a-2", strTxt02));
	      params.add(new BasicNameValuePair("Q1a-3", strTxt03));
	      params.add(new BasicNameValuePair("Q1a-4", strTxt04));
	      params.add(new BasicNameValuePair("Q1a-5", strTxt05));
	      params.add(new BasicNameValuePair("Q1a-6", strTxt06));
	      params.add(new BasicNameValuePair("Q1a-7", strTxt07));
	      params.add(new BasicNameValuePair("Q1a-8", strTxt08));
	      params.add(new BasicNameValuePair("Q1a-9", strTxt09));
	      params.add(new BasicNameValuePair("Q1b", strTxt10));
	      params.add(new BasicNameValuePair("Q2a", strTxt11));
	      params.add(new BasicNameValuePair("Q2b", strTxt12));
	      params.add(new BasicNameValuePair("Q3", strTxt13));
	      params.add(new BasicNameValuePair("Q4a", strTxt14));
	      params.add(new BasicNameValuePair("Q4b", strTxt15));
	      params.add(new BasicNameValuePair("Q4c", strTxt16));
	      params.add(new BasicNameValuePair("Q4d", strTxt17));
	      params.add(new BasicNameValuePair("Q4e", strTxt18));
	      params.add(new BasicNameValuePair("Q5", strTxt19));
	      params.add(new BasicNameValuePair("Q6", strTxt20));
	      
	      try
	      {
	          /* 發出HTTP request */
	          httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	          /* 取得HTTP response */
	          HttpResponse httpResponse = new DefaultHttpClient()
	                .execute(httpRequest);
	          /* 若狀態碼為200 ok */
	          if (httpResponse.getStatusLine().getStatusCode() == 200)
	          {
	             /* 取出回應字串 */
	             String strResult = EntityUtils.toString(httpResponse
	                   .getEntity());
	             // 回傳回應字串
	             return strResult;
	          }
	      } catch (Exception e)
	      {
	          e.printStackTrace();
	      }
	      return null;
		
	   }
	
	class sendPostRunnable implements Runnable
	   {
		  String strTxt0 = null;
		  String strTxt01 = null;
	      String strTxt02 = null;
	      String strTxt03 = null;
	      String strTxt04 = null;
	      String strTxt05 = null;
	      String strTxt06 = null;
	      String strTxt07 = null;
	      String strTxt08 = null;
	      String strTxt09 = null;
	      String strTxt10 = null;
	      String strTxt11 = null;
	      String strTxt12 = null;
	      String strTxt13 = null;
	      String strTxt14 = null;
	      String strTxt15 = null;
	      String strTxt16 = null;
	      String strTxt17 = null;
	      String strTxt18 = null;
	      String strTxt19 = null;
	      String strTxt20 = null;
	      
	      public sendPostRunnable(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt10,String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15,String strTxt16,String strTxt17,String strTxt18,String strTxt19,String strTxt20)
	      {
	    	  this.strTxt0 = strTxt0;	 
	    	  this.strTxt01 = strTxt01;
		         this.strTxt02 = strTxt02;
		         this.strTxt03 = strTxt03;
		         this.strTxt04 = strTxt04;
		         this.strTxt05 = strTxt05;
		         this.strTxt06 = strTxt06;
		         this.strTxt07 = strTxt07;
		         this.strTxt08 = strTxt08;
		         this.strTxt09 = strTxt09;
		         this.strTxt10 = strTxt10;
		         this.strTxt11 = strTxt11;
		         this.strTxt12 = strTxt12;
		         this.strTxt13 = strTxt13;
		         this.strTxt14 = strTxt14;
		         this.strTxt15 = strTxt15;
		         this.strTxt16 = strTxt16;
		         this.strTxt17 = strTxt17;
		         this.strTxt18 = strTxt18;
		         this.strTxt19 = strTxt19;
		         this.strTxt20 = strTxt20;
	      }
	      
	      @Override
	      public void run()
	      {
	    	  String result = sendPostDataToInternet(strTxt0,strTxt01,strTxt02,strTxt03,strTxt04,strTxt05,strTxt06,strTxt07,strTxt08,strTxt09,strTxt10,strTxt11,strTxt12,strTxt13,strTxt14,strTxt15,strTxt16,strTxt17,strTxt18,strTxt19,strTxt20);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	      }
	   }
}
