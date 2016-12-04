package com.example.alex.lazymowing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.alex.lazymowing.Tools.ConnectionApi;

public class MainActivity extends AppCompatActivity {

    private ImageButton imTurnLeft;
    private ImageButton imTurnRight;
    private ImageButton imMoveForward;
    private ImageButton imMoveBack;

    //id of the app or user defined randomly for the example
    private final int idApp = (int)(Math.random() * 100000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        populateViews();
    }

    private void bindViews()
    {
        imMoveBack = (ImageButton)findViewById(R.id.imMoveBack);
        imMoveForward = (ImageButton)findViewById(R.id.imMoveForward);
        imTurnLeft = (ImageButton)findViewById(R.id.imTurnLeft);
        imTurnRight = (ImageButton)findViewById(R.id.imTurnRight);
    }

    private void populateViews() {
        imMoveBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionApi.sendRequest(idApp, "moveBack");
            }
        });

        imMoveForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionApi.sendRequest(idApp, "moveForward");
            }
        });

        imTurnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionApi.sendRequest(idApp, "turnLeft");
            }
        });

        imTurnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionApi.sendRequest(idApp, "turnRight");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
