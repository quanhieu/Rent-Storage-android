package com.example.democrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UpdateStorage extends AppCompatActivity implements View.OnClickListener {
    private EditText edt_dimensions_in_square_meters_update, edt_mouhthly_rent_price_update, edt_name_of_reporter_update, edt_note_update;
    private Spinner spinner_storage_type_update, spinner_storage_feature_update;
    private Button btnUpdate;
    StorageDAO storageDAO;
    LoadFragment loadFragment;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edt_dimensions_in_square_meters_update = (EditText) findViewById(R.id.edt_dimensions_in_square_meters_update);
        edt_mouhthly_rent_price_update = (EditText) findViewById(R.id.edt_mouhthly_rent_price_update);
        edt_name_of_reporter_update = (EditText) findViewById(R.id.edt_name_of_reporter_update);
        edt_note_update = (EditText) findViewById(R.id.edt_note_update);
        spinner_storage_type_update = (Spinner) findViewById(R.id.spinner_storage_type_update);
        spinner_storage_feature_update = (Spinner) findViewById(R.id.spinner_storage_feature_update);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        id = getIntent().getIntExtra("id", -1);
        storageDAO = new StorageDAO(this);
        loadFragment = new LoadFragment();
        AddDataAndSetAdapterForSpinnerUpdate();
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                String sSpinner_storage_type_update = spinner_storage_type_update.getSelectedItem().toString();
                String sEdt_dimensions_in_square_meters_update = edt_dimensions_in_square_meters_update.getText().toString();
                String sSpinner_storage_feature_update = spinner_storage_feature_update.getSelectedItem().toString();
                String sEdt_mouhthly_rent_price_update = edt_mouhthly_rent_price_update.getText().toString();
                String sEdt_name_of_reporter_update = edt_name_of_reporter_update.getText().toString();
                String sEdt_note_update = edt_note_update.getText().toString();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sDatetime = simpleDateFormat.format(calendar.getTime());
                if (sSpinner_storage_type_update != null && sEdt_dimensions_in_square_meters_update != null && sSpinner_storage_feature_update != null && sEdt_mouhthly_rent_price_update != null && sEdt_name_of_reporter_update != null
                        && !sSpinner_storage_type_update.equals("") && !sEdt_dimensions_in_square_meters_update.equals("") && !sSpinner_storage_feature_update.equals("") && !sEdt_mouhthly_rent_price_update.equals("") && !sEdt_name_of_reporter_update.equals("") ) {
                    Storage storage = new Storage();
                    storage.setStorage_type(sSpinner_storage_type_update);
                    storage.setDimens_in_square_meters(sEdt_dimensions_in_square_meters_update);
                    storage.setStorage_feature(sSpinner_storage_feature_update);
                    storage.setMounthly_rent_price(sEdt_mouhthly_rent_price_update);
                    storage.setName_of_reporter(sEdt_name_of_reporter_update);
                    storage.setNote(sEdt_note_update);
                    storage.setDate_time(sDatetime);
                    boolean check = storageDAO.UpdateStorage(id, storage);
                    Intent intent = new Intent();
                    intent.putExtra("checkupdate", check);
                    setResult(Activity.RESULT_OK, intent);
                    Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Input is required", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }

    public void AddDataAndSetAdapterForSpinnerUpdate() {
        List<String> list_storage_type = new ArrayList<>();
        list_storage_type.add("Home");
        list_storage_type.add("Business");
        list_storage_type.add("Apartment");
        List<String> list_storage_feature = new ArrayList<>();
        list_storage_feature.add("PRIVATE SPACE");
        list_storage_feature.add("SHARE SPACE");
        list_storage_feature.add("CCTV");
        list_storage_feature.add("AIR CONDITIONING");
        list_storage_feature.add("STORAGE FOR SHORT TERM");
        list_storage_feature.add("STORAGE FOR LONG TERM");
        list_storage_feature.add("COLLECT AND DELIVERY SERVICE");
        ArrayAdapter<String> adapter_storage_type = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_storage_type);
        adapter_storage_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_storage_type_update.setAdapter(adapter_storage_type);
        ArrayAdapter<String> adapter_storage_feature = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list_storage_feature);
        adapter_storage_feature.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_storage_feature_update.setAdapter(adapter_storage_feature);
    }

}
