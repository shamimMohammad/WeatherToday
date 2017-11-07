package com.example.shamim_ahmed.weathertoday.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shamim_ahmed.weathertoday.R;
import com.example.shamim_ahmed.weathertoday.fragment.ViewPagerFragment;
import com.example.shamim_ahmed.weathertoday.weather.DailyWeather;
import com.example.shamim_ahmed.weathertoday.weather.HourlyWeather;

import java.util.Arrays;

public class ForecastActivity extends AppCompatActivity {

    public static final String DAILY_SERIALIZABLE_KEY = "DAILY_SERIALIZABLE_KEY";
    public static final String HOURLY_SERIALIZABLE_KEY = "HOURLY_SERIALIZABLE_KEY";
    private DailyWeather[] mDailyWeather;
    private HourlyWeather[] mHourlyWeathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDailyWeather = Arrays.copyOf(parcelables,parcelables.length,DailyWeather[].class);

        Parcelable[] parcelables2 = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORECAST);
        mHourlyWeathers = Arrays.copyOf(parcelables2,parcelables2.length,HourlyWeather[].class);


        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DAILY_SERIALIZABLE_KEY, mDailyWeather);
        bundle.putSerializable(HOURLY_SERIALIZABLE_KEY, mHourlyWeathers);
        viewPagerFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeholder,viewPagerFragment);
        fragmentTransaction.commit();


    }
}
