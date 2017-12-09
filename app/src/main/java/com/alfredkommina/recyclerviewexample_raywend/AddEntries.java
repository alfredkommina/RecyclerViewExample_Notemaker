package com.alfredkommina.recyclerviewexample_raywend;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddEntries extends AppCompatActivity {
    EditText nameTxt,posTxt;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Player> players=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entries);


        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        posTxt= (EditText) findViewById(R.id.posEditTxt);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saver:
                if(!(nameTxt.getText().toString().isEmpty() && posTxt.getText().toString().isEmpty())){
                save(nameTxt.getText().toString(),posTxt.getText().toString());
                Intent intent = new Intent(AddEntries.this,MainActivity.class);
                startActivity(intent);
                return true;
                }
                else{
                    Toast.makeText(this,"Nothing to Save",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddEntries.this,MainActivity.class);
                    startActivity(intent);

                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        //SAVE
    private void save(String name, String pos)
    {
        DBAdapter db=new DBAdapter(this);

        //OPEN
        db.openDB();

        //INSERT
        long result=db.add(name,pos);

        if(result>0)
        {
            nameTxt.setText("");
            posTxt.setText("");
        }else
        {
            Snackbar.make(nameTxt,"Unable To Insert",Snackbar.LENGTH_SHORT).show();
        }

        //CLOSE
        db.close();


    }
    }

