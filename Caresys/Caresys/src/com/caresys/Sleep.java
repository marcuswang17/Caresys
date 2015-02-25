package com.caresys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Sleep extends Activity {
	private static final int POLL_INTERVAL = 300;
	private boolean mRunning = false;
	TextView N;
	TextView B;
	TextView DB;
	private Button GB;
	private Button Button2;
	private Handler mHandler = new Handler();
	MediaRecorder mMediaRecorder; 
	Time mTime = new Time();
	float ratio;
	float BASE=20;
	float recent;
	float temp;
	int db;
	Double[]x =new Double[500];
	String a=" ";
	int i=0;
	int I=0;

	private Runnable mSleepTask = new Runnable() {
        public void run() {
        	//Log.i("Noise", "runnable mSleepTask"); 
        	start();
        }
	};
	
	private Runnable mPollTask = new Runnable() {
        public void run() {
        	if (mMediaRecorder != null)
        		recent = mMediaRecorder.getMaxAmplitude();
            else
                recent = 0;
        	
        	ratio = recent/BASE;
        	//Log.i("Noise", "runnable mPollTask");
        	db = (int) (20 * Math.log10(ratio));
        	N.setText("實際聲壓"+recent);
        	B.setText("基礎聲壓"+BASE);
        	DB.setText("分貝"+db);
        	i++;
        	I++;
        	if(i>0){
        		x[i] = 20 * Math.log10(ratio);
        		mTime.setToNow();
        		a=a+mTime.year+"/"+(mTime.month+1)+"/"+mTime.monthDay+"  "+mTime.hour+":"+mTime.minute+":"+mTime.second+ "\r\n分貝="+x[i]+"\r\n\r\n";
        	}
            if(i==499){
            	for(int k=1;k<500;k++){
            		x[k-1]=x[k];
            	}
            	i=i-1;
        	}
            // Runnable(mPollTask) will again execute after POLL_INTERVAL
            mHandler.postDelayed(mPollTask, POLL_INTERVAL);
        }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sleep);
		N=(TextView)findViewById(R.id.mylocation);
		B=(TextView)findViewById(R.id.airinfo);
		DB=(TextView)findViewById(R.id.textView3);
		GB=(Button)findViewById(R.id.btn_quespsqi);
		Button2=(Button)findViewById(R.id.btn_sporttime);
		for(int j=0;j<500;j++){
			x[j]=0.0;
		}
		
		GB.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View view){
				if (mMediaRecorder != null)
	                temp = mMediaRecorder.getMaxAmplitude();
	            else
	                temp = 0;
				BASE=temp;
				for(int j=0;j<500;j++){
					x[j]=0.0;
				} 
				i=0;
				I=0;
			}
		});
		
		start();
		
		Button2.setOnClickListener(new Button.OnClickListener(){//繪圖
			@Override
	        public void onClick(View v) {{
					try{
						File accFile = null;
						//檢查有沒有SD卡裝置
						if(Environment.getExternalStorageState().equals( Environment.MEDIA_REMOVED)){
							Toast.makeText(Sleep.this , "沒有SD卡!!!" , Toast.LENGTH_SHORT ).show();
							return ;
						}
						else{
							//取得SD卡儲存路徑
							accFile = Environment.getExternalStorageDirectory();
						}
						//建立文件檔儲存路徑
						File aFile = new File(accFile.getPath() + "/聲音資料");
						//若沒有檔案儲存路徑時則建立此檔案路徑
						if(!aFile.exists()){
							aFile.mkdirs();
						}
						//取得mEdit文字並儲存寫入至SD卡文件裡
						FileWriter mFileWriter = new FileWriter(accFile.getPath() + "/聲音資料/分貝值.txt");
						mFileWriter.write(a.toString());
						mFileWriter.close();
						Toast.makeText(Sleep.this, "已儲存文字", Toast.LENGTH_SHORT).show();
	                }
					catch (Exception e){
						
					}
				}
	        	//傳送參數到下一個Actiity
	        	Intent intent = new Intent();
	        	//設定下一個Activity
	        	intent.setClass(Sleep.this,LineSleep.class);
	        	String[] d= new String[1500];
	        	Bundle bundle = new Bundle();
	        	for(int j=0;j<1500;j++)
	        		d[j]=String.valueOf(j);
	        	for(int j=0;j<i;j++){
	        		bundle.putDouble(d[j],x[j]);
	        	}
	        	bundle.putInt("I",I);
	        	bundle.putInt("i",i);
	        	//將Bundle物件assign給intent
	        	intent.putExtras(bundle);
	        	//開啟Activity
	        	startActivity(intent);
			}
		});
	}
	@Override
	public void onResume(){
		super.onResume();
		if (!mRunning) {
			mRunning = true;
            start();
            startrun();
		}
	}
	@Override
    public void onStop() {
		super.onStop();
		// Log.i("Noise", "==== onStop ===");
		//Stop noise monitoring
		stop();
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
    		ratio = 0;
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
		mHandler.removeCallbacks(mSleepTask);
		mHandler.removeCallbacks(mPollTask);
		mRunning = false;
	}
}
