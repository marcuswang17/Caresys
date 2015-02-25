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

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuesCOPDSGRQ extends Activity implements OnClickListener{
	
	private EditText txtMessage1,txtMessage2;
	private Button sendBtn;
	private String uriAPI = "http://140.116.39.44/CopdSgrqPost.php";
	private RadioGroup group0,group1,group2,group3,group4,group5,group6,group7,group9;
	private RadioGroup group10,group11,group12,group13,group14,group15,group16,group17,group18,group19;
	private RadioGroup group20,group21,group22,group23,group24,group25,group26,group27,group28,group29;
	private RadioGroup group30,group31,group32,group33,group34,group35,group36,group37,group38,group39;
	private RadioGroup group40,group41,group42,group43,group44,group45,group46,group47,group48,group49,group50;
	private String q0,q1,q2,q3,q4,q5,q6,q7,q9;//RadioGroup
	private String q10,q11,q12,q13,q14,q15,q16,q17,q18,q19;
	private String q20,q21,q22,q23,q24,q25,q26,q27,q28,q29;
	private String q30,q31,q32,q33,q34,q35,q36,q37,q38,q39;
	private String q40,q41,q42,q43,q44,q45,q46,q47,q48,q49,q50;
	
	
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
					Toast.makeText(QuesCOPDSGRQ.this, result, Toast.LENGTH_LONG).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ques_copdsgrq);
		
		
		txtMessage1 = (EditText) findViewById(R.id.editText1);
		txtMessage2 = (EditText) findViewById(R.id.editText2);
		
	    sendBtn = (Button) findViewById(R.id.buttonsend);
	    group0 = (RadioGroup) findViewById(R.id.radioGroup5);
	    group1 = (RadioGroup) findViewById(R.id.radioGroup6);
	    group2 = (RadioGroup) findViewById(R.id.radioGroup7);
	    group3 = (RadioGroup) findViewById(R.id.radioGroup8);
	    group4 = (RadioGroup) findViewById(R.id.radioGroup9);
	    group5 = (RadioGroup) findViewById(R.id.radioGroup10);
	    group6 = (RadioGroup) findViewById(R.id.radioGroup11);
	    group7 = (RadioGroup) findViewById(R.id.radioGroup12);
	   // group8 = (RadioGroup) findViewById(R.id.radioGroup13);眼花不行逆幹
	    group9 = (RadioGroup) findViewById(R.id.radioGroup15);
	    group10 = (RadioGroup) findViewById(R.id.radioGroup16);
	    group11 = (RadioGroup) findViewById(R.id.radioGroup18);
	    group12 = (RadioGroup) findViewById(R.id.radioGroup19);
	    group13 = (RadioGroup) findViewById(R.id.radioGroup20);
	    group14 = (RadioGroup) findViewById(R.id.radioGroup21);
	    group15 = (RadioGroup) findViewById(R.id.radioGroup22);
	    group16 = (RadioGroup) findViewById(R.id.radioGroup23);
	    group17 = (RadioGroup) findViewById(R.id.radioGroup24);
	    group18 = (RadioGroup) findViewById(R.id.radioGroup26);
	    group19 = (RadioGroup) findViewById(R.id.radioGroup27);
	    group20 = (RadioGroup) findViewById(R.id.radioGroup28);
	    group21 = (RadioGroup) findViewById(R.id.radioGroup29);
	    group22 = (RadioGroup) findViewById(R.id.radioGroup30);
	    group23 = (RadioGroup) findViewById(R.id.radioGroup31);
	    group24 = (RadioGroup) findViewById(R.id.radioGroup33);
	    group25 = (RadioGroup) findViewById(R.id.radioGroup34);
	    group26 = (RadioGroup) findViewById(R.id.radioGroup35);
	    group27 = (RadioGroup) findViewById(R.id.radioGroup36);
	    group28 = (RadioGroup) findViewById(R.id.radioGroup37);
	    group29 = (RadioGroup) findViewById(R.id.radioGroup38);
	    group30 = (RadioGroup) findViewById(R.id.radioGroup39);
	    group31 = (RadioGroup) findViewById(R.id.radioGroup40);
	    group32 = (RadioGroup) findViewById(R.id.radioGroup42);
	    group33 = (RadioGroup) findViewById(R.id.radioGroup43);
	    group34 = (RadioGroup) findViewById(R.id.radioGroup44);
	    group35 = (RadioGroup) findViewById(R.id.radioGroup45);
	    group36 = (RadioGroup) findViewById(R.id.radioGroup47);
	    group37 = (RadioGroup) findViewById(R.id.radioGroup48);
	    group38 = (RadioGroup) findViewById(R.id.radioGroup49);
	    group39 = (RadioGroup) findViewById(R.id.radioGroup50);
	    group40 = (RadioGroup) findViewById(R.id.radioGroup51);
	    group41 = (RadioGroup) findViewById(R.id.radioGroup52);
	    group42 = (RadioGroup) findViewById(R.id.radioGroup53);
	    group43 = (RadioGroup) findViewById(R.id.radioGroup54); 
	    group44 = (RadioGroup) findViewById(R.id.radioGroup55);
	    group45 = (RadioGroup) findViewById(R.id.radioGroup57);
	    group46 = (RadioGroup) findViewById(R.id.radioGroup58);
	    group47 = (RadioGroup) findViewById(R.id.radioGroup59);
	    group48 = (RadioGroup) findViewById(R.id.radioGroup60);
	    group49 = (RadioGroup) findViewById(R.id.radioGroup61);
	    group50 = (RadioGroup) findViewById(R.id.radioGroup62);
	    
	    
	    if (sendBtn != null)
	          sendBtn.setOnClickListener(this);
	    
	    group0.setOnCheckedChangeListener(listener0);
	    group1.setOnCheckedChangeListener(listener1);
	    group2.setOnCheckedChangeListener(listener2);
	    group3.setOnCheckedChangeListener(listener3);
	    group4.setOnCheckedChangeListener(listener4);
	    group5.setOnCheckedChangeListener(listener5);
	    group6.setOnCheckedChangeListener(listener6);
	    group7.setOnCheckedChangeListener(listener7);
	   // group8.setOnCheckedChangeListener(listener8);
	    group9.setOnCheckedChangeListener(listener9);
	    group10.setOnCheckedChangeListener(listener10);
	    group11.setOnCheckedChangeListener(listener11);
	    group12.setOnCheckedChangeListener(listener12);
	    group13.setOnCheckedChangeListener(listener13);
	    group14.setOnCheckedChangeListener(listener14);
	    group15.setOnCheckedChangeListener(listener15);
	    group16.setOnCheckedChangeListener(listener16);
	    group17.setOnCheckedChangeListener(listener17);
	    group18.setOnCheckedChangeListener(listener18);
	    group19.setOnCheckedChangeListener(listener19);
	    group20.setOnCheckedChangeListener(listener20);
	    group21.setOnCheckedChangeListener(listener21);
	    group22.setOnCheckedChangeListener(listener22);
	    group23.setOnCheckedChangeListener(listener23);
	    group24.setOnCheckedChangeListener(listener24);
	    group25.setOnCheckedChangeListener(listener25);
	    group26.setOnCheckedChangeListener(listener26);
	    group27.setOnCheckedChangeListener(listener27);
	    group28.setOnCheckedChangeListener(listener28);
	    group29.setOnCheckedChangeListener(listener29);
	    group30.setOnCheckedChangeListener(listener30);
	    group31.setOnCheckedChangeListener(listener31);
	    group32.setOnCheckedChangeListener(listener32);
	    group33.setOnCheckedChangeListener(listener33);
	    group34.setOnCheckedChangeListener(listener34);
	    group35.setOnCheckedChangeListener(listener35);
	    group36.setOnCheckedChangeListener(listener36);
	    group37.setOnCheckedChangeListener(listener37);
	    group38.setOnCheckedChangeListener(listener38);
	    group39.setOnCheckedChangeListener(listener39);
	    group40.setOnCheckedChangeListener(listener40);
	    group41.setOnCheckedChangeListener(listener41);
	    group42.setOnCheckedChangeListener(listener42);
	    group43.setOnCheckedChangeListener(listener43);
	    group44.setOnCheckedChangeListener(listener44);
	    group45.setOnCheckedChangeListener(listener45);
	    group46.setOnCheckedChangeListener(listener46);
	    group47.setOnCheckedChangeListener(listener47);
	    group48.setOnCheckedChangeListener(listener48);
	    group49.setOnCheckedChangeListener(listener49);
	    group50.setOnCheckedChangeListener(listener50);
	}
	
	private RadioGroup.OnCheckedChangeListener listener0 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio50:
				q0 = "完全沒有";
				break;	
			case R.id.radio51:
				q0 = "只有呼吸道受感染時";
				break;
			case R.id.radio52:
				q0 = "一周中有幾天";
				break;
			case R.id.radio53:
				q0 = "一周中有好幾天";
				break;
			case R.id.radio54:
				q0 = "一周中幾乎每天";
				break;
				
			default :
				
				break;
			
			}
		}
	};
	
	private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio60:
				q1 = "完全沒有";
				break;	
			case R.id.radio61:
				q1 = "只有呼吸道受感染時";
				break;
			case R.id.radio62:
				q1 = "一周中有幾天";
				break;
			case R.id.radio63:
				q1 = "一周中有好幾天";
				break;
			case R.id.radio64:
				q1 = "一周中幾乎每天";
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
			case R.id.radio70:
				q2 = "完全沒有";
				break;	
			case R.id.radio71:
				q2 = "只有呼吸道受感染時";
				break;
			case R.id.radio72:
				q2 = "一周中有幾天";
				break;
			case R.id.radio73:
				q2 = "一周中有好幾天";
				break;
			case R.id.radio74:
				q2 = "一周中幾乎每天";
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
			case R.id.radio80:
				q3 = "完全沒有";
				break;	
			case R.id.radio81:
				q3 = "只有呼吸道受感染時";
				break;
			case R.id.radio82:
				q3 = "一周中有幾天";
				break;
			case R.id.radio83:
				q3 = "一周中有好幾天";
				break;
			case R.id.radio84:
				q3 = "一周中幾乎每天";
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
			case R.id.radio90:
				q4 = "從未";
				break;	
			case R.id.radio91:
				q4 = "一次";
				break;
			case R.id.radio92:
				q4 = "兩次";
				break;
			case R.id.radio93:
				q4 = "三次";
				break;
			case R.id.radio94:
				q4 = "超過三次";
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
			case R.id.radio100:
				q5 = "沒有嚴重的呼吸問題發作";
				break;	
			case R.id.radio101:
				q5 = "發作時間少於一天";
				break;
			case R.id.radio102:
				q5 = "發作時間一至二天";
				break;
			case R.id.radio103:
				q5 = "發作時間三天以上";
				break;
			case R.id.radio104:
				q5 = "發作時間一周以上";
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
			case R.id.radio110:
				q6 = "每天都好";
				break;	
			case R.id.radio111:
				q6 = "幾乎每天都好";
				break;	
			case R.id.radio112:
				q6 = "三或四天好(較舒服)的日子";
				break;	
			case R.id.radio113:
				q6 = "一或二天(較舒服)的日子";
				break;	
			case R.id.radio114:
				q6 = "都沒有好日子";
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
			case R.id.radio120:
				q7 = "不是";
				break;	
			case R.id.radio121:
				q7 = "是";
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
			case R.id.radio150:
				q9 = "並未造成我的問題";
				break;	
			case R.id.radio151:
				q9 = "造成我些許(一些)問題";
				break;
			case R.id.radio152:
				q9 = "造成我很多問題";
				break;
			case R.id.radio153:
				q9 = "造成我最重要的問題";
				break;
			default :
				
				break;
			}
		}
	};
	
	
	
	//=======這之後有權重===========================================================================
	
	
	
	
