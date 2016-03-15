package com.example.pyry.kayttoliittymat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class modifyUserScroll extends AppCompatActivity {
    UserDatabase userData;
    Button modifyUsers;
    EditText username, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userData = new UserDatabase(this);
        username = (EditText) findViewById(R.id.UsernamemodifyID);
        password = (EditText) findViewById(R.id.UserPasswordModifyID);
        modifyUsers = (Button) findViewById(R.id.modifyUserSettingsButtonID);

        modifyUsers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean isUpdated = userData.updateData(username.getText().toString(),password.getText().toString(), null);
                if(isUpdated == true)
                    Toast.makeText(modifyUserScroll.this, "Data Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(modifyUserScroll.this, "Data not Updated", Toast.LENGTH_LONG).show();
            }
        });
    }
}
