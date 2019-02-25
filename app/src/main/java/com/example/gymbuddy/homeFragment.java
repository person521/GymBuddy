package com.example.gymbuddy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class homeFragment extends Fragment implements View.OnClickListener {

    private TextView usernameView, meetView;
    private DBHandler mDBHandler;
    private String username, password, msg;
    private Button matchBtn;

    public homeFragment () {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        usernameView = view.findViewById(R.id.viewUsername);
        meetView = view.findViewById(R.id.meetText);
        matchBtn = view.findViewById(R.id.matchBtn);
        mDBHandler = new DBHandler(getActivity());
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
            usernameView.setText(username);
            meetView.setText(msg);
        }
        matchBtn.setOnClickListener(this);
        return view;
    }



    public void onClick (View view) {
        if (view.getId() == R.id.matchBtn) {
            int id = mDBHandler.getUserId(username, password);
            Schedule schedule = mDBHandler.getSchedule(id);
            Member member = mDBHandler.getMember(id);
            Schedule tempSchedule;
            Member tempMember;
            int[] userArray = new int[mDBHandler.getUserCount()];
            int count = 0;
            Calendar calStart = Calendar.getInstance();
            Calendar calEnd = Calendar.getInstance();
            int day = calStart.get(Calendar.DAY_OF_WEEK);
            calStart.set(Calendar.HOUR_OF_DAY, schedule.getStartHour());
            calStart.set(Calendar.MINUTE, schedule.getStartMin());
            calEnd.set(Calendar.HOUR_OF_DAY, schedule.getEndHour());
            calEnd.set(Calendar.MINUTE, schedule.getEndMin());
            int compare = 0;
            switch (day) {
                case 1:
                    compare = schedule.getsDay();
                    break;
                case 2:
                    compare = schedule.getmDay();
                    break;
                case 3:
                    compare = schedule.gettDay();
                    break;
                case 4:
                    compare = schedule.getwDay();
                    break;
                case 5:
                    compare = schedule.getThDay();
                    break;
                case 6:
                    compare = schedule.getfDay();
                    break;
                case 7:
                    compare = schedule.getSaDay();
                    break;
            }
            for (int k = 1; k <= mDBHandler.getUserCount(); k++) {
                tempSchedule = mDBHandler.getSchedule(k);
                tempMember = mDBHandler.getMember(k);
                Calendar tCalStart = Calendar.getInstance();
                Calendar tCalEnd = Calendar.getInstance();
                tCalStart.set(Calendar.HOUR_OF_DAY, tempSchedule.getStartHour());
                tCalStart.set(Calendar.MINUTE, tempSchedule.getStartMin());
                tCalEnd.set(Calendar.HOUR_OF_DAY, tempSchedule.getEndHour());
                tCalEnd.set(Calendar.MINUTE, tempSchedule.getEndMin());
                int compare2 = 0;
                switch (day) {
                    case 1:
                        compare2 = tempSchedule.getsDay();
                        break;
                    case 2:
                        compare2 = tempSchedule.getmDay();
                        break;
                    case 3:
                        compare2 = tempSchedule.gettDay();
                        break;
                    case 4:
                        compare2 = tempSchedule.getwDay();
                        break;
                    case 5:
                        compare2 = tempSchedule.getThDay();
                        break;
                    case 6:
                        compare2 = tempSchedule.getfDay();
                        break;
                    case 7:
                        compare2 = tempSchedule.getSaDay();
                        break;
                }
                if (compare == 0) {
                    continue;
                } else if ((member.getGym().equals(tempMember.getGym())) && (member.getFitness().equals(tempMember.getFitness())) && (compare == compare2) && (compare > 0) && (compare2 > 0) && (calStart.before(tCalEnd) && (tCalStart.before(calEnd)))) {
                    userArray[count] = k;
                    count++;
                }
            }
            if ((count == 0) || count == 1) {
                Toast.makeText(getActivity(), "No match found", Toast.LENGTH_SHORT).show();
            } else {
                int[] matchedArray = Arrays.copyOfRange(userArray, 0, count);
                Random generator = new Random();
                int randomIndex = generator.nextInt(matchedArray.length);
                while (matchedArray[randomIndex] == id) {
                    randomIndex = generator.nextInt(matchedArray.length);
                }
                tempMember = mDBHandler.getMember(matchedArray[randomIndex]);
                Toast.makeText(getActivity(), "You have been matched with " + tempMember.getUsername(), Toast.LENGTH_SHORT).show();
                msg = "Meet " + tempMember.getUsername() + " at " + calStart.get(Calendar.HOUR) + ":" + calStart.get(Calendar.MINUTE) + "\n(military time) \n at " + tempMember.getGym();
                meetView.setText(msg);
            }
        }
    }

}
