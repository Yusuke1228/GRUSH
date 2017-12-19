package com.example.yusuk.grush;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yusuk on 2017/12/19.
 * HTTP接続するためのAsync
 */

public class AsyncHttpGet extends AsyncTask<String, Integer, String> {

    private TextView txtResult;

    // 結果を反映させるTextViewを取得
    public AsyncHttpGet(Context context){
        super();
        LoginActivity activity = (LoginActivity)context;
        txtResult = (TextView)activity.findViewById(R.id.txtResult);
    }

    // 非同期でHTTP GETリクエストを送信
    @Override
    protected String doInBackground(String... params){
        publishProgress(30); // 進捗の通知
        // ダミーで時間のかかる処理を実装
        SystemClock.sleep(3000);
        publishProgress(60); // 進捗の通知

        StringBuilder builder = new StringBuilder();
        StringBuilder list = new StringBuilder();

        try{
            // 指定したアドレスにアクセス
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try{

                JSONObject json = new JSONObject("{\"login_result\":\"success\",\"access_token\":\"abejfejhafhiu3huih\"}");

                String str1 = json.getString("login_result");
                String str2 = json.getString("access_token");

                Log.d("javatest", str1);


                // booksキーにアクセス
                JSONArray books = json.getJSONArray("books");

                // 配下のオブジェクトから順にtitle/priceキーを取得
                for (int i = 0; i < books.length(); i++){
                    JSONObject book = books.getJSONObject(i);
                    list.append(book.getString("title")).append("／");
                    list.append(book.getString("price")).append("円\n");
                }

            }catch(JSONException e){
                // JSONの解析に失敗
                e.printStackTrace();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        publishProgress(100); // 進捗の通知
        return list.toString();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        Log.d("url",values[0].toString());
    }

    @Override
    protected void onPostExecute(String result) {
        txtResult.setText(result);
    }

    @Override
    protected void onCancelled() {
        txtResult.setText("キャンセルされました。");
    }
}