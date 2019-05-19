package com.gouvealtda.gouvea.activity.checkOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.gouvealtda.gouvea.activity.BaseActivity;
import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.model.ListItemOrder;
import com.gouvealtda.gouvea.recyclerView.listItemOrder.ListItemOrderAdapter;

import java.util.ArrayList;

public class CheckOrderValidationActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private LinearLayout linearLayoutNoItens;
    private LinearLayout linearLayoutRecyclerView;

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
        buildRecyclerView();
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
        recyclerView = findViewById(R.id.idRecyclerView);
        fab = findViewById(R.id.fab);
        linearLayoutNoItens = findViewById(R.id.linearLayoutNoItens);
        linearLayoutRecyclerView = findViewById(R.id.linearLayoutRecyclerView);
    }

    @Override
    public void setHandlerInterface() {
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

    private void buildRecyclerView() {
        if (listItemOrderCurrent != null && listItemOrderCurrent.size() > 0) {
            ListItemOrderAdapter listItemOrderAdapter = new ListItemOrderAdapter(listItemOrderCurrent);
            recyclerView.setAdapter(listItemOrderAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    public void onClick(View v) {
        int id = v.getId();
       if (id == R.id.fab) {
            //open form
            Intent intent = new Intent(this, CheckOrderValidItemActivity.class);
            //startActivity(intent);
           startActivityForResult(intent,1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                buildRecyclerView();
            }
        }
    }

    @Override
    public void onBackPressed() {
        listItemOrderCurrent = new ArrayList<>();
        finish();
    }
}
