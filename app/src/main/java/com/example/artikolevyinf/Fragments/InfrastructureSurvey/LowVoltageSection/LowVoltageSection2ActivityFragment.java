package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

public class LowVoltageSection2ActivityFragment extends Fragment {

    Spinner spnNConductorsLowVoltage, spnDispositionConductorsLowVoltage;
    EditText etLengthLowVoltage;
    ImageView ivInfoNConductorsLowVoltage, ivInfoDispositionConductorsLowVoltage, ivInfoLengthLowVoltage;
    Button btnNextLowVoltage, btnPreviousLowVoltage;
    TextView tvNConductorsLowVoltage, tvDispositionConductorsLowVoltage, tvLengthLowVoltage;

    public LowVoltageSection2ActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_section2_activity, container, false);

        spnNConductorsLowVoltage = rootView.findViewById(R.id.nConductors_spn_lowVoltage);
        spnDispositionConductorsLowVoltage = rootView.findViewById(R.id.dispositionConductor_spn_lowVoltage);

        etLengthLowVoltage = rootView.findViewById(R.id.length_et_lowVoltage);
        etLengthLowVoltage.setTransformationMethod(null);

        ivInfoNConductorsLowVoltage = rootView.findViewById(R.id.nConductors_spn_lowVoltage);
        ivInfoDispositionConductorsLowVoltage = rootView.findViewById(R.id.info_ic_dispositionConductor_iv_lowVoltage);
        ivInfoLengthLowVoltage = rootView.findViewById(R.id.info_ic_length_iv_lowVoltage);

        btnNextLowVoltage = rootView.findViewById(R.id.next_btn_lowVoltage2);
        btnPreviousLowVoltage = rootView.findViewById(R.id.previous_btn_lowVoltage2);

        tvNConductorsLowVoltage = rootView.findViewById(R.id.textView1);
        tvDispositionConductorsLowVoltage = rootView.findViewById(R.id.textView2);
        tvLengthLowVoltage = rootView.findViewById(R.id.textView3);

        /** Se incluye la información a los Spinners */
        String[] optionNConductors = {"","Tríplex","2F+N","3F+N","1F+N","Cuádruplex","1F","3F","2F"};
        ArrayAdapter<String> adapterNConductors = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionNConductors);
        spnNConductorsLowVoltage.setAdapter(adapterNConductors);
        String[] optionDispositionConductor = {"","Abierta","Chilena","Trenzada","Bajante","De conexión","Mensajero","Retenida","Subterránea","Especial"};
        ArrayAdapter<String> adapterDispositionConductor = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionDispositionConductor);
        spnDispositionConductorsLowVoltage.setAdapter(adapterDispositionConductor);

        //Se escucha cada cambio del campo de texto y se toma el string para concatenar una "," en los valores
        etLengthLowVoltage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Utils.addCharToString(etLengthLowVoltage, ".",9);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /** Mensaje toast para los iconos de información */
        ivInfoNConductorsLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoDispositionConductorsLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoLengthLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Digite la longitud.", 0, getContext());
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
        String nConductors = spnNConductorsLowVoltage.getSelectedItem().toString();
        String dispositionConductor = spnDispositionConductorsLowVoltage.getSelectedItem().toString();
        int length = etLengthLowVoltage.getText().length();

        if(!TextUtils.isEmpty(nConductors) && !TextUtils.isEmpty(dispositionConductor) && length != 0){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(1, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(1, false);
        }

        /** En caso de tener los campos necesarios completos, puede pasar a la siguiente */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            return true;
        }else {
            if (!TextUtils.isEmpty(nConductors) && !TextUtils.isEmpty(dispositionConductor) && length != 0) {
                return true;
            }
        }

        /** Se validan los campos para ofrecer retroalimentación */
        if(TextUtils.isEmpty(nConductors)){
            TextView errorText = (TextView) spnNConductorsLowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(dispositionConductor)){
            TextView errorText = (TextView) spnDispositionConductorsLowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(length == 0){
            etLengthLowVoltage.setError("Este campo no puede quedar vacío");
            etLengthLowVoltage.requestFocus();
        }

        return false;
    }

    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSectionActivity lowVoltageSectionActivity = Globals.lowVoltageSectionActivity;
            spnNConductorsLowVoltage.setSelection(((ArrayAdapter<String>)spnNConductorsLowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrNConductors()));
            spnDispositionConductorsLowVoltage.setSelection(((ArrayAdapter<String>)spnDispositionConductorsLowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrDispositionConductor()));
            etLengthLowVoltage.setText(lowVoltageSectionActivity.getStrLength() == null ? "" : lowVoltageSectionActivity.getStrLength());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){

        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar spinner si la actividad se encuentra finalizada*/
            Utils.changeSpinnerStyleDisable(spnNConductorsLowVoltage,getContext());
            Utils.changeSpinnerStyleDisable(spnDispositionConductorsLowVoltage,getContext());
            Utils.changeEditTextStyleDisable(etLengthLowVoltage, getContext());
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(spnNConductorsLowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvNConductorsLowVoltage, ivInfoNConductorsLowVoltage, getContext());
            }

            if(TextUtils.isEmpty(spnDispositionConductorsLowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvDispositionConductorsLowVoltage, ivInfoDispositionConductorsLowVoltage, getContext());
            }

            if(TextUtils.isEmpty(etLengthLowVoltage.getText().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvLengthLowVoltage, ivInfoLengthLowVoltage, getContext());
            }
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerSpinner(spnNConductorsLowVoltage, Globals.mapLowVoltageSectionActivityData, "nConductors");
        Utils.addListenerSpinner(spnDispositionConductorsLowVoltage, Globals.mapLowVoltageSectionActivityData, "dispositionConductor");
        Utils.addListenerEditText(etLengthLowVoltage, Globals.mapLowVoltageSectionActivityData, "length", "string");
    }

}