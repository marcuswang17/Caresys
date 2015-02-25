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

public class QuesLCQ extends Activity implements OnClickListener{

	private Button sendBtn;
	private String uriAPI = "http://140.116.39.44/lcqPostTest.php";
	private RadioGroup group1,group2,group3,group4,group5,group6,group7,group8,group9,group10,group11,group12,group13,group14,group15,group16,group17,group18,group19;
	private String q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14,q15,q16,q17,q18,q19;
	
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
					Toast.makeText(QuesLCQ.this, result, Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_queslcq);
		
		sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
		ID = sharedPreferences.getString("id", "0000000000");
		

	    sendBtn = (Button) findViewById(R.id.sendBtn);
	    
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
	    group11 = (RadioGroup) findViewById(R.id.radioGroup11);
	    group12 = (RadioGroup) findViewById(R.id.radioGroup12);
	    group13 = (RadioGroup) findViewById(R.id.radioGroup13);
	    group14 = (RadioGroup) findViewById(R.id.radioGroup14);
	    group15 = (RadioGroup) findViewById(R.id.radioGroup15);
	    group16 = (RadioGroup) findViewById(R.id.radioGroup16);
	    group17 = (RadioGroup) findViewById(R.id.radioGroup17);
	    group18 = (RadioGroup) findViewById(R.id.radioGroup18);
	    group19 = (RadioGroup) findViewById(R.id.radioGroup19);
	    
	    
	    
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
	    group11.setOnCheckedChangeListener(listener11);
	    group12.setOnCheckedChangeListener(listener12);
	    group13.setOnCheckedChangeListener(listener13);
	    group14.setOnCheckedChangeListener(listener14);
	    group15.setOnCheckedChangeListener(listener15);
	    group16.setOnCheckedChangeListener(listener16);
	    group17.setOnCheckedChangeListener(listener17);
	    group18.setOnCheckedChangeListener(listener18);
	    group19.setOnCheckedChangeListener(listener19);
	    

	}
	
	
private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio11:
				q1 = "1";
				break;	
			case R.id.radio12:
				q1 = "2";
				break;
			case R.id.radio13:
				q1 = "3";
				break;
			case R.id.radio14:
				q1 = "4";
				break;
			case R.id.radio15:
				q1 = "5";
				break;
			case R.id.radio16:
				q1 = "6";
				break;
			case R.id.radio17:
				q1 = "7";
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
			case R.id.radio21:
				q2 = "1";
				break;	
			case R.id.radio22:
				q2 = "2";
				break;
			case R.id.radio23:
				q2 = "3";
				break;
			case R.id.radio24:
				q2 = "4";
				break;
			case R.id.radio25:
				q2 = "5";
				break;
			case R.id.radio26:
				q2 = "6";
				break;
			case R.id.radio27:
				q2 = "7";
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
			case R.id.radio31:
				q3 = "1";
				break;	
			case R.id.radio32:
				q3 = "2";
				break;
			case R.id.radio33:
				q3 = "3";
				break;
			case R.id.radio34:
				q3 = "4";
				break;
			case R.id.radio35:
				q3 = "5";
				break;
			case R.id.radio36:
				q3 = "6";
				break;
			case R.id.radio37:
				q3 = "7";
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
			case R.id.radio41:
				q4 = "1";
				break;	
			case R.id.radio42:
				q4 = "2";
				break;
			case R.id.radio43:
				q4 = "3";
				break;
			case R.id.radio44:
				q4 = "4";
				break;
			case R.id.radio45:
				q4 = "5";
				break;
			case R.id.radio46:
				q4 = "6";
				break;
			case R.id.radio47:
				q4 = "7";
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
			case R.id.radio51:
				q5 = "1";
				break;
			case R.id.radio52:
				q5 = "2";
				break;
			case R.id.radio53:
				q5 = "3";
				break;
			case R.id.radio54:
				q5 = "4";
				break;
			case R.id.radio55:
				q5 = "5";
				break;
			case R.id.radio56:
				q5 = "6";
				break;
			case R.id.radio57:
				q5 = "7";
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
			case R.id.radio61:
				q6 = "1";
				break;	
			case R.id.radio62:
				q6 = "2";
				break;
			case R.id.radio63:
				q6 = "3";
				break;
			case R.id.radio64:
				q6 = "4";
				break;
			case R.id.radio65:
				q6 = "5";
				break;
			case R.id.radio66:
				q6 = "6";
				break;
			case R.id.radio67:
				q6 = "7";
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
			case R.id.radio71:
				q7 = "1";
				break;	
			case R.id.radio72:
				q7 = "2";
				break;
			case R.id.radio73:
				q7 = "3";
				break;
			case R.id.radio74:
				q7 = "4";
				break;
			case R.id.radio75:
				q7 = "5";
				break;
			case R.id.radio76:
				q7 = "6";
				break;
			case R.id.radio77:
				q7 = "7";
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
			case R.id.radio81:
				q8 = "1";
				break;	
			case R.id.radio82:
				q8 = "2";
				break;
			case R.id.radio83:
				q8 = "3";
				break;
			case R.id.radio84:
				q8 = "4";
				break;
			case R.id.radio85:
				q8 = "5";
				break;
			case R.id.radio86:
				q8 = "6";
				break;
			case R.id.radio87:
				q8 = "7";
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
			case R.id.radio91:
				q9 = "1";
				break;	
			case R.id.radio92:
				q9 = "2";
				break;
			case R.id.radio93:
				q9 = "3";
				break;
			case R.id.radio94:
				q9 = "4";
				break;
			case R.id.radio95:
				q9 = "5";
				break;
			case R.id.radio96:
				q9 = "6";
				break;
			case R.id.radio97:
				q9 = "7";
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
			case R.id.radio101:
				q10 = "1";
				break;	
			case R.id.radio102:
				q10 = "2";
				break;
			case R.id.radio103:
				q10 = "3";
				break;
			case R.id.radio104:
				q10 = "4";
				break;
			case R.id.radio105:
				q10 = "5";
				break;
			case R.id.radio106:
				q10 = "6";
				break;
			case R.id.radio107:
				q10 = "7";
				break;
			
			default :
				
				break;
			
			}
		}
	};

	
	
