package com.example.pyry.kayttoliittymat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class adminModifyUser extends AppCompatActivity {
    UserDatabase userData;
    HouseDatabase houseDatabase;
    Button modifyUsers, deleteUser;
    EditText username, password, usernameDel;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_modify_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userData = new UserDatabase(this);
        houseDatabase = new HouseDatabase(this);
        username = (EditText) findViewById(R.id.UsernamemodifyID);
        password = (EditText) findViewById(R.id.UserPasswordModifyID);
        usernameDel = (EditText) findViewById(R.id.UsernamedeleteID);
        modifyUsers = (Button) findViewById(R.id.modifyUserSettingsButtonID);
        deleteUser = (Button) findViewById(R.id.modifyUserDeleteButtonID);
        listView = (ListView) findViewById(R.id.adminModifyUserListView);

        Cursor res = houseDatabase.getAllData();
        List<String> allUsers = new ArrayList<String>();

        int count = 0;
        while (res.moveToNext()) {
            allUsers.add(res.getString(0));
            count++;
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, allUsers);
        listView.setAdapter(listAdapter);

        modifyUsers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean isUpdated = userData.updateData(username.getText().toString(),password.getText().toString(), null, null);
                if(isUpdated == true)
                    Toast.makeText(adminModifyUser.this, "Data Updated", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(adminModifyUser.this, "Data not Updated", Toast.LENGTH_LONG).show();
            }
        });
        deleteUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Integer isDeleted = userData.deleteData(usernameDel.getText().toString());
                if(isDeleted > 0)
                    Toast.makeText(adminModifyUser.this, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(adminModifyUser.this, "Data not Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }
}
