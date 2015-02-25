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
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class AlarmDialog_Wakeup extends Activity {
	
	SharedPreferences sharedPreferences,uploadsleepfile,uploadsportfile,uploadground;
	private String ID,uploadfilepath,uploadsleepfilename,filename,filedate;
	private String uploadsportfilename,uploadsportpath,sportdate,sleepdate;
	
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
	                Toast.makeText(AlarmDialog_Wakeup.this, result, Toast.LENGTH_LONG).show();
	             break;
	          }
	      }
	      
	   };
	
    int serverResponseCode = 0;
        
    String upLoadServerUri,upLoaddataUri = null;
	
	/** Called when the activity is first created. */
	Integer n;
	String Title;
	private Thread thread = null;
	private int alertId;
	private SoundPool soundPool;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/************* Php script path ****************/
        upLoaddataUri = "http://140.116.39.44/dataPostTest.php";
        upLoadServerUri = "http://140.116.39.44/filesendTest.php";
        
		//setContentView(R.layout.activity_main);
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		Title = bundle.getString("title");
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
		//( SoundPool 內總共放置的音效數目, 串流類型,音效品質)
		alertId = soundPool.load(this, R.raw.fanfare, 1); 
		//讀取效果音resource的檔案，檔名記得要小寫並且小於1mb 
		thread = new Thread(){
			@Override
            public void run(){
				try{
                    
                    Thread.sleep(1000);
                    handler.sendEmptyMessage(0);
                }catch (Exception e){
                    e.printStackTrace();
                }finally{
                }
				
			}
		};
		thread.start();
	}
	Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            viewCategory();
           
        }
    };

	private void viewCategory() {
		
		AlertDialog.Builder viewDialog = new AlertDialog.Builder(this);

		viewDialog.setTitle(Title);

		LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogView = li.inflate(R.layout.spinner, null);
		viewDialog.setView(dialogView);
		Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
		myVibrator.vibrate(new long[]{10, 1000, 500, 200, 100, 200, 100, 200}, -1);
		//停0.01秒 震1秒 停0.5秒 震0.2秒 停0.5秒 震0.2秒 停0.5秒 震0.2秒 
		soundPool.play(alertId, 1.0F, 1.0F, 0, 0, 1.0F);
		//(播放哪個音效檔, 左喇叭音量, 右喇叭音量,固定用 0,0 為不重複，-1 為無限重複,播放速度，可用 0.5 到 2)
		viewDialog.setPositiveButton("賴床",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MINUTE, n);
				AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
				Intent intent = new Intent(AlarmDialog_Wakeup.this,AlarmReceiver.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("title", Title);
				PendingIntent sender = PendingIntent.getBroadcast(AlarmDialog_Wakeup.this, 4, intent, PendingIntent.FLAG_ONE_SHOT);
				alarm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
				finish();
			}
		});

		viewDialog.setNegativeButton("起床",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				
				sharedPreferences = getSharedPreferences("sharedPre",MODE_PRIVATE);
        		ID = sharedPreferences.getString("id", "0000000000");
        		
				Intent intent2 = new Intent();
        		intent2.setClass(AlarmDialog_Wakeup.this, SportService.class);
        		stopService(intent2);
        		
        		uploadsportfile = getSharedPreferences("uploadsport",MODE_PRIVATE);
        		uploadsportpath = uploadsportfile.getString("sportfilepath","");
        		uploadsportfilename = uploadsportfile.getString("sportfilename", "");
        		sportdate = uploadsportfile.getString("sportfiledate", "");
        		filedate = sportdate;
        		filename = uploadsportfilename;
        		SendfileData();
        		new Thread(new Runnable() {
        			
        			public void run() {
        				
        				runOnUiThread(new Runnable() {
        					
        					public void run() {
        						
        					}
        				});
        				
        				uploadFile(uploadsportpath + uploadsportfilename);
        			}
        		}).start();
				
				Intent intent = new Intent();
        		intent.setClass(AlarmDialog_Wakeup.this, SleepService.class);
        		stopService(intent);
        		
        		uploadsleepfile = getSharedPreferences("uploadsleep",MODE_PRIVATE);
        		uploadfilepath = uploadsleepfile.getString("filepath", "");
        		uploadsleepfilename = uploadsleepfile.getString("sleepfilename", "");
        		sleepdate = uploadsleepfile.getString("sleepfiledate", "");
        		filename = uploadsleepfilename;
        		filedate = sleepdate;
        		       		        		
        		SendfileData();
        		       		
        		new Thread(new Runnable() {
        			
        			public void run() {
        				
        				runOnUiThread(new Runnable() {
        					
        					public void run() {
        						
        					}
        				});
        				
        				uploadFile(uploadfilepath + uploadsleepfilename);
        			}
        		}).start();
        		    		        		
				Intent intent3 = new Intent(AlarmDialog_Wakeup.this,Menu_v1.class);
				startActivity(intent3);
				
				Intent intent5 = new Intent();
        		intent5.setClass(AlarmDialog_Wakeup.this, SportService.class);
        		startService(intent5);
								
			}
		});
		
		Spinner spinnercategory = (Spinner) dialogView
				.findViewById(R.id.sp);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.category, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnercategory.setAdapter(adapter);

		spinnercategory.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View arg1,
					int arg2, long arg3) {
				String selItem = parent.getSelectedItem().toString();
				n = Integer.valueOf(selItem).intValue();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
		viewDialog.show();
	}
	
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
        			Toast.makeText(AlarmDialog_Wakeup.this, "Source File not exist",Toast.LENGTH_LONG).show();	
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
                			
                			Toast.makeText(AlarmDialog_Wakeup.this, "File Upload Complete.",Toast.LENGTH_LONG).show();
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
                		Toast.makeText(AlarmDialog_Wakeup.this, "MalformedURLException",Toast.LENGTH_LONG).show();
                	}
                });
                
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
        	}catch (Exception e) {
        		
                e.printStackTrace();
                 
                runOnUiThread(new Runnable() {
                	
                	public void run() {
                		
                		Toast.makeText(AlarmDialog_Wakeup.this,"Got Exception : see logcat",Toast.LENGTH_LONG).show();
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

}