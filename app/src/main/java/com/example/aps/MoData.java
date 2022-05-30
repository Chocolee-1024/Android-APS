package com.example.aps;

import android.content.Context;
import android.util.Log;

import com.example.aps.API.MoPost;

import java.util.List;

public class MoData {
    private static List<MoPost> listOrderNumber;
    //存取Mo
    public void getMoData(List<MoPost> listOrderNumber){
        this.listOrderNumber=listOrderNumber;

    }
    public List<MoPost> takeMoData(){
        return listOrderNumber;
    }

}
