package com.example.gymbuddy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymbuddy.data.GymContract;

public class logoutFragment extends Fragment implements View.OnClickListener {

    private Button loginBtn, signBtn;
    private DBHandler mDBHandler;
    private EditText usernameEditText, passwordEditText;

    public logoutFragment () {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        mDBHandler = new DBHandler(getActivity());
        loginBtn = (Button) view.findViewById(R.id.login);
        signBtn = view.findViewById(R.id.signUp);
        usernameEditText = view.findViewById(R.id.username);
        passwordEditText = view.findViewById(R.id.password);
        usernameEditText.setText("");
        passwordEditText.setText("");
        loginBtn.setOnClickListener(this);
        signBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login) {
            SQLiteDatabase db = mDBHandler.getReadableDatabase();
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            Cursor cursor = db.rawQuery("SELECT * FROM " + GymContract.NewUser.TABLE_NAME + " WHERE " + GymContract.NewUser.column_username + " =? AND " + GymContract.NewUser.column_password + "=?", new String[]{username, password});
            if (cursor!=null) {
                if (cursor.getCount() > 0) {
                    Toast.makeText(getActivity(), "login successful ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), ViewProfile.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Login failed. Your username or password was incorrect.", Toast.LENGTH_LONG).show();
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                }
            }
            db.close();
            cursor.close();
        }
        else if (view.getId() == R.id.signUp) {
            Intent intent = new Intent(getActivity(), Add.class);
            startActivity(intent);
        }
    }

    /*public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }*/
}