private RadioGroup.OnCheckedChangeListener listener10 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio160:
				q10 = "未作答";
				break;	
			case R.id.radio161:
				q10 = "我的呼吸問題並未影響我的工作";
				break;
			case R.id.radio162:
				q10 = "我的呼吸問題干擾我的工作或令我換工作";
				break;
			case R.id.radio163:
				q10 = "我的呼吸問題使我完全停止工作";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener11 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio180:
				q11 = "未作答";
				break;	
			case R.id.radio181:
				q11 = "否";
				break;
			case R.id.radio182:
				q11 = "是";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener12 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio190:
				q12 = "未作答";
				break;	
			case R.id.radio191:
				q12 = "否";
				break;
			case R.id.radio192:
				q12 = "是";
				break;
			default :
				
				break;
			}
		}
	};
	
	
private RadioGroup.OnCheckedChangeListener listener13 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio200:
				q13 = "未作答";
				break;	
			case R.id.radio201:
				q13 = "否";
				break;
			case R.id.radio202:
				q13 = "是";
				break;
			default :
				
				break;
			}
		}
	};
	
	
private RadioGroup.OnCheckedChangeListener listener14 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio210:
				q14 = "未作答";
				break;	
			case R.id.radio211:
				q14 = "否";
				break;
			case R.id.radio212:
				q14 = "是";
				break;
			default :
				
				break;
			}
		}
	};
	
	
	
