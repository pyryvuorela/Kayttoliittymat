package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminModifyRoom extends AppCompatActivity {
    HouseDatabase houseDatabase;
    Button modifyHouse;
    EditText selectHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_modify_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        houseDatabase = new HouseDatabase(this);
        modifyHouse = (Button) findViewById(R.id.modifyHouseSelectButtonID);
        selectHouse = (EditText) findViewById(R.id.modifyHouseSelectNameID);


        modifyHouse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cursor res = houseDatabase.getAllData();
                boolean found = false;
                while(res.moveToNext()){
                    if(res.getString(0).equals(selectHouse.getText().toString())){
                        startActivity(new Intent(getApplicationContext(), adminHouseControl.class));
                        found = true;
                    }
                }
                if(found == false)
                Toast.makeText(adminModifyRoom.this, "House not found", Toast.LENGTH_LONG).show();
            }
        });
    }
}
