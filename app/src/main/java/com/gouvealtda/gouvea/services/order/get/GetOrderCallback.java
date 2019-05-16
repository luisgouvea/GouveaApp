package com.gouvealtda.gouvea.services.order.get;

import com.gouvealtda.gouvea.model.ListItemOrder;
import com.gouvealtda.gouvea.model.ResponseAPIModel;

public interface GetOrderCallback {
    void getOrderCallbackSuccess(ListItemOrder listItemOrder);
    void getOrderCallbackFail(ResponseAPIModel errorResponseAPI);
}
