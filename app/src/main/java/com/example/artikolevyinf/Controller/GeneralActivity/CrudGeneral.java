package com.example.artikolevyinf.Controller.GeneralActivity;

import android.util.Log;

import com.example.artikolevyinf.Controller.ConnectionHttp;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.net.URL;

public class CrudGeneral {

    public static boolean sendGeneralActivity(CardGeneralActivity cardGeneralActivity, boolean isFailed, String reasonFailed, String detailFailed){
        URL url = ConnectionHttp.getActivityInformationByKeyword("actividad-general");
        ConnectionHttp connectionHttp = new ConnectionHttp();

        try {
            /** Parametros que se enviaran con el post*/
            JSONObject values = new JSONObject();
            values.put("p_tipo_orden", cardGeneralActivity.getStrActivityTitle());
            values.put("p_serial", cardGeneralActivity.getStrId());
            values.put("p_direccion", cardGeneralActivity.getStrAddress());

            if(cardGeneralActivity.getStrActivityTitle().equals(Globals.strTransformer)){
                values.put("p_lat", cardGeneralActivity.getDbLatitude());
                values.put("p_lon", cardGeneralActivity.getDbLongitude());
            }else if(cardGeneralActivity.getStrActivityTitle().equals(Globals.strLowVoltageSection)){
                /** Se obtiene el centro */
                LatLng latLng = Utils.getCenterTwoPoints(cardGeneralActivity.getDbLatitude(), cardGeneralActivity.getDbLongitude(), cardGeneralActivity.getDbLatitude2(), cardGeneralActivity.getDbLongitude2());
                values.put("p_lat", latLng.latitude);
                values.put("p_lon", latLng.longitude);
            }

            values.put("p_cedula", Globals.strUserDocument);
            values.put("p_estado", cardGeneralActivity.getStrState());
            values.put("p_fecha_inicio_ejecutada", cardGeneralActivity.getDateStartExecuted());
            values.put("p_fecha_fin_ejecutada", cardGeneralActivity.getDateFinishExecuted());
            values.put("p_fecha_inicio_fallida", cardGeneralActivity.getDateStartFailed() == null ? "" : cardGeneralActivity.getDateStartFailed());
            values.put("p_fecha_fin_fallida", cardGeneralActivity.getDateFinishFailed() == null ? "" : cardGeneralActivity.getDateFinishFailed());
            values.put("p_fallida", isFailed ? 1 : 0);
            values.put("p_razon_fallida", reasonFailed);
            values.put("p_detalle_fallida", detailFailed);

            String response = "";

            Log.d("FFF", values.toString());

            if(cardGeneralActivity.getIsCreated()){
                response = connectionHttp.postResponse(url, values);
            }else{
                //values.put("p_id", cardGeneralActivity.getStrId());
                response = connectionHttp.putResponse(url, values);
            }

            if(response != null) {
                Globals.boolSyncroDataGeneral = true;
                return true;
            }else{
                Globals.boolSyncroDataGeneral = false;
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d("FFF", "ERROR");
        }

        return false;
    }
}
