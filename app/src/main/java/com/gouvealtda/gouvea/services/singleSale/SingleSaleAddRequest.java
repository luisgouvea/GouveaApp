package com.gouvealtda.gouvea.services.singleSale;

import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.model.SingleSaleModel;
import com.gouvealtda.gouvea.retrofit.InitialRetrofit;
import com.gouvealtda.gouvea.util.LibraryUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleSaleAddRequest {
    private SingleSaleAddCallback singleSaleAddCallbackReceive;
    private SingleSaleModel singleSale;

    public SingleSaleAddRequest(SingleSaleAddCallback singleSaleAddCallback) {
        this.singleSaleAddCallbackReceive = singleSaleAddCallback;
    }

    public void addSingleSale(double amount, int status) {
        createObject(amount, status);
    }

    private void createObject(double amount, int status) {
        SingleSaleMethodsInterface singleSaleMethodsInterface = InitialRetrofit.getRetrofitFeature().create(SingleSaleMethodsInterface.class);
        Call<ResponseAPIModel> call = singleSaleMethodsInterface.addSingleSale(singleSale);
        call.enqueue(new Callback<ResponseAPIModel>() {
            @Override
            public void onResponse(Call<ResponseAPIModel> call, retrofit2.Response<ResponseAPIModel> response) {
                Object object = LibraryUtil.parseResponseAPI(response, SingleSaleModel.class); // Response API ou SingleSaleModel
                SingleSaleModel singleSaleAdd = LibraryUtil.parseObjectToOtherObject(object, SingleSaleModel.class);
                singleSaleAddCallbackReceive.requestSingleSaleAddSuccess(singleSaleAdd);
            }

            @Override
            public void onFailure(Call<ResponseAPIModel> call, Throwable t) {
                singleSaleAddCallbackReceive.requestSingleSaleAddFailed(null);
            }
        });
    }
}
