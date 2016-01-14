package com.ismailmare.imare_fueltrack;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ismailmare on 16-01-10.
 */
public class LogList implements Serializable{

    private static final long serialVersionUID = 6673446047991058932L;
    protected ArrayList<Log> logList= null;
    protected transient ArrayList<Listener> listeners = null;

    public LogList(){
        logList = new ArrayList<Log>();
        listeners = new ArrayList<Listener>();
    }

    private ArrayList<Listener> getListeners() {
        if (listeners == null ) {
            listeners = new ArrayList<Listener>();
        }
        return listeners;
    }

    public Collection<Log> getLogs(){
        return logList;
    }

    public void addLog(Log log){
        logList.add(log);
        notifyListeners();
    }

    private void notifyListeners() {
        for (Listener  listener : listeners) {
            listener.update();
        }
    }

    public void removeLogg(int log){
        logList.remove(log);
        notifyListeners();
    }

    public void replace(Log oldLog, Log newLog) {
        logList.remove(oldLog);
        logList.add(newLog);
        notifyListeners();
    }



    public static int size(){
        return LogList.size();
    }

    //public boolean contains(Log testLog){
    //    return LogList.contains(testLog);
    //}

    public void addListener(Listener l) {
        listeners.add(l);
    }

    public void removeListener(Listener l) {
        listeners.remove(l);
    }

    public class EmptyLogException extends Exception {

        /**
         *
         */
        private static final long serialVersionUID = -2417935479828663701L;

    }
}
