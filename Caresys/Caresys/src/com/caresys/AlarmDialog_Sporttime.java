package com.caresys;


import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AlarmDialog_Sporttime extends Activity {
	/** Called when the activity is first created. */
	Integer n;
	String Title;
	private Thread thread = null;
	private int alertId;
	private SoundPool soundPool;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		viewDialog.setPositiveButton("延遲",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MINUTE, n);
				AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
				Intent intent = new Intent(AlarmDialog_Sporttime.this,AlarmReceiver.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("title", Title);
				PendingIntent sender = PendingIntent.getBroadcast(AlarmDialog_Sporttime.this, 4, intent, PendingIntent.FLAG_ONE_SHOT);
				alarm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
				finish();
			}
		});

		viewDialog.setNegativeButton("記錄",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Intent intent = new Intent(AlarmDialog_Sporttime.this,Menu_v1.class);
				startActivity(intent);
				
				
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

}