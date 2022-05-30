package com.example.aps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aps.Contract.LoginContract;
import com.example.aps.Presenter.LoginPresenter;
import com.example.aps.R;
import com.example.aps.databinding.ActivityLoginBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.view {
    @BindView(R.id.account) EditText account;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.login) Button login;
    private LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this); // 連結View，切記要打這行

        presenter = new LoginPresenter(this);
        account.setText("e1001");
        password.setText("e1001");
        //登入監聽
        login.setOnClickListener(view -> presenter.login(account.getText().toString()
                        ,password.getText().toString()));
    }

    //帳密正確跳到MainMenuActivity
    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
        startActivity(intent);
        Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show();
    }
    //帳密錯誤
    @Override
    public void loginFail() {
        account.setText("");
        password.setText("");
        Toast.makeText(this, "帳密輸入錯誤", Toast.LENGTH_SHORT).show();
    }
}