private RadioGroup.OnCheckedChangeListener listener15 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio220:
				q15 = "未作答";
				break;	
			case R.id.radio221:
				q15 = "否";
				break;
			case R.id.radio222:
				q15 = "是";
				break;
			default :
				
				break;
			}
		}
	};
	
	
	
private RadioGroup.OnCheckedChangeListener listener16 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio230:
				q16 = "未作答";
				break;	
			case R.id.radio231:
				q16 = "否";
				break;
			case R.id.radio232:
				q16 = "是";
				break;
			default :
				
				break;
			}
		}
	};
	
	
	
private RadioGroup.OnCheckedChangeListener listener17 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio240:
				q17 = "未作答";
				break;	
			case R.id.radio241:
				q17 = "否";
				break;
			case R.id.radio242:
				q17 = "是";
				break;
			default :
				
				break;
			}
		}
	};
	
	
	
private RadioGroup.OnCheckedChangeListener listener18 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio260:
				q18 = "未作答";
				break;	
			case R.id.radio261:
				q18 = "否";
				break;
			case R.id.radio262:
				q18 = "是";
				break;
			default :
				
				break;
			}
		}
	};

private RadioGroup.OnCheckedChangeListener listener19 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio270:
				q19 = "未作答";
				break;	
			case R.id.radio271:
				q19 = "否";
				break;
			case R.id.radio272:
				q19 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener20 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio280:
				q20 = "未作答";
				break;	
			case R.id.radio281:
				q20 = "否";
				break;
			case R.id.radio282:
				q20 = "是";
				break;
			default :
				
				break;
			}
		}
	};
	
