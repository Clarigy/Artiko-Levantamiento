package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSection;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

public class LowVoltageSection4ActivityFragment extends Fragment {

    Spinner spnPropertyLowVoltage;
    SearchableSpinner spnDriverType1LowVoltage, spnDriverType2LowVoltage, spnDriverType3LowVoltage;
    ImageView ivInfoPropertyLowVoltage, ivInfoAreaTypeLowVoltage, ivInfoDriverType1LowVoltage, ivInfoDriverType2LowVoltage, ivInfoDriverType3LowVoltage;
    Button btnRuralLowVoltage, btnUrbanLowVoltage, btnNextLowVoltage, btnPreviousLowVoltage;
    TextView tvPropertyLowVoltage, tvAreaTypeLowVoltage, tvDriverType1LowVoltage, tvDriverType2LowVoltage, tvDriverType3LowVoltage;

    int intTextUnselectedFilter, intTextSelectedFilter;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter;

    ArrayList<Button> buttonListArea;
    boolean isBtnAreaSelected = false;

    public LowVoltageSection4ActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_section4_activity, container, false);

        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        spnPropertyLowVoltage = rootView.findViewById(R.id.property_spn_lowVoltage);
        spnDriverType1LowVoltage = rootView.findViewById(R.id.driverType1_spn_lowVoltage);
        spnDriverType2LowVoltage = rootView.findViewById(R.id.driverType2_spn_lowVoltage);
        spnDriverType3LowVoltage = rootView.findViewById(R.id.driverType3_spn_lowVoltage);

        ivInfoPropertyLowVoltage = rootView.findViewById(R.id.info_ic_property_iv_lowVoltage);
        ivInfoAreaTypeLowVoltage = rootView.findViewById(R.id.info_ic_typeAreal_iv_lowVoltage);
        ivInfoDriverType1LowVoltage = rootView.findViewById(R.id.info_ic_driverType1_iv_lowVoltage);
        ivInfoDriverType2LowVoltage = rootView.findViewById(R.id.info_ic_driverType2_iv_lowVoltage);
        ivInfoDriverType3LowVoltage = rootView.findViewById(R.id.info_ic_driverType3_iv_lowVoltage);

        btnRuralLowVoltage = rootView.findViewById(R.id.typeArea_btn_rural);
        btnUrbanLowVoltage = rootView.findViewById(R.id.typeArea_btn_urban);
        buttonListArea = new ArrayList<Button>() {{ add(btnRuralLowVoltage); add(btnUrbanLowVoltage); }};
        btnNextLowVoltage = rootView.findViewById(R.id.next_btn_lowVoltage4);
        btnPreviousLowVoltage = rootView.findViewById(R.id.previous_btn_lowVoltage4);

        tvPropertyLowVoltage = rootView.findViewById(R.id.textView1);
        tvAreaTypeLowVoltage = rootView.findViewById(R.id.textView2);
        tvDriverType1LowVoltage = rootView.findViewById(R.id.textView3);
        tvDriverType2LowVoltage = rootView.findViewById(R.id.textView4);
        tvDriverType3LowVoltage = rootView.findViewById(R.id.textView5);

        /** Se incluye la información a los Spinners */
        String[] optionProperty = {"","AIR-E","PARTICULAR","MIXTO","CENS","MYPIMES","SIN PROPIEDAD","PUBLICA","STN"};
        ArrayAdapter<String> adapterProperty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionProperty);
        spnPropertyLowVoltage.setAdapter(adapterProperty);
        String[] optionDriverType123 = {"","1650 KCM XLPE","3 N 5 ALUMOWELD","7 N 8 ALUMOWELD","1/4 ACERO GALVANIZADO","394.5 ACSR","559.5 AAAC","394.5 AAAC","312.8 AAAC","123.3 AAAC","366.4 ACSR","123 CU","123.3 ACSR","6 CU","2 CU","2 CU XLPE","2/0 CU","3/0 CU","4/0 CU","4/0 CU XLPE","477 AAAC","500 CU XLPE","4 ACSR","2 ACSR","1/0 ACSR","4/0 ACSR","2/0 ACSR","477 ACSR","3/0 ACSR","750 CU XLPE","4 CU","4 CU XLPE","350 MCR","6 CU XLPE","350 CU","1 CU XLPE","250 CU","BARRA CU","336.4 ACSR","336 AAC","1/0 CU","6 ACSR","1/0 AAAC","4/0 AAAC","2/0 AAAC","2 AAAC","1/0 AL","2/0 AL","4/0 AL","2 AL","500 AAC","1000 AAC","6-A CU","250 AAAC","400 CU XLPE","8 ACSR","500 CU","250 CU XLPE","400 CU","2/0 CU XLPE","1/0 CU XLPE","600 CU XLPE","600 CU","3/0 AAAC","4 AL","8 AL","8 CU","6 AL","4 AAAC","247 ACSR","394.8 AAAC","266.8 ACSR","123.3 ACSR","350 CU XLPE","300 ACSR","500 ACSR XLPE","500 AL","750 AL","300 CU XLPE","350 AL XLPE","750 XLPE MCM","750 XLPE MCM AL","1/0 ACSR XLPE","3/0 XLPE AL","4/0 AL XLPE","2 AWG XLPE","2 XLPE","1/0 XLPE","300 XLPE","350 XLPE","500 XLPE","750 XLPE","4/0 XLPE","500 MCM","ACSR 266 MCM","500 XLPE-AL","1000 XLPE-CU","246,9 AAAC","336.4 ACSR/GA","CF-159","CF-200","CF-125","CF-63","477 ACSR XLPE","1/0 AL XLPE","CF-65","1 CU","CABLE ACERO GALVANIZADO 1/2","CABLE ACERO GALVANIZADO 3/8","1/0 AAC","4/0 AAC","2/0 AL XLPE","1000 MCM CU XLPE","500 MCM XLPE-AL","350 CU MCM","500 MCM CU","477 AL","3X70MM2 AL XLPE"};
        ArrayAdapter<String> adapterDriverType1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionDriverType123);
        spnDriverType1LowVoltage.setAdapter(adapterDriverType1);
        spnDriverType1LowVoltage.setTitle("Seleccione el tipo de conductor");
        spnDriverType1LowVoltage.setPositiveButton("Aceptar");
        ArrayAdapter<String> adapterDriverType2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionDriverType123);
        spnDriverType2LowVoltage.setAdapter(adapterDriverType2);
        spnDriverType2LowVoltage.setTitle("Seleccione el tipo de conductor");
        spnDriverType2LowVoltage.setPositiveButton("Aceptar");
        ArrayAdapter<String> adapterDriverType3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionDriverType123);
        spnDriverType3LowVoltage.setAdapter(adapterDriverType3);
        spnDriverType3LowVoltage.setTitle("Seleccione el tipo de conductor");
        spnDriverType3LowVoltage.setPositiveButton("Aceptar");

        /** Mensaje toast para los iconos de información */
        ivInfoPropertyLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoAreaTypeLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoDriverType1LowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoDriverType2LowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoDriverType3LowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });

        /** OnClic para los botones de la interfaz, se cambia el color de los mismos al seleccionarlo */
        btnRuralLowVoltage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListArea, btnRuralLowVoltage, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnAreaSelected = true;
                Globals.mapLowVoltageSectionActivityData.put("areaType", "Rural");
            }
        });
        btnUrbanLowVoltage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListArea, btnUrbanLowVoltage, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnAreaSelected = true;
                Globals.mapLowVoltageSectionActivityData.put("areaType", "Urbano");
            }
        });
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
        String property = spnPropertyLowVoltage.getSelectedItem().toString();
        String driverType1 = spnDriverType1LowVoltage.getSelectedItem().toString();
        String driverType2 = spnDriverType2LowVoltage.getSelectedItem().toString();
        String driverType3 = spnDriverType3LowVoltage.getSelectedItem().toString();

        if(!TextUtils.isEmpty(property) && !TextUtils.isEmpty(driverType1) && !TextUtils.isEmpty(driverType2) && !TextUtils.isEmpty(driverType3) && isBtnAreaSelected){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(3, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(3, false);
        }

        /** En caso de tener los campos necesarios completos, puede pasar a la siguiente */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            return true;
        }else {
            if (!TextUtils.isEmpty(property) && !TextUtils.isEmpty(driverType1) && !TextUtils.isEmpty(driverType2) && !TextUtils.isEmpty(driverType3) && isBtnAreaSelected) {
                return true;
            }
        }

        /** Se validan los campos para ofrecer retroalimentación */
        if(TextUtils.isEmpty(property)){
            TextView errorText = (TextView) spnPropertyLowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(driverType1)){
            TextView errorText = (TextView) spnDriverType1LowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(driverType2)){
            TextView errorText = (TextView) spnDriverType2LowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(driverType3)){
            TextView errorText = (TextView) spnDriverType3LowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(isBtnAreaSelected == false){
            Utils.toastMessage("Debe de seleccionar una tipo de área.",1,getContext());
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSectionActivity lowVoltageSectionActivity = Globals.lowVoltageSectionActivity;
            spnPropertyLowVoltage.setSelection(((ArrayAdapter<String>)spnPropertyLowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrProperty()));
            isBtnAreaSelected = Utils.changeButtonSelectionFromDB(buttonListArea, lowVoltageSectionActivity.getStrAreaType(), Globals.mapLowVoltageSectionActivityData, "areaType", isBtnAreaSelected, getContext());
            spnDriverType1LowVoltage.setSelection(((ArrayAdapter<String>)spnDriverType1LowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrConductorType1()));
            spnDriverType2LowVoltage.setSelection(((ArrayAdapter<String>)spnDriverType2LowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrConductorType2()));
            spnDriverType3LowVoltage.setSelection(((ArrayAdapter<String>)spnDriverType3LowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrConductorType3()));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){

        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar EditText si la actividad se encuentra finalizada*/
            Utils.changeSpinnerStyleDisable(spnPropertyLowVoltage, getContext());
            Utils.changeSpinnerStyleDisable(spnDriverType1LowVoltage, getContext());
            Utils.changeSpinnerStyleDisable(spnDriverType2LowVoltage, getContext());
            Utils.changeSpinnerStyleDisable(spnDriverType3LowVoltage, getContext());

            btnRuralLowVoltage.setEnabled(false);
            btnUrbanLowVoltage.setEnabled(false);
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(spnPropertyLowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPropertyLowVoltage, ivInfoPropertyLowVoltage, getContext());
            }
            if(isBtnAreaSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvAreaTypeLowVoltage, ivInfoAreaTypeLowVoltage, getContext());
            }
            if(TextUtils.isEmpty(spnDriverType1LowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvDriverType1LowVoltage, ivInfoDriverType1LowVoltage, getContext());
            }
            if(TextUtils.isEmpty(spnDriverType2LowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvDriverType2LowVoltage, ivInfoDriverType2LowVoltage, getContext());
            }
            if(TextUtils.isEmpty(spnDriverType3LowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvDriverType3LowVoltage, ivInfoDriverType3LowVoltage, getContext());
            }
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerSpinner(spnPropertyLowVoltage, Globals.mapLowVoltageSectionActivityData, "property");
        Utils.addListenerSpinner(spnDriverType1LowVoltage, Globals.mapLowVoltageSectionActivityData, "conductorType1");
        Utils.addListenerSpinner(spnDriverType2LowVoltage, Globals.mapLowVoltageSectionActivityData, "conductorType2");
        Utils.addListenerSpinner(spnDriverType3LowVoltage, Globals.mapLowVoltageSectionActivityData, "conductorType3");
    }
}