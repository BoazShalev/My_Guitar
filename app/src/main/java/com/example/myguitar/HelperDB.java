package com.example.myguitar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "players.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PLAYERS = "tblPlayers";
    private static final String PLAYER_ID = "playerId";
    private static final String REGISTER_USER_NAME = "stRegisterUserName";
    private static final String REGISTER_PASSWORD = "stRegisterPassword";
    private static final String[] allColumnsplayers = {PLAYER_ID, REGISTER_USER_NAME, REGISTER_PASSWORD};


    private static final String CREATE_TABLE_PLAYER = "CREATE TABLE IF NOT EXISTS " +
            TABLE_PLAYERS + " ( " +
            PLAYER_ID + " INTEGER PRIMARY KEY, " +
            REGISTER_USER_NAME + " TEXT, " +
            REGISTER_PASSWORD + " TEXT);";


    SQLiteDatabase db;

    public HelperDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(CREATE_TABLE_PLAYER);
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public Player insertPlayerRecord(Player player){
        db = getWritableDatabase();
        ContentValues valuesToAdd = new ContentValues();
        valuesToAdd.put(REGISTER_USER_NAME, player.getStRegisterUserName());
        valuesToAdd.put(REGISTER_PASSWORD, player.getStRegisterPassword());
        long id = db.insert(TABLE_PLAYERS, null, valuesToAdd);
        player.setPlayerId(id);
        db.close();
        return player;
    }

    public ArrayList<Player> getAllPlayerRecords() {
        db = getReadableDatabase(); //get access to read from database
        ArrayList<Player> customerArrayList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_PLAYERS, allColumnsplayers, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String stRegisterUserName = cursor.getString(cursor.getColumnIndexOrThrow(REGISTER_USER_NAME));
                String stRegisterPassword = cursor.getString(cursor.getColumnIndexOrThrow(REGISTER_PASSWORD));
                long playerId = cursor.getLong(cursor.getColumnIndexOrThrow(PLAYER_ID));
                Player record = new Player(playerId, stRegisterUserName, stRegisterPassword);
                customerArrayList.add(record);
            }
        }
        db.close(); // close the database
        return customerArrayList;
    }




}