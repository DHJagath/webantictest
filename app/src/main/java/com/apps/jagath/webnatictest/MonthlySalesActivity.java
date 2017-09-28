package com.apps.jagath.webnatictest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.apps.jagath.webnatictest.adapters.SalesAdapter;
import com.apps.jagath.webnatictest.helpers.AppRequestQueue;
import com.apps.jagath.webnatictest.helpers.Helper;
import com.apps.jagath.webnatictest.helpers.VolleyResponseListener;
import com.apps.jagath.webnatictest.models.Sales;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MonthlySalesActivity extends AppCompatActivity {
    private List<Sales> salesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SalesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_sales);

        this.setTitle("Monthly Sales - 2017");

        recyclerView = (RecyclerView) findViewById(R.id.sales_recycler_view);

        mAdapter = new SalesAdapter(salesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        loadSales();
    }

    private void loadSales(){
        final Map<String, String> params = new HashMap();
        params.put("data_type", "monthly_sales");
        params.put("year", "2017");

        AppRequestQueue.processRequest(getApplicationContext(), Request.Method.POST, Helper.requestUrl, params, new VolleyResponseListener() {
            @Override
            public void onVolleyResponse(String response) {

                try {
                    JSONObject dataObject = new JSONObject(response).getJSONObject("data");
                    Iterator iter = dataObject.keys();
                    while(iter.hasNext()){
                        String month = (String)iter.next();
                        String sale = dataObject.getString(month);
                        Sales saleObj = new Sales(Integer.parseInt(sale), month, 2017);
                        salesList.add(saleObj);
                    }

                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Something wrong please try again", Toast.LENGTH_LONG);
                }

            }
        });
    }
}
