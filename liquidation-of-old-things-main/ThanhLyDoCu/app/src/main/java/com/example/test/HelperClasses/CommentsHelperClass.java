package com.example.test.HelperClasses;

public class CommentsHelperClass {

    public String comment, date, time, username;

    public CommentsHelperClass(){}

    public CommentsHelperClass(String comment, String date, String time, String username) {
        this.comment = comment;
        this.date = date;
        this.time = time;
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
