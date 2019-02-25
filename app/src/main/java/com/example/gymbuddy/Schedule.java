package com.example.gymbuddy;

public class Schedule {

    private int _id;
    private int startHour, startMin, endHour, endMin;
    private int sDay, mDay, tDay, wDay, thDay, fDay, saDay;

    public Schedule() {
        super();
    }

    public Schedule (int _id, int startHour, int startMin, int endHour, int endMin, int sDay, int mDay, int tDay, int wDay, int thDay, int fDay, int saDay) {
        super();
        this._id = _id;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.sDay = sDay;
        this.mDay = mDay;
        this.tDay = tDay;
        this.wDay = wDay;
        this.thDay = thDay;
        this.fDay = fDay;
        this.saDay = saDay;
    }

    public int get_id() {
        return _id;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMin() {
        return endMin;
    }

    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

    public int getsDay() {
        return sDay;
    }

    public void setsDay(int sDay) {
        this.sDay = sDay;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public int gettDay() {
        return tDay;
    }

    public void settDay(int tDay) {
        this.tDay = tDay;
    }

    public int getwDay() {
        return wDay;
    }

    public void setwDay(int wDay) {
        this.wDay = wDay;
    }

    public int getThDay() {
        return thDay;
    }

    public void setThDay(int thDay) {
        this.thDay = thDay;
    }

    public int getfDay() {
        return fDay;
    }

    public void setfDay(int fDay) {
        this.fDay = fDay;
    }

    public int getSaDay() {
        return saDay;
    }

    public void setSaDay(int saDay) {
        this.saDay = saDay;
    }
}
