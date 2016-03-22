package com.example.pyry.kayttoliittymat;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class adminAddHouse extends AppCompatActivity {
    HouseDatabase houseData;

    Button addHouse;
    EditText houseName;
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

    Button viewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_house);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        houseData = new HouseDatabase(this);
        addHouse = (Button) findViewById(R.id.addNewHouseButtonID);
        houseName = (EditText) findViewById(R.id.addHousenameID);
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
        room2Temp = (CheckBox) findViewById(R.id.Room2TempAdd);
        room3Temp = (CheckBox) findViewById(R.id.Room3TempAdd);

        viewAll = (Button) findViewById(R.id.addNewHouseViewAllButtonID);

        addHouse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                boolean isInserted = houseData.insertData(houseName.getText().toString(),room1.isChecked(),room1Light.isChecked(),room1Lock.isChecked(),room1Temp.isChecked(),room2.isChecked(),room2Light.isChecked(),room2Lock.isChecked(),room2Temp.isChecked(),room3.isChecked(),room3Light.isChecked(),room3Lock.isChecked(),room3Temp.isChecked());
                if(isInserted == true)
                    Toast.makeText(adminAddHouse.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(adminAddHouse.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });



        viewAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Cursor res = houseData.getAllData();
                if(res.getCount() == 0){
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();

                while(res.moveToNext()){
                    buffer.append("House name :" + res.getString(0)+ "\n");
                    buffer.append("Room1 :" + res.getString(1)+ "\n");
                    buffer.append("Room1 light :" + res.getString(2)+ "\n");
                    buffer.append("Room1 lock :" + res.getString(3)+ "\n");
                    buffer.append("Room1 temp :" + res.getString(4)+ "\n");
                    buffer.append("Room2 :" + res.getString(5)+ "\n");
                    buffer.append("Room2 light :" + res.getString(6)+ "\n");
                    buffer.append("Room2 lock :" + res.getString(7)+ "\n");
                    buffer.append("Room2 temp :" + res.getString(8)+ "\n");
                    buffer.append("Room3 :" + res.getString(9)+ "\n");
                    buffer.append("Room3 light :" + res.getString(10)+ "\n");
                    buffer.append("Room3 lock :" + res.getString(11)+ "\n");
                    buffer.append("Room3 temp :" + res.getString(12)+ "\n\n");

                }
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
