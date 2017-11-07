package com.example.shamim_ahmed.weathertoday.weather;

import android.graphics.drawable.Icon;
import android.icu.text.SimpleDateFormat;

import com.example.shamim_ahmed.weathertoday.R;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by shamim_ahmed on 9/28/2017.
 */

public class CurrentWeather {

    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPrecipChance;
    private String mSummary;
    private String mTimeZone;

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public int getIconId(){
      return Forecast.getIconId(mIcon);
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long mTime) {
        this.mTime = mTime;
    }

    public int getTemperature() {
        return (int)Math.round(mTemperature);
    }

    public String getFormattedTime(){
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime()*1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    public void setTemperature(double temperature) {
        this.mTemperature = (temperature-32)*5/9;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double mhumidity) {
        this.mHumidity = mhumidity;
    }

    public int getPrecipChance() {
        double precipPercentage = mPrecipChance*100;
        return (int)Math.round(precipPercentage);
    }

    public void setPrecipChance(double mPrecipChance) {
        this.mPrecipChance = mPrecipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String mSummary) {
        this.mSummary = mSummary;
    }
}
