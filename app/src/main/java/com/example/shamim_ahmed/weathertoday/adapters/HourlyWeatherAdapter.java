package com.example.shamim_ahmed.weathertoday.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shamim_ahmed.weathertoday.R;
import com.example.shamim_ahmed.weathertoday.weather.HourlyWeather;

/**
 * Created by shamim_ahmed on 10/15/2017.
 */

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.HourlyViewHolder> {

    private HourlyWeather[] mHourlyWeathers;
    private Context mContext;

    public HourlyWeatherAdapter(Context context,HourlyWeather[] hourlyWeathers){
        mContext = context;
        mHourlyWeathers = hourlyWeathers;
    }

    @Override
    public HourlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item,parent,false);
        HourlyViewHolder viewHolder = new HourlyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourlyViewHolder holder, int position) {

        holder.bindHour(mHourlyWeathers[position]);

    }

    @Override
    public int getItemCount() {
        return mHourlyWeathers.length;
    }

    public class HourlyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTimeText;
        public TextView mTemperatureText;
        public ImageView mIconImage;

        public HourlyViewHolder(View itemView) {
            super(itemView);

            mTimeText = (TextView) itemView.findViewById(R.id.hourlyTimeTextView);
            mTemperatureText = (TextView) itemView.findViewById(R.id.hourlyTempTextView);
            mIconImage = (ImageView) itemView.findViewById(R.id.hourlyIconImageView);

            itemView.setOnClickListener(this);
        }

        public void bindHour(HourlyWeather hourlyWeather) {

            mTimeText.setText(hourlyWeather.getHour());
            mTemperatureText.setText(hourlyWeather.getTemperature()+"");
            mIconImage.setImageResource(hourlyWeather.getIconId());
        }

        @Override
        public void onClick(View v) {
            String hourlyTime = mTimeText.getText().toString();
            String temperature = mTemperatureText.getText().toString();
            String message = String.format("On %s The Temperature Will Be %s",hourlyTime,temperature);

            Toast.makeText(mContext ,message,Toast.LENGTH_LONG).show();
        }
    }

}
