package com.example.alex.lazymowing;

import android.util.Log;

import java.net.HttpURLConnection;

/**
 * Created by Alex on 20/11/2016.
 *
 * Define the url / ip  address of the API and the port he used
 * then call a function with the id of this application / user and the wanted action
 */

public class ConnectionApi {
    private static final String URL = "10.0.3.2";
    private static final int port = 8080;

    public static void sendRequest(final int id, final String action)  {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                    try {
                        String completUrl = "http://" + URL + ":" + port + "/ApiMower/serviceAPI/Action/" + id + "/" + action;

                        java.net.URL url = new java.net.URL(completUrl);

                        HttpURLConnection client = (HttpURLConnection) url.openConnection();
                        client.setReadTimeout(10000);
                        client.setConnectTimeout(15000);
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
}
