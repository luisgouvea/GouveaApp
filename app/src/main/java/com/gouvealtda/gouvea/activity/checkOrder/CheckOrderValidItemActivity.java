package com.gouvealtda.gouvea.activity.checkOrder;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.Result;
import com.gouvealtda.gouvea.activity.BaseActivity;
import com.gouvealtda.gouvea.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CheckOrderValidItemActivity extends BaseActivity implements View.OnClickListener {

    private Button btnActiveCamera;
    private Button btnFinishedItem;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_order_valid_item_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.initialInterfaceActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //stopLoaderCustom(false);
    }

    @Override
    public void initialInterfaceActivity() {
        super.initialInterfaceActivity();
        this.setInterfacesFindView();
        this.setHandlerInterface();
    }

    @Override
    public void setInterfacesFindView() {
        btnActiveCamera = findViewById(R.id.btnActiveCamera);
        btnFinishedItem = findViewById(R.id.btnFinishedItem);
    }

    @Override
    public void setHandlerInterface() {
        btnActiveCamera.setOnClickListener(this);
        btnFinishedItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnActiveCamera) {
            //abre a camera

            Intent intentRecoverPassword = new Intent(getContext(),SimpleScannerActivity.class);

            startActivity(intentRecoverPassword); // Start intent without transitions

        } else if (id == R.id.btnFinishedItem) {
            // valida o item
        }
    }
}