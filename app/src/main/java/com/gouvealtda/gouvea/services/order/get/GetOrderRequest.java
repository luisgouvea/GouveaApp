package com.gouvealtda.gouvea.services.order.get;

import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.model.SingleSaleModel;
import com.gouvealtda.gouvea.retrofit.InitialRetrofit;
import com.gouvealtda.gouvea.services.order.OrderMethodsInterface;
import com.gouvealtda.gouvea.services.singleSale.SingleSaleMethodsInterface;
import com.gouvealtda.gouvea.util.LibraryUtil;

import retrofit2.Call;
import retrofit2.Callback;

public class GetOrderRequest {
    private GetOrderCallback getOrderCallback;

    public GetOrderRequest(GetOrderCallback getOrderCallback) {
        this.getOrderCallback = getOrderCallback;
    }

    public void getOrderRequest(String numberOrder) {
        OrderMethodsInterface orderMethodsInterface = InitialRetrofit.getRetrofitFeature().create(OrderMethodsInterface.class);
        Call<ResponseAPIModel> call = orderMethodsInterface.getOrderByNumber(numberOrder);
        call.enqueue(new Callback<ResponseAPIModel>() {
            @Override
            public void onResponse(Call<ResponseAPIModel> call, retrofit2.Response<ResponseAPIModel> response) {
                Object object = LibraryUtil.parseResponseAPI(response, SingleSaleModel.class); // Response API ou SingleSaleModel
                SingleSaleModel singleSaleAdd = LibraryUtil.parseObjectToOtherObject(object, SingleSaleModel.class);
                getOrderCallback.getOrderCallbackSuccess(singleSaleAdd);
            }

            @Override
            public void onFailure(Call<ResponseAPIModel> call, Throwable t) {
                getOrderCallback.getOrderCallbackFail(null);
            }
        });
    }
}
