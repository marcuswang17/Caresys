package com.caresys;


import java.io.File;
import java.io.FileWriter;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.text.format.Time;
import android.widget.Toast;

public class SportService extends Service {
	
	SharedPreferences uploadsportfile;
	private String sportpath,sportname,uploadsportfilename,uploadsportfilepath,sportdate,uploadsportfiledate;
	
	private static final int POLL_INTERVAL = 100;
	SensorManager sensorManager;
	boolean accelerometerPresent;
	Sensor accelerometerSensor;
	private Handler mHandler = new Handler();
	Time mTime = new Time();
	Double[]linear_acceleration =new Double[3];
	Double[]gravity =new Double[3];
	Double[]x =new Double[10];
	Double[]y =new Double[10];
	Double[]z =new Double[10];
	Double[]acc =new Double[10];
	String a="";
	int i=-10; //前1秒左右的a會有誤差
	File accFile = null;
	File aFile ;
	FileWriter mFileWriter ;
	@Override
	public void onCreate() {
		super.onCreate();
		
		uploadsportfile = getSharedPreferences("uploadsport",MODE_PRIVATE);
		sportname = uploadsportfile.getString("sportfilename","");
		sportpath = uploadsportfile.getString("sportfilepath","");
		sportdate = uploadsportfile.getString("sportfiledate","");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		{
			try {
				//檢查有沒有SD卡裝置
				if(Environment.getExternalStorageState().equals( Environment.MEDIA_REMOVED)) {
					Toast.makeText(SportService.this , "沒有SD卡!!!" , Toast.LENGTH_SHORT ).show();
				}
				else {
					//取得SD卡儲存路徑
					accFile = Environment.getExternalStorageDirectory();
				}
				//建立文件檔儲存路徑
				aFile = new File(accFile.getPath() + "/CareSys");
				uploadsportfilepath = accFile.getPath() + "/CareSys/";
				//若沒有檔案儲存路徑時則建立此檔案路徑
				if(!aFile.exists()) {
					aFile.mkdirs();
				}
				//取得mEdit文字並儲存寫入至SD卡文件裡
				String m="";
				String d="";
				
				mTime.setToNow();
				if((mTime.month+1) >= 10) {
					m = "";
				}
				else {
					m="0";
				}
				if(mTime.monthDay >= 10) {
					d = "";
				}
				else {
					d="0";
				}
								
				mFileWriter = new FileWriter(accFile.getPath() + "/CareSys/acc" + m + (mTime.month+1) + d + mTime.monthDay + ".txt",true);
				//a = mTime.year+"/"+(mTime.month+1)+"/"+mTime.monthDay+"  "+mTime.hour+":"+mTime.minute+":"+mTime.second+ "\r\n";
				//mFileWriter.write(a.toString() );
				//a = "";
				Toast.makeText(SportService.this, "acc" + m + (mTime.month+1) + d + mTime.monthDay +".txt已建立", Toast.LENGTH_SHORT).show();
				uploadsportfilename = "acc" + m + (mTime.month+1) + d + mTime.monthDay + ".txt";
				uploadsportfiledate = m + (mTime.month+1) + d + mTime.monthDay ;
				
				uploadsportfile.edit()
				.putString ("sportfilepath",uploadsportfilepath)
				.putString ("sportfilename",uploadsportfilename)
				.putString ("sportfiledate",uploadsportfiledate)
				.commit();
			}
			catch (Exception e) {
				
			} 
		}
		gravity[0]=0.0;
	    gravity[1]=0.0;
	    gravity[2]=0.0;
	    for(int j = 0 ; j < 10 ; j++) {
	    	x[j] = 0.0;
	    	y[j] = 0.0;
	    	z[j] = 0.0;
	    }
	    sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	    List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
	    if(sensorList.size() > 0) {
	    	accelerometerPresent = true;
	    	accelerometerSensor = sensorList.get(0);
	    }
	    else {
	    	accelerometerPresent = false;
	    }
	    if(accelerometerPresent) {
			sensorManager.registerListener(accelerometerListener, accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
		}
	    mHandler.postDelayed(mPollTask, POLL_INTERVAL);
		return START_STICKY;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(accelerometerPresent) {
			sensorManager.unregisterListener(accelerometerListener);
		}
		//mTime.setToNow();
		//a = a + mTime.year+"/"+(mTime.month+1)+"/"+mTime.monthDay+"  "+mTime.hour+":"+mTime.minute+":"+mTime.second+ "\r\n";
		//SaveData();
		mHandler.removeCallbacks(mPollTask);
		{
			try{
				mFileWriter.close();
			}
			catch (Exception e) {
				
			}
		}
		Toast.makeText(SportService.this, "已儲存文字", Toast.LENGTH_SHORT).show();
		
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	private Runnable mPollTask = new Runnable() {
		 public void run() {
			 i++;
			 if( i > 0) {
				 x[i] = linear_acceleration[0];
				 y[i] = linear_acceleration[1];
				 z[i] = linear_acceleration[2];
				 acc[i]= Math.pow((Math.pow(x[i],2)+Math.pow(y[i],2)+Math.pow(z[i],2)),0.5);
				 mTime.setToNow();
				 a = a +x[i]+","+y[i]+","+z[i]+","+acc[i]+","+ mTime.hour+":"+mTime.minute+":"+mTime.second+"\r\n";
			 }
			 if( i == 9 ) {
				 SaveData();
				 i = 0;
				 a="";
			 }
			 mHandler.postDelayed(mPollTask, POLL_INTERVAL);
		 }
	};
	private SensorEventListener accelerometerListener = new SensorEventListener() {
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
		}
		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
		
			// alpha is calculated as t / (t + dT)
	        // with t, the low-pass filter's time-constant
	        // and dT, the event delivery rate

	        final double alpha = 0.8;

	        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
	        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
	        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
	        
	        linear_acceleration[0] = event.values[0] - gravity[0];
	        linear_acceleration[1] = event.values[1] - gravity[1];
	        linear_acceleration[2] = event.values[2] - gravity[2];
		}
	};
	private void SaveData() {
		{
			try {
				mFileWriter.write(a.toString() );
			}
			catch (Exception e) {
				
			}
		}
	}
}