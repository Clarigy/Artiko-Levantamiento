package com.example.artikolevyinf.Controller.Socket;

import com.example.artikolevyinf.Controller.ConnectionHttp;

import org.json.JSONObject;

import java.net.URL;

public class CrudSocket {

    public static boolean sendSocket(String value){

        URL url = ConnectionHttp.getSocketInformationByKeyword("Infrastructure");
        ConnectionHttp connectionHttp = new ConnectionHttp();

        try {
            /** Parametros que se enviaran con el post*/
            JSONObject values = new JSONObject();
            values.put("body", value);

            String response = connectionHttp.postResponse(url, values);

            if(response != null) {
                JSONObject jsonObject = new JSONObject(response);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
