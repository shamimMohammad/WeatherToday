package com.example.shamim_ahmed.weathertoday.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shamim_ahmed.weathertoday.R;
import com.example.shamim_ahmed.weathertoday.weather.DailyWeather;

/**
 * Created by shamim_ahmed on 10/10/2017.
 */

public class DailyWeatherAdapter extends BaseAdapter {

    private Context mContext;
    private DailyWeather[] mDailyWeather;

    public DailyWeatherAdapter(Context context, DailyWeather[] dailyWeather){
        mContext = context;
        mDailyWeather = dailyWeather;
    }

    @Override
    public int getCount() {
        return mDailyWeather.length;
    }

    @Override
    public Object getItem(int position) {
        return mDailyWeather[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item,null);
            holder = new ViewHolder();
            holder.iconImage = (ImageView)convertView.findViewById(R.id.iconImageView);
            holder.dayNameText = (TextView)convertView.findViewById(R.id.dayNameTextView);
            holder.listTemperatureText = (TextView)convertView.findViewById(R.id.listTemperatureTextView);
            holder.circleImage = (ImageView)convertView.findViewById(R.id.circleImageView);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        DailyWeather dailyWeather = mDailyWeather[position];

        holder.iconImage.setImageResource(dailyWeather.getIconId());
        holder.dayNameText.setText(dailyWeather.getDayOfTheWeek());
        holder.listTemperatureText.setText(dailyWeather.getTemperatureMax()+"");
        holder.circleImage.setImageResource(R.drawable.bg_temperature);

        return convertView;
    }

    private static class ViewHolder{
        ImageView iconImage;
        TextView listTemperatureText;
        TextView dayNameText;
        ImageView circleImage;
    }

}
