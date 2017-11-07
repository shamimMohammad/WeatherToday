package com.example.shamim_ahmed.weathertoday.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shamim_ahmed.weathertoday.R;
import com.example.shamim_ahmed.weathertoday.adapters.DailyWeatherAdapter;
import com.example.shamim_ahmed.weathertoday.weather.DailyWeather;

/**
 * Created by shamim_ahmed on 10/30/2017.
 */

public class ListFragment extends Fragment {

    private DailyWeather[] mDailyWeather;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mDailyWeather = (DailyWeather[]) bundle.getSerializable(ViewPagerFragment.SERIALIZABLE_KEY_2);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment,container,false);

        ListView listview = (ListView) view.findViewById(R.id.list);
        TextView emptyText = (TextView) view.findViewById(R.id.emptyTextView);

        DailyWeatherAdapter adapter = new DailyWeatherAdapter(getContext(),mDailyWeather);
        listview.setAdapter(adapter);
        listview.setEmptyView(emptyText);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dayOfTheWeek = mDailyWeather[position].getDayOfTheWeek();
                String temperature = mDailyWeather[position].getTemperatureMax()+"";
                String condition = mDailyWeather[position].getSummary();
                String message = String.format("On %s The Temperature Will Be %s and It Will Be %s",dayOfTheWeek,temperature,condition);

                Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}

