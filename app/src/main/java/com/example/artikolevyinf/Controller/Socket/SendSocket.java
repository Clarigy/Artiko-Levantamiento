package com.example.artikolevyinf.Controller.Socket;

import android.app.Activity;
import android.os.AsyncTask;

public class SendSocket extends AsyncTask<String, String, String> {

    private Activity activity;
    private String strValue;

    public SendSocket(Activity activity, String strValue) {
        this.activity = activity;
        this.strValue = strValue;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (CrudSocket.sendSocket(strValue))
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
