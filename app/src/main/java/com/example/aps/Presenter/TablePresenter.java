package com.example.aps.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.aps.API.ApiClient;
import com.example.aps.API.ApiService;
import com.example.aps.API.MoPost;
import com.example.aps.API.TablePost;
import com.example.aps.Contract.LoginContract;
import com.example.aps.Contract.TableContract;
import com.example.aps.MoData;
import com.example.aps.TokenAndName;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TablePresenter extends ApiClient implements TableContract.presenter {
    private TableContract.view  view;
    private ApiService apiService;
    private TokenAndName tokenAndName;
    private String token;
    private List<TablePost> listBom;

    public TablePresenter(TableContract.view view){
        this.view=view;
        apiService=new ApiClient().getApiService();
        tokenAndName=new TokenAndName((Context) view);
        token=tokenAndName.takeToken();
    }

    @Override
    public void gitItemId(String item_id) {
        apiService.getCurrStageMO(token,item_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<List<TablePost>>>() {
                    @Override
                    public void onSuccess(Response<List<TablePost>> listResponse) {
                        if(listResponse.code()==200){
                            Log.e("www", "本階製令 獲取成功!!!");
                            listBom=listResponse.body();
                            view.takeBOM(listBom);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("www", "本階製令 獲取成功!!!");
                    }
                });

    }
}
