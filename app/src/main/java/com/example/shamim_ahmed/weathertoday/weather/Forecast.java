package com.example.shamim_ahmed.weathertoday.weather;

import com.example.shamim_ahmed.weathertoday.R;

/**
 * Created by shamim_ahmed on 10/9/2017.
 */

public class Forecast {

    private CurrentWeather mCurrentWeather;
    private HourlyWeather[] mHourlyWeathers;
    private DailyWeather[] mDailyWeathers;

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public HourlyWeather[] getHourlyWeathers() {
        return mHourlyWeathers;
    }

    public void setHourlyWeathers(HourlyWeather[] hourlyWeathers) {
        mHourlyWeathers = hourlyWeathers;
    }

    public DailyWeather[] getDailyWeathers() {
        return mDailyWeathers;
    }

    public void setDailyWeathers(DailyWeather[] dailyWeathers) {
        mDailyWeathers = dailyWeathers;
    }

    public static int getIconId(String iconString){
        int IconId = R.drawable.clear_day;

        if(iconString.equals("clear-day")){
            IconId = R.drawable.clear_day;
        }
        else if(iconString.equals("clear-night")){
            IconId = R.drawable.clear_night;
        }
        else if(iconString.equals("cloudy")){
            IconId = R.drawable.cloudy;
        }
        else if(iconString.equals("cloudy-night")){
            IconId = R.drawable.cloudy_night;
        }
        else if(iconString.equals("party-cloudy")){
            IconId = R.drawable.partly_cloudy;
        }
        else if(iconString.equals("fog")){
            IconId = R.drawable.fog;
        }
        else if(iconString.equals("rain")){
            IconId = R.drawable.rain;
        }
        else if(iconString.equals("sleet")){
            IconId = R.drawable.sleet;
        }
        else if(iconString.equals("snow")){
            IconId = R.drawable.snow;
        }
        else if(iconString.equals("sunny")){
            IconId = R.drawable.sunny;
        }
        else if(iconString.equals("wind")){
            IconId = R.drawable.wind;
        }
        return IconId;
    }
}
