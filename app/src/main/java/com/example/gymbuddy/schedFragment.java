package com.example.gymbuddy;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gymbuddy.data.GymContract;

import static android.content.ContentValues.TAG;

public class schedFragment extends Fragment implements View.OnClickListener {

    private Button schedBtn;
    protected DBHandler mDBHandler;
    private int sDay, mDay, tDay, wDay, thDay, fDay, saDay;
    private TimePicker timeStart, timeEnd;
    private int startHour, startMin, endHour, endMin;
    CheckBox boxSDay, boxMDay, boxTDay, boxWDay, boxThDay, boxFDay, boxSaDay;

    public schedFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        mDBHandler = new DBHandler(getActivity());
        boxSDay = view.findViewById(R.id.sDay);
        boxSaDay = view.findViewById(R.id.saDay);
        boxMDay = view.findViewById(R.id.mDay);
        boxTDay = view.findViewById(R.id.tDay);
        boxWDay = view.findViewById(R.id.wDay);
        boxThDay = view.findViewById(R.id.thDay);
        boxFDay = view.findViewById(R.id.fDay);
        boxSaDay = view.findViewById(R.id.saDay);
        schedBtn = view.findViewById(R.id.schedBtn);
        timeStart = view.findViewById(R.id.timePickStart);
        timeEnd = view.findViewById(R.id.timePickEnd);
        timeStart.setIs24HourView(true);
        timeEnd.setIs24HourView(true);
        schedBtn.setOnClickListener(this);
        boxSDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    sDay = 1;
                }
                else
                    sDay = 0;
            }
        });
        boxMDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    mDay = 1;
                }
                else
                    mDay = 0;
            }
        });
        boxTDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    tDay = 1;
                }
                else
                    tDay = 0;
            }
        });
        boxWDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    wDay = 1;
                }
                else
                    wDay = 0;
            }
        });
        boxThDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    thDay = 1;
                }
                else
                    thDay = 0;
            }
        });
        boxFDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    fDay = 1;
                }
                else
                    fDay = 0;
            }
        });
        boxSaDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    saDay = 1;
                }
                else
                    saDay = 0;
            }
        });
        mDBHandler = new DBHandler(getActivity());
        return view;
    }


    public void onClick(View view) {
        if (view.getId() == R.id.schedBtn) {
            String username = getArguments().getString("username");
            String password = getArguments().getString("password");
            startHour = timeStart.getHour();
            startMin = timeStart.getMinute();
            endHour = timeEnd.getHour();
            endMin = timeEnd.getMinute();
            int id = mDBHandler.getUserId(username, password);
            Schedule schedule = mDBHandler.getSchedule(id);
            schedule.setStartHour(startHour);
            schedule.setStartMin(startMin);
            schedule.setEndHour(endHour);
            schedule.setEndMin(endMin);
            schedule.setsDay(sDay);
            schedule.setmDay(mDay);
            schedule.settDay(tDay);
            schedule.setwDay(wDay);
            schedule.setThDay(thDay);
            schedule.setfDay(fDay);
            schedule.setSaDay(saDay);
            mDBHandler.updateSchedule(schedule);
            Toast.makeText(getActivity(), "Schedule has been updated.", Toast.LENGTH_LONG).show();
            onResume();
            return;
        }
    }
}
