package com.example.aps.Contract;

public interface LoginContract {
    interface view {
        void loginSuccess();
        void loginFail();
    }
    interface presenter {
        void login(String account,String password);
    }
}
