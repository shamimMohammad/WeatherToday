package com.example.shamim_ahmed.weathertoday.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by shamim_ahmed on 10/9/2017.
 */

public class HourlyWeather implements Parcelable{

    private long mTime;
    private String mIcon;
    private double mTemperature;
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

    public int getTemperature() {
        return (int)Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = (temperature-32)*5/9;
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

    public String getHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        Date dateTime = new Date(mTime*1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    public int getIconId(){
        return Forecast.getIconId(mIcon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(mTime);
        dest.writeString(mIcon);
        dest.writeDouble(mTemperature);
        dest.writeString(mTimeZone);
        dest.writeString(mSummary);

    }

    private HourlyWeather(Parcel in){

        mTime = in.readLong();
        mIcon = in.readString();
        mTemperature = in.readDouble();
        mTimeZone = in.readString();
        mSummary = in.readString();

    }

    public HourlyWeather(){

    }

    public static final Creator<HourlyWeather> CREATOR = new Creator<HourlyWeather>() {
        @Override
        public HourlyWeather createFromParcel(Parcel source) {
            return new HourlyWeather(source);
        }

        @Override
        public HourlyWeather[] newArray(int size) {
            return new HourlyWeather[size];
        }
    };
}
