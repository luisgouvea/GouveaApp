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

        itemOrder.setBarcode("123");
        itemOrder.setQtd("1");
        itemOrder.setDescription("ALMOFADA PARA CARIMBO AZUL");

        listItens.add(itemOrder);


        ItemOrder itemOrder2 = new ItemOrder();
        itemOrder2.setBarcode("321");
        itemOrder2.setQtd("1");
        itemOrder2.setDescription("TECLADO USB");

        listItens.add(itemOrder2);


        listItemOrder.setListItemOder(listItens);

        getOrderCallback.getOrderCallbackSuccess(listItemOrder);
        //execute(numberOrder);
    }

    private void execute(String numberOrder) {
        OrderMethodsInterface orderMethodsInterface = InitialRetrofit.getRetrofitFeature().create(OrderMethodsInterface.class);
        Call<ResponseAPIModel> call = orderMethodsInterface.getOrderByNumber(numberOrder);
        call.enqueue(new Callback<ResponseAPIModel>() {
            @Override
            public void onResponse(Call<ListItemOrder> call, retrofit2.Response<ListItemOrder> response) {
                ListItemOrder listItemOrder = LibraryUtil.parseObjectToOtherObject(response.body(), ListItemOrder.class);
                getOrderCallback.getOrderCallbackSuccess(listItemOrder);
            }

            @Override
            public void onFailure(Call<ResponseAPIModel> call, Throwable t) {
                getOrderCallback.getOrderCallbackFail(null);
            }
        });
    }
}
