package com.example.yusuk.grush;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

/**
 * Created by yusuk on 2017/12/19.
 */

public class AsyncHttpPost extends AsyncTask<String, Integer, String> {
    private TextView txtResult;

    // 結果を反映させるTextViewを取得
    public AsyncHttpPost(Context context){
        super();
        RestaurantRegisterActivity activity = (RestaurantRegisterActivity)context;
        txtResult = (TextView)activity.findViewById(R.id.txtResult);
    }

    // 非同期でHTTP GETリクエストを送信
    @Override
    protected String doInBackground(String... params){

        StringBuilder list = new StringBuilder();

        try{
            // 指定したアドレスにデータを送信
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();


            //接続タイムアウトを設定する。
            con.setConnectTimeout(100000);
            //レスポンスデータ読み取りタイムアウトを設定する。
            con.setReadTimeout(100000);
            //ヘッダーにUser-Agentを設定する。
            con.setRequestProperty("User-Agent", "Android");
            //ヘッダーにAccept-Languageを設定する。
            con.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            //ヘッダーにContent-Typeを設定する
            con.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //HTTPのメソッドをPOSTに設定する。
            con.setRequestMethod("POST");
            //リクエストのボディ送信を許可する
            con.setDoOutput(true);
            //レスポンスのボディ受信を許可する
            con.setDoInput(true);

            //ステップ4.コネクションを開く
            con.connect();

            //ステップ5:リクエストボディの書き出しを行う。
            OutputStream outputStream = con.getOutputStream();

            HashMap<String, String> keyValues = new HashMap<String, String>();
            keyValues.put("key" , "value");

            if (keyValues.size() > 0) {
                Uri.Builder builder = new Uri.Builder();

                //HashMapを[key=value]形式の文字列に変換する
                Set<String> keys = keyValues.keySet();

                for (String key : keys) {
                    //[key=value]形式の文字列に変換する。
                    builder.appendQueryParameter(key , keyValues.get(key));
                }

                //[key=value&key=value…]形式の文字列に変換する。
                String join = builder.build().getEncodedQuery();
                PrintStream ps = new PrintStream(outputStream);
                ps.print(join);
                ps.close();
            }
            outputStream.close();

            // =====================================================================
            int statusCode = con.getResponseCode();

            String responseData = "";
            InputStream stream = con.getInputStream();
            StringBuffer sb = new StringBuffer();
            String line = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            responseData = sb.toString();
            Log.d("javatest2",responseData);
            // =====================================================================


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
