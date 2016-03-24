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
        }
        while(resHouse.moveToNext()){
            if(currentHouse.equals(resHouse.getString(0))){
                if(resHouse.getString(2).equals("1"))allRooms.add("Room1");
                if(resHouse.getString(6).equals("1"))allRooms.add("Room2");
                if(resHouse.getString(10).equals("1"))allRooms.add("Room3");
            }
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, allRooms);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String room = String.valueOf(parent.getItemAtPosition(position));
                Intent i = new Intent(getApplicationContext(), userRoomControl.class);
                i.putExtra("roomname", room);
                startActivity(i);
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), userMainMenu.class));
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
