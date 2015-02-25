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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuesGoodSleepLive extends Activity implements OnClickListener{
	private EditText txtMessage1,txtMessage2,txtMessage3,txtMessage4,txtMessage5,txtMessage6,txtMessage7,txtMessage8,txtMessage9,txtMessage10,txtMessage11,txtMessage12,txtMessage13;
	private Button sendBtn;
	private String uriAPI = "http://140.116.39.44/hmgPostTest.php";
	private RadioGroup group0,group1,group2,group3,group4,group5,group6,group7,group8,group9;
	private String q0,q1,q2,q3,q4,q5,q6,q7,q8,q9;//RadioGroup
	
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
					Toast.makeText(QuesGoodSleepLive.this, result, Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quesgoodsleeplive);
		
		sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
		ID = sharedPreferences.getString("id", "0000000000");
		
		txtMessage1 = (EditText) findViewById(R.id.editText1);
		txtMessage2 = (EditText) findViewById(R.id.editText2);
		txtMessage3 = (EditText) findViewById(R.id.editText3);
		txtMessage4 = (EditText) findViewById(R.id.editText4);
		txtMessage5 = (EditText) findViewById(R.id.editText5);
		txtMessage6 = (EditText) findViewById(R.id.editText6);
		txtMessage7 = (EditText) findViewById(R.id.editText7);
		txtMessage8 = (EditText) findViewById(R.id.editText8);
		txtMessage9 = (EditText) findViewById(R.id.editText9);
		txtMessage10 = (EditText) findViewById(R.id.editText10);
		txtMessage11 = (EditText) findViewById(R.id.editText11);
		txtMessage12 = (EditText) findViewById(R.id.editText12);
		txtMessage13 = (EditText) findViewById(R.id.editText13);
	    sendBtn = (Button) findViewById(R.id.sendBtn);
	    group0 = (RadioGroup) findViewById(R.id.rgroup0);
	    group1 = (RadioGroup) findViewById(R.id.rgroup1);
	    group2 = (RadioGroup) findViewById(R.id.rgroup2);
	    group3 = (RadioGroup) findViewById(R.id.rgroup3);
	    group4 = (RadioGroup) findViewById(R.id.rgroup4);
	    group5 = (RadioGroup) findViewById(R.id.rgroup5);
	    group6 = (RadioGroup) findViewById(R.id.rgroup6);
	    group7 = (RadioGroup) findViewById(R.id.rgroup7);
	    group8 = (RadioGroup) findViewById(R.id.rgroup8);
	    group9 = (RadioGroup) findViewById(R.id.rgroup9);
	    
	    
	    if (sendBtn != null)
	          sendBtn.setOnClickListener(this);
	    
	    group0.setOnCheckedChangeListener(listener0);
	    group1.setOnCheckedChangeListener(listener1);
	    group2.setOnCheckedChangeListener(listener2);
	    group3.setOnCheckedChangeListener(listener3);
	    group4.setOnCheckedChangeListener(listener4);
	    group5.setOnCheckedChangeListener(listener5);
	    group6.setOnCheckedChangeListener(listener6);
	    group7.setOnCheckedChangeListener(listener7);
	    group8.setOnCheckedChangeListener(listener8);
	    group9.setOnCheckedChangeListener(listener9);
	    

	}
	
	private RadioGroup.OnCheckedChangeListener listener0 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radioButton01:
				q0 = "無";
				break;	
			case R.id.radioButton02:
				q0 = "看電視";
				break;
			case R.id.radioButton03:
				q0 = "電玩遊戲";
				break;
			case R.id.radioButton04:
				q0 = "上網";
				break;
			case R.id.radioButton05:
				q0 = "閱讀";
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
			case R.id.radioButton11:
				q1 = "1";
				break;	
			case R.id.radioButton12:
				q1 = "2";
				break;
			case R.id.radioButton13:
				q1 = "3";
				break;
			case R.id.radioButton14:
				q1 = "4";
				break;
			case R.id.radioButton15:
				q1 = "5";
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
			case R.id.radioButton21:
				q2 = "1";
				break;	
			case R.id.radioButton22:
				q2 = "2";
				break;
			case R.id.radioButton23:
				q2 = "3";
				break;
			case R.id.radioButton24:
				q2 = "4";
				break;
			case R.id.radioButton25:
				q2 = "5";
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
			case R.id.radioButton31:
				q3 = "喚醒裝置(鬧鐘)";
				break;	
			case R.id.radioButton32:
				q3 = "自然清醒";
				break;
			case R.id.radioButton33:
				q3 = "ZEO";
				break;
			case R.id.radioButton34:
				q3 = "其他";
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
			case R.id.radioButton41:
				q4 = "1";
				break;	
			case R.id.radioButton42:
				q4 = "2";
				break;
			case R.id.radioButton43:
				q4 = "3";
				break;
			case R.id.radioButton44:
				q4 = "4";
				break;
			case R.id.radioButton45:
				q4 = "5";
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
			case R.id.radioButton51:
				q5 = "1";
				break;	
			case R.id.radioButton52:
				q5 = "2";
				break;
			case R.id.radioButton53:
				q5 = "3";
				break;
			case R.id.radioButton54:
				q5 = "4";
				break;
			case R.id.radioButton55:
				q5 = "5";
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
			case R.id.radioButton61:
				q6 = "游泳";
				break;	
			case R.id.radioButton62:
				q6 = "跑步";
				break;	
			case R.id.radioButton63:
				q6 = "健行";
				break;	
			case R.id.radioButton64:
				q6 = "球類";
				break;	
			case R.id.radioButton65:
				q6 = "無";
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
			case R.id.radioButton71:
				q7 = "安眠藥";
				break;	
			case R.id.radioButton72:
				q7 = "褪黑激素";
				break;
			case R.id.radioButton73:
				q7 = "無";
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
			case R.id.radioButton81:
				q8 = "茶";
				break;	
			case R.id.radioButton82:
				q8 = "咖啡";
				break;
			case R.id.radioButton83:
				q8 = "可樂";
				break;
			case R.id.radioButton84:
				q8 = "無";
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
			case R.id.radioButton91:
				q9 = "啤酒";
				break;	
			case R.id.radioButton92:
				q9 = "紅酒";
				break;
			case R.id.radioButton93:
				q9 = "威士忌";
				break;
			case R.id.radioButton94:
				q9 = "無";
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
	        	 String msg0 = ID;
	        	 String q1_gobedtime = txtMessage1.getEditableText().toString();
		         String q2_howlongasleep = txtMessage2.getEditableText().toString();
		         String q3_awaketimes = txtMessage3.getEditableText().toString();
	             String q4_activitybeforeasleep = q0;
	             String q5_moodbeforeasleep = q1;
	             String q6_sleepquality = q2;
	             String q7_wakeuptime = txtMessage4.getEditableText().toString();
	             String q8_expectedtimetowakeup = txtMessage5.getEditableText().toString();
	             String q9_whatmethodwakeup = q3;
	             String q10_awakemood = q4;
	             String q11_awakespirit = q5;
	             String q12_gobedtimenoon = txtMessage6.getEditableText().toString();
	             String q13_interval = txtMessage7.getEditableText().toString();
	             String q14_exercise = q6;
	             String q15_exercisestarttime = txtMessage8.getEditableText().toString();
	             String q16_exerciseendtime = txtMessage9.getEditableText().toString();
	             String q17_medicine = q7;
	             String q18_medicinetime = txtMessage10.getEditableText().toString();
	             String q19_medicinequantity = txtMessage11.getEditableText().toString();
	             String q20_caffeine = q8;
	             String q21_caffeinequantity = txtMessage12.getEditableText().toString();
	             String q22_alcohol = q9;
	             String q23_alcoholquantity = txtMessage13.getEditableText().toString();
	            
	             // 啟動一個Thread(執行緒)，將要傳送的資料放進Runnable中，讓Thread執行
	             Thread t = new Thread(new sendPostRunnable(msg0,q1_gobedtime,q2_howlongasleep,q3_awaketimes,q4_activitybeforeasleep,q5_moodbeforeasleep,q6_sleepquality,q7_wakeuptime,q8_expectedtimetowakeup,q9_whatmethodwakeup,
	            		 q10_awakemood,q11_awakespirit,q12_gobedtimenoon,q13_interval,q14_exercise,q15_exercisestarttime,q16_exerciseendtime,q17_medicine,q18_medicinetime,q19_medicinequantity,q20_caffeine,q21_caffeinequantity,
	            		 q22_alcohol,q23_alcoholquantity));
	             t.start();
	             
	             Intent intent = new Intent();
	             intent.setClass(QuesGoodSleepLive.this, Menu_v1.class);
	             startActivity(intent);
	      }
	   }

	private String sendPostDataToInternet(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,
  		  String strTxt10,String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15,String strTxt16,String strTxt17,String strTxt18,String strTxt19,
  		  String strTxt20,String strTxt21,String strTxt22,String strTxt23)
	
		{
	      /* 建立HTTP Post連線 */
	      HttpPost httpRequest = new HttpPost(uriAPI);
	      /*
	       * Post運作傳送變數必須用NameValuePair[]陣列儲存
	       */
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair("ID", strTxt0));
	      params.add(new BasicNameValuePair("Q1_gobedtime", strTxt01));
	      params.add(new BasicNameValuePair("Q2_howlongasleep", strTxt02));
	      params.add(new BasicNameValuePair("Q3_awaketimes", strTxt03));
	      params.add(new BasicNameValuePair("Q4_activitybeforeasleep", strTxt04));
	      params.add(new BasicNameValuePair("Q5_moodbeforeasleep", strTxt05));
	      params.add(new BasicNameValuePair("Q6_sleepquality", strTxt06));
	      params.add(new BasicNameValuePair("Q7_wakeuptime", strTxt07));
	      params.add(new BasicNameValuePair("Q8_expectedtimetowakeup", strTxt08));
	      params.add(new BasicNameValuePair("Q9_whatmethodwakeup", strTxt09));
	      params.add(new BasicNameValuePair("Q10_awakemood", strTxt10));
	      params.add(new BasicNameValuePair("Q11_awakespirit", strTxt11));
	      params.add(new BasicNameValuePair("Q12_gobedtimenoon", strTxt12));
	      params.add(new BasicNameValuePair("Q13_interval", strTxt13));
	      params.add(new BasicNameValuePair("Q14_exercise", strTxt14));
	      params.add(new BasicNameValuePair("Q15_exercisestarttime", strTxt15));
	      params.add(new BasicNameValuePair("Q16_exerciseendtime", strTxt16));
	      params.add(new BasicNameValuePair("Q17_medicine", strTxt17));
	      params.add(new BasicNameValuePair("Q18_medicinetime", strTxt18));
	      params.add(new BasicNameValuePair("Q19_medicinequantity", strTxt19));
	      params.add(new BasicNameValuePair("Q20_caffeine", strTxt20));
	      params.add(new BasicNameValuePair("Q21_caffeinequantity", strTxt21));
	      params.add(new BasicNameValuePair("Q22_alcohol", strTxt22));
	      params.add(new BasicNameValuePair("Q23_alcoholquantity", strTxt23));
	      
	 
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
	      String strTxt21 = null;
	      String strTxt22 = null;
	      String strTxt23 = null;
	      
		   
	      	 
	      // 建構子，設定要傳的字串
	      public sendPostRunnable(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,
	    		  String strTxt10,String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15,String strTxt16,String strTxt17,String strTxt18,String strTxt19,
	    		  String strTxt20,String strTxt21,String strTxt22,String strTxt23)
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
	         this.strTxt21 = strTxt21;
	         this.strTxt22 = strTxt22;
	         this.strTxt23 = strTxt23;
	      }
	 
	      @Override
	      public void run()
	      {
	          String result = sendPostDataToInternet(strTxt0,strTxt01, strTxt02, strTxt03, strTxt04, strTxt05, strTxt06, strTxt07, strTxt08, strTxt09,
		    		   strTxt10, strTxt11, strTxt12, strTxt13, strTxt14, strTxt15, strTxt16, strTxt17, strTxt18, strTxt19,
		    		   strTxt20, strTxt21, strTxt22, strTxt23);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	          
	      }
	   }
}
