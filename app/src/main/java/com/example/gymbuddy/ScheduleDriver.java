package com.example.gymbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ScheduleDriver extends AppCompatActivity {

    private EditText timeStart;
    private EditText timeEnd;
    private CheckBox sDayCheck;
    private CheckBox mDayCheck;
    private CheckBox tDayCheck;
    private CheckBox wDayCheck;
    private CheckBox thDayCheck;
    private CheckBox fDayCheck;
    private CheckBox saDayCheck;
    private Button schedBtn;
    private DBHandler mDBHandler = new DBHandler(this);
    private int sDay, mDay, tDay, wDay, thDay, fDay, saDay;

    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_schedule);

        timeStart = findViewById(R.id.timeStart);
        timeEnd = findViewById(R.id.timeEnd);
        sDayCheck = findViewById(R.id.sDay);
        mDayCheck = findViewById(R.id.mDay);
        tDayCheck = findViewById(R.id.tDay);
        wDayCheck = findViewById(R.id.wDay);
        thDayCheck = findViewById(R.id.thDay);
        fDayCheck = findViewById(R.id.fDay);
        saDayCheck = findViewById(R.id.saDay);
        schedBtn = findViewById(R.id.schedBtn);

        mDBHandler = new DBHandler(this);

        //schedBtn.setOnClickListener(enterSchedule);

    }

    public void onCheckboxClicked (View view) {
        boolean checked = ((CheckBox) view).isChecked();
        //Intent i = getIntent();
        //String username = i.getStringExtra("username");
        //String password = i.getStringExtra("password");
        //int id = mDBHandler.getUserId(username, password);

        switch (view.getId()) {
            case R.id.sDay:
                if (checked) {
                    sDay = 1;
                    Toast.makeText(getApplicationContext(), "check", Toast.LENGTH_SHORT).show();
                }
                else
                    sDay = 0;
                Toast.makeText(getApplicationContext(), "no check", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mDay:
                if (checked)
                    mDay = 1;
                else
                    mDay = 0;
                break;
            case R.id.tDay:
                if (checked)
                    tDay = 1;
                else
                    tDay = 0;
                break;
            case R.id.wDay:
                if (checked)
                    wDay = 1;
                else
                    wDay = 0;
                break;
            case R.id.thDay:
                if (checked)
                    thDay = 1;
                else
                    thDay = 0;
                break;
            case R.id.fDay:
                if (checked)
                    fDay = 1;
                else
                    fDay = 0;
                break;
            case R.id.saDay:
                if (checked)
                    saDay = 1;
                else
                    saDay = 0;
                break;
        }
    }

    /*private final View.OnClickListener enterSchedule = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = getIntent();
            String username = i.getStringExtra("username");
            String password = i.getStringExtra("password");
            int id = mDBHandler.getUserId(username, password);
            Schedule sched = new Schedule(id, sDay, mDay, tDay, wDay, thDay, fDay, saDay);
            mDBHandler.addSchedule(sched);
            Toast.makeText(getApplicationContext(), "Schedule has been updated.", Toast.LENGTH_LONG).show();
            onResume();
            return;
        }
    };*/

}
