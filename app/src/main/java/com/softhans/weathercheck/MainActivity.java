package com.softhans.weathercheck;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private EditText enterLocation;
    private TextView main, description, temperature, visibility;

    private Button search;

    class Weather extends AsyncTask<String,Void, String>{ //first string means URL is in string, Void means nothing, the second string is return type is string.

        @Override
        protected String doInBackground(String... address) {

            try {
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                //established connection with address
                connection.connect();

                //retrieve data from url
                InputStream is = connection.getInputStream();

                InputStreamReader isr = new InputStreamReader(is);

                //retrieve data and return it as string
                int data = isr.read();
                String content = "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        enterLocation = findViewById(R.id.etLocation);
        main = findViewById(R.id.main);
        description = findViewById(R.id.description);
        temperature = findViewById(R.id.temperature);
        visibility = findViewById(R.id.visibility);
        search = findViewById(R.id.search);

        String content;
        Weather weather = new Weather();
        try {
            content = weather.execute("https://openweathermap.org/data/2.5/weather?q=London&appid=b6907d289e10d714a6e88b30761fae22").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
