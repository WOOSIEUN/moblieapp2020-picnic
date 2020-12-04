package com.example.moblieapp2020;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GetDbData extends AsyncTask<String, Void, String> {
    String sendMsg, recvMsg;

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("http://192.168.56.1:8080/DB/androidDB.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            Log.d("Connection","DB Connection Success");
            if(strings[1].equals("1"))
                Log.d("Connection","Data is DBTable=" + strings[0] + "&mode=" + strings[1] + "&latitude=" + strings[2] + "&longitude=" + strings[3]);
            else if(strings[1].equals("2"))
                Log.d("Connection","Data is DBTable=" + strings[0] + "&mode=" + strings[1] + "&location=" + strings[2]);
            else if(strings[1].equals("3"))
                Log.d("Connection","Data is DBTable=" + strings[0] + "&mode=" + strings[1] + "&id=" + strings[2]);

            //파라미터 전달
            OutputStreamWriter outputStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            if(strings[1].equals("1"))
                sendMsg = "DBTable=" + strings[0] + "&mode=" + strings[1] + "&latitude=" + strings[2] + "&longitude=" + strings[3];
            else if(strings[1].equals("2"))
                sendMsg = "DBTable=" + strings[0] + "&mode=" + strings[1] + "&location=" + strings[2];
            else if(strings[1].equals("3"))
                sendMsg = "DBTable=" + strings[0] + "&mode=" + strings[1] + "&id=" + strings[2];

            outputStream.write(sendMsg);
            outputStream.flush();
            Log.d("Connection","Send msg to Server Success");

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader inputStream = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader recvBuffer = new BufferedReader(inputStream);
                StringBuffer strBuffer = new StringBuffer();
                Log.d("Connection","Msg Receiving...");
                //Recv Msg
                String temp;
                while ((temp = recvBuffer.readLine()) != null)
                    strBuffer.append(temp);
                recvMsg = strBuffer.toString();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("Connection","Msg Receive Success");
        return recvMsg;
    }
}