private RadioGroup.OnCheckedChangeListener listener21 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio290:
				q21 = "未作答";
				break;	
			case R.id.radio291:
				q21 = "否";
				break;
			case R.id.radio292:
				q21 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener22 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio300:
				q22 = "未作答";
				break;	
			case R.id.radio301:
				q22 = "否";
				break;
			case R.id.radio302:
				q22 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
	
private RadioGroup.OnCheckedChangeListener listener23 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio310:
				q23 = "未作答";
				break;	
			case R.id.radio311:
				q23 = "否";
				break;
			case R.id.radio312:
				q23 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener24 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio330:
				q24 = "未作答";
				break;	
			case R.id.radio331:
				q24 = "否";
				break;
			case R.id.radio332:
				q24 = "是";
				break;
			default :
				
				break;
			}
		}
	};	

	
private RadioGroup.OnCheckedChangeListener listener25 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio340:
				q25 = "未作答";
				break;	
			case R.id.radio341:
				q25 = "否";
				break;
			case R.id.radio342:
				q25 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener26 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio350:
				q26 = "未作答";
				break;	
			case R.id.radio351:
				q26 = "否";
				break;
			case R.id.radio352:
				q26 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener27 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio360:
				q27 = "未作答";
				break;	
			case R.id.radio361:
				q27 = "否";
				break;
			case R.id.radio362:
				q27 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener28 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio370:
				q28 = "未作答";
				break;	
			case R.id.radio371:
				q28 = "否";
				break;
			case R.id.radio372:
				q28 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener29 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio380:
				q29 = "未作答";
				break;	
			case R.id.radio381:
				q29 = "否";
				break;
			case R.id.radio382:
				q29 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener30 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio390:
				q30 = "未作答";
				break;	
			case R.id.radio391:
				q30 = "否";
				break;
			case R.id.radio392:
				q30 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener31 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio400:
				q31 = "未作答";
				break;	
			case R.id.radio401:
				q31 = "否";
				break;
			case R.id.radio402:
				q31 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener32 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio420:
				q32 = "未作答";
				break;	
			case R.id.radio421:
				q32 = "否";
				break;
			case R.id.radio422:
				q32 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
		
