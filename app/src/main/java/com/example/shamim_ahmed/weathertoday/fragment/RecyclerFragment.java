package com.example.shamim_ahmed.weathertoday.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shamim_ahmed.weathertoday.R;
import com.example.shamim_ahmed.weathertoday.adapters.HourlyWeatherAdapter;
import com.example.shamim_ahmed.weathertoday.weather.DailyWeather;
import com.example.shamim_ahmed.weathertoday.weather.HourlyWeather;

/**
 * Created by shamim_ahmed on 10/30/2017.
 */

public class RecyclerFragment extends Fragment {

    private HourlyWeather[] mHourlyWeathers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mHourlyWeathers = (HourlyWeather[]) bundle.getSerializable(ViewPagerFragment.SERIALIZABLE_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_fragment,container,false);

        RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        HourlyWeatherAdapter adapter = new HourlyWeatherAdapter(getContext(),mHourlyWeathers);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        return view;
    }
}

