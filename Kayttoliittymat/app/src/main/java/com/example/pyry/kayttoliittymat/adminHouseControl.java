package com.example.pyry.kayttoliittymat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;

public class adminHouseControl extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_house_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        room1 = (Switch) findViewById(R.id.room1AddSwitch);
        room2 = (Switch) findViewById(R.id.room2AddSwitch);
        room3 = (Switch) findViewById(R.id.room3AddSwitch);
        room1Light = (CheckBox) findViewById(R.id.Room1LigthAdd);
        room2Light = (CheckBox) findViewById(R.id.Room2LigthAdd);
        room3Light = (CheckBox) findViewById(R.id.Room3LigthAdd);
        room1Lock = (CheckBox) findViewById(R.id.Room1LockAdd);
        room2Lock = (CheckBox) findViewById(R.id.Room2LockAdd);
        room3Lock = (CheckBox) findViewById(R.id.Room3LockAdd);
        room1Temp = (CheckBox) findViewById(R.id.Room1TempAdd);
        room2Temp = (CheckBox) findViewById(R.id.Room2Temp);
        room3Temp = (CheckBox) findViewById(R.id.Room3Temp);

    }

}
