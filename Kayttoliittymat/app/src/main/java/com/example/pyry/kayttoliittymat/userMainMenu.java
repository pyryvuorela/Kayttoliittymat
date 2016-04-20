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
    String currentUser;
    Button logOut;
    Button house1;
    Button house2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userDatabase = new UserDatabase(this);
        logOut = (Button) findViewById(R.id.logoutUserID);
        house1 = (Button) findViewById(R.id.userMAinMenuHouse1);
        house2 = (Button) findViewById(R.id.userMAinMenuHouse2);
        Cursor resUser = userDatabase.getAllData();
        final List<String> allHouses = new ArrayList<String>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentUser = extras.getString("username");
        }
        while(resUser.moveToNext()){
            if(currentUser.equals(resUser.getString(1))){
                if(resUser.getString(3).equals("")){
                    house1.setVisibility(View.INVISIBLE);
                } else house1.setText(resUser.getString(3));
                if(resUser.getString(4).equals("")){
                    house2.setVisibility(View.INVISIBLE);
                } else house2.setText(resUser.getString(4));

            }
        }

        logOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        house1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String house = house1.getText().toString();
                Intent i = new Intent(getApplicationContext(), userRoomSelection.class);
                i.putExtra("housename", house);
                i.putExtra("username", currentUser);
                startActivity(i);
            }
        });
        house2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String house = house2.getText().toString();
                Intent i = new Intent(getApplicationContext(), userRoomSelection.class);
                i.putExtra("housename", house);
                i.putExtra("username", currentUser);
                startActivity(i);            }
        });

    }
}
