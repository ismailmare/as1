package com.ismailmare.imare_fueltrack;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.content.DialogInterface;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class ShowLogAct extends ListActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        LogListManager.initManager(this.getApplicationContext());



        //ListView listView = (ListView) findViewById(R.id.list);
        ListView listView = getListView();

        Collection<Log> logs = LogListController.getLogList().getLogs();
        final ArrayList<Log> list = new ArrayList<Log>(logs);
        final ArrayAdapter<Log> logAdapter = new ArrayAdapter<Log>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(logAdapter);

        LogListController.getLogList().addListener(new Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<Log> logs1 = LogListController.getLogList().getLogs();
                list.addAll(logs1);
                logAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder alt = new AlertDialog.Builder(ShowLogAct.this);
                alt.setMessage("Edit or Delete this Log? " + list.get(position).toString());
                alt.setCancelable(true);
                final int pos = position;
                alt.setPositiveButton("Cancel", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //new intent new view
                    }
                });

                alt.setNeutralButton("Edit", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log log_info = list.get(pos);
                        Moreinfo(log_info);
                    }
                });

                alt.setNegativeButton("Delete", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Log deletinglog;
                        Log log = list.get(position);
                        String station = log.Station;
                        //list.remove(pos);
                        //logAdapter.notifyDataSetChanged();
                        LogListController.getLogList().removeLogg(pos);


                        //LogListController.getLogList().removeLog(deletinglog);

                    }
                });
                alt.show();
                return false;
            }
        });

    }

    public void done(){
        Intent intent = new Intent(this, ShowLogAct.class);
        startActivity(intent);
    }

    public void Moreinfo(Log log){
        Intent intent = new Intent(this, MoreInfo.class);
        intent.putExtra("Log", log);
        startActivity(intent);
    }
}

