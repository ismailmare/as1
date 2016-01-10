package com.ismailmare.imare_fueltrack;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ismailmare on 16-01-10.
 */
public class LogList implements Serializable{


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
        for (Listener  listener : getListeners()) {
            listener.update();
        }
    }

    public void removeLog(Log log){
        logList.remove(log);
        notifyListeners();
    }

    public Log chooseLog(){
        int size = logList.size();
        int index = (int) (logList.size() * Math.random());
        return logList.get(index);
    }

    public interface Listener {
        public void update();
    }
}
