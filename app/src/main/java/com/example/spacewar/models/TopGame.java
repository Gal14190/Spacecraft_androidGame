package com.example.spacewar.models;

public class TopGame {
    private int score;
    private String time;
    private float lon;
    private float lat;

    public TopGame(int score, String time, float lon, float lat) {
        this.score = score;
        this.lon = lon;
        this.lat = lat;
        this.time = time;
    }
    public TopGame(){}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
