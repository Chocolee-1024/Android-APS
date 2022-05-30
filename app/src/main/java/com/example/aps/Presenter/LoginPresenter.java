package com.example.aps.Presenter;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.example.aps.API.ApiClient;
import com.example.aps.API.ApiService;
import com.example.aps.API.LoginPost;
import com.example.aps.API.LoginStaffInfoPost;
import com.example.aps.Contract.LoginContract;
import com.example.aps.TokenAndName;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginPresenter extends ApiClient implements LoginContract.presenter {
    private LoginContract.view view;
    private ApiService apiService;
    private TokenAndName tokenAndName;
    private String token;

    public LoginPresenter(LoginContract.view view){
        this.view=view;
        apiService=new ApiClient().getApiService();
        tokenAndName= new TokenAndName((Context) view);
    }
    //從view傳入帳號密碼
    @Override
    public void login(String account, String password) {
        //判斷帳密是否空值

        if(account.equals("") || password.equals("")){
            Toast.makeText((Context)view, "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
            return;//沒帳密不Call Api
        }

        // 向api發送login請求
        apiService.login(new LoginPost(account,password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<LoginPost>>() {
                    @Override
                    public void onSuccess(Response<LoginPost> loginPostResponse) {
                        //登入成功200
                        if(loginPostResponse.code()==200 ) {
                            //拿取Token
                            token=loginPostResponse.body().getToken();
                            //把token存入Token&&Name
                            tokenAndName.getToken(token);
                            //token傳入loginStaffInfo(抓取登入人員資料)
                            loginStaffInfo(token);
                            SystemClock.sleep(44);
                            //回View跳頁
                            view.loginSuccess();
                        }
                        //帳密錯誤401
                        else if(loginPostResponse.code()==401)
                            view.loginFail();
                        else
                            Log.e("www", "未知狀況");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof ConnectException) {
                            Log.e("www", "請連上網路!");
                        } else if(e instanceof SocketTimeoutException) {
                            Log.e("www", "連不上伺服器!!!");
                        } else {
                            Log.e("www", "Login Failure：" + e);
                        }
                    }
                });
    }
    // 向api獲取登入人員資料
        public void loginStaffInfo(String token) {
            apiService.loginStaffInfo(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<Response<LoginStaffInfoPost>>() {

                        @Override
                        public void onSuccess(Response<LoginStaffInfoPost> loginStaffInfoPostResponse) {
                            //獲取成功200
                            if(loginStaffInfoPostResponse.code()==200) {
                                //把登入人員名稱存入Token&&Name
                                tokenAndName.getName(loginStaffInfoPostResponse.body().getName());
                            }
                            else
                                Log.e("www","獲取登入人員資料失敗");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("www","獲取登入人員資料失敗");
                        }
                    });
        }
}
