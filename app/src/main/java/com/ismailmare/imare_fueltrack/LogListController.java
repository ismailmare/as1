package com.ismailmare.imare_fueltrack;

/**
 * Created by ismailmare on 16-01-11.
 */

import java.io.IOException;
import java.util.Date;

/*
Mostly from Abram Hindle Student Picker
used to control what values are deleting from the
log list and what values are added
using these functions and the override function
that rewrites the list after an addition or deletion
has been made
 */

public class LogListController {


    private static LogList logList = null;

    // Used to retrive the Loglist at the start of ShowLogList Activity
    static public LogList getLogList() {

        if (logList == null) {

            try {
                logList = LogListManager.getManager().loadLogList();
                logList.addListener(new Listener() {
                    @Override
                    public void update() {
                        saveLogList();
                    }
                });

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Error");

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error");
            }
        }

        return logList;
    }

    static public void saveLogList() {

        try {
            LogListManager.getManager().saveLogList(getLogList());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error");
        }
    }

    // Adding a log to LogList
    public void addLogg(Log log) {

        getLogList().addLog(log);
    }

    // Adding a log from NewLogAct
    public void AddLog(Date Date, String Station, Double Odometer, String Fuel_Grade, Double Fuel_Amount, Double Fuel_Unit_Cost, Double Fuel_Cost ){


        Log log = new Log(Date, Station, Odometer, Fuel_Grade, Fuel_Amount, Fuel_Unit_Cost, Fuel_Cost);
        addLogg(log);
    }








}
