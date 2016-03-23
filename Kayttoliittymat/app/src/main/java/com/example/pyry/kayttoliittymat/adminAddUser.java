package com.example.pyry.kayttoliittymat;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class adminAddUser extends AppCompatActivity {
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

        userData = new UserDatabase(this);
        houseDatabase = new HouseDatabase(this);

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

        save.setOnClickListener(new View.OnClickListener() {
              public void onClick(View view) {

                 boolean isInserted = userData.insertData(username.getText().toString(), password.getText().toString(), null, null);
                  if(isInserted == true)
                      Toast.makeText(adminAddUser.this, "Data Inserted", Toast.LENGTH_LONG).show();
                  else
                      Toast.makeText(adminAddUser.this, "Data not Inserted", Toast.LENGTH_LONG).show();
              }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Cursor res = userData.getAllData();
                if(res.getCount() == 0){
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();

                while(res.moveToNext()){
                    buffer.append("Id :" + res.getString(0)+ "\n");
                    buffer.append("Username :" + res.getString(1)+ "\n");
                    buffer.append("Password :" + res.getString(2)+ "\n");
                    buffer.append("House :" + res.getString(3)+ "\n\n");
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
}
