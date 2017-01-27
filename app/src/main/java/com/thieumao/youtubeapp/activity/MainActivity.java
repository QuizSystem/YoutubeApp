package com.thieumao.youtubeapp.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thieumao.youtubeapp.R;
import com.thieumao.youtubeapp.utils.Database;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Database database = new Database(this);
    private String sqlCreateUserTable = "CREATE TABLE IF NOT EXISTS user (_id INTEGER PRIMARY KEY, username VARCHAR(200) NOT NULL UNIQUE, password VARCHAR(200) NOT NULL)";
    private String sqlCreateHistoryTable = "CREATE TABLE IF NOT EXISTS history (_id INTEGER PRIMARY KEY, title VARCHAR(200) NOT NULL, idVideoYoutube VARCHAR(200) NOT NULL, idUser INTEGER NOT NULL)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);

        database.queryData(sqlCreateUserTable);
        database.queryData(sqlCreateHistoryTable);

        database.queryData("INSERT INTO user VALUES(null, 'thieumao', '123456')");
        database.queryData("INSERT INTO user VALUES(null, 'motmao', '123456')");
        database.queryData("INSERT INTO user VALUES(null, 'leolinhtinh', '123456')");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "";
                int i = 0;
                Cursor kq = database.getData("SELECT * FROM user");
                while (kq.moveToNext()) {
                    i++;
                    s =  s+  " -- " + i + " "+  kq.getString(0)  + " " + kq.getString(1) + " " + kq.getString(2);
                }
                Log.d("mao","mao" + s);
                Toast.makeText(MainActivity.this, "Result: " + s, Toast.LENGTH_LONG).show();
            }
        });
    }
}
