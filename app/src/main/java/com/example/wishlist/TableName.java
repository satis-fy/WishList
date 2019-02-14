package com.example.wishlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TableName extends Activity {

    EditText ettablename;
    Button bnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_name);

        ettablename=findViewById(R.id.ettablename);
        bnext=findViewById(R.id.bnext);

        bnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tname=ettablename.getText().toString();
                if(tname.isEmpty()){
                    ettablename.setError("Tablename Can't be empty");
                }
                else {
                    Intent i = new Intent(TableName.this, additemlist.class);
                    i.putExtra("tn", ettablename.getText().toString());
                    startActivity(i);
                }
            }
        });

    }

}
