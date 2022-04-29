package com.example.rent.log;

import android.os.AsyncTask;
import android.util.Log;

import com.example.rent.R;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetLog extends AsyncTask<String,String,String> {

    private MainActivity act;
    private String mail, password;

    public GetLog(MainActivity aa, String mail, String password){
        this.act = aa;
        this.mail = mail;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(this.act.getResources().getString(R.string.url)+"/login.php?mail=" + mail + "&password=" + password);
            Log.d("url", this.act.getResources().getString(R.string.url) + "/login.php?mail=" + mail + "&password=" + password);

            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            String resultat = new String();
            StringBuffer line = new StringBuffer();
            int val = isw.read();
            while(val!=-1){
                line.append((char)val);
                val = isw.read();
            }
            Log.d("HTTP_REPONSE",line.toString());
            return line.toString();

        }catch(Exception e){
            e.printStackTrace();
        };
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        this.act.onTaskCompleteLogin(s);
    }
}