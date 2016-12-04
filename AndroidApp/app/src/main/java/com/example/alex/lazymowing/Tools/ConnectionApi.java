package com.example.alex.lazymowing.Tools;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.net.HttpURLConnection;

/**
 * Created by Alex on 20/11/2016.
 *
 * Define the url / ip  address of the API and the port he used
 * then call a function with the id of this application / user and the wanted action
 */

public class ConnectionApi {
    private static String URL = "10.0.3.2";
    private static int port = 8080;

    public static void sendRequest(final int id, final String action)  {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                    try {
                        String completUrl = "http://" + URL + ":" + port + "/ApiMower/serviceAPI/Action/" + id + "/" + action;

                        java.net.URL url = new java.net.URL(completUrl);

                        HttpURLConnection client = (HttpURLConnection) url.openConnection();
                        client.setReadTimeout(2000);
                        client.setConnectTimeout(3000);
                        client.setRequestMethod("GET");

                        if (client.getResponseCode() == HttpURLConnection.HTTP_OK)
                            Log.d("ExecutedRequest", "Done");
                        else
                            Log.e("ExecutedRequest", "Fail");
                    }
                    catch (Exception e) {
                       Log.e("ConnectionAPI", e.getMessage());
                    }
            }
        });

        thread.start();
    }

    public static void tryConnection(final String url, final String port, final Activity acti)  {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String completUrl = "http://" + url + ":" + port + "/ApiMower/serviceAPI/Action/";

                    java.net.URL url = new java.net.URL(completUrl);

                    HttpURLConnection client = (HttpURLConnection) url.openConnection();
                    client.setReadTimeout(2000);
                    client.setConnectTimeout(3000);
                    client.setRequestMethod("GET");

                    if (client.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        acti.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(acti, "Connection succeeded", Toast.LENGTH_LONG).show();
                            }
                        });
                        return;
                    }
                }
                catch (Exception e) {
                    Log.e("ConnectionAPI", e.getMessage());
                }

                acti.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(acti, "Connection failed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        thread.start();
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        ConnectionApi.URL = URL;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        ConnectionApi.port = port;
    }

}
