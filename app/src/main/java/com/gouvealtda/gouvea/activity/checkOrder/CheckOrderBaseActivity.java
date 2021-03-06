package com.gouvealtda.gouvea.activity.checkOrder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gouvealtda.gouvea.activity.BaseActivity;
import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.model.ListItemOrder;
import com.gouvealtda.gouvea.model.ResponseAPIModel;
import com.gouvealtda.gouvea.services.order.get.GetOrderCallback;
import com.gouvealtda.gouvea.services.order.get.GetOrderRequest;
import com.gouvealtda.gouvea.util.LibraryUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CheckOrderBaseActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, GetOrderCallback {

    private Button buttonBeginCheckOrder;
    private EditText editTextNumerOrder;

    private ProgressDialog progressDialog;
    private AlertDialog.Builder dialogNumberOrder;

    private GetOrderCallback callback = this;
    public static String numberOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.initialInterfaceActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLoaderRequest();
        //stopLoaderCustom(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLoaderRequest();
    }

    @Override
    public void initialInterfaceActivity() {
        super.initialInterfaceActivity();
        this.setInterfacesFindView();
        this.setHandlerInterface();
    }

    @Override
    public void setInterfacesFindView() {
        buttonBeginCheckOrder = findViewById(R.id.btnBeginCheckOrder);
        editTextNumerOrder = findViewById(R.id.editTextNumerOrder);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Aguarde");
        progressDialog.setMessage("Por favor, estou buscando o seu orçamento!");
        progressDialog.setCancelable(false);

        dialogNumberOrder = new AlertDialog.Builder(getContext());
        dialogNumberOrder.setMessage("Número de orçamento inválido")
                .setTitle("Ops, algo errado.");
        dialogNumberOrder.setPositiveButton("OK", null);
    }

    @Override
    public void setHandlerInterface() {
        buttonBeginCheckOrder.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_check_order) {
            // Handle the camera action
        } else if (id == R.id.nav_separate_order) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnBeginCheckOrder) {
            checkFieldNumberOrder();
        }
    }

    private void checkFieldNumberOrder() {
        if (editTextNumerOrder != null) {
            String numberOrder = editTextNumerOrder.getText().toString();
            if (!numberOrder.isEmpty() && numberOrder.length() > 0 && !numberOrder.equals("")) {
                //tenho em si o numero do orcamento
                //4930
                //faz o request
                this.numberOrder = numberOrder;
                startLoaderRequest();
                getOrderRequest(numberOrder);
            } else {
                //preencha corretamente o campo
                dialogNumberOrder.show();
            }
        }
    }

    public void getOrderRequest(String numberOrder) {
        GetOrderRequest getOrderRequest = new GetOrderRequest(callback);
        getOrderRequest.getOrderRequest(numberOrder);
    }

    private void startLoaderRequest() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void stopLoaderRequest() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void checkOrderCache() {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ItemOrder>>() {
        }.getType();
        ArrayList<ItemOrder> listItemOrderCurrentCache = gson.fromJson(LibraryUtil.GetPreference(CheckOrderBaseActivity.numberOrder, CheckOrderBaseActivity.this), listType);
        if (listItemOrderCurrentCache != null && listItemOrderCurrentCache.size() > 0) {
            openAlertDialogChooseImport(listItemOrderCurrentCache); // tem algo
        } else {
            startValidationActivity();
        }
    }

    public void openAlertDialogChooseImport(final ArrayList<ItemOrder> listItemOrderCurrentCache) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage("Esse orçamento não foi VALIDADO, deseja importar?");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CheckOrderValidationActivity.listItemOrderCurrent = listItemOrderCurrentCache;
                startValidationActivity();
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        alertDialog.show();
    }

    public void startValidationActivity() {
        Intent intent = new Intent(this, CheckOrderValidationActivity.class);
        startActivity(intent);
    }

    private void checkResponseAPI(ListItemOrder listItemOrder) {
        if (listItemOrder != null) {
            CheckOrderValidationActivity.listItemOrder = listItemOrder;
            checkOrderCache();
        }
    }

    @Override
    public void getOrderCallbackSuccess(ListItemOrder listItemOrder) {
        stopLoaderRequest();
        checkResponseAPI(listItemOrder);
    }

    @Override
    public void getOrderCallbackFail(ResponseAPIModel errorResponseAPI) {
        stopLoaderRequest();
    }

}
