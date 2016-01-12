package com.ismailmare.imare_fueltrack;

/**
 * Created by ismailmare on 16-01-11.
 */

import java.io.IOException;
import java.util.Date;


public class LogListController {


    private static LogList logList = null;


    static public LogList getLogList() {

        if (logList == null) {

            try {
                logList = LogListManager.getManager().loadLogList();
                logList.addListener(new LogList.Listener() {
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

    public Log chooseLog() throws LogList.EmptyLogException{

        return getLogList().chooseLog();
    }

    public void addLog(Log log) {

        getLogList().addLog(log);
    }

    public static void AddLog(Date Date, String Station, Double Odometer, String Fuel_Grade, Double Fuel_Amount, Double Fuel_Unit_Cost, Double Fuel_Cost ){
        LogList list = getLogList();
        Log log = new Log(Date, Station, Odometer, Fuel_Grade, Fuel_Amount, Fuel_Unit_Cost, Fuel_Cost);
        list.addLog(log);
    }
}
