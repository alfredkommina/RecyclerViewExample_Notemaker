package com.alfredkommina.recyclerviewexample_raywend;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText nameTxt,posTxt;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Player> players=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(MainActivity.this,AddEntries.class);
                startActivity(intentAdd);
            }
        });

        //recycler
        rv= (RecyclerView) findViewById(R.id.myRecycler);

        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        adapter=new MyAdapter(this,players);

        retrieve();

    }


    //RETRIEVE
    private void retrieve()
    {
        DBAdapter db=new DBAdapter(this);

        //OPEN
        db.openDB();

        players.clear();

        //SELECT
        Cursor c=db.getAllPlayers();

        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String pos=c.getString(2);

            //CREATE PLAYER
            Player p=new Player(name,pos,id);

            //ADD TO PLAYERS
            players.add(p);
        }

        //SET ADAPTER TO RV
        if(!(players.size()<1))
        {
            rv.setAdapter(adapter);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieve();
    }
}
