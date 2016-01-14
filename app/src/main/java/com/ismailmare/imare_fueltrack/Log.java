package com.ismailmare.imare_fueltrack;

import android.content.Intent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by ismailmare on 16-01-09.
 */

public class Log implements Serializable{

    public Date Date;
    public String Station;
    public Double Odometer;
    public String Fuel_Grade;
    public Double Fuel_Amount;
    public Double Fuel_Unit_Cost;
    public Double Fuel_Cost;
    public int Log_Id;

    public Log(Date Date, String Station, Double Odometer, String Fuel_Grade, Double Fuel_Amount, Double Fuel_Unit_Cost, Double Fuel_Cost){
        this.Date = Date;
        this.Station = Station;
        this.Odometer = Odometer;
        this.Fuel_Grade = Fuel_Grade;
        this.Fuel_Amount = Fuel_Amount;
        this.Fuel_Unit_Cost = Fuel_Unit_Cost;
        this.Fuel_Cost = Fuel_Cost;
        int random = (int )(Math.random() * 500000000 + 1);
        this.Log_Id = random;
    }

    public int Get_ID(){
        return this.Log_Id;
    }

    public Date GetDate(Log log){
        return log.Date;
    }

    public String toString(){
        Date dateNow= this.Date;
        String date_to_string;
        SimpleDateFormat dateformatJava = new SimpleDateFormat("yyyy-MM-dd");
        date_to_string = dateformatJava.format(dateNow);

        return this.Station+" "+date_to_string;
    }

    public String GetStation(){
        return this.Station;
    }

    public boolean equals(Object Compare_Log){
        if (Compare_Log != null && Compare_Log.getClass() == this.getClass()){
            return this.equals((Log)Compare_Log);
        } else {
            return false;
        }
    }

    public boolean equals(Log Compare_Log){
        if (Compare_Log != null){
            return false;
        } else {
            return Get_ID()==Compare_Log.Get_ID();
        }
    }

    public int hashCode(){
        return ("Log ID: "+GetStation()).hashCode();
    }


}
