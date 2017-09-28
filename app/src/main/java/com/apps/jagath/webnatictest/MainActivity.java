package com.apps.jagath.webnatictest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showMonthlySales(View view){
        startActivity(new Intent(getApplicationContext(), MonthlySalesActivity.class));
    }

    public void showActivityCount(View view){
        startActivity(new Intent(getApplicationContext(), ActivitycountActivity.class));
    }


}
