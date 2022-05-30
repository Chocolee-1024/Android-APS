package com.example.aps;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

public class TokenAndName {
    private Context context;
    private SharedPreferences sPref;

    public TokenAndName(Context context) {
        this.context = context;
        //只讓APS這個檔案能存取
        sPref = this.context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
    }

    //存取Token
    public void getToken(String token){
        Log.e("www",token);
        sPref.edit().putString("token",token).apply();

    }

    public String takeToken() {
        return sPref.getString("token","");
    }


    //存取登入人員名稱
    public void getName(String name){

        sPref.edit().putString("user_name",name).apply();
        Log.e("www",name);
    }

    public String takeName() {
        return sPref.getString("user_name","");
    }
}
