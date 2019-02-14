package com.example.wishlist;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class additemlist extends Activity implements listview_adapter.Onclickinterface{

    RecyclerView listview;
    Object[] objects;
    public String[] i;
    public String[] p;
    public  String[] ids;
    public String[] RedColor;
    Mydb mydb;
    listview_adapter listview_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additemlist);

        listview=findViewById(R.id.listview);
        listview.setLayoutManager(new LinearLayoutManager(this));

        String tn = getIntent().getExtras().getString("tn");

        Mydb.setTableName(tn);

        mydb=new Mydb(getApplicationContext());

         //
            main();
        //

        mydb=new Mydb(getApplicationContext());

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                Intent i=new Intent(additemlist.this,save_items.class);
                startActivity(i);
            }
        });

    }

    public void main (){
        try {
            objects = mydb.search();
            i = ((String[]) objects[0]);
            p = (String[]) objects[1];

            ids=(String[])objects[2];
            RedColor=(String[])objects[3];


            listview_adapter = new listview_adapter(i,p,RedColor);
            listview.setAdapter(listview_adapter);
            listview_adapter.setOnclicklistener(additemlist.this);

            //swipe delete


            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                    Log.d("viewholderid", String.valueOf(viewHolder.getAdapterPosition()));
                    Log.d("ids",ids[viewHolder.getAdapterPosition()]);

                    boolean isdelete = mydb.delete(ids[viewHolder.getAdapterPosition()]);
                    if(isdelete==true) {
                        Toast.makeText(additemlist.this, "Delete", Toast.LENGTH_SHORT).show();
                        main();
                    }
                    else {
                        Toast.makeText(additemlist.this, "Not Delete", Toast.LENGTH_SHORT).show();
                    }
                }
            }).attachToRecyclerView(listview);

            //swip delete end

            //add

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                    Toast.makeText(additemlist.this, "RIGHT", Toast.LENGTH_SHORT).show();
                    Log.d("UPDATE", String.valueOf(viewHolder.getAdapterPosition()));
                    mydb.UpdateColor(ids[viewHolder.getAdapterPosition()]);
                    main();
                }
            }).attachToRecyclerView(listview);

            //add

        }
        catch (Exception e){
            Log.d("HELLO",String.valueOf(e));
        }
    }

    @Override
    public void Onclickiteminterface(int position) {
        Log.d("HELLO", String.valueOf(position));
        Intent intent=new Intent(additemlist.this,save_items.class);
        intent.putExtra("item",i[position]);
        intent.putExtra("price",p[position]);
        intent.putExtra("id",String.valueOf(position+1));
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
        Log.d("RESTART","RESTART");
    }
}
