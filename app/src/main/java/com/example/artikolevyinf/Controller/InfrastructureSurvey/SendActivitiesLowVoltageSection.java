package com.example.artikolevyinf.Controller.InfrastructureSurvey;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

public class SendActivitiesLowVoltageSection extends AsyncTask<String, String, String> {
    private Activity activity;
    private LowVoltageSectionActivity lowVoltageSectionActivity;

    public SendActivitiesLowVoltageSection(Activity activity, LowVoltageSectionActivity lowVoltageSectionActivity) {
        this.activity = activity;
        this.lowVoltageSectionActivity = lowVoltageSectionActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (CrudInfrastructure.sendLowVoltageSection(lowVoltageSectionActivity, activity) && Globals.boolSyncroDataGeneral)
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Utils.toastMessage("Actividades SCR cargadas", 1, context);
                    /** Se actualiza la varibale indicando que ya fue subido */
                    int pos = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, lowVoltageSectionActivity.getStrIdActivity());
                    Globals.dataInfrastructureSurveyActivitiesArray.get(pos).setIsUploadAPI(true);
                    /** Se actualiza en la BD */
                    Utils.updateGeneralActivityToSQLite(activity, Globals.dataInfrastructureSurveyActivitiesArray.get(pos));
                }
            });
        else
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.toastMessage("La actividad no pudo ser enviada exitosamente tramo aereo", 1, activity);

                }
            });
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
