package com.example.artikolevyinf.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.artikolevyinf.Controller.GeneralActivity.SendGeneralActivities;
import com.example.artikolevyinf.Controller.InfrastructureSurvey.SendActivitiesLowVoltageSection;
import com.example.artikolevyinf.Controller.InfrastructureSurvey.SendActivitiesTransformer;
import com.example.artikolevyinf.Controller.SQLite.AdminSQLiteOpenHelper;
import com.example.artikolevyinf.Controller.Socket.SendSocket;
import com.example.artikolevyinf.MainActivity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.DistributionTransformerActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSupportActivity;
import com.example.artikolevyinf.R;
import com.example.artikolevyinf.TextViews.TextView_blueDark_10;
import com.example.artikolevyinf.TextViews.TextView_blue_12;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;

public class Utils {
    public static void openMainActivity(Activity activity){
        if(Globals.getInfrastructureActivitiesFinish){

            Utils.getAllGeneralActivitiesToSQLite(activity);

            Globals.splashActivityFinish = true;
            System.out.println("OPEN MAIN ACTIVITY");

            /** Se obtienen las cards padres de cada tipo */
            getActivities();

            Intent i = new Intent(activity, MainActivity.class);
            activity.startActivity(i);

            Globals.getInfrastructureActivitiesFinish = false;

        }
    }
    public static void getActivities() {


        /** Información de las cards*/
        ArrayList<String> cardsName = new ArrayList<>();
        cardsName.add("Levantamiento de infraestructura");
        ArrayList<Integer> cardsIcon = new ArrayList<>();
        cardsIcon.add(R.drawable.ic_infrastructure);

        ArrayList<ArrayList<CardGeneralActivity>> cardsArray = new ArrayList<>();
        cardsArray.add(Globals.dataInfrastructureSurveyActivitiesArray);
        System.out.println("GET ACTIVITIES");

        for (int i = 0; i < cardsName.size(); i++) {

            int numTotal = cardsArray.get(i).size();
            int numExecuted = Utils.numCardsExecuted(cardsArray.get(i));
            String subTitle = "Actividades " + numExecuted + "/" + numTotal;

        }
    }
    //Toast para mostrar información
    public static void toastMessage (String infoMessage,int duration, Context context){
        Toast.makeText(context, infoMessage, duration).show();
    }

    //Obtiene la ubicación actual, ademas de solicitar permisos en caso de no tenerlos
    public static void loadCurrentLocationWithVerification (Activity activity, Context context, TextView textView,double activityLatitude, double activityLongitude){
        try {
            HashMap map = getCurrentLocation(activity, context);
            Double latitude = (Double) map.get("latitude");
            Double longitude = (Double) map.get("longitude");

            double distance = getDistanceBetween(latitude, longitude, activityLatitude, activityLongitude);
            if (distance <= 30) {
                textView.setText(latitude + ", " + longitude);
            } else {
                toastMessage("No se encuentra en el lugar correcto. Por favor diríjase al lugar donde está ubicada la entidad y vuelva a presionar la casilla de Ubicación geográfica.", 1, context);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Convertir un vector a un bitmap
    public static Bitmap vectorToBitmap (Context context,int resId){
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        return bitmap;
    }

    //Convertir un vector a BitmapDexcriptor (se usa el los marcadores del mapa)
    public static BitmapDescriptor vectorToBitmapBitmapDescriptorWithColor (Context context,int resId, int colorId){
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        int color = context.getResources().getColor(colorId);

        DrawableCompat.setTint(drawable, color);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    //Convertir un vector a BitmapDexcriptor (se usa el los marcadores del mapa)
    public static BitmapDescriptor vectorToBitmapBitmapDescriptorWithOutColor (Context context,int resId){
        Drawable drawable = ContextCompat.getDrawable(context, resId);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    //Obtiene la ubicación actual, ademas de solicitar permisos en caso de no tenerlos
    public static HashMap<String, Double> getCurrentLocation (Activity activity, Context context)
    {
        Location currentLocation;
        LocationManager locationManager = (LocationManager) activity.getSystemService(context.LOCATION_SERVICE);

        HashMap<String, Double> map = new HashMap<>();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            //Se solicitan los permisos necesarios si no los tiene
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return null;
        }

        try {
            currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (currentLocation != null) {
                Double latitude = currentLocation.getLatitude();
                Double longitude = currentLocation.getLongitude();
                map.put("latitude", latitude);
                map.put("longitude", longitude);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public static String getCurrentAddress (Double latitude, Double longitude, Context context, Activity activity){
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        addresses = null;

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String[] separated = address.split(",");
        String finalAddress = separated[0];


        return finalAddress;
    }
    public static boolean callPhoneNumber (String phone, Context context, Activity activity){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            //Se solicitan los permisos necesarios si no los tiene
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return false;
        }
        context.startActivity(intent);
        return true;
    }

    //Obtiene la ubicación actual, ademas de solicitar permisos en caso de no tenerlos
    public static String getDeviceID (Activity activity, Context context){

        String deviceId = "";

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                //Se solicitan los permisos necesarios si no los tiene
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                return null;
            }

            try {
                deviceId = mTelephony.getDeviceId();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (deviceId == "") {
                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        }

        return deviceId;
    }

    //añadir imagen a una lista
    public static void addImageCamera (Bitmap bitmap, ArrayList < Bitmap > arrayList, boolean onlyShow, int maxImage, Context context, LinearLayout linearLayout){
        if (arrayList.size() < maxImage) {
            //se obtiene el valor del ancho en unidades dp
            int width = flotatToDp(52, context);

            //Se crea un image view para cada foto
            ShapeableImageView imgView = new ShapeableImageView(context);
            //Se define el radio de las esquinas
            float radius = 4 * context.getResources().getDisplayMetrics().density;
            //Se aplica el radio a todas las esquinas
            imgView.setShapeAppearanceModel(imgView.getShapeAppearanceModel().toBuilder().setAllCorners(CornerFamily.ROUNDED, radius).build());
            //Se ha ilita para no perder la proporci[on de la imagen
            imgView.setAdjustViewBounds(true);
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //Se actualiza el alto, ancho y margenes
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
            //Se define el valor del margin en dp
            int margin = flotatToDp(5, context);
            lp.setMargins(margin, 0, margin, 0);
            imgView.setLayoutParams(lp);

            //Se añade al linear layout
            linearLayout.addView(imgView);

            //Se actualiza la imagen
            imgView.setImageBitmap(bitmap);

            if (!onlyShow) {
                arrayList.add(bitmap);
            }

        } else {
            Utils.toastMessage("Has alcanzado el límite de 3 imágenes", 1, context);
        }
    }
    //Se crea una ventana emergente de tipo information
    public static void alertInformation(String title, String content, Context context, Activity activity, final Callable<Void> functionPositive, final Callable<Void> functionNegative) {
        AlertDialog.Builder alertBox = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        //Se asigna el estilo del contenido
        View view = activity.getLayoutInflater().inflate(R.layout.alert_dialog_information, null);
        alertBox.setView(view);

        final AlertDialog alert = alertBox.create();
        alert.setCanceledOnTouchOutside(true);

        //Se asigna el titulo a la emergente
        TextView_blue_12 titleAlert = (TextView_blue_12) view.findViewById(R.id.titleAlertDialogInfo);
        titleAlert.setText(title);

        //Se asigna el texto de contenido a la emergente
        TextView_blueDark_10 contentAlert = (TextView_blueDark_10) view.findViewById(R.id.contentTextAlertDialogInfo);
        contentAlert.setText(content);

        //Asigno el onclick de los botones positivos y negativos
        Button negative = (Button) view.findViewById(R.id.negative_action);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se llama al metodo enviado
                try {
                    functionNegative.call();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                alert.dismiss();
            }
        });
        Button positive = (Button) view.findViewById(R.id.positive_action);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se llama al metodo enviado
                try {
                    functionPositive.call();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                alert.dismiss();
            }
        });
        /** Detecta el click fuera de la ventana*/
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //Se llama al metodo enviado
                try {
                    functionNegative.call();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                alert.dismiss();
            }
        });
        alert.show();
    }

