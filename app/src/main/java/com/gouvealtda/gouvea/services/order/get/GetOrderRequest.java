package com.gouvealtda.gouvea.services.order.get;

import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.model.ListItemOrder;
import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.retrofit.InitialRetrofit;
import com.gouvealtda.gouvea.services.order.OrderMethodsInterface;
import com.gouvealtda.gouvea.util.LibraryUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class GetOrderRequest {
    private GetOrderCallback getOrderCallback;

    public GetOrderRequest(GetOrderCallback getOrderCallback) {
        this.getOrderCallback = getOrderCallback;
    }

    public void getOrderRequest(String numberOrder) {


        ListItemOrder listItemOrder = new ListItemOrder();
        ArrayList<ItemOrder> listItens = new ArrayList<>();
        ItemOrder itemOrder = new ItemOrder();

        itemOrder.setBarcode("7859459I4");
        itemOrder.setQtd("109");
        itemOrder.setDescription("ALMOFADA PARA CARIMBO AZUL");

        listItens.add(itemOrder);

        listItemOrder.setListItemOder(listItens);

        getOrderCallback.getOrderCallbackSuccess(listItemOrder);
        //execute(numberOrder);
    }

    private void execute(String numberOrder) {
        OrderMethodsInterface orderMethodsInterface = InitialRetrofit.getRetrofitFeature().create(OrderMethodsInterface.class);
        Call<ResponseAPIModel> call = orderMethodsInterface.getOrderByNumber(numberOrder);
        call.enqueue(new Callback<ResponseAPIModel>() {
            @Override
            public void onResponse(Call<ResponseAPIModel> call, retrofit2.Response<ResponseAPIModel> response) {
                Object object = LibraryUtil.parseResponseAPI(response, ListItemOrder.class); // Response API ou SingleSaleModel
                ListItemOrder listItemOrder = LibraryUtil.parseObjectToOtherObject(object, ListItemOrder.class);
                getOrderCallback.getOrderCallbackSuccess(listItemOrder);
            }

            @Override
            public void onFailure(Call<ResponseAPIModel> call, Throwable t) {
                getOrderCallback.getOrderCallbackFail(null);
            }
        });
    }
}
