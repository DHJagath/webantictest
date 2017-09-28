package com.apps.jagath.webnatictest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.apps.jagath.webnatictest.helpers.AppRequestQueue;
import com.apps.jagath.webnatictest.helpers.Helper;
import com.apps.jagath.webnatictest.helpers.VolleyResponseListener;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ActivitycountActivity extends AppCompatActivity {

    private BarChart barChart;
    private ArrayList<BarEntry> countList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitycount);

        this.setTitle("Hourly Activity Count");

        barChart = (BarChart) findViewById(R.id.activity_count_chart);

        loadActivityCount();
    }

    private void loadActivityCount(){
        final Map<String, String> params = new HashMap();
        params.put("data_type", "hourly_activity_count");
        params.put("date", "15-09-2017");

        AppRequestQueue.processRequest(getApplicationContext(), Request.Method.POST, Helper.requestUrl, params, new VolleyResponseListener() {
            @Override
            public void onVolleyResponse(String response) {

                try {
                    JSONObject dataObject = new JSONObject(response).getJSONObject("data");
                    Iterator iter = dataObject.keys();

                    final String[] hoursList = new String[dataObject.length()];
                    int i = 0;

                    while(iter.hasNext()){
                        String hour = (String)iter.next();
                        String count = dataObject.getString(hour);

                        countList.add(new BarEntry(i, Float.parseFloat(count)));
                        hoursList[i] = hour;

                        i++;
                    }

                    IAxisValueFormatter formatter = new IAxisValueFormatter() {
                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {
                            return hoursList[(int) value];
                        }
                    };

                    XAxis xAxis = barChart.getXAxis();
                    xAxis.setValueFormatter(formatter);

                    BarDataSet barDataSet = new BarDataSet(countList, "Activity Count");
                    BarData barData = new BarData(barDataSet);
                    barChart.setData(barData);

                    barChart.getDescription().setEnabled(false);
                    barChart.invalidate();//refresh the chart

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Something wrong please try again", Toast.LENGTH_LONG);
                }
            }
        });
    }
}
