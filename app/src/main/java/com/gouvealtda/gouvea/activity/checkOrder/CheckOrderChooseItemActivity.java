package com.gouvealtda.gouvea.activity.checkOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.activity.BaseActivity;
import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.recyclerView.listItemChoose.ListItemChooseAdapter;
import com.gouvealtda.gouvea.util.LibraryUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CheckOrderChooseItemActivity extends BaseActivity implements ListItemChooseAdapter.CbListChooseAdapter {

    private RecyclerView recylerViewChooseBarcode;
    private LinearLayout linearLayoutNoItens;
    private LinearLayout linearLayoutRecyclerView;

    private String barcodeTarget = "";
    private ArrayList<ItemOrder> listItemOrder = new ArrayList<>();
    private ListItemChooseAdapter.CbListChooseAdapter cbListChooseAdapter = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_order_choose_item_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.initialInterfaceActivity();
        getListPrepareItensOrder();
        buildRecyclerView();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void initialInterfaceActivity() {
        super.initialInterfaceActivity();
        this.setInterfacesFindView();
    }

    @Override
    public void setInterfacesFindView() {
        recylerViewChooseBarcode = findViewById(R.id.idRecyclerView);
        linearLayoutNoItens = findViewById(R.id.linearLayoutNoItens);
        linearLayoutRecyclerView = findViewById(R.id.linearLayoutRecyclerView);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CheckOrderValidItemActivity.class);
        intent.putExtra("barcodeChoose", barcodeTarget);
        setResult(-1, intent);
        finish();
    }

    private void getListPrepareItensOrder() {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ItemOrder>>() {
        }.getType();
        ArrayList<ItemOrder> listItemOrderPrepare = gson.fromJson(LibraryUtil.GetPreference(LibraryUtil.LIST_PREPARE_ITENS_ORDER, CheckOrderChooseItemActivity.this), listType);
        if (listItemOrderPrepare != null && listItemOrderPrepare.size() > 0) {
            this.listItemOrder = listItemOrderPrepare;
        }
    }
    private void buildRecyclerView() {
        if (listItemOrder != null && listItemOrder.size() > 0) {
            ListItemChooseAdapter listItemChooseAdapter = new ListItemChooseAdapter(listItemOrder, cbListChooseAdapter);
            recylerViewChooseBarcode.setAdapter(listItemChooseAdapter);
            recylerViewChooseBarcode.setLayoutManager(new LinearLayoutManager(getActivity()));
            showItensInList();
        } else {
            showLayoutNoItens();
        }
    }

    private void showItensInList() {
        linearLayoutNoItens.setVisibility(View.GONE);
        linearLayoutRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showLayoutNoItens(){
        linearLayoutNoItens.setVisibility(View.VISIBLE);
        linearLayoutRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void itemChoosed(ItemOrder itemOrder) {
        barcodeTarget = itemOrder.getBarcode();
        this.onBackPressed();
    }
}
