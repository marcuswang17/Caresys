package com.caresys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.xml.sax.SAXException;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;
 
//繼承android.app.Service
public class background extends Service implements LocationListener{
	
	SharedPreferences uploadground;
	private String groundpath,groundname,uploadgroundpath,uploadgroundname,grounddate,uploadgrounddate;
	
    private Handler handler = new Handler();
  //宣告定位管理控制
  	private LocationManager mLocationManager;
  	//建立List，屬性為Poi物件
  	private ArrayList<Poi> Pois = new ArrayList<Poi>();
  	private ArrayList<Poiw> Poiws = new ArrayList<Poiw>();
  	private ArrayList<PoiInfo> PoiInfos = new ArrayList<PoiInfo>();
  	
  	Intent intent;

  	URL url = null;
  	
  	protected static final int REFRESH_DATA = 0x00000001;
  	
  	String trgUrl="http://opendata.cwb.gov.tw/opendata/DIV2/O-A0003-001.xml";
  	String Url = null;
  	
  	Weather[] Arr_Weather;
    Weather nearest;
    
    WeatherInfo[] Arr_WeatherInfo;
    WeatherInfo wi;
    
    String[]Locw =new String[10];
    String[]H_24R =new String[10];
    String[]HUMD =new String[10];
    String[]Lat =new String[10];
    String LatBuf;
    String[]Long =new String[10];
    String LongBuf;
    String[]Dis =new String[10];
    String DisBuf;
    String[]Disw =new String[10];
    String DiswBuf;
    String[]Loc =new String[10];
    String LocBuf;
	String[]PSI =new String[10];
	String PSIBuf;
	String[]Major =new String[10];
	String MajorBuf;
	String[]Loci = new String[10];
	String[]Info = new String[10];
	String[]time = new String[10];
	String[]A =new String[10];
    String a = "";
    String warning = "";
    int i = 0;
    int count = 0;
    Time mTime = new Time();
    File envFile = null;
    File aFile;
    FileWriter mFileWriter;
    
