package com.example.artikolevyinf.Fragments.Map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Controller.SQLite.AdminSQLiteOpenHelper;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;
import com.google.android.gms.maps.CameraUpdate;
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

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements SearchView.OnQueryTextListener {

    public MapFragment() {
        // Required empty public constructor
    }

    /** Mapa */
    GoogleMap gMap;
    ArrayList<MarkerOptions> markersArray;

    TextView tvUnderlineText, tvFilterApply, tvFilterApplyTitle;

    /** Buscador */
    SearchView searchView;

    /** Lista de actividades */
    ArrayList<CardGeneralActivity> activitiesArray;

    /** Botones */
    Button btActivitiesFilter, btSubActivitiesFilter, btStateActivitiesFilter, btOrderRoute, btSearch;

    /** boolean */
    boolean isSearch = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

        activitiesArray = Globals.dataMapActivitiesArray;
        markersArray = new ArrayList<>();

        /** Se llaman los elementos gráficos de la interfaz */
        tvUnderlineText = rootView.findViewById(R.id.underlineText_map);
        tvFilterApply = rootView.findViewById(R.id.tv_filter_apply);
        tvFilterApply.setVisibility(View.INVISIBLE);
        tvFilterApplyTitle = rootView.findViewById(R.id.tv_filter_apply_title);
        tvFilterApplyTitle.setVisibility(View.INVISIBLE);
        searchView = rootView.findViewById(R.id.sv_search_map);
        searchView.setVisibility(View.INVISIBLE);

        /** Se pinta la linea inferior del texto */
        tvUnderlineText.setPaintFlags(tvUnderlineText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        /** Se abre la interfaz con los filtros aplicados*/
        tvUnderlineText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFilterActivitiesFragment fragment = new MapFilterActivitiesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "Map")
                        .addToBackStack("Map").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        });

        /** Se inicializa el listener del search */
        initListener();

        /** Inizializan os botones */
        btActivitiesFilter = rootView.findViewById(R.id.btn_to_be_checked_map_infrastructure);
        btActivitiesFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Función que se enviara a la ventana emergente */
                Callable<Void> function = new Callable<Void>() {
                    public Void call() {
                        applyFilterActivities("All");
                        return null;
                    }
                };

                /** Se abre la ventana emergente */
                Utils.alertFilterMap("SCR", "Revisiones e Inspecciones BT", "Revisiones e Inspecciones MT", getContext(), getActivity(), function, "Activities");
            }
        });

        btSubActivitiesFilter = rootView.findViewById(R.id.btn_checked_map_infrastructure);
        btSubActivitiesFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Función que se enviara a la ventana emergente */
                Callable<Void> function = new Callable<Void>() {
                    public Void call() {
                        applyFilterSubActivities(true);
                        return null;
                    }
                };

                /** Se abre la ventana emergente */
                Utils.alertFilterMap("Suspensión", "Corte", "Reconexión", getContext(), getActivity(), function, "subActivities");
            }
        });

        btStateActivitiesFilter = rootView.findViewById(R.id.btn_all_map_infrastructure);
        btStateActivitiesFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Función que se enviara a la ventana emergente */
                Callable<Void> function = new Callable<Void>() {
                    public Void call() {
                        applyFilterStateActivities(true);
                        return null;
                    }
                };

                /** Se abre la ventana emergente */
                Utils.alertFilterMap("Por hacer", "Ejecutadas", "Fallidas", getContext(), getActivity(), function, "stateActivities");
            }
        });

        btOrderRoute = rootView.findViewById(R.id.btn_organice_route_map);
        btOrderRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFilterActivitiesFragment fragment = new MapFilterActivitiesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, "Map")
                        .addToBackStack("Map").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        });

        btSearch = rootView.findViewById(R.id.btn_search_map_scr);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSearch) {
                    btSearch.setBackground(getResources().getDrawable(R.drawable.button_map));
                    searchView.setVisibility(View.INVISIBLE);
                } else {
                    btSearch.setBackground(getResources().getDrawable(R.drawable.button_white_stroke_blue));
                    searchView.setVisibility(View.VISIBLE);
                }

                isSearch = !isSearch;

            }
        });

        /** Async map */
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
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
                    if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                applyFilterActivities("All");

                if (gMap != null) {
                    gMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                        @Override
                        public void onMyLocationChange(Location location) {
                            /** La camara sigue la ubicación del usuario */
//                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                            float zoom = gMap.getCameraPosition().zoom;
//                            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
                        }
                    });

                    gMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {
                            // Getting view from the layout file infowindowlayout.xml
                            View v = getLayoutInflater().inflate(R.layout.info_windows_map, null);
                            int width = Utils.flotatToDp(250, getContext());
                            v.setLayoutParams(new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT));

                            LatLng latLng = marker.getPosition();

                            TextView tvTitle = v.findViewById(R.id.title_card_map);
                            View view = v.findViewById(R.id.view_map);
                            CardView line = v.findViewById(R.id.lineColor_map);
                            TextView tvName = v.findViewById(R.id.name_card_map);
                            TextView tvAddress = v.findViewById(R.id.address_card_map);
                            TextView tvSerial = v.findViewById(R.id.serial_card_map);
                            TextView tvOrder = v.findViewById(R.id.order_card_map);

                            String title = marker.getTitle();
                            String[] information = marker.getSnippet().split("\n");

                            /** Se actualizan los textview de acuerdo al tipo */
                            tvTitle.setText(title);
                            tvName.setText(information[2]);
                            tvAddress.setText(information[3]);
                            tvSerial.setText(information[4]);
                            tvTitle.setText(title + " - " + information[1]);
                            tvOrder.setText(information[5]);


                            int color = getContext().getResources().getColor(R.color.colorBlue);


                            line.setCardBackgroundColor(color);
                            tvTitle.setTextColor(color);

                            return v;
                        }
                    });

                    gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            String[] information = marker.getSnippet().split("\n");

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

                            double latitude = marker.getPosition().latitude;
                            double longitude = marker.getPosition().longitude;

                            boolean isUpload = Boolean.parseBoolean(information[10]);
                            boolean isCreated = Boolean.parseBoolean(information[11]);

                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date starExecuted = new Date();
                            Date finishExecuted = new Date();
                            Date starFailed = new Date();
                            Date finishFailed = new Date();

                            try {
                                starExecuted = dateFormat.parse(information[12]);
                                finishExecuted = dateFormat.parse(information[13]);
                                starFailed = dateFormat.parse(information[14]);
                                finishFailed = dateFormat.parse(information[15]);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }



                        }
                    });
                }
            }
        });

        return rootView;
    }

    private void getActivities(){
        /** Se pregunta por la actividad donde se esta actualmente */
        activitiesArray.clear();

        if(Globals.strCurrentActivities.equals(Globals.strAllActivities)){
            activitiesArray.addAll(Globals.dataInfrastructureSurveyActivitiesArray);
            //activitiesArray.addAll(Globals.dataReviewsBTActivitiesArray);
        }

        readActivities();
    }

    private void readActivities(){
        /** Limpia el mapa */
        gMap.clear();
        markersArray.clear();

        /** Se validan los filtros a mostrar*/
        verifyFilters();

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
            boolean isUpload = card.getIsUploadAPI();
            boolean isCreated = card.getIsCreated();
            Date starExecuted = card.getDateStartExecuted();
            Date finishExecuted = card.getDateFinishExecuted();
            Date starFailed = card.getDateStartFailed();
            Date finishFailed = card.getDateFinishFailed();

            String titleCard = activityTitle;
            String snipet = id + "\n" + title + "\n" +name + "\n" + address + "\n" + serial + "\n" + order + "\n" + state + "\n" + type + "\n" + failedReason + "\n" + distance + "\n" + isUpload + "\n" + isCreated + "\n" + starExecuted + "\n" + finishExecuted + "\n" + starFailed + "\n" + finishFailed;

            addMarker(latitude, longitude, titleCard, snipet, type, activityTitle);
        }
    }

    private void addMarker(double latitude, double longitude, String title, String snipet, String type, String activityTitle){
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
        if(type == Globals.strReviewBTActivities){

        }
        marker.icon(icon);

        markersArray.add(marker);

        /** Se añade al mapa */
        gMap.addMarker(marker);
    }

    private void initListener(){
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        ArrayList<CardGeneralActivity> activitiesSearch = new ArrayList<>();

        for (int i = 0; i < activitiesArray.size(); i++){
            String id = activitiesArray.get(i).getStrId();

         if(id.contains(query)){
            activitiesSearch.add(activitiesArray.get(i));
         }

        }

        if(activitiesSearch.size() > 0){
            activitiesArray.clear();
            activitiesArray = activitiesSearch;
            readActivities();

            /** Ajusta la camara para mostrar todos los resultados */
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (MarkerOptions marker : markersArray) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();

            int padding = 100; /** distancia de los resultados del borde de la pantalla */
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

            gMap.animateCamera(cu);
        }else {
            Utils.toastMessage("No se encontraron coincidencias", 1, getContext());
        }


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.length() < 1){
            getActivities();
        }
        return false;
    }

    public void applyFilterActivities(String whereCalled){
        activitiesArray.clear();

        if(Globals.cbFilterScrActivityMap){
            activitiesArray.addAll(Globals.dataInfrastructureSurveyActivitiesArray);
        }

        if(Globals.cbFilterReviewsBtActivityMap){
            /** Añadir cards de revisiones BT */
            //activitiesArray.addAll(Globals.dataScrActivitiesArray);
        }

        if(Globals.cbFilterReviewsMtActivityMap){
            /** Añadir cards de revisiones MT */
            //activitiesArray.addAll(Globals.dataScrActivitiesArray);
        }

        /** En caso de que deseleccione todos los filtros mostrar los que estaban por defecto*/
        if(Globals.cbFilterScrActivityMap == false && Globals.cbFilterReviewsBtActivityMap == false && Globals.cbFilterReviewsMtActivityMap == false){
            getActivities();
        }

        /** Se guarda en caso de que los otros filtros no esten seleccionados */
        ArrayList<CardGeneralActivity> currentActivities = new ArrayList<>();
        currentActivities.addAll(activitiesArray);

        if(!whereCalled.equals("subActivities")){
            /** Se aplican los otros filtros en caso de que tengan algo seleccionado*/
            boolean subActivity = applyFilterSubActivities(false);
            /** Si los filtros anteriores estaban sin seleccionar deja el filtro como estaba*/
            if(subActivity){
                activitiesArray.addAll(currentActivities);
                readActivities();
            }else{
                /** Se guarda la nueva listra filtrada por titulo de actividad*/
                currentActivities.clear();
                currentActivities.addAll(activitiesArray);
            }
        }

        if(!whereCalled.equals("stateActivities")){
            /** Se aplican los otros filtros en caso de que tengan algo seleccionado*/
            boolean state = applyFilterStateActivities(false);
            /** Si los filtros anteriores estaban sin seleccionar deja el filtro como estaba*/
            if(state){
                activitiesArray.addAll(currentActivities);
                readActivities();
            }
        }

    }

    public boolean applyFilterSubActivities(boolean isButton){
        ArrayList<CardGeneralActivity> currentActivities = new ArrayList<>();
        /** En caso de cambiar el mismo filtro que se actualice de acuerdo a las actividades sin tomar en cuenta este filtro*/
        if(isButton){
            applyFilterActivities("SubActivities");
        }
        currentActivities.addAll(activitiesArray);
        activitiesArray.clear();

        boolean isempty = true;


        if(isButton){
            if(Globals.cbFilterSuspensionMap == false && Globals.cbFilterCutMap == false && Globals.cbFilterReconnectionMap == false){
                applyFilterActivities("All");
            }
        }

        readActivities();

        return isempty;
    }

    public boolean applyFilterStateActivities(boolean isButton){
        ArrayList<CardGeneralActivity> currentActivities = new ArrayList<>();
        /** En caso de cambiar el mismo filtro que se actualice de acuerdo a las actividades sin tomar en cuenta este filtro*/
        if(isButton){
            applyFilterActivities("stateActivities");
        }
        currentActivities.addAll(activitiesArray);
        activitiesArray.clear();

        boolean isempty = true;

        if(Globals.cbFilterToDoMap){
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strTodoState));
            isempty = false;
        }

        if(Globals.cbFilterExecutedMap){
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strExecuteState));
            isempty = false;
        }

        if(Globals.cbFilterFailedMap){
            activitiesArray.addAll(Utils.filterByStateActivity(currentActivities, Globals.strFailedState));
            isempty = false;
        }

        if(isButton){
            if(Globals.cbFilterToDoMap == false && Globals.cbFilterExecutedMap == false && Globals.cbFilterFailedMap == false){
                applyFilterActivities("All");
            }
        }

        readActivities();

        return isempty;
    }

    public void verifyFilters(){
        /** Se limpia la caja de texto */
        String text = "";

        if(Globals.cbFilterScrActivityMap){
            text = text + "SCR,";
        }
        if(Globals.cbFilterReviewsBtActivityMap){
            text = text + " RI BT,";
        }
        if(Globals.cbFilterReviewsMtActivityMap){
            text = text + " RI MT,";
        }

        if(Globals.cbFilterSuspensionMap){
            text = text + " Suspención,";
        }
        if(Globals.cbFilterCutMap){
            text = text + " Corte,";
        }
        if(Globals.cbFilterReconnectionMap){
            text = text + " Reconexión,";
        }

        if(Globals.cbFilterToDoMap){
            text = text + " Por hacer,";
        }
        if(Globals.cbFilterExecutedMap){
            text = text + " Ejecutadas,";
        }
        if(Globals.cbFilterFailedMap){
            text = text + " Fallidas,";
        }

        if (!text.equals("")){

            /** Se elimina la ultima coma y se añade un punto */
            text = text.substring(0, text.length() - 1);
            text = text + ".";

            tvFilterApply.setVisibility(View.VISIBLE);
            tvFilterApply.setText(text);

            tvFilterApplyTitle.setVisibility(View.VISIBLE);
        }else {
            tvFilterApply.setVisibility(View.INVISIBLE);
            tvFilterApplyTitle.setVisibility(View.INVISIBLE);
        }
    }
}
