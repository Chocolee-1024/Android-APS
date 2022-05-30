package com.example.aps.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoPost {
    /**------訂單單號(SO)-------*/
    @SerializedName("so_id")
    private String so_id;
    public String getSo_id() {
        return so_id;
    }



    /**------製令單號(MO)-------*/
    @SerializedName("mo_id")
    private String mo_id;
    public String getMo_id() {
        return mo_id;
    }



    /**------母件代碼-------*/
    @SerializedName("item_id")
    private String item_id;
    public String getItem_id() {
        return item_id;
    }



    /**------母件名稱-------*/
    @SerializedName("item_name")
    private String item_name;
    public String getItem_name() {
        return item_name;
    }



    /**------上線日期-------*/
    @SerializedName("online_date")
    private String online_date;
    public String getOnline_date() {
        return online_date;
    }




    /**------結關日期-------*/
    @SerializedName("demand_complete_date")
    private String demand_complete_date;
    public String getDemand_complete_date() {
        return demand_complete_date;
    }

    /**------數量-------*/
    @SerializedName("qty")
    private String qty;
    public String getQty() {
        return qty;
    }



    /**------客戶名稱-------*/
    @SerializedName("customer")
    private String customer;
    public String getCustomer() {
        return customer;
    }



    /**------相關工藝路線-------*/
    @SerializedName("related_tech_route")
    private RelatedTechRoute relatedTechRoute;


    public RelatedTechRoute getRelatedTechRoute() {
        return relatedTechRoute;
    }

    public class RelatedTechRoute{

        @SerializedName("tech_routing_name")
        private String tech_routing_name;
        public String getTech_routing_name() {
            return tech_routing_name;
        }
    }
}
