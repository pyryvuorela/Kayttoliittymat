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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class userRoomSelection extends AppCompatActivity {
    HouseDatabase houseDatabase;
    ListView listView;
    String currentHouse;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_room_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        houseDatabase = new HouseDatabase(this);
        listView = (ListView) findViewById(R.id.userRoomSelectionListView);
        Cursor resHouse = houseDatabase.getAllData();
        final List<String> allRooms = new ArrayList<String>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentHouse = extras.getString("housename");
            currentUser = extras.getString("username");
        }
        while(resHouse.moveToNext()){
            if(currentHouse.equals(resHouse.getString(1))){
                if(resHouse.getString(14).equals("")){}else allRooms.add(resHouse.getString(14));
                if(resHouse.getString(15).equals("")){}else allRooms.add(resHouse.getString(15));
                if(resHouse.getString(16).equals("")){}else allRooms.add(resHouse.getString(16));
            }
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.customuserroom_listview, allRooms);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String room = String.valueOf(parent.getItemAtPosition(position));
                Intent i = new Intent(getApplicationContext(), userRoomControl.class);
                Cursor house = houseDatabase.getAllData();
                while(house.moveToNext()){
                    if(currentHouse.equals(house.getString(1))){
                        if(house.getString(14).equals(room)){
                            if(house.getString(3).equals("1"))i.putExtra("light", "1");
                            else i.putExtra("light", "0");
                            if(house.getString(4).equals("1"))i.putExtra("lock", "1");
                            else i.putExtra("lock", "0");
                            if(house.getString(5).equals("1"))i.putExtra("temp", "1");
                            else i.putExtra("temp", "0");
                        }
                        else if(house.getString(15).equals(room)){
                            if(house.getString(7).equals("1"))i.putExtra("light", "1");
                            else i.putExtra("light", "0");
                            if(house.getString(8).equals("1"))i.putExtra("lock", "1");
                            else i.putExtra("lock", "0");
                            if(house.getString(9).equals("1"))i.putExtra("temp", "1");
                            else i.putExtra("temp", "0");
                        }
                        else if(house.getString(16).equals(room)){
                            if(house.getString(11).equals("1"))i.putExtra("light", "1");
                            else i.putExtra("light", "0");
                            if(house.getString(12).equals("1"))i.putExtra("lock", "1");
                            else i.putExtra("lock", "0");
                            if(house.getString(13).equals("1"))i.putExtra("temp", "1");
                            else i.putExtra("temp", "0");
                        }
                    }
                }

                i.putExtra("roomname", room);
                i.putExtra("housename", currentHouse);
                i.putExtra("username", currentUser);
                startActivity(i);
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent i = new Intent(getApplicationContext(), userMainMenu.class);
            i.putExtra("username", currentUser);
            startActivity(i);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
