package com.example.aps.Contract;

import com.example.aps.API.MoPost;

import java.util.List;

public interface TableRecyclerviewContract {
    interface view {
        void selectTable(int position);
    }
    interface adapter{
        void loadData(List<MoPost> moData);
    }
}
