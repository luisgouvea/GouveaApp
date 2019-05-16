package com.gouvealtda.gouvea.activity.checkOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gouvealtda.gouvea.activity.BaseActivity;
import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.model.ListItemOrder;
import com.gouvealtda.gouvea.util.LibraryUtil;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CheckOrderValidItemActivity extends BaseActivity implements View.OnClickListener {

    /*
    Interface controll
     */
    private Button btnActiveCamera;
    private Button btnFinishedItem;
    private EditText editTextBarcode;
    private EditText editTextQtdItem;

    private ZXingScannerView scannerView;
    public static ListItemOrder listItemOrder;
    public static String barcode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_order_valid_item_activity);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.initialInterfaceActivity();
        if (!barcode.equals("")) {
            editTextBarcode.setText(barcode);
        }
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
        editTextBarcode = findViewById(R.id.editTextBarcode);
        editTextQtdItem = findViewById(R.id.editTextQtdItem);
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

            Intent intentRecoverPassword = new Intent(getContext(), SimpleScannerActivity.class);

            startActivity(intentRecoverPassword); // Start intent without transitions

        } else if (id == R.id.btnFinishedItem) {
            // valida o item
            clickBtnValideItem();
        }
    }

    public void clickBtnValideItem() {
        if (fieldsFormFilledAndOk()) {
            executeValidItem();
        }
    }

    private Boolean fieldsFormFilledAndOk() {
        if (editTextBarcode != null && editTextQtdItem != null) {
            String barcode = editTextBarcode.getText().toString();
            String qtdItem = editTextQtdItem.getText().toString();
            if (LibraryUtil.IsTextFieldEmpty(editTextBarcode) && LibraryUtil.IsTextFieldEmpty(editTextQtdItem)) {
                Toast.makeText(getContext(), "Preencha todos os campos.",
                        Toast.LENGTH_LONG).show();

            } else if (LibraryUtil.IsTextFieldEmpty(editTextQtdItem) && qtdItem.length() <= 0) {
                Toast.makeText(this, "O campo Quantidade deve ser preenchido corretamente.",
                        Toast.LENGTH_LONG).show();

            //} else if (LibraryUtil.IsTextFieldEmpty(editTextBarcode) && barcode.length() <= 11) {
            } else if (LibraryUtil.IsTextFieldEmpty(editTextBarcode) && barcode.length() <= 0) {
                Toast.makeText(this, "O campo Código de Barras deve ser preenchido corretamente.",
                        Toast.LENGTH_LONG).show();

            } else {
                // nao caiu em nenhum if, entao, ta ok. ta ok?
                return true;
            }
            return false;
        }
        return false;
    }

    public void executeValidItem() {
        String qtdItem = editTextQtdItem.getText().toString();
        String barcode = editTextBarcode.getText().toString();

        ItemOrder itemOrder = checkBarcodeExits(barcode);

        if ( itemOrder != null) { // encontrou na lista
            if (qtdItem.equals(itemOrder.getQtd())) {
                // quatidade bateu
                ArrayList<ItemOrder> listItemOrderCurrent = CheckOrderValidationActivity.getListItemOrderCurrent();
                listItemOrderCurrent.add(itemOrder);

                Toast.makeText(getContext(), "Item correto!!!",
                        Toast.LENGTH_LONG).show();
                //voltar
                CheckOrderValidItemActivity.super.onBackPressed();
            } else {
                Toast.makeText(getContext(), "Quantidade incorreta. ATENÇÃO!!!",
                        Toast.LENGTH_LONG).show();
            }
        } else { // codigo de barras nao encontrado
            Toast.makeText(getContext(), "Código de barras não encontrado no orçamento. ATENÇÃO!!!",
                    Toast.LENGTH_LONG).show();
        }

    }

    /*
     Verificar se tem o barcode na lista do orcamento
     */

    private ItemOrder checkBarcodeExits(String barcode) {
        ListItemOrder listItemOrder = CheckOrderValidationActivity.getListItemOrder();
        ArrayList<ItemOrder> listClippStore = listItemOrder.getListItemOder();
        for (int i = 0; i <= listClippStore.size() - 1; i++) {
            ItemOrder itemOrder = listClippStore.get(i);
            if (itemOrder.getBarcode().equals(barcode)) {
                // o barcode do orcamento foi separado
                return itemOrder;
            }
        }
        return null;
    }
}