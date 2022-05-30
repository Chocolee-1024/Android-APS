package com.example.aps.Contract;

import com.example.aps.API.MoPost;
import com.example.aps.API.TableQueryPost;

import java.util.List;

public interface TableQueryContract {
    interface view{
        void takeQuerySaleOrder(List<TableQueryPost> listSaleOrder);
        void takeQueryCustomerName(List<TableQueryPost> listCustomerName);
        void takeQueryOrderNumber();
        void noData();
        void inputNoData();
    }
    interface presenter{
        void getQuerySaleOrder(String so_id);
        void getQueryCustomerName(String customer_name);
        void getQueryOrderNumber(String so_id,String onlineDate,String customer);
    }
}
