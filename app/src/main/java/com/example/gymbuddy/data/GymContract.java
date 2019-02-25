package com.example.gymbuddy.data;

import android.provider.BaseColumns;

/**
 * Created by Rich on 2/27/2018.
 */

public class GymContract {

    public GymContract() {
    }

    public static final class NewUser implements BaseColumns {
        public final static String TABLE_NAME = "users";

        public final static String _ID = BaseColumns._ID;
        public static final String column_fname = "fname";
        public static final String column_lname = "lname";
        public static final String column_fitness = "fitness";
        public static final String column_gym = "gym";
        public static final String column_username = "username";
        public static final String column_password = "password";
        public static final String column_sched = "schedule";

        public static final int gym_jersey_strong = 0;
        public static final int gym_planet_fitness = 1;
        public static final int gym_retro_fitness = 2;

        public static final int fitness_beginner = 0;
        public static final int fitness_intermediate = 1;
        public static final int fitness_experienced = 2;
    }

}
