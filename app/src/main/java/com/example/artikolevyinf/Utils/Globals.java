package com.example.artikolevyinf.Utils;

import android.graphics.Bitmap;

import androidx.viewpager.widget.ViewPager;

import com.example.artikolevyinf.Fragments.Adapters.ViewPagerAdapter;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSupportActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Globals {
    /** Usuario que inicia sesión*/
    public static String strUserDocument = "1234556";

    /** Boleano para detener la barra de progreso de splash*/
    public static boolean splashActivityFinish = false;

    /** Boleeanos que indican la carga de los datos*/
    public static boolean getInfrastructureActivitiesFinish = false;


    /** Tipos de actividades*/
    public static String strAllActivities = "ALL";
    public static String strInfrastructureSurveyActivities = "INFRASTRUCTURE_SURVEY";
    public static String strReviewBTActivities = "REVIEW_BT";

    /** Actividada actual*/
    public static String strCurrentActivities = strAllActivities;

    /** Card seleccionada*/
    public static CardGeneralActivity cardGeneralActivitySelected;

    /** Razon actividad fallidad*/
    public static String strFailedReasonActivity;
    public static String strFailedDetailActivity;

    /** Horas min y seg cuando entra a la actividad y cuando la finaliza */
    public static Date dateStartActivity;

    /** --------- Estados de las actividades --------- */
    public static String strTodoState = "TODO";
    public static String strExecuteState = "EXECUTE";
    public static String strFailedStateIncomplete = "EXECUTE INCOMPLETE";
    public static String strFailedState = "FAILED";

    /** --------- Levantamiento de infrastructura ---------*/
    public static Integer intPageSelected = 0;
    public static ViewPager viewPager;
    public static ViewPagerAdapter viewPagerAdapter;
    public static ArrayList<CardGeneralActivity> dataInfrastructureSurveyActivitiesArray = new ArrayList<>();
    /** Booleano para identificar si lleno todos los campos de la actividad finalizada*/
    public static ArrayList<Boolean> boolArrayCompleteInfrastructurePages = new ArrayList<>();
    public static boolean boolIsActivityFinishComplete = true;
    /** Titulos*/
    public static String strTransformer = "TRANSFORMADOR DE DISTRIBUCIÓN";
    public static String strTransformationCenter = "CENTRO DE TRANSFORMACIÓN";
    public static String strLowVoltageSupport = "APOYO DE BAJA TENSIÓN";
    public static String strUndergroundBox = "CAJA SUBTERRÁNEA";
    public static String strLowVoltageSection = "TRAMOS BAJA TENSIÓN";

    /** Transformadores*/
    public static HashMap mapTransformerActivityData = new HashMap<>();
    public static DistributionTransformerActivity transformerActivityData;
    /** Imagenes y cards*/
    public static ArrayList<Bitmap> imageBeforeTransformerArray = new ArrayList<>();
    public static ArrayList<Bitmap> imageAfterTransformerArray = new ArrayList<>();
    public static ArrayList<Bitmap> imagePlateInstalledTransformerArray = new ArrayList<>();
    /** TRAMOS BAJA TENSIÓN */
    public static HashMap mapLowVoltageSectionActivityData = new HashMap<>();
    public static LowVoltageSectionActivity lowVoltageSectionActivity;
    public static String strStartSupportLowVoltageSection = "";
    public static String strFinalSupportLowVoltageSection = "";
    /** APOYO BAJA TENSIÓN */
    public static HashMap mapLowVoltageSupportActivityData = new HashMap<>();
    public static LowVoltageSupportActivity lowVoltageSupportActivity;

    /** --------- Revisiones BT ---------*/
    /** Imagenes y cards*/
    public static ArrayList<CardGeneralActivity> dataReviewsBTActivitiesArray = new ArrayList<>();

    /** Cards del mapa que guardaran los filtros*/
    public static ArrayList<CardGeneralActivity> dataMapActivitiesArray = new ArrayList<>();

    /** CheckBox para filtros del mapa*/
    /** Actividades */
    public static boolean cbFilterScrActivityMap = false;
    public static boolean cbFilterReviewsBtActivityMap = false;
    public static boolean cbFilterReviewsMtActivityMap = false;
    /** SubActividades */
    public static boolean cbFilterSuspensionMap = false;
    public static boolean cbFilterCutMap = false;
    public static boolean cbFilterReconnectionMap = false;
    /** Estado */
    public static boolean cbFilterToDoMap = false;
    public static boolean cbFilterExecutedMap = false;
    public static boolean cbFilterFailedMap = false;

    /** ------------------- Syncronizar información ------------------*/
    public static boolean boolSyncroDataGeneral = false;
    public static boolean boolSyncroDataTransformer = false;
    public static boolean boolSyncroDataLowVoltage = false;
    public static boolean boolSyncroDataScr = false;


}
