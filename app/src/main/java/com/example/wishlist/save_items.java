package com.example.wishlist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class save_items extends AppCompatActivity {
    EditText saveitems,saveprice;
    Button savebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_items);

        saveitems=findViewById(R.id.saveitems);
        saveprice=findViewById(R.id.saveprice);
        savebutton=findViewById(R.id.savebutton);

        try{
            String item=getIntent().getExtras().getString("item");
            String price=getIntent().getExtras().getString("price");
            final String id=getIntent().getExtras().getString("id");
            saveitems.setText(item);
            saveprice.setText(price);

            savebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item=saveitems.getText().toString();
                    String price=saveprice.getText().toString();

                    if(item.isEmpty()){
                        saveitems.setError("Item can't be empty");
                    }
                    else if(price.isEmpty()){
                        saveprice.setError("Price can't be empty");
                    }
                    else {
                        Mydb mydb = new Mydb(getApplicationContext());
                        mydb.update(saveitems.getText().toString(), Integer.parseInt(saveprice.getText().toString()),id);

                        Toast.makeText(save_items.this, "Update", Toast.LENGTH_SHORT).show();

                        saveitems.setText("");
                        saveprice.setText("");
                    }
                }
            });
        }
        catch (Exception e){
            savebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item=saveitems.getText().toString();
                    String price=saveprice.getText().toString();

                    if(item.isEmpty()){
                        saveitems.setError("Item can't be empty");
                    }
                    else if(price.isEmpty()){
                        saveprice.setError("Price can't be empty");
                    }
                    else {
                        Mydb mydb = new Mydb(getApplicationContext());
                        mydb.insert(saveitems.getText().toString(), Integer.parseInt(saveprice.getText().toString()));

                        Toast.makeText(save_items.this, "Done", Toast.LENGTH_SHORT).show();

                        saveitems.setText("");
                        saveprice.setText("");
                    }
                }
            });
        }

    }
}
