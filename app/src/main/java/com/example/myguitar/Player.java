package com.example.myguitar;

import java.io.Serializable;

public class Player implements Serializable {
    private long playerId;
    private String stRegisterUserName;
    private String stRegisterPassword;

    public Player(String stRegisterUserName, String stRegisterPassword) {
        this.stRegisterUserName = stRegisterUserName;
        this.stRegisterPassword = stRegisterPassword;
    }
    public Player(long playerId, String stRegisterUserName, String stRegisterPassword) {
        this.playerId = playerId;
        this.stRegisterUserName = stRegisterUserName;
        this.stRegisterPassword = stRegisterPassword;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getStRegisterUserName() {
        return stRegisterUserName;
    }

    public void setStRegisterUserName(String stRegisterUserName) {
        this.stRegisterUserName = stRegisterUserName;
    }

    public String getStRegisterPassword() {
        return stRegisterPassword;
    }

    public void setStRegisterPassword(String stRegisterPassword) {
        this.stRegisterPassword = stRegisterPassword;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "stRegisterUserName='" + stRegisterUserName + '\'' +
                ", stRegisterPassword='" + stRegisterPassword + '\'' +
                '}';
    }
}
