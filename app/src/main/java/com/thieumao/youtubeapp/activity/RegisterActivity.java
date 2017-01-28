package com.thieumao.youtubeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.thieumao.youtubeapp.R;
import com.thieumao.youtubeapp.utils.Database;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etFullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register");
        setContentView(R.layout.activity_register);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etFullname = (EditText) findViewById(R.id.etFullname);
    }

    public void register(View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String fullname = etFullname.getText().toString();
        if (username.length() > 0 && password.length() > 0 && fullname.length() > 0) {
            Boolean isRegister = Database.getInstance(this).queryData("INSERT INTO user VALUES(null, '" + username + "', '" + password + "', '" + fullname + "')");
            if (isRegister == true) {
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Register Failure", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please check again", Toast.LENGTH_SHORT).show();
        }
    }
}
