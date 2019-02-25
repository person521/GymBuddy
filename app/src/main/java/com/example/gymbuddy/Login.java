package com.example.gymbuddy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymbuddy.data.GymContract.NewUser;

/**
 * Created by Rich on 2/27/2018.
 */

public class Login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginBtn, signBtn;
    private DBHandler mDBHandler;

    public Login() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDBHandler = new DBHandler(this);

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        signBtn = (Button) findViewById(R.id.signUp);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SQLiteDatabase db = mDBHandler.getReadableDatabase();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Cursor cursor = db.rawQuery("SELECT * FROM " + NewUser.TABLE_NAME + " WHERE " + NewUser.column_username + " =? AND " + NewUser.column_password + "=?", new String[]{username, password});
                if (cursor!=null) {
                    if (cursor.getCount() > 0) {
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                        Toast.makeText(getApplicationContext(), "login successful ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ViewProfile.class);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Login failed. Your username or password was incorrect.", Toast.LENGTH_LONG).show();
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                    }
                }
                db.close();
                cursor.close();

            }
        });

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(view);
            }
        });
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }

    /*public int getUserLoginId() {
        int userLoginId;
        SQLiteDatabase db = mDBHandler.getReadableDatabase();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Cursor cursor = db.query("SELECT * FROM " + NewUser.TABLE_NAME + " WHERE " + NewUser.column_username + " =? AND " + NewUser.column_password + "=?", new String[]{username, password});
        if (cursor==null) {
            return -1;
        }
        else {
            userLoginId = cursor.getCount();
            cursor.close();
            return userLoginId;
        }
    }*/

}
