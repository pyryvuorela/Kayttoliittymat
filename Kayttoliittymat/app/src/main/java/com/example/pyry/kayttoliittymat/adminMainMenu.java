package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.view.Menu;
//import android.app.ActionBar;

public class adminMainMenu extends AppCompatActivity {
    Button userControl;
    Button roomControl;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //back button
        //setupActionBar();
        setContentView(R.layout.activity_admin_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //yritettiin lisata back buttonia, ehka tarpeellisia joskus
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        userControl = (Button) findViewById(R.id.usercontrolID);
        roomControl = (Button) findViewById(R.id.roomcontrolID);
        logOut = (Button) findViewById(R.id.logoutAdminID);

        userControl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adminUserControl.class));
            }
        });
        roomControl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adminRoomControl.class));
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // ehka back buttonin kaytossa oleellinen ja options menun kanssa
    // Determines if Action bar item was selected. If true then do corresponding action.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //handle presses on the action bar items
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_back:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //back button tyonalla
    //private void setupActionBar() {
    //    ActionBar actionBar = getSupportActionBar();
    //    if (actionBar != null) {
    //        actionBar.setDisplayHomeAsUpEnabled(true);
    //    }
    //}

}
