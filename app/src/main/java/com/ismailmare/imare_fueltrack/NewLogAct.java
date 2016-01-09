package com.ismailmare.imare_fueltrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewLogAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log);
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

    public void AddNewLog(View view){
        Button button = (Button) (findViewById(R.id.button));

        if(!Validate()){
            Error();
            return;
        }
        button.setEnabled(false);

        double FuelCost;
        EditText fuel_amount = (EditText) (findViewById(R.id.FuelAmount));
        EditText fuel_unit_cost = (EditText) (findViewById(R.id.FuelUnitCost));

        String fuel_amount_str = fuel_amount.getText().toString();
        String fuel_unit_cost_str = fuel_unit_cost.getText().toString();

        double Fuel_Amount = Double.parseDouble(fuel_amount_str);
        double Fuel_Unit_Cost = Double.parseDouble(fuel_unit_cost_str);
        FuelCost = Fuel_Amount*Fuel_Unit_Cost;

        Fuel_Amount = Math.round(Fuel_Amount*100.000)/100.000;
        Fuel_Unit_Cost = Math.round(Fuel_Unit_Cost*100.0)/100.0;
        FuelCost = Math.round(FuelCost*100.00)/100.00;

        Toast.makeText(getBaseContext(),"Fuel Cost = "+FuelCost, Toast.LENGTH_LONG).show();
        button.setEnabled(true);

    }

    public void Error(){
        Button button = (Button) (findViewById(R.id.button));
        Toast.makeText(getBaseContext(),"Log Failed", Toast.LENGTH_LONG).show();
        button.setEnabled(true);

    }

    public boolean Validate(){
        boolean valid = true;
        EditText date = (EditText) (findViewById(R.id.Date));
        EditText station = (EditText) (findViewById(R.id.Station));
        EditText fuel_grade = (EditText) (findViewById(R.id.FuelGrade));
        EditText fuel_amount = (EditText) (findViewById(R.id.FuelAmount));
        EditText fuel_unit_cost = (EditText) (findViewById(R.id.FuelUnitCost));
        EditText odometer = (EditText) (findViewById(R.id.Odometer));

        String date_str = date.getText().toString();
        String station_str = station.getText().toString();
        String fuel_grade_str = fuel_grade.getText().toString();
        String fuel_amount_str = fuel_amount.getText().toString();
        String fuel_unit_cost_str = fuel_unit_cost.getText().toString();
        String odometer_str = odometer.getText().toString();

        DateFormat format= new SimpleDateFormat("yyyy-mm-dd");
        //DATE CHECK
        try {
            Date Date = format.parse(date_str);
        } catch (ParseException e) {
            valid = false;
            date.setError("DATE yyyy-mm-dd FORMAT");
            return valid;
        }

        try {
            double Odometer = Double.parseDouble(odometer_str);
        }catch(NumberFormatException e){
            odometer.setError("Not a valid number");
            valid = false;
        }

        try {
            double Fuel_Amount = Double.parseDouble(fuel_amount_str);
        }catch(NumberFormatException e){
            fuel_amount.setError("Not a valid number");
            valid=false;
        }

        try{
            double Fuel_Unit_Cost = Double.parseDouble(fuel_unit_cost_str);
        }catch(NumberFormatException e){
            valid = false;
            fuel_unit_cost.setError("Not a valid number");
        }

        if (fuel_grade_str.isEmpty()){
            valid=false;
            fuel_grade.setError("Enter a Fuel Grade");
        }

        if (station_str.isEmpty()){
            valid = false;
            station.setError("Enter a Station");
        }
        return valid;
    }
}
