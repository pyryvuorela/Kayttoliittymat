package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;


public class adminHouseControl extends AppCompatActivity {
    HouseDatabase houseDatabase;
    Button modifyHouse;
    Button deleteHouse;
    Switch room1;
    CheckBox room1Light;
    CheckBox room1Lock;
    CheckBox room1Temp;
    Switch room2;
    CheckBox room2Light;
    CheckBox room2Lock;
    CheckBox room2Temp;
    Switch room3;
    CheckBox room3Light;
    CheckBox room3Lock;
    CheckBox room3Temp;
    String currentHouse;

    EditText room1Name;
    EditText room2Name;
    EditText room3Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_house_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        houseDatabase = new HouseDatabase(this);
        modifyHouse = (Button) findViewById(R.id.modifyHousefinalButtonID);
        deleteHouse = (Button) findViewById(R.id.deleteHouseButtonID);
        room1 = (Switch) findViewById(R.id.room1ModiSwitch);
        room2 = (Switch) findViewById(R.id.room2ModiSwitch);
        room3 = (Switch) findViewById(R.id.room3ModiSwitch);
        room1Light = (CheckBox) findViewById(R.id.Room1LigthModi);
        room2Light = (CheckBox) findViewById(R.id.Room2LigthModi);
        room3Light = (CheckBox) findViewById(R.id.Room3LigthModi);
        room1Lock = (CheckBox) findViewById(R.id.Room1LockModi);
        room2Lock = (CheckBox) findViewById(R.id.Room2LockModi);
        room3Lock = (CheckBox) findViewById(R.id.Room3LockModi);
        room1Temp = (CheckBox) findViewById(R.id.Room1TempModi);
        room2Temp = (CheckBox) findViewById(R.id.Room2TempModi);
        room3Temp = (CheckBox) findViewById(R.id.Room3TempModi);
        room1Name = (EditText) findViewById(R.id.adminHouseControlRoom1NAme);
        room2Name = (EditText) findViewById(R.id.adminHouseControlRoom2NAme);
        room3Name = (EditText) findViewById(R.id.adminHouseControlRoom3NAme);

        Cursor res = houseDatabase.getAllData();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentHouse = extras.getString("housename");
        }
        while(res.moveToNext()){
            if(res.getString(1).equals(currentHouse)){
                if(res.getString(2).equals("1"))room1.setChecked(true);
                if(res.getString(3).equals("1"))room1Light.setChecked(true);
                if(res.getString(4).equals("1"))room1Lock.setChecked(true);
                if(res.getString(5).equals("1"))room1Temp.setChecked(true);
                if(res.getString(6).equals("1"))room2.setChecked(true);
                if(res.getString(7).equals("1"))room2Light.setChecked(true);
                if(res.getString(8).equals("1"))room2Lock.setChecked(true);
                if(res.getString(9).equals("1"))room2Temp.setChecked(true);
                if(res.getString(10).equals("1"))room3.setChecked(true);
                if(res.getString(11).equals("1"))room3Light.setChecked(true);
                if(res.getString(12).equals("1"))room3Lock.setChecked(true);
                if(res.getString(13).equals("1"))room3Temp.setChecked(true);
                if(res.getString(14).equals("")){}else room1Name.setText(res.getString(14));
                if(res.getString(15).equals("")){}else room2Name.setText(res.getString(15));
                if(res.getString(16).equals("")){}else room3Name.setText(res.getString(16));
            }
        }

        modifyHouse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean isInserted = houseDatabase.updateData(currentHouse, room1.isChecked(), room1Light.isChecked(), room1Lock.isChecked(), room1Temp.isChecked(), room2.isChecked(), room2Light.isChecked(), room2Lock.isChecked(), room2Temp.isChecked(), room3.isChecked(), room3Light.isChecked(), room3Lock.isChecked(), room3Temp.isChecked(), room1Name.getText().toString(),room2Name.getText().toString(),room3Name.getText().toString());
                if(isInserted == true) {
                    Toast.makeText(adminHouseControl.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), adminRoomControl.class));
                }
                else
                    Toast.makeText(adminHouseControl.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });
        deleteHouse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer isDeleted = houseDatabase.deleteData(currentHouse);
                if(isDeleted > 0) {
                    Toast.makeText(adminHouseControl.this, "Data Deleted", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), adminRoomControl.class));
                }
                else
                    Toast.makeText(adminHouseControl.this, "Data not Deleted", Toast.LENGTH_LONG).show();
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), adminRoomControl.class));
            return  true;
        }
        return super.onOptionsItemSelected(item);

    }
}