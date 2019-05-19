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
    private Button btnImportBarcode;
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
        btnImportBarcode = findViewById(R.id.btnImportBarcode);
        btnFinishedItem = findViewById(R.id.btnFinishedItem);
        editTextBarcode = findViewById(R.id.editTextBarcode);
        editTextQtdItem = findViewById(R.id.editTextQtdItem);
    }

    @Override
    public void setHandlerInterface() {
        btnActiveCamera.setOnClickListener(this);
        btnFinishedItem.setOnClickListener(this);
        btnImportBarcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnActiveCamera) {
            //abre a camera

            Intent intentRecoverPassword = new Intent(getContext(), SimpleScannerActivity.class);

            startActivity(intentRecoverPassword); // Start intent without transitions

        } else if (id == R.id.btnImportBarcode) {
            // importar algum codigo de barras dentro do app
            clickBtnImportBarcode();
        } else if (id == R.id.btnFinishedItem) {
            // valida o item
            clickBtnValideItem();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CheckOrderValidationActivity.class);
        setResult(-1, intent);
        finish();
    }

    public void clickBtnImportBarcode() {
        //open dialog
        openActivityChooseBarcode();
    }

    public void clickBtnValideItem() {
        if (fieldsFormFilledAndOk()) {
            executeValidItem();
        }
    }

    private void openActivityChooseBarcode() {
        Intent intent = new Intent(this, CheckOrderChooseItemActivity.class);
        //startActivity(intent);
        startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String barcodeChoosed = data.getStringExtra("barcodeChoose");
                editTextBarcode.setText(barcodeChoosed);
            }
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

        if (itemOrder != null) { // encontrou na lista
            if (qtdItem.equals(itemOrder.getQtd())) { // quatidade bateu
                addItemInListCurrent(itemOrder);
            } else {
                Toast.makeText(getContext(), "Quantidade incorreta. ATENÇÃO!!!",
                        Toast.LENGTH_LONG).show();
            }
        } else { // codigo de barras nao encontrado
            Toast.makeText(getContext(), "Código de barras não encontrado no orçamento. ATENÇÃO!!!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void addItemInListCurrent(ItemOrder itemOrder) {
        if (CheckOrderValidationActivity.getListItemOrderCurrent() == null) {
            CheckOrderValidationActivity.listItemOrderCurrent = new ArrayList<>();
        }
        if (!itemAddedInListCurrent(itemOrder.getBarcode())) {
            CheckOrderValidationActivity.listItemOrderCurrent.add(itemOrder);
            //saveListCurrentInCache();
            Toast.makeText(getContext(), "Item separado corretamente!!!",
                    Toast.LENGTH_LONG).show();
            onBackPressed(); //voltar
        } else {
            Toast.makeText(getContext(), "Item já foi validado, vá para o próximo!!!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void saveListCurrentInCache() {
        String listString = LibraryUtil.objectToString(CheckOrderValidationActivity.listItemOrderCurrent);
        LibraryUtil.SetPreference(CheckOrderBaseActivity.numberOrder, listString, CheckOrderValidItemActivity.this); // Set authinfo in shared preferences
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

    /*
     Verificar se o item em questao ja ta na lista CURRENT
     */

    private Boolean itemAddedInListCurrent(String barcode) {
        ArrayList<ItemOrder> listItemCurrent = CheckOrderValidationActivity.getListItemOrderCurrent();
        for (int i = 0; i <= listItemCurrent.size() - 1; i++) {
            ItemOrder itemOrder = listItemCurrent.get(i);
            if (itemOrder.getBarcode().equals(barcode)) {
                // o item ja foi conferido pelo separador
                return true;
            }
        }
        return false;
    }
}