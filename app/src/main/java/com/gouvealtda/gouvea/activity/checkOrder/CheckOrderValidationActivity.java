package com.gouvealtda.gouvea.activity.checkOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gouvealtda.gouvea.activity.BaseActivity;
import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.model.ListItemOrder;

import java.util.ArrayList;

public class CheckOrderValidationActivity extends BaseActivity implements View.OnClickListener {

    private Button btnAddItem;
    private Button btnValidationOrder;

    public static ListItemOrder listItemOrder;
    public static ArrayList<ItemOrder> listItemOrderCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.algoai);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.initialInterfaceActivity();
        CheckOrderValidItemActivity.listItemOrder = this.listItemOrder;
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
        listItemOrderCurrent = new ArrayList<>();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.validation_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    public static ListItemOrder getListItemOrder() {
        return listItemOrder;
    }

    public static ArrayList<ItemOrder> getListItemOrderCurrent() {
        return listItemOrderCurrent;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnAddItem) {
            //open form
            Intent intent = new Intent(this, CheckOrderValidItemActivity.class);
            startActivity(intent);
        } else if (id == R.id.btnValidationOrder) {
            // valida o orcamento
        }
    }
}
