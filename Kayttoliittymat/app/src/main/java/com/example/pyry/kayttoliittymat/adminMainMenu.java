package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.view.Menu;

public class adminMainMenu extends AppCompatActivity {
    Button userControl;
    Button roomControl;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
}
