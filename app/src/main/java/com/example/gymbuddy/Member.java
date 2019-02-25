package com.example.gymbuddy;

/**
 * Created by Rich on 2/27/2018.
 */

public class Member {



    private int _id;
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String gym;
    private String fitness;
    private int sched_id;


    public Member() {
        super();
    }

    public Member(int _id, String username, String password, String fname, String lname, String gym, String fitness, int sched_id) {
        super();
        this._id = _id;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.gym = gym;
        this.fitness = fitness;
        this.sched_id = sched_id;
    }

    public Member(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public int get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getFitness() {
        return fitness;
    }

    public void setFitness(String fitness) {
        this.fitness = fitness;
    }

    public int getSched_id() {
        return sched_id;
    }

    public void setSched_id(int sched_id) {
        this.sched_id = sched_id;
    }

    public String toString() {
        return "Member [username = " + username + ", password = " + password + "]";
    }

}
