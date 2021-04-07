package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport;

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
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSupportActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LowVoltageSupport5ActivityFragment extends Fragment {

    ImageView ivInfoPass, ivInfoPortico, ivInfoProvince, ivInfoGrounding, ivInfoProperty;
    TextView tvPass, tvPortico, tvProvince, tvGrounding, tvProperty;

    Button btnPassYes, btnPassPending, btnPassNo;
    Button btnPorticoYes, btnPorticoPending, btnPorticoNo;
    Button btnProvinceGuajira, btnProvinceMagdalena, btnProvinceAtlantico;
    Button btnGroundingYes, btnGroundingNo;
    Button btnNextActivity, btnPreviousActivity;

    Spinner spnProperty;

    ArrayList<Button> buttonListPass, buttonListPortico, buttonListProvince, buttonListGrounding;
    boolean isBtnPassSelected = false, isBtnPorticoSelected = false, isBtnProvinceSelected = false, isBtnGroundingSelected = false;

    int intTextUnselectedFilter, intTextSelectedFilter;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter;

    public LowVoltageSupport5ActivityFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_support5_activity, container, false);

        /** Colores y background para los botones*/
        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        /** Grupos de botones*/
        btnPassYes = rootView.findViewById(R.id.pass_yes_btn_lvSupport);
        btnPassPending = rootView.findViewById(R.id.pass_pending_btn_lvSupport);
        btnPassNo = rootView.findViewById(R.id.pass_no_btn_lvSupport);
        buttonListPass = new ArrayList<Button>() {{ add(btnPassYes); add(btnPassPending); add(btnPassNo); }};

        btnPorticoYes = rootView.findViewById(R.id.portico_yes_btn_lvSupport);
        btnPorticoPending = rootView.findViewById(R.id.portico_pending_btn_lvSupport);
        btnPorticoNo = rootView.findViewById(R.id.portico_no_btn_lvSupport);
        buttonListPortico = new ArrayList<Button>() {{ add(btnPorticoYes); add(btnPorticoPending); add(btnPorticoNo); }};

        btnProvinceGuajira = rootView.findViewById(R.id.province_guajira_btn_lvSupport);
        btnProvinceMagdalena = rootView.findViewById(R.id.province_magdalena_btn_lvSupport);
        btnProvinceAtlantico = rootView.findViewById(R.id.province_atlantico_btn_lvSupport);
        buttonListProvince = new ArrayList<Button>() {{ add(btnProvinceGuajira); add(btnProvinceMagdalena); add(btnProvinceAtlantico); }};

        btnGroundingYes = rootView.findViewById(R.id.grounding_yes_btn_lvSupport);
        btnGroundingNo = rootView.findViewById(R.id.grounding_no_btn_lvSupport);
        buttonListGrounding = new ArrayList<Button>() {{ add(btnGroundingYes); add(btnGroundingNo); }};

        btnPreviousActivity = rootView.findViewById(R.id.previous_btn_lvSupport5);
        btnNextActivity = rootView.findViewById(R.id.next_btn_lvSupport5);

        /** Texview y imageView*/
        tvPass = rootView.findViewById(R.id.textView1);
        tvPortico = rootView.findViewById(R.id.textView2);
        tvProperty = rootView.findViewById(R.id.textView3);
        tvProvince = rootView.findViewById(R.id.textView4);
        tvGrounding = rootView.findViewById(R.id.textView5);

        ivInfoPass = rootView.findViewById(R.id.info_ic_pass_iv_lvSupport);
        ivInfoPortico = rootView.findViewById(R.id.info_ic_portico_iv_lvSupport);
        ivInfoProperty = rootView.findViewById(R.id.info_ic_property_iv_lvSupport);
        ivInfoProvince = rootView.findViewById(R.id.info_ic_province_iv_lvSupport);
        ivInfoGrounding = rootView.findViewById(R.id.info_ic_grounding_iv_lvSupport);

        /** spinner*/
        spnProperty = rootView.findViewById(R.id.property_spn_lvSupport);

        /** Se incluye la información a los Spinners */
        String[] optionProperty = {"","AIR-E","PARTICULAR","MIXTO","CENS","MYPIMES","SIN PROPIEDAD","PUBLICA","STN"};
        ArrayAdapter<String> adapterProperty = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionProperty);
        spnProperty.setAdapter(adapterProperty);

        /** Mensaje toast para los iconos de información */
        ivInfoPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoPortico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoGrounding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });

        /** OnClic para los botones de la interfaz, se cambia el color de los mismos al seleccionarlo */
        btnPassYes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPass, btnPassYes, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnPassSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("pass", "Si");
            }
        });
        btnPassPending.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPass, btnPassPending, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnPassSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("pass", "Pendiente");
            }
        });
        btnPorticoNo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPass, btnPorticoNo, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnPassSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("pass", "No");
            }
        });
        /** */
        btnPorticoYes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPortico, btnPorticoYes, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnPorticoSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("portico", "Si");
            }
        });
        btnPorticoPending.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPortico, btnPorticoPending, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnPorticoSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("portico", "Pendiente");
            }
        });
        btnPorticoNo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListPortico, btnPorticoNo, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnPorticoSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("portico", "No");
            }
        });
        /** */
        btnProvinceGuajira.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListProvince, btnProvinceGuajira, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnProvinceSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("Province", "Guajira");
            }
        });
        btnProvinceMagdalena.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListProvince, btnProvinceMagdalena, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnProvinceSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("Province", "Magdalena");
            }
        });
        btnProvinceAtlantico.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListProvince, btnProvinceAtlantico, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnProvinceSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("Province", "Atlántico");
            }
        });
        /** */
        btnGroundingYes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListGrounding, btnGroundingYes, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnGroundingSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("grounding", "Si");
            }
        });
        btnGroundingNo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListGrounding, btnGroundingNo, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnGroundingSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("grounding", "No");
            }
        });
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

        return rootView;
    }

    private boolean verifyInformation(){

        String property = spnProperty.getSelectedItem().toString();

        if(!TextUtils.isEmpty(property) && isBtnPassSelected && isBtnPorticoSelected && isBtnProvinceSelected && isBtnGroundingSelected){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(4, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(4, false);
        }

        if(TextUtils.isEmpty(property)){
            TextView errorText = (TextView) spnProperty.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }

        if(isBtnPassSelected == false){
            Utils.toastMessage("Debe de seleccionar una opción de paso aéreo/subterráneo.",1,getContext());
        }
        if(isBtnPorticoSelected == false){
            Utils.toastMessage("Debe de seleccionar una opción de pórtico.",1,getContext());
        }
        if(isBtnProvinceSelected == false){
            Utils.toastMessage("Debe de seleccionar una opción de provincia.",1,getContext());
        }
        if(isBtnGroundingSelected == false){
            Utils.toastMessage("Debe de seleccionar una opción de puesta tierra.",1,getContext());
        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSupportActivity lowVoltageSupportActivity = Globals.lowVoltageSupportActivity;
            isBtnPassSelected = Utils.changeButtonSelectionFromDB(buttonListPass, lowVoltageSupportActivity.getStrPass(), Globals.mapLowVoltageSupportActivityData, "pass", isBtnPassSelected, getContext());
            isBtnPorticoSelected = Utils.changeButtonSelectionFromDB(buttonListPortico, lowVoltageSupportActivity.getStrPortico(), Globals.mapLowVoltageSupportActivityData, "portico", isBtnPorticoSelected, getContext());
            spnProperty.setSelection(((ArrayAdapter<String>)spnProperty.getAdapter()).getPosition(lowVoltageSupportActivity.getStrProperty()));
            isBtnProvinceSelected = Utils.changeButtonSelectionFromDB(buttonListProvince, lowVoltageSupportActivity.getStrProvince(), Globals.mapLowVoltageSupportActivityData, "Province", isBtnProvinceSelected, getContext());
            isBtnGroundingSelected = Utils.changeButtonSelectionFromDB(buttonListGrounding, lowVoltageSupportActivity.getStrGrounding(), Globals.mapLowVoltageSupportActivityData, "grounding", isBtnGroundingSelected, getContext());
        }
    }

    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerSpinner(spnProperty, Globals.mapLowVoltageSupportActivityData, "property");
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(spnProperty.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvProperty, ivInfoProperty, getContext());
            }

            if(isBtnPassSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPass, ivInfoPass, getContext());
            }
            if(isBtnPorticoSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvPortico, ivInfoPortico, getContext());
            }
            if(isBtnProvinceSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvProvince, ivInfoProvince, getContext());
            }
            if(isBtnGroundingSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvGrounding, ivInfoGrounding, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){

        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar EditText si la actividad se encuentra finalizada*/
            Utils.changeSpinnerStyleDisable(spnProperty, getContext());

            btnPassYes.setEnabled(false);
            btnPassPending.setEnabled(false);
            btnPassNo.setEnabled(false);

            btnPorticoYes.setEnabled(false);
            btnPorticoPending.setEnabled(false);
            btnPorticoNo.setEnabled(false);

            btnProvinceGuajira.setEnabled(false);
            btnProvinceMagdalena.setEnabled(false);
            btnProvinceAtlantico.setEnabled(false);

            btnGroundingYes.setEnabled(false);
            btnGroundingNo.setEnabled(false);
        }
    }
}
