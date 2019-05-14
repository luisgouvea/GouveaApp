package com.gouvealtda.gouvea.activity.checkOrder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gouvealtda.gouvea.BaseActivity;
import com.gouvealtda.gouvea.R;

public class CheckOrderValidationActivity extends BaseActivity implements View.OnClickListener {

    private Button btnAddItem;
    private Button btnValidationOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_order_validation_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.initialInterfaceActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLoaderCustom(false);
    }

    @Override
    public void initialInterfaceActivity() {
        super.initialInterfaceActivity();
        this.setInterfacesFindView();
        this.setHandlerInterface();
    }

    @Override
    public void setInterfacesFindView() {
        btnAddItem = findViewById(R.id.btnAddItem);
        btnValidationOrder = findViewById(R.id.btnValidationOrder);
    }

    @Override
    public void setHandlerInterface() {
        btnAddItem.setOnClickListener(this);
        btnValidationOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnAddItem) {
            //open dialog

        } else if (id == R.id.btnValidationOrder) {
            // valida o orcamento
        }
    }
}
