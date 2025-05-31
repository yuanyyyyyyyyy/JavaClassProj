package com.example.exp1;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 会议预定实体类
 */
public class Reservation {
    private MeetingRoom meetingRoom;//会议室
    private LocalDate date;//预定日期
    private LocalTime startTime;//预定开始时间
    private LocalTime endTime;//预定结束时间

    //构造函数
    public Reservation(MeetingRoom meetingRoom, LocalDate data, LocalTime startTime, LocalTime endTime) {
        this.meetingRoom = meetingRoom;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //setter 和 getter
    public MeetingRoom getMeetingRoom(){
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom){
        this.meetingRoom = meetingRoom;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }

    public LocalTime getEndTime(){
        return endTime;
    }

    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }

    //toString
    @Override
    public String toString(){
        return "预订信息{" + 
               "meetingRoom = " + meetingRoom +
               ", date = " + date +
               ", startTime = " + startTime +
               ", endTime = " + endTime + 
               '}';
    }
}
