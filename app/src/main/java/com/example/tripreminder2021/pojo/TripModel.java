package com.example.tripreminder2021.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TripModel implements Serializable {

    public String getInclude_in() {
        return include_in;
    }

    public void setInclude_in(String include_in) {
        this.include_in = include_in;
    }

    public String startloc,endloc,date,time,status,tripname,dateTime;

    private String include_in;
    private List<String> notes = new ArrayList<>();
    //@TODO add lat Long

    public String  getDateTime() {
        return dateTime;
    }

    public void setDateTime(String  dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public TripModel(String startloc, String endloc, String date, String time, String tripname, String status,
                     List<String> notes, String dateTime,String include_in) {
        this.startloc = startloc;
        this.endloc = endloc;
        this.date = date;
        this.time = time;
        this.status = status;
        this.tripname = tripname;
        this.status = status;
        this.notes = notes;
        this.dateTime = dateTime;
        this.include_in = include_in;
    }
    public TripModel(String startloc, String endloc, String date, String time, String tripname, String status, List<String> notes) {
        this.startloc = startloc;
        this.endloc = endloc;
        this.date = date;
        this.time = time;
        this.status = status;
        this.tripname = tripname;
        this.status = status;
        this.notes = notes;

    }
    public TripModel(String startloc, String endloc, String date, String time, String tripname, String status) {
        this.startloc = startloc;
        this.endloc = endloc;
        this.date = date;
        this.time = time;
        this.status = status;
        this.tripname = tripname;
        this.status = status;
        this.notes = null;
    }

    public TripModel() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartloc() {
        return startloc;
    }

    public void setStartloc(String startloc) {
        this.startloc = startloc;
    }

    public String getEndloc() {
        return endloc;
    }

    public void setEndloc(String endloc) {
        this.endloc = endloc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getTripname() {
        return tripname;
    }

    public void setTripname(String tripname) {
        this.tripname = tripname;
    }

}
