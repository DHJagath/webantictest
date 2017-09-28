package com.apps.jagath.webnatictest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.jagath.webnatictest.R;
import com.apps.jagath.webnatictest.models.Sales;

import java.util.List;

/**
 * Created by Jagath on 9/28/17.
 */

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.MyViewHolder> {
    private List<Sales> salesList;

    public SalesAdapter(List<Sales> salesList) {
        this.salesList = salesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sales_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Sales sales = salesList.get(position);
        holder.monthtxt.setText(sales.getMonth());
        holder.salestxt.setText(String.valueOf(sales.getSale()));
    }

    @Override
    public int getItemCount() {
        return salesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView monthtxt, salestxt;

        public MyViewHolder(View view) {
            super(view);
            monthtxt = (TextView) view.findViewById(R.id.month);
            salestxt = (TextView) view.findViewById(R.id.sales);
        }
    }

}
