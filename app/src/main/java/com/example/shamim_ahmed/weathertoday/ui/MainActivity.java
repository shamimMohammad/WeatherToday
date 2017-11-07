package com.example.shamim_ahmed.weathertoday.ui;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shamim_ahmed.weathertoday.R;
import com.example.shamim_ahmed.weathertoday.weather.CurrentWeather;
import com.example.shamim_ahmed.weathertoday.weather.DailyWeather;
import com.example.shamim_ahmed.weathertoday.weather.Forecast;
import com.example.shamim_ahmed.weathertoday.weather.HourlyWeather;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Forecast mForecast;
    private TextView temperatureText;
    private TextView degreeText;
    private TextView timeZoneText;
    private TextView humidityText;
    private TextView humidityValueText;
    private TextView precipChanceText;
    private TextView precipChanceValueText;
    private TextView timeText;
    private TextView summaryText;
    private ImageView weatherImage;
    private ImageView refreshImage;
    private ProgressBar mProgressBar;
    private Button detailsButton;

    public static final String DAILY_FORECAST = "DAILY_FORECAST";
    public static final String HOURLY_FORECAST = "HOURLY_FORECAST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final double latitude = 23.8103;
        final double longitude = 90.4125;

        temperatureText = (TextView) findViewById(R.id.temperatureTextView);
        degreeText = (TextView) findViewById(R.id.degreeTextView);
        timeZoneText = (TextView) findViewById(R.id.timeZoneTextView);
        timeText = (TextView) findViewById(R.id.timeTextView);
        humidityText = (TextView) findViewById(R.id.humidityTextView);
        humidityValueText = (TextView) findViewById(R.id.humidityValueTextView);
        precipChanceText = (TextView) findViewById(R.id.precipChanceTextView);
        precipChanceValueText = (TextView) findViewById(R.id.precipChanceValueTextView);
        summaryText = (TextView) findViewById(R.id.summaryTextView);
        weatherImage = (ImageView) findViewById(R.id.weatherImageView);
        refreshImage = (ImageView) findViewById(R.id.refreshImageView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        detailsButton = (Button) findViewById(R.id.detailsButtonTextView);


        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ForecastActivity.class);
                i.putExtra(DAILY_FORECAST, mForecast.getDailyWeathers());
                i.putExtra(HOURLY_FORECAST, mForecast.getHourlyWeathers());
                startActivity(i);
            }
        });


        mProgressBar.setVisibility(View.INVISIBLE);

        refreshImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForecast(latitude, longitude);
            }
        });


        getForecast(latitude, longitude);


    }


    private void getForecast(double latitude, double longitude) {

        String apiKey = "0100b461096d62e874b9142c73ff4330";
        String forecastUrl = "https://api.darksky.net/forecast/"+apiKey+"/"+latitude+","+longitude;

        if (isNetworkIsAvailable()) {
            toggleRefresh();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(forecastUrl).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });

                    final String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            try {
                                mForecast = getForecastDetails(jsonData);
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateDisplay();
                                    }
                                });
                            }

                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            alertUserAboutError();
                        }

                }
            });
        }
        else{
            Toast.makeText(this, R.string.network_unavailable_message,Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefresh() {

        if(mProgressBar.getVisibility() == View.INVISIBLE){
            mProgressBar.setVisibility(View.VISIBLE);
            refreshImage.setVisibility(View.INVISIBLE);
        }
        else {
            mProgressBar.setVisibility(View.INVISIBLE);
            refreshImage.setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplay() {
        CurrentWeather currentWeather = mForecast.getCurrentWeather();

        temperatureText.setText(currentWeather.getTemperature()+"");
        timeZoneText.setText(currentWeather.getTimeZone()+"");
        timeText.setText(currentWeather.getFormattedTime()+"");
        humidityValueText.setText(currentWeather.getHumidity()+"");
        precipChanceValueText.setText(currentWeather.getPrecipChance()+"%");
        summaryText.setText(currentWeather.getSummary());

        Drawable drawable = getResources().getDrawable(currentWeather.getIconId());
        weatherImage.setImageDrawable(drawable);
    }

    private Forecast getForecastDetails(String jsonData) throws JSONException{
        Forecast forecast = new Forecast();

        forecast.setCurrentWeather(getCurrentDetails(jsonData));
        forecast.setDailyWeathers(getDailyDetails(jsonData));
        forecast.setHourlyWeathers(getHourDetails(jsonData));

        return forecast;
    }

    private DailyWeather[] getDailyDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");
        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray data = daily.getJSONArray("data");

        DailyWeather[] days = new DailyWeather[data.length()];

        for (int i = 0; i<days.length;i++){
            JSONObject jDays = data.getJSONObject(i);
            DailyWeather day = new DailyWeather();

            day.setSummary(jDays.getString("summary"));
            day.setIcon(jDays.getString("icon"));
            day.setTemperatureMax(jDays.getDouble("temperatureMax"));
            day.setTime(jDays.getLong("time"));
            day.setTimeZone(timeZone);

            days[i] = day;
        }
        return days;
    }

    private HourlyWeather[] getHourDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");

        HourlyWeather[] hours = new HourlyWeather[data.length()];

        for (int i = 0; i<hours.length;i++){
            JSONObject jHours = data.getJSONObject(i);
            HourlyWeather hour = new HourlyWeather();

            hour.setSummary(jHours.getString("summary"));
            hour.setIcon(jHours.getString("icon"));
            hour.setTemperature(jHours.getDouble("temperature"));
            hour.setTime(jHours.getLong("time"));
            hour.setTimeZone(timeZone);

            hours[i] = hour;
        }
        return hours;
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {

            JSONObject forecast = new JSONObject(jsonData);
            String timeZone = forecast.getString("timezone");

            JSONObject currently = forecast.getJSONObject("currently");

            CurrentWeather currentWeather = new CurrentWeather();
            currentWeather.setHumidity(currently.getDouble("humidity"));
            currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
            currentWeather.setTemperature(currently.getDouble("temperature"));
            currentWeather.setIcon(currently.getString("icon"));
            currentWeather.setSummary(currently.getString("summary"));
            currentWeather.setTime(currently.getLong("time"));
            currentWeather.setTimeZone(timeZone);

            return currentWeather;
    }

    private boolean isNetworkIsAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (info !=null && info.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {

        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_dialog");

    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);

        Drawable myLocationDrawable = menu.findItem(R.id.myLocation).getIcon();
        Drawable searchCityDrawable = menu.findItem(R.id.app_bar_search).getIcon();

        myLocationDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        searchCityDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.app_bar_search:
                break;
            case R.id.myLocation:
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/


}