private RadioGroup.OnCheckedChangeListener listener11 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio111:
				q11 = "1";
				break;	
			case R.id.radio112:
				q11 = "2";
				break;
			case R.id.radio113:
				q11 = "3";
				break;
			case R.id.radio114:
				q11 = "4";
				break;
			case R.id.radio115:
				q11 = "5";
				break;
			case R.id.radio116:
				q11 = "6";
				break;
			case R.id.radio117:
				q11 = "7";
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
			case R.id.radio121:
				q12 = "1";
				break;	
			case R.id.radio122:
				q12 = "2";
				break;
			case R.id.radio123:
				q12 = "3";
				break;
			case R.id.radio124:
				q12 = "4";
				break;
			case R.id.radio125:
				q12 = "5";
				break;
			case R.id.radio126:
				q12 = "6";
				break;
			case R.id.radio127:
				q12 = "7";
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
			case R.id.radio131:
				q13 = "1";
				break;	
			case R.id.radio132:
				q13 = "2";
				break;
			case R.id.radio133:
				q13 = "3";
				break;
			case R.id.radio134:
				q13 = "4";
				break;
			case R.id.radio135:
				q13 = "5";
				break;
			case R.id.radio136:
				q13 = "6";
				break;
			case R.id.radio137:
				q13 = "7";
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
			case R.id.radio141:
				q14 = "1";
				break;	
			case R.id.radio142:
				q14 = "2";
				break;
			case R.id.radio143:
				q14 = "3";
				break;
			case R.id.radio144:
				q14 = "4";
				break;
			case R.id.radio145:
				q14 = "5";
				break;
			case R.id.radio146:
				q14 = "6";
				break;
			case R.id.radio147:
				q14 = "7";
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
			case R.id.radio151:
				q15 = "1";
				break;	
			case R.id.radio152:
				q15 = "2";
				break;
			case R.id.radio153:
				q15 = "3";
				break;
			case R.id.radio154:
				q15 = "4";
				break;
			case R.id.radio155:
				q15 = "5";
				break;
			case R.id.radio156:
				q15 = "6";
				break;
			case R.id.radio157:
				q15 = "7";
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
			case R.id.radio161:
				q16 = "1";
				break;	
			case R.id.radio162:
				q16 = "2";
				break;
			case R.id.radio163:
				q16 = "3";
				break;
			case R.id.radio164:
				q16 = "4";
				break;
			case R.id.radio165:
				q16 = "5";
				break;
			case R.id.radio166:
				q16 = "6";
				break;
			case R.id.radio167:
				q16 = "7";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener17 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio171:
				q17 = "1";
				break;	
			case R.id.radio172:
				q17 = "2";
				break;
			case R.id.radio173:
				q17 = "3";
				break;
			case R.id.radio174:
				q17 = "4";
				break;
			case R.id.radio175:
				q17 = "5";
				break;
			case R.id.radio176:
				q17 = "6";
				break;
			case R.id.radio177:
				q17 = "7";
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
			case R.id.radio181:
				q18 = "1";
				break;	
			case R.id.radio182:
				q18 = "2";
				break;
			case R.id.radio183:
				q18 = "3";
				break;
			case R.id.radio184:
				q18 = "4";
				break;
			case R.id.radio185:
				q18 = "5";
				break;
			case R.id.radio186:
				q18 = "6";
				break;
			case R.id.radio187:
				q18 = "7";
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
			case R.id.radio191:
				q19 = "1";
				break;	
			case R.id.radio192:
				q19 = "2";
				break;
			case R.id.radio193:
				q19 = "3";
				break;
			case R.id.radio194:
				q19 = "4";
				break;
			case R.id.radio195:
				q19 = "5";
				break;
			case R.id.radio196:
				q19 = "6";
				break;
			case R.id.radio197:
				q19 = "7";
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
	             String msg11 = q11;
	             String msg12 = q12;
	             String msg13 = q13;
	             String msg14 = q14;
	             String msg15 = q15;
	             String msg16 = q16;
	             String msg17 = q17;
	             String msg18 = q18;
	             String msg19 = q19;
	             
	             // 啟動一個Thread(執行緒)，將要傳送的資料放進Runnable中，讓Thread執行
	             Thread t = new Thread(new sendPostRunnable(msg0,msg01,msg02,msg03,msg04,msg05,msg06,msg07,msg08,msg09,msg10,msg11,msg12,msg13,msg14,msg15,msg16,msg17,msg18,msg19));
	             t.start();
	             
	             Intent intent = new Intent();
	             intent.setClass(QuesLCQ.this, Menu_v1.class);
	             startActivity(intent);
	      }
	   }

	private String sendPostDataToInternet(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt10,
  		  String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15,String strTxt16,String strTxt17,String strTxt18,String strTxt19)
	
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
	      params.add(new BasicNameValuePair("Q11", strTxt11));
	      params.add(new BasicNameValuePair("Q12", strTxt12));
	      params.add(new BasicNameValuePair("Q13", strTxt13));
	      params.add(new BasicNameValuePair("Q14", strTxt14));
	      params.add(new BasicNameValuePair("Q15", strTxt15));
	      params.add(new BasicNameValuePair("Q16", strTxt16));
	      params.add(new BasicNameValuePair("Q17", strTxt17));
	      params.add(new BasicNameValuePair("Q18", strTxt18));
	      params.add(new BasicNameValuePair("Q19", strTxt19));
	     
	      
	 
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
	  
	      
		   
	      	 
	      // 建構子，設定要傳的字串
	      public sendPostRunnable(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt10,
	    		  String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15,String strTxt16,String strTxt17,String strTxt18,String strTxt19)
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
	         
	      }
	 
	      @Override
	      public void run()
	      {
	          String result = sendPostDataToInternet(strTxt0,strTxt01,strTxt02,strTxt03,strTxt04,strTxt05,strTxt06,strTxt07,strTxt08,strTxt09,strTxt10, strTxt11, strTxt12, strTxt13, strTxt14, strTxt15, 
	        		  strTxt16, strTxt17, strTxt18, strTxt19);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	          
	      }
	   }
}
