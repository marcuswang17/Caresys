package com.caresys;

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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuesEpworth extends Activity implements OnClickListener{
	
	private Button sendBtn;
	private String uriAPI = "http://140.116.39.44/epworthPostTest.php";
	private RadioGroup group1,group2,group3,group4,group5,group6,group7,group8;
	private String q1,q2,q3,q4,q5,q6,q7,q8;
	
	SharedPreferences sharedPreferences;
	private String ID;
	
	protected static final int REFRESH_DATA = 0x00000001;
	//public static Handler mHandler;
	
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
					Toast.makeText(QuesEpworth.this, result, Toast.LENGTH_LONG).show();
	             break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quesepworth);
		SetObjFunction();
		SetMyOnClick();
		
		sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
		ID = sharedPreferences.getString("id", "0000000000");
		
	}
	
	private void SetMyOnClick() {
		// TODO Auto-generated method stub
		sendBtn.setOnClickListener(this);
	}
	
	private void SetObjFunction() {
		// TODO Auto-generated method stub
		//定義每個物件變數
		sendBtn = (Button) findViewById(R.id.buttonsend);
    
	    group1 = (RadioGroup) findViewById(R.id.radioGroupsitread);
	    group2 = (RadioGroup) findViewById(R.id.radioGroupwatchtv);
	    group3 = (RadioGroup) findViewById(R.id.radioGroupquitesit);
	    group4 = (RadioGroup) findViewById(R.id.radioGroupsitover);
	    group5 = (RadioGroup) findViewById(R.id.radioGrouprest);
	    group6 = (RadioGroup) findViewById(R.id.radioGrouptalk);
	    group7 = (RadioGroup) findViewById(R.id.radioGroupnodrink);
	    group8 = (RadioGroup) findViewById(R.id.radioGrouptraffic);
	    
	    group1.setOnCheckedChangeListener(listener1);
	    group2.setOnCheckedChangeListener(listener2);
	    group3.setOnCheckedChangeListener(listener3);
	    group4.setOnCheckedChangeListener(listener4);
	    group5.setOnCheckedChangeListener(listener5);
	    group6.setOnCheckedChangeListener(listener6);
	    group7.setOnCheckedChangeListener(listener7);
	    group8.setOnCheckedChangeListener(listener8);
	}
	
	private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radiositread0:
				q1 = "0";
				break;	
			case R.id.radiositread1:
				q1 = "1";
				break;
			case R.id.radiositread2:
				q1 = "2";
				break;
			case R.id.radiositread3:
				q1 = "3";
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
			case R.id.radiowatchtv4:
				q2 = "0";
				break;	
			case R.id.radiowatchtv5:
				q2 = "1";
				break;
			case R.id.radiowatchtv6:
				q2 = "2";
				break;
			case R.id.radiowatchtv7:
				q2 = "3";
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
			case R.id.radioquitesit8:
				q3 = "0";
				break;	
			case R.id.radioquitesit9:
				q3 = "1";
				break;
			case R.id.radioquitesit10:
				q3 = "2";
				break;
			case R.id.radioquitesit11:
				q3 = "3";
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
			case R.id.radiositover12:
				q4 = "0";
				break;	
			case R.id.radiositover13:
				q4 = "1";
				break;
			case R.id.radiositover14:
				q4 = "2";
				break;
			case R.id.radiositover15:
				q4 = "3";
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
			case R.id.radiorest16:
				q5 = "0";
				break;	
			case R.id.radiorest17:
				q5 = "1";
				break;
			case R.id.radiorest18:
				q5 = "2";
				break;
			case R.id.radiorest19:
				q5 = "3";
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
			case R.id.radiotalk20:
				q6 = "0";
				break;	
			case R.id.radiotalk21:
				q6 = "1";
				break;
			case R.id.radiotalk22:
				q6 = "2";
				break;
			case R.id.radiotalk23:
				q6 = "3";
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
			case R.id.radionodrink24:
				q7 = "0";
				break;	
			case R.id.radionodrink25:
				q7 = "1";
				break;
			case R.id.radionodrink26:
				q7 = "2";
				break;
			case R.id.radionodrink27:
				q7 = "3";
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
			case R.id.radiotraffic28:
				q8 = "0";
				break;	
			case R.id.radiotraffic29:
				q8 = "1";
				break;
			case R.id.radiotraffic30:
				q8 = "2";
				break;
			case R.id.radiotraffic31:
				q8 = "3";
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
				 String msg01 = q1;
	             String msg02 = q2;
	             String msg03 = q3;
	             String msg04 = q4;
	             String msg05 = q5;
	             String msg06 = q6;
	             String msg07 = q7;
	             String msg08 = q8;
	             
	             Thread t = new Thread(new sendPostRunnable(msg0,msg01,msg02,msg03,msg04,msg05,msg06,msg07,msg08));
	             t.start();
	             
	             Intent intent = new Intent();
	             intent.setClass(QuesEpworth.this, Menu_v1.class);
	             startActivity(intent);
			}
	   }
	
	private String sendPostDataToInternet(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08)
	   {
		/* 建立HTTP Post連線 */
	      HttpPost httpRequest = new HttpPost(uriAPI);
	      /*
	       * Post運作傳送變數必須用NameValuePair[]陣列儲存
	       */
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair("ID", strTxt0));
	      params.add(new BasicNameValuePair("Q1", strTxt01));
	      params.add(new BasicNameValuePair("Q2", strTxt02));
	      params.add(new BasicNameValuePair("Q3", strTxt03));
	      params.add(new BasicNameValuePair("Q4", strTxt04));
	      params.add(new BasicNameValuePair("Q5", strTxt05));
	      params.add(new BasicNameValuePair("Q6", strTxt06));
	      params.add(new BasicNameValuePair("Q7", strTxt07));
	      params.add(new BasicNameValuePair("Q8", strTxt08));
	      
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
	      
	      public sendPostRunnable(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08)
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
	      }
	      
	      @Override
	      public void run()
	      {
	    	  String result = sendPostDataToInternet(strTxt0,strTxt01,strTxt02,strTxt03,strTxt04,strTxt05,strTxt06,strTxt07,strTxt08);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	      }
	   }
}
