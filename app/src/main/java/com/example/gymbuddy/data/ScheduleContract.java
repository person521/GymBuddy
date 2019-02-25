package com.example.gymbuddy.data;

import android.provider.BaseColumns;

public class ScheduleContract {

    public ScheduleContract() {
    }

    public static final class userSchedule implements BaseColumns {
        public final static String TABLE_NAME = "time";

        public final static String _ID = BaseColumns._ID;
        public final static String column_startHour = "startHour";
        public final static String column_startMin = "startMin";
        public final static String column_endHour = "endHour";
        public final static String column_endMin = "endMin";
        public final static String column_sDay = "sDay";
        public final static String column_mDay = "mDay";
        public final static String column_tDay = "tDay";
        public final static String column_wDay = "wDay";
        public final static String column_thDay = "thDay";
        public final static String column_fDay = "fDay";
        public final static String column_saDay = "saDay";

    }
}
