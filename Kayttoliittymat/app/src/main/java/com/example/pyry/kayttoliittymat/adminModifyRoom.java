package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class adminModifyRoom extends AppCompatActivity {
    HouseDatabase houseDatabase;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_modify_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        houseDatabase = new HouseDatabase(this);
        listView = (ListView) findViewById(R.id.listView);
        Cursor res = houseDatabase.getAllData();
        List<String> allHouses = new ArrayList<String>();

        int count = 0;
        while (res.moveToNext()) {
           allHouses.add(res.getString(0));
           count++;
       }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allHouses);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String house = String.valueOf(parent.getItemAtPosition(position));
                Intent i = new Intent(getApplicationContext(), adminHouseControl.class);
                i.putExtra("housename",house);
                startActivity(i);
            }
        });
    }
}


