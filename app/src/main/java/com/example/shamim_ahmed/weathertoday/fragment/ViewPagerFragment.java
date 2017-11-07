package com.example.shamim_ahmed.weathertoday.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shamim_ahmed.weathertoday.R;
import com.example.shamim_ahmed.weathertoday.ui.ForecastActivity;
import com.example.shamim_ahmed.weathertoday.weather.DailyWeather;
import com.example.shamim_ahmed.weathertoday.weather.HourlyWeather;

/**
 * Created by shamim_ahmed on 10/30/2017.
 */

public class ViewPagerFragment extends Fragment {

    public static final String SERIALIZABLE_KEY = "SERIALIZABLE_KEY";
    public static final String SERIALIZABLE_KEY_2 = "SERIALIZABLE_KEY_2";
    private DailyWeather[] mDailyWeather;
    private HourlyWeather[] mHourlyWeathers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_pager_fragment,container,false);

        final ListFragment listFragment = new ListFragment();
        final RecyclerFragment recyclerFragment = new RecyclerFragment();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mDailyWeather = (DailyWeather[]) bundle.getSerializable(ForecastActivity.DAILY_SERIALIZABLE_KEY);
        }
        Bundle bundle2 = this.getArguments();
        if (bundle != null) {
            mHourlyWeathers = (HourlyWeather[]) bundle2.getSerializable(ForecastActivity.HOURLY_SERIALIZABLE_KEY);
        }

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerLayout);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SERIALIZABLE_KEY, mHourlyWeathers);
                    RecyclerFragment recyclerFragment = new RecyclerFragment();

                    recyclerFragment.setArguments(bundle);
                    return recyclerFragment;
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SERIALIZABLE_KEY_2, mDailyWeather);
                    ListFragment listFragment = new ListFragment();

                    listFragment.setArguments(bundle);
                    return listFragment;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "HOURLY";
                }
                else {
                    return "7 DAY";
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
