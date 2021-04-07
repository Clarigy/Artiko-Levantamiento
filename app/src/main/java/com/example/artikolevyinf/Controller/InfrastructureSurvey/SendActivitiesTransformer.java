package com.example.artikolevyinf.Controller.InfrastructureSurvey;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

public class SendActivitiesTransformer extends AsyncTask<String, String, String> {

    private Activity activity;
    private DistributionTransformerActivity distributionTransformerActivity;
    private int capacity;

    public SendActivitiesTransformer(Activity activity, DistributionTransformerActivity distributionTransformerActivity, int capacity) {
        this.activity = activity;
        this.distributionTransformerActivity = distributionTransformerActivity;
        this.capacity = capacity;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (CrudInfrastructure.sendTransformer(distributionTransformerActivity, activity, capacity) && Globals.boolSyncroDataGeneral)
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Utils.toastMessage("Actividades SCR cargadas", 1, context);
                    /** Se actualiza la varibale indicando que ya fue subido */
                    int pos = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, distributionTransformerActivity.getStrIdTransformerActivity());
                    Globals.dataInfrastructureSurveyActivitiesArray.get(pos).setIsUploadAPI(true);
                    /** Se actualiza en la BD */
                    Utils.updateGeneralActivityToSQLite(activity, Globals.dataInfrastructureSurveyActivitiesArray.get(pos));
                }
            });
        else
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.toastMessage("La actividad de transformador no pudo ser enviada exitosamente", 1, activity);
                }
            });
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
