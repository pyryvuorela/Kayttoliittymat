package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                Cursor res = userData.getAllData();

                if (userName.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    startActivity(new Intent(getApplicationContext(), adminMainMenu.class));
                } else{
                    while (res.moveToNext()) {
                        if (res.getString(1).equals(userName.getText().toString()) && res.getString(2).equals(password.getText().toString())) {
                            Intent i = new Intent(getApplicationContext(), userMainMenu.class);
                            i.putExtra("username", userName.getText().toString());
                            startActivity(i);
                            break;
                        }
                    }
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
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }
}
