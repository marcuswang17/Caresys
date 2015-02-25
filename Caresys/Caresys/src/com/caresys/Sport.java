package com.caresys;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Sport extends Activity{
	SensorManager sensorManager;
	boolean accelerometerPresent;
	Sensor accelerometerSensor;
	TextView textInfo, textX, textY, textZ;
	TextView textView01;
	TextView textView02;
	TextView textView03;
	Button Button1;
	Time mTime = new Time();
	Double[]linear_acceleration =new Double[3];
	Double[]gravity =new Double[3];
	Double[]x =new Double[500];
	Double[]y =new Double[500];
	Double[]z =new Double[500];
	String[]A =new String[500];
	String a=" ";
	int i=-10; //前1秒左右的a會有誤差
	int I=-10;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sport);
		Button1 = (Button)findViewById(R.id.btn_quespsqi);
		textInfo = (TextView)findViewById(R.id.info);
		textX = (TextView)findViewById(R.id.textx);
		textY = (TextView)findViewById(R.id.texty);
		textZ = (TextView)findViewById(R.id.textz);
		gravity[0]=0.0;
		gravity[1]=0.0;
		gravity[2]=0.0;
		for(int j=0;j<500;j++) {
			x[j]=0.0;
			y[j]=0.0;
			z[j]=0.0;
		}
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if(sensorList.size() > 0) {
			accelerometerPresent = true;
			accelerometerSensor = sensorList.get(0);
			String strSensor  = "Name: " + accelerometerSensor.getName()
										 + "\nVersion: " + String.valueOf(accelerometerSensor.getVersion())
										 + "\nVendor: " + accelerometerSensor.getVendor()
										 + "\nType: " + String.valueOf(accelerometerSensor.getType())
										 + "\nPower: " + String.valueOf(accelerometerSensor.getPower());
			textInfo.setText(strSensor);
		}
	    else {
	    	accelerometerPresent = false;
	    }
		Button1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				{
					try {
						File accFile = null;
						//檢查有沒有SD卡裝置
						if(Environment.getExternalStorageState().equals( Environment.MEDIA_REMOVED)) {
							Toast.makeText(Sport.this , "沒有SD卡!!!" , Toast.LENGTH_SHORT ).show();
							return ;
						}
						else {
							//取得SD卡儲存路徑
							accFile = Environment.getExternalStorageDirectory();
						}
						//建立文件檔儲存路徑
						File aFile = new File(accFile.getPath() + "/acc資料");
						//若沒有檔案儲存路徑時則建立此檔案路徑
						if(!aFile.exists()) {
							aFile.mkdirs();
						}
						//取得mEdit文字並儲存寫入至SD卡文件裡
						FileWriter mFileWriter = new FileWriter(accFile.getPath() + "/acc資料/acc值.txt");
						mFileWriter.write(a.toString() );
						mFileWriter.close();
						Toast.makeText(Sport.this, "已儲存文字", Toast.LENGTH_SHORT).show();
	                }
					catch (Exception e) {
						
					}
				}
				//傳送參數到下一個Actiity
				Intent intent = new Intent();
				//設定下一個Activity
				intent.setClass(Sport.this,LineSport.class);
				String[] d= new String[1500];
				Bundle bundle = new Bundle();
				for(int j=0;j<1500;j++)
					d[j]=String.valueOf(j);
				for(int j=0;j<i;j++) {
					bundle.putDouble(d[j],x[j]);
					bundle.putDouble(d[j+500],y[j]);
					bundle.putDouble(d[j+1000],z[j]);
				}
				bundle.putInt("I",I);
				bundle.putInt("i",i);
				//將Bundle物件assign給intent
				intent.putExtras(bundle);
				//開啟Activity
				startActivity(intent);
			}
		});
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 在此處加上執行的代碼
				handler.postDelayed(this, 100);// 100是延長時間
				i++;
				I++;
			}
		}, 100);
	}
	@Override
	protected void onResume() {
		// TODO <span class="z94t4765w5q" id="z94t4765w5q_2">Auto</span>-generated method stub
		super.onResume();
		if(accelerometerPresent) {
			sensorManager.registerListener(accelerometerListener, accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
		}
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(accelerometerPresent) {
			sensorManager.unregisterListener(accelerometerListener);
		}
	}
	private SensorEventListener accelerometerListener = new SensorEventListener(){
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
	        
	        if( i > 0) {
	        	textX.setText("X: " + String.valueOf(linear_acceleration[0]));
	        	x[i] = linear_acceleration[0];
	        	textY.setText("Y: " + String.valueOf(linear_acceleration[1]));
	        	y[i] = linear_acceleration[1]; 
	        	textZ.setText("Z: " + String.valueOf(linear_acceleration[2]));
	        	z[i] = linear_acceleration[2];
	        	mTime.setToNow();
	        	a=a+mTime.year+"/"+(mTime.month+1)+"/"+mTime.monthDay+"  "+mTime.hour+":"+mTime.minute+":"+mTime.second+ "\r\nx="+x[i]+",\r\ny="+y[i]+",\r\nz="+z[i]+"\r\n\r\n";
	        }
	        if( i == 499 ) {
	        	for(int k=1;k<500;k++) {
	        		x[k-1]=x[k];
	        		y[k-1]=y[k];
	        		z[k-1]=z[k];
	        	}
	        	i=i-1;
	        }
	    }
	};		
}