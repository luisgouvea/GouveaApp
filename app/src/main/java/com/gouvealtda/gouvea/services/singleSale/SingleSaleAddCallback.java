package com.gouvealtda.gouvea.services.singleSale;

import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.model.SingleSaleModel;

public interface SingleSaleAddCallback {
    void requestSingleSaleAddSuccess(SingleSaleModel authenticationRequest);
    void requestSingleSaleAddFailed(ResponseAPIModel errorResponseAPI);
}
