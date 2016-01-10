package com.ismailmare.imare_fueltrack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewLogAct extends AppCompatActivity {
    private static final String TAG = "Ismail's Message";

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
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
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
        EditText odometer = (EditText) (findViewById(R.id.Odometer));
        EditText date = (EditText) (findViewById(R.id.Date));
        EditText station = (EditText) (findViewById(R.id.Station));
        EditText fuel_grade = (EditText) (findViewById(R.id.FuelGrade));

        String Station = station.getText().toString();
        String Fuel_Grade = fuel_grade.getText().toString();
        String odometer_str = odometer.getText().toString();
        String fuel_amount_str = fuel_amount.getText().toString();
        String fuel_unit_cost_str = fuel_unit_cost.getText().toString();
        String date_str = date.getText().toString();

        double Odometer = Double.parseDouble(odometer_str);
        double Fuel_Amount = Double.parseDouble(fuel_amount_str);
        double Fuel_Unit_Cost = Double.parseDouble(fuel_unit_cost_str);
        FuelCost = Fuel_Amount*Fuel_Unit_Cost;
        Fuel_Amount = Math.round(Fuel_Amount*100.000)/100.000;
        Fuel_Unit_Cost = Math.round(Fuel_Unit_Cost*100.0)/100.0;
        FuelCost = Math.round(FuelCost*100.00)/100.00;

        // So Far //
        // Fuel_Amount, Fuel_Unit_Cost, FuelCost, Odometer, Fuel_Grade, Station, Date //

        DateFormat format= new SimpleDateFormat("yyyy-mm-dd");
        //DATE CHECK
        try {
            Date Date = format.parse(date_str);
        } catch (ParseException e) {

            date.setError("DATE yyyy-mm-dd FORMAT");
        }


        Toast.makeText(getBaseContext(),"Fuel Cost = "+FuelCost, Toast.LENGTH_LONG).show();


        button.setEnabled(false);
        Log.d(TAG, "Login");
        final ProgressDialog progressDialog = new ProgressDialog(NewLogAct.this, R.style.MyTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding Log");
        progressDialog.show();

        //pd = new ProgressDialog(NewLogAct.this,R.style.MyTheme);
        //pd.setCancelable(false);
        //pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        //pd.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                        done();
                    }
                }, 3000);




    }
    public void done(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Error(){
        Button button = (Button) (findViewById(R.id.button));
        Toast.makeText(getBaseContext(),"Log Failed", Toast.LENGTH_LONG).show();
        button.setEnabled(true);

    }

    public boolean Validate(){

        EditText fuel_amount = (EditText) (findViewById(R.id.FuelAmount));
        EditText fuel_unit_cost = (EditText) (findViewById(R.id.FuelUnitCost));
        EditText odometer = (EditText) (findViewById(R.id.Odometer));
        EditText date = (EditText) (findViewById(R.id.Date));
        EditText station = (EditText) (findViewById(R.id.Station));
        EditText fuel_grade = (EditText) (findViewById(R.id.FuelGrade));

        String Station = station.getText().toString();
        String Fuel_Grade = fuel_grade.getText().toString();
        String Odometer = odometer.getText().toString();
        String Fuel_Amount = fuel_amount.getText().toString();
        String Fuel_Unit_Cost = fuel_unit_cost.getText().toString();
        String date_str = date.getText().toString();

        boolean valid = true;


        DateFormat format= new SimpleDateFormat("yyyy-mm-dd");
        //DATE CHECK
        try {
            Date Date = format.parse(date_str);
        } catch (ParseException e) {
            valid = false;
            date.setError("DATE yyyy-mm-dd FORMAT");
            return valid;
        }

        if (Fuel_Grade.isEmpty()){
            valid=false;
            fuel_grade.setError("Enter a Fuel Grade");
        }

        if (Station.isEmpty()){
            valid = false;
            station.setError("Enter a Station");
        }

        if (Odometer.isEmpty()) {
            valid = false;
            odometer.setError("Enter a Odometer Reading");
        }

        if (Fuel_Amount.isEmpty()){
            valid = false;
            fuel_amount.setError("Enter Fuel Amount");
        }

        if (Fuel_Unit_Cost.isEmpty()){
            valid = false;
            fuel_unit_cost.setError("Enter a Fuel Unit Cost");
        }
        return valid;
    }

}
