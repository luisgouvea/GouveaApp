package com.gouvealtda.gouvea.activity.checkOrder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

import com.gouvealtda.gouvea.BaseActivity;
import com.gouvealtda.gouvea.R;

public class CheckOrderBaseActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button buttonBeginCheckOrder;
    private EditText editTextNumerOrder;

    private ProgressDialog progressDialog;
    private AlertDialog.Builder dialogNumberOrder;

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
        buttonBeginCheckOrder = findViewById(R.id.btnBeginCheckOrder);
        editTextNumerOrder = findViewById(R.id.editTextNumerOrder);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Ops");
        progressDialog.setMessage("Não foi encontrado o orçamento");
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
            } else {
                //preencha corretamente o campo
                dialogNumberOrder.show();
            }
        }
    }
}
