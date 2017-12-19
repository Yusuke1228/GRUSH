package com.example.yusuk.grush;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList_ListActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private RestaurantAdapter mRestaurantAdapter;



    // RestaurantSearch画面用のPopupWindow
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list__list);

        // ListViewの設定
        mRestaurantAdapter = new RestaurantAdapter(RestaurantList_ListActivity.this);
        mListView = (ListView) findViewById(R.id.restaurantListView);

        reloadListView();

        // SearchButtonのインスタンス生成
        Button button1 = (Button) findViewById(R.id.searchButton);
        button1.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RestaurantSearchActivity.class);
        startActivity(intent);
    }


        private void reloadListView() {

        // 後でTaskクラスに変更する
        List<String> taskList = new ArrayList<String>();
        taskList.add("Restaurant-1");
        taskList.add("Restaurant-2");
        taskList.add("Restaurant-3");

        mRestaurantAdapter.setTaskList(taskList);
        mListView.setAdapter(mRestaurantAdapter);
        mRestaurantAdapter.notifyDataSetChanged();
    }
}
