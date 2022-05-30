package com.example.aps.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.aps.API.ApiClient;
import com.example.aps.API.ApiService;
import com.example.aps.API.MoPost;
import com.example.aps.API.TableQueryPost;
import com.example.aps.Contract.TableQueryContract;
import com.example.aps.MoData;
import com.example.aps.TokenAndName;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TableQueryPresenter extends ApiClient implements TableQueryContract.presenter {
    private TableQueryContract.view view;
    private ApiService apiService;
    private TokenAndName tokenAndName;
    private MoData moData;
    private String token;
    private List<TableQueryPost> listSaleOrder;
    private List<TableQueryPost> listCustomerName;
    private List<MoPost> listOrderNumber;

    public  TableQueryPresenter(TableQueryContract.view view){
        this.view=view;
        apiService=new ApiClient().getApiService();
        tokenAndName= new TokenAndName((Context) view);
        token=tokenAndName.takeToken();
        moData=new MoData();
    }
    //從view傳進so_id
    @Override
    public void getQuerySaleOrder(String so_id) {
        // 向api獲取"模糊訂單單號"
        apiService.getSaleOrder(token,so_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<List<TableQueryPost>>>() {
                    @Override
                    public void onSuccess(Response<List<TableQueryPost>> listResponse) {
                        if(listResponse.code()==200){
                            Log.e("www", "模糊查詢訂單單號 獲取成功!!!");
                            listSaleOrder=listResponse.body();
                            //獲取成功傳回view
                            view.takeQuerySaleOrder(listSaleOrder);
                        }
                        else
                            Log.e("www", "模糊查詢訂單單號 獲取失敗!!!");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("www", "模糊查詢訂單單號 獲取失敗!!!");
                    }
                });

    }

    //從view傳進CustomerName
    @Override
    public void getQueryCustomerName(String customer_name) {
        // 向api獲取"模糊客戶名稱"
        apiService.getCustomerName(token,customer_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<List<TableQueryPost>>>() {
                    @Override
                    public void onSuccess(Response<List<TableQueryPost>> listResponse) {
                        if(listResponse.code()==200){
                            Log.e("www", "模糊查詢客戶名稱 獲取成功!!!");
                            listCustomerName=listResponse.body();
                            //獲取成功傳回view
                            view.takeQueryCustomerName(listCustomerName);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("www", "模糊查詢客戶名稱 獲取失敗!!!");
                    }
                });
    }

    //從view傳進so_id，onlineDate，customer_name
    @Override
    public void getQueryOrderNumber(String sale_order, String online_date, String customer) {
        //判斷使否有值輸入
        if(sale_order.equals("")&&online_date.equals("")&&customer.equals(""))
            view.inputNoData();
        // 向api獲取"製令單號"
        else {
            Log.e("www",customer);
            apiService.getOrderNumber(token,customer,sale_order,online_date)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<Response<List<MoPost>>>() {
                        @Override
                        public void onSuccess(Response<List<MoPost>> listResponse) {
                            Log.e("www", "製令單號(Mo) 獲取成功!!!");
                            if (listResponse.code() == 200) {
                                Log.e("www",String.valueOf(listResponse.body().size()));
                                listOrderNumber = listResponse.body();
                                //沒有資料
                                if (listResponse.body().size() == 0)
                                    view.noData();
                                //獲取成功傳回view
                                else {
                                    moData.getMoData(listOrderNumber);
                                    view.takeQueryOrderNumber();
                                }

                            } else
                                Log.e("www", "製令單號(Mo) 獲取失敗!!!");

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("www", "製令單號(Mo) 獲取失敗!!!");
                        }
                    });
        }


    }
}
