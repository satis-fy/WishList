package com.example.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements listview_adapter.Onclickinterface {

    RecyclerView recyclerViewtn;
    public String[] alltablename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewtn = findViewById(R.id.listviewtn);
        recyclerViewtn.setLayoutManager(new LinearLayoutManager(this));

        Mydb mydb = new Mydb(getApplicationContext());
        String[] a = mydb.tablelist();
        alltablename = new String[a.length - 3];

        for (int i = 0; i < alltablename.length; i++) {
            alltablename[i] = a[i + 3];
        }

        String[] pricenull = new String[alltablename.length];
        String[] RedColor=new String[alltablename.length];

        listview_adapter listview_adapter = new listview_adapter(alltablename, pricenull,RedColor);
        recyclerViewtn.setAdapter(listview_adapter);
        //
        listview_adapter.setOnclicklistener(MainActivity.this);
//
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                Intent i = new Intent(MainActivity.this, TableName.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void Onclickiteminterface(int position) {
        Intent i=new Intent(MainActivity.this,additemlist.class);
        Log.d("POSITION", String.valueOf(position));

        i.putExtra("tn",alltablename[position]);
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
