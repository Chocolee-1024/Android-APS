package com.example.aps.API;

import java.util.List;
import java.util.Queue;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
        //登入獲取Token
        @POST("auth/login")
        Single<Response<LoginPost>> login(@Body LoginPost loginPost);

        //獲取登入人員資料
        @GET("auth/")
        Single<Response<LoginStaffInfoPost>> loginStaffInfo(@Query("token")String token);

        //獲取模糊訂單單號
        @GET("app-search-so")
        Single <Response<List<TableQueryPost>>> getSaleOrder(@Query("token") String token
                                                           ,@Query("so_id") String so_id);

        //獲取模糊客戶名稱
        @GET("app-search-customer")
        Single <Response<List<TableQueryPost>>> getCustomerName(@Query("token") String token
                                                                ,@Query("customer_name") String customer_name);

        //獲取Mo訂單資料
        @GET("get-manufacture")
        Single <Response<List<MoPost>>> getOrderNumber(@Query("token") String token
                                                       ,@Query("customer") String customer
                                                       ,@Query("sale_order")String sale_order
                                                       ,@Query("online_date")String online_date);
        @GET("get-current-stage-com")
        Single<Response<List<TablePost>>>getCurrStageMO(@Query("token")String token
                                                        ,@Query("item_id")String item_id);

}
