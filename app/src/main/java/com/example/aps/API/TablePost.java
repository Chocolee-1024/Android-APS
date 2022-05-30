package com.example.aps.API;

import com.google.gson.annotations.SerializedName;

public class TablePost {

    @SerializedName("unit_id") //單位 ex:PCS
    private String unitId;

    public String getUnitId() {
        return unitId;
    }

    @SerializedName("unit_qty") //單位用量 ex:1.00
    private String unitQty;

    public String getUnitQty() {
        return unitQty;
    }
    @SerializedName("nuse_qty") //需領用量 ex:3.00
    private String nuseQty;

    public String getNuseQty() {
        return nuseQty;
    }

    @SerializedName("parent")
    private Parent parent;

    public Parent getParent() {
        return parent;
    }


    public  class Parent{
        @SerializedName("bomkey_name")
        private String bomkeyName;

        public String getBomkeyName() {
            return bomkeyName;
        }

        @SerializedName("material_id")
        private String materialId;

        public String getMaterialId() {
            return materialId;
        }
    }

}
