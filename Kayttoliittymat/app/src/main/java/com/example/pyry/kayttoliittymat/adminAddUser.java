package com.example.pyry.kayttoliittymat;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class adminAddUser extends AppCompatActivity {
    ArrayList<String> usersSelectedHouses;
    UserDatabase userData;
    HouseDatabase houseDatabase;
    EditText username;
    EditText password;
    Button save;
    Button viewAll;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        userData = new UserDatabase(this);
        houseDatabase = new HouseDatabase(this);
        usersSelectedHouses = new ArrayList<String>();

        username = (EditText) findViewById(R.id.addUsernameID);
        password = (EditText) findViewById(R.id.addPasswordID);
        save = (Button) findViewById(R.id.addNewUserButtonID);
        viewAll = (Button) findViewById(R.id.ViewAllID);
        listView = (ListView) findViewById(R.id.adminAddUserListViewID);

        Cursor res = houseDatabase.getAllData();
        List<String> allUsers = new ArrayList<String>();

        int count = 0;
        while (res.moveToNext()) {
            allUsers.add(res.getString(0));
            count++;
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.custom_listview, allUsers);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String house = String.valueOf(parent.getItemAtPosition(position));
                usersSelectedHouses.add(house);
                Toast.makeText(adminAddUser.this, "House selected", Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
              public void onClick(View view) {
                 boolean isInserted = userData.insertData(username.getText().toString(), password.getText().toString(), usersSelectedHouses.get(0));
                  if(isInserted == true) {
                      Toast.makeText(adminAddUser.this, "Data Inserted", Toast.LENGTH_LONG).show();
                      startActivity(new Intent(getApplicationContext(), adminUserControl.class));
                  }
                  else
                      Toast.makeText(adminAddUser.this, "Data not Inserted", Toast.LENGTH_LONG).show();
              }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Cursor reso = userData.getAllData();
                if(reso.getCount() == 0){
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();

                while(reso.moveToNext()){
                    buffer.append("Id :" + reso.getString(0)+ "\n");
                    buffer.append("Username :" + reso.getString(1)+ "\n");
                    buffer.append("Password :" + reso.getString(2)+ "\n");
                    buffer.append("House :" + reso.getString(3)+ "\n\n");

                }
                showMessage("Data", buffer.toString());

            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getApplicationContext(), adminUserControl.class));
            return  true;
        }
        return super.onOptionsItemSelected(item);

    }
}
