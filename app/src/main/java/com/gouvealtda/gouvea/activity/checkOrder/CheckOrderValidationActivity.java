package com.gouvealtda.gouvea.activity.checkOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gouvealtda.gouvea.activity.BaseActivity;
import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.model.ListItemOrder;
import com.gouvealtda.gouvea.recyclerView.listItemOrder.ListItemOrderAdapter;

import java.util.ArrayList;

public class CheckOrderValidationActivity extends BaseActivity implements View.OnClickListener {

    private Button btnAddItem;
    private Button btnValidationOrder;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;


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
        //checkOrderCache();
        if (listItemOrderCurrent != null && listItemOrderCurrent.size() > 0) {
            ListItemOrderAdapter listItemOrderAdapter = new ListItemOrderAdapter(getContext(), listItemOrderCurrent);
            recyclerView.setAdapter(listItemOrderAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //stopLoaderCustom(false);
    }

    private void checkOrderCache() {
        //TODO: FAZER GET NA LISTA EM MEMORIA
    }

    @Override
    public void initialInterfaceActivity() {
        super.initialInterfaceActivity();
        this.setInterfacesFindView();
        this.setHandlerInterface();
    }

    @Override
    public void setInterfacesFindView() {
//        btnAddItem = findViewById(R.id.btnAddItem);
//        btnValidationOrder = findViewById(R.id.btnValidationOrder);
        recyclerView = findViewById(R.id.idRecyclerView);
        fab = findViewById(R.id.fab);
    }

    @Override
    public void setHandlerInterface() {
//        btnAddItem.setOnClickListener(this);
//        btnValidationOrder.setOnClickListener(this);
        fab.setOnClickListener(this);
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
        } else if (id == R.id.fab) {
            //open form
            Intent intent = new Intent(this, CheckOrderValidItemActivity.class);
            startActivity(intent);
        }
    }
}
