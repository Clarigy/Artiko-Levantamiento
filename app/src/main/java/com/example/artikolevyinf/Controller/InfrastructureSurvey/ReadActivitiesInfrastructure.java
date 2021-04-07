package com.example.artikolevyinf.Controller.InfrastructureSurvey;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

public class ReadActivitiesInfrastructure extends AsyncTask<String, String, String> {


    private Activity context;

    public ReadActivitiesInfrastructure(Activity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        if (CrudInfrastructure.read(context, context))
            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                }
            });
        else
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.toastMessage("Actividades Levantamiento de infrastructura no encontradas", 1, context);
                }
            });
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        /** Indica que ya termino de cargar y trata de abrir el main*/
        Globals.getInfrastructureActivitiesFinish = true;
        Utils.openMainActivity(context);
    }
}





