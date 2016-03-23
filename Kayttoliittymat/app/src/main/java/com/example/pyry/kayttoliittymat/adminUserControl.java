package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class adminUserControl extends AppCompatActivity {
    UserDatabase userDatabase;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userDatabase = new UserDatabase(this);
        listView = (ListView) findViewById(R.id.adminUserControlListViewID);
        Cursor res = userDatabase.getAllData();
        List<String> allUsers = new ArrayList<String>();


        int count = 0;
         while (res.moveToNext()) {
             allUsers.add(res.getString(1));
              count++;
          }

           ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, allUsers);
           listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String username = String.valueOf(parent.getItemAtPosition(position));
                Intent i = new Intent(getApplicationContext(), adminModifyUser.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.adminUserControlFabID);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adminAddUser.class));
            }
        });
    }
}
