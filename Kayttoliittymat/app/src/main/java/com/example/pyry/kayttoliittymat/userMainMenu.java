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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class userMainMenu extends AppCompatActivity {
    UserDatabase userDatabase;
    ListView listView;
    String currentUser;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userDatabase = new UserDatabase(this);
        listView = (ListView) findViewById(R.id.userMainMenuListView);
        logOut = (Button) findViewById(R.id.logoutUserID);
        Cursor resUser = userDatabase.getAllData();
        final List<String> allHouses = new ArrayList<String>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getString("username");
        }
        while(resUser.moveToNext()){
            if(currentUser.equals(resUser.getString(1))){
                allHouses.add(resUser.getString(3));
                allHouses.add(resUser.getString(4));
            }
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.customuser_listview, allHouses);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String house = String.valueOf(parent.getItemAtPosition(position));
                Intent i = new Intent(getApplicationContext(), userRoomSelection.class);
                i.putExtra("housename", house);
                i.putExtra("username", currentUser);
                startActivity(i);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
