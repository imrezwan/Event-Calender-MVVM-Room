package com.rezwan2525.event_calender_mvvm_room.services.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "events_table")
public class Event {

    @NonNull
    @PrimaryKey
    public String uid;

    @ColumnInfo(name = "title")
    public String eventTitle;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "start_time")
    public String startTime;

    @ColumnInfo(name = "end_time")
    public String endTime;

    @ColumnInfo(name = "desc")
    public String descriptions;


    public Event(){}

    @Ignore
    public Event(String eventTitle, String date, String startTime, String endTime, String descriptions) {
        this.eventTitle = eventTitle;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.descriptions = descriptions;
    }

    @Ignore
    public Event(String uid, String eventTitle, String date, String startTime, String endTime, String descriptions) {
        this.uid = uid;
        this.eventTitle = eventTitle;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.descriptions = descriptions;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Event{" +
                "uid=" + uid +
                ", eventTitle='" + eventTitle + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }
}
