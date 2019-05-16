package com.gouvealtda.gouvea.services.order.get;

import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.model.SingleSaleModel;

public interface GetOrderCallback {
    void getOrderCallbackSuccess(SingleSaleModel authenticationRequest);
    void getOrderCallbackFail(ResponseAPIModel errorResponseAPI);
}
