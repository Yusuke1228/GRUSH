package com.example.yusuk.grush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class RestaurantSearchActivity extends AppCompatActivity {
    private LinearLayout mLayout1;
    private LinearLayout mLayout2;
    private LinearLayout mLayout3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search);
        mLayout1 = (LinearLayout)findViewById(R.id.layout1);
        mLayout2 = (LinearLayout)findViewById(R.id.layout2);
        mLayout3 = (LinearLayout)findViewById(R.id.layout3);


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout1.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.INVISIBLE);
                mLayout3.setVisibility(View.INVISIBLE);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout2.setVisibility(View.VISIBLE);
                mLayout1.setVisibility(View.INVISIBLE);
                mLayout3.setVisibility(View.INVISIBLE);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout3.setVisibility(View.VISIBLE);
                mLayout2.setVisibility(View.INVISIBLE);
                mLayout1.setVisibility(View.INVISIBLE);
            }
        });

    }
}
