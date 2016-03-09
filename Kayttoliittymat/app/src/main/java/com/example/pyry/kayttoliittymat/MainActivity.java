package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button loginButton;
    EditText password;
    EditText userName;
    String currentUser;
    String adminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //id yhdistetään muuttujaan
        loginButton = (Button) findViewById(R.id.loginButtonID);
        userName = (EditText) findViewById(R.id.usernameID);
        password = (EditText) findViewById(R.id.passwordID);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(userName.getText().toString().equals("admin")&& password.getText().toString().equals("admin")) {
                    startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                }else{
                    Snackbar.make(view, "User not found!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
        userName.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
               currentUser = userName.getText().toString();
            }
        });
        password.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                adminPassword = password.getText().toString();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
