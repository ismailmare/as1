package com.ismailmare.imare_fueltrack;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ismailmare on 16-01-10.
 */

/*
Initializing the log list.
Keeps track of what logs have been added and deleted
using listeners
 */
public class LogList implements Serializable{

    private static final long serialVersionUID = 6673446047991058932L;
    protected ArrayList<Log> logList= null;
    protected transient ArrayList<Listener> listeners = null;

    // Contructor for the Loglist
    public LogList(){
        logList = new ArrayList<Log>();
        listeners = new ArrayList<Listener>();
    }
    //returning the listeners for the array of logs
    private ArrayList<Listener> getListeners() {
        if (listeners == null ) {
            listeners = new ArrayList<Listener>();
        }
        return listeners;
    }
    // Holds all the logs
    public Collection<Log> getLogs(){
        return logList;
    }
    // Used for adding a log, will run the function below and update listeners
    public void addLog(Log log){
        logList.add(log);
        notifyListeners();
    }

    private void notifyListeners() {
        for (Listener  listener : listeners) {
            listener.update();
        }
    }
    // removing logs
    public void removeLogg(int log){
        logList.remove(log);
        notifyListeners();
    }
    // replacing logs. Used for the editing in MoreInfo
    public void replace(Log oldLog, Log newLog) {
        logList.remove(oldLog);
        logList.add(newLog);
        notifyListeners();
    }


    // Returns the size of the loglist
    public static int size(){
        return LogList.size();
    }

    // Adding a listener, used when adding a log
    public void addListener(Listener l) {
        getListeners().add(l);
    }
    // removing a listenr, used when removing a log
    public void removeListener(Listener l) {
        getListeners().remove(l);
    }
    // Exception
    public class EmptyLogException extends Exception {

        /**
         *
         */
        private static final long serialVersionUID = -2417935479828663701L;

    }
}
