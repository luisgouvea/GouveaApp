package com.gouvealtda.gouvea.services.singleSale;

import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.model.SingleSaleModel;
import com.gouvealtda.gouvea.services.ServicesURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SingleSaleMethodsInterface {
    @POST(ServicesURL.SINGLE_SALE_ADD)
    Call<ResponseAPIModel> addSingleSale(@Body SingleSaleModel singleSale);
}
