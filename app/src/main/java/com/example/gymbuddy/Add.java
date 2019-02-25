package com.example.gymbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymbuddy.data.GymContract.NewUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rich on 2/27/2018.
 */

public class Add extends AppCompatActivity {

    private EditText fnameEditText;
    private EditText lnameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private Spinner gymSpinner;
    private Spinner fitnessSpinner;
    Button signUpBtn;

    protected DBHandler mDBHandler;
    private ArrayAdapter<Member> adapt;

    List<Member> MemberArrayList = new ArrayList<Member>();

    private int gym = NewUser.gym_jersey_strong;
    private int fitness = NewUser.fitness_beginner;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fnameEditText = (EditText) findViewById(R.id.fname);
        lnameEditText = (EditText) findViewById(R.id.lname);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.new_password);
        gymSpinner = (Spinner) findViewById(R.id.gym);
        fitnessSpinner = (Spinner) findViewById(R.id.fitness);
        signUpBtn = (Button) findViewById(R.id.signButton);

        mDBHandler = new DBHandler(this);

        signUpBtn.setOnClickListener(enterUser);


        setupSpinner();
    }

    private void setupSpinner() {
        ArrayAdapter gymSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.gym_array, android.R.layout.simple_dropdown_item_1line);

        gymSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        gymSpinner.setAdapter(gymSpinnerAdapter);

        gymSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getResources().getStringArray(R.array.gym_array)[0])) {
                        gym = NewUser.gym_jersey_strong;
                    } else if (selection.equals(getResources().getStringArray(R.array.gym_array)[1])) {
                        gym = NewUser.gym_planet_fitness;
                    } else {
                        gym = NewUser.gym_retro_fitness;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gym = NewUser.gym_jersey_strong;
            }
        });

        ArrayAdapter fitnessSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.fitness_array, android.R.layout.simple_dropdown_item_1line);

        fitnessSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        fitnessSpinner.setAdapter(fitnessSpinnerAdapter);

        fitnessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getResources().getStringArray(R.array.fitness_array)[0])) {
                        fitness = NewUser.fitness_beginner;
                    } else if (selection.equals(getResources().getStringArray(R.array.fitness_array)[1])) {
                        fitness = NewUser.fitness_intermediate;
                    } else {
                        fitness = NewUser.fitness_experienced;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                fitness = NewUser.fitness_beginner;
            }
        });
    }

    /*private class MyAdapter extends ArrayAdapter<Member> {
        Context context;
        List<Member> memberList = new ArrayList<Member>();

        public MyAdapter(Context c, int id, List<Member> objects) {
            super(c, id, objects);
            memberList = objects;
            context = c;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_match, parent, false);
            }

            TextView fname = (TextView) convertView.findViewById(R.id.viewFname);
            TextView lname = (TextView) convertView.findViewById(R.id.viewLname);
        }
    }*/

    private final View.OnClickListener enterUser = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Member user = new Member(mDBHandler.getUserCount(), String.valueOf(usernameEditText.getText().toString()), String.valueOf(passwordEditText.getText().toString()), String.valueOf(fnameEditText.getText().toString()), String.valueOf(lnameEditText.getText().toString()),
                    String.valueOf(gymSpinner.getSelectedItem().toString()), String.valueOf(fitnessSpinner.getSelectedItem().toString()), mDBHandler.getUserCount() + 1);
            Schedule sched = new Schedule(mDBHandler.getUserCount(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            mDBHandler.addUsers(user);
            mDBHandler.addSchedule(sched);
            MemberArrayList.add(user);
            onResume();
            Toast.makeText(getApplicationContext(), String.valueOf(usernameEditText.getText().toString()) + " has been added.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            return;
        }
    };

}
