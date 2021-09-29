package com.rezwan2525.event_calender_mvvm_room.services.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    public int uid;

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


    public Event(String eventTitle, String date, String startTime, String endTime, String descriptions) {
        this.eventTitle = eventTitle;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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
