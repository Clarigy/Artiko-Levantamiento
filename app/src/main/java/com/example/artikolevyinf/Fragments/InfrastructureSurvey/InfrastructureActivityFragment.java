package com.example.artikolevyinf.Fragments.InfrastructureSurvey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.badoualy.stepperindicator.StepperIndicator;
import com.example.artikolevyinf.Fragments.Adapters.CustomAdapterCardGeneralActivities;
import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Fragments.Adapters.ViewPagerAdapter;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Utils.Globals;

import java.util.ArrayList;

public class InfrastructureActivityFragment extends Fragment {

    public InfrastructureActivityFragment() {
        // Required empty public constructor
    }

    // Elementos para la lista de cards
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    // Componente grafico que contiene todas las cards
    private static RecyclerView recyclerView;

    //Objeto para recibir la información de la card
    public static CardGeneralActivity generalActivityData;
    // Array que contendra las cards a mostrar
    private static ArrayList<CardGeneralActivity> cardsArray;

    //Viewpager para deslizar los fragments
//    private ViewPager viewPager;
    //private ViewPagerAdapter viewPagerAdapter;
    int totalPages;
    int activityPage;
    private StepperIndicator indicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Globals.intPageSelected = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_infrastructure_activity, container, false);

        // Recibe los parametros enviados
        Bundle bundle = this.getArguments();
        if(bundle != null){
            generalActivityData = (CardGeneralActivity) bundle.getSerializable("data");
            totalPages = bundle.getInt("totalPages");
            activityPage = bundle.getInt("activityPage");
        }

        //Se crea un objeto card a partir de la info recibida
        cardsArray = new ArrayList<CardGeneralActivity>();
        cardsArray.add(generalActivityData);

        // Se define el RecyclerView como el que esta en el fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_transformer_activity);
        recyclerView.setHasFixedSize(true);

        // Se cre un linear layout para las cards
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomAdapterCardGeneralActivities(cardsArray, getContext());
        recyclerView.setAdapter(adapter);

        //ViewPage para deslizar entre fragments, el adapter posee la lógica de fragments
        Globals.viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), totalPages, activityPage); //view pager adapter
        Globals.viewPager = (ViewPager) rootView.findViewById(R.id.view_pager_intro);
        Globals.viewPager.setOffscreenPageLimit(20);
        //Se trae el stepper indicator de la interfaz
        indicator = (StepperIndicator) rootView.findViewById(R.id.stepperIndicator);
        Globals.viewPager.setAdapter(Globals.viewPagerAdapter);
        //Se le asigna el viewPager para que tenga control de la posición de los fragments
        indicator.setViewPager(Globals.viewPager);
        //Se asignan los labels del indicador inferior
        CharSequence[] labelList = new String[totalPages];
        for (int i = 0; i < totalPages; i++) {
            labelList[i] = String.valueOf(i+1);
        }
        indicator.setLabels(labelList);

        Globals.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Globals.intPageSelected = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        
        return rootView;
    }

    public static String getIdCardSelected(){
        return generalActivityData.getStrId();
    }

}