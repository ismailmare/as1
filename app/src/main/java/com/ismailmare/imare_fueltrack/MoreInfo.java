package com.ismailmare.imare_fueltrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.Date;
import android.widget.EditText;
public class MoreInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log log = (Log)getIntent().getSerializableExtra("MyClassObject");
        PrintLog(log);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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



        EditText editText = (EditText)findViewById(R.id.Station);
        editText.setText(OldStation);

        EditText editText1 = (EditText) findViewById(R.id.Odometer);
        editText1.setText(OldOdometer.toString());

        EditText editText2 = (EditText)findViewById(R.id.FuelGrade);
        editText2.setText(OldFuelGrade);

        EditText editText3 = (EditText) findViewById(R.id.FuelAmount);
        editText3.setText(OldFuelAmount.toString());

        EditText editText4 = (EditText) findViewById(R.id.FuelUnitCost);
        editText4.setText(OldFuelUnitCost.toString());

        EditText editText5 = (EditText) findViewById(R.id.FuelCost);
        editText5.setText(OldFuelCost.toString());

        EditText editText6 = (EditText) findViewById(R.id.Date);
        editText6.setText(OldDate.toString());






    }

    //public void ReplaceLog(Log){

    //}







}
