package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    UserDatabase userData;
    Button loginButton;
    EditText password;
    EditText userName;
    String currentUser;
    String adminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userData = new UserDatabase(this);


        //id yhdistetään muuttujaan
        loginButton = (Button) findViewById(R.id.loginButtonID);
        userName = (EditText) findViewById(R.id.addUsernameID);
        password = (EditText) findViewById(R.id.addPasswordID);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(userName.getText().toString().equals("admin")&& password.getText().toString().equals("admin")) {
                    startActivity(new Intent(getApplicationContext(), adminMainMenu.class));
                }
                if(userName.getText().toString().equals("asd")&& password.getText().toString().equals("asd")) {
                    startActivity(new Intent(getApplicationContext(), userMainMenu.class));
                }
                else{
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
        password.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                adminPassword = password.getText().toString();
            }
        });
    }
}
