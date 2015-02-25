package com.caresys;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WeatherInfo implements Serializable
{
     private String locationName = null;
     private String memo = null;
 
     public void setLocationName(String locationName)
     {
           this.locationName = locationName;
     }
 
     public String getLocationName()
     {
           return locationName;
     }
     
     public void setMemo(String memo)
     {
    	 if(this.memo == null)
    		 this.memo = memo;
    	 else if(memo == "&amp")
    		 this.memo = this.memo + memo;
    	 else if(memo == "<")
    		 this.memo = this.memo + memo;
    	 else if(memo == ">")
    		 this.memo = this.memo + memo;
    	 else
    		 this.memo = this.memo + "\r\n" + memo;
     }
 
     public String getMemo()
     {
           return memo;
     }
 
     @Override
     public String toString()
     {
           return "WeatherInfo [locationName=" + locationName + ", memo=" + memo + "]";
     }
}