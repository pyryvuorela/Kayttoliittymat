package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class userRoomControl extends AppCompatActivity {
    TextView currentRoom;
    Switch ligths;
    Switch lock;
    SeekBar temp;
    RoomDatabase roomData;
    HouseDatabase houseDatabase;
    TextView tempNumber;
    String currentUser;
    String currentHouse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_room_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentRoom = (TextView) findViewById(R.id.userRoomControlRoomname);
        roomData = new RoomDatabase(this);
        houseDatabase = new HouseDatabase(this);
        Cursor resRoom = roomData.getAllData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ligths = (Switch) findViewById(R.id.userRoomControlLigth);
        lock = (Switch) findViewById(R.id.userRoomControlLock);
        temp = (SeekBar) findViewById(R.id.userRoomControlTemp);
        tempNumber = (TextView) findViewById(R.id.userRoomControltempnumber);
        temp.setMax(40);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentRoom.setText(extras.getString("roomname"));
            currentHouse = extras.getString("housename");
            currentUser = extras.getString("username");
            if(extras.getString("light").equals("1")){} else ligths.setVisibility(View.GONE);
            if(extras.getString("lock").equals("1")){} else lock.setVisibility(View.GONE);
            if(extras.getString("temp").equals("1")){} else temp.setVisibility(View.GONE);
        }

        Boolean found = false;
        while (resRoom.moveToNext()) {
            if (currentRoom.getText().toString().equals(resRoom.getString(1))) {
                found = true;
                if (resRoom.getString(2).equals("1")) ligths.setChecked(true);
                if (resRoom.getString(3).equals("1")) lock.setChecked(true);
                temp.setProgress(resRoom.getInt(4));
                tempNumber.setText(Integer.toString(resRoom.getInt(4)) + (char) 0x00B0);
            }
        }
        if (found == false) {
            roomData.insertData(currentRoom.getText().toString(), false, false, 0, currentHouse);
        }
        ligths.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               roomData.updateData(currentRoom.getText().toString(), ligths.isChecked(), lock.isChecked(), temp.getProgress(), currentHouse);
            }
        });
        lock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                roomData.updateData(currentRoom.getText().toString(), ligths.isChecked(), lock.isChecked(), temp.getProgress(), currentHouse);
            }
    });
        temp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                roomData.updateData(currentRoom.getText().toString(), ligths.isChecked(), lock.isChecked(),progress, currentHouse);
                tempNumber.setText(Integer.toString(progress) +  (char) 0x00B0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent i = new Intent(getApplicationContext(), userRoomSelection.class);
            i.putExtra("username", currentUser);
            i.putExtra("housename", currentHouse);
            startActivity(i);
            return  true;
        }
        return super.onOptionsItemSelected(item);

    }
}