    //Se crea una ventana emergente de tipo fail
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void alertFail(final String[] content, Context context, final Activity activity, final Callable<Void> function) {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        //Se asigna el estilo del contenido
        View view = activity.getLayoutInflater().inflate(R.layout.alert_dialog_fail, null);
        alertbox.setView(view);
        final AlertDialog alert = alertbox.create();

        //Se asigna los valores al spinner
        final Spinner spFailAlertEnroll = view.findViewById(R.id.fail_reason_activity_spn_fail_alert_dialog);
        ArrayAdapter<String> adapterSFAE = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, content);
        spFailAlertEnroll.setAdapter(adapterSFAE);

        //Se inicializa el EditText
        final EditText etWhichReason = view.findViewById(R.id.which_reason_et_fail_alert_dialog);
        etWhichReason.setEnabled(false);
        etWhichReason.setBackground(activity.getDrawable(R.drawable.input_general_disable));

        //Se escucha el spinner
        spFailAlertEnroll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = spFailAlertEnroll.getItemAtPosition(position).toString();
                if(itemSelected.equals("Otro")){
                    etWhichReason.setEnabled(true);
                    etWhichReason.setBackground(activity.getDrawable(R.drawable.input_general));
                }else{
                    etWhichReason.setEnabled(false);
                    etWhichReason.setBackground(activity.getDrawable(R.drawable.input_general_disable));
                    etWhichReason.setError(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Asigno el onclick de los botones positivos y negativos
        Button negative = (Button) view.findViewById(R.id.negative_action);
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        Button positive = (Button) view.findViewById(R.id.positive_action);
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se validan que los campos no esten vacios
                String failReason = spFailAlertEnroll.getSelectedItem().toString();
                String whichReason = etWhichReason.getText().toString();

                if (!TextUtils.isEmpty(failReason)) {

                    if(failReason.equals("Otro") && !TextUtils.isEmpty(whichReason)){
                        //Se llama al metodo enviado
                        try {
                            Globals.strFailedReasonActivity = whichReason;
                            Globals.strFailedDetailActivity = whichReason;
                            function.call();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        alert.dismiss();

                        return;
                    }else if(!failReason.equals("Otro")){
                        //Se llama al metodo enviado
                        try {
                            Globals.strFailedReasonActivity = failReason;
                            Globals.strFailedDetailActivity = "";
                            function.call();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        alert.dismiss();

                        return;
                    }

                }

                if (TextUtils.isEmpty(failReason)) {
                    TextView errorText = (TextView) spFailAlertEnroll.getSelectedView();
                    errorText.setPadding(0, 0, 100, 0);
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Este campo no puede quedar vacío");//changes the selected item text to this
                }
                if (TextUtils.isEmpty(whichReason)) {
                    etWhichReason.setError("Este campo no puede quedar vacío");
                    etWhichReason.requestFocus();
                }
            }
        });

        alert.show();
    }


    public static int flotatToDp(float value, Context context) {
        int dp = (int) (value * context.getResources().getDisplayMetrics().density);
        return dp;
    }

    public static void addCharToString(EditText editText, CharSequence charSequence, int position) {
        if (editText.getText().toString().contains(charSequence) == false) {
            if (editText.getText().length() == (position + 1)) {
                String tempText = editText.getText().toString();
                tempText = tempText.substring(0, position) + charSequence + tempText.substring(position, tempText.length());
                editText.setText(tempText);
                editText.setSelection(editText.getText().length());
            }
        }
    }

