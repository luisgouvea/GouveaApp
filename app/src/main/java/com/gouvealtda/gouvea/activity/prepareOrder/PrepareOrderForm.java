package com.gouvealtda.gouvea.activity.prepareOrder;

import android.os.Bundle;
import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.activity.BaseActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class PrepareOrderForm extends BaseActivity {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_prepare_order_form);
    }
}
