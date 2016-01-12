package com.ismailmare.imare_fueltrack;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;


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

        ListView listView = (ListView) findViewById(R.id.Showlogs);

        Collection<Log> log = LogListController.getLogList().getLogs();
        final ArrayList<Log> list = new ArrayList<Log>(log);
        final ArrayAdapter<Log> logAdapter = new ArrayAdapter<Log>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(logAdapter);

        LogListController.getLogList().addListener(new LogList.Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<Log> log = LogListController.getLogList().getLogs();
                list.addAll(log);
                logAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alt = new AlertDialog.Builder(ShowLogAct.this);
                //alt.setMessage("Edit " +list.get(position).toString());
                alt.setCancelable(true);
                final int pos = position;
                alt.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //new intent new view
                    }
                });

                alt.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //new intent new view
                    }
                });

                alt.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

            }
        });

    }
}

