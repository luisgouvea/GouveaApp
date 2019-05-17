package com.gouvealtda.gouvea.activity.checkOrder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gouvealtda.gouvea.activity.BaseActivity;
import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.model.ListItemOrder;
import com.gouvealtda.gouvea.recyclerView.listItemOrder.ListItemOrderAdapter;
import com.gouvealtda.gouvea.util.LibraryUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CheckOrderValidationActivity extends BaseActivity implements View.OnClickListener {

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
        buildRecyclerView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //stopLoaderCustom(false);
    }

    private void checkOrderCache() {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ItemOrder>>() {
        }.getType();
        ArrayList<ItemOrder> listItemOrderCurrentCache = gson.fromJson(LibraryUtil.GetPreference(CheckOrderBaseActivity.numberOrder, CheckOrderValidationActivity.this), listType);
        if (listItemOrderCurrentCache != null && listItemOrderCurrentCache.size() > 0) { // tem algo
            openAlertDialogChooseImport(listItemOrderCurrentCache);
        }
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
            ListItemOrderAdapter listItemOrderAdapter = new ListItemOrderAdapter(getContext(), listItemOrderCurrent);
            recyclerView.setAdapter(listItemOrderAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    public void openAlertDialogChooseImport(final ArrayList<ItemOrder> listItemOrderCurrentCache) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Esse orçamento não foi VALIDADO, deseja importar?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CheckOrderValidationActivity.listItemOrderCurrent = listItemOrderCurrentCache;
                buildRecyclerView();
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        alertDialog.show();
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