    Boolean check1 = false;
    Boolean check2 = false;
    Boolean check3 = false;
 
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    public void onCreate() { 
    	super.onCreate();
    	
    	uploadground = getSharedPreferences("uploadground",MODE_PRIVATE);
    	groundpath = uploadground.getString("groundfilepath","");
    	groundname = uploadground.getString("groundfilename","");
    	grounddate = uploadground.getString("groundfiledate","");
    	
    	Pois.add(new Poi("二林" , 120.409653 , 23.925175 ));
        Pois.add(new Poi("三重" , 121.493806 , 25.072611 ));
        Pois.add(new Poi("三義" , 120.758833 , 24.382942 ));
        Pois.add(new Poi("土城" , 121.451861 , 24.982528 ));
        Pois.add(new Poi("士林" , 121.515389 , 25.105417 ));
        Pois.add(new Poi("大同" , 121.513311 , 25.0632 ));
        Pois.add(new Poi("大里" , 120.677689 , 24.099611 ));
        Pois.add(new Poi("大園" , 121.201811 , 25.060344 ));
        Pois.add(new Poi("大寮" , 120.425081 , 22.565747 ));
        Pois.add(new Poi("小港" , 120.337736 , 22.565833 ));
        Pois.add(new Poi("中山" , 121.526528 , 25.062361 ));
        Pois.add(new Poi("中壢" , 121.221667 , 24.953278 ));
        Pois.add(new Poi("仁武" , 120.332631 , 22.689056 ));
        Pois.add(new Poi("斗六" , 120.544994 , 23.711853 ));
        Pois.add(new Poi("冬山" , 121.793872 , 24.633436 ));
        Pois.add(new Poi("古亭" , 121.529556 , 25.020608 ));
        Pois.add(new Poi("左營" , 120.292917 , 22.674861 ));
        Pois.add(new Poi("平鎮" , 121.203986 , 24.952786 ));
        Pois.add(new Poi("永和" , 121.516306 , 25.017 ));
        Pois.add(new Poi("安南" , 120.2175 , 23.048197 ));
        Pois.add(new Poi("朴子" , 120.24735 , 23.465308 ));
        Pois.add(new Poi("汐止" , 121.6423 , 25.067131 ));
        Pois.add(new Poi("竹山" , 120.677306 , 23.756389 ));
        Pois.add(new Poi("竹東" , 121.088903 , 24.740644 ));
        Pois.add(new Poi("西屯" , 120.616917 , 24.162197 ));
        Pois.add(new Poi("沙鹿" , 120.568794 , 24.225628 ));
        Pois.add(new Poi("宜蘭" , 121.746394 , 24.747917 ));
        Pois.add(new Poi("忠明" , 120.641092 , 24.151958 ));
        Pois.add(new Poi("松山" , 121.578556 , 25.050694 ));
        Pois.add(new Poi("板橋" , 121.458667 , 25.012972 ));
        Pois.add(new Poi("林口" , 121.376869 , 25.077197 ));
        Pois.add(new Poi("林園" , 120.41175 , 22.4795 ));
        Pois.add(new Poi("花蓮" , 121.599769 , 23.971956 ));
        Pois.add(new Poi("金門" , 118.312256 , 24.432133 ));
        Pois.add(new Poi("前金" , 120.288086 , 22.632567 ));
        Pois.add(new Poi("前鎮" , 120.307564 , 22.605386 ));
        Pois.add(new Poi("南投" , 120.685306 , 23.913 ));
        Pois.add(new Poi("屏東" , 120.488033 , 22.673081 ));
        Pois.add(new Poi("恆春" , 120.788928 , 21.958069 ));
        Pois.add(new Poi("美濃" , 120.530542 , 22.883583 ));
        Pois.add(new Poi("苗栗" , 120.8202 , 24.565269 ));
        Pois.add(new Poi("埔里" , 120.967903 , 23.968842 ));
        Pois.add(new Poi("桃園" , 121.319964 , 24.994789 ));
        Pois.add(new Poi("馬公" , 119.566158 , 23.569031 ));
        Pois.add(new Poi("馬祖" , 119.949875 , 26.160469 ));
        Pois.add(new Poi("基隆" , 121.760056 , 25.129167 ));
        Pois.add(new Poi("崙背" , 120.348742 , 23.757547 ));
        Pois.add(new Poi("淡水" , 121.449239 , 25.1645 ));
        Pois.add(new Poi("麥寮" , 120.251825 , 23.753506 ));
        Pois.add(new Poi("善化" , 120.297142 , 23.115097 ));
        Pois.add(new Poi("復興" , 120.312017 , 22.608711 ));
        Pois.add(new Poi("湖口" , 121.038653 , 24.900142 ));
        Pois.add(new Poi("菜寮" , 121.481028 , 25.06895 ));
        Pois.add(new Poi("陽明" , 121.529583 , 25.182722 ));
        Pois.add(new Poi("新竹" , 120.972075 , 24.805619 ));
        Pois.add(new Poi("新店" , 121.537778 , 24.977222 ));
        Pois.add(new Poi("新莊" , 121.4325 , 25.037972 ));
        Pois.add(new Poi("新港" , 120.345531 , 23.554839 ));
        Pois.add(new Poi("新營" , 120.31725 , 23.305633 ));
        Pois.add(new Poi("楠梓" , 120.328289 , 22.733667 ));
        Pois.add(new Poi("萬里" , 121.689881 , 25.179667 ));
        Pois.add(new Poi("萬華" , 121.507972 , 25.046503 ));
        Pois.add(new Poi("嘉義" , 120.438367 , 23.464789 ));
        Pois.add(new Poi("彰化" , 120.541519 , 24.066 ));
        Pois.add(new Poi("臺西" , 120.202842 , 23.717533 ));
        Pois.add(new Poi("臺東" , 121.15045 , 22.755358 ));
        Pois.add(new Poi("臺南" , 120.202617 , 22.984581 ));
        Pois.add(new Poi("鳳山" , 120.358083 , 22.627392 ));
        Pois.add(new Poi("潮州" , 120.561175 , 22.523108 ));
        Pois.add(new Poi("線西" , 120.469061 , 24.131672 ));
        Pois.add(new Poi("橋頭" , 120.305689 , 22.757506 ));
        Pois.add(new Poi("頭份" , 120.898572 , 24.696969 ));
        Pois.add(new Poi("龍潭" , 121.21635 , 24.863869 ));
        Pois.add(new Poi("豐原" , 120.741711 , 24.256586 ));
        Pois.add(new Poi("關山" , 121.161933 , 23.045083 ));
        Pois.add(new Poi("觀音" , 121.082761 , 25.035503 ));
         
        Poiws.add(new Poiw("基隆" , 25.1348 , 121.7321));
   	    Poiws.add(new Poiw("淡水" , 25.1656 , 121.44));
   	    Poiws.add(new Poiw("板橋" , 24.9993 , 121.4338));
   	    Poiws.add(new Poiw("竹子湖" , 25.165 , 121.5363));
        Poiws.add(new Poiw("新竹" , 24.83 , 121.0061));
        Poiws.add(new Poiw("臺中" , 24.1475 , 120.6759));
        Poiws.add(new Poiw("梧棲" , 24.2587 , 120.5151));
        Poiws.add(new Poiw("澎湖" , 23.5672 , 119.5552));
        Poiws.add(new Poiw("日月潭" , 23.883 , 120.8999));
        Poiws.add(new Poiw("阿里山" , 23.5104 , 120.8051));
        Poiws.add(new Poiw("玉山" , 23.4893 , 120.9517));
        Poiws.add(new Poiw("嘉義" , 23.4977 , 120.4245));
        Poiws.add(new Poiw("高雄" , 22.5679 , 120.308));
        Poiws.add(new Poiw("恆春" , 22.0054 , 120.7381));
        Poiws.add(new Poiw("宜蘭" , 24.7656 , 121.7479));
        Poiws.add(new Poiw("蘇澳" , 24.6017 , 121.8644));
        Poiws.add(new Poiw("花蓮" , 23.977 , 121.605));
        Poiws.add(new Poiw("成功" , 23.0992 , 121.3654));
        Poiws.add(new Poiw("臺東" , 22.754 , 121.1465));
        Poiws.add(new Poiw("大武" , 22.3576 , 120.8957));
        Poiws.add(new Poiw("蘭嶼" , 22.0387 , 121.5506));
        Poiws.add(new Poiw("彭佳嶼" , 25.6294 , 122.0713));
        Poiws.add(new Poiw("東吉島" , 23.259 , 119.6596));
        Poiws.add(new Poiw("新店" , 24.9608 , 121.5165));
        Poiws.add(new Poiw("臺北" , 25.0396 , 121.5067));
        Poiws.add(new Poiw("臺南" , 22.9952 , 120.197));
        Poiws.add(new Poiw("東沙" , 20.42 , 116.43));
        Poiws.add(new Poiw("金門" , 24.4074 , 118.2893));
        Poiws.add(new Poiw("馬祖" , 26.1694 , 119.9232));
        Poiws.add(new Poiw("新屋" , 25.0067 , 121.0475));
        
        PoiInfos.add(new PoiInfo("台北市中正區" , 121.526611 , 25.021568 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市大同區" , 121.515458 , 25.066028 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市中山區" , 121.5335067 , 25.0644073 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市松山區" , 121.556008 , 25.0486 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市大安區" , 121.538397 , 25.037911 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市萬華區" , 121.4978839 , 25.0294935 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市信義區" , 121.542022 , 25.034108 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市士林區" , 121.51967 , 25.092808 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市北投區" , 121.502691 , 25.132672 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市內湖區" , 121.588898 , 25.069374 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市南港區" , 121.592271 , 25.054501 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("台北市文山區" , 121.569962 , 24.989558 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-009.xml"));
        PoiInfos.add(new PoiInfo("新北市萬里區" , 121.6489393 , 25.1763969 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市金山區" , 121.6064228 , 25.219039 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市板橋區" , 121.4627576 , 25.0130994 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市汐止區" , 121.6627857 , 25.0837541 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市深坑區" , 121.6206943 , 24.9974469 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市石碇區" , 121.6465734 , 24.9496777 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市瑞芳區" , 121.8373634 , 25.0890857 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市平溪區" , 121.7589534 , 25.0256894 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市雙溪區" , 121.8304425 , 24.9985175 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市貢寮區" , 121.9131761 , 25.0178293 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市新店區" , 121.5339701 , 24.9283855 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市坪林區" , 121.7423434 , 24.9207611 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市烏來區" , 121.5654039 , 24.7834696 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市永和區" , 121.5170166 , 25.0083253 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市中和區" , 121.4970294 , 24.994446 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市土城區" , 121.444696 , 24.96598 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市三峽區" , 121.4073846 , 24.8711008 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市樹林區" , 121.4056153 , 24.9840028 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市鶯歌區" , 121.3427262 , 24.9614967 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市三重區" , 121.4860739 , 25.0666224 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市新莊區" , 121.4284756 , 25.0211328 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市泰山區" , 121.43278 , 25.061103 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市林口區" , 121.348854 , 25.0997426 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市蘆洲區" , 121.4713265 , 25.088044 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市五股區" , 121.4354923 , 25.0965186 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市八里區" , 121.417046 , 25.1406052 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市淡水區" , 121.4637702 , 25.1836779 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市三芝區" , 121.507491 , 25.2302076 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("新北市石門區" , 121.5639843 , 25.2573316 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-010.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣宜蘭市" , 121.7586106 , 24.7488791 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣頭城鎮" , 121.8676464 , 24.9440106 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣礁溪鄉" , 121.7309452 , 24.8068769 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣壯圍鄉" , 121.7993168 , 24.769569 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣員山鄉" , 121.662532 , 24.7381293 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣羅東鎮" , 121.7708337 , 24.675606 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣三星鄉" , 121.653518 , 24.666513 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣大同鄉" , 121.5141615 , 24.5498206 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣五結鄉" , 121.8050125 , 24.6887633 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣冬山鄉" , 121.7528747 , 24.6465605 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣蘇澳鎮" , 121.8448745 , 24.568141 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("宜蘭縣南澳鄉" , 121.6739371 , 24.4065995 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        PoiInfos.add(new PoiInfo("基隆市仁愛區" , 121.736754 , 25.125069 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-011.xml"));
        PoiInfos.add(new PoiInfo("基隆市信義區" , 121.752571 , 25.12938 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-011.xml"));
        PoiInfos.add(new PoiInfo("基隆市中正區" , 121.7747335 , 25.142764 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-011.xml"));
        PoiInfos.add(new PoiInfo("基隆市中山區" , 121.7295058 , 25.1475559 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-011.xml"));
        PoiInfos.add(new PoiInfo("基隆市安樂區" , 121.723251 , 25.121002 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-011.xml"));
        PoiInfos.add(new PoiInfo("基隆市暖暖區" , 121.748042 , 25.0836163 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-011.xml"));
        PoiInfos.add(new PoiInfo("基隆市七堵區" , 121.713235 , 25.095836 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-011.xml"));
        PoiInfos.add(new PoiInfo("桃園縣中壢市" , 121.2091898 , 24.9767833 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣平鎮市" , 121.2176512 , 24.9218598 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣龍潭鄉" , 121.2088865 , 24.846696 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣楊梅鎮" , 121.1232997 , 24.9156284 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣新屋鄉" , 121.0751762 , 24.9762761 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣觀音鄉" , 121.0988572 , 25.0220719 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣桃園市" , 121.2959896 , 24.9971708 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣龜山鄉" , 121.3528726 , 25.0214525 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣八德市" , 121.2912463 , 24.9469059 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣大溪鎮" , 121.3007677 , 24.871684 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣復興鄉" , 121.362266 , 24.7161946 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣大園鄉" , 121.193945 , 25.0492632 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("桃園縣蘆竹鄉" , 121.2936461 , 25.0544634 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-022.xml"));
        PoiInfos.add(new PoiInfo("新竹市東區" , 120.973262 , 24.805203 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-024.xml"));
        PoiInfos.add(new PoiInfo("新竹市北區" , 120.970238 , 24.816329 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-024.xml"));
        PoiInfos.add(new PoiInfo("新竹市香山區" , 120.941965 , 24.797016 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-024.xml"));
        PoiInfos.add(new PoiInfo("新竹縣竹北市" , 120.9933678 , 24.8346871 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣湖口鄉" , 121.0573479 , 24.8963312 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣新豐鄉" , 120.9987838 , 24.9039214 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣新埔鎮" , 121.0908329 , 24.8496061 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣關西鎮" , 121.1980805 , 24.7848374 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣芎林鄉" , 121.1080244 , 24.765792 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣寶山鄉" , 121.0026014 , 24.7422987 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣竹東鎮" , 121.0746233 , 24.7210224 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣五峰鄉" , 121.1385269 , 24.5841438 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣橫山鄉" , 121.1509657 , 24.7058223 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣尖石鄉" , 121.2821103 , 24.5964144 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣北埔鄉" , 121.065197 , 24.6726287 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("新竹縣峨眉鄉" , 121.0067497 , 24.672365 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-023.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣竹南鎮" , 120.8786026 , 24.7009219 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣頭份鎮" , 120.9265347 , 24.6692386 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣三灣鄉" , 120.9537359 , 24.6312524 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣南庄鄉" , 121.015663 , 24.5691731 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣獅潭鄉" , 120.9196242 , 24.5228623 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣後龍鎮" , 120.776541 , 24.6148772 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣通霄鎮" , 120.7064451 , 24.4891473 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣苑裡鎮" , 120.685395 , 24.4081517 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣苗栗市" , 120.8117496 , 24.5589086 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣造橋鄉" , 120.8814198 , 24.6278661 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣頭屋鄉" , 120.8843434 , 24.5769291 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣公館鄉" , 120.8593811 , 24.4979567 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣大湖鄉" , 120.8475471 , 24.3983003 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣泰安鄉" , 121.0826519 , 24.4046954 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣銅鑼鄉" , 120.7980923 , 24.4609013 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣三義鄉" , 120.7736498 , 24.3793779 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣西湖鄉" , 120.7583175 , 24.5343657 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("苗栗縣卓蘭鎮" , 120.8433671 , 24.3267833 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-020.xml"));
        PoiInfos.add(new PoiInfo("台中市中區" , 120.6818181 , 24.1402601 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市東區" , 120.6976809 , 24.1372161 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市南區" , 120.6602534 , 24.1196339 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市西區" , 120.6631289 , 24.1430604 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市北區" , 120.6832556 , 24.1573171 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市北屯區" , 120.7300926 , 24.1879867 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市西屯區" , 120.6262448 , 24.1845592 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市南屯區" , 120.6147166 , 24.140194 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市太平區" , 120.768669 , 24.104841 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市大里區" , 120.6961552 , 24.0968671 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市霧峰區" , 120.7222266 , 24.0454226 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市烏日區" , 120.6224444 , 24.0822291 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市豐原區" , 120.7352642 , 24.2502093 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市后里區" , 120.7070642 , 24.3183325 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市石岡區" , 120.7867129 , 24.2625616 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市東勢區" , 120.8270067 , 24.2422363 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市和平區" , 121.1581499 , 24.2745407 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市新社區" , 120.8322397 , 24.1775553 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市潭子區" , 120.7062535 , 24.2163612 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市大雅區" , 120.6395727 , 24.2303415 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市神岡區" , 120.6723024 , 24.2635506 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市大肚區" , 120.5505795 , 24.1426275 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市沙鹿區" ,  120.5899158 , 24.2307866 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市龍井區" , 120.5293592 , 24.2302427 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市梧棲區" , 120.5300963 , 24.2478093 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市清水區" ,  120.5667287 , 24.2950782 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市大甲區" , 120.571217 , 24.194323 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市外埔區" , 120.6429991 , 24.1501874 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("台中市大安區" , 120.5912217 , 24.3702355 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-021.xml"));
        PoiInfos.add(new PoiInfo("彰化縣彰化市" , 120.5628336 , 24.0758685 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣芬園鄉" , 120.6353135 , 23.9995265 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣花壇鄉" , 120.5612631 , 24.0291741 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣秀水鄉" , 120.5068325 , 24.0342979 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣鹿港鎮" , 120.428604 , 24.083388 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣福興鄉" , 120.4352412 , 24.0268049 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣線西鄉" , 120.4530456 , 24.1331612 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣和美鎮" , 120.5134965 , 24.1189036 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣伸港鄉" , 120.4790659 , 24.1651392 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣員林鎮" , 120.5908231 , 23.9568517 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣社頭鄉" , 120.6009755 , 23.9057947 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣永靖鄉" , 120.5451797 , 23.9202279 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣埔心鄉" , 120.5365862 , 23.9559994 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣溪湖鎮" , 120.4804099 , 23.9517401 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣大村鄉" , 120.5583613 , 23.9922359 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣埔鹽鄉" , 120.4619711 , 23.9932346 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣田中鎮" , 120.589777 , 23.8571283 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣北斗鎮" , 120.5361636 , 23.8679477 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣田尾鄉" , 120.5221524 , 23.9038461 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣埤頭鄉" , 120.4742228 , 23.8785117 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣溪州鄉" , 120.5287905 , 23.8320309 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣竹塘鄉" , 120.4121758 , 23.8524464 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣二林鎮" , 120.4042855 , 23.9260064 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣大城鄉" , 120.3128119 , 23.857811 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣芳苑鄉" , 120.3529131 , 23.9586013 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("彰化縣二水鄉" , 120.6329143 , 23.8137907 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-028.xml"));
        PoiInfos.add(new PoiInfo("南投縣南投市" , 120.6808094 , 23.9217644 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣中寮鄉" , 120.781039 , 23.9083812 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣草屯鎮" , 120.7387545 , 23.9824733 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣國姓鄉" , 120.8684227 , 24.0102423 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣埔里鎮" , 120.9646866 , 23.9932872 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣仁愛鄉" , 121.1252135 , 24.0213745 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣名間鄉" , 120.6764056 , 23.8458028 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣集集鎮" , 120.7866621 , 23.8376137 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣水里鄉" , 120.873597 , 23.8091815 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣魚池鄉" , 120.9187827 , 23.8753852 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣信義鄉" , 120.9876321 , 23.6679756 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣竹山鎮" , 120.7269641 , 23.7152608 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("南投縣鹿谷鄉" , 120.7771592 , 23.7336729 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-026.xml"));
        PoiInfos.add(new PoiInfo("雲林縣斗南鎮" , 120.4782047 , 23.6772467 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣大埤鄉" , 120.4204898 , 23.6435131 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣虎尾鎮" , 120.436587 , 23.7078018 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣土庫鎮" , 120.3685326 , 23.6960666 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣褒忠鄉" , 120.310928 , 23.7152747 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣東勢鄉" , 120.2624463 , 23.6990235 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣台西鄉" , 120.2011833 , 23.7105486 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣崙背鄉" , 120.3414512 , 23.7816346 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣麥寮鄉" , 120.2318071 , 23.7878962 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣斗六市" , 120.5717192 , 23.7052606 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣林內鄉" , 120.6132376 , 23.7524077 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣古坑鄉" , 120.6210789 , 23.633158 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣莿桐鄉" , 120.5354096 , 23.7654099 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣西螺鎮" , 120.4650059 , 23.7794766 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣二崙鄉" , 120.3981784 , 23.7872258 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣北港鎮" , 120.2994101 , 23.5891249 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣水林鄉" , 120.2301377 , 23.5689751 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣口湖鄉" , 120.1739975 , 23.5733306 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣四湖鄉" , 120.2078503 , 23.644524 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("雲林縣元長鄉" , 120.3310854 , 23.6439258 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-029.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣番路鄉" , 120.5889002 , 23.4189673 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣梅山鄉" , 120.6306443 , 23.5475509 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣竹崎鄉" , 120.5994896 , 23.5015232 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣阿里山鄉" , 120.7997884 , 23.4496199 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣中埔鄉" , 120.5216429 , 23.3992591 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣大埔鄉" , 120.5909823 , 23.2909268 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣水上鄉" , 120.4245112 , 23.4267082 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣鹿草鄉" , 120.3083368 , 23.4068169 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣太保市" , 120.349316 , 23.4727623 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣朴子市" , 120.250477 , 23.4430205 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣東石鄉" , 120.1727458 , 23.4596722 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣六腳鄉" , 120.2634957 , 23.515994 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣新港鄉" , 120.3524881 , 23.5490555 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣民雄鄉" , 120.4467284 , 23.5416653 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣大林鎮" , 120.4705018 , 23.5930231 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣溪口鄉" , 120.4081682 , 23.5971618 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣義竹鄉" , 120.2245408 , 23.3488268 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義縣布袋鎮" , 120.166955 , 23.378049 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-018.xml"));
        PoiInfos.add(new PoiInfo("嘉義市東區" , 120.4760853 , 23.4853348 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-019.xml"));
        PoiInfos.add(new PoiInfo("嘉義市西區" , 120.4492964 , 23.4789575 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-019.xml"));
        PoiInfos.add(new PoiInfo("台南市中西區" , 120.1964522 , 22.9948212 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市東區" , 120.2303192 , 22.9805827 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市南區" , 120.1877943 , 22.9563522 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市北區" , 120.2079953 , 23.008515 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市安平區" , 120.1647036 , 22.9934043 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市安南區" , 120.1358346 , 23.0585336 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市永康區" , 120.2509308 , 23.0291532 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市歸仁區" , 120.2942877 , 22.9555003 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市新化區" , 120.3353161 , 23.0361177 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市左鎮區" , 120.4098315 , 23.0230843 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市玉井區" , 120.4668114 , 23.1263439 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市楠西區" , 120.5184776 , 23.1773127 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市南化區" , 120.5436593 , 23.12114 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市仁德區" , 120.2336203 , 22.9452519 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市關廟區" , 120.3342474 , 22.9506525 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市龍崎區" , 120.3911907 , 22.9570652 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市官田區" , 120.3587193 , 23.1897917 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市麻豆區" , 120.241545 , 23.1810568 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市佳里區" , 120.1803552 , 23.1646723 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市西港區" , 120.1993381 , 23.1257853 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市七股區" , 120.1067925 , 23.1220826 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市將軍區" , 120.1219503 , 23.2097068 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市學甲區" , 120.1829403 , 23.2540277 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市北門區" , 120.1242853 , 23.2866026 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市新營區" , 120.3008961 , 23.2996805 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市後壁區" , 120.3452438 , 23.3594081 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市白河區" , 120.4601038 , 23.3505232 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市東山區" , 120.4318908 , 23.2808846 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市六甲區" , 120.3807281 , 23.2281019 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市下營區" , 120.2600642 , 23.2330877 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市柳營區" , 120.3526275 , 23.2683848 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市鹽水區" , 120.2452032 , 23.3009024 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市善化區" , 120.3005966 , 23.1406713 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市大內區" , 120.3986963 , 23.1506207 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市山上區" , 120.3752999 , 23.1045741 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市新市區" , 120.2926175 , 23.0821945 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("台南市安定區" , 120.2282836 , 23.1008017 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-016.xml"));
        PoiInfos.add(new PoiInfo("高雄市新興區" , 120.3060706 , 22.6283893 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市前金區" , 120.2945362 , 22.6254162 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市苓雅區" , 120.317791 , 22.6238684 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市鹽埕區" , 120.2830007 , 22.6224414 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市鼓山區" , 120.2794495 , 22.6438447 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市旗津區" , 120.2873266 , 22.5879991 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市前鎮區" , 120.3147208 , 22.5970794 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市三民區" , 120.3159731 , 22.6561323 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市楠梓區" , 120.3031871 , 22.7175372 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市小港區" , 120.34871 , 22.5472484 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市左營區" , 120.2916524 , 22.6877358 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市仁武區" , 120.359076 , 22.7055156 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市大社區" , 120.3729977 , 22.7377959 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("南海島東沙群島" , 116.887312 , 20.617512 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("南海島南沙群島" , 116.562386 , 11.318545 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市岡山區" , 120.3007949 , 22.8039527 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市路竹區" , 120.2601179 , 22.859284 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市阿蓮區" , 120.3178338 , 22.869724 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市田寮區" , 120.3958884 , 22.8656099 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市燕巢區" , 120.3726559 , 22.7893266 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市橋頭區" , 120.3144615 , 22.7534325 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市梓官區" , 120.2596135 , 22.7441543 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市彌陀區" , 120.2455033 , 22.7832231 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市永安區" , 120.2265046 , 22.8250844 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市湖內區" , 120.2242974 , 22.8947286 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市鳳山區" , 120.3493158 , 22.6113591 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市大寮區" , 120.4077776 , 22.5913477 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市林園區" , 120.393878 , 22.5067389 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市鳥松區" , 120.3668409 , 22.6671664 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市大樹區" , 120.4215178 , 22.7103667 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市旗山區" , 120.4723394 , 22.8762981 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市美濃區" , 120.5617711 , 22.90584 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市六龜區" , 120.6689023 , 23.0022543 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市內門區" , 120.47254 , 22.9568552 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市杉林區" , 120.5629089 , 22.9868402 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市甲仙區" , 120.6163578 , 23.1224586 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市桃源區" , 120.8498944 , 23.2280755 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市那瑪夏區" , 120.7309143 , 23.2700378 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市茂林區" , 120.7589491 , 22.9187538 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("高雄市茄萣區" , 120.2108809 , 22.8763476 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-017.xml"));
        PoiInfos.add(new PoiInfo("屏東縣屏東市" , 120.4819203 , 22.6663275 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣三地門鄉" , 120.7005906 , 22.7865643 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣霧台鄉" , 120.8029815 , 22.7481415 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣瑪家鄉" , 120.6714686 , 22.6694537 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣九如鄉" , 120.4945966 , 22.7302871 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣里港鄉" , 120.5112494 , 22.7970233 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣高樹鄉" , 120.5963114 , 22.8025079 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣鹽埔鄉" , 120.5697357 , 22.7403118 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣長治鄉" , 120.5607768 , 22.6910229 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣麟洛鄉" , 120.5337727 , 22.6457834 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣竹田鄉" , 120.5255653 , 22.5922797 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣內埔鄉" , 120.5920948 , 22.6463658 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣萬丹鄉" , 120.4783094 , 22.5776221 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣潮州鎮" , 120.5570154 , 22.5346954 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣泰武鄉" , 120.6926174 , 22.5982134 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣來義鄉" , 120.6867268 , 22.5023252 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣萬巒鄉" , 120.5895585 , 22.5879062 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣崁頂鄉" , 120.4991209 , 22.5158427 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣新埤鄉" , 120.5821832 , 22.485827 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣南州鄉" , 120.5167677 , 22.481123 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣林邊鄉" , 120.5124893 , 22.4415326 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣東港鎮" , 120.469456 , 22.4652612 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣琉球鄉" , 120.369492 , 22.341646 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣佳冬鄉" , 120.5442601 , 22.4296759 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣新園鄉" , 120.4541003 , 22.51484 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣枋寮鄉" , 120.5928688 , 22.3991525 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣枋山鄉" , 120.6573779 , 22.2606439 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣春日鄉" , 120.7027715 , 22.4058805 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣獅子鄉" , 120.7463733 , 22.2393347 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣車城鄉" , 120.7407035 , 22.076219 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣牡丹鄉" , 120.815646 , 22.1527653 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣恆春鎮" , 120.7784606 , 21.9798144 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("屏東縣滿州鄉" , 120.8360579 , 22.0578424 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-025.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣花蓮市" , 121.602374 , 23.9943246 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣新城鄉" , 121.6175645 , 24.0713695 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣秀林鄉" , 121.5370003 , 24.2258575 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣吉安鄉" , 121.5616133 , 23.9548273 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣壽豐鄉" , 121.5262575 , 23.8397856 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣鳳林鎮" , 121.4798948 , 23.7392299 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣光復鄉" , 121.4412825 , 23.64886 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣豐濱鄉" , 121.5027404 , 23.5851901 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣瑞穗鄉" , 121.4113308 , 23.5205619 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣萬榮鄉" , 121.3289342 , 23.6962737 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣玉里鎮" , 121.35765 , 23.3786239 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣卓溪鄉" , 121.2168466 , 23.404007 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("花蓮縣富里鄉" , 121.2953078 , 23.1986876 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-012.xml"));
        PoiInfos.add(new PoiInfo("台東縣台東市" , 121.1133647 , 22.7521598 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣綠島鄉" , 121.4882182 , 22.657522 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣蘭嶼鄉" , 121.548418 , 22.0435616 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣延平鄉" , 121.0075121 , 22.8867807 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣卑南鄉" , 120.9898192 , 22.7737882 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣鹿野鄉" , 121.1595842 , 22.9580514 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣關山鎮" , 121.1824925 , 23.0170591 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣海端鄉" , 121.0207446 , 23.1236224 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣池上鄉" , 121.2282958 , 23.0923579 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣東河鄉" , 121.2401362 , 22.9808097 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣成功鎮" , 121.378517 , 23.103359 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣長濱鄉" , 121.4263125 , 23.3290146 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣太麻里鄉" , 120.9532123 , 22.5540555 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣金峰鄉" , 120.8721585 , 22.5759956 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣大武鄉" , 120.900544 , 22.3778668 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("台東縣達仁鄉" , 120.8269231 , 22.3990588 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-027.xml"));
        PoiInfos.add(new PoiInfo("澎湖縣馬公市" , 119.5861581 , 23.5661218 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-015.xml"));
        PoiInfos.add(new PoiInfo("澎湖縣西嶼鄉" , 119.506926 , 23.600989 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-015.xml"));
        PoiInfos.add(new PoiInfo("澎湖縣望安鄉" , 119.5020698 , 23.3596967 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-015.xml"));
        PoiInfos.add(new PoiInfo("澎湖縣七美鄉" , 119.4353441 , 23.2087756 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-015.xml"));
        PoiInfos.add(new PoiInfo("澎湖縣白沙鄉" , 119.5948541 , 23.6640444 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-015.xml"));
        PoiInfos.add(new PoiInfo("澎湖縣湖西鄉" , 119.6615055 , 23.5773682 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-015.xml"));
        PoiInfos.add(new PoiInfo("金門縣金沙鎮" , 118.4256523 , 24.4883627 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-014.xml"));
        PoiInfos.add(new PoiInfo("金門縣金湖鎮" , 118.4204496 , 24.4374853 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-014.xml"));
        PoiInfos.add(new PoiInfo("金門縣金寧鄉" , 118.3345061 , 24.4567205 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-014.xml"));
        PoiInfos.add(new PoiInfo("金門縣金城鎮" , 118.3169714 , 24.4113998 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-014.xml"));
        PoiInfos.add(new PoiInfo("金門縣烈嶼鄉" , 118.2351192 , 24.4279737 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-014.xml"));
        PoiInfos.add(new PoiInfo("金門縣烏坵鄉" , 119.4542045 , 24.9911547 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-014.xml"));
        PoiInfos.add(new PoiInfo("連江縣南竿鄉" , 119.6834467 , 26.2720228 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-030.xml"));
        PoiInfos.add(new PoiInfo("連江縣北竿鄉" , 119.989167 , 26.223611 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-030.xml"));
        PoiInfos.add(new PoiInfo("連江縣莒光鄉" , 119.938292 , 25.9737901 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-030.xml"));
        PoiInfos.add(new PoiInfo("連江縣東引鄉" , 120.506667 , 26.3762242 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-030.xml"));
        PoiInfos.add(new PoiInfo("釣魚台" , 118.7791503 , 32.0160717 , "http://opendata.cwb.gov.tw/opendata/MFC/F-C0032-013.xml"));
        
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Log.i("test", "GPS");
        locationServiceInitial();
        Log.i("test", "end");
        
    }
    
    private LocationManager lms;
	private String bestProvider = LocationManager.GPS_PROVIDER;	//最佳資訊提供者
	private void locationServiceInitial() {
		lms = (LocationManager) getSystemService(LOCATION_SERVICE);	//取得系統定位服務
		Log.i("GPS:", "yes");
		Criteria criteria = new Criteria();	//資訊提供者選取標準
		bestProvider = lms.getBestProvider(criteria, true);	//選擇精準度最高的提供者
		mLocationManager.requestLocationUpdates(bestProvider,10,0,LocationChange);
	}
    
    public int onStartCommand(Intent intent, int flags, int startId) {
    	super.onStartCommand(intent, flags, startId);
    	{
    		try {
    			//檢查有沒有SD卡裝置
    			if(Environment.getExternalStorageState().equals( Environment.MEDIA_REMOVED)) {
    				Toast.makeText(background.this , "沒有SD卡!!!" , Toast.LENGTH_SHORT ).show();
    			}
    			else {
    				//取得SD卡儲存路徑
    				envFile = Environment.getExternalStorageDirectory();
    			}
    			//建立文件檔儲存路徑
    			aFile = new File(envFile.getPath() + "/CareSys");
    			uploadgroundpath = envFile.getPath() + "/CareSys/";
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
    			
    			mFileWriter = new FileWriter(envFile.getPath() + "/CareSys/env" + m + (mTime.month+1) + d + mTime.monthDay + ".txt",true);
    			a = mTime.year+"/"+(mTime.month+1)+"/"+mTime.monthDay+"  "+mTime.hour+":"+mTime.minute+":"+mTime.second+ "\r\n";
    			mFileWriter.write(a.toString() );
    			a = "";
    			Toast.makeText(background.this, "env" + m + (mTime.month+1) + d + mTime.monthDay +".txt已建立", Toast.LENGTH_SHORT).show();
    			uploadgrounddate = m + (mTime.month+1) + d + mTime.monthDay ;
    			uploadgroundname = "env" + m + (mTime.month+1) + d + mTime.monthDay + ".txt";
    			
    			uploadground.edit()
				.putString ("groundfilepath",uploadgroundpath)
				.putString ("groundfilename",uploadgroundname)
				.putString ("groundfiledate",uploadgrounddate)
				.commit();
    		}
    		catch (Exception e) {
    			
    		} 
    	}
    	    	
        handler.postDelayed(showTime, 3000);
        return START_STICKY;
    }
 
    @Override
    public void onDestroy() {
        super.onDestroy();
        {
        	mTime.setToNow();
    		a = a + mTime.year+"/"+(mTime.month+1)+"/"+mTime.monthDay+"  "+mTime.hour+":"+mTime.minute+":"+mTime.second+ "\r\n";
    		SaveData();
    		handler.removeCallbacks(showTime);
    		{
    			try{
    				mFileWriter.close();
    			}
    			catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		try{
    			lms.removeUpdates(LocationChange);
    			lms = null;
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
    		Toast.makeText(background.this, "已儲存文字", Toast.LENGTH_SHORT).show();
        }
    }
     
    private Runnable showTime = new Runnable() {
        public void run() {
        	if( check1 == true && check2 == true && check3 == true ){
        		i++;
        		Log.v("i=", String.valueOf(i));
   			    if( i > 0 ) {
   			    	Long[i] = LongBuf.toString();
   			    	Lat[i] = LatBuf.toString();
   			    	Locw[i] = nearest.getLocationName();
   			    	Disw[i] = DiswBuf.toString();
   			    	H_24R[i] = nearest.getH_24R();
   				    HUMD[i] = nearest.getHUMD();
   				    Loc[i] = LocBuf.toString();
   				    Dis[i] = DisBuf.toString();
   				    PSI[i] = PSIBuf.toString();
   				    Major[i] = MajorBuf.toString();
   				    Loci[i] = wi.getLocationName();
   				    Info[i] = wi.getMemo();
   				    time[i] = new Date().toString();
   				    intent = new Intent("Infomation");
   				    intent.putExtra("H_24R", H_24R[i]);
   				    intent.putExtra("HUMD", HUMD[i]);
   				    intent.putExtra("PSI", PSI[i]);
   			    	intent.putExtra("Major", Major[i]);
   				    intent.putExtra("Info", Info[i]);
   				    a = a + "Longtitute=" + Long[i];
   				    a = a + "\r\nLatitute=" + Lat[i];
   				    a = a + "\r\nLocationName-1=" + Locw[i];
   				    a = a + "\r\nDistance-1=" + Disw[i];
   				    a = a + "\r\nH_24R=" + H_24R[i];
   				    a = a + "\r\nHUMD=" + HUMD[i];
   				    a = a + "\r\nLocationName-2=" + Loc[i];
   				    a = a + "\r\nDistance-2=" + Dis[i];
   				    a = a + "\r\nPSI=" + PSI[i];
   				    a = a + "\r\nMajorPollutant=" + Major[i];
   				    a = a + "\r\nLocationName-3=" + Loci[i];
   				    a = a + "\r\nInfo=" + Info[i];
   				    a = a + "\r\nTime=" + time[i] + "\r\n";
   				    Log.v("txt", Locw[i]+" "+Loc[i]);
   				    notification(i);
   			    }
   			    if( i == 9 ) {
   			    	SaveData();
   			    	i = 0;
   			    	a="";
   			    }
   			    
        	}
        	
            //log目前時間
        	chkNetwork();
            Log.i("time:", new Date().toString());
            handler.postDelayed(this, 3600000);
        }
    };
    
    
    private void notification(int a) {
    	NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    	//設定當按下這個通知之後要執行的activity
    	Intent notifyIntent = new Intent(background.this,background.class);
    	notifyIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
    	PendingIntent appIntent=PendingIntent.getActivity(background.this,0,notifyIntent,0);
    	
    	if( Double.parseDouble(HUMD[a]) > 0.8 ) {
    		Notification notification1 = new Notification.Builder(getBaseContext())
        	.setSmallIcon(R.drawable.ic_launcher)
        	.setContentTitle("Test")
        	.setContentText("目前氣象資料顯示，濕度超過80%，可能影響呼吸道症狀，請即刻作尖峰吐氣流速三次，上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
        	.setTicker("notification on status bar.")
        	.setStyle(new Notification.BigTextStyle().bigText("目前氣象資料顯示，濕度超過80%，可能影響呼吸道症狀，請即刻作尖峰吐氣流速三次，上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
        	.setPriority(2)
        	.setContentIntent(appIntent)
        	.setAutoCancel(false)
        	.build();
        	
    		if(warning == "")
    			warning = "目前氣象資料顯示，濕度超過80%，可能影響呼吸道症狀，請即刻作尖峰吐氣流速三次，上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
    		else
    			warning = warning + "\r\n目前氣象資料顯示，濕度超過80%，可能影響呼吸道症狀，請即刻作尖峰吐氣流速三次，上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
    		
        	notificationManager.notify(count,notification1);
        	count++;
    	}
    	
    	if( Double.parseDouble(H_24R[a]) > 100 ) {
    		Notification notification2 = new Notification.Builder(getBaseContext())
        	.setSmallIcon(R.drawable.ic_launcher)
        	.setContentTitle("Test")
        	.setContentText("目前氣象資料顯示，雨量大於 100 ml，可能影響呼吸道症狀，請即刻作尖峰吐氣流速三次，上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
        	.setTicker("notification on status bar.")
        	.setStyle(new Notification.BigTextStyle().bigText("目前氣象資料顯示，雨量大於 100 ml，可能影響呼吸道症狀，請即刻作尖峰吐氣流速三次，上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
        	.setPriority(2)
        	.setContentIntent(appIntent)
        	.setAutoCancel(false)
        	.build();
        	
    		if(warning == "")
    			warning = "目前氣象資料顯示，雨量大於 100 ml，可能影響呼吸道症狀，請即刻作尖峰吐氣流速三次，上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
    		else
    			warning = warning + "\r\n目前氣象資料顯示，雨量大於 100 ml，可能影響呼吸道症狀，請即刻作尖峰吐氣流速三次，上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
    		
        	notificationManager.notify(count,notification2);
        	count++;
    	}
    	
    	if( Double.parseDouble(PSI[a]) > 50 && Double.parseDouble(PSI[a]) <= 200 ) {
    		if( Major[a].equals("懸浮微粒") ) {
    			Notification notification3 = new Notification.Builder(getBaseContext())
            	.setSmallIcon(R.drawable.ic_launcher)
            	.setContentTitle("Test")
            	.setContentText("目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過150 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
            	.setTicker("notification on status bar.")
            	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過150 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
            	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
    			if(warning == "")
        			warning = "目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過150 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過150 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
    			
            	notificationManager.notify(count,notification3);
            	count++;
    		} else if( Major[a].equals("二氧化硫") ) {
    			Notification notification3 = new Notification.Builder(getBaseContext())
            	.setSmallIcon(R.drawable.ic_launcher)
            	.setContentTitle("Test")
            	.setContentText("目前空氣品質不好，二氧化硫濃度超過140 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
            	.setTicker("notification on status bar.")
            	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，二氧化硫濃度超過140 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
            	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
    			if(warning == "")
        			warning = "目前空氣品質不好，二氧化硫濃度超過140 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，二氧化硫濃度超過140 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
    			
            	notificationManager.notify(count,notification3);
            	count++;
    		} else if( Major[a].equals("一氧化碳") ) {
    			Notification notification3 = new Notification.Builder(getBaseContext())
            	.setSmallIcon(R.drawable.ic_launcher)
            	.setContentTitle("Test")
            	.setContentText("目前空氣品質不好，一氧化碳濃度超過9 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
            	.setTicker("notification on status bar.")
            	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，一氧化碳濃度超過9 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
            	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
    			if(warning == "")
        			warning = "目前空氣品質不好，一氧化碳濃度超過9 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，一氧化碳濃度超過9 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
    			
            	notificationManager.notify(count,notification3);
            	count++;
    		} else if( Major[a].equals("臭氧") ) {
    			Notification notification3 = new Notification.Builder(getBaseContext())
            	.setSmallIcon(R.drawable.ic_launcher)
            	.setContentTitle("Test")
            	.setContentText("目前空氣品質不好，臭氧濃度超過 120 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
            	.setTicker("notification on status bar.")
            	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，臭氧濃度超過 120 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
            	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
    			if(warning == "")
        			warning = "目前空氣品質不好，臭氧濃度超過 120 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，臭氧濃度超過 120 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
    			
            	notificationManager.notify(count,notification3);
            	count++;
    		}
    	} else if( Double.parseDouble(PSI[a]) > 200 && Double.parseDouble(PSI[a]) <= 300 ) {
    		if( Major[a].equals("懸浮微粒") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過350 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過350 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過350 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過350 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	} else if( Major[a].equals("二氧化硫") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，二氧化硫濃度超過300 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，二氧化硫濃度超過300 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，二氧化硫濃度超過300 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，二氧化硫濃度超過300 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	} else if( Major[a].equals("一氧化碳") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，一氧化碳濃度超過15 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，一氧化碳濃度超過15 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，一氧化碳濃度超過15 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，一氧化碳濃度超過15 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	} else if( Major[a].equals("臭氧") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，臭氧濃度超過200 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，臭氧濃度超過200 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，臭氧濃度超過200 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，臭氧濃度超過200 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	} else if( Major[a].equals("二氧化氮") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，二氧化氮濃度超過600 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，二氧化氮濃度超過600 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，二氧化氮濃度超過600 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，二氧化氮濃度超過600 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	}
    	} else if( Double.parseDouble(PSI[a]) > 300 ) {
        	if( Major[a].equals("懸浮微粒") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過420 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過420 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過420 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，空氣中懸浮微粒(PM10)濃度超過420 mg/m^3，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	} else if( Major[a].equals("二氧化硫") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Test")
               	.setContentText("目前空氣品質不好，二氧化硫濃度超過600 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，二氧化硫濃度超過600 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，二氧化硫濃度超過600 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，二氧化硫濃度超過600 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	} else if( Major[a].equals("一氧化碳") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，一氧化碳濃度超過30 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，一氧化碳濃度超過30 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，一氧化碳濃度超過30 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，一氧化碳濃度超過30 ppm，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	} else if( Major[a].equals("臭氧") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，臭氧濃度超過400 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，臭氧濃度超過400 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，臭氧濃度超過400 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，臭氧濃度超過400 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	} else if( Major[a].equals("二氧化氮") ) {
        		Notification notification3 = new Notification.Builder(getBaseContext())
               	.setSmallIcon(R.drawable.ic_launcher)
               	.setContentTitle("Test")
               	.setContentText("目前空氣品質不好，二氧化氮濃度超過1200 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。")
               	.setTicker("notification on status bar.")
               	.setStyle(new Notification.BigTextStyle().bigText("目前空氣品質不好，二氧化氮濃度超過1200 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。"))
               	.setPriority(2)
            	.setContentIntent(appIntent)
            	.setAutoCancel(false)
            	.build();
            	
        		if(warning == "")
        			warning = "目前空氣品質不好，二氧化氮濃度超過1200 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		else
        			warning = warning + "\r\n目前空氣品質不好，二氧化氮濃度超過1200 ppb，可能加重呼吸道症狀，請即刻作尖峰吐氣流速三次，並上傳資料，如果發作，儘快使用緩解藥物或迅速就醫。";
        		
            	notificationManager.notify(count,notification3);
            	count++;
        	}
    	}
    	
    	intent.putExtra("warning" , warning);
    	sendBroadcast(intent);
    	warning = "";
    }
    
    private void SaveData() {
		{
			try {
				mFileWriter.write(a.toString() );
				Log.v("write", "yes");
			}
			catch (Exception e) {
				
			}
		}
	}
    
    public void chkNetwork(){
        new Thread()
            {
                 @Override
                 public void run()
                 {
                       Arr_Weather = getWeather();
                       if(Arr_Weather != null) {
                    	   for (int i = 0; i < Arr_Weather.length; i++)
                           {
                           	if(Arr_Weather[i].getLocationName().equals(Poiws.get(0).getName()))
                           		nearest = Arr_Weather[i];
                           }
                           if (nearest != null){
                        	   Log.i("weather:", nearest.getLocationName());
                        	   Log.i("weather:", String.valueOf(nearest.getH_24R()));
                        	   Log.i("weather:", String.valueOf(nearest.getHUMD()));
                        	   check1 = true;
                           }
                       }       
                       Arr_WeatherInfo = getWeatherInfo();
                       wi = Arr_WeatherInfo[0];
                       if (wi != null){
                    	   Log.i("weatherInfo:", wi.getLocationName());
                    	   Log.i("weatherInfo:", wi.getMemo());
                    	   check3 = true;
                       }
                 }
             }.start();
            
            //呼叫AsyncTask
            if(url != null){
            	new  DownloadWebpageTask().execute();
            }
    }
    
    private class DownloadWebpageTask extends AsyncTask<Void,Integer,String[]> 
    {
     
        @Override
        //要在背景中做的事
        protected String[]  doInBackground(Void... params) {
            try {
                return getWebData();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }        
        }



       //@Override
       //背景工作處理完"後"需作的事
       protected void onPostExecute(String[] result) {
    	   super.onPostExecute(result);
    	   if(result != null) {
    		   Log.v("PSI", result[0]);
        	   Log.v("pol", result[1]);
        	   PSIBuf = result[0];
        	   MajorBuf = result[1];
        	   LocBuf = Pois.get(0).getName();
        	   check2 = true;
    	   }
           //mText3.setText(" 距離最近的測站:"+Pois.get(0).getName()+"\n"+" 空氣品質 :"+result);
       }

       //取得網路資料
       public String[] getWebData() throws IOException{
    	   HttpURLConnection conn = (HttpURLConnection)url.openConnection();
           conn.setReadTimeout(10000 /* milliseconds */);
           conn.setConnectTimeout(15000 /* milliseconds */);
           conn.setRequestMethod("GET");
           conn.setDoInput(true);        
           conn.connect();
           BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
           String jsonString= reader.readLine();
           reader.close();
      
           try {
               return (getJson(jsonString));
           } catch (JSONException e) {
        	   e.printStackTrace();
               return null;
           }
       }

       //將JSON字串解析
       public String[]  getJson(String jsonString) throws JSONException {
    	   //如果是巢狀JSON字串,須分兩次來取資料
           String[] data = new String[2];
           data[0] = new JSONArray(jsonString).getJSONObject(0).getString("PSI");
           //if(Integer.parseInt(data[0]) > 50){
        	   data[1] = new JSONArray(jsonString).getJSONObject(0).getString("MajorPollutant");
           //} else {
        	   //data[1] = "None";
           //}
           
           return data;
       }
    }
    
    public LocationListener LocationChange = new LocationListener() 
	{
		public void onLocationChanged(Location mLocation) 
		{	 
			LatBuf = String.valueOf(mLocation.getLatitude());
			LongBuf = String.valueOf(mLocation.getLongitude());
			Log.v("GPS:", String.valueOf(mLocation.getLongitude())+String.valueOf(mLocation.getLatitude()));
			for(Poi mPoi : Pois) 	
			{
				//for迴圈將距離帶入，判斷距離為Distance function，需帶入使用者取得定位後的緯度、經度、景點店家緯度、經度。 
				mPoi.setDistance(Distance(mLocation.getLatitude(),mLocation.getLongitude(),mPoi.getLatitude(),mPoi.getLongitude()));
	        }
	        	
			//依照距離遠近進行List重新排列
	        DistanceSort(Pois);
	        
	        DisBuf = DistanceText(Pois.get(0).getDistance());
	        
	        Log.v("GPS", Pois.get(0).getName());
	        
	        for(Poiw mPoi : Poiws) 	
			{
				//for迴圈將距離帶入，判斷距離為Distance function，需帶入使用者取得定位後的緯度、經度、景點店家緯度、經度。 
				mPoi.setDistance(Distance(mLocation.getLatitude(),mLocation.getLongitude(),mPoi.getLatitude(),mPoi.getLongitude()));
	        }
	        	
			//依照距離遠近進行List重新排列
	        DistanceSortw(Poiws);
	        
	        DiswBuf = DistanceText(Poiws.get(0).getDistance());
	        
	        for(PoiInfo mPoi : PoiInfos) 	
			{
				//for迴圈將距離帶入，判斷距離為Distance function，需帶入使用者取得定位後的緯度、經度、景點店家緯度、經度。 
				mPoi.setDistance(Distance(mLocation.getLatitude(),mLocation.getLongitude(),mPoi.getLatitude(),mPoi.getLongitude()));
	        }
	        	
			//依照距離遠近進行List重新排列
	        DistanceSortInfo(PoiInfos);
			
			try {
				url = new URL("http://opendata.epa.gov.tw/ws/Data/AQX/?$filter=SiteName%20eq%20%27"+java.net.URLEncoder.encode(Pois.get(0).getName(),"UTF-8")+"%27&$select=PSI,MajorPollutant&$orderby=PSI&$skip=0&$top=1&format=json");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       	
	    }
	         
	    public void onProviderDisabled(String provider) 
	    {
	    }
	         
	    public void onProviderEnabled(String provider) 
	    {
	    }
	         
	    public void onStatusChanged(String provider, int status,Bundle extras) 
	    {
	    }
	};
	
	
	//帶入距離回傳字串 (距離小於一公里以公尺呈現，距離大於一公里以公里呈現並取小數點兩位)
	private String DistanceText(double distance)
	{
		if(distance < 1000 ) return String.valueOf((int)distance) + "m" ;
		else return new DecimalFormat("#.00").format(distance/1000) + "km" ;
	}
	
	//List排序，依照距離由近開始排列，第一筆為最近，最後一筆為最遠
	private void DistanceSort(ArrayList<Poi> poi)
	{
		Collections.sort(poi, new Comparator<Poi>() 
		{
			@Override
			public int compare(Poi poi1, Poi poi2) 
			{
				return poi1.getDistance() < poi2.getDistance() ? -1 : 1 ;
			}
		});
	}
		
	private void DistanceSortw(ArrayList<Poiw> poiw)
	{
		Collections.sort(poiw, new Comparator<Poiw>() 
		{
			@Override
			public int compare(Poiw poiw1, Poiw poiw2) 
			{
				return poiw1.getDistance() < poiw2.getDistance() ? -1 : 1 ;
			}
		});
	}
	
	private void DistanceSortInfo(ArrayList<PoiInfo> poiInfo)
	{
		Collections.sort(poiInfo, new Comparator<PoiInfo>() 
		{
			@Override
			public int compare(PoiInfo poiInfo1, PoiInfo poiInfo2) 
			{
				return poiInfo1.getDistance() < poiInfo2.getDistance() ? -1 : 1 ;
			}
		});
	}
	
	public double Distance(double longitude1, double latitude1, double longitude2,double latitude2) 
	{
		double radLatitude1 = latitude1 * Math.PI / 180;
		double radLatitude2 = latitude2 * Math.PI / 180;
		double l = radLatitude1 - radLatitude2;
		double p = longitude1 * Math.PI / 180 - longitude2 * Math.PI / 180;
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(l / 2), 2)
		                 + Math.cos(radLatitude1) * Math.cos(radLatitude2)
		                 * Math.pow(Math.sin(p / 2), 2)));
		distance = distance * 6378137.0;
		distance = Math.round(distance * 10000) / 10000;
		    
		return distance ;
	}
	
	public Weather[] getWeather()
    {
          if (trgUrl == null)
               return null;
          try
          {
               // 建立一個Parser物件，並指定擷取規則 (ParsingHandler)
               SimpleXMLParser dataXMLParser = new SimpleXMLParser(
               new WeatherXMLParsingHandler());
               // 呼叫getData方法取得物件陣列
               Object[] data = (Object[]) dataXMLParser.getData(trgUrl);
               if (data != null)
               {
                    // 如果資料形態正確，就回傳
                    if (data[0] instanceof Weather[])
                    {
                          return (Weather[]) data[0];
                    }
               }
          } catch (SAXException e)
          {
               e.printStackTrace();
          } catch (IOException e)
          {
               e.printStackTrace();
          } catch (ParserConfigurationException e)
          {
               e.printStackTrace();
          }
          // 若有錯誤則回傳null
          return null;
    }
	
	public WeatherInfo[] getWeatherInfo()
    {
		  Url = PoiInfos.get(0).getUrl();
		  Log.v("Info", Url);
		  
          if (Url == null)
               return null;
          try
          {
               // 建立一個Parser物件，並指定擷取規則 (ParsingHandler)
               SimpleXMLParser dataXMLParser = new SimpleXMLParser(
               new WeatherInfoXMLParsingHandler());
               // 呼叫getData方法取得物件陣列
               Object[] data = (Object[]) dataXMLParser.getData(Url);
               if (data != null)
               {
                    // 如果資料形態正確，就回傳
                    if (data[0] instanceof WeatherInfo[])
                    {
                          return (WeatherInfo[]) data[0];
                    }
               }
          } catch (SAXException e)
          {
               e.printStackTrace();
          } catch (IOException e)
          {
               e.printStackTrace();
          } catch (ParserConfigurationException e)
          {
               e.printStackTrace();
          }
          // 若有錯誤則回傳null
          return null;
    }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}