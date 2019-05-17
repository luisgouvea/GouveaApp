package com.gouvealtda.gouvea.activity.checkOrder;

import android.os.Bundle;

import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.activity.BaseActivity;

public class CheckOrderChooseBarcodeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_order_choose_barcode_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.initialInterfaceActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
