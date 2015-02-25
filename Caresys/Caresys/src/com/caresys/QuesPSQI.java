package com.caresys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioGroup;

public class QuesPSQI extends Activity implements OnClickListener{
	
	private Button sendBtn;
	private EditText txtMessage1,txtMessage2,txtMessage3,txtMessage4,txtMessage510,txtMessage105;
	private String uriAPI = "http://140.116.39.44/psqiPostTest.php";
	
	private RadioGroup group51,group52,group53,group54,group55,group56,group57,group58,group59,group510;
	private RadioGroup group6,group7,group8,group9,group10;
	private RadioGroup group101,group102,group103,group104,group105;
	private String q6,q7,q8,q9,q10;
	private String q51,q52,q53,q54,q55,q56,q57,q58,q59,q510;
	private String q101,q102,q103,q104,q105;
	
	SharedPreferences sharedPreferences;
	private String ID;

	protected static final int REFRESH_DATA = 0x00000001;
	
	Handler mHandler = new Handler()
	   {
	      @Override
	      public void handleMessage(Message msg1)
	      {
	          switch (msg1.what)
	          {
	          // 顯示網路上抓取的資料
	          case REFRESH_DATA:
	             String result = null;
	             if (msg1.obj instanceof String)
	                result = (String) msg1.obj;
	             
	             if (result != null)
	                // 印出網路回傳的文字
	                Toast.makeText(QuesPSQI.this, result, Toast.LENGTH_LONG).show();
	             break;
	          }
	      }
	      
	   };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quespsqi);
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
		
	    group51 = (RadioGroup) findViewById(R.id.radioGroupquestion51);
	    group52 = (RadioGroup) findViewById(R.id.radioGroupquestion52);
	    group53 = (RadioGroup) findViewById(R.id.radioGroupquestion53);
	    group54 = (RadioGroup) findViewById(R.id.radioGroupquestion54);
	    group55 = (RadioGroup) findViewById(R.id.radioGroupquestion55);
	    group56 = (RadioGroup) findViewById(R.id.radioGroupquestion56);
	    group57 = (RadioGroup) findViewById(R.id.radioGroupquestion57);
	    group58 = (RadioGroup) findViewById(R.id.radioGroupquestion58);
	    group59 = (RadioGroup) findViewById(R.id.radioGroupquestion59);
	    group510 = (RadioGroup) findViewById(R.id.radioGroupquestion510);
	    
	    group6 = (RadioGroup) findViewById(R.id.radioGroupquestion6);
	    group7 = (RadioGroup) findViewById(R.id.radioGroupquestion7);
	    group8 = (RadioGroup) findViewById(R.id.radioGroupquestion8);
	    group9 = (RadioGroup) findViewById(R.id.radioGroupquestion9);
	    group10 = (RadioGroup) findViewById(R.id.radioGroupquestion10);
	    
	    group101 = (RadioGroup) findViewById(R.id.radioGroupquestion101);
	    group102 = (RadioGroup) findViewById(R.id.radioGroupquestion102);
	    group103 = (RadioGroup) findViewById(R.id.radioGroupquestion103);
	    group104 = (RadioGroup) findViewById(R.id.radioGroupquestion104);
	    group105 = (RadioGroup) findViewById(R.id.radioGroupquestion105);
	    
	    group51.setOnCheckedChangeListener(listener51);
	    group52.setOnCheckedChangeListener(listener52);
	    group53.setOnCheckedChangeListener(listener53);
	    group54.setOnCheckedChangeListener(listener54);
	    group55.setOnCheckedChangeListener(listener55);
	    group56.setOnCheckedChangeListener(listener56);
	    group57.setOnCheckedChangeListener(listener57);
	    group58.setOnCheckedChangeListener(listener58);
	    group59.setOnCheckedChangeListener(listener59);
	    group510.setOnCheckedChangeListener(listener510);
	    
	    group6.setOnCheckedChangeListener(listener6);
	    group7.setOnCheckedChangeListener(listener7);
	    group8.setOnCheckedChangeListener(listener8);
	    group9.setOnCheckedChangeListener(listener9);
	    group10.setOnCheckedChangeListener(listener10);
	    
	    group101.setOnCheckedChangeListener(listener101);
	    group102.setOnCheckedChangeListener(listener102);
	    group103.setOnCheckedChangeListener(listener103);
	    group104.setOnCheckedChangeListener(listener104);
	    group105.setOnCheckedChangeListener(listener105);
	    
	    txtMessage1 = (EditText) findViewById(R.id.editTextq1);
	    txtMessage2 = (EditText) findViewById(R.id.editTextq2);
	    txtMessage3 = (EditText) findViewById(R.id.editTextq3);
	    txtMessage4 = (EditText) findViewById(R.id.editTextq4);
	    txtMessage510 = (EditText) findViewById(R.id.editTextquestion510);
	    txtMessage105 = (EditText) findViewById(R.id.editTextquestion105);
	}
	
	private RadioGroup.OnCheckedChangeListener listener51 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio51a:
				q51 = "0";
				break;	
			case R.id.radio51b:
				q51 = "0~1";
				break;
			case R.id.radio51c:
				q51 = "1~2";
				break;
			case R.id.radio51d:
				q51 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener52 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio52a:
				q52 = "0";
				break;	
			case R.id.radio52b:
				q52 = "0~1";
				break;
			case R.id.radio52c:
				q52 = "1~2";
				break;
			case R.id.radio52d:
				q52 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener53 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio53a:
				q53 = "0";
				break;	
			case R.id.radio53b:
				q53 = "0~1";
				break;
			case R.id.radio53c:
				q53 = "1~2";
				break;
			case R.id.radio53d:
				q53 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener54 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio54a:
				q54 = "0";
				break;	
			case R.id.radio54b:
				q54 = "0~1";
				break;
			case R.id.radio54c:
				q54 = "1~2";
				break;
			case R.id.radio54d:
				q54 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener55 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio55a:
				q55 = "0";
				break;	
			case R.id.radio55b:
				q55 = "0~1";
				break;
			case R.id.radio55c:
				q55 = "1~2";
				break;
			case R.id.radio55d:
				q55 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener56 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio56a:
				q56 = "0";
				break;	
			case R.id.radio56b:
				q56 = "0~1";
				break;
			case R.id.radio56c:
				q56 = "1~2";
				break;
			case R.id.radio56d:
				q56 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener57 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio57a:
				q57 = "0";
				break;	
			case R.id.radio57b:
				q57 = "0~1";
				break;
			case R.id.radio57c:
				q57 = "1~2";
				break;
			case R.id.radio57d:
				q57 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener58 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio58a:
				q58 = "0";
				break;	
			case R.id.radio58b:
				q58 = "0~1";
				break;
			case R.id.radio58c:
				q58 = "1~2";
				break;
			case R.id.radio58d:
				q58 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener59 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio59a:
				q59 = "0";
				break;	
			case R.id.radio59b:
				q59 = "0~1";
				break;
			case R.id.radio59c:
				q59 = "1~2";
				break;
			case R.id.radio59d:
				q59 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener510 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio510a:
				q510 = "0";
				break;	
			case R.id.radio510b:
				q510 = "0~1";
				break;
			case R.id.radio510c:
				q510 = "1~2";
				break;
			case R.id.radio510d:
				q510 = "above3~4";
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
			case R.id.radioquestion6a:
				q6 = "很好";
				break;	
			case R.id.radioquestion6b:
				q6 = "還不錯";
				break;
			case R.id.radioquestion6c:
				q6 = "差了一點";
				break;
			case R.id.radioquestion6d:
				q6 = "很差";
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
			case R.id.radioquestion7a:
				q7 = "0";
				break;	
			case R.id.radioquestion7b:
				q7 = "0~1";
				break;
			case R.id.radioquestion7c:
				q7 = "1~2";
				break;
			case R.id.radioquestion7d:
				q7 = "above3~4";
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
			case R.id.radioquestion8a:
				q8 = "0";
				break;	
			case R.id.radioquestion8b:
				q8 = "0~1";
				break;
			case R.id.radioquestion8c:
				q8 = "1~2";
				break;
			case R.id.radioquestion8d:
				q8 = "above3~4";
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
			case R.id.radioquestion9a:
				q9 = "沒有";
				break;	
			case R.id.radioquestion9b:
				q9 = "有一點";
				break;
			case R.id.radioquestion9c:
				q9 = "的確有";
				break;
			case R.id.radioquestion9d:
				q9 = "很嚴重";
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
			case R.id.radioquestion10a:
				q10 = "沒有睡伴或室友";
				break;	
			case R.id.radioquestion10b:
				q10 = "睡伴或室友不同臥房";
				break;
			case R.id.radioquestion10c:
				q10 = "睡伴同室友不同床";
				break;
			case R.id.radioquestion10d:
				q10 = "睡伴或室友同床";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener101 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio101a:
				q101 = "0";
				break;	
			case R.id.radio101b:
				q101 = "0~1";
				break;
			case R.id.radio101c:
				q101 = "1~2";
				break;
			case R.id.radio101d:
				q101 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener102 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio102a:
				q102 = "0";
				break;	
			case R.id.radio102b:
				q102 = "0~1";
				break;
			case R.id.radio102c:
				q102 = "1~2";
				break;
			case R.id.radio102d:
				q102 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener103 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio103a:
				q103 = "0";
				break;	
			case R.id.radio103b:
				q103 = "0~1";
				break;
			case R.id.radio103c:
				q103 = "1~2";
				break;
			case R.id.radio103d:
				q103 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener104 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio104a:
				q104 = "0";
				break;	
			case R.id.radio104b:
				q104 = "0~1";
				break;
			case R.id.radio104c:
				q104 = "1~2";
				break;
			case R.id.radio104d:
				q104 = "above3~4";
				break;
			default :
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener105 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio105a:
				q105 = "0";
				break;	
			case R.id.radio105b:
				q105 = "0~1";
				break;
			case R.id.radio105c:
				q105 = "1~2";
				break;
			case R.id.radio105d:
				q105 = "above3~4";
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
				 String msg01 = txtMessage1.getEditableText().toString();
	             String msg02 = txtMessage2.getEditableText().toString();
	             String msg03 = txtMessage3.getEditableText().toString();
	             String msg04 = txtMessage4.getEditableText().toString();
	             String msg510 = txtMessage510.getEditableText().toString();
	             String msg0105 = txtMessage105.getEditableText().toString();
	             
	             String msg051 = q51;
	             String msg052 = q52;
	             String msg053 = q53;
	             String msg054 = q54;
	             String msg055 = q55;
	             String msg056 = q56;
	             String msg057 = q57;
	             String msg058 = q58;
	             String msg059 = q59;
	             String msg0510 = q510;
	             
	             String msg06 = q6;
	             String msg07 = q7;
	             String msg08 = q8;
	             String msg09 = q9;
	             String msg10 = q10;
	             
	             String msg101 = q101;
	             String msg102 = q102;
	             String msg103 = q103;
	             String msg104 = q104;
	             String msg105 = q105;
	             
	             Thread t = new Thread(new sendPostRunnable(msg0,msg01,msg02,msg03,msg04,msg051,msg052,msg053,msg054,msg055,msg056,msg057,msg058,msg059,msg510,msg0510,msg06,msg07,msg08,msg09,msg10,msg101,msg102,msg103,msg104,msg0105,msg105));
	             t.start();
	             
	             Intent intent = new Intent();
	             intent.setClass(QuesPSQI.this, Menu_v1.class);
	             startActivity(intent);
			}
	   }
	
	private String sendPostDataToInternet(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt051,String strTxt052,String strTxt053,String strTxt054,String strTxt055,String strTxt056,String strTxt057,String strTxt058,String strTxt059,String strTxt510,String strTxt0510,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt010,String strTxt101,String strTxt102,String strTxt103,String strTxt104,String strTxt0105,String strTxt105)
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
	      params.add(new BasicNameValuePair("Q5-1", strTxt051));
	      params.add(new BasicNameValuePair("Q5-2", strTxt052));
	      params.add(new BasicNameValuePair("Q5-3", strTxt053));
	      params.add(new BasicNameValuePair("Q5-4", strTxt054));
	      params.add(new BasicNameValuePair("Q5-5", strTxt055));
	      params.add(new BasicNameValuePair("Q5-6", strTxt056));
	      params.add(new BasicNameValuePair("Q5-7", strTxt057));
	      params.add(new BasicNameValuePair("Q5-8", strTxt058));
	      params.add(new BasicNameValuePair("Q5-9", strTxt059));
	      params.add(new BasicNameValuePair("Q5-10condition", strTxt510));
	      params.add(new BasicNameValuePair("Q5-10", strTxt0510));
	      params.add(new BasicNameValuePair("Q6", strTxt06));
	      params.add(new BasicNameValuePair("Q7", strTxt07));
	      params.add(new BasicNameValuePair("Q8", strTxt08));
	      params.add(new BasicNameValuePair("Q9", strTxt09));
	      params.add(new BasicNameValuePair("Q10", strTxt010));
	      params.add(new BasicNameValuePair("Q10-1", strTxt101));
	      params.add(new BasicNameValuePair("Q10-2", strTxt102));
	      params.add(new BasicNameValuePair("Q10-3", strTxt103));
	      params.add(new BasicNameValuePair("Q10-4", strTxt104));
	      params.add(new BasicNameValuePair("Q10-5condition", strTxt0105));
	      params.add(new BasicNameValuePair("Q10-5", strTxt105));
	      
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
	      String strTxt051 = null;
	      String strTxt052 = null;
	      String strTxt053 = null;
	      String strTxt054 = null;
	      String strTxt055 = null;
	      String strTxt056 = null;
	      String strTxt057 = null;
	      String strTxt058 = null;
	      String strTxt059 = null;
	      String strTxt510 = null;
	      String strTxt0510 = null;
	      String strTxt06 = null;
	      String strTxt07 = null;
	      String strTxt08 = null;
	      String strTxt09 = null;
	      String strTxt10 = null;
	      String strTxt101 = null;
	      String strTxt102 = null;
	      String strTxt103 = null;
	      String strTxt104 = null;
	      String strTxt0105 = null;
	      String strTxt105 = null;
	      
	      
	      public sendPostRunnable(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt051,String strTxt052,String strTxt053,String strTxt054,String strTxt055,String strTxt056,String strTxt057,String strTxt058,String strTxt059,String strTxt510,String strTxt0510,String strTxt06,String strTxt07,String strTxt08,String strTxt09,String strTxt010,String strTxt101,String strTxt102,String strTxt103,String strTxt104,String strTxt0105,String strTxt105)
	      {
	    	  this.strTxt0 = strTxt0;
	    	  	 this.strTxt01 = strTxt01;
		         this.strTxt02 = strTxt02;
		         this.strTxt03 = strTxt03;
		         this.strTxt04 = strTxt04;
		         this.strTxt051 = strTxt051;
		         this.strTxt052 = strTxt052;
		         this.strTxt053 = strTxt053;
		         this.strTxt054 = strTxt054;
		         this.strTxt055 = strTxt055;
		         this.strTxt056 = strTxt056;
		         this.strTxt057 = strTxt057;
		         this.strTxt058 = strTxt058;
		         this.strTxt059 = strTxt059;
		         this.strTxt510 = strTxt510;
		         this.strTxt0510 = strTxt0510;
			     this.strTxt06 = strTxt06;
		         this.strTxt07 = strTxt07;
		         this.strTxt08 = strTxt08;
		         this.strTxt09 = strTxt09;
		         this.strTxt10 = strTxt010;
		         this.strTxt101 = strTxt101;
		         this.strTxt102 = strTxt102;
		         this.strTxt103 = strTxt103;
		         this.strTxt104 = strTxt104;
		         this.strTxt0105 = strTxt0105;
		         this.strTxt105 = strTxt105;
	      }
	      
	      @Override
	      public void run()
	      {
	    	  String result = sendPostDataToInternet(strTxt0,strTxt01,strTxt02,strTxt03,strTxt04,strTxt051,strTxt052,strTxt053,strTxt054,strTxt055,strTxt056,strTxt057,strTxt058,strTxt059,strTxt510,strTxt0510,strTxt06,strTxt07,strTxt08,strTxt09,strTxt10,strTxt101,strTxt102,strTxt103,strTxt104,strTxt0105,strTxt105);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	      }
	   }
}