    public static void addDashToString(EditText editText, CharSequence charSequence, int position) {

        String tempText = editText.getText().toString();

        //Se obtiene un subtring solo con los caracteres que estan despues del ultimo caracter que separe
        String[] subsTempText = tempText.split((String) charSequence);

        if (subsTempText.length > 0) {
            String subTempText = subsTempText[subsTempText.length - 1];

            if (subTempText.length() >= (position + 1)) {

                //Se añade el caracter al substring
                subTempText = subTempText.substring(0, position) + charSequence + subTempText.substring(position, subTempText.length());

                //Se crea el nuevo texto, tomando el que reecibe el substring para añadir el nuevo
                tempText = tempText.substring(0, (tempText.length() - (position + 1))) + subTempText;

                editText.setText(tempText);
                editText.setSelection(editText.getText().length());

            }
        }

    }

    public static void changeFailedReasonActivity(ArrayList<CardGeneralActivity> array, String failedReason, String id) {

        int position = Utils.getPositionArrayActivitiesById(array, id);
        if (position != -1) {
            if(!TextUtils.isEmpty(failedReason)){
                array.get(position).setStrFailedReason("Motivo: " + failedReason);
            }else{
                array.get(position).setStrFailedReason("");
            }
        }

    }

    public static void changeStateActivity(ArrayList<CardGeneralActivity> array, String state, String id) {

        int position = Utils.getPositionArrayActivitiesById(array, id);
        if (position != -1) {
            array.get(position).setStrState(state);
        }

    }

