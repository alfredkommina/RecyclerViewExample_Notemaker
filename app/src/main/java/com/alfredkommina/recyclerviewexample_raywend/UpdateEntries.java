package com.alfredkommina.recyclerviewexample_raywend;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;


public class UpdateEntries extends AppCompatActivity {
    ImageView img;
    EditText nameTxt,posTxt;
    //Button updateBtn,deleteBtn;
     int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entries);
        Intent i=getIntent();

        final String name=i.getExtras().getString("NAME");
        final String pos=i.getExtras().getString("POSITION");
        ids=i.getExtras().getInt("ID");

       // updateBtn= (Button) findViewById(R.id.updateBtn);
        //deleteBtn= (Button) findViewById(R.id.deleteBtn);
        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        posTxt= (EditText) findViewById(R.id.posEditTxt);

        //ASSIGN DATA TO THOSE VIEWS
        nameTxt.setText(name);
        posTxt.setText(pos);

    }

    public void sampler() {
        System.out.print("This is from ");




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options_del, menu);

        Drawable yourdrawable = menu.getItem(0).getIcon();
        Drawable yourdrawable2 = menu.getItem(1).getIcon(); // change 0 with 1,2 ...

        yourdrawable.mutate();
        yourdrawable.setColorFilter(getResources().getColor(R.color.card_background_default), PorterDuff.Mode.SRC_IN);
        yourdrawable2.mutate();
        yourdrawable2.setColorFilter(getResources().getColor(R.color.card_background_default), PorterDuff.Mode.SRC_IN);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.saverupdate:
                update(ids,nameTxt.getText().toString(),posTxt.getText().toString());
                Intent intent1 = new Intent(UpdateEntries.this,MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.deleteme:
                deleter(ids);
                Intent intent2 = new Intent(UpdateEntries.this,MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void update(int id, String newName, String newPos)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.UPDATE(id,newName,newPos);

        if(result>0)
        {
            nameTxt.setText(newName);
            nameTxt.setText(newPos);
            Snackbar.make(nameTxt,"Updated Sucesfully",Snackbar.LENGTH_SHORT).show();
        }else
        {
            Snackbar.make(nameTxt,"Unable to Update",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }

    //DELETE
    private void deleter(int id)

    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.Delete(id);


        if(result>0)
        {
            this.finish();
        }else
        {
            Snackbar.make(nameTxt,"Unable to Update",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }




}
