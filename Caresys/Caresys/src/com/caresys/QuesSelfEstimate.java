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

public class QuesSelfEstimate extends Activity implements OnClickListener{
	
	private Button sendBtn;
	private String uriAPI = "http://140.116.39.44/sleepselfPostTest.php";
	private RadioGroup group1,group2,group3,group4,group5,group6,group7,group8,group9,group10;
	private String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;

	
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
					Toast.makeText(QuesSelfEstimate.this, result, Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quesselfestimate);
		
		sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
		ID = sharedPreferences.getString("id", "0000000000");
		
	    sendBtn = (Button) findViewById(R.id.btn_quespsqi);
	    
	    group1 = (RadioGroup) findViewById(R.id.radioGroup1);
	    group2 = (RadioGroup) findViewById(R.id.radioGroup2);
	    group3 = (RadioGroup) findViewById(R.id.radioGroup3);
	    group4 = (RadioGroup) findViewById(R.id.radioGroup4);
	    group5 = (RadioGroup) findViewById(R.id.radioGroup5);
	    group6 = (RadioGroup) findViewById(R.id.radioGroup6);
	    group7 = (RadioGroup) findViewById(R.id.radioGroup7);
	    group8 = (RadioGroup) findViewById(R.id.radioGroup8);
	    group9 = (RadioGroup) findViewById(R.id.radioGroup9);
	    group10 = (RadioGroup) findViewById(R.id.radioGroup10);
	    
	    if (sendBtn != null)
	          sendBtn.setOnClickListener(this);
	    
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
			case R.id.radio0:
				q1 = "Yes";
				break;	
			case R.id.radio1:
				q1 = "No";
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
			case R.id.radio2:
				q2 = "Yes";
				break;	
			case R.id.radio3:
				q2 = "No";
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
			case R.id.radio4:
				q3 = "Yes";
				break;	
			case R.id.radio5:
				q3 = "No";
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
			case R.id.radio6:
				q4 = "Yes";
				break;	
			case R.id.radio7:
				q4 = "No";
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
			case R.id.radio8:
				q5 = "Yes";
				break;	
			case R.id.radio9:
				q5 = "No";
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
			case R.id.radio10:
				q6 = "Yes";
				break;	
			case R.id.radio11:
				q6 = "No";
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
			case R.id.radio12:
				q7 = "Yes";
				break;	
			case R.id.radio13:
				q7 = "No";
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
			case R.id.radio14:
				q8 = "Yes";
				break;	
			case R.id.radio15:
				q8 = "No";
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
			case R.id.radio16:
				q9 = "Yes";
				break;	
			case R.id.radio17:
				q9 = "No";
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
			case R.id.radio18:
				q10 = "Yes";
				break;	
			case R.id.radio19:
				q10 = "No";
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
	         
	             // 擷取文字框上的文字
	             String msg1 = ID;
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
	             // 啟動一個Thread(執行緒)，將要傳送的資料放進Runnable中，讓Thread執行
	             Thread t = new Thread(new sendPostRunnable(msg1,msg01,msg02,msg03,msg04,msg05,msg06,msg07,msg08,msg09,msg10));
	             t.start();
	             
	             Intent intent = new Intent();
					intent.setClass(QuesSelfEstimate.this, SportService.class);
					startService(intent);
	             
	             Intent intent2 = new Intent();
	     		intent2.setClass(QuesSelfEstimate.this, Setting_v1.class);
	     		startService(intent2);
	      }
	   }

	private String sendPostDataToInternet(String strTxt1,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt10)
	   {
	      /* 建立HTTP Post連線 */
	      HttpPost httpRequest = new HttpPost(uriAPI);
	      /*
	       * Post運作傳送變數必須用NameValuePair[]陣列儲存
	       */
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair("ID", strTxt1));
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
	      String strTxt1 = null;
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
	      	 
	      // 建構子，設定要傳的字串
	      public sendPostRunnable(String strTxt1,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt10)
	      {
	         this.strTxt1 = strTxt1;
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
	          String result = sendPostDataToInternet(strTxt1,strTxt01,strTxt02,strTxt03,strTxt04,strTxt05,strTxt06,strTxt07,strTxt08,strTxt09,strTxt10);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	          
	      }
	   }
}