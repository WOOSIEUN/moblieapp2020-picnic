package com.example.moblieapp2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GetDbData extends AppCompatActivity {
    String sendMsg, recvMsg;

    protected String doInBackGround(String... strings) {
        try {
            URL url = new URL("http://ocalhost/:8080/DB/Android/androidDB.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");

            //파라미터 전달
            OutputStreamWriter outputStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            sendMsg = "DBTable=" + strings[0] + "&mode=" + strings[1] + "&latitude=" + strings[2] + "&longitude=" + strings[2];
            outputStream.write(sendMsg);
            outputStream.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader inputStream = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader recvBuffer = new BufferedReader(inputStream);
                StringBuffer strBuffer = new StringBuffer();

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
        return recvMsg;
    }
}