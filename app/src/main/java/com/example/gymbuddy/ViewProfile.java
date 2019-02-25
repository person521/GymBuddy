package com.example.gymbuddy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gymbuddy.data.GymContract.NewUser;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static java.util.Objects.requireNonNull;

public class ViewProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DBHandler mDBHandler = new DBHandler(this);
    private static String TAG = ViewProfile.class.getSimpleName();

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private TextView usernameView, meetView;
    private NavigationView navView;
    private String username, password;
    private int sDay, mDay, tDay, wDay, thDay, fDay, saDay;
    private TimePicker timeStart, timeEnd;
    private int startHour, startMin, endHour, endMin;
    private Bundle schedBundle;
    private schedFragment frag;
    private Button matchBtn;
    private NotificationCompat.Builder mBuilder;
    private String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();

        usernameView = findViewById(R.id.viewUsername);
        username = i.getStringExtra("username");
        password = i.getStringExtra("password");
        usernameView.setText(username);
        int id = mDBHandler.getUserId(username, password);
        Toast.makeText(getApplicationContext(), id + "", Toast.LENGTH_LONG).show();
        navView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        this.navView.setNavigationItemSelectedListener(this);
        meetView = findViewById(R.id.meetText);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        Intent intent = new Intent(getApplicationContext(), ViewProfile.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*public static class LocaltimeRange {
        private final LocalTime from;
        private final LocalTime to;

        public LocaltimeRange(LocalTime from, LocalTime to) {
            requireNonNull(from, "from must not be null");
            requireNonNull(to, "to must not be null");
            this.from = from;
            this.to = to;
        }

        public booleanoverlaps ()

    }*/

    public void match (View view) {
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
            }
            else if ((member.getGym().equals(tempMember.getGym())) && (member.getFitness().equals(tempMember.getFitness())) && (compare == compare2) && (compare > 0) && (compare2 > 0) && (calStart.before(tCalEnd) && (tCalStart.before(calEnd)))) {
                userArray[count] = k;
                count++;
            }
        }
        if ((count == 0 ) || count == 1){
            Toast.makeText(getApplicationContext(), "No match found", Toast.LENGTH_SHORT).show();
        }
        else {
            int[] matchedArray = Arrays.copyOfRange(userArray, 0, count);
            Random generator = new Random();
            int randomIndex = generator.nextInt(matchedArray.length);
            while (matchedArray[randomIndex] == id) {
                randomIndex = generator.nextInt(matchedArray.length);
            }
            tempMember = mDBHandler.getMember(matchedArray[randomIndex]);
            Toast.makeText(getApplicationContext(), "You have been matched with " + tempMember.getUsername(), Toast.LENGTH_SHORT).show();
            msg = "Meet " + tempMember.getUsername() + " at " +  calStart.get(Calendar.HOUR) + ":" + calStart.get(Calendar.MINUTE) + "\n(military time) \n at " + tempMember.getGym();
            meetView.setText(msg);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            homeFragment fragment = new homeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putString("password", password);
            fragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.main, fragment).addToBackStack(null).commit();
            Toast.makeText(getApplicationContext(), "HOME", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_schedule) {
            schedFragment fragment = new schedFragment();
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putString("password", password);
            fragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.main, fragment).addToBackStack(null).commit();
            Toast.makeText(getApplicationContext(), "SCHEDULE", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_logout) {
            logoutFragment fragment = new logoutFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.main, fragment).commit();
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            mDrawerLayout.removeDrawerListener(mDrawerToggle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
        }

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerLayout.closeDrawers();
        return true;
    }

    public void boxClick (View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.sDay:
                if (checked) {
                    sDay = 1;
                }
                else {
                    sDay = 0;
                }
                break;
            case R.id.mDay:
                if (checked) {
                    mDay = 1;
                }
                else {
                    mDay = 0;
                }
                break;
            case R.id.tDay:
                if (checked) {
                    tDay = 1;
                }
                else {
                    tDay = 0;
                }
                break;
            case R.id.wDay:
                if (checked) {
                    wDay = 1;
                }
                else {
                    wDay = 0;
                }
                break;
            case R.id.thDay:
                if (checked) {
                    thDay = 1;
                }
                else {
                    thDay = 0;
                }
                break;
            case R.id.fDay:
                if (checked) {
                    fDay = 1;
                }
                else {
                    fDay = 0;
                }
                break;
            case R.id.saDay:
                if (checked) {
                    saDay = 1;
                }
                else {
                    saDay = 0;
                }
                break;
        }
    }


    private final View.OnClickListener enterSchedule = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = getIntent();
            String username = i.getStringExtra("username");
            String password = i.getStringExtra("password");
            int id = mDBHandler.getUserId(username, password);
            Schedule sched = new Schedule(id, startHour, startMin, endHour, endMin, sDay, mDay, tDay, wDay, thDay, fDay, saDay);
            mDBHandler.addSchedule(sched);
            Toast.makeText(getApplicationContext(), "Schedule has been updated.", Toast.LENGTH_LONG).show();
            onResume();
            return;
        }
    };

}
