package com.example.myguitar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    String stUserName, stPassword, stRegisterUserName, stRegisterPassword;
    EditText userName, password, registerUserName, registerPassword;
    Button moveToLogin, moveToSignUp;
    HelperDB helperDB;
    ArrayList<Player> playerArrayList;
    Context context = MainActivity2.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity2.this, R.color.black));
        setContentView(R.layout.activity_main2);
        init();
        moveToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Switch(new fragment1());
            }
        });
        moveToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Switch(new fragment2());
            }
        });
    }

    public void init(){


        moveToLogin = findViewById(R.id.moveToLogin);
        moveToSignUp = findViewById(R.id.moveToSignUp);

        helperDB = new HelperDB(context);
    }

    public void login(View view) {
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        stUserName= userName.getText().toString();
        stPassword= password.getText().toString();

        if(stUserName.equals("") ||stPassword.equals("")){
            Toast.makeText(this, "User name or Password are missing", Toast.LENGTH_SHORT).show();
            return;
        }

        if(playerArrayList == null) {
            playerArrayList = helperDB.getAllPlayerRecords();
        }

        int playerFound = -1;
        for(int i=0; i<playerArrayList.size() && playerFound == -1; i++){
            Player player = playerArrayList.get(i);
            if (player.getStRegisterUserName().equals(stUserName) && player.getStRegisterPassword().equals(stPassword)) {
                playerFound = i;
            }
        }
        if(playerFound == -1){
            Toast.makeText(this, "User name or Password are incorrect", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent go = new Intent(this, MainActivity3.class);
        Player chosenPlayer = playerArrayList.get(playerFound);
        go.putExtra("chosenPlayer", chosenPlayer);
        startActivity(go);
    }

    public void signUp(View view) {
        registerUserName = findViewById(R.id.registerUserName);
        registerPassword = findViewById(R.id.registerPassword);
        stRegisterUserName = registerUserName.getText().toString();
        stRegisterPassword = registerPassword.getText().toString();

        if(stRegisterUserName.equals("") || stRegisterPassword.equals("")){
            Toast.makeText(this, "User name or Password are missing", Toast.LENGTH_SHORT).show();
            return;
        }

        writeToDB();
        playerArrayList = helperDB.getAllPlayerRecords();
        Switch(new fragment1());


    }

    public void Switch(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void writeToDB() {
        Player player = new Player(stRegisterUserName, stRegisterPassword);
        helperDB.insertPlayerRecord(player);
    }
}