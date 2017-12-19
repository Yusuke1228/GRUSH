package com.example.yusuk.grush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ユーザがログイン済みか否かを判断する（※確認中※）


        // ログイン画面に遷移する
        Intent intent = new Intent(getApplicationContext(), RestaurantRegisterActivity.class);
        startActivity(intent);

    }
}
