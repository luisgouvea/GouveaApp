package com.gouvealtda.gouvea.services.order;

import com.gouvealtda.gouvea.model.ListItemOrder;
import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.services.ServicesURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderMethodsInterface {
    @GET(ServicesURL.GET_ORDER_BY_NUMBER)
    Call<ListItemOrder> getOrderByNumber(@Query("numberOrder") String numberOrder);
}