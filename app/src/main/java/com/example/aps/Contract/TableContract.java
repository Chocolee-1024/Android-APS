package com.example.aps.Contract;

import com.example.aps.API.MoPost;
import com.example.aps.API.TablePost;

import java.util.List;

import retrofit2.Response;

public interface TableContract {
    interface view{
        void takeBOM(List<TablePost> listBom);

    }
    interface presenter{
        void gitItemId(String itemId);
    }

    interface adapter{
        void showBOM(List<TablePost> listBom);
    }
}
