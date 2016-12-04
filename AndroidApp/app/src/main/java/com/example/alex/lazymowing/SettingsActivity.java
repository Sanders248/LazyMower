package com.example.alex.lazymowing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Connection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.lazymowing.Tools.ConnectionApi;

/**
 * Created by Alex on 04/12/2016.
 */

public class SettingsActivity extends AppCompatActivity {
    private EditText editTextUrl;
    private EditText editTextPort;
    private Button buttonChangeSet;
    private Button buttonTrySet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bindViews();
        populateViews();
    }

    private void bindViews()
    {
        editTextUrl = (EditText)findViewById(R.id.editTextURL);
        editTextPort = (EditText)findViewById(R.id.editTextPort);
        buttonChangeSet = (Button)findViewById(R.id.buttonChangeSet);
        buttonTrySet = (Button)findViewById(R.id.buttonTryCo);
    }

    private void populateViews()
    {
        final Activity acti = this;

        editTextPort.setText(String.valueOf(ConnectionApi.getPort()));
        editTextUrl.setText(ConnectionApi.getURL());

        buttonChangeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionApi.setPort(Integer.parseInt(editTextPort.getText().toString()));
                ConnectionApi.setURL(editTextUrl.getText().toString());
                Toast.makeText(acti, "Settings saved", Toast.LENGTH_LONG).show();
            }
        });

        buttonTrySet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(acti, "Waiting for server response", Toast.LENGTH_LONG).show();
                ConnectionApi.tryConnection(editTextUrl.getText().toString(), editTextPort.getText().toString(), acti);
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        editTextPort.setText(String.valueOf(ConnectionApi.getPort()));
        editTextUrl.setText(ConnectionApi.getURL());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
