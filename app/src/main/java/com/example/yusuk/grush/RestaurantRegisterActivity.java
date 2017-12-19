package com.example.yusuk.grush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RestaurantRegisterActivity extends AppCompatActivity {

    AsyncHttpPost task;
    // HTTP POSTで送信すべきデータ
    String data = "post test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_register);

        // 非同期処理を実行
        task = new AsyncHttpPost(this);
        task.execute("http://grush-s.sakura.ne.jp/dev/samplePost.php", data);

    }
}
