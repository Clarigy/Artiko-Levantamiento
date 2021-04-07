package com.example.artikolevyinf.Fragments.Map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.InfrastructureActivityFragment;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapInfrastructureFragment extends Fragment {

    public MapInfrastructureFragment() {
        // Required empty public constructor
    }

    /** Mapa */
    GoogleMap gMap;
    ArrayList<MarkerOptions> markersArray;

    /** Lista de actividades */
    ArrayList<CardGeneralActivity> activitiesArray;

    /** Botones */
    Button btnSupport, btnAirSection, btnSubstation, btnTransformer;
    Button btnToDo, btnExecuted, btnFailed, btnAll, btnStartPoint;

    TextView tvCancelEntity;

    ArrayList<Button> buttonsFilterArray, buttonsEntityArray;
    int intTextUnselectedFilter, intTextSelectedFilter, intTextSelectedEntity, intTextUnselectedEntity;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter, dwBackgroundUnselectedEntity, dwBackgroundSelectedEntity;

    /** que tipo de entidad selecciono el usuario*/
    String srtEntityType = "";

    /** Cantidad de apoyos seleccionados para hacer un tramo aereo*/
    ArrayList<Marker> markersSupportSelectedArray = new ArrayList<>();

    /** Tipos de entidades*/
    String strAirSectionEntity = "AirSection";
    String strTransformerEntity = "transformer";
    String strFilterSelected = Globals.strAllActivities;

    /** Ubicación del usuario*/
    LatLng latLngCurrentLocation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map_infrastructure, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map_infrastructure);

        activitiesArray = Globals.dataMapActivitiesArray;
        markersArray = new ArrayList<>();

        btnToDo = rootView.findViewById(R.id.btn_to_be_checked_map_infrastructure);
        btnExecuted = rootView.findViewById(R.id.btn_checked_map_infrastructure);
        btnFailed = rootView.findViewById(R.id.btn_failed_map_infrastructure);
        btnAll = rootView.findViewById(R.id.btn_all_map_infrastructure);

        btnSupport = rootView.findViewById(R.id.btn_support_map_infrastructure);
        btnAirSection = rootView.findViewById(R.id.btn_air_section_map_infrastructure);
        btnSubstation = rootView.findViewById(R.id.btn_substation_map_infrastructure);
        btnTransformer = rootView.findViewById(R.id.btn_transformer_map_infrastructure);
        btnStartPoint = rootView.findViewById(R.id.btn_start_point_map_infrastructure);

        /** Botones de filtros */

        buttonsFilterArray = new ArrayList<>();
        buttonsFilterArray.add(btnToDo);
        buttonsFilterArray.add(btnExecuted);
        buttonsFilterArray.add(btnFailed);
        buttonsFilterArray.add(btnAll);

        tvCancelEntity = rootView.findViewById(R.id.tv_cancel_entity_map_infrastructure);
        /**Se pinta la linea inferior del texto*/
        tvCancelEntity.setPaintFlags(tvCancelEntity.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        btnToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeColorButtonGroup(buttonsFilterArray, btnToDo, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                strFilterSelected = Globals.strTodoState;
                applyFilterStateActivities(strFilterSelected);
            }
        });

        btnExecuted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeColorButtonGroup(buttonsFilterArray, btnExecuted, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                strFilterSelected = Globals.strExecuteState;
                applyFilterStateActivities(strFilterSelected);
            }
        });

        btnFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeColorButtonGroup(buttonsFilterArray, btnFailed, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                strFilterSelected = Globals.strFailedState;
                applyFilterStateActivities(strFilterSelected);
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.changeColorButtonGroup(buttonsFilterArray, btnAll, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                strFilterSelected = Globals.strAllActivities;
                applyFilterStateActivities(strFilterSelected);
            }
        });


        /** Botones de entidades */

        buttonsEntityArray = new ArrayList<>();
        buttonsEntityArray.add(btnSupport);
        buttonsEntityArray.add(btnAirSection);
        buttonsEntityArray.add(btnSubstation);
        buttonsEntityArray.add(btnTransformer);

        intTextSelectedEntity = getResources().getColor(R.color.colorBlue);
        intTextUnselectedEntity = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedEntity = getResources().getDrawable(R.drawable.button_white_stroke_blue);
        dwBackgroundUnselectedEntity = getResources().getDrawable(R.drawable.unselected_button);

        btnAirSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCancelEntity.setVisibility(View.VISIBLE);
                Utils.changeColorButtonGroup(buttonsEntityArray, btnAirSection, intTextUnselectedEntity, dwBackgroundUnselectedEntity, intTextSelectedEntity, dwBackgroundSelectedEntity);
                Utils.toastMessage("Selecciona dos apoyos para agregar el tramo aéreo.", 1, getContext());
                airSectionEdition();

                /** Se ajusta el zoom */
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngCurrentLocation, 30));
            }
        });

        btnTransformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                srtEntityType = strTransformerEntity;
                tvCancelEntity.setVisibility(View.VISIBLE);
                Utils.toastMessage("Selecciona un lugar para agregar un transformador.", 1, getContext());
                Utils.changeColorButtonGroup(buttonsEntityArray, btnTransformer, intTextUnselectedEntity, dwBackgroundUnselectedEntity, intTextSelectedEntity, dwBackgroundSelectedEntity);

                /** Se ajusta el zoom */
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngCurrentLocation, 30));
            }
        });

        /** Cancelar la entidad que haya seleccionado*/
        tvCancelEntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Se llaman de nuevo todas las actividades*/
                applyFilterStateActivities(strFilterSelected);
                /** Se actualiza la entidad seleccionada*/
                srtEntityType = "";
                Utils.changeColorButtonGroup(buttonsEntityArray, null, intTextUnselectedEntity, dwBackgroundUnselectedEntity, intTextSelectedEntity, dwBackgroundSelectedEntity);
                /** El texto se vuelve invisible de nuevo*/
                tvCancelEntity.setVisibility(View.INVISIBLE);
            }
        });

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            //@SuppressLint("MissingPermission")
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                gMap = googleMap;

                /** Quitar las etiquetas de restaurantes del mapas*/
                gMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getContext(), R.raw.style_json));

                HashMap map = Utils.getCurrentLocation(getActivity(), getContext());
                if (map != null) {
                    Double latitude = (Double) map.get("latitude");
                    Double longitude = (Double) map.get("longitude");

                    LatLng latLng = new LatLng(latitude, longitude);

                    /** Se ajusta el mapa en la ubicación actual */
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                    gMap.getUiSettings().setZoomControlsEnabled(true);
                    if (ActivityCompat.checkSelfPermission( getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    gMap.setMyLocationEnabled(true);
                }

                /** Añadir actividades al mapa */
                getActivities();

                /** Se valida si no hay filtros activos*/
                //applyFilter("All");

                if (gMap != null) {
                    gMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                        @Override
                        public void onMyLocationChange(Location location) {
                            /** La camara sigue la ubicación del usuario */
                            latLngCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
//                            float zoom = gMap.getCameraPosition().zoom;
//                            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
                        }
                    });

                    gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(final LatLng latLng) {
                            if(srtEntityType.equals(strTransformerEntity)){
                                /** Funciónes que se enviara a la ventana emergente */
                                Callable<Void> functionPositive = new Callable<Void>() {
                                    public Void call() {
                                        /** finaliza la tarea */
                                        try {
                                            createTransformerActivity(latLng);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        return null;
                                    }
                                };

                                Callable<Void> functionNegative = new Callable<Void>() {
                                    public Void call() {
                                        /** Se ajusta el zoom */
                                        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngCurrentLocation,20));

                                        /** El texto se vuelve invisible de nuevo*/
                                        tvCancelEntity.setVisibility(View.INVISIBLE);

                                        /** finaliza la tarea */
                                        /** Se deselecciona la opción de la entidad*/
                                        srtEntityType = "";
                                        Utils.changeColorButtonGroup(buttonsEntityArray, null, intTextUnselectedEntity, dwBackgroundUnselectedEntity, intTextSelectedEntity, dwBackgroundSelectedEntity);
                                        return null;
                                    }
                                };

                                Utils.alertInformation("Importante", "Has agregado un nuevo transformador en la ubicación seleccionada. Presiona aceptar para tomar los datos.", getContext(), getActivity(), functionPositive, functionNegative);
                            }
                        }
                    });

                    gMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {
                            if(srtEntityType.equals(strAirSectionEntity)){

                                /** Se cambia el marcador seleccionado y se agrega a la lista*/
                                if(marker.getTitle().equals(Globals.strLowVoltageSupport)){
                                    BitmapDescriptor icon = Utils.vectorToBitmapBitmapDescriptorWithOutColor(getContext(), R.drawable.ic_support_selected_map);
                                    markersSupportSelectedArray.add(marker);
                                    marker.setIcon(icon);
                                }

                                /** Si ya hay 2 marcadores seleccionado se abre la emergente*/
                                if(markersSupportSelectedArray.size() == 2){
                                    /** Funciónes que se enviara a la ventana emergente */
                                    Callable<Void> functionPositive = new Callable<Void>() {
                                        public Void call() {
                                            /** finaliza la tarea */
                                            createAirSectionActivity();
                                            return null;
                                        }
                                    };

                                    /** Función que se enviara a la ventana emergente */
                                    Callable<Void> functionNegative = new Callable<Void>() {
                                        public Void call() {
                                            /** Se ajusta el zoom */
                                            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngCurrentLocation,20));
                                            /** finaliza la tarea */
                                            airSectionEdition();
                                            return null;
                                        }
                                    };

                                    Utils.alertInformation("Importante", "Has agregado un nuevo tramo con los puntos seleccionados. Presiona aceptar para tomar los datos.", getContext(), getActivity(), functionPositive, functionNegative);
                                }
                            }

                            return showMarkerInfo(marker);
                        }
                    });

                    gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            String[] information = marker.getSnippet().split("\n");

                            if(srtEntityType.equals("")){
                                /** Información para abrir el detalle */
                                String id = information[0];
                                String CardActivityTitle = marker.getTitle();
                                String title = information[1];
                                String name = information[2];
                                String address = information[3];
                                String serial = information[4];
                                String order = information[5];
                                String state = information[6];
                                String type = information[7];
                                String failedReason = information[8];
                                double distance = Double.parseDouble(information[9]);
                                Double latitude2 = Double.parseDouble(information[10]);
                                Double longitude2 = Double.parseDouble(information[11]);
                                boolean isUpload = Boolean.parseBoolean(information[12]);
                                boolean isCreated = Boolean.parseBoolean(information[13]);

                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                Date starExecuted = new Date();
                                Date finishExecuted = new Date();
                                Date starFailed = new Date();
                                Date finishFailed = new Date();

                                try {
                                    starExecuted = dateFormat.parse(information[14]);
                                    finishExecuted = dateFormat.parse(information[15]);
                                    starFailed = dateFormat.parse(information[16]);
                                    finishFailed = dateFormat.parse(information[17]);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                double latitude = marker.getPosition().latitude;
                                double longitude = marker.getPosition().longitude;

                                Log.d("FFF", id);
                                Log.d("FFF", String.valueOf(isCreated));
                                if(type.equals(Globals.strInfrastructureSurveyActivities)){

                                    CardGeneralActivity infoTransformer = new CardGeneralActivity(id, CardActivityTitle, title, name, serial, order, address, latitude, longitude, state, type, failedReason, distance, latitude2, longitude2, isUpload, starExecuted, finishExecuted, starFailed, finishFailed, isCreated);
                                    Globals.cardGeneralActivitySelected = infoTransformer;
                                    openActivityFragment(infoTransformer, "", "");

                                }
                            }

                        }
                    });
                }
            }
        });

        return rootView;
    }

    private void getActivities(){
        /** Se cargn las actividades*/
        activitiesArray.clear();
        activitiesArray.addAll(Globals.dataInfrastructureSurveyActivitiesArray);
        readActivities();
    }

    private void readActivities(){
        /** Limpia el mapa */
        gMap.clear();
        markersArray.clear();

        /** Recorre las actividades y añade marcadores */
        for(int i = 0; i < activitiesArray.size(); i++){
            CardGeneralActivity card = activitiesArray.get(i);
            String activityTitle = card.getStrActivityTitle();
            String id = card.getStrId();
            String title = card.getStrTitle();
            String name = card.getStrName();
            String address = card.getStrAddress();
            String serial = card.getStrSerial();
            String order = card.getStrOrder();
            double latitude = card.getDbLatitude();
            double longitude = card.getDbLongitude();
            String state = card.getStrState();
            String type = card.getStrType();
            String failedReason = card.getStrFailedReason();
            double distance = card.getDbDistance();

            double latitude2 = card.getDbLatitude2();
            double longitude2 = card.getDbLongitude2();

            boolean isUpload = card.getIsUploadAPI();
            boolean isCreated = card.getIsCreated();

            Date starExecuted = card.getDateStartExecuted();
            Date finishExecuted = card.getDateFinishExecuted();
            Date starFailed = card.getDateStartFailed();
            Date finishFailed = card.getDateFinishFailed();

            String titleCard = activityTitle;
            String snipet = id + "\n" + title + "\n" +name + "\n" + address + "\n" + serial + "\n" + order + "\n" + state + "\n" + type + "\n" + failedReason + "\n" + distance + "\n" + latitude2 + "\n" + longitude2 + "\n" + isUpload + "\n" + isCreated + "\n" + starExecuted + "\n" + finishExecuted + "\n" + starFailed + "\n" + finishFailed;

            addMarker(latitude, longitude, titleCard, snipet, activityTitle);
        }
    }

    private void addMarker(double latitude, double longitude, String title, String snipet, String activityTitle){
        /** Se inicializan la posición del marcador */
        LatLng latLng = new LatLng(latitude, longitude);
        /** Inicializa el marcador */
        MarkerOptions marker = new MarkerOptions();
        /** Se ajustan los parametros */
        marker.position(latLng);
        /** Se cambia el titulo */
        marker.title(title);
        /** Se cambia el contenido */
        marker.snippet(snipet);
        /** Se cambia el icono del marcador */
        BitmapDescriptor icon = null;
        /** Si el icono es de scr */

        /** Obtener el estado de la actividad del snipet */
        String[] information = marker.getSnippet().split("\n");

        /** Información para abrir el detalle */
        String state = information[6];

        /** Agregar el icono de acuerdo al tipo de actividad*/
        switch (activityTitle){
            case "TRANSFORMADOR DE DISTRIBUCIÓN":
                /** Se pregunta por el estado de la actividad */
                if(state.equals(Globals.strTodoState)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithOutColor(getContext(), R.drawable.ic_transformer_blue_map);
                }else if(state.equals(Globals.strExecuteState)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithOutColor(getContext(), R.drawable.ic_transformer_green_map);
                }else if(state.equals(Globals.strFailedState) || state.equals(Globals.strFailedStateIncomplete)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithOutColor(getContext(), R.drawable.ic_transformer_red_map);
                }
                break;
            case "TRAMOS BAJA TENSIÓN":

                LatLng point1 = new LatLng(latitude, longitude);
                double latitude2 = Double.parseDouble(information[10]);
                double longitude2 = Double.parseDouble(information[11]);
                LatLng point2 = new LatLng(latitude2, longitude2);

                /** Se crea la linea y se añade al mapa*/
                PolylineOptions options = new PolylineOptions().add(point1).add(point2).width(4f).color(R.color.colorBlueDark);
                Polyline polyline = gMap.addPolyline(options);

                /** Se ubica el icono en centro de la linea creada*/
                marker.position(getPolylineCentroid(polyline));

                if(state.equals(Globals.strTodoState)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithOutColor(getContext(), R.drawable.ic_air_section_failed_map);
                }else if(state.equals(Globals.strExecuteState)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithOutColor(getContext(), R.drawable.ic_air_section_checked_map);
                }else if(state.equals(Globals.strFailedState) || state.equals(Globals.strFailedStateIncomplete)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithOutColor(getContext(), R.drawable.ic_air_section_failed_map);
                }
                break;
            case "APOYO DE BAJA TENSIÓN":
                if(state.equals(Globals.strTodoState)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithColor(getContext(), R.drawable.ic_support_map, R.color.colorBlue);
                }else if(state.equals(Globals.strExecuteState)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithColor(getContext(), R.drawable.ic_support_map, R.color.colorGreen);
                }else if(state.equals(Globals.strFailedState) || state.equals(Globals.strFailedStateIncomplete)){
                    icon = Utils.vectorToBitmapBitmapDescriptorWithColor(getContext(), R.drawable.ic_support_map, R.color.colorRed);
                }
                break;
            case "CAJA SUBTERRÁNEA":

                break;
        }

        marker.icon(icon);

        markersArray.add(marker);

        /** Se añade al mapa */
        gMap.addMarker(marker);
    }

    public View showMarkerInfo(Marker marker){
        // Getting view from the layout file infowindowlayout.xml
        View rootView = getLayoutInflater().inflate(R.layout.info_windows_map, null);
        int width = Utils.flotatToDp(250, getContext());
        rootView.setLayoutParams(new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT));

        LatLng latLng = marker.getPosition();

        TextView tvTitle = rootView.findViewById(R.id.title_card_map);
        View view = rootView.findViewById(R.id.view_map);
        CardView line = rootView.findViewById(R.id.lineColor_map);
        TextView tvName = rootView.findViewById(R.id.name_card_map);
        TextView tvAddress = rootView.findViewById(R.id.address_card_map);
        TextView tvSerial = rootView.findViewById(R.id.serial_card_map);
        TextView tvOrder = rootView.findViewById(R.id.order_card_map);

        String title = marker.getTitle();
        String[] information = marker.getSnippet().split("\n");

        /** Se actualizan los textview de acuerdo al tipo */
        tvTitle.setText(title);
        tvName.setText(information[2]);
        tvAddress.setText(information[3]);
        tvSerial.setText(information[4]);
        tvOrder.setWidth(0);
        tvOrder.setHeight(0);

        int color = getContext().getResources().getColor(R.color.colorBlue);

        /** Se cambia el color */
        if(information[6].equals(Globals.strFailedState)){
            color = getContext().getResources().getColor(R.color.colorRed);
        }else if(information[6].equals(Globals.strExecuteState)){
            color = getContext().getResources().getColor(R.color.colorGreen);
        }

        line.setCardBackgroundColor(color);
        tvTitle.setTextColor(color);

        return rootView;
    }

    public void airSectionEdition(){
        markersSupportSelectedArray.clear();
        srtEntityType = strAirSectionEntity;

        /** Se dejan visibles solo las actividades de apoyo de baja tensión*/
        ArrayList<CardGeneralActivity> currentActivities = new ArrayList<>();
        currentActivities.addAll(activitiesArray);
        activitiesArray.clear();
        for(int i = 0; i < currentActivities.size(); i++){
            CardGeneralActivity card = currentActivities.get(i);
            if(card.getStrActivityTitle().equals(Globals.strLowVoltageSupport) || card.getStrActivityTitle().equals(Globals.strLowVoltageSection)){
                activitiesArray.add(card);
            }
        }

        readActivities();
    }

    public void createAirSectionActivity(){
        /** Ubicación de los puntos seleccionados*/
        LatLng point1 = markersSupportSelectedArray.get(0).getPosition();
        LatLng point2 = markersSupportSelectedArray.get(1).getPosition();

        String snippet1 = markersSupportSelectedArray.get(0).getSnippet();
        String[] info1 = snippet1.split("\n");
        String id1 = info1[0];

        String snippet2 = markersSupportSelectedArray.get(1).getSnippet();
        String[] info2 = snippet2.split("\n");
        String id2 = info2[0];


        /** Se crea la nueva actividad y se guarda en globals*/
        HashMap map = Utils.getCurrentLocation(getActivity(), getContext());
        if(map != null){
            Double latitudeStart = (Double) map.get("latitude");
            Double longitudeStart = (Double) map.get("longitude");
            double distance = Utils.getDistanceBetween(latitudeStart, longitudeStart, point1.latitude, point1.longitude);
            double distance2 = Utils.getDistanceBetween(latitudeStart, longitudeStart, point2.latitude, point2.longitude);
            double distanceProm = (distance + distance2)/2;

            /** Se guarda la hora min y seg cuando se abre la actividad */
            Date dateStartActivity = Utils.currentTime();
            Globals.dateStartActivity = dateStartActivity;

            Date today = Utils.currentTime();
            String id = String.valueOf(today.getDay())+""+ String.valueOf(today.getMonth())+""+ String.valueOf(today.getYear())+""+ String.valueOf(today.getTime());
            CardGeneralActivity activity = new CardGeneralActivity(id, Globals.strLowVoltageSection,"", "No. Serie "+id,"","","", point1.latitude, point1.longitude, Globals.strTodoState, Globals.strInfrastructureSurveyActivities, "", distanceProm, point2.latitude, point2.longitude, false, null, null, null, null, true);
            Utils.createGeneralActivityToSQLite(getContext(), activity);
            Globals.dataInfrastructureSurveyActivitiesArray.add(activity);
            /** Se actualizan la información de card en general activities */
            Utils.getActivities();

            /** Se crea la información de la actividad para enviar el punto de apoyo inicial y final */
            createLowVoltageSectionActivitySQLite(id, id1, id2);

            /** Abrir la actividad*/
            Globals.cardGeneralActivitySelected = activity;
            openActivityFragment(activity, id1, id2);
        }

        /** Se vuelve a cargar todas las actividade*/
        getActivities();

        /** Se deselecciona la opción de la entidad*/
        srtEntityType = "";
        Utils.changeColorButtonGroup(buttonsEntityArray, null, intTextUnselectedEntity, dwBackgroundUnselectedEntity, intTextSelectedEntity, dwBackgroundSelectedEntity);

        /** Se limpia la lista que guarda los marcadores seleccionados */
        markersSupportSelectedArray.clear();
    }

    private void createLowVoltageSectionActivitySQLite(String idActivity, String startSupport, String finalSupport){
        Globals.mapLowVoltageSectionActivityData.put("idActivity", idActivity);
        Globals.mapLowVoltageSectionActivityData.put("startSupport", startSupport);
        Globals.mapLowVoltageSectionActivityData.put("finalSupport", finalSupport);
        Globals.mapLowVoltageSectionActivityData.put("isFailed", false);
        Globals.mapLowVoltageSectionActivityData.put("failReason", "");
        Globals.mapLowVoltageSectionActivityData.put("failDetail", "");
        Utils.createLowVoltageSectionActivityObjToSQLite(getContext());
        /** Se limpia el mapa de información de la actividad */
        Globals.mapLowVoltageSectionActivityData = new HashMap();
    }

    public void createTransformerActivity(LatLng location) throws IOException {

        Double latitudeStart = location.latitude;
        Double longitudeStart = location.longitude;
        double distance = Utils.getDistanceBetween(latitudeStart, longitudeStart, latitudeStart, longitudeStart);

        /** Se ajusta el mapa en la ubicación actual */
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 30));

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        addresses = geocoder.getFromLocation(latitudeStart, longitudeStart, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        String address = addresses.get(0).getThoroughfare() + " # " + addresses.get(0).getFeatureName();

        /** Se guarda la hora min y seg cuando se abre la actividad */
        Date dateStartActivity = Utils.currentTime();
        Globals.dateStartActivity = dateStartActivity;

            Date today = Utils.currentTime();
            String id = String.valueOf(today.getDay())+""+ String.valueOf(today.getMonth())+""+ String.valueOf(today.getYear())+""+ String.valueOf(today.getTime());
            CardGeneralActivity activity = new CardGeneralActivity(id, Globals.strTransformer,"", "No. Serie "+id,"","",address, latitudeStart, longitudeStart, Globals.strTodoState, Globals.strInfrastructureSurveyActivities, "", distance, false, null, null, null, null, true);
            Utils.createGeneralActivityToSQLite(getContext(), activity);
            Globals.dataInfrastructureSurveyActivitiesArray.add(activity);
            /** Se actualizan la información de card en general activities */
            Utils.getActivities();

        /** Abrir la actividad*/
        Globals.cardGeneralActivitySelected = activity;
        openActivityFragment(activity, "", "");

        /** Se vuelve a cargar todas las actividade*/
        getActivities();

        /** Se deselecciona la opción de la entidad*/
        srtEntityType = "";
        Utils.changeColorButtonGroup(buttonsEntityArray, null, intTextUnselectedEntity, dwBackgroundUnselectedEntity, intTextSelectedEntity, dwBackgroundSelectedEntity);
    }

    public LatLng getPolylineCentroid(Polyline p) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i = 0; i < p.getPoints().size(); i++){
            builder.include(p.getPoints().get(i));
        }
        LatLngBounds bounds = builder.build();
        return bounds.getCenter();
    }

    public void applyFilterStateActivities(String stateSelected){
        ArrayList<CardGeneralActivity> currentActivities = new ArrayList<>();

        /** Si se oprimio el boton cargar denuevo todas las actividades*/
        getActivities();
        /** Se deselecciona la opción de la entidad*/
        srtEntityType = "";
        Utils.changeColorButtonGroup(buttonsEntityArray, null, intTextUnselectedEntity, dwBackgroundUnselectedEntity, intTextSelectedEntity, dwBackgroundSelectedEntity);

        currentActivities.addAll(activitiesArray);
        activitiesArray.clear();

        if(stateSelected.equals(Globals.strTodoState)){
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strTodoState));
        }else if(stateSelected.equals(Globals.strExecuteState)){
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strExecuteState));
        }else if(stateSelected.equals(Globals.strFailedState)){
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strFailedState));
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strFailedStateIncomplete));
        }else if(stateSelected.equals(Globals.strAllActivities)){
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strTodoState));
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strExecuteState));
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strFailedState));
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strFailedStateIncomplete));
        }

        readActivities();
    }

    public void openActivityFragment(CardGeneralActivity card, String idMarker1, String idMarker2){
        /** Se crea un fragment del tipo transformerActivitiesFragment */
        InfrastructureActivityFragment infrastructureActivityFragment = new InfrastructureActivityFragment();

        /** Se crea un objeto a partir de la información de la card seleccionada */
        Globals.cardGeneralActivitySelected = card;

        /** Se envian argumentos al nuevo fragment */
        Bundle bundle = new Bundle();
        switch (card.getStrActivityTitle()){
            case "TRANSFORMADOR DE DISTRIBUCIÓN":
                bundle.putInt("totalPages", 8);
                bundle.putInt("activityPage", 1);
                Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{ add(false); add(false); add(false); add(false); add(false); add(false); add(false); add(false); }};
                break;
            case "CENTRO DE TRANSFORMACIÓN":
                bundle.putInt("totalPages", 1);
                bundle.putInt("activityPage", 2);
                Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{ add(false); }};
                break;
            case "APOYO DE BAJA TENSIÓN":
                bundle.putInt("totalPages", 5);
                bundle.putInt("activityPage", 3);
                Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{ add(false); add(false); add(false); add(false); add(false); }};
                break;
            case "CAJA SUBTERRÁNEA":
                bundle.putInt("totalPages", 2);
                bundle.putInt("activityPage", 4);
                Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{ add(false); add(false);}};
                break;
            case "TRAMOS BAJA TENSIÓN":
                bundle.putInt("totalPages", 5);
                bundle.putInt("activityPage", 5);
                Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{ add(false); add(false); add(false); add(false); add(false); }};
                Globals.strStartSupportLowVoltageSection = idMarker1;
                Globals.strFinalSupportLowVoltageSection = idMarker2;
                break;
        }
        bundle.putSerializable("data", (Serializable) card);
        infrastructureActivityFragment.setArguments(bundle);

        /** Se trae la información de las actividad seleccionada que no esta por hacer (Fallidas, Fallidas incompletas, Ejecutadas) */
        /** Se trae la información de las actividad seleccionada que no esta por hacer (Fallidas, Fallidas incompletas, Ejecutadas) */
        if (!card.getStrState().equals(Globals.strTodoState)) {
            /** Se trae la información de la actividad de transformador de distribución */
            if (card.getStrActivityTitle().equals(Globals.strTransformer)) {
                Utils.getTransformerActivity(card, getContext());
            }
        }
        if(card.getStrActivityTitle().equals(Globals.strLowVoltageSection)){
            Utils.getLowVoltageSectionActivity(card, getContext());
        }

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, infrastructureActivityFragment, "Infrastructure")
                .addToBackStack("Infrastructure").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}
