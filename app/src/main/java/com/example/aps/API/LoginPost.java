package com.example.aps.API;

import com.google.gson.annotations.SerializedName;

public class LoginPost {

    public LoginPost(String account, String password){
        this.account=account;
        this.password=password;
    }
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    @SerializedName("account")
    private String  account;

    public void setAccount(String account) {
        this.account = account;
    }

    @SerializedName("password")
    private String  password;

    public void setPassword(String password) {
        this.password = password;
    }


}