private RadioGroup.OnCheckedChangeListener listener33 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio430:
				q33 = "未作答";
				break;	
			case R.id.radio431:
				q33 = "否";
				break;
			case R.id.radio432:
				q33 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
		
private RadioGroup.OnCheckedChangeListener listener34 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio440:
				q34 = "未作答";
				break;	
			case R.id.radio441:
				q34 = "否";
				break;
			case R.id.radio442:
				q34 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener35 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio450:
				q35 = "未作答";
				break;	
			case R.id.radio451:
				q35 = "否";
				break;
			case R.id.radio452:
				q35 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener36 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio470:
				q36 = "未作答";
				break;	
			case R.id.radio471:
				q36 = "否";
				break;
			case R.id.radio472:
				q36 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener37 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio480:
				q37 = "未作答";
				break;	
			case R.id.radio481:
				q37 = "否";
				break;
			case R.id.radio482:
				q37 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener38 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio490:
				q38 = "未作答";
				break;	
			case R.id.radio491:
				q38 = "否";
				break;
			case R.id.radio492:
				q38 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener39 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio500:
				q39 = "未作答";
				break;	
			case R.id.radio501:
				q39 = "否";
				break;
			case R.id.radio502:
				q39 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener40 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio510:
				q40 = "未作答";
				break;	
			case R.id.radio511:
				q40 = "否";
				break;
			case R.id.radio512:
				q40 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener41 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio520:
				q41 = "未作答";
				break;	
			case R.id.radio521:
				q41 = "否";
				break;
			case R.id.radio522:
				q41 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener42 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio530:
				q42 = "未作答";
				break;	
			case R.id.radio531:
				q42 = "否";
				break;
			case R.id.radio532:
				q42 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener43 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio540:
				q43 = "未作答";
				break;	
			case R.id.radio541:
				q43 = "否";
				break;
			case R.id.radio542:
				q43 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
private RadioGroup.OnCheckedChangeListener listener44 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio550:
				q44 = "未作答";
				break;	
			case R.id.radio551:
				q44 = "否";
				break;
			case R.id.radio552:
				q44 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener45 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio570:
				q45 = "未作答";
				break;	
			case R.id.radio571:
				q45 = "否";
				break;
			case R.id.radio572:
				q45 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener46 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio580:
				q46 = "未作答";
				break;	
			case R.id.radio581:
				q46 = "否";
				break;
			case R.id.radio582:
				q46 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener47 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio590:
				q47 = "未作答";
				break;	
			case R.id.radio591:
				q47 = "否";
				break;
			case R.id.radio592:
				q47 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener48 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio600:
				q48 = "未作答";
				break;	
			case R.id.radio601:
				q48 = "否";
				break;
			case R.id.radio602:
				q48 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
private RadioGroup.OnCheckedChangeListener listener49 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio610:
				q49 = "未作答";
				break;	
			case R.id.radio611:
				q49 = "否";
				break;
			case R.id.radio612:
				q49 = "是";
				break;
			default :
				
				break;
			}
		}
	};	
	
	
	
	
