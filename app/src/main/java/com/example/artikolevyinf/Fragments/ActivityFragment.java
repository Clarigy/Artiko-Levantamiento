package com.example.artikolevyinf.Fragments;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.artikolevyinf.Fragments.Adapters.CustomAdapterCardGeneralActivities;
import com.example.artikolevyinf.Fragments.InfrastructureSurvey.InfrastructureActivityFragment;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.R;
import com.example.artikolevyinf.TextViews.TextView_blue_12;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActivityFragment() {
        // Required empty public constructor
    }

    private LinearLayout linearLayout = null;

    /**
     * Elementos para la lista de cards
     */
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public static View.OnClickListener myOnClickListener;

    /**
     * Componente grafico que contiene todas las cards
     */
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    TextView_blue_12 tvEmptyArrayState;

    /**
     * Array que contendra las cards a mostrar
     */
    ArrayList<CardGeneralActivity> cardsArray, cardsArrayTodo, cardsArrayExecuted, cardsArrayFailed;

    /**
     * Elementos gráficos de la interfaz
     */
    Button btnTodo, btnExecuted, btnFailed;
    TextView tvGeneralActivity;



    /**
     * Titulo que recibira por medio de los bundle y el estado de acuerdo a los botones
     */
    String strActivityTitle, strCurrentState;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**Inflate the layout for this fragment*/
        View rootView = inflater.inflate(R.layout.fragment_activity, container, false);

        /**Llamo las listas*/
        cardsArrayTodo = new ArrayList<>();
        cardsArrayExecuted = new ArrayList<>();
        cardsArrayFailed = new ArrayList<>();


        /**Llamo a los botones de la interfaz*/
        btnTodo = (Button) rootView.findViewById(R.id.btn_todo_general_activities);
        btnExecuted = (Button) rootView.findViewById(R.id.btn_executed_general_activities);
        btnFailed = (Button) rootView.findViewById(R.id.btn_failed_general_activities);

        /**Llamo los textview de la interfaz*/
        tvGeneralActivity = (TextView) rootView.findViewById(R.id.generalActivity);

        relativeLayout = rootView.findViewById(R.id.relative_layout_general_activities);
        tvEmptyArrayState = rootView.findViewById(R.id.tvEmptyActivity_general_activities);
        /**Se pinta la linea inferior del texto*/

        /** Llama al metodo que detecta el click*/
        myOnClickListener = new MyOnClickListener(getActivity());

        /** Se define el RecyclerView como el que esta en el fragment*/
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_cards_scr_activity);
        recyclerView.setHasFixedSize(true);

        tvGeneralActivity.setText("Actividad - Levantamiento");

        ////////////////////////////////////////////////
        ArrayList<CardGeneralActivity> dataActivities = new ArrayList<>();
        dataActivities.addAll(Globals.dataInfrastructureSurveyActivitiesArray);
        Globals.strCurrentActivities = Globals.strInfrastructureSurveyActivities;
        cardsArray = dataActivities;
        System.out.println(cardsArray);

        for (int i = 0; i < cardsArray.size(); i++) {
            if (cardsArray.get(i).getStrState().equals(Globals.strTodoState)) {
                cardsArrayTodo.add(cardsArray.get(i));
            } else if (cardsArray.get(i).getStrState().equals(Globals.strExecuteState)) {
                cardsArrayExecuted.add(cardsArray.get(i));
            } else if (cardsArray.get(i).getStrState().equals(Globals.strFailedState) || cardsArray.get(i).getStrState().equals(Globals.strFailedStateIncomplete)) {
                cardsArrayFailed.add(cardsArray.get(i));
            }
        }

        System.out.println(cardsArrayTodo);
        /**Por defecto el estado sera por hacer*/
        strCurrentState = Globals.strTodoState;

        /** Se cre un linear layout para las cards*/
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomAdapterCardGeneralActivities(cardsArrayTodo, getContext());

        recyclerView.setAdapter(adapter);

        if(cardsArrayTodo.size() == 0){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            tvEmptyArrayState.setLayoutParams(params);
        }

        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBtnColor(1);
                strCurrentState = Globals.strTodoState;

                if(cardsArrayTodo.size() == 0){
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    tvEmptyArrayState.setLayoutParams(params);
                }else{
                    tvEmptyArrayState.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
                }

                /**Se muestran las cards por hacer*/
                adapter = new CustomAdapterCardGeneralActivities(cardsArrayTodo, getContext());
                recyclerView.setAdapter(adapter);
            }
        });

        btnExecuted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strCurrentState = Globals.strExecuteState;
                changeBtnColor(2);

                if(cardsArrayExecuted.size() == 0){
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    tvEmptyArrayState.setLayoutParams(params);
                }else{
                    tvEmptyArrayState.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
                }

                /**Se muestran las cards ejecutadas*/
                adapter = new CustomAdapterCardGeneralActivities(cardsArrayExecuted, getContext());
                recyclerView.setAdapter(adapter);
            }
        });

        btnFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strCurrentState = Globals.strFailedState;
                changeBtnColor(3);

                if(cardsArrayFailed.size() == 0){
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    tvEmptyArrayState.setLayoutParams(params);
                }else{
                    tvEmptyArrayState.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
                }

                //Se muestran las cards fallidas
                adapter = new CustomAdapterCardGeneralActivities(cardsArrayFailed, getContext());
                recyclerView.setAdapter(adapter);
            }
        });

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayout = (LinearLayout) view.findViewById(R.id.activity_fragment_layout);
    }

    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {

            final Fragment fragment = (Fragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
            final String tagFragment = fragment.getTag();

            /** Se valida que el click solo sea valido desde esta ventana y no desde el detalle de cada atividad*/
            if (tagFragment.equals("Activities")) {

                /**Se trae la posición del view clickeado*/
                int selectedItemPosition = recyclerView.getChildPosition(v);
                /**Se trae el viewHolder en la posición anterior*/
                RecyclerView.ViewHolder viewHolder
                        = recyclerView.findViewHolderForPosition(selectedItemPosition);

                /**Se crea un array de acuerdo a el estado seleccionado*/
                ArrayList<CardGeneralActivity> selectedArray = new ArrayList<>();
                if (strCurrentState == Globals.strTodoState) {
                    selectedArray.addAll(cardsArrayTodo);
                } else if (strCurrentState == Globals.strExecuteState) {
                    selectedArray.addAll(cardsArrayExecuted);
                } else if (strCurrentState == Globals.strFailedState) {
                    selectedArray.addAll(cardsArrayFailed);
                }

                /**Se trae la información faltante de la card seleccionada*/
                String id = selectedArray.get(selectedItemPosition).getStrId();
                String CardActivityTitle = selectedArray.get(selectedItemPosition).getStrActivityTitle();
                String title = selectedArray.get(selectedItemPosition).getStrTitle();
                String name = selectedArray.get(selectedItemPosition).getStrName();
                String serial = selectedArray.get(selectedItemPosition).getStrSerial();
                String order = selectedArray.get(selectedItemPosition).getStrOrder();
                String address = selectedArray.get(selectedItemPosition).getStrAddress();
                double latitude = selectedArray.get(selectedItemPosition).getDbLatitude();
                double longitude = selectedArray.get(selectedItemPosition).getDbLongitude();
                String state = selectedArray.get(selectedItemPosition).getStrState();
                String type = selectedArray.get(selectedItemPosition).getStrType();
                String failedReason = selectedArray.get(selectedItemPosition).getStrFailedReason();
                double distance = selectedArray.get(selectedItemPosition).getDbDistance();
                boolean isUpload = selectedArray.get(selectedItemPosition).getIsUploadAPI();
                boolean isCreated = selectedArray.get(selectedItemPosition).getIsCreated();
                Date starExecuted = selectedArray.get(selectedItemPosition).getDateStartExecuted();
                Date finishExecuted = selectedArray.get(selectedItemPosition).getDateFinishExecuted();
                Date starFailed = selectedArray.get(selectedItemPosition).getDateStartFailed();
                Date finishFailed = selectedArray.get(selectedItemPosition).getDateFinishFailed();

                /** Se guarda la hora min y seg cuando se abre la actividad */
                Date dateStartActivity = Utils.currentTime();
                Globals.dateStartActivity = dateStartActivity;

                if (type.equals(Globals.strInfrastructureSurveyActivities)) {

                    /**Se crea un fragment del tipo transformerActivitiesFragment*/
                    InfrastructureActivityFragment infrastructureActivityFragment = new InfrastructureActivityFragment();

                    /**Se crea un objeto a partir de la información de la card seleccionada*/
                    CardGeneralActivity infoInfrastructure = new CardGeneralActivity(id, CardActivityTitle, title, name, serial, order, address, latitude, longitude, state, type, failedReason, distance, isUpload, starExecuted, finishExecuted, starFailed, finishFailed, isCreated);
                    Globals.cardGeneralActivitySelected = infoInfrastructure;

                    /**Se envian argumentos al nuevo fragment*/
                    Bundle bundle = new Bundle();
                    switch (CardActivityTitle) {
                        case "TRANSFORMADOR DE DISTRIBUCIÓN":
                            bundle.putInt("totalPages", 8);
                            bundle.putInt("activityPage", 1);
                            Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                            }};
                            break;
                        case "CENTRO DE TRANSFORMACIÓN":
                            bundle.putInt("totalPages", 1);
                            bundle.putInt("activityPage", 2);
                            Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{
                                add(false);
                            }};
                            break;
                        case "APOYO DE BAJA TENSIÓN":
                            bundle.putInt("totalPages", 7);
                            bundle.putInt("activityPage", 3);
                            Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                            }};
                            break;
                        case "CAJA SUBTERRÁNEA":
                            bundle.putInt("totalPages", 2);
                            bundle.putInt("activityPage", 4);
                            Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{
                                add(false);
                                add(false);
                            }};
                            break;
                        case "TRAMOS BAJA TENSIÓN":
                            bundle.putInt("totalPages", 5);
                            bundle.putInt("activityPage", 5);
                            Globals.boolArrayCompleteInfrastructurePages = new ArrayList<Boolean>() {{
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                                add(false);
                            }};
                            break;
                    }
                    bundle.putSerializable("data", (Serializable) infoInfrastructure);
                    infrastructureActivityFragment.setArguments(bundle);

                    /** Se trae la información de las actividad seleccionada que no esta por hacer (Fallidas, Fallidas incompletas, Ejecutadas) */
                    if (!infoInfrastructure.getStrState().equals(Globals.strTodoState)) {
                        /** Se trae la información de la actividad de transformador de distribución */
                        if (infoInfrastructure.getStrActivityTitle().equals(Globals.strTransformer)) {
                            Utils.getTransformerActivity(infoInfrastructure, context);
                        }
                    }
                    if (infoInfrastructure.getStrActivityTitle().equals(Globals.strLowVoltageSection)) {
                        Utils.getLowVoltageSectionActivity(infoInfrastructure, getContext());
                    }

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, infrastructureActivityFragment, "Infrastructure")
                            .addToBackStack("Infrastructure").setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

                }
            }
        }
    }

    /**Se cambia el color de los botones al ser presionados*/
    private void changeBtnColor(int index){
        switch (index){
            case 1:
                /**Se cambia el color del boton clickeado a azul con letra blanca*/
                btnTodo.setTextColor(getResources().getColor(R.color.colorWhite));
                btnTodo.setBackground(getResources().getDrawable(R.drawable.button_blue));

                btnExecuted.setTextColor(getResources().getColor(R.color.colorBlueDark));
                btnExecuted.setBackground(getResources().getDrawable(R.drawable.unselected_button));

                btnFailed.setTextColor(getResources().getColor(R.color.colorBlueDark));
                btnFailed.setBackground(getResources().getDrawable(R.drawable.unselected_button));
                break;
            case 2:
                /**Se cambia el color del boton clickeado a azul con letra blanca*/
                btnExecuted.setTextColor(getResources().getColor(R.color.colorWhite));
                btnExecuted.setBackground(getResources().getDrawable(R.drawable.button_blue));

                btnTodo.setTextColor(getResources().getColor(R.color.colorBlueDark));
                btnTodo.setBackground(getResources().getDrawable(R.drawable.unselected_button));

                btnFailed.setTextColor(getResources().getColor(R.color.colorBlueDark));
                btnFailed.setBackground(getResources().getDrawable(R.drawable.unselected_button));
                break;
            case 3:
                /**Se cambia el color del boton clickeado a azul con letra blanca*/
                btnFailed.setTextColor(getResources().getColor(R.color.colorWhite));
                btnFailed.setBackground(getResources().getDrawable(R.drawable.button_blue));

                btnTodo.setTextColor(getResources().getColor(R.color.colorBlueDark));
                btnTodo.setBackground(getResources().getDrawable(R.drawable.unselected_button));

                btnExecuted.setTextColor(getResources().getColor(R.color.colorBlueDark));
                btnExecuted.setBackground(getResources().getDrawable(R.drawable.unselected_button));
                break;
        }
    }


}