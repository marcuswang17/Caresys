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

public class QuesBerlin extends Activity implements OnClickListener{
	
	private Button sendBtn;
	private String uriAPI = "http://140.116.39.44/berlinPostTest.php";
	private RadioGroup group1,group2,group3,group4,group5,group6,group7,group8,group9,group10;
	private String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;
	
	protected static final int REFRESH_DATA = 0x00000001;
	
	SharedPreferences sharedPreferences;
	private String ID;
	
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg1) {
	          switch (msg1.what)
	          {
	          // 顯示網路上抓取的資料
	          case REFRESH_DATA:
	             String result = null;
	             if (msg1.obj instanceof String)
	                result = (String) msg1.obj;
	             if (result != null)
	                // 印出網路回傳的文字
	                Toast.makeText(QuesBerlin.this, result, Toast.LENGTH_LONG).show();
	             break;
	          }
	      }
	      
	   };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quesberlin);
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
		sendBtn = (Button) findViewById(R.id.buttonpost);

	    group1 = (RadioGroup) findViewById(R.id.radioGroupquestion1);
	    group2 = (RadioGroup) findViewById(R.id.radioGroupquestion2);
	    group3 = (RadioGroup) findViewById(R.id.radioGroupquestion3);
	    group4 = (RadioGroup) findViewById(R.id.radioGroupquestion4);
	    group5 = (RadioGroup) findViewById(R.id.radioGroupquestion5);
	    group6 = (RadioGroup) findViewById(R.id.radioGroupquestion6);
	    group7 = (RadioGroup) findViewById(R.id.radioGroupquestion7);
	    group8 = (RadioGroup) findViewById(R.id.radioGroupquestion8);
	    group9 = (RadioGroup) findViewById(R.id.radioGroupquestion9);
	    group10 = (RadioGroup) findViewById(R.id.radioGroupquestion10);
	    
	    group1.setOnCheckedChangeListener(listener1);
	    group2.setOnCheckedChangeListener(listener2);
	    group3.setOnCheckedChangeListener(listener3);
	    group4.setOnCheckedChangeListener(listener4);
	    group5.setOnCheckedChangeListener(listener5);
	    group6.setOnCheckedChangeListener(listener6);
	    group7.setOnCheckedChangeListener(listener7);
	    group8.setOnCheckedChangeListener(listener8);
	    group9.setOnCheckedChangeListener(listener9);
	    group10.setOnCheckedChangeListener(listener10);
	}
	
	private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radioquestion0:
				q1 = "yes";
				break;	
			case R.id.radioquestion1:
				q1 = "no";
				break;
			case R.id.radioquestion2:
				q1 = "unknown";
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
			case R.id.radioquestion3:
				q2 = "比呼吸聲稍微大聲一點";
				break;	
			case R.id.radioquestion4:
				q2 = "跟講話聲差不多";
				break;
			case R.id.radioquestion5:
				q2 = "比講話聲還大";
				break;
			case R.id.radioquestion6:
				q2 = "非常大聲";
				break;
			default :
				q2 = " ";
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radioquestion8:
				q3 = "幾乎每天";
				break;	
			case R.id.radioquestion9:
				q3 = "每週1~2次";
				break;
			case R.id.radioquestion10:
				q3 = "每週3~4次";
				break;
			case R.id.radioquestion11:
				q3 = "每月1~2次";
				break;
			default :
				q3 = " ";
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener4 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radioquestion13:
				q4 = "yes";
				break;	
			case R.id.radioquestion14:
				q4 = "no";
				break;
			case R.id.radioquestion15:
				q4 = "unknown";
				break;
			default :
				q4 = " ";
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener5 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radioquestion16:
				q5 = "幾乎每天";
				break;	
			case R.id.radioquestion17:
				q5 = "每週1~2次";
				break;
			case R.id.radioquestion18:
				q5 = "每週3~4次";
				break;
			case R.id.radioquestion19:
				q5 = "每月1~2次";
				break;
			case R.id.radioquestion20:
				q5 = "從未";
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
			case R.id.radioquestion21:
				q6 = "幾乎每天";
				break;	
			case R.id.radioquestion22:
				q6 = "每週1~2次";
				break;
			case R.id.radioquestion23:
				q6 = "每週3~4次";
				break;
			case R.id.radioquestion24:
				q6 = "每月1~2次";
				break;
			case R.id.radioquestion25:
				q6 = "從未";
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
			case R.id.radioquestion26:
				q7 = "幾乎每天";
				break;	
			case R.id.radioquestion27:
				q7 = "每週1~2次";
				break;
			case R.id.radioquestion28:
				q7 = "每週3~4次";
				break;
			case R.id.radioquestion29:
				q7 = "每月1~2次";
				break;
			case R.id.radioquestion30:
				q7 = "從未";
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
			case R.id.radioquestion31:
				q8 = "yes";
				break;	
			case R.id.radioquestion32:
				q8 = "no";
				break;
			
			default :
				
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener9 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radioquestion33:
				q9 = "幾乎每天";
				break;	
			case R.id.radioquestion34:
				q9 = "每週1~2次";
				break;
			case R.id.radioquestion35:
				q9 = "每週3~4次";
				break;
			case R.id.radioquestion36:
				q9 = "每月1~2次";
				break;
			default :
				q9 = " ";
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener10 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radioquestion38:
				q10 = "yes";
				break;	
			case R.id.radioquestion39:
				q10 = "no";
				break;
			case R.id.radioquestion40:
				q10 = "unknown";
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
	             String msg09 = q9;
	             String msg10 = q10;
	             
	             Thread t = new Thread(new sendPostRunnable(msg0,msg01,msg02,msg03,msg04,msg05,msg06,msg07,msg08,msg09,msg10));
	             t.start();
			
	             Intent intent = new Intent();
	             intent.setClass(QuesBerlin.this, Menu_v1.class);
	             startActivity(intent);
		}
	   }
	
	private String sendPostDataToInternet(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt10)
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
	      params.add(new BasicNameValuePair("Q9", strTxt09));
	      params.add(new BasicNameValuePair("Q10", strTxt10));
	      
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
	      
	      public sendPostRunnable(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt10)
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
	      }
	      
	      @Override
	      public void run()
	      {
	    	  String result = sendPostDataToInternet(strTxt0,strTxt01,strTxt02,strTxt03,strTxt04,strTxt05,strTxt06,strTxt07,strTxt08,strTxt09,strTxt10);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	      }
	   }
}
