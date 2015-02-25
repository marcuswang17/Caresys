package com.caresys;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.caresys.WeatherInfo;
import com.caresys.SimpleXMLParsingHandler;
 
/**
 * 記錄著Rss新聞資料的解析XML處理方式的class （繼承了SimpleXMLParsingHandler）
 */
public class WeatherInfoXMLParsingHandler extends SimpleXMLParsingHandler
{
     /** 用來儲存Rss新聞的物件 */
     private WeatherInfo weatherItem;
     /** 用來儲存Rss新聞的物件Stack(堆疊) */
     private Stack<WeatherInfo> mWeatherItem_list;
     private StringBuffer sb = new StringBuffer();
     
     Boolean check1 = false;
     Boolean check2 = false;
 
     /** 建構子 */
     public WeatherInfoXMLParsingHandler()
     {
     }
 
     /**
      * @return回傳RssNews[]。程式會將讀到的物件{ RssNews[] }包成Object[]然後回傳
      */
     @Override
     public Object[] getParsedData()
     {
           WeatherInfo[] Arr_RssNews = (WeatherInfo[]) mWeatherItem_list
                     .toArray(new WeatherInfo[mWeatherItem_list.size()]);
           // 解析結果回報
           return new Object[] { Arr_RssNews };
     }
 
     /**
      * XML文件開始解析時呼叫此method
      */
     @Override
     public void startDocument() throws SAXException
     {
           super.startDocument();
           // 在文件開始的時候，宣告出該RssNews形態的Stack(堆疊)
           mWeatherItem_list = new Stack<WeatherInfo>();
     }
 
     /**
      * XML文件結束時呼叫此method
      */
     @Override
     public void endDocument() throws SAXException
     {
           super.endDocument();
     }
 
     /**
      * 解析到Element的開頭時呼叫此method
      */
     @Override
     public void startElement(String namespaceURI, String localName,
                String qName, Attributes atts) throws SAXException
     {
           super.startElement(namespaceURI, localName, qName, atts);
           // 若搞不清楚現在在哪裡的話可以用printNodePos();
           // printNodePos();
           
           if (getInNode().size() >= 2
                     && getInNode().get(getInNode().size() - 2).equals("fifowml")
                     && getInNode().get(getInNode().size() - 1).equals("data"))
           {
                // 在cwbopendata -> location這個位置中
                // 新增一個RssNews
      	        weatherItem = new WeatherInfo();
           }
     }
 
     /**
      * 解析到Element的結尾時呼叫此method
      */
     @Override
     public void endElement(String namespaceURI, String localName, String qName)
                throws SAXException
     {
           if (getInNode().size() >= 2
                     && getInNode().get(getInNode().size() - 2).equals("fifowml")
                     && getInNode().get(getInNode().size() - 1).equals("data"))
           {
        	   // 在cwbopendata -> location這個位置中
               // 新增一筆新聞資料到 Stack(堆疊) 裡
        	   
        	   weatherItem.setMemo(sb.toString());
       	       mWeatherItem_list.add(weatherItem);
       	       weatherItem = null;
           }
           
           super.endElement(namespaceURI, localName, qName);
     }
 
     /**
      * 取得Element的開頭結尾中間夾的字串
      */
     @Override
     public void characters(String fetchStr)
     {
           if (getInNode().size() >= 3
                     && getInNode().get(getInNode().size() - 3).equals("fifowml")
                     && getInNode().get(getInNode().size() - 2).equals("data"))
           {
                // 在rss -> channel -> item -> XXX這個位置中
                // 新增Node的上所有資料
                // 在rss -> channel -> item -> title這個位置中
                if (getInNode().lastElement().equals("memo"))
                     // 設定標題
                	 sb.append(fetchStr);
                // 在rss -> channel -> item -> pubDate這個位置中
                else if (getInNode().lastElement().equals("name"))
                     // 設定發佈日期
                	 weatherItem.setLocationName(fetchStr);
           }
     }
}