package com.caresys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.text.format.Time;
import android.widget.Toast;


public class SleepService extends Service {
	
	SharedPreferences uploadsleepfile;
	private String path,name,uploadfilepath,uploadsleepfilename,date,uploadsleepdate;
	
	private static final int POLL_INTERVAL = 100;
	private Handler mHandler = new Handler();
	private boolean mRunning = false;
	MediaRecorder mMediaRecorder; 
	Time mTime = new Time();
	float recent;
	Double[]x =new Double[30];
	String a="";
	int i=0;
	File accFile = null;
	File aFile ;
	FileWriter mFileWriter ;
	private Runnable mPollTask = new Runnable() {
        public void run() {
        	if (mMediaRecorder != null)
        		recent = mMediaRecorder.getMaxAmplitude();
            else
                recent = 0;
        	//Log.i("Noise", "runnable mPollTask");
        	i++;
        	mTime.setToNow();
        	if(i > 0) {
        		x[i] = (double)recent;
        		a = a +x[i]+","+ mTime.hour+":"+mTime.minute+":"+mTime.second +"\r\n";
        	}
            if(i == 29) {
            	SaveData();
            	i = 0;
            	a="";
        	}
            // Runnable(mPollTask) will again execute after POLL_INTERVAL
            mHandler.postDelayed(mPollTask, POLL_INTERVAL);
        }
	};
	@Override
	public void onCreate() {
		super.onCreate();
		
		uploadsleepfile = getSharedPreferences("uploadsleep",MODE_PRIVATE);
		path = uploadsleepfile.getString("filepath","");
		name = uploadsleepfile.getString("sleepfilename","");
		date = uploadsleepfile.getString("sleepfiledate","");
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		{
    		try{
				//檢查有沒有SD卡裝置
				if(Environment.getExternalStorageState().equals( Environment.MEDIA_REMOVED)){
					Toast.makeText(SleepService.this , "沒有SD卡!!!" , Toast.LENGTH_SHORT ).show();
				}
				else{
					//取得SD卡儲存路徑
					accFile = Environment.getExternalStorageDirectory();
				}
				//建立文件檔儲存路徑
				aFile = new File(accFile.getPath() + "/CareSys");
				uploadfilepath = accFile.getPath() + "/CareSys/";
				
				//若沒有檔案儲存路徑時則建立此檔案路徑
				if(!aFile.exists()){
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
				
				mFileWriter = new FileWriter(accFile.getPath() + "/CareSys/sound" + m + (mTime.month+1) + d + mTime.monthDay + ".txt",true);
				//a = mTime.year+"/"+(mTime.month+1)+"/"+mTime.monthDay+"  "+mTime.hour+":"+mTime.minute+":"+mTime.second+ "\r\n";
				//mFileWriter.write(a.toString() );
				//a = "";
				Toast.makeText(SleepService.this, "sound" + m + (mTime.month+1) + d + mTime.monthDay + ".txt已建立", Toast.LENGTH_SHORT).show();
				uploadsleepfilename = "sound" + m + (mTime.month+1) + d + mTime.monthDay + ".txt";
				uploadsleepdate = m + (mTime.month+1) + d + mTime.monthDay ;
				
				uploadsleepfile.edit()
				.putString ("filepath",uploadfilepath)
				.putString ("sleepfilename",uploadsleepfilename)
				.putString ("sleepfiledate",uploadsleepdate)
				.commit();
            }
			catch (Exception e){
				
			}
		}
		for(int j=0;j<30;j++) {
			x[j]=0.0;
		}
		
		if (!mRunning) {
			mRunning = true;
            start();
            startrun();
		}
		return START_STICKY;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		stop();
		//mTime.setToNow();
		//a = a + mTime.year+"/"+(mTime.month+1)+"/"+mTime.monthDay+"  "+mTime.hour+":"+mTime.minute+":"+mTime.second+ "\r\n";
		//SaveData();
		{
			try {
				mFileWriter.close();
			}
			catch (Exception e) {
				
			}
		}
		Toast.makeText(SleepService.this, "已儲存文字", Toast.LENGTH_SHORT).show();
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	public void start() {
		if (mMediaRecorder == null) {
			mMediaRecorder = new MediaRecorder();
    		mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    		mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    		mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    		mMediaRecorder.setOutputFile("/dev/null");
    		try {
    			mMediaRecorder.prepare();
    		}
    		catch (IllegalStateException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		mMediaRecorder.start();
    		recent = 0;
    	}
	}
	private void startrun() {
		//Log.i("Noise", "==== start ===");
		//Noise monitoring start
        // Runnable(mPollTask) will execute after POLL_INTERVAL
        mHandler.postDelayed(mPollTask, POLL_INTERVAL);
	}
	private void stop() {
		if (mMediaRecorder != null) {
			mMediaRecorder.stop();       
			mMediaRecorder.release();
			mMediaRecorder = null;
		}
		mHandler.removeCallbacks(mPollTask);
	}
	private void SaveData() {
		{
			try{
				mFileWriter.write(a.toString() );
			}
			catch (Exception e) {
				
			}
		}
	}
}