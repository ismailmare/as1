package com.ismailmare.imare_fueltrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import java.io.Serializable;
import java.util.Date;
import android.widget.EditText;
import android.content.Intent;
public class MoreInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        Log log = (Log) intent.getSerializableExtra("Log");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PrintLog(log);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void PrintLog(Log log){
        String OldStation = log.Station;
        Double OldOdometer = log.Odometer;
        String OldFuelGrade = log.Fuel_Grade;
        Double OldFuelAmount = log.Fuel_Amount;
        Double OldFuelUnitCost = log.Fuel_Unit_Cost;
        Double OldFuelCost = log.Fuel_Cost;
        Date OldDate = log.Date;

        System.out.println(OldStation);

        EditText Odometer = (EditText) findViewById(R.id.Odometer);
        Odometer.setText(String.valueOf(OldOdometer));

        EditText editText = (EditText)findViewById(R.id.Station);
        editText.setText(OldStation);

        EditText editText2 = (EditText)findViewById(R.id.FuelGrade);
        editText2.setText(OldFuelGrade,TextView.BufferType.EDITABLE);

        EditText editText3 = (EditText) findViewById(R.id.FuelAmount);
        editText3.setText(OldFuelAmount.toString(),TextView.BufferType.EDITABLE);

        EditText editText4 = (EditText) findViewById(R.id.FuelUnitCost);
        editText4.setText(OldFuelUnitCost.toString(),TextView.BufferType.EDITABLE);

        EditText editText5 = (EditText) findViewById(R.id.FuelCost);
        editText5.setText(OldFuelCost.toString(),TextView.BufferType.EDITABLE);

        EditText editText6 = (EditText) findViewById(R.id.Date);
        editText6.setText(OldDate.toString(),TextView.BufferType.EDITABLE);






    }

    public void Replace(Log){

    }







}
