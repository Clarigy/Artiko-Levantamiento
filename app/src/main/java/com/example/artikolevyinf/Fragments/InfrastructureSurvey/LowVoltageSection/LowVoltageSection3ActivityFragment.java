package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

public class LowVoltageSection3ActivityFragment extends Fragment {

    EditText etObservationLowVoltage;
    ImageView ivInfoObservationLowVoltage;
    Button btnNextLowVoltage, btnPreviousLowVoltage;
    TextView tvObservationLowVoltage;

    public LowVoltageSection3ActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_section3_activity, container, false);

        etObservationLowVoltage = rootView.findViewById(R.id.observations_et_lowVoltage);
        ivInfoObservationLowVoltage = rootView.findViewById(R.id.info_ic_observations_iv_lowVoltage);
        btnNextLowVoltage = rootView.findViewById(R.id.next_btn_lowVoltage3);
        btnPreviousLowVoltage = rootView.findViewById(R.id.previous_btn_lowVoltage3);
        tvObservationLowVoltage = rootView.findViewById(R.id.textView1);

        /** Mensaje toast para los iconos de información */
        ivInfoObservationLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite las observaciones encontradas.", 0, getContext());
            }
        });

        /** OnClic Botones para pasar de pag */
        btnPreviousLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected - 1,true);
            }
        });
        btnNextLowVoltage.setOnClickListener(new View.OnClickListener() {
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

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        changeTitleColorIconColor();

        /** En caso de ser una actividad finalizada completa, esta no podrá ser editada, las fallidas no tendrán habilitado el botón de marcar como fallida */
        disableElementsByState();

        return rootView;
    }

    private boolean verifyInformation(){
        /** Se trae la información que haya llenado hasta el momento */
        String observation = etObservationLowVoltage.getText().toString();

        if(!TextUtils.isEmpty(observation)){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(2, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(2, false);
        }

        /** True porque el único campo es opcional */
        return true;
    }

    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSectionActivity lowVoltageSectionActivity = Globals.lowVoltageSectionActivity;
            etObservationLowVoltage.setText(lowVoltageSectionActivity.getStrObservation());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){

        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar EditText si la actividad se encuentra finalizada*/
            Utils.changeEditTextStyleDisable(etObservationLowVoltage, getContext());
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(etObservationLowVoltage.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvObservationLowVoltage, ivInfoObservationLowVoltage, getContext());
            }
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etObservationLowVoltage, Globals.mapLowVoltageSectionActivityData, "observation", "string");
    }
}