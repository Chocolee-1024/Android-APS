package com.example.aps.API;

import com.google.gson.annotations.SerializedName;

public class LoginStaffInfoPost {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
