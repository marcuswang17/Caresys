package com.caresys;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Setting_v1 extends Activity {
	
	private ToggleButton btn;
	SharedPreferences uploadsportfile,uploadground;
	private String filename,filedate;
	private String uploadgroundfilepath,uploadgroundfilename,uploadgroundfiledate;
	
	protected static final int REFRESH_DATA = 0x00000001;
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
	                Toast.makeText(Setting_v1.this, result, Toast.LENGTH_LONG).show();
	             break;
	          }
	      }
	      
	   };
	
    int serverResponseCode = 0;
    private LocationManager mLocationManager;    
    String upLoadServerUri,upLoaddataUri = null;
	boolean toggle,btn_state;
	private static Context context;
	URL url;
	String st;
	EditText et_number;
	Button btn_sporttime;
	Button btn_sleeptime;
	Button btn_wakeuptime;
	Button btn_groundon,btn_groundoff;
	static final int SPORT_TIME_ID = 0;
	static final int SLEEP_TIME_ID = 1;
	static final int WAKEUP_TIME_ID = 2;
	final Calendar c = Calendar.getInstance();
    int mHour_sport = c.get(Calendar.HOUR_OF_DAY);
    int mMinute_sport = c.get(Calendar.MINUTE);
    int mHour_sleep = c.get(Calendar.HOUR_OF_DAY);
    int mMinute_sleep = c.get(Calendar.MINUTE);
    int mHour_wakeup = c.get(Calendar.HOUR_OF_DAY);
    int mMinute_wakeup = c.get(Calendar.MINUTE);
    long firstime_sport;
    long firstime_sport2;
    Calendar d = Calendar.getInstance();
    Calendar d_sport = Calendar.getInstance();
    Calendar initial_sport = Calendar.getInstance();
    int spinner_option;
    int sport_time_long;
    private String[] sport_time ={"30分鐘","35分鐘","40分鐘","45分鐘","50分鐘","55分鐘","60分鐘","2min"}; 
    
  	//String字串
  	private String ID;
	SharedPreferences sharedPreferences;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		/************* Php script path ****************/
        upLoaddataUri = "http://140.116.39.44/dataPostTest.php";
        upLoadServerUri = "http://140.116.39.44/filesendTest.php";
        
		setContentView(R.layout.activity_setting_v1);
		sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
		
		Setting_v1.context = getApplicationContext();
		et_number = (EditText) findViewById(R.id.et_number1);
		btn_sporttime = (Button) findViewById(R.id.btn_sporttime1);
		btn_sleeptime = (Button) findViewById(R.id.btn_sleeptime1);
		btn_wakeuptime = (Button) findViewById(R.id.btn_wakeuptime1);
		
		btn = (ToggleButton) findViewById(R.id.toggleButton);
		btn.setOnClickListener(btnClickListener);
		toggle =  sharedPreferences.getBoolean("check",btn_state);
		btn.setChecked(btn_state);
		ID = sharedPreferences.getString("id", "0000000000");
		et_number.setText(ID);
		spinner_option = sharedPreferences.getInt("spinneroption", 0);
		mHour_sport = sharedPreferences.getInt("sporthour", 18);
		mMinute_sport = sharedPreferences.getInt("sportminute", 0);
		updateDisplay(btn_sporttime,mHour_sport,mMinute_sport);
		mHour_sleep = sharedPreferences.getInt("sleephour", 22);
		mMinute_sleep = sharedPreferences.getInt("sleepminute", 0);
		updateDisplay(btn_sleeptime,mHour_sleep,mMinute_sleep);
		mHour_wakeup = sharedPreferences.getInt("wakeuphour", 7);
		mMinute_wakeup = sharedPreferences.getInt("wakeupminute", 30);
		updateDisplay(btn_wakeuptime,mHour_wakeup,mMinute_wakeup);
		initial_sport.set(Calendar.HOUR_OF_DAY,18);
		initial_sport.set(Calendar.MINUTE, 0);
		initial_sport.set(Calendar.SECOND, 0);
		initial_sport.set(Calendar.MILLISECOND, 0);
         if(initial_sport.getTimeInMillis()<c.getTimeInMillis())
        	 initial_sport.add(Calendar.HOUR_OF_DAY, 24);
		firstime_sport=sharedPreferences.getLong("firstime_sport",initial_sport.getTimeInMillis());
		
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		Spinner spinner = (Spinner) findViewById(R.id.sp_sporttime_long);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容
		  ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sport_time);
		      //設定下拉選單的樣式
		       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        spinner.setAdapter(adapter);
		        spinner.setSelection(spinner_option);
	        //設定項目被選取之後的動作
		        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
		           public void onItemSelected(AdapterView adapterView, View view,int position, long id){
		        	    if(position==0)
		                	sport_time_long=30;
		                else if(position==1)
		                	sport_time_long=35;
		                else if(position==2)
		                	sport_time_long=40;
		                else if(position==3)
		                	sport_time_long=45;
		                else if(position==4)
		                	sport_time_long=50;
		                else if(position==5)
		                	sport_time_long=55;
		                else if(position==6)
		                	sport_time_long=60; 
		                else if(position==7)
			                sport_time_long=2;
		               
		                sharedPreferences.edit()
		        		.putInt ("spinneroption",position)
		        		.commit();
			    	    Intent intent =new Intent(Setting_v1.this, AlarmReceiver.class);
		        		intent.putExtra("title", "sporttime");
		        	    intent.setAction("repeating");
		        	    PendingIntent sender=PendingIntent
		        	        .getBroadcast(Setting_v1.this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		                AlarmManager alarmsporttime=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
		        	    //开始时间
		                firstime_sport=sharedPreferences.getLong("firstime_sport",initial_sport.getTimeInMillis());
		                firstime_sport2=firstime_sport+sport_time_long*60*1000;
		                if(firstime_sport2<c.getTimeInMillis())
		                	firstime_sport2=firstime_sport2+24*60*60*1000;
		        	    alarmsporttime.setRepeating(AlarmManager.RTC_WAKEUP
		        	            , firstime_sport2, AlarmManager.INTERVAL_DAY, sender);
		        	 	
		             	
		        	    
		           }
		            public void onNothingSelected(AdapterView arg0) {
		               Toast.makeText(Setting_v1.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
		           }
		            
		            
		 });
		
		btn_sporttime.setOnClickListener(new OnClickListener() {
			@Override
        	public void onClick(View v){
        		showDialog(SPORT_TIME_ID);
        	}
        });
		btn_sleeptime.setOnClickListener(new OnClickListener() {
			@Override
        	public void onClick(View v){
        		showDialog(SLEEP_TIME_ID);
        	}
        });   
		btn_wakeuptime.setOnClickListener(new OnClickListener() {
			@Override
        	public void onClick(View v){
        		showDialog(WAKEUP_TIME_ID);
        	}
        });   
		     	     	
     	ImageButton btn_once = (ImageButton)findViewById(R.id.btn_once);
     	btn_once.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent();
				intent.setClass(Setting_v1.this, QuesSelfEstimate.class);
				startActivity(intent);
			}
		});

     	ImageButton btn_re_setting_v1 = (ImageButton)findViewById(R.id.btn_re_setting_v1);
     	btn_re_setting_v1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				
				sharedPreferences.edit()
				.putString ("id",et_number.getEditableText().toString())
				.putBoolean("check", btn_state)
				.commit();
				Toast.makeText(Setting_v1.this, "已儲存 設定", Toast.LENGTH_SHORT).show();
				
				Intent intent2 = new Intent();
				intent2.setClass(Setting_v1.this, Menu_v1.class);
				startActivity(intent2);
			}
		});
     	
    
     	
	}
	
	private String pad(int c) {
		if (c>=10)
			return String.valueOf(c);
		else
			return "0"+String.valueOf(c);
	}

	private TimePickerDialog.OnTimeSetListener timeSetListener_sport = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour_sport = hourOfDay;
    		mMinute_sport = minute;
    		updateDisplay(btn_sporttime,mHour_sport,mMinute_sport);
    		sharedPreferences.edit()
    		.putInt ("sporthour",mHour_sport)
    		.putInt("sportminute", mMinute_sport)
    		.commit();
    		Intent intent =new Intent(Setting_v1.this, AlarmReceiver.class);
    		intent.putExtra("title", "sport");
    	    intent.setAction("repeating");
    	    PendingIntent sender=PendingIntent
    	        .getBroadcast(Setting_v1.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    	    d_sport.getInstance();
    	    d_sport.set(Calendar.HOUR_OF_DAY, mHour_sport);
    	    d_sport.set(Calendar.MINUTE, mMinute_sport);
    	    d_sport.set(Calendar.SECOND, 0);
    	    d_sport.set(Calendar.MILLISECOND, 0);
            if(d_sport.getTimeInMillis()<c.getTimeInMillis())
            	d_sport.add(Calendar.HOUR_OF_DAY, 24);
            AlarmManager alarmsport=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	    //开始时间
            firstime_sport=d_sport.getTimeInMillis();
            sharedPreferences.edit()
    		.putLong ("firstime_sport",firstime_sport)
    		.commit();
    	    alarmsport.setRepeating(AlarmManager.RTC_WAKEUP
    	            , firstime_sport, AlarmManager.INTERVAL_DAY, sender);
    	    
    	    //-------運動長度鬧鐘-----
    	    intent.putExtra("title", "sporttime");
    	    sender=PendingIntent
    	        .getBroadcast(Setting_v1.this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmsporttime=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	    //开始时间
            firstime_sport=sharedPreferences.getLong("firstime_sport",initial_sport.getTimeInMillis());
            firstime_sport2=firstime_sport+sport_time_long*60*1000;
            if(firstime_sport2<c.getTimeInMillis())
            	firstime_sport2=firstime_sport2+24*60*60*1000;
    	    alarmsporttime.setRepeating(AlarmManager.RTC_WAKEUP
    	            , firstime_sport2, AlarmManager.INTERVAL_DAY, sender);
    	    
		}
	};
	
	
    private TimePickerDialog.OnTimeSetListener timeSetListener_sleep = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour_sleep = hourOfDay;
    		mMinute_sleep = minute;
    		updateDisplay(btn_sleeptime,mHour_sleep,mMinute_sleep);
    		sharedPreferences.edit()
    		.putInt ("sleephour",mHour_sleep)
    		.putInt("sleepminute", mMinute_sleep)
    		.commit();
    		Intent intent = new Intent(Setting_v1.this, AlarmReceiver.class);
    		intent.putExtra("title", "sleep");
    	    intent.setAction("repeating");
    	    PendingIntent sender = PendingIntent
    	        .getBroadcast(Setting_v1.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    	    d.getInstance();
    	    d.set(Calendar.HOUR_OF_DAY, mHour_sleep);
            d.set(Calendar.MINUTE, mMinute_sleep);
            d.set(Calendar.SECOND, 0);
            d.set(Calendar.MILLISECOND, 0);
            if(d.getTimeInMillis()<c.getTimeInMillis())
                d.add(Calendar.HOUR_OF_DAY, 24);
            AlarmManager alarmsleep=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	    //开始时间
    	    long firstime = d.getTimeInMillis();
    	    alarmsleep.setRepeating(AlarmManager.RTC_WAKEUP
    	            , firstime, AlarmManager.INTERVAL_DAY, sender);
    	}
    };
    private TimePickerDialog.OnTimeSetListener timeSetListener_wakeup = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour_wakeup = hourOfDay;
    		mMinute_wakeup = minute;
    		updateDisplay(btn_wakeuptime,mHour_wakeup,mMinute_wakeup);
    		sharedPreferences.edit()
    		.putInt ("wakeuphour",mHour_wakeup)
    		.putInt("wakeupminute", mMinute_wakeup)
    		.commit();
    		Intent intent = new Intent(Setting_v1.this, AlarmReceiver.class);
    		intent.putExtra("title", "wakeup");
    	    intent.setAction("repeating");
    	    PendingIntent sender = PendingIntent
    	        .getBroadcast(Setting_v1.this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    	    //d為設定時間，c為當下時間，d必須在c之後鬧鐘才可正常執行
    	    d.getInstance();
    	    d.set(Calendar.HOUR_OF_DAY, mHour_wakeup);
            d.set(Calendar.MINUTE, mMinute_wakeup);
            d.set(Calendar.SECOND, 0);
            d.set(Calendar.MILLISECOND, 0);
            if(d.getTimeInMillis()<c.getTimeInMillis())
            d.add(Calendar.HOUR_OF_DAY, 24); 	
            AlarmManager alarmwakeup=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
    	    //开始时间
    	    long firstime = d.getTimeInMillis();
    	    alarmwakeup.setRepeating(AlarmManager.RTC_WAKEUP
    	           , firstime, AlarmManager.INTERVAL_DAY, sender);
    	  
    	   
		}
		
		
    };
    private void updateDisplay(Button btn,int hour,int minute) {
    	btn.setText(
    			new StringBuilder()
    			.append(pad(hour)).append(":")
    			.append(pad(minute))
    			);
    	
    }
    
	protected Dialog onCreateDialog(int id, Bundle args) {
		switch(id) {
		case SPORT_TIME_ID:
			return new TimePickerDialog(this,timeSetListener_sport, mHour_sport, mMinute_sport , false);
		case SLEEP_TIME_ID:
			return new TimePickerDialog(this,timeSetListener_sleep, mHour_sleep, mMinute_sleep, false);
		case WAKEUP_TIME_ID:
			return new TimePickerDialog(this,timeSetListener_wakeup, mHour_wakeup, mMinute_wakeup, false);
		
		}
		return null;
	}
	
	private ToggleButton.OnClickListener btnClickListener = new ToggleButton.OnClickListener() {
        public void onClick(View arg0) {
        	if (btn.isChecked()) {
        		if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        Log.d("CHECK_POINT", "有網路" );
                        //啟動服務
                        Intent intent1 = new Intent(Setting_v1.this, background.class);
                        startService(intent1);
                        btn_state = true ;
                    } else {
                    	btn.setChecked(false);
                        Log.d("CHECK_POINT", "網路斷線 ");
                        Toast.makeText(Setting_v1.this, "請連接網路 ", Toast.LENGTH_SHORT).show();
                        btn_state = false ;
                    }
    			} else {
    				btn.setChecked(false);
    				Toast.makeText(Setting_v1.this, "請開啟定位服務", Toast.LENGTH_SHORT).show();
    				startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));	//開啟設定頁面
    				btn_state = false ;
    			}
        	} else {
        		
        		//停止服務
        		Intent intent2 = new Intent(Setting_v1.this, background.class);
                stopService(intent2);
                
                uploadground = getSharedPreferences("uploadground",MODE_PRIVATE);
                uploadgroundfilepath = uploadground.getString("groundfilepath","");
                uploadgroundfilename = uploadground.getString("groundfilename","");
                uploadgroundfiledate = uploadground.getString("groundfiledate","");
        		
        		sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
        		ID = sharedPreferences.getString("id", "0000000000");
        		
        		filename = uploadgroundfilename;
        		filedate = uploadgroundfiledate;
        		SendfileData();
        		new Thread(new Runnable() {
        			
        			public void run() {
        				
        				runOnUiThread(new Runnable() {
        					
        					public void run() {
        						
        					}
        				});
        				
        				uploadFile(uploadgroundfilepath + uploadgroundfilename);
        			}
        		}).start();
        		
        		btn_state = false ;
        	}
        }
    };
	
	public int uploadFile(String sourceFileUri) {
		
		String fileName = sourceFileUri;
		  
        HttpURLConnection conn = null;
        DataOutputStream dos = null;  
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024; 
        File sourceFile = new File(sourceFileUri);
        
        if (!sourceFile.isFile()) {
        	

        	runOnUiThread(new Runnable() {
        		public void run() {
        			Toast.makeText(Setting_v1.this, "Source File not exist",Toast.LENGTH_LONG).show();	
        		}
        	});
        	
        	
        	return 0;
        }
        else{
        	
        	try {
        		
        		// open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);
                
                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection(); 
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);
                
                dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data;name=\"uploaded_file\";filename=\""+fileName+"\"" + lineEnd);
                dos.writeBytes(lineEnd);
                
                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available(); 
       
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
       
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
                   
                while (bytesRead > 0) {
                     
                  dos.write(buffer, 0, bufferSize);
                  bytesAvailable = fileInputStream.available();
                  bufferSize = Math.min(bytesAvailable, maxBufferSize);
                  bytesRead = fileInputStream.read(buffer, 0, bufferSize);   
                   
                 }
       
                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
       
                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();
                
                Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
                
                if(serverResponseCode == 200){
                	
                	runOnUiThread(new Runnable() {
                		
                		public void run() {
                			
                			Toast.makeText(Setting_v1.this, "File Upload Complete.",Toast.LENGTH_LONG).show();
                		}
                	});
                }
                
              //close the streams 
                fileInputStream.close();
                dos.flush();
                dos.close();
                
        	}catch (MalformedURLException ex) {
        		
                ex.printStackTrace();
                 
                runOnUiThread(new Runnable() {
                	
                	public void run() {
                		Toast.makeText(Setting_v1.this, "MalformedURLException",Toast.LENGTH_LONG).show();
                	}
                });
                
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        	}catch (Exception e) {
        		
                e.printStackTrace();
                 
                runOnUiThread(new Runnable() {
                	
                	public void run() {
                		
                		Toast.makeText(Setting_v1.this,"Got Exception : see logcat",Toast.LENGTH_LONG).show();
                	}
                });
                Log.e("Upload file to server Exception", "Exception : "+ e.getMessage(), e);
        	}
  
            return serverResponseCode;
        } // End else block
	}
    
    
	public void SendfileData()
	   {
			
				 String msg0 = ID;
				 String msg01 = filename;
				 String msg02 = filedate;
	             
	             Thread t = new Thread(new sendPostRunnable(msg0,msg01,msg02));
	             t.start();
			
	   }
	
	private String sendPostDataToInternet(String strTxt0,String strTxt01,String strTxt02)
	   {
		// 建立HTTP Post連線 
	      HttpPost httpRequest = new HttpPost(upLoaddataUri);
	      
	       // Post運作傳送變數必須用NameValuePair[]陣列儲存
	       
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair("ID", strTxt0));
	      params.add(new BasicNameValuePair("name", strTxt01));
	      params.add(new BasicNameValuePair("date", strTxt02));
	      try
	      {
	          // 發出HTTP request 
	          httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	          // 取得HTTP response 
	          HttpResponse httpResponse = new DefaultHttpClient()
	                .execute(httpRequest);
	          // 若狀態碼為200 ok 
	          if (httpResponse.getStatusLine().getStatusCode() == 200)
	          {
	             // 取出回應字串 
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
	      
	      public sendPostRunnable(String strTxt0,String strTxt01,String strTxt02)
	      {
	    	  this.strTxt0 = strTxt0;	 
	    	  this.strTxt01 = strTxt01;
	    	  this.strTxt02 = strTxt02;
	      }
	      
	      @Override
	      public void run()
	      {
	    	  String result = sendPostDataToInternet(strTxt0,strTxt01,strTxt02);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	      }
	   }
		   
	
		   
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		sharedPreferences.edit()
		.putString ("id",et_number.getEditableText().toString())
		.putBoolean("check", btn_state)
		.commit();
		Toast.makeText(Setting_v1.this, "已儲存 設定", Toast.LENGTH_SHORT).show();
		
	}
	
	
}
