package com.gouvealtda.gouvea.services.order;

import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.services.ServicesURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderMethodsInterface {
    @POST(ServicesURL.SINGLE_SALE_ADD)
    Call<ResponseAPIModel> getOrderByNumber(@Body String numberOrder);
}