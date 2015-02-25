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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuesCAT extends Activity implements OnClickListener{

	private Button sendBtn;
	private String uriAPI = "http://140.116.39.44/CopdCatPost.php";
	private RadioGroup group1,group2,group3,group4,group5,group6,group7,group8,group9,group10;
	private String q2,q3,q4,q5,q6,q7,q8,q9,q10,q11;
	private EditText editText1,editText6,editText7,editText8;
	private String s141,s142,s143,s144,s145;
	private String s151,s152,s153,s154,s155,s156,s157,s158;
	private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7;
	private CheckBox checkBox8,checkBox9,checkBox10,checkBox11,checkBox12,checkBox13;
	private int p1,p2,p3,p4,p5,p6,p7,p8,ptatal;
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
					Toast.makeText(QuesCAT.this, result, Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {//16
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ques_cat);
		
		sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
		ID = sharedPreferences.getString("id", "0000000000");
		

	    sendBtn = (Button) findViewById(R.id.sendBtn);
	    editText1 =(EditText)findViewById(R.id.editText1);
	    editText6 =(EditText)findViewById(R.id.editText6);
	    editText7 =(EditText)findViewById(R.id.editText7);
	    editText8 =(EditText)findViewById(R.id.editText8);
	    group1 = (RadioGroup) findViewById(R.id.radioGroup2);
	    group2 = (RadioGroup) findViewById(R.id.radioGroup3);
	    group3 = (RadioGroup) findViewById(R.id.radioGroup4);
	    group4 = (RadioGroup) findViewById(R.id.radioGroup5);
	    group5 = (RadioGroup) findViewById(R.id.radioGroup6);
	    group6 = (RadioGroup) findViewById(R.id.radioGroup7);
	    group7 = (RadioGroup) findViewById(R.id.radioGroup8);
	    group8 = (RadioGroup) findViewById(R.id.radioGroup9);
	    group9 = (RadioGroup) findViewById(R.id.radioGroup10);
	    group10 = (RadioGroup) findViewById(R.id.radioGroup11);
	   
	    checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
	    checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
	    checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
	    checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
	    checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
	    checkBox6 = (CheckBox)findViewById(R.id.checkBox6);
	    checkBox7 = (CheckBox)findViewById(R.id.checkBox7);
	    checkBox8 = (CheckBox)findViewById(R.id.checkBox8);
	    checkBox9 = (CheckBox)findViewById(R.id.checkBox9);
	    checkBox10 = (CheckBox)findViewById(R.id.checkBox10);
	    checkBox11 = (CheckBox)findViewById(R.id.checkBox11);
	    checkBox12 = (CheckBox)findViewById(R.id.checkBox12);
	    checkBox13 = (CheckBox)findViewById(R.id.checkBox13);
	    
	    
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
	  
	    checkBox1.setOnCheckedChangeListener(chklistener);
	    checkBox2.setOnCheckedChangeListener(chklistener);
	    checkBox3.setOnCheckedChangeListener(chklistener);
	    checkBox4.setOnCheckedChangeListener(chklistener);
	    checkBox5.setOnCheckedChangeListener(chklistener);
	    checkBox6.setOnCheckedChangeListener(chklistener);
	    checkBox7.setOnCheckedChangeListener(chklistener);
	    checkBox8.setOnCheckedChangeListener(chklistener);
	    checkBox9.setOnCheckedChangeListener(chklistener);
	    checkBox10.setOnCheckedChangeListener(chklistener);
	    checkBox11.setOnCheckedChangeListener(chklistener);
	    checkBox12.setOnCheckedChangeListener(chklistener);
	    checkBox13.setOnCheckedChangeListener(chklistener);
	  
	    
	    

	}
	
	

	
private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio21:
				q2 = "0";
				p1=0;
				break;	
			case R.id.radio22:
				q2 = "1";
				p1=1;
				break;
			case R.id.radio23:
				q2 = "2";
				p1=2;
				break;
			case R.id.radio24:
				q2 = "3";
				p1=3;
				break;
			case R.id.radio25:
				q2 = "4";
				p1=4;
				break;
			case R.id.radio26:
				q2 = "2";
				p1=5;
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
			case R.id.radio31:
				q3 = "0";
				p2=0;
				break;	
			case R.id.radio32:
				q3 = "1";
				p2=1;
				break;
			case R.id.radio33:
				q3 = "2";
				p2=2;
				break;
			case R.id.radio34:
				q3 = "3";
				p2=3;
				break;
			case R.id.radio35:
				q3 = "4";
				p2=4;
				break;
			case R.id.radio36:
				q3 = "5";
				p2=5;
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
			case R.id.radio41:
				q4 = "0";
				p3=0;
				break;	
			case R.id.radio42:
				q4 = "1";
				p3=1;
				break;
			case R.id.radio43:
				q4 = "2";
				p3=2;
				break;
			case R.id.radio44:
				q4 = "3";
				p3=3;
				break;
			case R.id.radio45:
				q4 = "4";
				p3=4;
				break;
			case R.id.radio46:
				q4 = "5";
				p3=5;
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
			case R.id.radio51:
				q5 = "0";
				p4=0;
				break;
			case R.id.radio52:
				q5 = "1";
				p4=1;
				break;
			case R.id.radio53:
				q5 = "2";
				p4=2;
				break;
			case R.id.radio54:
				q5 = "3";
				p4=3;
				break;
			case R.id.radio55:
				q5 = "4";
				p4=4;
				break;
			case R.id.radio56:
				q5 = "5";
				p4=5;
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
			case R.id.radio61:
				q6 = "0";
				p5=0;
				break;	
			case R.id.radio62:
				q6 = "1";
				p5=1;
				break;
			case R.id.radio63:
				q6 = "2";
				p5=2;
				break;
			case R.id.radio64:
				q6 = "3";
				p5=3;
				break;
			case R.id.radio65:
				q6 = "4";
				p5=4;
				break;
			case R.id.radio66:
				q6 = "5";
				p5=5;
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
			case R.id.radio71:
				q7 = "0";
				p6=0;
				break;	
			case R.id.radio72:
				q7 = "1";
				p6=1;
				break;
			case R.id.radio73:
				q7 = "2";
				p6=2;
				break;
			case R.id.radio74:
				q7 = "3";
				p6=3;
				break;
			case R.id.radio75:
				q7 = "4";
				p6=4;
				break;
			case R.id.radio76:
				q7 = "5";
				p6=5;
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
			case R.id.radio81:
				q8 = "0";
				p7=0;
				break;	
			case R.id.radio82:
				q8 = "1";
				p7=1;
				break;
			case R.id.radio83:
				q8 = "2";
				p7=2;
				break;
			case R.id.radio84:
				q8 = "3";
				p7=3;
				break;
			case R.id.radio85:
				q8 = "4";
				p7=4;
				break;
			case R.id.radio86:
				q8 = "5";
				p7=5;
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
			case R.id.radio91:
				q9 = "0";
				p8=0;
				break;	
			case R.id.radio92:
				q9 = "1";
				p8=1;
				break;
			case R.id.radio93:
				q9 = "2";
				p8=2;
				break;
			case R.id.radio94:
				q9 = "3";
				p8=3;
				break;
			case R.id.radio95:
				q9 = "4";
				p8=4;
				break;
			case R.id.radio96:
				q9 = "5";
				p8=5;
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
			case R.id.radio101:
				q10 = "執行效果佳";
				break;	
			case R.id.radio102:
				q10 = "須再加強";
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
			case R.id.radio111:
				q11 = "佳";
				break;	
			case R.id.radio112:
				q11 = "須再加強";
				break;
			case R.id.radio113:
				q11 =  editText1.getEditableText().toString();
				break;
			
			default :
				
				break;
			}
		}
	};
	
	
	private CheckBox.OnCheckedChangeListener chklistener = new CheckBox.OnCheckedChangeListener(){
			 
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
			boolean isChecked) {
			// TODO Auto-generated method stub
			 
			if ( checkBox1.isChecked())
			{s141 = "F17007A BD, ";}
			else
			{s141 = "";}
			
			if ( checkBox2.isChecked())
			{s142 = "F17016B 6MIN, ";}
			else
			{s142 = "";}
			
			if ( checkBox3.isChecked())
			{s143 = "F57017B 經脈搏氧飽和濃度監測/次, ";}
			else
			{s143 = "";}
			
			if ( checkBox4.isChecked())
			{s144 = "F17001B PEFR, ";}
			else
			{s144 = "";}
			
			if ( checkBox5.isChecked())
			{s145 = "F57010B 呼吸運動, ";}
			else
			{s145 = "";}
			
			if ( checkBox6.isChecked())
			{s151 = "李俊年, ";}
			else
			{s151 = "";}
			
			if ( checkBox7.isChecked())
			{s152 = "張志誠, ";}
			else
			{s152 = "";}
			
			if ( checkBox8.isChecked())
			{s153 = "陳資濤, ";}
			else
			{s153 = "";}
			
			if ( checkBox9.isChecked())
			{s154 = "林啟嵐, ";}
			else
			{s154 = "";}
			
			if ( checkBox10.isChecked())
			{s155 = "劉文德, ";}
			else
			{s155 = "";}
			
			if ( checkBox11.isChecked())
			{s156 = "陳豪成, ";}
			else
			{s156 = "";}
			
			if ( checkBox12.isChecked())
			{s157 = "馮博皓, ";}
			else
			{s157 = "";}
		
			if ( checkBox13.isChecked())
			{s158 = "李岡遠, ";}
			else
			{s158 = "";}
			}
			};
				
			

			
	
	
	
	
	@Override
	   public void onClick(View v)
	   {
	      if (v == sendBtn)
	      {
	    	  	ptatal=p1+p2+p3+p4+p5+p6+p7+p8;
	             // 擷取文字框上的文字
	        	 String msg0 = ID;
	             String msg01 = q2;
	             String msg02 = q3;
	             String msg03 = q4;
	             String msg04 = q5;
	             String msg05 = q6;
	             String msg06 = q7;
	             String msg07 = q8;
	             String msg08 = q9;
	             String msgpoint =String.valueOf(ptatal);
	             String msg09 = q10;
	             String msg10 = q11;
	             String msg11 = editText6.getEditableText().toString();
	             String msg12 = editText7.getEditableText().toString();
	             String msg13 = editText8.getEditableText().toString();
	             String msg14 = s141+s142+s143+s144+s145;
	             String msg15 = s151+s152+s153+s154+s155+s156+s157+s158;
	             
	            
	             
	             // 啟動一個Thread(執行緒)，將要傳送的資料放進Runnable中，讓Thread執行
	             Thread t = new Thread(new sendPostRunnable(msg0,msg01,msg02,msg03,msg04,msg05,msg06,msg07,msg08,msgpoint,msg09,msg10,msg11,msg12,msg13,msg14,msg15));
	             t.start();
	             
	             Intent intent = new Intent();
	             intent.setClass(QuesCAT.this, Menu_v1.class);
	             startActivity(intent);
	      }
	   }

	private String sendPostDataToInternet(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,
			String strTxtpoint,String strTxt09,String strTxt10,  String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15)
	
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
	      params.add(new BasicNameValuePair("Qpoint", strTxtpoint));
	      params.add(new BasicNameValuePair("Q9", strTxt09));
	      params.add(new BasicNameValuePair("Q10", strTxt10));
	      params.add(new BasicNameValuePair("Q11", strTxt11));
	      params.add(new BasicNameValuePair("Q12", strTxt12));
	      params.add(new BasicNameValuePair("Q13", strTxt13));
	      params.add(new BasicNameValuePair("Q14", strTxt14));
	      params.add(new BasicNameValuePair("Q15", strTxt15));
	
	     
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
	      String strTxtpoint= null;
	      String strTxt09 = null;
	      String strTxt10 = null;
	      String strTxt11 = null;
	      String strTxt12 = null;
	      String strTxt13 = null;
	      String strTxt14 = null;
	      String strTxt15 = null;
	
	  
		   
	      	 
	      // 建構子，設定要傳的字串
	      public sendPostRunnable(String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt08,
	    		  String strTxtpoint, String strTxt09,String strTxt10, String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15)
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
	         this.strTxtpoint = strTxtpoint;
	         this.strTxt09 = strTxt09;
	         this.strTxt10 = strTxt10;
	         this.strTxt11 = strTxt11;
	         this.strTxt12 = strTxt12;
	         this.strTxt13 = strTxt13;
	         this.strTxt14 = strTxt14;
	         this.strTxt15 = strTxt15;
	  
	         
	      }
	 
	      @Override
	      public void run()
	      {
	          String result = sendPostDataToInternet(strTxt0,strTxt01,strTxt02,strTxt03,strTxt04,strTxt05,strTxt06,strTxt07,strTxt08,strTxtpoint,strTxt09,strTxt10, strTxt11, strTxt12, strTxt13, strTxt14, strTxt15 	);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	          
	      }
	   }
}
