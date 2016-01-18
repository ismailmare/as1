package com.ismailmare.imare_fueltrack;

/**
 * Created by ismailmare on 16-01-11.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;



/*
From Mostly from Abram Hindle Student Picker
Used to serialize the data of the logs
also uses shared preferences
 */
public class LogListManager {


    static final String prefFile =  "logList42";
    static final String slKey = "logList42";


    Context context;
    static private LogListManager logListManager = null;

    //Constructor
    public LogListManager(Context context) {
        this.context = context;
    }

    // Initializing the Log Manager used in ShowLogAct
    public static void initManager(Context context){

        if(logListManager == null){
            if(context == null){
                throw new RuntimeException("Error");
            }
            logListManager = new LogListManager(context);
        }


    }
    // returning loglistmanager
    public static LogListManager getManager() {
        if (logListManager==null) {
            throw new RuntimeException("Error");
        }
        return logListManager;
    }

    // Loading log list form shared preferences
    public LogList loadLogList() throws ClassNotFoundException, IOException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        String logListData = settings.getString(slKey, "");
        if (logListData.equals("")) {
            return new LogList();
        } else {
            return logListFromString(logListData);
        }
    }
    // returning log list from Serlialized data
    static public LogList logListFromString(String logListData) throws ClassNotFoundException, IOException {
        ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(logListData, Base64.DEFAULT));
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (LogList)oi.readObject();
    }
    // writing data to be serialized
    static public String logListToString(LogList sl) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(sl);
        oo.close();
        byte bytes[] = bo.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }
    // saving log list in shared preferences
    public void saveLogList(LogList sl) throws IOException {
        SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        Editor editor = settings.edit();
        editor.putString(slKey, logListToString(sl));
        editor.commit();
    }




}
