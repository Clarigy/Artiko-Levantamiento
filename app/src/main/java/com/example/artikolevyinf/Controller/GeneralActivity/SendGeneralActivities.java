package com.example.artikolevyinf.Controller.GeneralActivity;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Utils.Utils;

public class SendGeneralActivities extends AsyncTask<String, String, String> {
    private Activity activity;
    private CardGeneralActivity cardGeneralActivity;
    private boolean boolIsFailed;
    private String strReasonFailed;
    private String strDetailFailed;

    public SendGeneralActivities(Activity activity, CardGeneralActivity cardGeneralActivity, boolean boolIsFailed, String strReasonFailed, String strDetailFailed) {
        this.activity = activity;
        this.cardGeneralActivity = cardGeneralActivity;
        this.boolIsFailed = boolIsFailed;
        this.strReasonFailed = strReasonFailed;
        this.strDetailFailed = strDetailFailed;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (CrudGeneral.sendGeneralActivity(cardGeneralActivity, boolIsFailed, strReasonFailed, strDetailFailed))
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Utils.toastMessage("Actividades SCR cargadas", 1, context);
//                    if(cardGeneralActivity.getStrType().equals(Globals.strScrActivities)){
//                        /** Se actualiza la varibale indicando que ya fue subido */
//                        int pos = Utils.getPositionArrayActivitiesById(Globals.dataScrActivitiesArray, cardGeneralActivity.getStrId());
//                        Globals.dataScrActivitiesArray.get(pos).setIsUploadAPI(true);
//                        /** Se actualiza en la BD */
//                        Utils.updateGeneralActivityToSQLite(activity, Globals.dataScrActivitiesArray.get(pos));
//                    }else if(cardGeneralActivity.getStrType().equals(Globals.strInfrastructureSurveyActivities)){
//                        /** Se actualiza la varibale indicando que ya fue subido */
//                        int pos = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, cardGeneralActivity.getStrId());
//                        Globals.dataInfrastructureSurveyActivitiesArray.get(pos).setIsUploadAPI(true);
//                        /** Se actualiza en la BD */
//                        Utils.updateGeneralActivityToSQLite(activity, Globals.dataInfrastructureSurveyActivitiesArray.get(pos));
//                    }

                }
            });
        else
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.toastMessage("La actividad general no pudo ser enviada exitosamente", 1, activity);

                }
            });
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