private RadioGroup.OnCheckedChangeListener listener50 = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub		
			switch (checkedId) {
			case R.id.radio620:
				q50 = "它不會阻止我從事任何我想要做的事";
				break;	
			case R.id.radio621:
				q50 = "它會阻止我從事一或二項我想要做的事";
				break;
			case R.id.radio622:
				q50 = "它會阻止我從事大部分我想要做的事";
				break;
			case R.id.radio623:
				q50 = "它會阻止我從事每一件我想要做的事";
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
	          
	             // 擷取文字框上的文字
	        	 String q_1= txtMessage1.getEditableText().toString();
		         String q_2 = txtMessage2.getEditableText().toString();
	             String q0_ = q0;
	             String q1_ = q1;
	             String q2_ = q2;
	             String q3_ = q3;
	             String q4_ = q4;
	             String q5_ = q5;
	             String q6_ = q6;
	             String q7_ = q7;
	             //String q8_ = q8;
	             String q9_ = q9;
	             String q10_ = q10;
	             String q11_ = q11;
	             String q12_ = q12;
	             String q13_ = q13;
	             String q14_ = q14;
	             String q15_ = q15;
	             String q16_ = q16;
	             String q17_ = q17;
	             String q18_ = q18;
	             String q19_ = q19;
	             String q20_ = q20;
	             String q21_ = q21;
	             String q22_ = q22;
	             String q23_ = q23;
	             String q24_ = q24;
	             String q25_ = q25;
	             String q26_ = q26;
	             String q27_ = q27;
	             String q28_ = q28;
	             String q29_ = q29;
	             String q30_ = q30;
	             String q31_ = q31;
	             String q32_ = q32;
	             String q33_ = q33;
	             String q34_ = q34;
	             String q35_ = q35;
	             String q36_ = q36;
	             String q37_ = q37;
	             String q38_ = q38;
	             String q39_ = q39;
	             String q40_ = q40;
	             String q41_ = q41;
	             String q42_ = q42;
	             String q43_ = q43;
	             String q44_ = q44;
	             String q45_ = q45;
	             String q46_ = q46;
	             String q47_ = q47;
	             String q48_ = q48;
	             String q49_ = q49;
	             String q50_ = q50;
	        
	            
	             // 啟動一個Thread(執行緒)，將要傳送的資料放進Runnable中，讓Thread執行
	             Thread t = new Thread(new sendPostRunnable(q_1,q_2,q0_,q1_,q2_,q3_,q4_,q5_,q6_,q7_,q9_,q10_,q11_,q12_,q13_,q14_,q15_,q16_,q17_,q18_,q19_,q20_,q21_,q22_,q23_,q24_,q25_,
	            		 q26_,q27_,q28_,q29_,q30_,q31_,q32_,q33_,q34_,q35_,q36_,q37_,q38_,q39_,q40_,q41_,q42_,q43_,q44_,q45_,q46_,q47_,q48_,q49_,q50_));
	             t.start();
	             
	             Intent intent = new Intent();
	             intent.setClass(QuesCOPDSGRQ.this, Menu_v1.class);
	             startActivity(intent);
	      }
	   }

	private String sendPostDataToInternet(String strTxt_0,String strTxt_1,String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt09,
  		  String strTxt10,String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15,String strTxt16,String strTxt17,String strTxt18,String strTxt19,
  		  String strTxt20,String strTxt21,String strTxt22,String strTxt23,String strTxt24,String strTxt25,String strTxt26,String strTxt27,String strTxt28,String strTxt29,
  		  String strTxt30,String strTxt31,String strTxt32,String strTxt33,String strTxt34,String strTxt35,String strTxt36,String strTxt37,String strTxt38,String strTxt39,
  		  String strTxt40,String strTxt41,String strTxt42,String strTxt43,String strTxt44,String strTxt45,String strTxt46,String strTxt47,String strTxt48,String strTxt49,
  		  String strTxt50)
	
		{
	      /* 建立HTTP Post連線 */
	      HttpPost httpRequest = new HttpPost(uriAPI);
	      /*
	       * Post運作傳送變數必須用NameValuePair[]陣列儲存
	       */
	      List<NameValuePair> params = new ArrayList<NameValuePair>();
	      params.add(new BasicNameValuePair("Q_0", strTxt_0));
	      params.add(new BasicNameValuePair("Q_1", strTxt_1));
	      params.add(new BasicNameValuePair("Q0", strTxt0));
	      params.add(new BasicNameValuePair("Q1", strTxt01));
	      params.add(new BasicNameValuePair("Q2", strTxt02));
	      params.add(new BasicNameValuePair("Q3", strTxt03));
	      params.add(new BasicNameValuePair("Q4", strTxt04));
	      params.add(new BasicNameValuePair("Q5", strTxt05));
	      params.add(new BasicNameValuePair("Q6", strTxt06));
	      params.add(new BasicNameValuePair("Q7", strTxt07));
	      
	      params.add(new BasicNameValuePair("Q9", strTxt09));
	      params.add(new BasicNameValuePair("Q10", strTxt10));
	      params.add(new BasicNameValuePair("Q11", strTxt11));
	      params.add(new BasicNameValuePair("Q12", strTxt12));
	      params.add(new BasicNameValuePair("Q13", strTxt13));
	      params.add(new BasicNameValuePair("Q14", strTxt14));
	      params.add(new BasicNameValuePair("Q15", strTxt15));
	      params.add(new BasicNameValuePair("Q16", strTxt16));
	      params.add(new BasicNameValuePair("Q17", strTxt17));
	      params.add(new BasicNameValuePair("Q18", strTxt18));
	      params.add(new BasicNameValuePair("Q19", strTxt19));
	      params.add(new BasicNameValuePair("Q20", strTxt20));
	      params.add(new BasicNameValuePair("Q21", strTxt21));
	      params.add(new BasicNameValuePair("Q22", strTxt22));
	      params.add(new BasicNameValuePair("Q23", strTxt23));
	      params.add(new BasicNameValuePair("Q24", strTxt24));
	      params.add(new BasicNameValuePair("Q25", strTxt25));
	      params.add(new BasicNameValuePair("Q26", strTxt26));
	      params.add(new BasicNameValuePair("Q27", strTxt27));
	      params.add(new BasicNameValuePair("Q28", strTxt28));
	      params.add(new BasicNameValuePair("Q29", strTxt29));
	      params.add(new BasicNameValuePair("Q30", strTxt30));
	      params.add(new BasicNameValuePair("Q31", strTxt31));
	      params.add(new BasicNameValuePair("Q32", strTxt32));
	      params.add(new BasicNameValuePair("Q33", strTxt33));
	      params.add(new BasicNameValuePair("Q34", strTxt34));
	      params.add(new BasicNameValuePair("Q35", strTxt35));
	      params.add(new BasicNameValuePair("Q36", strTxt36));
	      params.add(new BasicNameValuePair("Q37", strTxt37));
	      params.add(new BasicNameValuePair("Q38", strTxt38));
	      params.add(new BasicNameValuePair("Q39", strTxt39));
	      params.add(new BasicNameValuePair("Q40", strTxt40));
	      params.add(new BasicNameValuePair("Q41", strTxt41));
	      params.add(new BasicNameValuePair("Q42", strTxt42));
	      params.add(new BasicNameValuePair("Q43", strTxt43));
	      params.add(new BasicNameValuePair("Q44", strTxt44));
	      params.add(new BasicNameValuePair("Q45", strTxt45));
	      params.add(new BasicNameValuePair("Q46", strTxt46));
	      params.add(new BasicNameValuePair("Q47", strTxt47));
	      params.add(new BasicNameValuePair("Q48", strTxt48));
	      params.add(new BasicNameValuePair("Q49", strTxt49));
	      params.add(new BasicNameValuePair("Q50", strTxt50));
	 
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
		  String strTxt_0 = null;
		  String strTxt_1 = null;
		  String strTxt0 = null;
	      String strTxt01 = null;
	      String strTxt02 = null;
	      String strTxt03 = null;
	      String strTxt04 = null;
	      String strTxt05 = null;
	      String strTxt06 = null;
	      String strTxt07 = null;
	   
	      String strTxt09 = null;
	      String strTxt10 = null;
	      String strTxt11 = null;
	      String strTxt12 = null;
	      String strTxt13 = null;
	      String strTxt14 = null;
	      String strTxt15 = null;
	      String strTxt16 = null;
	      String strTxt17 = null;
	      String strTxt18 = null;
	      String strTxt19 = null;
	      String strTxt20 = null;
	      String strTxt21 = null;
	      String strTxt22 = null;
	      String strTxt23 = null;
	      String strTxt24 = null;
	      String strTxt25 = null;
	      String strTxt26 = null;
	      String strTxt27 = null;
	      String strTxt28 = null;
	      String strTxt29 = null;
	      String strTxt30 = null;
	      String strTxt31 = null;
	      String strTxt32 = null;
	      String strTxt33 = null;
	      String strTxt34 = null;
	      String strTxt35 = null;
	      String strTxt36 = null;
	      String strTxt37 = null;
	      String strTxt38 = null;
	      String strTxt39 = null;
	      String strTxt40 = null;
	      String strTxt41 = null;
	      String strTxt42 = null;
	      String strTxt43 = null;
	      String strTxt44 = null;
	      String strTxt45 = null;
	      String strTxt46 = null;
	      String strTxt47 = null;
	      String strTxt48 = null;
	      String strTxt49 = null;
	      String strTxt50 = null;

		   
	      	 
	      // 建構子，設定要傳的字串
	      public sendPostRunnable(String strTxt_0,String strTxt_1,String strTxt0,String strTxt01,String strTxt02,String strTxt03,String strTxt04,String strTxt05,String strTxt06,String strTxt07,String strTxt09,
	      		  String strTxt10,String strTxt11,String strTxt12,String strTxt13,String strTxt14,String strTxt15,String strTxt16,String strTxt17,String strTxt18,String strTxt19,
	      		  String strTxt20,String strTxt21,String strTxt22,String strTxt23,String strTxt24,String strTxt25,String strTxt26,String strTxt27,String strTxt28,String strTxt29,
	      		  String strTxt30,String strTxt31,String strTxt32,String strTxt33,String strTxt34,String strTxt35,String strTxt36,String strTxt37,String strTxt38,String strTxt39,
	      		  String strTxt40,String strTxt41,String strTxt42,String strTxt43,String strTxt44,String strTxt45,String strTxt46,String strTxt47,String strTxt48,String strTxt49,
	      		  String strTxt50)
	      {
	    	 this.strTxt_0 = strTxt_0;
	    	 this.strTxt_1 = strTxt_1;
	    	 this.strTxt0 = strTxt0;
	         this.strTxt01 = strTxt01;
	         this.strTxt02 = strTxt02;
	         this.strTxt03 = strTxt03;
	         this.strTxt04 = strTxt04;
	         this.strTxt05 = strTxt05;
	         this.strTxt06 = strTxt06;
	         this.strTxt07 = strTxt07;
	        
	         this.strTxt09 = strTxt09;
	         this.strTxt10 = strTxt10;
	         this.strTxt11 = strTxt11;
	         this.strTxt12 = strTxt12;
	         this.strTxt13 = strTxt13;
	         this.strTxt14 = strTxt14;
	         this.strTxt15 = strTxt15;
	         this.strTxt16 = strTxt16;
	         this.strTxt17 = strTxt17;
	         this.strTxt18 = strTxt18;
	         this.strTxt19 = strTxt19;
	         this.strTxt20 = strTxt20;
	         this.strTxt21 = strTxt21;
	         this.strTxt22 = strTxt22;
	         this.strTxt23 = strTxt23;
	         this.strTxt24 = strTxt24;
	         this.strTxt25 = strTxt25;
	         this.strTxt26 = strTxt26;
	         this.strTxt27 = strTxt27;
	         this.strTxt28 = strTxt28;
	         this.strTxt29 = strTxt29;
	         this.strTxt30 = strTxt30;
	         this.strTxt31 = strTxt31;
	         this.strTxt32 = strTxt32;
	         this.strTxt33 = strTxt33;
	         this.strTxt34 = strTxt34;
	         this.strTxt35 = strTxt35;
	         this.strTxt36 = strTxt36;
	         this.strTxt37 = strTxt37;
	         this.strTxt38 = strTxt38;
	         this.strTxt39 = strTxt39;
	         this.strTxt40 = strTxt40;
	         this.strTxt41 = strTxt41;
	         this.strTxt42 = strTxt42;
	         this.strTxt43 = strTxt43;
	         this.strTxt44 = strTxt44;
	         this.strTxt45 = strTxt45;
	         this.strTxt46 = strTxt46;
	         this.strTxt47 = strTxt47;
	         this.strTxt48 = strTxt48;
	         this.strTxt49 = strTxt49;
	         this.strTxt50 = strTxt50;   
	      }
	 
	      @Override
	      public void run()
	      {
	          String result = sendPostDataToInternet(strTxt_0,strTxt_1,strTxt0,strTxt01, strTxt02, strTxt03, strTxt04, strTxt05, strTxt06, strTxt07,strTxt09,
		    		   strTxt10, strTxt11, strTxt12, strTxt13, strTxt14, strTxt15, strTxt16, strTxt17, strTxt18, strTxt19,
		    		   strTxt20, strTxt21, strTxt22, strTxt23,  strTxt24,  strTxt25,  strTxt26,  strTxt27,  strTxt28,  strTxt29,
		      		    strTxt30,  strTxt31,  strTxt32,  strTxt33,  strTxt34,  strTxt35,  strTxt36,  strTxt37,  strTxt38,  strTxt39,
		      		    strTxt40,  strTxt41,  strTxt42,  strTxt43,  strTxt44,  strTxt45,  strTxt46,  strTxt47,  strTxt48,  strTxt49,
		      		    strTxt50);
	          
	          mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
	          
	      }
	   }
}