    public static int getPositionArrayActivitiesById(ArrayList<CardGeneralActivity> array, String id) {
        int position = -1;

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getStrId().equals(id)) {
                position = i;
                return position;
            }
        }

        return position;
    }

    public static void changeColorTextViewIconInfoWhenEmpty(TextView textView, ImageView imageView, Context context) {
        //Se cambia el texto a color rojo
        textView.setTextColor(context.getResources().getColor(R.color.colorRed));
        if(imageView != null){
            //Se cambia el icono de in formación a uno rojo
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_info_red));
        }
    }

    /**----- CRUD GENERAL ACTIVITY -----*/
    public static void createGeneralActivityToSQLite(Context context, CardGeneralActivity generalActivity){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());
        /** El objeto se envia a la BD, aquí se desglosa para enviarla */
        db.createGeneralActivitySQLite(generalActivity);
        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static void updateGeneralActivityToSQLite(Context context, CardGeneralActivity generalActivity){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());
        /** El objeto se envia a la BD, aquí se desglosa para enviarla */
        db.updateGeneralActivitySQLite(generalActivity);
        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static boolean getGeneralActivityToSQLite(Context context, String idGeneralActivity){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());
        CardGeneralActivity generalActivity = db.getGeneralActivitySQLite(idGeneralActivity);
        /** Se cierra la conección a la BD */
        db.closeDB();

        if(generalActivity != null){
            return true;
        }

        return false;
    }

    public static void getAllGeneralActivitiesToSQLite(Context context){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());
        /** Se obtiene la información de todas las actividades generales de la base de datos */
        ArrayList<CardGeneralActivity> generalActivityArray = new ArrayList<>();
        generalActivityArray.addAll(db.getAllGeneralActivitySQLite());
        System.out.println("GET AL GENERAL ACTIVITIES");
        System.out.println("GET AL GENERAL ACTIVITIES" + generalActivityArray);

        for(int i = 0; i < generalActivityArray.size(); i++){
            CardGeneralActivity generalActivity = generalActivityArray.get(i);
                Globals.dataInfrastructureSurveyActivitiesArray.add(generalActivityArray.get(i));
                System.out.println(Globals.dataInfrastructureSurveyActivitiesArray);
            System.out.println("EN EL FOR DE GENERAL ACTIVITIES");

        }

        /** Se ordena la lista de acuerdo a la distancia */
        Collections.sort(Globals.dataInfrastructureSurveyActivitiesArray);


        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static void deleteGeneralActivityToSQLite(Context context, String id){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());
        db.deleteToGeneralActivitySQLite(id);
        db.closeDB();
    }

    public static void deleteAllGeneralActivityToSQLite(ArrayList<CardGeneralActivity> arrayList, Context context){
        for(int i = 0; i < arrayList.size(); i++){
            String id = arrayList.get(i).getStrId();
            deleteGeneralActivityToSQLite(context, id);
        }
    }

    /**----- CRUD TRANSFORMER -----*/
    public static void createTransformerActivityObjToSQLite(Context context){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());

        /** A partir del HashMap que se llena en la actividad, se crea un objeto de esa actividad */
        HashMap transformerActivityData = Globals.mapTransformerActivityData;
        DistributionTransformerActivity transformerActivityObj = new DistributionTransformerActivity(
                (String) transformerActivityData.get("idTransformerActivity"),
                (String) transformerActivityData.get("enrollment"),
                (ArrayList<Bitmap>) transformerActivityData.get("imagesBefore"),
                (Integer) transformerActivityData.get("technicalPlate"),
                (ArrayList<Bitmap>) transformerActivityData.get("imagesAfter"),
                (String) transformerActivityData.get("vegetableOil"),
                (Double) transformerActivityData.get("dbLatitude"),
                (Double) transformerActivityData.get("dbLongitude"),
                (String) transformerActivityData.get("areaType"),
                (String) transformerActivityData.get("type"),
                (String) transformerActivityData.get("pcbEquip"),
                (String) transformerActivityData.get("phaseR"),
                (String) transformerActivityData.get("phaseS"),
                (String) transformerActivityData.get("phaseT"),
                (String) transformerActivityData.get("equipState"),
                (String) transformerActivityData.get("transformerState"),
                (Date) transformerActivityData.get("fabricationDate"),
                (Integer) transformerActivityData.get("group"),
                (String) transformerActivityData.get("installationOrigin"),
                (String) transformerActivityData.get("brand"),
                (String) transformerActivityData.get("observation"),
                (String) transformerActivityData.get("activePci"),
                (String) transformerActivityData.get("nominalPower"),
                (String) transformerActivityData.get("property"),
                (String) transformerActivityData.get("phaseSequence"),
                (String) transformerActivityData.get("subnormal"),
                (String) transformerActivityData.get("primaryVoltage"),
                (String) transformerActivityData.get("secondaryVoltage"),
                (String) transformerActivityData.get("connectionType"),
                (String) transformerActivityData.get("transformerType"),
                (String) transformerActivityData.get("secondaryNetType"),
                (String) transformerActivityData.get("resourcesUse"),
                (ArrayList<Bitmap>) transformerActivityData.get("imageInstalledPlate"),
                (boolean) transformerActivityData.get("isFailed"),
                (String) transformerActivityData.get("failReason"),
                (String) transformerActivityData.get("failDetail"));

        /** El objeto se envia a la BD, aquí se desglosa para enviarla */
        db.createTransformerActivitySQLite(transformerActivityObj);

        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static void updateTransformerActivityObjToSQLite(Context context){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());

        /** A partir del HashMap que se llena en la actividad, se crea un objeto de esa actividad */
        HashMap transformerActivityData = Globals.mapTransformerActivityData;
        DistributionTransformerActivity transformerActivityObj = new DistributionTransformerActivity(
                (String)transformerActivityData.get("idTransformerActivity"),
                (String) transformerActivityData.get("enrollment"),
                (ArrayList<Bitmap>) transformerActivityData.get("imagesBefore"),
                (Integer) transformerActivityData.get("technicalPlate"),
                (ArrayList<Bitmap>) transformerActivityData.get("imagesAfter"),
                (String) transformerActivityData.get("vegetableOil"),
                (Double) transformerActivityData.get("dbLatitude"),
                (Double) transformerActivityData.get("dbLongitude"),
                (String) transformerActivityData.get("areaType"),
                (String) transformerActivityData.get("type"),
                (String) transformerActivityData.get("pcbEquip"),
                (String) transformerActivityData.get("phaseR"),
                (String) transformerActivityData.get("phaseS"),
                (String) transformerActivityData.get("phaseT"),
                (String) transformerActivityData.get("equipState"),
                (String) transformerActivityData.get("transformerState"),
                (Date) transformerActivityData.get("fabricationDate"),
                (Integer) transformerActivityData.get("group"),
                (String) transformerActivityData.get("installationOrigin"),
                (String) transformerActivityData.get("brand"),
                (String) transformerActivityData.get("observation"),
                (String) transformerActivityData.get("activePci"),
                (String) transformerActivityData.get("nominalPower"),
                (String) transformerActivityData.get("property"),
                (String) transformerActivityData.get("phaseSequence"),
                (String) transformerActivityData.get("subnormal"),
                (String) transformerActivityData.get("primaryVoltage"),
                (String) transformerActivityData.get("secondaryVoltage"),
                (String) transformerActivityData.get("connectionType"),
                (String) transformerActivityData.get("transformerType"),
                (String) transformerActivityData.get("secondaryNetType"),
                (String) transformerActivityData.get("resourcesUse"),
                (ArrayList<Bitmap>) transformerActivityData.get("imageInstalledPlate"),
                (boolean) transformerActivityData.get("isFailed"),
                (String) transformerActivityData.get("failReason"),
                (String) transformerActivityData.get("failDetail"));

        /** El objeto se envia a la BD, aquí se desglosa para enviarla */
        db.updateTransformerActivitySQLite(transformerActivityObj);

        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static void deleteTransformerActivityToSQLite(Context context, String id){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());
        db.deleteToTransformerActivity(id);
        db.closeDB();
    }

    public static void deleteAllTransformerActivityToSQLite(ArrayList<DistributionTransformerActivity> arrayList, Context context){
        for(int i = 0; i < arrayList.size(); i++){
            String id = arrayList.get(i).getStrIdTransformerActivity();
            deleteTransformerActivityToSQLite(context, id);
        }
    }

    public static void getTransformerActivity(CardGeneralActivity cardGeneralActivity, Context context){
        String transformerId = cardGeneralActivity.getStrId();
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context);
        db.getTransformerActivity(transformerId);
        db.closeDB();
    }

    public static ArrayList getAllTransformerActivitiesToSQLite(Context context){
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context);
        ArrayList<DistributionTransformerActivity> transformerActivities = db.getAllTransformerActivities();
        db.closeDB();
        return transformerActivities;
    }

    public static boolean sendDataTransformerActivityFromSQLiteToAPI(ArrayList<DistributionTransformerActivity> arrayList, Activity activity){

        try {
            for (int i = 0; i < arrayList.size(); i++){
                Globals.boolSyncroDataTransformer  = false;
                Globals.boolSyncroDataGeneral = false;

                DistributionTransformerActivity distributionTransformerActivity = arrayList.get(i);
                int pos = getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, distributionTransformerActivity.getStrIdTransformerActivity());
                CardGeneralActivity TransformerGeneral =  Globals.dataInfrastructureSurveyActivitiesArray.get(pos);
                String state = TransformerGeneral.getStrState();
                boolean isUploadAPI = Globals.dataInfrastructureSurveyActivitiesArray.get(pos).getIsUploadAPI();



                if((state.equals(Globals.strFailedState) || state.equals(Globals.strFailedStateIncomplete)) && isUploadAPI == false){
                    /** Se actualiza la actividad general unicamente*/
                    new SendGeneralActivities(activity, TransformerGeneral, distributionTransformerActivity.getBoolIsFailed(), distributionTransformerActivity.getStrFailReason(), distributionTransformerActivity.getStrFailDetail()).execute().get();

                    if(Globals.boolSyncroDataGeneral == false){
                        new SendSocket(activity, "Actividad General").execute().get();
                        return false;
                    }
                }else if (state.equals(Globals.strExecuteState) && isUploadAPI == false){
                    int capacity = Integer.parseInt(TransformerGeneral.getStrSerial().replaceAll("Capacidad ", ""));
                    /** Se actualiza la actividad general y se envia el detalle de la información recolectada*/
                    new SendGeneralActivities(activity, TransformerGeneral, distributionTransformerActivity.getBoolIsFailed(), distributionTransformerActivity.getStrFailReason(), distributionTransformerActivity.getStrFailDetail()).execute().get();
                    new SendActivitiesTransformer(activity, distributionTransformerActivity, capacity).execute().get();

                    if(Globals.boolSyncroDataTransformer  == false|| Globals.boolSyncroDataGeneral == false){
                        new SendSocket(activity, "Actividad General").execute().get();
                        new SendSocket(activity, "Actividad Transformador").execute().get();
                        return false;
                    }
                }

            }
        }catch (Exception e){}

        new SendSocket(activity, "Actividad General").execute();
        new SendSocket(activity, "Actividad Transformador").execute();

        return true;
    }

    /**-----
     *
     * CRUD LOW VOLTAGE SECTION
     *
     * -----*/
    public static void createLowVoltageSectionActivityObjToSQLite(Context context){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());

        /** A partir del HashMap que se llena en la actividad, se crea un objeto de esa actividad */
        HashMap lowVoltageSectionActivityData = Globals.mapLowVoltageSectionActivityData;
        LowVoltageSectionActivity lowVoltageSectionActivityObj = new LowVoltageSectionActivity(
                (String) lowVoltageSectionActivityData.get("idActivity"),
                (String) lowVoltageSectionActivityData.get("startSupport"),
                (String) lowVoltageSectionActivityData.get("finalSupport"),
                (String) lowVoltageSectionActivityData.get("material"),
                (String) lowVoltageSectionActivityData.get("canalization"),
                (String) lowVoltageSectionActivityData.get("nConductors"),
                (String) lowVoltageSectionActivityData.get("dispositionConductor"),
                (String) lowVoltageSectionActivityData.get("length"),
                (String) lowVoltageSectionActivityData.get("observation"),
                (String) lowVoltageSectionActivityData.get("property"),
                (String) lowVoltageSectionActivityData.get("areaType"),
                (String) lowVoltageSectionActivityData.get("conductorType1"),
                (String) lowVoltageSectionActivityData.get("conductorType2"),
                (String) lowVoltageSectionActivityData.get("conductorType3"),
                (String) lowVoltageSectionActivityData.get("neutralConductor"),
                (String) lowVoltageSectionActivityData.get("sectionType"),
                (String) lowVoltageSectionActivityData.get("resourcesUse"),
                (boolean) lowVoltageSectionActivityData.get("isFailed"),
                (String) lowVoltageSectionActivityData.get("failReason"),
                (String) lowVoltageSectionActivityData.get("failDetail"));

        /** El objeto se envia a la BD, aquí se desglosa para enviarla */
        db.createLowVoltageSectionActivitySQLite(lowVoltageSectionActivityObj);

        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static void updateLowVoltageSectionActivityObjToSQLite(Context context){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());

        /** A partir del HashMap que se llena en la actividad, se crea un objeto de esa actividad */
        HashMap lowVoltageSectionActivityData = Globals.mapLowVoltageSectionActivityData;
        LowVoltageSectionActivity lowVoltageSectionActivityObj = new LowVoltageSectionActivity(
                (String) lowVoltageSectionActivityData.get("idActivity"),
                (String) lowVoltageSectionActivityData.get("startSupport"),
                (String) lowVoltageSectionActivityData.get("finalSupport"),
                (String) lowVoltageSectionActivityData.get("material"),
                (String) lowVoltageSectionActivityData.get("canalization"),
                (String) lowVoltageSectionActivityData.get("nConductors"),
                (String) lowVoltageSectionActivityData.get("dispositionConductor"),
                (String) lowVoltageSectionActivityData.get("length"),
                (String) lowVoltageSectionActivityData.get("observation"),
                (String) lowVoltageSectionActivityData.get("property"),
                (String) lowVoltageSectionActivityData.get("areaType"),
                (String) lowVoltageSectionActivityData.get("conductorType1"),
                (String) lowVoltageSectionActivityData.get("conductorType2"),
                (String) lowVoltageSectionActivityData.get("conductorType3"),
                (String) lowVoltageSectionActivityData.get("neutralConductor"),
                (String) lowVoltageSectionActivityData.get("sectionType"),
                (String) lowVoltageSectionActivityData.get("resourcesUse"),
                (boolean) lowVoltageSectionActivityData.get("isFailed"),
                (String) lowVoltageSectionActivityData.get("failReason"),
                (String) lowVoltageSectionActivityData.get("failDetail"));

        /** El objeto se envia a la BD, aquí se desglosa para enviarla */
        db.updateLowVoltageSectionActivity(lowVoltageSectionActivityObj);

        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static void deleteLowVoltageSectionActivityToSQLite(Context context, String id){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());
        db.deleteToLowVoltageSectionActivity(id);
        db.closeDB();
    }

    public static void deleteAllLowVoltageSectionActivityToSQLite(ArrayList<LowVoltageSectionActivity> arrayList, Context context){
        for(int i = 0; i < arrayList.size(); i++){
            String id = arrayList.get(i).getStrIdActivity();
            deleteLowVoltageSectionActivityToSQLite(context, id);
        }
    }

    public static void getLowVoltageSectionActivity(CardGeneralActivity cardGeneralActivity, Context context){
        String LowVoltageSectionId = cardGeneralActivity.getStrId();
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context);
        db.getLowVoltageSectionActivity(LowVoltageSectionId);
        db.closeDB();
    }

    public static ArrayList getAllLowVoltageActivities(Context context){
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context);
        ArrayList<LowVoltageSectionActivity> lowVoltageSectionActivities = db.getAllLowVoltageSectionActivities();
        db.closeDB();
        return lowVoltageSectionActivities;
    }

    public static boolean sendDataLowVoltageSectionActivityFromSQLiteToAPI(ArrayList<LowVoltageSectionActivity> arrayList, Activity activity){


        try {
            for (int i = 0; i < arrayList.size(); i++){
                Globals.boolSyncroDataLowVoltage = false;
                Globals.boolSyncroDataGeneral = false;

                LowVoltageSectionActivity lowVoltageSectionActivity = arrayList.get(i);
                int pos = getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, lowVoltageSectionActivity.getStrIdActivity());
                CardGeneralActivity lowVoltageGeneral = Globals.dataInfrastructureSurveyActivitiesArray.get(pos);
                String state = lowVoltageGeneral.getStrState();
                boolean isUploadAPI = Globals.dataInfrastructureSurveyActivitiesArray.get(pos).getIsUploadAPI();

                if((state.equals(Globals.strFailedState) || state.equals(Globals.strFailedStateIncomplete)) && isUploadAPI == false){
                    /** Se actualiza la actividad general unicamente*/
                    new SendGeneralActivities(activity, lowVoltageGeneral, lowVoltageSectionActivity.getBoolIsFailed(), lowVoltageSectionActivity.getStrFailReason(), lowVoltageSectionActivity.getStrFailDetail()).execute().get();

                    if(Globals.boolSyncroDataGeneral == false){
                        new SendSocket(activity, "Actividad General").execute().get();

                        return false;
                    }
                }else if (state.equals(Globals.strExecuteState) && isUploadAPI == false){
                    /** Se actualiza la actividad general y se envia el detalle de la información recolectada*/
                    new SendGeneralActivities(activity, lowVoltageGeneral, lowVoltageSectionActivity.getBoolIsFailed(), lowVoltageSectionActivity.getStrFailReason(), lowVoltageSectionActivity.getStrFailDetail()).execute().get();
                    new SendActivitiesLowVoltageSection(activity, lowVoltageSectionActivity).execute().get();


                    if(Globals.boolSyncroDataLowVoltage == false || Globals.boolSyncroDataGeneral == false){
                        new SendSocket(activity, "Actividad General").execute().get();
                        new SendSocket(activity, "Actividad Baja Tension").execute().get();

                        return false;
                    }
                }
            }
        }catch (Exception e){}

        new SendSocket(activity, "Actividad General").execute();
        new SendSocket(activity, "Actividad Baja Tension").execute();

        return true;
    }

    /**-----
     *
     * CRUD LOW VOLTAGE SUPPORT
     *
     * -----*/
    public static void createLowVoltageSupportActivityObjToSQLite(Context context){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());

        /** A partir del HashMap que se llena en la actividad, se crea un objeto de esa actividad */
        HashMap lowVoltageSupportActivityData = Globals.mapLowVoltageSupportActivityData;
        LowVoltageSupportActivity lowVoltageSupportActivityObj = new LowVoltageSupportActivity(
                (String) lowVoltageSupportActivityData.get("idActivity"),
                (Integer) lowVoltageSupportActivityData.get("height"),
                (String) lowVoltageSupportActivityData.get("load"),
                (String) lowVoltageSupportActivityData.get("foundation"),
                (Integer) lowVoltageSupportActivityData.get("circuits"),
                (String) lowVoltageSupportActivityData.get("shared"),
                (String) lowVoltageSupportActivityData.get("state"),
                (String) lowVoltageSupportActivityData.get("structure"),
                (String) lowVoltageSupportActivityData.get("structureBT"),
                (String) lowVoltageSupportActivityData.get("structureMT"),
                (Double) lowVoltageSupportActivityData.get("dbLatitude"),
                (Double) lowVoltageSupportActivityData.get("dbLongitude"),
                (String) lowVoltageSupportActivityData.get("material"),
                (String) lowVoltageSupportActivityData.get("enroll"),
                (String) lowVoltageSupportActivityData.get("observation"),
                (String) lowVoltageSupportActivityData.get("pass"),
                (String) lowVoltageSupportActivityData.get("portico"),
                (String) lowVoltageSupportActivityData.get("property"),
                (String) lowVoltageSupportActivityData.get("Province"),
                (String) lowVoltageSupportActivityData.get("grounding"),
                (String) lowVoltageSupportActivityData.get("detained"),
                (String) lowVoltageSupportActivityData.get("insulatorType"),
                (String) lowVoltageSupportActivityData.get("supportType"),
                (String) lowVoltageSupportActivityData.get("areaType"),
                (String) lowVoltageSupportActivityData.get("netType"),
                (String) lowVoltageSupportActivityData.get("useResources"),
                (String) lowVoltageSupportActivityData.get("UUCC"),
                (boolean) lowVoltageSupportActivityData.get("isFailed"),
                (String) lowVoltageSupportActivityData.get("failReason"),
                (String) lowVoltageSupportActivityData.get("failDetail"));

        /** El objeto se envia a la BD, aquí se desglosa para enviarla */
        db.createLowVoltageSupportActivitySQLite(lowVoltageSupportActivityObj);

        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static void updateLowVoltageSupportActivityObjToSQLite(Context context){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());

        /** A partir del HashMap que se llena en la actividad, se crea un objeto de esa actividad */
        HashMap lowVoltageSupportActivityData = Globals.mapLowVoltageSupportActivityData;
        LowVoltageSupportActivity lowVoltageSupportActivityObj = new LowVoltageSupportActivity(
                (String) lowVoltageSupportActivityData.get("idActivity"),
                (Integer) lowVoltageSupportActivityData.get("height"),
                (String) lowVoltageSupportActivityData.get("load"),
                (String) lowVoltageSupportActivityData.get("foundation"),
                (Integer) lowVoltageSupportActivityData.get("circuits"),
                (String) lowVoltageSupportActivityData.get("shared"),
                (String) lowVoltageSupportActivityData.get("state"),
                (String) lowVoltageSupportActivityData.get("structure"),
                (String) lowVoltageSupportActivityData.get("structureBT"),
                (String) lowVoltageSupportActivityData.get("structureMT"),
                (Double) lowVoltageSupportActivityData.get("dbLatitude"),
                (Double) lowVoltageSupportActivityData.get("dbLongitude"),
                (String) lowVoltageSupportActivityData.get("material"),
                (String) lowVoltageSupportActivityData.get("enroll"),
                (String) lowVoltageSupportActivityData.get("observation"),
                (String) lowVoltageSupportActivityData.get("pass"),
                (String) lowVoltageSupportActivityData.get("portico"),
                (String) lowVoltageSupportActivityData.get("property"),
                (String) lowVoltageSupportActivityData.get("Province"),
                (String) lowVoltageSupportActivityData.get("grounding"),
                (String) lowVoltageSupportActivityData.get("detained"),
                (String) lowVoltageSupportActivityData.get("insulatorType"),
                (String) lowVoltageSupportActivityData.get("supportType"),
                (String) lowVoltageSupportActivityData.get("areaType"),
                (String) lowVoltageSupportActivityData.get("netType"),
                (String) lowVoltageSupportActivityData.get("useResources"),
                (String) lowVoltageSupportActivityData.get("UUCC"),
                (boolean) lowVoltageSupportActivityData.get("isFailed"),
                (String) lowVoltageSupportActivityData.get("failReason"),
                (String) lowVoltageSupportActivityData.get("failDetail"));

        /** El objeto se envia a la BD, aquí se desglosa para enviarla */
        db.updateLowVoltageSupportActivity(lowVoltageSupportActivityObj);

        /** Se cierra la conección a la BD */
        db.closeDB();
    }

    public static void deleteLowVoltageSupportActivityToSQLite(Context context, String id){
        /** Se inicializa la BD */
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context.getApplicationContext());
        db.deleteToLowVoltageSupportActivity(id);
        db.closeDB();
    }

    public static void deleteAllLowVoltageSupportActivityToSQLite(ArrayList<LowVoltageSectionActivity> arrayList, Context context){
        for(int i = 0; i < arrayList.size(); i++){
            String id = arrayList.get(i).getStrIdActivity();
            deleteLowVoltageSupportActivityToSQLite(context, id);
        }
    }

    public static void getLowVoltageSupportActivity(CardGeneralActivity cardGeneralActivity, Context context){
        String LowVoltageSupportId = cardGeneralActivity.getStrId();
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context);
        db.getLowVoltageSupportActivity(LowVoltageSupportId);
        db.closeDB();
    }

    public static ArrayList getAllLowVoltageSupportActivities(Context context){
        AdminSQLiteOpenHelper db = new AdminSQLiteOpenHelper(context);
        ArrayList<LowVoltageSupportActivity> lowVoltageSupportActivities = db.getAllLowVoltageSupportActivities();
        db.closeDB();
        return lowVoltageSupportActivities;
    }

    public static boolean sendDataLowVoltageSupportActivityFromSQLiteToAPI(ArrayList<LowVoltageSupportActivity> arrayList, Activity activity){

        try {
            for (int i = 0; i < arrayList.size(); i++){
                Globals.boolSyncroDataLowVoltage = false;
                Globals.boolSyncroDataGeneral = false;

                LowVoltageSupportActivity lowVoltageSupportActivity = arrayList.get(i);
                int pos = getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, lowVoltageSupportActivity.getStrIdActivity());
                CardGeneralActivity lowVoltageGeneral = Globals.dataInfrastructureSurveyActivitiesArray.get(pos);
                String state = lowVoltageGeneral.getStrState();
                boolean isUploadAPI = Globals.dataInfrastructureSurveyActivitiesArray.get(pos).getIsUploadAPI();

                if((state.equals(Globals.strFailedState) || state.equals(Globals.strFailedStateIncomplete)) && isUploadAPI == false){
                    /** Se actualiza la actividad general unicamente*/
                    new SendGeneralActivities(activity, lowVoltageGeneral, lowVoltageSupportActivity.getBoolIsFailed(), lowVoltageSupportActivity.getStrFailReason(), lowVoltageSupportActivity.getStrFailDetail()).execute().get();

                    if(Globals.boolSyncroDataGeneral == false){
                        new SendSocket(activity, "Actividad General").execute().get();

                        return false;
                    }
                }else if (state.equals(Globals.strExecuteState) && isUploadAPI == false){
                    /** Se actualiza la actividad general y se envia el detalle de la información recolectada*/
                    new SendGeneralActivities(activity, lowVoltageGeneral, lowVoltageSupportActivity.getBoolIsFailed(), lowVoltageSupportActivity.getStrFailReason(), lowVoltageSupportActivity.getStrFailDetail()).execute().get();
//                    new SendActivitiesLowVoltageSection(activity, lowVoltageSupportActivity).execute().get();

                    if(Globals.boolSyncroDataLowVoltage == false || Globals.boolSyncroDataGeneral == false){
                        new SendSocket(activity, "Actividad General").execute().get();
                        new SendSocket(activity, "Actividad Apoyo Baja Tension").execute().get();

                        return false;
                    }
                }
            }
        }catch (Exception e){}

        new SendSocket(activity, "Actividad General").execute();
        new SendSocket(activity, "Actividad Apoyo Baja Tension").execute();

        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Boolean changeButtonSelectionFromDB(ArrayList<Button> buttons, String selectedButton, HashMap dataMap, String keyWord, boolean isSelected, Context context){
        for (int i = 0; i < buttons.size(); i++) {
            if(buttons.get(i).getText().toString().equals(selectedButton)){
                buttons.get(i).setBackground(context.getResources().getDrawable(R.drawable.button_blue));
                buttons.get(i).setTextColor(context.getResources().getColor(R.color.colorWhite));
                isSelected = true;
                dataMap.put(keyWord, selectedButton);
            }else {
                if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState)){
                    buttons.get(i).setElevation(0);
                    buttons.get(i).setBackground(context.getResources().getDrawable(R.drawable.button_gray));
                    buttons.get(i).setTextColor(context.getResources().getColor(R.color.colorWhite));
                }
            }
        }
        return isSelected;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void changeButtonStyleDisable(Button disableButton, Context context){
        disableButton.setElevation(0);
        disableButton.setBackground(context.getResources().getDrawable(R.drawable.button_gray));
        disableButton.setTextColor(context.getResources().getColor(R.color.colorWhite));
    }

    public static void changeEditTextStyleDisable(EditText editText, Context context){
        editText.setEnabled(false);
        editText.setBackground(context.getResources().getDrawable(R.drawable.input_general_disable));
    }

    public static void changeSpinnerStyleDisable(Spinner spinner, Context context){
        spinner.setEnabled(false);
        spinner.setBackground(context.getResources().getDrawable(R.drawable.spinner_shape_disable));
    }

    public static double getDistanceBetween(double latitudeStart, double longitudeStart, double latitudeEnd, double longitudeEnd){
        float results[] = new float[10];
        Location.distanceBetween(latitudeStart, longitudeStart, latitudeEnd, longitudeEnd, results);

        double distanceBetween = results[0];
        return distanceBetween;

    }

    /** Retorna una lista de card general filtrada por un valor de titulo actividad*/
    public static ArrayList<CardGeneralActivity> filterByActivityTitle (ArrayList<CardGeneralActivity> array, String value){
        ArrayList<CardGeneralActivity> filterArray = new ArrayList<>();
        for(int i = 0; i < array.size(); i++){
            if(array.get(i).getStrActivityTitle().equals(value)){
                filterArray.add(array.get(i));
            }
        }
        return filterArray;
    }

    /** Retorna una lista de card general filtrada por un valor de estado*/
    public static ArrayList<CardGeneralActivity> filterByStateActivity (ArrayList<CardGeneralActivity> array, String value){
        ArrayList<CardGeneralActivity> filterArray = new ArrayList<>();
        for(int i = 0; i < array.size(); i++){
            if(array.get(i).getStrState().equals(value)){
                filterArray.add(array.get(i));
            }
        }
        return filterArray;
    }

    /** Retorna el número de actividades ejecutadas dentro de un array de cards*/
    public static int numCardsExecuted(ArrayList<CardGeneralActivity> array){
        int numExecuted = 0;

        for(int i = 0; i < array.size(); i++){
            if(array.get(i).getStrState().equals(Globals.strExecuteState)){
                numExecuted++;
            }
        }

        return numExecuted;
    }

    public static void changeColorButtonGroup(ArrayList<Button> buttonList, Button clickedButton, int colorTextUnselected, Drawable backgroundUnselected, int colorTextSelected, Drawable backgroundSelected) {
        for (Button button: buttonList
        ) {
            //Si no esta seleccionado se pone unselected style
            button.setTextColor(colorTextUnselected);
            button.setBackground(backgroundUnselected);
        }
        //Se cambia el color del boton clickeado a azul con letra blanca
        if(clickedButton != null){
            clickedButton.setTextColor(colorTextSelected);
            clickedButton.setBackground(backgroundSelected);
        }
    }
    /** Escuchar cambios de textos en edit text*/
    public static void addListenerEditText(EditText editText, final HashMap map, final String nameMap, final String type){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    if(type.equals("string")){
                        map.put(nameMap, s.toString());
                    }else if(type.equals("int")){
                        map.put(nameMap, Integer.parseInt(s.toString()));
                    }else if(type.equals("double")){
                        map.put(nameMap, Double.parseDouble(s.toString()));
                    }else if (type.equals("location")){
                        String location = s.toString();
                        String[] locationSplit = location.split(", ");
                        map.put("dbLatitude", Double.parseDouble(locationSplit[0]));
                        map.put("dbLongitude", Double.parseDouble(locationSplit[1]));
                    }
                }else{
                    map.put(nameMap, null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /** Escuchar cambios de textos en spinner*/
    public static void addListenerSpinner(final Spinner spinner, final HashMap map, final String nameMap){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = spinner.getItemAtPosition(position).toString();
                map.put(nameMap, itemSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /** Se obtiene la fecha y hora actual */
    public static Date currentTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateFormatted = dateFormat.format(date);
        try {
            date = dateFormat.parse(dateFormatted);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /** Se obtiene el centro de 2 puntos en el mapa*/
    public static LatLng getCenterTwoPoints(double lat1, double lon1, double lat2, double lon2){
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);

        double Bx = Math.cos(lat2) * Math.cos(dLon);
        double By = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
        double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

        LatLng latLng3 = new LatLng(lat3, lon3);
        return latLng3;
    }

    /** Se validan que no haya actividades por hacer*/
    public static boolean existToDoActivities(ArrayList<CardGeneralActivity> arrayList){

        /** Se recorre en busca de una actividad por hacer y si existe retorna false*/
        for (int i = 0; i < arrayList.size(); i++){
            CardGeneralActivity activity = arrayList.get(i);
            if(activity.getStrState().equals(Globals.strTodoState)){
                return true;
            }
        }

        return false;
    }





}
