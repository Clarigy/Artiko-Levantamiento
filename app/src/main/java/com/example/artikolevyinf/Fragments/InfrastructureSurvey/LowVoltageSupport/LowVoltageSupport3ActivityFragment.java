package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSupportActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

public class LowVoltageSupport3ActivityFragment extends Fragment {

    EditText etLocationSupport, etEnroll;
    Spinner spnMaterialSupport;
    ImageView ivInfoLocationSupport, ivInfoMaterialSupport, ivInfoEnroll;
    TextView tvLocationSupport, tvMaterialSupport, tvEnroll;
    Button btnPreviousActivity, btnNextActivity;

    public LowVoltageSupport3ActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_support3_activity, container, false);

        etLocationSupport = rootView.findViewById(R.id.locationSupport_et_lvSupport);
        etEnroll = rootView.findViewById(R.id.enroll_et_lvSupport);
        spnMaterialSupport = rootView.findViewById(R.id.materialSupport_spn_lvSupport);
        ivInfoLocationSupport = rootView.findViewById(R.id.info_ic_locationSupport_iv_lvSupport);
        ivInfoMaterialSupport = rootView.findViewById(R.id.info_ic_materialSupport_iv_lvSupport);
        ivInfoEnroll = rootView.findViewById(R.id.info_ic_enroll_iv_lvSupport);
        tvLocationSupport = rootView.findViewById(R.id.textView1);
        tvMaterialSupport = rootView.findViewById(R.id.textView2);
        tvEnroll = rootView.findViewById(R.id.textView3);
        btnPreviousActivity = rootView.findViewById(R.id.previous_btn_lvSupport3);
        btnNextActivity = rootView.findViewById(R.id.next_btn_lvSupport3);

        /** EditText ubicación */
        etLocationSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.loadCurrentLocationWithVerification(getActivity(), getContext(), etLocationSupport, Globals.cardGeneralActivitySelected.getDbLatitude(), Globals.cardGeneralActivitySelected.getDbLongitude());
            }
        });

        /** Información del spinner */
        String[] optionMaterial = {"","POSTE DE CONCRETO","POSTE METALICO","TORRECILLA","FIBRA","TORRE","Poste PRFV","POSTE DE MADERA"};
        ArrayAdapter<String> adapterMaterial = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionMaterial);
        spnMaterialSupport.setAdapter(adapterMaterial);

        /** Iconos de información */
        ivInfoLocationSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Presione sobre la casilla para capturar la latitud y longitud del lugar donde se encuentra.", 1, getContext());
            }
        });
        ivInfoMaterialSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione el número de matrícula correspondiente a la entidad a matricular.", 0, getContext());
            }
        });

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

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        changeTitleColorIconColor();

        /** En caso de ser una actividad finalizada completa, esta no podrá ser editada, las fallidas no tendrán habilitado el botón de marcar como fallida */
        disableElementsByState();

        return  rootView;
    }

    private boolean verifyInformation(){
        /** Se trae la información que haya llenado hasta el momento */
        String location = etLocationSupport.getText().toString();
        String material = spnMaterialSupport.getSelectedItem().toString();
        String enroll = etEnroll.getText().toString();

        if( !TextUtils.isEmpty(location) && !TextUtils.isEmpty(material) && !TextUtils.isEmpty(enroll) ){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(2, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(2, false);
        }

        /** Se validan los campos para ofrecer retroalimentación */
        if(TextUtils.isEmpty(location)){
            etLocationSupport.setError("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(material)){
            TextView errorText = (TextView) spnMaterialSupport.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(enroll)){
            etEnroll.setError("Este campo no puede quedar vacío");
        }

        return true;
    }

    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSupportActivity lowVoltageSupportActivity = Globals.lowVoltageSupportActivity;
            if(lowVoltageSupportActivity.getDbLocationLatitude() != 0.0 && lowVoltageSupportActivity.getDbLocationLongitude() != 0.0){
                String latLong = lowVoltageSupportActivity.getDbLocationLatitude().toString() + ", " + lowVoltageSupportActivity.getDbLocationLongitude().toString();
                etLocationSupport.setText(latLong);
                Globals.mapTransformerActivityData.put("dbLatitude", lowVoltageSupportActivity.getDbLocationLatitude());
                Globals.mapTransformerActivityData.put("dbLongitude", lowVoltageSupportActivity.getDbLocationLongitude());
            }
            spnMaterialSupport.setSelection(((ArrayAdapter<String>)spnMaterialSupport.getAdapter()).getPosition(lowVoltageSupportActivity.getStrMaterialSupport()));
            etEnroll.setText(lowVoltageSupportActivity.getStrEnroll());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){
        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar spinner si la actividad se encuentra finalizada*/
            Utils.changeEditTextStyleDisable(etLocationSupport, getContext());
            Utils.changeSpinnerStyleDisable(spnMaterialSupport,getContext());
            Utils.changeEditTextStyleDisable(etEnroll, getContext());
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(etLocationSupport.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvLocationSupport, ivInfoLocationSupport, getContext());
            }
            if(TextUtils.isEmpty(spnMaterialSupport.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvMaterialSupport, ivInfoMaterialSupport, getContext());
            }
            if(TextUtils.isEmpty(etEnroll.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvEnroll, ivInfoEnroll, getContext());
            }

        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerEditText(etLocationSupport, Globals.mapLowVoltageSupportActivityData, "location", "string");
        Utils.addListenerSpinner(spnMaterialSupport, Globals.mapLowVoltageSupportActivityData, "material");
        Utils.addListenerEditText(etEnroll, Globals.mapLowVoltageSupportActivityData, "enroll", "string");
    }
}