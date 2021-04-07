package com.example.artikolevyinf.Controller.InfrastructureSurvey;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.artikolevyinf.Controller.ConnectionHttp;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class CrudInfrastructure {

    public static boolean read(Activity activity, Context context){

        URL url = null;
        try {
            url = new URL("https://run.mocky.io/v3/c5e6b518-75ea-493e-853c-1610bb56a541");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ConnectionHttp conectionHttp = new ConnectionHttp();

        try {
            JSONObject values = new JSONObject();
            String response = conectionHttp.postResponse(url, values);

            if(response != null){
                JSONObject jsonObject = new JSONObject(response);
                String res = jsonObject.getString("respuesta");

                //Si el servicio encontro resultados para el usuario
                if(res.equals("OK")){

                    //Se crea un json con todos los datos y se recorren para ir crenado cada actividad
                    JSONArray data = jsonObject.getJSONArray("datos");

                    System.out.println(data);
                    for(int i = 0; i < data.length(); i++){
                        JSONObject obj = data.getJSONObject(i);
                        String activityTitle = obj.getString("tipoOrden");
                        String id, title, name, serial, order, address, failedReason = "";
                        double latitude, longitude, latitude2, longitude2;

                        /** Se crea una actividad de tipo Tramo de baja tensión (Tramo aereo) */
                        if(activityTitle.equals(Globals.strLowVoltageSection)){
                            System.out.println("RECIVE DATO CRUD");
                            id = obj.getString("numeroSerie");
                            name = "No. Serie " + obj.getString("numeroSerie");
                            latitude = obj.getDouble("latitud");
                            longitude = obj.getDouble("longitud");
                            latitude2 = obj.getDouble("latitud2");
                            longitude2 = obj.getDouble("longitud2");

                            //Se obtiene la distancia de cada actividad con respecto a la actual
                            HashMap map = Utils.getCurrentLocation(activity, context);
                            if(!map.isEmpty()){
                                Double latitudeStart = (Double) map.get("latitude");
                                Double longitudeStart = (Double) map.get("longitude");
                                double distance = Utils.getDistanceBetween(latitudeStart, longitudeStart, latitude, longitude);
                                double distance2 = Utils.getDistanceBetween(latitudeStart, longitudeStart, latitude2, longitude2);
                                double distanceProm = (distance + distance2)/2;

                                CardGeneralActivity transformerActivity = new CardGeneralActivity(id, activityTitle,"",name,"","","", latitude, longitude, Globals.strTodoState, Globals.strInfrastructureSurveyActivities, failedReason, distanceProm, latitude2, longitude2, false, null, null, null, null, false);

                                if(!(Utils.getGeneralActivityToSQLite(context, id))){
                                    Utils.createGeneralActivityToSQLite(context, transformerActivity);
                                }

                            }
                        /** Se crea una actividad de los otros tipos */
                        }else{

                            title  = obj.getString("ubicacionOrden");
                            id = obj.getString("numeroSerie");
                            name = "No. Serie " + obj.getString("numeroSerie");
                            serial = "Capacidad " + obj.getInt("numeroCapacidad");
                            order = "";
                            address = obj.getString("direccion");
                            latitude = obj.getDouble("latitud");
                            longitude = obj.getDouble("longitud");

                            //Se obtiene la distancia de cada actividad con respecto a la actual
                            HashMap map = Utils.getCurrentLocation(activity, context);
                            if(!map.isEmpty()){
                                System.out.println("RECIVE DATO CRUD");
                                Double latitudeStart = (Double) map.get("latitude");
                                Double longitudeStart = (Double) map.get("longitude");
                                double distance = Utils.getDistanceBetween(latitudeStart, longitudeStart, latitude, longitude);

                                CardGeneralActivity transformerActivity = new CardGeneralActivity(id, activityTitle, title, name, serial, order, address, latitude, longitude, Globals.strTodoState, Globals.strInfrastructureSurveyActivities, failedReason, distance, false, null, null, null, null, false);
                                System.out.println(transformerActivity);
                                System.out.println("HOLAAAAA");
                                if(!(Utils.getGeneralActivityToSQLite(context, id))){
                                    Utils.createGeneralActivityToSQLite(context, transformerActivity);
                                }
                            }
                        }
                    }

                    return true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;

    }

    public static boolean sendTransformer(DistributionTransformerActivity transformerActivity, Activity activity, int capacity){
        URL url = ConnectionHttp.getActivityInformationByKeyword("actividad_transformador");
        ConnectionHttp connectionHttp = new ConnectionHttp();

        try {
            /** Parametros que se enviaran con el post*/
            JSONObject values = new JSONObject();
            values.put("p_matricula", transformerActivity.getStrEnrollment());
            values.put("p_foto_antes_1", ConnectionHttp.putImageBucket("ANCT", transformerActivity.getStrIdTransformerActivity(),"1", transformerActivity.getImagesBeforeArray().get(0)));
            values.put("p_foto_antes_2", transformerActivity.getImagesBeforeArray().get(1) != null ? ConnectionHttp.putImageBucket("ANCT", transformerActivity.getStrIdTransformerActivity(),"2", transformerActivity.getImagesBeforeArray().get(1)) : "");
            values.put("p_foto_antes_3", transformerActivity.getImagesBeforeArray().get(2) != null ? ConnectionHttp.putImageBucket("ANCT", transformerActivity.getStrIdTransformerActivity(),"3", transformerActivity.getImagesBeforeArray().get(2)) : "");
            values.put("p_placa_tecnica",transformerActivity.getIntTechnicalPlate());
            values.put("p_foto_despues_1", transformerActivity.getImagesAfterArray().get(0) != null ? ConnectionHttp.putImageBucket("PT", transformerActivity.getStrIdTransformerActivity(),"1", transformerActivity.getImagesAfterArray().get(0)) : "");
            values.put("p_foto_despues_2", transformerActivity.getImagesAfterArray().get(1) != null ? ConnectionHttp.putImageBucket("PT", transformerActivity.getStrIdTransformerActivity(),"2", transformerActivity.getImagesAfterArray().get(1)) : "");
            values.put("p_foto_despues_3", transformerActivity.getImagesAfterArray().get(2) != null ? ConnectionHttp.putImageBucket("PT", transformerActivity.getStrIdTransformerActivity(),"3", transformerActivity.getImagesAfterArray().get(2)) : "");
            values.put("p_aceite_vegetal", transformerActivity.getStrVegetableOil());
            values.put("p_lat", transformerActivity.getDbTransformerLatitude());
            values.put("p_lon", transformerActivity.getDbTransformerLongitude());
            values.put("p_tipo_area", transformerActivity.getStrAreaType());
            values.put("p_tipo", transformerActivity.getStrType());
            values.put("p_fabricado_pcb", transformerActivity.getStrPcbEquip());
            values.put("p_faser", transformerActivity.getStrPhaseR());
            values.put("p_fases", transformerActivity.getStrPhaseS());
            values.put("p_faset", transformerActivity.getStrPhaseT());
            values.put("p_estado_equipo", transformerActivity.getStrEquipState());
            values.put("p_estado_transformador", transformerActivity.getStrTransformerState());
            values.put("p_fecha_fabricacion", transformerActivity.getDateFabricationDate());
            values.put("p_grupo", transformerActivity.getIntGroup());
            values.put("p_instalacion_origen", transformerActivity.getStrInstallationOrigin());
            values.put("p_marca", transformerActivity.getStrBrand());
            values.put("p_observaciones", transformerActivity.getStrObservation());
            values.put("p_pci_activo", transformerActivity.getStrActivePci());
            values.put("p_potencia_nominal", transformerActivity.getStrNominalPower());
            values.put("p_propiedad", transformerActivity.getStrProperty());
            values.put("p_secuencia_fases",transformerActivity.getStrPhaseSequence());
            values.put("p_subnormal", transformerActivity.getStrSubnormal());
            values.put("p_tension_primaria", transformerActivity.getStrPrimaryVoltage());
            values.put("p_tension_secundaria", transformerActivity.getStrSecondaryVoltage());
            values.put("p_tipo_conexion", transformerActivity.getStrConnectionType());
            values.put("p_tipo_transformador",transformerActivity.getStrTransformerType());
            values.put("p_tipo_red_secundaria", transformerActivity.getStrSecondatyNetType());
            values.put("p_uso_recursos", transformerActivity.getStrResourcesUse());
            values.put("p_foto_matricula_1", transformerActivity.getImageInstalledPlateArray().get(0) != null ? ConnectionHttp.putImageBucket("TR", transformerActivity.getStrIdTransformerActivity(),"1", transformerActivity.getImageInstalledPlateArray().get(0)) : "");
            values.put("p_foto_matricula_2", transformerActivity.getImageInstalledPlateArray().get(1) != null ? ConnectionHttp.putImageBucket("TR", transformerActivity.getStrIdTransformerActivity(),"2", transformerActivity.getImageInstalledPlateArray().get(1)) : "");
            values.put("p_foto_matricula_3", transformerActivity.getImageInstalledPlateArray().get(2) != null ? ConnectionHttp.putImageBucket("TR", transformerActivity.getStrIdTransformerActivity(),"3", transformerActivity.getImageInstalledPlateArray().get(2)) : "");
            values.put("p_id_actividad_general", transformerActivity.getStrIdTransformerActivity());
            values.put("p_capacidad", capacity);

            String response = connectionHttp.postResponse(url, values);

            Log.d("FFF", response);

            if(response != null) {
                Globals.boolSyncroDataTransformer = true;
                return true;
            }else{
                Globals.boolSyncroDataTransformer = false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean sendLowVoltageSection(LowVoltageSectionActivity lowVoltageSectionActivity, Activity activity){
        URL url = ConnectionHttp.getActivityInformationByKeyword("actividad-baja-tension");
        ConnectionHttp connectionHttp = new ConnectionHttp();

        try {
            /** Parametros que se enviaran con el post*/
            JSONObject values = new JSONObject();
            values.put("p_apoyo_inicia", lowVoltageSectionActivity.getStrStartSupport());
            values.put("p_apoyo_final", lowVoltageSectionActivity.getStrFinalSupport());
            values.put("p_calibre_material", lowVoltageSectionActivity.getStrMaterial());
            values.put("p_canalizacion", lowVoltageSectionActivity.getStrCanalization());
            values.put("p_cantidad_conductores", lowVoltageSectionActivity.getStrNConductors());
            values.put("p_disposicion_conductores", lowVoltageSectionActivity.getStrDispositionConductor());
            values.put("p_longitud_tramo", lowVoltageSectionActivity.getStrLength());
            values.put("p_observaciones", lowVoltageSectionActivity.getStrObservation());
            values.put("p_propiedad", lowVoltageSectionActivity.getStrProperty());
            values.put("p_tipo_area", lowVoltageSectionActivity.getStrAreaType());
            values.put("p_tipo_conductor_1", lowVoltageSectionActivity.getStrConductorType1());
            values.put("p_tipo_conductor_2", lowVoltageSectionActivity.getStrConductorType2());
            values.put("p_tipo_conductor_3", lowVoltageSectionActivity.getStrConductorType3());
            values.put("p_tipo_conductor_neutro", lowVoltageSectionActivity.getStrNeutralConductor());
            values.put("p_tipo_tramo", lowVoltageSectionActivity.getStrSectionType());
            values.put("p_uso_recursos", lowVoltageSectionActivity.getStrResourcesUse());
            values.put("p_id_actividad_general", lowVoltageSectionActivity.getStrIdActivity());

            String response = connectionHttp.postResponse(url, values);

            Log.d("FFF", response);

            if(response != null) {
                Globals.boolSyncroDataLowVoltage = true;
                return true;
            }else{
                Globals.boolSyncroDataLowVoltage = false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
