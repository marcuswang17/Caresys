package com.caresys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class AlarmReceiver extends BroadcastReceiver {  
    @Override  
    public void onReceive(Context context, Intent intent) { 
    	
    	Bundle bundle = intent.getExtras();
    	if(bundle.get("title").equals("sleep")) {
    		intent.setClass(context, AlarmDialog_Sleep.class);
        	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		bundle.putString("title", "睡眠時間提醒");
    	}
    	else if(bundle.get("title").equals("sport")) 
    	{
    		intent.setClass(context, AlarmDialog_Sport.class);
    		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		bundle.putString("title", "運動開始提醒");
    	}
    	else if(bundle.get("title").equals("sporttime")) 
    	{
    		intent.setClass(context, AlarmDialog_Sporttime.class);
    		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		bundle.putString("title", "運動結束提醒");
    	}
    	else {
    		intent.setClass(context, AlarmDialog_Wakeup.class);
    		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		bundle.putString("title", "起床時間提醒");
    	}
    	intent.putExtras(bundle);
    	context.startActivity(intent);  
    	
    }  
} 