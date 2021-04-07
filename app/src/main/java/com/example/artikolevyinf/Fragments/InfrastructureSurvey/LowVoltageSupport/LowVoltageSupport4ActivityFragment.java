package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSupportActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

public class LowVoltageSupport4ActivityFragment extends Fragment {

    EditText etObservationSupport;
    TextView tvObservationSupport;
    ImageView ivInfoObservationSupport;
    Button btnNextActivity, btnPreviousActivity;

    public LowVoltageSupport4ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_support4_activity, container, false);

        etObservationSupport = rootView.findViewById(R.id.observations_et_lvSupport);
        tvObservationSupport = rootView.findViewById(R.id.textView1);
        ivInfoObservationSupport = rootView.findViewById(R.id.info_ic_observations_iv_lvSupport);
        btnNextActivity = rootView.findViewById(R.id.next_btn_lvSupport4);
        btnPreviousActivity = rootView.findViewById(R.id.previous_btn_lvSupport4);

        /** Botones de siguiente y anterior actividad */
        btnPreviousActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected - 1,true);
            }
        });
        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInformation()){
                    Globals.viewPager.setCurrentItem(Globals.intPageSelected + 1,true);
                }
            }
        });

        /** Se obtiene la información que va diligenciando para guardarla inmediatamente */
        saveInstantInformation();

        /** En caso de que sea una información fallida o ejecutada, se trae la información de SQLite */
        getInformationFromDB();

        /** En caso de ser una actividad finalizada completa, esta no podrá ser editada, las fallidas no tendrán habilitado el botón de marcar como fallida */
        disableElementsByState();

        return rootView;
    }

    private boolean verifyInformation(){

        /** Se marca como que la página esta completa */
        Globals.boolArrayCompleteInfrastructurePages.set(3, true);

        return true;
    }

    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSupportActivity lowVoltageSupportActivity = Globals.lowVoltageSupportActivity;
            etObservationSupport.setText(lowVoltageSupportActivity.getStrObservation() == null ? "" : lowVoltageSupportActivity.getStrObservation());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){
        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar elemento si la actividad se encuentra finalizada*/
            Utils.changeEditTextStyleDisable(etObservationSupport, getContext());
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etObservationSupport, Globals.mapLowVoltageSupportActivityData, "observation", "string");
    }
}