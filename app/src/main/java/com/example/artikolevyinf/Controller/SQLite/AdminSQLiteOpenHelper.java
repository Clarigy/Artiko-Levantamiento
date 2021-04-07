package com.example.artikolevyinf.Controller.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSupportActivity;
import com.example.artikolevyinf.Utils.Globals;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    /** Database Version */
    /** NECESARIO ACTUALIZAR ESTE NUMERO CADA VEZ QUE MUEVAN LA BD */
    private static final int DATABASE_VERSION = 21;

    /** Database Name */
    private static final String DATABASE_NAME = "artiko";

    /** Table Names */
    private static final String TABLE_USER = "usuario";
    private static final String TABLE_GENERAL_ACTIVITY = "actividad_general";
    private static final String TABLE_TRANSFORMER_ACTIVITY = "datos_actividad_transformador";
    private static final String TABLE_LOW_VOLTAGE_SECTION_ACTIVITY = "datos_actividad_tramo_baja_tension";
    private static final String TABLE_LOW_VOLTAGE_SUPPORT_ACTIVITY = "datos_actividad_apoyo_baja_tension";
    /** Common column names */
    private static final String KEY_ID = "id";

    /** ------ TABLES DATA ------ */

    /** USER Table - column names */
    private static final String KEY_USER_ID = "cedula";
    private static final String KEY_NAME = "nombre";
    private static final String KEY_POSITION = "cargo";
    private static final String KEY_CONTRACT = "contrato";
    private static final String KEY_EMAIL = "correo";
    private static final String KEY_NUMBER = "numero";
    private static final String KEY_COMPANY = "empresa";
    private static final String KEY_COMPANY_LOGO = "logoEmpresa";
    private static final String KEY_IMAGE = "imagen";
    private static final String KEY_SUPERVISOR = "supervisor";

    /** GENERAL_ACTIVITY Table - column names */
    private static final String KEY_GENERAL_TITLE_ACTIVITY = "tituloActividad";
    private static final String KEY_GENERAL_TITLE = "titulo";
    private static final String KEY_GENERAL_NAME = "nombre";
    private static final String KEY_GENERAL_SERIAL = "serial";
    private static final String KEY_GENERAL_ORDER = "orden";
    private static final String KEY_GENERAL_ADDRESS = "direccion";
    private static final String KEY_GENERAL_LATITUDE = "latitud";
    private static final String KEY_GENERAL_LONGITUDE = "longitud";
    private static final String KEY_GENERAL_STATE = "estado";
    private static final String KEY_GENERAL_TYPE = "tipo";
    private static final String KEY_GENERAL_FAILED_REASON = "razonFallida";
    private static final String KEY_GENERAL_DISTANCE = "distancia";
    private static final String KEY_GENERAL_LATITUDE2 = "latitud2";
    private static final String KEY_GENERAL_LONGITUDE2 = "longitud2";
    private static final String KEY_GENERAL_START_EXECUTED = "fechaInicioEjecutada";
    private static final String KEY_GENERAL_FINISH_EXECUTED = "fechaFinEjecutada";
    private static final String KEY_GENERAL_START_FAILED = "fechaInicioFallida";
    private static final String KEY_GENERAL_FINISH_FAILED = "fechaFinFallida";
    private static final String KEY_GENERAL_UPLOAD = "subido";
    private static final String KEY_GENERAL_CREATED = "creado";
    private static final String KEY_GENERAL_USER_DOCUMENT = "cedula";

    /** TRANSFORMER_ACTIVITY Table - column names */
    private static final String KEY_TRANSFORMER_ENROLLMENT = "matricula";
    private static final String KEY_TRANSFORMER_IMAGE_BEFORE1 = "fotoAntes1";
    private static final String KEY_TRANSFORMER_IMAGE_BEFORE2 = "fotoAntes2";
    private static final String KEY_TRANSFORMER_IMAGE_BEFORE3 = "fotoAntes3";
    private static final String KEY_TRANSFORMER_PLATE = "placaTecnica";
    private static final String KEY_TRANSFORMER_IMAGE_AFTER1 = "fotoDespues1";
    private static final String KEY_TRANSFORMER_IMAGE_AFTER2 = "fotoDespues2";
    private static final String KEY_TRANSFORMER_IMAGE_AFTER3 = "fotoDespues3";
    private static final String KEY_TRANSFORMER_OIL = "aceiteVegetal";
    private static final String KEY_TRANSFORMER_LATITUDE = "latitud";
    private static final String KEY_TRANSFORMER_LONGITUDE = "longitud";
    private static final String KEY_TRANSFORMER_AREA_TYPE = "tipoArea";
    private static final String KEY_TRANSFORMER_TYPE = "tipo";
    private static final String KEY_TRANSFORMER_PCB = "fabricadoPcb";
    private static final String KEY_TRANSFORMER_PHASE_R = "faseR";
    private static final String KEY_TRANSFORMER_PHASE_S = "faseS";
    private static final String KEY_TRANSFORMER_PHASE_T = "faseT";
    private static final String KEY_TRANSFORMER_EQUIP_STATE = "estadoEquipo";
    private static final String KEY_TRANSFORMER_STATE = "estadoTransformador";
    private static final String KEY_TRANSFORMER_FABRICATION_DATE = "fechaFabricacion";
    private static final String KEY_TRANSFORMER_GROUP = "grupo";
    private static final String KEY_TRANSFORMER_INSTALLATION = "instalacionOrigen";
    private static final String KEY_TRANSFORMER_BRAND = "marca";
    private static final String KEY_TRANSFORMER_OBSERVATION = "observaciones";
    private static final String KEY_TRANSFORMER_PCI = "pciActivo";
    private static final String KEY_TRANSFORMER_NOMINAL_POWER = "potenciaNominal";
    private static final String KEY_TRANSFORMER_PROPERTY = "propiedad";
    private static final String KEY_TRANSFORMER_SEQUENCE_PHASE = "secuenciaFases";
    private static final String KEY_TRANSFORMER_SUBNORMAL = "subnormal";
    private static final String KEY_TRANSFORMER_PRIMARY_VOLTAGE = "tensionPrimaria";
    private static final String KEY_TRANSFORMER_SECONDARY_VOLTAGE = "tensionSecundaria";
    private static final String KEY_TRANSFORMER_CONNECTION = "tipoConexion";
    private static final String KEY_TRANSFORMER_TRANSFORMER_TYPE = "tipoTransformador";
    private static final String KEY_TRANSFORMER_SECONDARY_NET = "tipoRedSecundaria";
    private static final String KEY_TRANSFORMER_USE_RESOURCES = "usoRecursos";
    private static final String KEY_TRANSFORMER_IMAGE_PLATE1 = "fotoMatricula1";
    private static final String KEY_TRANSFORMER_IMAGE_PLATE2 = "fotoMatricula2";
    private static final String KEY_TRANSFORMER_IMAGE_PLATE3 = "fotoMatricula3";
    private static final String KEY_TRANSFORMER_FAILED = "fallida";
    private static final String KEY_TRANSFORMER_FAIL_REASON = "razonFallida";
    private static final String KEY_TRANSFORMER_FAIL_DETAIL = "detalleFallida";

    /** LOW_VOLTAGE_SECTION_ACTIVITY Table - column names */
    private static final String KEY_LOW_VOLTAGE_SECTION_START_SUPPORT = "apoyoInicial";
    private static final String KEY_LOW_VOLTAGE_SECTION_FINAL_SUPPORT = "apoyoFinal";
    private static final String KEY_LOW_VOLTAGE_SECTION_MATERIAL = "calibreMaterial";
    private static final String KEY_LOW_VOLTAGE_SECTION_CANALIZATION = "canalizacionAsociada";
    private static final String KEY_LOW_VOLTAGE_SECTION_N_CONDUCTOR = "cantidadConductores";
    private static final String KEY_LOW_VOLTAGE_SECTION_DISPOSITION_CONDUCTOR = "disposicionConductor";
    private static final String KEY_LOW_VOLTAGE_SECTION_LENGTH = "longitud";
    private static final String KEY_LOW_VOLTAGE_SECTION_OBSERVATION = "observaciones";
    private static final String KEY_LOW_VOLTAGE_SECTION_PROPERTY = "propiedad";
    private static final String KEY_LOW_VOLTAGE_SECTION_AREA_TYPE = "tipoArea";
    private static final String KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE1 = "tipoConductor1";
    private static final String KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE2 = "tipoConductor2";
    private static final String KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE3 = "tipoConductor3";
    private static final String KEY_LOW_VOLTAGE_SECTION_NEUTRAL_CONDUCTOR = "tipoConductorNeutro";
    private static final String KEY_LOW_VOLTAGE_SECTION_SECTION_TYPE = "tipoTramo";
    private static final String KEY_LOW_VOLTAGE_SECTION_USE_RESOURCES = "usoRecursos";
    private static final String KEY_LOW_VOLTAGE_SECTION_FAILED = "fallida";
    private static final String KEY_LOW_VOLTAGE_SECTION_FAIL_REASON = "razonFallida";
    private static final String KEY_LOW_VOLTAGE_SECTION_FAIL_DETAIL = "detalleFallida";

    /** LOW_VOLTAGE_SECTION_ACTIVITY Table - column names */
    private static final String KEY_LOW_VOLTAGE_SUPPORT_HEIGHT = "alturaApoyo";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_LOAD = "cargaRotura";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_FOUNDATION = "cimentacion";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_CIRCUITS = "circuitosApoyo";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_SHARED = "compartida";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_STATE_SECTION = "estadoTramo";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE = "estructura";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_BT = "estructuraBT";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_MT = "estructuraMT";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_LATITUDE = "latitud";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_LONGITUDE = "longitud";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_MATERIAL = "material";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_ENROLL = "matricula";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_OBSERVATIONS = "observaciones";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_PASS = "paso";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_PORTICO = "portico";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_PROPERTY = "property";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_PROVINCE = "province";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_GROUNDING = "puestaTierra";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_DETAINED = "retenida";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_INSULATOR_TYPE = "tipoAislador";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_TYPE = "tipoApoyo";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_AREA_TYPE = "tipoArea";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_NET_TYPE = "tipoRed";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_USE_RESOURCES = "usoRecursos";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_UUCC = "uucc";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_FAILED = "fallida";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_FAIL_REASON = "razonFallida";
    private static final String KEY_LOW_VOLTAGE_SUPPORT_FAIL_DETAIL = "detalleFallida";

    // Table Create Statements
    /** USER table create statement */
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_USER_ID + " STRING PRIMARY KEY NOT NULL," +
            KEY_NAME + " TEXT NOT NULL," + KEY_POSITION + " TEXT NOT NULL," + KEY_CONTRACT + " TEXT NOT NULL," +
            KEY_EMAIL + " TEXT NOT NULL," + KEY_NUMBER + " INTEGER NOT NULL," + KEY_COMPANY + " TEXT NOT NULL," +
            KEY_COMPANY_LOGO + " BLOB NOT NULL," + KEY_IMAGE + " BLOB NOT NULL," + KEY_SUPERVISOR + " TEXT NOT NULL" + ")";

    /** GENERAL_ACTIVITY create statement */
    private static final String CREATE_TABLE_GENERAL_ACTIVITY = "CREATE TABLE "
            + TABLE_GENERAL_ACTIVITY + "(" + KEY_ID + " STRING PRIMARY KEY NOT NULL," + KEY_GENERAL_TITLE_ACTIVITY + " TEXT NOT NULL," +
            KEY_GENERAL_TITLE + " TEXT NOT NULL," + KEY_GENERAL_NAME + " TEXT NOT NULL," + KEY_GENERAL_SERIAL + " TEXT NOT NULL," +
            KEY_GENERAL_ORDER + " TEXT NOT NULL," + KEY_GENERAL_ADDRESS + " TEXT NOT NULL," + KEY_GENERAL_LATITUDE + " DOUBLE NOT NULL," +
            KEY_GENERAL_LONGITUDE + " DOUBLE NOT NULL," + KEY_GENERAL_STATE + " TEXT NOT NULL," + KEY_GENERAL_TYPE + " TEXT NOT NULL,"  +
            KEY_GENERAL_FAILED_REASON + " TEXT NOT NULL," + KEY_GENERAL_DISTANCE + " DOUBLE NOT NULL," + KEY_GENERAL_LATITUDE2 + " DOUBLE," +
            KEY_GENERAL_LONGITUDE2 + " DOUBLE," + KEY_GENERAL_UPLOAD + " BOOLEAN NOT NULL," + KEY_GENERAL_CREATED + " BOOLEAN NOT NULL," + KEY_GENERAL_START_EXECUTED +
            " DATE," + KEY_GENERAL_FINISH_EXECUTED + " DATE," + KEY_GENERAL_START_FAILED + " DATE," + KEY_GENERAL_FINISH_FAILED + " DATE," +
            KEY_GENERAL_USER_DOCUMENT + " INTEGER NOT NULL," + " FOREIGN KEY (" + KEY_GENERAL_USER_DOCUMENT + ") REFERENCES " + TABLE_USER + " (" + KEY_USER_ID + ") " + ")";

    /** TRANSFORMER_ACTIVITY table create statement */
    private static final String CREATE_TABLE_TRANSFORMER_ACTIVITY = "CREATE TABLE "
            + TABLE_TRANSFORMER_ACTIVITY + "(" + KEY_ID + " STRING PRIMARY KEY NOT NULL," + KEY_TRANSFORMER_ENROLLMENT + " TEXT NOT NULL," +
            KEY_TRANSFORMER_IMAGE_BEFORE1 + " BLOB NOT NULL," + KEY_TRANSFORMER_IMAGE_BEFORE2 + " BLOB," + KEY_TRANSFORMER_IMAGE_BEFORE3 + " BLOB," +
            KEY_TRANSFORMER_PLATE + " INTEGER NOT NULL," + KEY_TRANSFORMER_IMAGE_AFTER1 + " BLOB," + KEY_TRANSFORMER_IMAGE_AFTER2 + " BLOB," +
            KEY_TRANSFORMER_IMAGE_AFTER3 + " BLOB," + KEY_TRANSFORMER_OIL + " TEXT NOT NULL," + KEY_TRANSFORMER_LATITUDE + " DOUBLE NOT NULL," +
            KEY_TRANSFORMER_LONGITUDE + " DOUBLE NOT NULL," + KEY_TRANSFORMER_AREA_TYPE + " TEXT NOT NULL," + KEY_TRANSFORMER_TYPE + " TEXT NOT NULL," +
            KEY_TRANSFORMER_PCB + " TEXT NOT NULL," + KEY_TRANSFORMER_PHASE_R + " TEXT NOT NULL," + KEY_TRANSFORMER_PHASE_S + " TEXT NOT NULL," +
            KEY_TRANSFORMER_PHASE_T + " TEXT NOT NULL," + KEY_TRANSFORMER_EQUIP_STATE + " TEXT NOT NULL," +
            KEY_TRANSFORMER_STATE + " TEXT NOT NULL," + KEY_TRANSFORMER_FABRICATION_DATE + " DATE," + KEY_TRANSFORMER_GROUP + " INTEGER," +
            KEY_TRANSFORMER_INSTALLATION + " TEXT," + KEY_TRANSFORMER_BRAND + " TEXT," + KEY_TRANSFORMER_OBSERVATION + " TEXT," +
            KEY_TRANSFORMER_PCI + " TEXT," + KEY_TRANSFORMER_NOMINAL_POWER + " TEXT," + KEY_TRANSFORMER_PROPERTY + " TEXT," +
            KEY_TRANSFORMER_SEQUENCE_PHASE + " TEXT," + KEY_TRANSFORMER_SUBNORMAL + " TEXT," + KEY_TRANSFORMER_PRIMARY_VOLTAGE + " TEXT," +
            KEY_TRANSFORMER_SECONDARY_VOLTAGE + " TEXT," + KEY_TRANSFORMER_CONNECTION + " TEXT," + KEY_TRANSFORMER_TRANSFORMER_TYPE + " TEXT," +
            KEY_TRANSFORMER_SECONDARY_NET + " TEXT," + KEY_TRANSFORMER_USE_RESOURCES + " TEXT," + KEY_TRANSFORMER_IMAGE_PLATE1 + " BLOB," +
            KEY_TRANSFORMER_IMAGE_PLATE2 + " BLOB," + KEY_TRANSFORMER_IMAGE_PLATE3 + " BLOB," + KEY_TRANSFORMER_FAILED + " BOOLEAN NOT NULL," +
            KEY_TRANSFORMER_FAIL_REASON + " TEXT NOT NULL," + KEY_TRANSFORMER_FAIL_DETAIL + " TEXT NOT NULL," + " FOREIGN KEY (" + KEY_ID + ") REFERENCES " + TABLE_GENERAL_ACTIVITY + " (" + KEY_ID + ") " + ")";

    /** LOW_VOLTAGE_SECTION_ACTIVITY Table create statement */
    private static final String CREATE_TABLE_LOW_VOLTAGE_SECTION = "CREATE TABLE " + TABLE_LOW_VOLTAGE_SECTION_ACTIVITY +
            "(" + KEY_ID + " STRING PRIMARY KEY NOT NULL," + KEY_LOW_VOLTAGE_SECTION_START_SUPPORT + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SECTION_FINAL_SUPPORT + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SECTION_MATERIAL + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SECTION_CANALIZATION + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SECTION_N_CONDUCTOR + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SECTION_DISPOSITION_CONDUCTOR + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SECTION_LENGTH + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SECTION_OBSERVATION + " TEXT," + KEY_LOW_VOLTAGE_SECTION_PROPERTY + " TEXT NOT NULL,"  +
            KEY_LOW_VOLTAGE_SECTION_AREA_TYPE + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE1 + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE2 + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE3 + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SECTION_NEUTRAL_CONDUCTOR + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SECTION_SECTION_TYPE + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SECTION_USE_RESOURCES + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SECTION_FAILED + " BOOLEAN NOT NULL," +
            KEY_LOW_VOLTAGE_SECTION_FAIL_REASON + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SECTION_FAIL_DETAIL + " TEXT NOT NULL," +
            " FOREIGN KEY (" + KEY_ID + ") REFERENCES " + TABLE_GENERAL_ACTIVITY + " (" + KEY_ID + ") " + ")";

    /** LOW_VOLTAGE_SUPPORT_ACTIVITY Table create statement */
    private static final String CREATE_TABLE_LOW_VOLTAGE_SUPPORT = "CREATE TABLE " + TABLE_LOW_VOLTAGE_SUPPORT_ACTIVITY +
            "(" + KEY_ID + " STRING PRIMARY KEY NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_HEIGHT + " INTEGER NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_LOAD + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_FOUNDATION + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_CIRCUITS + " INTEGER NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_SHARED + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_STATE_SECTION + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_BT + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_MT + " TEXT NOT NULL,"  +
            KEY_LOW_VOLTAGE_SUPPORT_LATITUDE + " DOUBLE NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_LONGITUDE + " DOUBLE NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_MATERIAL + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_ENROLL + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_OBSERVATIONS + " TEXT," +KEY_LOW_VOLTAGE_SUPPORT_DETAINED + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_INSULATOR_TYPE + " TEXT," +KEY_LOW_VOLTAGE_SUPPORT_TYPE + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_AREA_TYPE + " TEXT," +KEY_LOW_VOLTAGE_SUPPORT_NET_TYPE + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_PASS + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_PORTICO + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_PROPERTY + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_PROVINCE + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_GROUNDING + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_USE_RESOURCES + " TEXT NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_UUCC + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_FAILED + " BOOLEAN NOT NULL," +
            KEY_LOW_VOLTAGE_SUPPORT_FAIL_REASON + " TEXT NOT NULL," + KEY_LOW_VOLTAGE_SUPPORT_FAIL_DETAIL + " TEXT NOT NULL," +
            " FOREIGN KEY (" + KEY_ID + ") REFERENCES " + TABLE_GENERAL_ACTIVITY + " (" + KEY_ID + ") " + ")";


    public AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /** Se crean las tablas presentes en la BD */
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_GENERAL_ACTIVITY);
        db.execSQL(CREATE_TABLE_TRANSFORMER_ACTIVITY);
        db.execSQL(CREATE_TABLE_LOW_VOLTAGE_SECTION);
        db.execSQL(CREATE_TABLE_LOW_VOLTAGE_SUPPORT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        /** Se actualiza la BD en cuanto cambie la Versión de la BD, Arriba. */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENERAL_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSFORMER_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOW_VOLTAGE_SECTION_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOW_VOLTAGE_SUPPORT_ACTIVITY);


        /** Se crean nuevamente las tablas */
        onCreate(db);
    }
    /** Se crea una actividad general*/
    public void createGeneralActivitySQLite(CardGeneralActivity generalActivity){
        SQLiteDatabase db = this.getWritableDatabase();

        /** Se establece el formato de la fecha */
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        ContentValues values = new ContentValues();
        values.put(KEY_ID, generalActivity.getStrId());
        values.put(KEY_GENERAL_TITLE_ACTIVITY, generalActivity.getStrActivityTitle());
        values.put(KEY_GENERAL_TITLE, generalActivity.getStrTitle());
        values.put(KEY_GENERAL_NAME, generalActivity.getStrName());
        values.put(KEY_GENERAL_SERIAL, generalActivity.getStrSerial());
        values.put(KEY_GENERAL_ORDER, generalActivity.getStrOrder());
        values.put(KEY_GENERAL_ADDRESS, generalActivity.getStrAddress());
        values.put(KEY_GENERAL_LATITUDE, generalActivity.getDbLatitude());
        values.put(KEY_GENERAL_LONGITUDE, generalActivity.getDbLongitude());
        values.put(KEY_GENERAL_STATE, generalActivity.getStrState());
        values.put(KEY_GENERAL_TYPE, generalActivity.getStrType());
        values.put(KEY_GENERAL_FAILED_REASON, generalActivity.getStrFailedReason());
        values.put(KEY_GENERAL_DISTANCE, generalActivity.getDbDistance());
        values.put(KEY_GENERAL_LATITUDE2, generalActivity.getDbLatitude2());
        values.put(KEY_GENERAL_LONGITUDE2, generalActivity.getDbLongitude2());
        values.put(KEY_GENERAL_UPLOAD, generalActivity.getIsUploadAPI());
        values.put(KEY_GENERAL_CREATED, generalActivity.getIsCreated());
        values.put(KEY_GENERAL_START_EXECUTED, generalActivity.getDateStartExecuted() == null ? null : dateFormat.format(generalActivity.getDateStartExecuted()));
        values.put(KEY_GENERAL_FINISH_EXECUTED, generalActivity.getDateFinishExecuted() == null ? null : dateFormat.format(generalActivity.getDateFinishExecuted()));
        values.put(KEY_GENERAL_START_FAILED, generalActivity.getDateStartFailed() == null ? null : dateFormat.format(generalActivity.getDateStartFailed()));
        values.put(KEY_GENERAL_FINISH_FAILED, generalActivity.getDateFinishFailed() == null ? null : dateFormat.format(generalActivity.getDateFinishFailed()));
        values.put(KEY_GENERAL_USER_DOCUMENT, Globals.strUserDocument);

        /** Se inserta la fila a la tabla */
        db.insert(TABLE_GENERAL_ACTIVITY, null, values);
    }

    /** Se trae una actividad general*/
    public CardGeneralActivity getGeneralActivitySQLite(String generalId){
        SQLiteDatabase db = this.getReadableDatabase();
        /** Se realiza la consulta a la BD de la actividad con el ID */
        String selectQuery = "SELECT  * FROM " + TABLE_GENERAL_ACTIVITY + " WHERE " + KEY_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] { generalId });

        if (cursor.moveToFirst()) {
            if (cursor != null)
                cursor.moveToFirst();

            /** Se trae las fecha inicio y fin, si no es null, se convierte de String -> Date */
            Date dateStartExecuted = new Date();
            Date dateFinishExecuted = new Date();
            Date dateStartFailed = new Date();
            Date dateFinishFailed = new Date();
            try {
                if(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_START_EXECUTED))) != null){
                    dateStartExecuted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_START_EXECUTED))));
                }else{
                    dateStartExecuted = null;
                }

                if(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FINISH_EXECUTED))) != null){
                    dateFinishExecuted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FINISH_EXECUTED))));
                }else{
                    dateFinishExecuted = null;
                }

                if(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_START_FAILED))) != null){
                    dateStartFailed = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_START_FAILED))));
                }else{
                    dateStartFailed = null;
                }

                if(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FINISH_FAILED))) != null){
                    dateFinishFailed = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FINISH_FAILED))));
                }else{
                    dateFinishFailed = null;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            /** Se crea un objeto de la actividaed con toda la información leida de la BD */
            CardGeneralActivity generalActivity = new CardGeneralActivity(
                    cursor.getString((cursor.getColumnIndex(KEY_ID))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_TITLE_ACTIVITY))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_TITLE))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_NAME))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_SERIAL))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_ORDER))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_ADDRESS))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_LATITUDE))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_LONGITUDE))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_STATE))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FAILED_REASON))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_DISTANCE))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_LATITUDE2))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_LONGITUDE2))),
                    cursor.getInt((cursor.getColumnIndex(KEY_GENERAL_UPLOAD))) > 0,
                    dateStartExecuted,
                    dateFinishExecuted,
                    dateStartFailed,
                    dateFinishFailed,
                    cursor.getInt((cursor.getColumnIndex(KEY_GENERAL_CREATED))) > 0
            );
            return generalActivity;
        }
        return null;
    }

    /** Se traen una actividad general*/
    public ArrayList<CardGeneralActivity> getAllGeneralActivitySQLite(){
        SQLiteDatabase db = this.getReadableDatabase();
        /** Se selecciona toda la tabla*/
        String selectQuery = "SELECT  * FROM " + TABLE_GENERAL_ACTIVITY;
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<CardGeneralActivity> generalActivityArray = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                /** Se trae las fecha inicio y fin, si no es null, se convierte de String -> Date */
                Date dateStartExecuted = new Date();
                Date dateFinishExecuted = new Date();
                Date dateStartFailed = new Date();
                Date dateFinishFailed = new Date();
                try {
                    if(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_START_EXECUTED))) != null){
                        dateStartExecuted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_START_EXECUTED))));
                    }else{
                        dateStartExecuted = null;
                    }

                    if(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FINISH_EXECUTED))) != null){
                        dateFinishExecuted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FINISH_EXECUTED))));
                    }else{
                        dateFinishExecuted = null;
                    }

                    if(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_START_FAILED))) != null){
                        dateStartFailed = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_START_FAILED))));
                    }else{
                        dateStartFailed = null;
                    }

                    if(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FINISH_FAILED))) != null){
                        dateFinishFailed = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FINISH_FAILED))));
                    }else{
                        dateFinishFailed = null;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /** Se crea un objeto de la actividaed con toda la información leida de la BD */
                CardGeneralActivity generalActivity = new CardGeneralActivity(
                        cursor.getString((cursor.getColumnIndex(KEY_ID))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_TITLE_ACTIVITY))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_TITLE))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_NAME))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_SERIAL))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_ORDER))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_ADDRESS))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_LATITUDE))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_LONGITUDE))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_STATE))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_GENERAL_FAILED_REASON))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_DISTANCE))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_LATITUDE2))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_GENERAL_LONGITUDE2))),
                        cursor.getInt((cursor.getColumnIndex(KEY_GENERAL_UPLOAD))) > 0,
                        dateStartExecuted,
                        dateFinishExecuted,
                        dateStartFailed,
                        dateFinishFailed,
                        cursor.getInt((cursor.getColumnIndex(KEY_GENERAL_CREATED))) > 0
                );
                generalActivityArray.add(generalActivity);

            }while (cursor.moveToNext());
        }
        return generalActivityArray;
    }

    /** --------- Se actualiza una actividad general ---------*/
    public void updateGeneralActivitySQLite(CardGeneralActivity generalActivity) {
        SQLiteDatabase db = this.getWritableDatabase();

        /** Se establece el formato de la fecha */
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        ContentValues values = new ContentValues();
        values.put(KEY_ID, generalActivity.getStrId());
        values.put(KEY_GENERAL_TITLE_ACTIVITY, generalActivity.getStrActivityTitle());
        values.put(KEY_GENERAL_TITLE, generalActivity.getStrTitle());
        values.put(KEY_GENERAL_NAME, generalActivity.getStrName());
        values.put(KEY_GENERAL_SERIAL, generalActivity.getStrSerial());
        values.put(KEY_GENERAL_ORDER, generalActivity.getStrOrder());
        values.put(KEY_GENERAL_ADDRESS, generalActivity.getStrAddress());
        values.put(KEY_GENERAL_LATITUDE, generalActivity.getDbLatitude());
        values.put(KEY_GENERAL_LONGITUDE, generalActivity.getDbLongitude());
        values.put(KEY_GENERAL_STATE, generalActivity.getStrState());
        values.put(KEY_GENERAL_TYPE, generalActivity.getStrType());
        values.put(KEY_GENERAL_FAILED_REASON, generalActivity.getStrFailedReason());
        values.put(KEY_GENERAL_DISTANCE, generalActivity.getDbDistance());
        values.put(KEY_GENERAL_LATITUDE2, generalActivity.getDbLatitude2());
        values.put(KEY_GENERAL_LONGITUDE2, generalActivity.getDbLongitude2());
        values.put(KEY_GENERAL_UPLOAD, generalActivity.getIsUploadAPI());
        values.put(KEY_GENERAL_CREATED, generalActivity.getIsCreated());
        values.put(KEY_GENERAL_START_EXECUTED, generalActivity.getDateStartExecuted() == null ? null : dateFormat.format(generalActivity.getDateStartExecuted()));
        values.put(KEY_GENERAL_FINISH_EXECUTED, generalActivity.getDateFinishExecuted() == null ? null : dateFormat.format(generalActivity.getDateFinishExecuted()));
        values.put(KEY_GENERAL_START_FAILED, generalActivity.getDateStartFailed() == null ? null : dateFormat.format(generalActivity.getDateStartFailed()));
        values.put(KEY_GENERAL_FINISH_FAILED, generalActivity.getDateFinishFailed() == null ? null : dateFormat.format(generalActivity.getDateFinishFailed()));
        values.put(KEY_GENERAL_USER_DOCUMENT, Globals.strUserDocument);

        db.update(TABLE_GENERAL_ACTIVITY, values,KEY_ID + " = ?",
                new String[] { generalActivity.getStrId() });
    }

    /** --------- Se elimina una actividad general ---------*/
    public void deleteToGeneralActivitySQLite(String generalId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GENERAL_ACTIVITY, KEY_ID + " = ?",
                new String[] { generalId });
    }

    /** --------- Se crea una actividad de Transformador --------- */
    public void createTransformerActivitySQLite(DistributionTransformerActivity transformerActivity) {
        SQLiteDatabase db = this.getWritableDatabase();

        /** Array de bytes vacio para poner una img vacia cuando termina la tarea fallida sin llegar a poner una img obligatoria */
        byte[] emptyArray = new byte[0];

        /** Se inicializa los arreglos de imagenes tipo Byte[] en null */
        ArrayList<byte[]> arrayImagesBefore = new ArrayList<byte[]>() {{
            add(null);
            add(null);
            add(null);
        }};
        ArrayList<byte[]> arrayImagesAfter = new ArrayList<byte[]>() {{
            add(null);
            add(null);
            add(null);
        }};
        ArrayList<byte[]> arrayImagesPlate = new ArrayList<byte[]>() {{
            add(null);
            add(null);
            add(null);
        }};

        /** Se llenan las posiciones que tienen imagen y se pasa de Bitmap a Byte para subirlo a la BD */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    if (i == 0) {
                        transformerActivity.getImagesBeforeArray().get(j).compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        arrayImagesBefore.set(j,bos.toByteArray());
                    } else if (i == 1) {
                        transformerActivity.getImagesAfterArray().get(j).compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        arrayImagesAfter.set(j,bos.toByteArray());

                    } else if (i == 2) {
                        transformerActivity.getImageInstalledPlateArray().get(j).compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        arrayImagesPlate.set(j,bos.toByteArray());
                    }
                } catch (Exception e) {
                }
            }
        }

        /** Se establece el formato de la fecha */
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        /** Se crea un contentvalue con cada uno de los valores del objeto de la actividad */
        /** Se validan que los campos obligatorios no sean Null ya que en el diseño de la bd se puso Not Null */
        /** Los que pueden ser null se envian vacios, ya que al recibirlos y crear el objeto, crean Strings "Null" */
        ContentValues values = new ContentValues();
        values.put(KEY_ID, transformerActivity.getStrIdTransformerActivity());
        values.put(KEY_TRANSFORMER_ENROLLMENT, transformerActivity.getStrEnrollment() == null ? "" : transformerActivity.getStrEnrollment());
        values.put(KEY_TRANSFORMER_IMAGE_BEFORE1, arrayImagesBefore.get(0) == null ? emptyArray : arrayImagesBefore.get(0));
        values.put(KEY_TRANSFORMER_IMAGE_BEFORE2, arrayImagesBefore.get(1));
        values.put(KEY_TRANSFORMER_IMAGE_BEFORE3, arrayImagesBefore.get(2));
        values.put(KEY_TRANSFORMER_PLATE, transformerActivity.getIntTechnicalPlate() == null ? 0 : transformerActivity.getIntTechnicalPlate());
        values.put(KEY_TRANSFORMER_IMAGE_AFTER1, arrayImagesAfter.get(0));
        values.put(KEY_TRANSFORMER_IMAGE_AFTER2, arrayImagesAfter.get(1));
        values.put(KEY_TRANSFORMER_IMAGE_AFTER3, arrayImagesAfter.get(2));
        values.put(KEY_TRANSFORMER_OIL, transformerActivity.getStrVegetableOil() == null ? "" : transformerActivity.getStrVegetableOil());
        values.put(KEY_TRANSFORMER_LATITUDE, transformerActivity.getDbTransformerLatitude() == null ? 0 : transformerActivity.getDbTransformerLatitude());
        values.put(KEY_TRANSFORMER_LONGITUDE, transformerActivity.getDbTransformerLongitude() == null ? 0 : transformerActivity.getDbTransformerLongitude());
        values.put(KEY_TRANSFORMER_AREA_TYPE, transformerActivity.getStrAreaType() == null ? "" : transformerActivity.getStrAreaType());
        values.put(KEY_TRANSFORMER_TYPE, transformerActivity.getStrType() == null ? "" : transformerActivity.getStrType());
        values.put(KEY_TRANSFORMER_PCB, transformerActivity.getStrPcbEquip() == null ? "" : transformerActivity.getStrPcbEquip());
        values.put(KEY_TRANSFORMER_PHASE_R, transformerActivity.getStrPhaseR() == null ? "" : transformerActivity.getStrPhaseR());
        values.put(KEY_TRANSFORMER_PHASE_S, transformerActivity.getStrPhaseS() == null ? "" : transformerActivity.getStrPhaseS());
        values.put(KEY_TRANSFORMER_PHASE_T, transformerActivity.getStrPhaseT() == null ? "" : transformerActivity.getStrPhaseT());
        values.put(KEY_TRANSFORMER_EQUIP_STATE, transformerActivity.getStrEquipState() == null ? "" : transformerActivity.getStrEquipState());
        values.put(KEY_TRANSFORMER_STATE, transformerActivity.getStrTransformerState() == null ? "" : transformerActivity.getStrTransformerState());
        values.put(KEY_TRANSFORMER_FABRICATION_DATE, transformerActivity.getDateFabricationDate() == null ? null : dateFormat.format(transformerActivity.getDateFabricationDate()));
        values.put(KEY_TRANSFORMER_GROUP, transformerActivity.getIntGroup());
        values.put(KEY_TRANSFORMER_INSTALLATION, transformerActivity.getStrInstallationOrigin() == null ? "" : transformerActivity.getStrInstallationOrigin());
        values.put(KEY_TRANSFORMER_BRAND, transformerActivity.getStrBrand() == null ? "" : transformerActivity.getStrBrand());
        values.put(KEY_TRANSFORMER_OBSERVATION, transformerActivity.getStrObservation() == null ? "" : transformerActivity.getStrObservation());
        values.put(KEY_TRANSFORMER_PCI, transformerActivity.getStrActivePci() == null ? "" : transformerActivity.getStrActivePci());
        values.put(KEY_TRANSFORMER_NOMINAL_POWER, transformerActivity.getStrNominalPower() == null ? "" : transformerActivity.getStrNominalPower());
        values.put(KEY_TRANSFORMER_PROPERTY, transformerActivity.getStrProperty() == null ? "" : transformerActivity.getStrProperty());
        values.put(KEY_TRANSFORMER_SEQUENCE_PHASE, transformerActivity.getStrPhaseSequence() == null ? "" : transformerActivity.getStrPhaseSequence());
        values.put(KEY_TRANSFORMER_SUBNORMAL, transformerActivity.getStrSubnormal() == null ? "" : transformerActivity.getStrSubnormal());
        values.put(KEY_TRANSFORMER_PRIMARY_VOLTAGE, transformerActivity.getStrPrimaryVoltage() == null ? "" : transformerActivity.getStrPrimaryVoltage());
        values.put(KEY_TRANSFORMER_SECONDARY_VOLTAGE, transformerActivity.getStrSecondaryVoltage() == null ? "" : transformerActivity.getStrSecondaryVoltage());
        values.put(KEY_TRANSFORMER_CONNECTION, transformerActivity.getStrConnectionType() == null ? "" : transformerActivity.getStrConnectionType());
        values.put(KEY_TRANSFORMER_TRANSFORMER_TYPE, transformerActivity.getStrTransformerType() == null ? "" : transformerActivity.getStrTransformerType());
        values.put(KEY_TRANSFORMER_SECONDARY_NET, transformerActivity.getStrSecondatyNetType() == null ? "" : transformerActivity.getStrSecondatyNetType());
        values.put(KEY_TRANSFORMER_USE_RESOURCES, transformerActivity.getStrResourcesUse() == null ? "" : transformerActivity.getStrResourcesUse());
        values.put(KEY_TRANSFORMER_IMAGE_PLATE1, arrayImagesPlate.get(0));
        values.put(KEY_TRANSFORMER_IMAGE_PLATE2, arrayImagesPlate.get(1));
        values.put(KEY_TRANSFORMER_IMAGE_PLATE3, arrayImagesPlate.get(2));
        values.put(KEY_TRANSFORMER_FAILED, transformerActivity.getBoolIsFailed());
        values.put(KEY_TRANSFORMER_FAIL_REASON, transformerActivity.getStrFailReason() == null ? "" : transformerActivity.getStrFailReason());
        values.put(KEY_TRANSFORMER_FAIL_DETAIL, transformerActivity.getStrFailDetail() == null ? "" : transformerActivity.getStrFailDetail());

        /** Se inserta la fila a la tabla */
        db.insert(TABLE_TRANSFORMER_ACTIVITY, null, values);
    }

    /** --------- Se actualiza una actividad de Transformador --------- */
    public void updateTransformerActivitySQLite(DistributionTransformerActivity transformerActivity) {
        SQLiteDatabase db = this.getWritableDatabase();

        /** Array de bytes vacio para poner una img vacia cuando termina la tarea fallida sin llegar a poner una img obligatoria */
        byte[] emptyArray = new byte[0];

        /** Se inicializa los arreglos de imagenes tipo Byte[] en null */
        ArrayList<byte[]> arrayImagesBefore = new ArrayList<byte[]>() {{
            add(null);
            add(null);
            add(null);
        }};
        ArrayList<byte[]> arrayImagesAfter = new ArrayList<byte[]>() {{
            add(null);
            add(null);
            add(null);
        }};
        ArrayList<byte[]> arrayImagesPlate = new ArrayList<byte[]>() {{
            add(null);
            add(null);
            add(null);
        }};

        /** Se llenan las posiciones que tienen imagen y se pasa de Bitmap a Byte para subirlo a la BD */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    if (i == 0) {
                        transformerActivity.getImagesBeforeArray().get(j).compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        arrayImagesBefore.set(j,bos.toByteArray());
                    } else if (i == 1) {
                        transformerActivity.getImagesAfterArray().get(j).compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        arrayImagesAfter.set(j,bos.toByteArray());

                    } else if (i == 2) {
                        transformerActivity.getImageInstalledPlateArray().get(j).compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        arrayImagesPlate.set(j,bos.toByteArray());
                    }
                } catch (Exception e) {
                }
            }
        }

        /** Se establece el formato de la fecha */
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        /** Se crea un contentvalue con cada uno de los valores del objeto de la actividad */
        /** Se validan que los campos obligatorios no sean Null ya que en el diseño de la bd se puso Not Null */
        /** Los que pueden ser null se envian vacios, ya que al recibirlos y crear el objeto, crean Strings "Null" */
        ContentValues values = new ContentValues();
        values.put(KEY_ID, transformerActivity.getStrIdTransformerActivity());
        values.put(KEY_TRANSFORMER_ENROLLMENT, transformerActivity.getStrEnrollment() == null ? "" : transformerActivity.getStrEnrollment());
        values.put(KEY_TRANSFORMER_IMAGE_BEFORE1, arrayImagesBefore.get(0) == null ? emptyArray : arrayImagesBefore.get(0));
        values.put(KEY_TRANSFORMER_IMAGE_BEFORE2, arrayImagesBefore.get(1));
        values.put(KEY_TRANSFORMER_IMAGE_BEFORE3, arrayImagesBefore.get(2));
        values.put(KEY_TRANSFORMER_PLATE, transformerActivity.getIntTechnicalPlate() == null ? 0 : transformerActivity.getIntTechnicalPlate());
        values.put(KEY_TRANSFORMER_IMAGE_AFTER1, arrayImagesAfter.get(0));
        values.put(KEY_TRANSFORMER_IMAGE_AFTER2, arrayImagesAfter.get(1));
        values.put(KEY_TRANSFORMER_IMAGE_AFTER3, arrayImagesAfter.get(2));
        values.put(KEY_TRANSFORMER_OIL, transformerActivity.getStrVegetableOil() == null ? "" : transformerActivity.getStrVegetableOil());
        values.put(KEY_TRANSFORMER_LATITUDE, transformerActivity.getDbTransformerLatitude() == null ? 0 : transformerActivity.getDbTransformerLatitude());
        values.put(KEY_TRANSFORMER_LONGITUDE, transformerActivity.getDbTransformerLongitude() == null ? 0 : transformerActivity.getDbTransformerLongitude());
        values.put(KEY_TRANSFORMER_AREA_TYPE, transformerActivity.getStrAreaType() == null ? "" : transformerActivity.getStrAreaType());
        values.put(KEY_TRANSFORMER_TYPE, transformerActivity.getStrType() == null ? "" : transformerActivity.getStrType());
        values.put(KEY_TRANSFORMER_PCB, transformerActivity.getStrPcbEquip() == null ? "" : transformerActivity.getStrPcbEquip());
        values.put(KEY_TRANSFORMER_PHASE_R, transformerActivity.getStrPhaseR() == null ? "" : transformerActivity.getStrPhaseR());
        values.put(KEY_TRANSFORMER_PHASE_S, transformerActivity.getStrPhaseS() == null ? "" : transformerActivity.getStrPhaseS());
        values.put(KEY_TRANSFORMER_PHASE_T, transformerActivity.getStrPhaseT() == null ? "" : transformerActivity.getStrPhaseT());
        values.put(KEY_TRANSFORMER_EQUIP_STATE, transformerActivity.getStrEquipState() == null ? "" : transformerActivity.getStrEquipState());
        values.put(KEY_TRANSFORMER_STATE, transformerActivity.getStrTransformerState() == null ? "" : transformerActivity.getStrTransformerState());
        values.put(KEY_TRANSFORMER_FABRICATION_DATE, transformerActivity.getDateFabricationDate() == null ? null : dateFormat.format(transformerActivity.getDateFabricationDate()));
        values.put(KEY_TRANSFORMER_GROUP, transformerActivity.getIntGroup());
        values.put(KEY_TRANSFORMER_INSTALLATION, transformerActivity.getStrInstallationOrigin() == null ? "" : transformerActivity.getStrInstallationOrigin());
        values.put(KEY_TRANSFORMER_BRAND, transformerActivity.getStrBrand() == null ? "" : transformerActivity.getStrBrand());
        values.put(KEY_TRANSFORMER_OBSERVATION, transformerActivity.getStrObservation() == null ? "" : transformerActivity.getStrObservation());
        values.put(KEY_TRANSFORMER_PCI, transformerActivity.getStrActivePci() == null ? "" : transformerActivity.getStrActivePci());
        values.put(KEY_TRANSFORMER_NOMINAL_POWER, transformerActivity.getStrNominalPower()  == null ? "" : transformerActivity.getStrNominalPower());
        values.put(KEY_TRANSFORMER_PROPERTY, transformerActivity.getStrProperty() == null ? "" : transformerActivity.getStrProperty());
        values.put(KEY_TRANSFORMER_SEQUENCE_PHASE, transformerActivity.getStrPhaseSequence() == null ? "" : transformerActivity.getStrPhaseSequence());
        values.put(KEY_TRANSFORMER_SUBNORMAL, transformerActivity.getStrSubnormal() == null ? "" : transformerActivity.getStrSubnormal());
        values.put(KEY_TRANSFORMER_PRIMARY_VOLTAGE, transformerActivity.getStrPrimaryVoltage() == null ? "" : transformerActivity.getStrPrimaryVoltage());
        values.put(KEY_TRANSFORMER_SECONDARY_VOLTAGE, transformerActivity.getStrSecondaryVoltage() == null ? "" : transformerActivity.getStrSecondaryVoltage());
        values.put(KEY_TRANSFORMER_CONNECTION, transformerActivity.getStrConnectionType() == null ? "" : transformerActivity.getStrConnectionType());
        values.put(KEY_TRANSFORMER_TRANSFORMER_TYPE, transformerActivity.getStrTransformerType() == null ? "" : transformerActivity.getStrTransformerType());
        values.put(KEY_TRANSFORMER_SECONDARY_NET, transformerActivity.getStrSecondatyNetType() == null ? "" : transformerActivity.getStrSecondatyNetType());
        values.put(KEY_TRANSFORMER_USE_RESOURCES, transformerActivity.getStrResourcesUse() == null ? "" : transformerActivity.getStrResourcesUse());
        values.put(KEY_TRANSFORMER_IMAGE_PLATE1, arrayImagesPlate.get(0));
        values.put(KEY_TRANSFORMER_IMAGE_PLATE2, arrayImagesPlate.get(1));
        values.put(KEY_TRANSFORMER_IMAGE_PLATE3, arrayImagesPlate.get(2));
        values.put(KEY_TRANSFORMER_FAILED, transformerActivity.getBoolIsFailed());
        values.put(KEY_TRANSFORMER_FAIL_REASON, transformerActivity.getStrFailReason() == null ? "" : transformerActivity.getStrFailReason());
        values.put(KEY_TRANSFORMER_FAIL_DETAIL, transformerActivity.getStrFailDetail() == null ? "" : transformerActivity.getStrFailDetail());

        /** Se actualiza la fila a la tabla */
        db.update(TABLE_TRANSFORMER_ACTIVITY, values, KEY_ID + " = ?",
                new String[] { transformerActivity.getStrIdTransformerActivity() });
    }

    /**
     * --------- Se obtiene una única actividad de la tabla transformador ---------
     */
    public void getTransformerActivity(String transformerId) {
        SQLiteDatabase db = this.getReadableDatabase();

        /** Se realiza la consulta a la BD de la actividad con el ID */
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSFORMER_ACTIVITY + " WHERE " + KEY_ID + " = " + transformerId;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            byte[] byteArrayImageBefore1 = null;
            byte[] byteArrayImageBefore2 = null;
            byte[] byteArrayImageBefore3 = null;
            byte[] byteArrayImageAfter1 = null;
            byte[] byteArrayImageAfter2 = null;
            byte[] byteArrayImageAfter3 = null;
            byte[] byteArrayImagePlate1 = null;
            byte[] byteArrayImagePlate2 = null;
            byte[] byteArrayImagePlate3 = null;

            try {
                /** Se leen las imagenes de la bd a partir de la consulta anterior y se guardan tipo Byte */
                byteArrayImageBefore1 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_BEFORE1)));
                byteArrayImageBefore2 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_BEFORE2)));
                byteArrayImageBefore3 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_BEFORE3)));
                byteArrayImageAfter1 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_AFTER1)));
                byteArrayImageAfter2 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_AFTER2)));
                byteArrayImageAfter3 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_AFTER3)));
                byteArrayImagePlate1 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_PLATE1)));
                byteArrayImagePlate2 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_PLATE2)));
                byteArrayImagePlate3 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_PLATE3)));

            }catch (Exception e){}

            /** Si el byte es null se guarda como null, si no, la imagen byte se vuelven bitmap */
            final Bitmap bitImageBefore1 = byteArrayImageBefore1 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageBefore1, 0 ,byteArrayImageBefore1.length);
            final Bitmap bitImageBefore2 = byteArrayImageBefore2 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageBefore2, 0 ,byteArrayImageBefore2.length);
            final Bitmap bitImageBefore3 = byteArrayImageBefore3 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageBefore3, 0 ,byteArrayImageBefore3.length);
            final Bitmap bitImageAfter1 = byteArrayImageAfter1 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageAfter1, 0 ,byteArrayImageAfter1.length);
            final Bitmap bitImageAfter2 = byteArrayImageAfter2 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageAfter2, 0 ,byteArrayImageAfter2.length);
            final Bitmap bitImageAfter3 = byteArrayImageAfter3 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageAfter3, 0 ,byteArrayImageAfter3.length);
            final Bitmap bitImagePlate1 = byteArrayImagePlate1 == null ? null : BitmapFactory.decodeByteArray(byteArrayImagePlate1, 0 ,byteArrayImagePlate1.length);
            final Bitmap bitImagePlate2 = byteArrayImagePlate2 == null ? null : BitmapFactory.decodeByteArray(byteArrayImagePlate2, 0 ,byteArrayImagePlate2.length);
            final Bitmap bitImagePlate3 = byteArrayImagePlate3 == null ? null : BitmapFactory.decodeByteArray(byteArrayImagePlate3, 0 ,byteArrayImagePlate3.length);


            /** Se crea el arreglo de imagenes bitmap para agregarlo al objeto en java */
            ArrayList<Bitmap> arrayImagesBefore = new ArrayList<Bitmap>() {{ add(bitImageBefore1); add(bitImageBefore2); add(bitImageBefore3);}};
            ArrayList<Bitmap> arrayImagesAfter = new ArrayList<Bitmap>() {{ add(bitImageAfter1); add(bitImageAfter2); add(bitImageAfter3); }};
            ArrayList<Bitmap> arrayImagesPlate = new ArrayList<Bitmap>() {{ add(bitImagePlate1); add(bitImagePlate2); add(bitImagePlate3); }};

            /** Se trae la fecha de fabricación, si no es null, se convierte de String -> Date */
            Date date = new Date();
            try {
                if(cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_FABRICATION_DATE))) != null){
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_FABRICATION_DATE))));
                }else{
                    date = null;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (cursor != null)
                cursor.moveToFirst();

            /** Se crea un objeto de la actividaed con toda la información leida de la BD */
            Globals.transformerActivityData = new DistributionTransformerActivity(
                    cursor.getString((cursor.getColumnIndex(KEY_ID))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_ENROLLMENT))),
                    arrayImagesBefore,
                    cursor.getInt((cursor.getColumnIndex(KEY_TRANSFORMER_PLATE))),
                    arrayImagesAfter,
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_OIL))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_TRANSFORMER_LATITUDE))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_TRANSFORMER_LONGITUDE))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_AREA_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PCB))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PHASE_R))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PHASE_S))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PHASE_T))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_EQUIP_STATE))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_STATE))),
                    date,
                    cursor.getInt((cursor.getColumnIndex(KEY_TRANSFORMER_GROUP))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_INSTALLATION))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_BRAND))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_OBSERVATION))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PCI))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_NOMINAL_POWER))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PROPERTY))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_SEQUENCE_PHASE))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_SUBNORMAL))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PRIMARY_VOLTAGE))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_SECONDARY_VOLTAGE))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_CONNECTION))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_TRANSFORMER_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_SECONDARY_NET))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_USE_RESOURCES))),
                    arrayImagesPlate,
                    cursor.getInt((cursor.getColumnIndex(KEY_TRANSFORMER_FAILED))) > 0,
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_FAIL_REASON))),
                    cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_FAIL_DETAIL)))
            );
        }
    }

    /**
     * --------- Se obtienen todas las actividades de la tabla transformador ---------
     * */
    public  ArrayList<DistributionTransformerActivity> getAllTransformerActivities() {
        ArrayList<DistributionTransformerActivity> arrayTransformerActivities = new ArrayList<>();

        /** Consulta para traer toda la información de la tabla de transformador */
        String selectQuery = "SELECT * FROM " + TABLE_TRANSFORMER_ACTIVITY;

        SQLiteDatabase db = this.getReadableDatabase();
        /** Se obtienen los resultados de la consulta */
        final Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                /** Se leen las imagenes de la bd a partir de la consulta anterior y se guardan tipo Byte */
                final byte[] byteArrayImageBefore1 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_BEFORE1)));
                final byte[] byteArrayImageBefore2 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_BEFORE2)));
                final byte[] byteArrayImageBefore3 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_BEFORE3)));
                final byte[] byteArrayImageAfter1 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_AFTER1)));
                final byte[] byteArrayImageAfter2 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_AFTER2)));
                final byte[] byteArrayImageAfter3 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_AFTER3)));
                final byte[] byteArrayImagePlate1 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_PLATE1)));
                final byte[] byteArrayImagePlate2 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_PLATE2)));
                final byte[] byteArrayImagePlate3 = cursor.getBlob((cursor.getColumnIndex(KEY_TRANSFORMER_IMAGE_PLATE3)));

                /** Si el byte es null se guarda como null, si no, la imagen byte se vuelven bitmap */
                final Bitmap bitImageBefore1 = byteArrayImageBefore1 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageBefore1, 0 ,byteArrayImageBefore1.length);
                final Bitmap bitImageBefore2 = byteArrayImageBefore2 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageBefore2, 0 ,byteArrayImageBefore2.length);
                final Bitmap bitImageBefore3 = byteArrayImageBefore3 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageBefore3, 0 ,byteArrayImageBefore3.length);
                final Bitmap bitImageAfter1 = byteArrayImageAfter1 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageAfter1, 0 ,byteArrayImageAfter1.length);
                final Bitmap bitImageAfter2 = byteArrayImageAfter2 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageAfter2, 0 ,byteArrayImageAfter2.length);
                final Bitmap bitImageAfter3 = byteArrayImageAfter3 == null ? null : BitmapFactory.decodeByteArray(byteArrayImageAfter3, 0 ,byteArrayImageAfter3.length);
                final Bitmap bitImagePlate1 = byteArrayImagePlate1 == null ? null : BitmapFactory.decodeByteArray(byteArrayImagePlate1, 0 ,byteArrayImagePlate1.length);
                final Bitmap bitImagePlate2 = byteArrayImagePlate2 == null ? null : BitmapFactory.decodeByteArray(byteArrayImagePlate2, 0 ,byteArrayImagePlate2.length);
                final Bitmap bitImagePlate3 = byteArrayImagePlate3 == null ? null : BitmapFactory.decodeByteArray(byteArrayImagePlate3, 0 ,byteArrayImagePlate3.length);

                /** Se crea el arreglo de imagenes bitmap para agregarlo al objeto en java */
                ArrayList<Bitmap> arrayImagesBefore = new ArrayList<Bitmap>() {{ add(bitImageBefore1); add(bitImageBefore2); add(bitImageBefore3);}};
                ArrayList<Bitmap> arrayImagesAfter = new ArrayList<Bitmap>() {{ add(bitImageAfter1); add(bitImageAfter2); add(bitImageAfter3); }};
                ArrayList<Bitmap> arrayImagesPlate = new ArrayList<Bitmap>() {{ add(bitImagePlate1); add(bitImagePlate2); add(bitImagePlate3); }};

                /** Se trae la fecha de fabricación, si no es null, se convierte de String -> Date */
                Date date = new Date();
                try {
                    if(cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_FABRICATION_DATE))) != null){
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_FABRICATION_DATE))));
                    }else{
                        date = null;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /** Se crea un objeto de la actividaed con toda la información leida de la BD */
                DistributionTransformerActivity transformerActivity = new DistributionTransformerActivity(
                        cursor.getString((cursor.getColumnIndex(KEY_ID))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_ENROLLMENT))),
                        arrayImagesBefore,
                        cursor.getInt((cursor.getColumnIndex(KEY_TRANSFORMER_PLATE))),
                        arrayImagesAfter,
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_OIL))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_TRANSFORMER_LATITUDE))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_TRANSFORMER_LONGITUDE))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_AREA_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PCB))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PHASE_R))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PHASE_S))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PHASE_T))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_EQUIP_STATE))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_STATE))),
                        date,
                        cursor.getInt((cursor.getColumnIndex(KEY_TRANSFORMER_GROUP))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_INSTALLATION))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_BRAND))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_OBSERVATION))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PCI))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_NOMINAL_POWER))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PROPERTY))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_SEQUENCE_PHASE))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_SUBNORMAL))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_PRIMARY_VOLTAGE))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_SECONDARY_VOLTAGE))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_CONNECTION))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_TRANSFORMER_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_SECONDARY_NET))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_USE_RESOURCES))),
                        arrayImagesPlate,
                        cursor.getInt((cursor.getColumnIndex(KEY_TRANSFORMER_FAILED))) > 0,
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_FAIL_REASON))),
                        cursor.getString((cursor.getColumnIndex(KEY_TRANSFORMER_FAIL_DETAIL)))
                );

                /** Se agrega la actividad a la lista de actividades */
                arrayTransformerActivities.add(transformerActivity);
            } while (cursor.moveToNext());
        }
        return arrayTransformerActivities;
    }

    /** --------- Se elimina una actividad de transformador ---------*/
    public void deleteToTransformerActivity(String transformerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSFORMER_ACTIVITY, KEY_ID + " = ?",
                new String[] { transformerId });
    }
    /**
     *
     * --------- Se crea una actividad de Tramo aereo (Tramo de baja tension) ---------
     *
     * */
    public void createLowVoltageSectionActivitySQLite(LowVoltageSectionActivity lowVoltageSectionActivity){
        SQLiteDatabase db = this.getWritableDatabase();

        /** Se crea un contentvalue con cada uno de los valores del objeto de la actividad */
        /** Se validan que los campos obligatorios no sean Null ya que en el diseño de la bd se puso Not Null */
        /** Los que pueden ser null se envian vacios, ya que al recibirlos y crear el objeto, crean Strings "Null" */
        ContentValues values = new ContentValues();
        values.put(KEY_ID, lowVoltageSectionActivity.getStrIdActivity());
        values.put(KEY_LOW_VOLTAGE_SECTION_START_SUPPORT, lowVoltageSectionActivity.getStrStartSupport() == null ? "" : lowVoltageSectionActivity.getStrStartSupport());
        values.put(KEY_LOW_VOLTAGE_SECTION_FINAL_SUPPORT, lowVoltageSectionActivity.getStrFinalSupport() == null ? "" : lowVoltageSectionActivity.getStrFinalSupport());
        values.put(KEY_LOW_VOLTAGE_SECTION_MATERIAL, lowVoltageSectionActivity.getStrMaterial() == null ? "" : lowVoltageSectionActivity.getStrMaterial());
        values.put(KEY_LOW_VOLTAGE_SECTION_CANALIZATION, lowVoltageSectionActivity.getStrCanalization() == null ? "" : lowVoltageSectionActivity.getStrCanalization());
        values.put(KEY_LOW_VOLTAGE_SECTION_N_CONDUCTOR, lowVoltageSectionActivity.getStrNConductors() == null ? "" : lowVoltageSectionActivity.getStrNConductors());
        values.put(KEY_LOW_VOLTAGE_SECTION_DISPOSITION_CONDUCTOR, lowVoltageSectionActivity.getStrDispositionConductor() == null ? "" : lowVoltageSectionActivity.getStrDispositionConductor());
        values.put(KEY_LOW_VOLTAGE_SECTION_LENGTH, lowVoltageSectionActivity.getStrLength() == null ? "" : lowVoltageSectionActivity.getStrLength());
        values.put(KEY_LOW_VOLTAGE_SECTION_OBSERVATION, lowVoltageSectionActivity.getStrObservation() == null ? "" : lowVoltageSectionActivity.getStrObservation());
        values.put(KEY_LOW_VOLTAGE_SECTION_PROPERTY, lowVoltageSectionActivity.getStrProperty() == null ? "" : lowVoltageSectionActivity.getStrProperty());
        values.put(KEY_LOW_VOLTAGE_SECTION_AREA_TYPE, lowVoltageSectionActivity.getStrAreaType() == null ? "" : lowVoltageSectionActivity.getStrAreaType());
        values.put(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE1, lowVoltageSectionActivity.getStrConductorType1() == null ? "" : lowVoltageSectionActivity.getStrConductorType1());
        values.put(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE2, lowVoltageSectionActivity.getStrConductorType2() == null ? "" : lowVoltageSectionActivity.getStrConductorType2());
        values.put(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE3, lowVoltageSectionActivity.getStrConductorType3() == null ? "" : lowVoltageSectionActivity.getStrConductorType3());
        values.put(KEY_LOW_VOLTAGE_SECTION_NEUTRAL_CONDUCTOR, lowVoltageSectionActivity.getStrNeutralConductor() == null ? "" : lowVoltageSectionActivity.getStrNeutralConductor());
        values.put(KEY_LOW_VOLTAGE_SECTION_SECTION_TYPE, lowVoltageSectionActivity.getStrSectionType() == null ? "" : lowVoltageSectionActivity.getStrSectionType());
        values.put(KEY_LOW_VOLTAGE_SECTION_USE_RESOURCES, lowVoltageSectionActivity.getStrResourcesUse() == null ? "" : lowVoltageSectionActivity.getStrResourcesUse());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAILED, lowVoltageSectionActivity.getBoolIsFailed());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAIL_REASON, lowVoltageSectionActivity.getStrFailReason() == null ? "" : lowVoltageSectionActivity.getStrFailReason());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAIL_DETAIL, lowVoltageSectionActivity.getStrFailDetail() == null ? "" : lowVoltageSectionActivity.getStrFailDetail());

        /** Se inserta la fila a la tabla */
        db.insert(TABLE_LOW_VOLTAGE_SECTION_ACTIVITY, null, values);
    }

    /**
     * --------- Se obtiene una única actividad de la tabla Tramo aereo (Tramo de baja tension) ---------
     */
    public void getLowVoltageSectionActivity(String idActivity) {
        SQLiteDatabase db = this.getReadableDatabase();

        /** Se realiza la consulta a la BD de la actividad con el ID */
        String selectQuery = "SELECT  * FROM " + TABLE_LOW_VOLTAGE_SECTION_ACTIVITY + " WHERE " + KEY_ID + " = " + idActivity;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            if (cursor != null)
                cursor.moveToFirst();

            /** Se crea un objeto de la actividaed con toda la información leida de la BD */
            Globals.lowVoltageSectionActivity = new LowVoltageSectionActivity(
                    cursor.getString((cursor.getColumnIndex(KEY_ID))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_START_SUPPORT))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_FINAL_SUPPORT))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_MATERIAL))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_CANALIZATION))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_N_CONDUCTOR))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_DISPOSITION_CONDUCTOR))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_LENGTH))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_OBSERVATION))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_PROPERTY))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_AREA_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE1))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE2))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE3))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_NEUTRAL_CONDUCTOR))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_SECTION_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_USE_RESOURCES))),
                    cursor.getInt((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_FAILED))) > 0,
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_FAIL_REASON))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_FAIL_DETAIL)))
            );

        }
    }

    /**
     * --------- Se obtienen todas las actividades de la tabla Tramo aereo (Tramo de baja tension) ---------
     * */
    public  ArrayList<LowVoltageSectionActivity> getAllLowVoltageSectionActivities() {
        ArrayList<LowVoltageSectionActivity> arrayLowVoltageSectionActivities = new ArrayList<>();

        /** Consulta para traer toda la información de la tabla de Tramo Aereo */
        String selectQuery = "SELECT * FROM " + TABLE_LOW_VOLTAGE_SECTION_ACTIVITY;

        SQLiteDatabase db = this.getReadableDatabase();
        /** Se obtienen los resultados de la consulta */
        final Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                /** Se crea un objeto de la actividaed con toda la información leida de la BD */
                LowVoltageSectionActivity lowVoltageSectionActivity = new LowVoltageSectionActivity(
                        cursor.getString((cursor.getColumnIndex(KEY_ID))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_START_SUPPORT))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_FINAL_SUPPORT))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_MATERIAL))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_CANALIZATION))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_N_CONDUCTOR))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_DISPOSITION_CONDUCTOR))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_LENGTH))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_OBSERVATION))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_PROPERTY))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_AREA_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE1))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE2))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE3))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_NEUTRAL_CONDUCTOR))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_SECTION_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_USE_RESOURCES))),
                        cursor.getInt((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_FAILED))) > 0,
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_FAIL_REASON))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SECTION_FAIL_DETAIL)))
                );

                /** Se agrega la actividad a la lista de actividades */
                arrayLowVoltageSectionActivities.add(lowVoltageSectionActivity);
            } while (cursor.moveToNext());
        }
        return arrayLowVoltageSectionActivities;
    }

    /**
     * --------- Se actualiza una actividad de Tramo aereo (Tramo de baja tension) ---------
     * */
    public void updateLowVoltageSectionActivity(LowVoltageSectionActivity lowVoltageSectionActivity) {
        SQLiteDatabase db = this.getWritableDatabase();

        /** Se crea un contentvalue con cada uno de los valores del objeto de la actividad */
        /** Se validan que los campos obligatorios no sean Null ya que en el diseño de la bd se puso Not Null */
        /** Los que pueden ser null se envian vacios, ya que al recibirlos y crear el objeto, crean Strings "Null" */
        ContentValues values = new ContentValues();
        values.put(KEY_ID, lowVoltageSectionActivity.getStrIdActivity());
        values.put(KEY_LOW_VOLTAGE_SECTION_START_SUPPORT, lowVoltageSectionActivity.getStrStartSupport() == null ? "" : lowVoltageSectionActivity.getStrStartSupport());
        values.put(KEY_LOW_VOLTAGE_SECTION_FINAL_SUPPORT, lowVoltageSectionActivity.getStrFinalSupport() == null ? "" : lowVoltageSectionActivity.getStrFinalSupport());
        values.put(KEY_LOW_VOLTAGE_SECTION_MATERIAL, lowVoltageSectionActivity.getStrMaterial() == null ? "" : lowVoltageSectionActivity.getStrMaterial());
        values.put(KEY_LOW_VOLTAGE_SECTION_CANALIZATION, lowVoltageSectionActivity.getStrCanalization() == null ? "" : lowVoltageSectionActivity.getStrCanalization());
        values.put(KEY_LOW_VOLTAGE_SECTION_N_CONDUCTOR, lowVoltageSectionActivity.getStrNConductors() == null ? "" : lowVoltageSectionActivity.getStrNConductors());
        values.put(KEY_LOW_VOLTAGE_SECTION_DISPOSITION_CONDUCTOR, lowVoltageSectionActivity.getStrDispositionConductor() == null ? "" : lowVoltageSectionActivity.getStrDispositionConductor());
        values.put(KEY_LOW_VOLTAGE_SECTION_LENGTH, lowVoltageSectionActivity.getStrLength() == null ? "" : lowVoltageSectionActivity.getStrLength());
        values.put(KEY_LOW_VOLTAGE_SECTION_OBSERVATION, lowVoltageSectionActivity.getStrObservation() == null ? "" : lowVoltageSectionActivity.getStrObservation());
        values.put(KEY_LOW_VOLTAGE_SECTION_PROPERTY, lowVoltageSectionActivity.getStrProperty() == null ? "" : lowVoltageSectionActivity.getStrProperty());
        values.put(KEY_LOW_VOLTAGE_SECTION_AREA_TYPE, lowVoltageSectionActivity.getStrAreaType() == null ? "" : lowVoltageSectionActivity.getStrAreaType());
        values.put(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE1, lowVoltageSectionActivity.getStrConductorType1() == null ? "" : lowVoltageSectionActivity.getStrConductorType1());
        values.put(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE2, lowVoltageSectionActivity.getStrConductorType2() == null ? "" : lowVoltageSectionActivity.getStrConductorType2());
        values.put(KEY_LOW_VOLTAGE_SECTION_CONDUCTOR_TYPE3, lowVoltageSectionActivity.getStrConductorType3() == null ? "" : lowVoltageSectionActivity.getStrConductorType3());
        values.put(KEY_LOW_VOLTAGE_SECTION_NEUTRAL_CONDUCTOR, lowVoltageSectionActivity.getStrNeutralConductor() == null ? "" : lowVoltageSectionActivity.getStrNeutralConductor());
        values.put(KEY_LOW_VOLTAGE_SECTION_SECTION_TYPE, lowVoltageSectionActivity.getStrSectionType() == null ? "" : lowVoltageSectionActivity.getStrSectionType());
        values.put(KEY_LOW_VOLTAGE_SECTION_USE_RESOURCES, lowVoltageSectionActivity.getStrResourcesUse() == null ? "" : lowVoltageSectionActivity.getStrResourcesUse());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAILED, lowVoltageSectionActivity.getBoolIsFailed());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAIL_REASON, lowVoltageSectionActivity.getStrFailReason() == null ? "" : lowVoltageSectionActivity.getStrFailReason());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAIL_DETAIL, lowVoltageSectionActivity.getStrFailDetail() == null ? "" : lowVoltageSectionActivity.getStrFailDetail());

        db.update(TABLE_LOW_VOLTAGE_SECTION_ACTIVITY, values,KEY_ID + " = ?",
                new String[] { lowVoltageSectionActivity.getStrIdActivity() });
    }

    /**
     * --------- Se elimina una actividad de Tramo aereo (Tramo de baja tension) ---------
     * */
    public void deleteToLowVoltageSectionActivity(String idActivity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOW_VOLTAGE_SECTION_ACTIVITY, KEY_ID + " =  ?",
                new String[] { idActivity });
    }


    /**
     *
     * --------- Se crea una actividad de apoyo aereo (apoyo de baja tension) ---------
     *
     * */
    public void createLowVoltageSupportActivitySQLite(LowVoltageSupportActivity lowVoltageSupportActivity){
        SQLiteDatabase db = this.getWritableDatabase();

        /** Se crea un contentvalue con cada uno de los valores del objeto de la actividad */
        /** Se validan que los campos obligatorios no sean Null ya que en el diseño de la bd se puso Not Null */
        /** Los que pueden ser null se envian vacios, ya que al recibirlos y crear el objeto, crean Strings "Null" */
        ContentValues values = new ContentValues();
        values.put(KEY_ID, lowVoltageSupportActivity.getStrIdActivity());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_HEIGHT, lowVoltageSupportActivity.getIntSupportHeight() == null ? 0 : lowVoltageSupportActivity.getIntSupportHeight());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_LOAD, lowVoltageSupportActivity.getStrLoadSupport() == null ? "" : lowVoltageSupportActivity.getStrLoadSupport());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_FOUNDATION, lowVoltageSupportActivity.getStrFoundationSupport() == null ? "" : lowVoltageSupportActivity.getStrFoundationSupport());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_CIRCUITS, lowVoltageSupportActivity.getIntCircuitsSupport() == null ? 10 : lowVoltageSupportActivity.getIntCircuitsSupport());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_SHARED, lowVoltageSupportActivity.getStrShared() == null ? "" : lowVoltageSupportActivity.getStrShared());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_STATE_SECTION, lowVoltageSupportActivity.getStrStateSection() == null ? "" : lowVoltageSupportActivity.getStrStateSection());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE, lowVoltageSupportActivity.getStrStructure() == null ? "" : lowVoltageSupportActivity.getStrStructure());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_BT, lowVoltageSupportActivity.getStrStructureBT() == null ? "" : lowVoltageSupportActivity.getStrStructureBT());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_MT, lowVoltageSupportActivity.getStrStructureMT() == null ? "" : lowVoltageSupportActivity.getStrStructureMT());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_LATITUDE, lowVoltageSupportActivity.getDbLocationLatitude() == null ? 0.0 : lowVoltageSupportActivity.getDbLocationLatitude());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_LONGITUDE, lowVoltageSupportActivity.getDbLocationLongitude() == null ? 0.0 : lowVoltageSupportActivity.getDbLocationLongitude());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_MATERIAL, lowVoltageSupportActivity.getStrMaterialSupport() == null ? "" : lowVoltageSupportActivity.getStrMaterialSupport());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_ENROLL, lowVoltageSupportActivity.getStrEnroll() == null ? "" : lowVoltageSupportActivity.getStrEnroll());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_OBSERVATIONS, lowVoltageSupportActivity.getStrObservation() == null ? "" : lowVoltageSupportActivity.getStrObservation());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_PASS, lowVoltageSupportActivity.getStrPass() == null ? "" : lowVoltageSupportActivity.getStrPass());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_PORTICO, lowVoltageSupportActivity.getStrPortico() == null ? "" : lowVoltageSupportActivity.getStrPortico());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_PROPERTY, lowVoltageSupportActivity.getStrProperty() == null ? "" : lowVoltageSupportActivity.getStrProperty());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_PROVINCE, lowVoltageSupportActivity.getStrProvince() == null ? "" : lowVoltageSupportActivity.getStrProvince());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_GROUNDING, lowVoltageSupportActivity.getStrGrounding() == null ? "" : lowVoltageSupportActivity.getStrGrounding());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_DETAINED, lowVoltageSupportActivity.getStrDetained() == null ? "" : lowVoltageSupportActivity.getStrDetained());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_INSULATOR_TYPE, lowVoltageSupportActivity.getStrInsulatorType() == null ? "" : lowVoltageSupportActivity.getStrInsulatorType());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_TYPE, lowVoltageSupportActivity.getStrSupportType() == null ? "" : lowVoltageSupportActivity.getStrSupportType());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_AREA_TYPE, lowVoltageSupportActivity.getStrAreaType() == null ? "" : lowVoltageSupportActivity.getStrAreaType());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_NET_TYPE, lowVoltageSupportActivity.getStrNetType() == null ? "" : lowVoltageSupportActivity.getStrNetType());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_USE_RESOURCES, lowVoltageSupportActivity.getStrUseResources() == null ? "" : lowVoltageSupportActivity.getStrUseResources());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_UUCC, lowVoltageSupportActivity.getStrUUCC() == null ? "" : lowVoltageSupportActivity.getStrUUCC());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAILED, lowVoltageSupportActivity.getBoolIsFailed());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAIL_REASON, lowVoltageSupportActivity.getStrFailReason() == null ? "" : lowVoltageSupportActivity.getStrFailReason());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAIL_DETAIL, lowVoltageSupportActivity.getStrFailDetail() == null ? "" : lowVoltageSupportActivity.getStrFailDetail());

        /** Se inserta la fila a la tabla */
        db.insert(TABLE_LOW_VOLTAGE_SUPPORT_ACTIVITY, null, values);
    }

    /**
     * --------- Se obtiene una única actividad de la tabla Apoyo aereo (Tramo de baja tension) ---------
     */
    public void getLowVoltageSupportActivity(String idActivity) {
        SQLiteDatabase db = this.getReadableDatabase();

        /** Se realiza la consulta a la BD de la actividad con el ID */
        String selectQuery = "SELECT  * FROM " + TABLE_LOW_VOLTAGE_SUPPORT_ACTIVITY + " WHERE " + KEY_ID + " = " + idActivity;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            if (cursor != null)
                cursor.moveToFirst();

            /** Se crea un objeto de la actividaed con toda la información leida de la BD */
            Globals.lowVoltageSupportActivity = new LowVoltageSupportActivity(
                    cursor.getString((cursor.getColumnIndex(KEY_ID))),
                    cursor.getInt((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_HEIGHT))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_LOAD))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_FOUNDATION))),
                    cursor.getInt((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_CIRCUITS))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_SHARED))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_STATE_SECTION))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_BT))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_MT))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_LATITUDE))),
                    cursor.getDouble((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_LONGITUDE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_MATERIAL))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_ENROLL))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_OBSERVATIONS))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_PASS))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_PORTICO))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_PROPERTY))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_PROVINCE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_GROUNDING))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_DETAINED))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_INSULATOR_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_AREA_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_NET_TYPE))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_USE_RESOURCES))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_UUCC))),
                    cursor.getInt((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_FAILED))) > 0,
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_FAIL_REASON))),
                    cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_FAIL_DETAIL)))
            );

        }
    }

    /**
     * --------- Se obtienen todas las actividades de la tabla Apoyo aereo (Tramo de baja tension) ---------
     * */
    public  ArrayList<LowVoltageSupportActivity> getAllLowVoltageSupportActivities() {
        ArrayList<LowVoltageSupportActivity> arrayLowVoltageSupportActivity = new ArrayList<>();

        /** Consulta para traer toda la información de la tabla de Tramo Aereo */
        String selectQuery = "SELECT * FROM " + TABLE_LOW_VOLTAGE_SUPPORT_ACTIVITY;

        SQLiteDatabase db = this.getReadableDatabase();
        /** Se obtienen los resultados de la consulta */
        final Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                /** Se crea un objeto de la actividaed con toda la información leida de la BD */
                LowVoltageSupportActivity lowVoltageSupportActivity = new LowVoltageSupportActivity(
                        cursor.getString((cursor.getColumnIndex(KEY_ID))),
                        cursor.getInt((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_HEIGHT))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_LOAD))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_FOUNDATION))),
                        cursor.getInt((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_CIRCUITS))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_SHARED))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_STATE_SECTION))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_BT))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_MT))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_LATITUDE))),
                        cursor.getDouble((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_LONGITUDE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_MATERIAL))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_ENROLL))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_OBSERVATIONS))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_PASS))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_PORTICO))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_PROPERTY))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_PROVINCE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_GROUNDING))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_DETAINED))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_INSULATOR_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_AREA_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_NET_TYPE))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_USE_RESOURCES))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_UUCC))),
                        cursor.getInt((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_FAILED))) > 0,
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_FAIL_REASON))),
                        cursor.getString((cursor.getColumnIndex(KEY_LOW_VOLTAGE_SUPPORT_FAIL_DETAIL)))
                );

                /** Se agrega la actividad a la lista de actividades */
                arrayLowVoltageSupportActivity.add(lowVoltageSupportActivity);
            } while (cursor.moveToNext());
        }
        return arrayLowVoltageSupportActivity;
    }

    /**
     * --------- Se actualiza una actividad de Apoyo aereo (Tramo de baja tension) ---------
     * */
    public void updateLowVoltageSupportActivity(LowVoltageSupportActivity lowVoltageSupportActivity) {
        SQLiteDatabase db = this.getWritableDatabase();

        /** Se crea un contentvalue con cada uno de los valores del objeto de la actividad */
        /** Se validan que los campos obligatorios no sean Null ya que en el diseño de la bd se puso Not Null */
        /** Los que pueden ser null se envian vacios, ya que al recibirlos y crear el objeto, crean Strings "Null" */
        ContentValues values = new ContentValues();
        values.put(KEY_ID, lowVoltageSupportActivity.getStrIdActivity());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_HEIGHT, lowVoltageSupportActivity.getIntSupportHeight() == null ? 0 : lowVoltageSupportActivity.getIntSupportHeight());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_LOAD, lowVoltageSupportActivity.getStrLoadSupport() == null ? "" : lowVoltageSupportActivity.getStrLoadSupport());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_FOUNDATION, lowVoltageSupportActivity.getStrFoundationSupport() == null ? "" : lowVoltageSupportActivity.getStrFoundationSupport());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_CIRCUITS, lowVoltageSupportActivity.getIntCircuitsSupport() == null ? 10 : lowVoltageSupportActivity.getIntCircuitsSupport());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_SHARED, lowVoltageSupportActivity.getStrShared() == null ? "" : lowVoltageSupportActivity.getStrShared());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_STATE_SECTION, lowVoltageSupportActivity.getStrStateSection() == null ? "" : lowVoltageSupportActivity.getStrStateSection());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE, lowVoltageSupportActivity.getStrStructure() == null ? "" : lowVoltageSupportActivity.getStrStructure());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_BT, lowVoltageSupportActivity.getStrStructureBT() == null ? "" : lowVoltageSupportActivity.getStrStructureBT());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_STRUCTURE_MT, lowVoltageSupportActivity.getStrStructureMT() == null ? "" : lowVoltageSupportActivity.getStrStructureMT());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_LATITUDE, lowVoltageSupportActivity.getDbLocationLatitude() == null ? 0.0 : lowVoltageSupportActivity.getDbLocationLatitude());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_LONGITUDE, lowVoltageSupportActivity.getDbLocationLongitude() == null ? 0.0 : lowVoltageSupportActivity.getDbLocationLongitude());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_MATERIAL, lowVoltageSupportActivity.getStrMaterialSupport() == null ? "" : lowVoltageSupportActivity.getStrMaterialSupport());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_ENROLL, lowVoltageSupportActivity.getStrEnroll() == null ? "" : lowVoltageSupportActivity.getStrEnroll());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_OBSERVATIONS, lowVoltageSupportActivity.getStrObservation() == null ? "" : lowVoltageSupportActivity.getStrObservation());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_PASS, lowVoltageSupportActivity.getStrPass() == null ? "" : lowVoltageSupportActivity.getStrPass());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_PORTICO, lowVoltageSupportActivity.getStrPortico() == null ? "" : lowVoltageSupportActivity.getStrPortico());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_PROPERTY, lowVoltageSupportActivity.getStrProperty() == null ? "" : lowVoltageSupportActivity.getStrProperty());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_PROVINCE, lowVoltageSupportActivity.getStrProvince() == null ? "" : lowVoltageSupportActivity.getStrProvince());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_GROUNDING, lowVoltageSupportActivity.getStrGrounding() == null ? "" : lowVoltageSupportActivity.getStrGrounding());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_DETAINED, lowVoltageSupportActivity.getStrDetained() == null ? "" : lowVoltageSupportActivity.getStrDetained());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_INSULATOR_TYPE, lowVoltageSupportActivity.getStrInsulatorType() == null ? "" : lowVoltageSupportActivity.getStrInsulatorType());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_TYPE, lowVoltageSupportActivity.getStrSupportType() == null ? "" : lowVoltageSupportActivity.getStrSupportType());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_AREA_TYPE, lowVoltageSupportActivity.getStrAreaType() == null ? "" : lowVoltageSupportActivity.getStrAreaType());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_NET_TYPE, lowVoltageSupportActivity.getStrNetType() == null ? "" : lowVoltageSupportActivity.getStrNetType());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_USE_RESOURCES, lowVoltageSupportActivity.getStrUseResources() == null ? "" : lowVoltageSupportActivity.getStrUseResources());
        values.put(KEY_LOW_VOLTAGE_SUPPORT_UUCC, lowVoltageSupportActivity.getStrUUCC() == null ? "" : lowVoltageSupportActivity.getStrUUCC());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAILED, lowVoltageSupportActivity.getBoolIsFailed());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAIL_REASON, lowVoltageSupportActivity.getStrFailReason() == null ? "" : lowVoltageSupportActivity.getStrFailReason());
        values.put(KEY_LOW_VOLTAGE_SECTION_FAIL_DETAIL, lowVoltageSupportActivity.getStrFailDetail() == null ? "" : lowVoltageSupportActivity.getStrFailDetail());

        db.update(TABLE_LOW_VOLTAGE_SUPPORT_ACTIVITY, values,KEY_ID + " = ?",
                new String[] { lowVoltageSupportActivity.getStrIdActivity() });
    }

    /**
     * --------- Se elimina una actividad de Apoyo aereo (Tramo de baja tension) ---------
     * */
    public void deleteToLowVoltageSupportActivity(String idActivity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOW_VOLTAGE_SUPPORT_ACTIVITY, KEY_ID + " =  ?",
                new String[] { idActivity });
    }

    /**
     * --------- Se cierra la BD ¡importante! hacerlo siempre ---------
     * */
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


}
