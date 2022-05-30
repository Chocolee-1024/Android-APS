package com.example.aps.API;

import com.google.gson.annotations.SerializedName;

public class TableQueryPost {

    @SerializedName("so_id")
    private String so_id;

    public String getSo_id() {
        return so_id;
    }

    @SerializedName("customer_name")
    private String customer_name;

    public String getCustomer_name() {
        return customer_name;
    }

}
