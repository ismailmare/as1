package com.ismailmare.imare_fueltrack;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

/*
This class is called when a user wants to edit a log
This class will display the values the user entered when he/she created
the log.

If the user edits the values and hits submit
the values will be updated
 */
public class MoreInfo extends AppCompatActivity {

    public static int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        Log log = (Log) intent.getSerializableExtra("Log");
        pos = intent.getIntExtra("Pos",1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PrintLog(log, pos);
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

    public void PrintLog(Log log, Integer pos){

        String OldStation = log.Station;
        Double OldOdometer = log.Odometer;
        String OldFuelGrade = log.Fuel_Grade;
        Double OldFuelAmount = log.Fuel_Amount;
        Double OldFuelUnitCost = log.Fuel_Unit_Cost;
        Double OldFuelCost = log.Fuel_Cost;
        Date OldDate = log.Date;


        SimpleDateFormat dateformatJava = new SimpleDateFormat("yyyy-MM-dd");
        String date_to_string = dateformatJava.format(OldDate);

        EditText Odometer = (EditText) findViewById(R.id.Odometer);
        Odometer.setText(String.valueOf(OldOdometer));

        EditText editText = (EditText)findViewById(R.id.Station);
        editText.setText(OldStation);

        EditText editText2 = (EditText)findViewById(R.id.FuelGrade);
        editText2.setText(OldFuelGrade, TextView.BufferType.EDITABLE);

        EditText editText3 = (EditText) findViewById(R.id.FuelAmount);
        editText3.setText(OldFuelAmount.toString(), TextView.BufferType.EDITABLE);

        EditText editText4 = (EditText) findViewById(R.id.FuelUnitCost);
        editText4.setText(OldFuelUnitCost.toString(), TextView.BufferType.EDITABLE);

        EditText editText5 = (EditText) findViewById(R.id.FuelCost);
        editText5.setText(OldFuelCost.toString(), TextView.BufferType.EDITABLE);

        EditText editText6 = (EditText) findViewById(R.id.Date);
        editText6.setText(date_to_string, TextView.BufferType.EDITABLE);


    }



    public void Replace(View view){

        if(!Validate()){
            Error();
            return;
        }


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
        Fuel_Unit_Cost=Fuel_Unit_Cost/100;
        FuelCost = Fuel_Amount*Fuel_Unit_Cost;
        Fuel_Amount = Math.round(Fuel_Amount*100.000)/100.000;
        Fuel_Unit_Cost = Math.round(Fuel_Unit_Cost*100.0)/100.0;
        FuelCost = Math.round(FuelCost*100.00)/100.00;

        // So Far //
        // Fuel_Amount, Fuel_Unit_Cost, FuelCost, Odometer, Fuel_Grade, Station, Date //

        DateFormat format= new SimpleDateFormat("yyyy-mm-dd");
        //DATE CHECK
        Date UserDate = new Date();
        try {
            UserDate = format.parse(date_str);
        } catch (ParseException e) {

            date.setError("DATE yyyy-mm-dd FORMAT");
        }


        Toast.makeText(getBaseContext(), "Fuel Cost = " + FuelCost, Toast.LENGTH_LONG).show();


        final ProgressDialog progressDialog = new ProgressDialog(MoreInfo.this, R.style.MyTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Editing Log");
        progressDialog.show();

        //pd = new ProgressDialog(NewLogAct.this,R.style.MyTheme);
        //pd.setCancelable(false);
        //pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        //pd.show();

        LogListController.getLogList().removeLogg(pos);
        LogListController list5 = new LogListController();
        list5.AddLog(UserDate, Station, Odometer, Fuel_Grade, Fuel_Amount, Fuel_Unit_Cost, FuelCost);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                        done();
                    }
                }, 2000);

    }
    public void done(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Error(){
        Button button = (Button) (findViewById(R.id.button));
        Toast.makeText(getBaseContext(),"Log Failed", Toast.LENGTH_LONG).show();

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
