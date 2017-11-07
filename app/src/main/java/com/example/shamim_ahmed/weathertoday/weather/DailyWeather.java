package com.example.shamim_ahmed.weathertoday.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.TimeZone;

import static java.lang.System.in;

/**
 * Created by shamim_ahmed on 10/9/2017.
 */

public class DailyWeather implements Parcelable{

    private long mTime;
    private String mIcon;
    private double mTemperatureMax;
    private String mTimeZone;
    private String mSummary;

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getTemperatureMax() {
        return (int)Math.round(mTemperatureMax);
    }

    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = (temperatureMax-32)*5/9;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }

    public String getDayOfTheWeek(){
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime()*1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(mTime);
        dest.writeString(mIcon);
        dest.writeDouble(mTemperatureMax);
        dest.writeString(mTimeZone);
        dest.writeString(mSummary);

    }

    private DailyWeather(Parcel in){

        mTime = in.readLong();
        mIcon = in.readString();
        mTemperatureMax = in.readDouble();
        mTimeZone = in.readString();
        mSummary = in.readString();

    }

    public DailyWeather(){

    }

    public static final Creator<DailyWeather> CREATOR = new Creator<DailyWeather>() {
        @Override
        public DailyWeather createFromParcel(Parcel source) {
            return new DailyWeather(source);
        }

        @Override
        public DailyWeather[] newArray(int size) {
            return new DailyWeather[size];
        }
    };


}
