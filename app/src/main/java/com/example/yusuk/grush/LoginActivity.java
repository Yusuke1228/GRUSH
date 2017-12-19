package com.example.yusuk.grush;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // メンバ変数の定義
    EditText mNameEditText; // 画面から入力したユーザ名を格納する
    EditText mTelEditText; // 画面から入力した電話番号を格納する
    EditText mPasswordEditText; // 画面から入力したパスワードを格納する
    ProgressDialog mProgress; //


    // DB接続のための試しに追加
    AsyncHttpGet task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 非同期処理を実行
        task = new AsyncHttpGet(this);

        // 接続先のURLを渡して非同期処理を起動する
        task.execute("http://grush-s.sakura.ne.jp/dev/json_output.php");

        // 変数の準備
        mNameEditText = (EditText) findViewById(R.id.nameText);
        mTelEditText = (EditText) findViewById(R.id.telText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordText);

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Processing...");

        // ボタンのインスタンスを生成する
        Button createAccountButton = (Button) findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(this);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // メンバ変数を定義する
        String name;
        String tel;
        String password;
        Boolean validationNameResult;
        Boolean validationPasswordResult;
        Boolean flagCreateAccount;
        Boolean flagLogin;

        // 変数の初期化
        validationNameResult = false;
        validationPasswordResult = false;
        flagCreateAccount = true;
        flagLogin = true;

        // ログイン後にRestaurantList_List画面に遷移するためのIntentを作成する
        Intent intent = new Intent(this, RestaurantList_ListActivity.class);

        name = mNameEditText.getText().toString();
        tel = mTelEditText.getText().toString();
        password = mPasswordEditText.getText().toString();

        // createAccountButtonを押下した場合
        if (v.getId() == R.id.createAccountButton) {

            // USER TBLを参照し、重複がないかを確認する

            // 重複がなかった場合、アカウントを生成

            // ユーザ名のValidationをする
            validationNameResult = validateName(name);

            // パスワードのValidationをする
            validationPasswordResult = validatePassword(password);

            // 重複があった場合は、エラーメッセージを出力する


            // RestaurantList_List画面に遷移する
            startActivity(intent);


            // loginButtonを押下した場合
        } else if (v.getId() == R.id.loginButton) {

            // ユーザ名のValidationをする
            validationNameResult = validateName(name);

            // パスワードのValidationをする
            validationPasswordResult = validatePassword(password);

            if (validationNameResult == false || validationPasswordResult == false) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Error");
                alertDialogBuilder.setMessage("Invalid User Name or Password");
                alertDialogBuilder.setPositiveButton("OK", null);
                alertDialogBuilder.show();

                flagLogin = false;

            }

            // USER TBLを参照し、USER_NAMEとPASSWORDの組み合わせが正しいかを確認する

            // 正しい場合は、ログインする

            // 誤っている場合は、エラーメッセージを出力する

            // RestaurantList_List画面に遷移する
            if (flagLogin == true) {
                startActivity(intent);
            }

        }
    }

    // ユーザ名のValidation（OKの場合Trueを、NGの場合はFalseを返す）
    public boolean validateName(String name) {

        // 値が入っているかを確認する
        if (name == null || name.isEmpty()) {
            return false;
        }
        return true;
    }

    // パスワードのValidation（OKの場合Trueを、NGの場合はFalseを返す）
    public boolean validatePassword(String password) {
        // 値が入っているかを確認する
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }
}
