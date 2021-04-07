package com.example.artikolevyinf.Fragments.Map;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.TextViews.TextView_blue_12;
import com.example.artikolevyinf.TextViews.TextView_red_10;
import com.example.artikolevyinf.Fragments.Adapters.CustomAdapterCardGeneralActivities;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.InfrastructureActivityFragment;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Utils.Globals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFilterActivitiesFragment extends Fragment {

    public MapFilterActivitiesFragment() {
        // Required empty public constructor
    }

    private LinearLayout linearLayout = null;

    /**Elementos para la lista de cards*/
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public static View.OnClickListener myOnClickListener;

    /**Componente grafico que contiene todas las cards*/
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    TextView_blue_12 tvEmptyArrayState;

    /**Array que contendra las cards a mostrar*/
    ArrayList<CardGeneralActivity> cardsArray;

    /**Elementos gráficos de la interfaz*/
    Button btnFilterScr, btnFilterRIBt, btnFilterRIMt, btnFilterSuspension, btnFilterCut, btnFilterReconnection, btnFilterTodo, btnFilterExecuted, btnFilterFailed;
    TextView_red_10 tvUnderlineText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map_filter_activities, container, false);

        /** Se asignan las cards filtradas que vienen del mapa*/
        cardsArray = Globals.dataMapActivitiesArray;
        /** Se ordena la lista de acuerdo a la distancia */
        Collections.sort(cardsArray);

        /**Llamo a los botones de la interfaz*/
        btnFilterScr = rootView.findViewById(R.id.btn_scr_filter_map);
        btnFilterRIBt = rootView.findViewById(R.id.btn_ri_bt_filter_map);
        btnFilterRIMt = rootView.findViewById(R.id.btn_ri_mt_filter_map);

        btnFilterSuspension = rootView.findViewById(R.id.btn_suspension_filter_map);
        btnFilterCut = rootView.findViewById(R.id.btn_cut_filter_map);
        btnFilterReconnection = rootView.findViewById(R.id.btn_reconnection_filter_map);

        btnFilterTodo = rootView.findViewById(R.id.btn_todo_filter_map);
        btnFilterExecuted = rootView.findViewById(R.id.btn_executed_filter_map);
        btnFilterFailed = rootView.findViewById(R.id.btn_failed_filter_map);

        /** Ocultar botones de acuerdo a los filtros activados*/
        verifyFilters();

        /**Llamo los textview de la interfaz*/
        tvUnderlineText = rootView.findViewById(R.id.underlineText_filter_map);
        tvEmptyArrayState = rootView.findViewById(R.id.tvEmptyActivity_filter_map);
        /**Se pinta la linea inferior del texto*/
        tvUnderlineText.setPaintFlags(tvUnderlineText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        relativeLayout = rootView.findViewById(R.id.relative_layout_filter_map);

        /** Metodo que elimina los filtros*/
        tvUnderlineText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Actividades */
                Globals.cbFilterScrActivityMap = false;
                Globals.cbFilterReviewsBtActivityMap = false;
                Globals.cbFilterReviewsMtActivityMap = false;
                /** SubActividades */
                Globals.cbFilterSuspensionMap = false;
                Globals.cbFilterCutMap = false;
                Globals.cbFilterReconnectionMap = false;
                /** Estado */
                Globals.cbFilterToDoMap = false;
                Globals.cbFilterExecutedMap = false;
                Globals.cbFilterFailedMap = false;

                getActivity().onBackPressed();
            }
        });

        /** Llama al metodo que detecta el click*/
        myOnClickListener = new MyOnClickListener(getActivity());

        /** Se define el RecyclerView como el que esta en el fragment*/
        recyclerView = rootView.findViewById(R.id.recycler_view_filter_cards_activity_map);
        recyclerView.setHasFixedSize(true);

        /** Se cre un linear layout para las cards*/
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomAdapterCardGeneralActivities(cardsArray, getContext());
        recyclerView.setAdapter(adapter);

        if(cardsArray.size() == 0){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            tvEmptyArrayState.setLayoutParams(params);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = view.findViewById(R.id.activity_fragment_layout);
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            /**Se trae la posición del view clickeado*/
            int selectedItemPosition = recyclerView.getChildPosition(v);
            /**Se trae el viewHolder en la posición anterior*/
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);

            /**Se trae la información faltante de la card seleccionada*/
            String id = cardsArray.get(selectedItemPosition).getStrId();
            String CardActivityTitle = cardsArray.get(selectedItemPosition).getStrActivityTitle();
            String title = cardsArray.get(selectedItemPosition).getStrTitle();
            String name = cardsArray.get(selectedItemPosition).getStrName();
            String serial = cardsArray.get(selectedItemPosition).getStrSerial();
            String order = cardsArray.get(selectedItemPosition).getStrOrder();
            String address = cardsArray.get(selectedItemPosition).getStrAddress();
            double latitude = cardsArray.get(selectedItemPosition).getDbLatitude();
            double longitude = cardsArray.get(selectedItemPosition).getDbLongitude();
            String state = cardsArray.get(selectedItemPosition).getStrState();
            String type = cardsArray.get(selectedItemPosition).getStrType();
            String failedReason = cardsArray.get(selectedItemPosition).getStrFailedReason();
            double distance = cardsArray.get(selectedItemPosition).getDbDistance();
            boolean isUpload = cardsArray.get(selectedItemPosition).getIsUploadAPI();
            boolean isCreated = cardsArray.get(selectedItemPosition).getIsCreated();
            Date starExecuted = cardsArray.get(selectedItemPosition).getDateStartExecuted();
            Date finishExecuted = cardsArray.get(selectedItemPosition).getDateFinishExecuted();
            Date starFailed = cardsArray.get(selectedItemPosition).getDateStartFailed();
            Date finishFailed = cardsArray.get(selectedItemPosition).getDateFinishFailed();

            if(type.equals(Globals.strInfrastructureSurveyActivities)){

                /**Se crea un fragment del tipo transformerActivitiesFragment*/
                InfrastructureActivityFragment infrastructureActivityFragment = new InfrastructureActivityFragment();

                /**Se crea un objeto a partir de la información de la card seleccionada*/
                CardGeneralActivity infoTransformer = new CardGeneralActivity(id, CardActivityTitle, title, name, serial, order, address, latitude, longitude, state, type, failedReason, distance, isUpload, starExecuted, finishExecuted, starFailed, finishFailed, isCreated);
                Globals.cardGeneralActivitySelected = infoTransformer;

                /**Se envian argumentos al nuevo fragment*/
                Bundle bundle = new Bundle();
                switch (CardActivityTitle){
                    case "TRANSFORMADOR DE DISTRIBUCIÓN":
                        bundle.putInt("totalPages", 8);
                        bundle.putInt("activityPage", 1);
                        break;
                    case "CENTRO DE TRANSFORMACIÓN":
                        bundle.putInt("totalPages", 1);
                        bundle.putInt("activityPage", 2);
                        break;
                    case "APOYO DE BAJA TENSIÓN":
                        bundle.putInt("totalPages", 5);
                        bundle.putInt("activityPage", 3);
                        break;
                    case "CAJA SUBTERRÁNEA":
                        bundle.putInt("totalPages", 2);
                        bundle.putInt("activityPage", 4);
                        break;
                }
                bundle.putSerializable("data", (Serializable) infoTransformer);
                infrastructureActivityFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, infrastructureActivityFragment, "Infrastructure")
                        .addToBackStack("Infrastructure").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

            }

        }

    }

    public void verifyFilters(){
        if(!Globals.cbFilterScrActivityMap){
            hideButton(btnFilterScr);
        }
        if(!Globals.cbFilterReviewsBtActivityMap){
            hideButton(btnFilterRIBt);
        }
        if(!Globals.cbFilterReviewsMtActivityMap){
            hideButton(btnFilterRIMt);
        }

        if(!Globals.cbFilterSuspensionMap){
            hideButton(btnFilterSuspension);
        }
        if(!Globals.cbFilterCutMap){
            hideButton(btnFilterCut);
        }
        if(!Globals.cbFilterReconnectionMap){
            hideButton(btnFilterReconnection);
        }

        if(!Globals.cbFilterToDoMap){
            hideButton(btnFilterTodo);
        }
        if(!Globals.cbFilterExecutedMap){
            hideButton(btnFilterExecuted);
        }
        if(!Globals.cbFilterFailedMap){
            hideButton(btnFilterFailed);
        }
    }

    public void hideButton(Button button){
        button.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
    }
}
