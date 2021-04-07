package com.example.artikolevyinf.Fragments.InfrastructureSurvey.LowVoltageSupport;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
public class LowVoltageSupport6ActivityFragment extends Fragment {

    ImageView ivInfoDetained, ivInfoInsulatorType, ivInfoSupportType, ivInfoAreaType, ivInfoNetType;
    TextView tvDetained, tvInsulatorType, tvSupportType, tvAreaType, tvNetType;

    Button btnDetainedYes, btnDetainedPending, btnDetainedNo;
    Button btnInsulatorTypePorcelain, btnInsulatorTypePolymeric, btnInsulatorTypeHybrid;
    Button btnSupportTypeMediumPower, btnSupportTypeMediumLowPower, btnSupportTypeLowPower;
    Button btnAreaTypeRural, btnAreaTypeUrban;
    Button btnCommonNet, btnBraidedNet;
    Button btnNextActivity, btnPreviousActivity;

    ArrayList<Button> buttonListDetained, buttonListInsulatorType, buttonListSupportType, buttonListAreaType, buttonListNetType;
    boolean isBtnDetainedSelected = false, isBtnInsulatorTypeSelected = false, isBtnSupportTypeSelected = false, isBtnAreaTypeSelected = false, isBtnNetTypeSelected;

    int intTextUnselectedFilter, intTextSelectedFilter;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter;

    public LowVoltageSupport6ActivityFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_support6_activity, container, false);

        /** Colores y background para los botones*/
        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        /** Grupos de botones*/
        btnDetainedYes = rootView.findViewById(R.id.detained_yes_btn_lvSupport);
        btnDetainedPending = rootView.findViewById(R.id.detained_pending_btn_lvSupport);
        btnDetainedNo = rootView.findViewById(R.id.detained_no_btn_lvSupport);
        buttonListDetained = new ArrayList<Button>() {{ add(btnDetainedYes); add(btnDetainedPending); add(btnDetainedNo); }};

        btnInsulatorTypePorcelain = rootView.findViewById(R.id.type_insulator_porcelain_btn_lvSupport);
        btnInsulatorTypePolymeric = rootView.findViewById(R.id.type_insulator_polymeric_btn_lvSupport);
        btnInsulatorTypeHybrid = rootView.findViewById(R.id.type_insulator_hybrid_btn_lvSupport);
        buttonListInsulatorType = new ArrayList<Button>() {{ add(btnInsulatorTypePorcelain); add(btnInsulatorTypePolymeric); add(btnInsulatorTypeHybrid); }};

        btnSupportTypeMediumPower = rootView.findViewById(R.id.type_support_medium_power_btn_lvSupport);
        btnSupportTypeMediumLowPower = rootView.findViewById(R.id.type_support_medium_low_power_btn_lvSupport);
        btnSupportTypeLowPower = rootView.findViewById(R.id.type_support_low_power_btn_lvSupport);
        buttonListSupportType = new ArrayList<Button>() {{ add(btnSupportTypeMediumPower); add(btnSupportTypeMediumLowPower); add(btnSupportTypeLowPower); }};

        btnAreaTypeRural = rootView.findViewById(R.id.type_area_rural_btn_lvSupport);
        btnAreaTypeUrban = rootView.findViewById(R.id.type_area_urban_btn_lvSupport);
        buttonListAreaType = new ArrayList<Button>() {{ add(btnAreaTypeRural); add(btnAreaTypeUrban); }};

        btnCommonNet = rootView.findViewById(R.id.netType_common_btn_lvSupport);
        btnBraidedNet = rootView.findViewById(R.id.netType_braided_btn_lvSupport);
        buttonListNetType = new ArrayList<Button>() {{ add(btnCommonNet); add(btnBraidedNet); }};

        btnNextActivity = rootView.findViewById(R.id.next_btn_lvSupport6);
        btnPreviousActivity = rootView.findViewById(R.id.previous_btn_lvSupport6);

        tvDetained = rootView.findViewById(R.id.textView1);
        tvInsulatorType = rootView.findViewById(R.id.textView2);
        tvSupportType = rootView.findViewById(R.id.textView3);
        tvAreaType = rootView.findViewById(R.id.textView4);
        tvNetType = rootView.findViewById(R.id.textView5);

        ivInfoDetained = rootView.findViewById(R.id.info_ic_detained_iv_lvSupport);
        ivInfoInsulatorType = rootView.findViewById(R.id.info_ic_type_insulator_iv_lvSupport);
        ivInfoSupportType = rootView.findViewById(R.id.info_ic_type_support_iv_lvSupport);
        ivInfoAreaType = rootView.findViewById(R.id.info_ic_type_area_iv_lvSupport);
        ivInfoNetType = rootView.findViewById(R.id.info_ic_netType_iv_lvSupport);

        /** Mensaje toast para los iconos de información */
        ivInfoDetained.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoInsulatorType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoSupportType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoAreaType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoNetType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });


        /** OnClic para los botones de la interfaz, se cambia el color de los mismos al seleccionarlo */
        btnDetainedYes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListDetained, btnDetainedYes, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnDetainedSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("detained", "Si");
            }
        });
        btnDetainedPending.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListDetained, btnDetainedPending, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnDetainedSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("detained", "Pendiente");
            }
        });
        btnDetainedNo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListDetained, btnDetainedNo, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnDetainedSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("detained", "No");
            }
        });
        /** */
        btnInsulatorTypePorcelain.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListInsulatorType, btnInsulatorTypePorcelain, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnInsulatorTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("insulatorType", "Porcelana");
            }
        });
        btnInsulatorTypePolymeric.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListInsulatorType, btnInsulatorTypePolymeric, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnInsulatorTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("insulatorType", "Polimérico");
            }
        });
        btnInsulatorTypeHybrid.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListInsulatorType, btnInsulatorTypeHybrid, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnInsulatorTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("insulatorType", "Híbrido");
            }
        });
        /** */
        btnSupportTypeMediumPower.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListSupportType, btnSupportTypeMediumPower, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnSupportTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("supportType", "Media Tensión");
            }
        });
        btnSupportTypeMediumLowPower.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListSupportType, btnSupportTypeMediumLowPower, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnSupportTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("supportType", "Media y Baja Tensión");
            }
        });
        btnSupportTypeLowPower.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListSupportType, btnSupportTypeLowPower, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnSupportTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("supportType", "Baja Tensión");
            }
        });
        /** */
        btnAreaTypeRural.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListAreaType, btnAreaTypeRural, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnAreaTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("areaType", "Rural");
            }
        });
        btnAreaTypeUrban.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListAreaType, btnAreaTypeUrban, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnAreaTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("areaType", "Urbano");
            }
        });
        /** */
        btnCommonNet.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListNetType, btnCommonNet, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnNetTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("netType", "Red Común");
            }
        });
        btnBraidedNet.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListNetType, btnBraidedNet, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnNetTypeSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("netType", "Red Trenzada");
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

        /** En caso de que sea una información fallida o ejecutada, se trae la información de SQLite */
        getInformationFromDB();

        /** Se cambia el color de los titulos y el icono en caso de que este finalizada o fallida en los campos opcionales vacios */
        changeTitleColorIconColor();

        /** En caso de ser una actividad finalizada completa, esta no podrá ser editada, las fallidas no tendrán habilitado el botón de marcar como fallida */
        disableElementsByState();

        return rootView;
    }

    private boolean verifyInformation(){

        if(isBtnDetainedSelected && isBtnInsulatorTypeSelected && isBtnSupportTypeSelected && isBtnAreaTypeSelected){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(5, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(5, false);
        }

        if(isBtnDetainedSelected == false){
            Utils.toastMessage("Debe de seleccionar una opción de retenida.",1,getContext());
        }
        if(isBtnInsulatorTypeSelected == false){
            Utils.toastMessage("Debe de seleccionar una tipo de aislador.",1,getContext());
        }
        if(isBtnSupportTypeSelected == false){
            Utils.toastMessage("Debe de seleccionar una tipo de apoyo.",1,getContext());
        }
        if(isBtnAreaTypeSelected == false){
            Utils.toastMessage("Debe de seleccionar una tipo de área.",1,getContext());
        }
        if(isBtnNetTypeSelected == false){
            Utils.toastMessage("Debe de seleccionar una tipo de área.",1,getContext());
        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSupportActivity lowVoltageSupportActivity = Globals.lowVoltageSupportActivity;
            isBtnDetainedSelected = Utils.changeButtonSelectionFromDB(buttonListDetained, lowVoltageSupportActivity.getStrDetained(), Globals.mapLowVoltageSupportActivityData, "pass", isBtnDetainedSelected, getContext());
            isBtnInsulatorTypeSelected = Utils.changeButtonSelectionFromDB(buttonListInsulatorType, lowVoltageSupportActivity.getStrInsulatorType(), Globals.mapLowVoltageSupportActivityData, "portico", isBtnInsulatorTypeSelected, getContext());
            isBtnSupportTypeSelected = Utils.changeButtonSelectionFromDB(buttonListSupportType, lowVoltageSupportActivity.getStrSupportType(), Globals.mapLowVoltageSupportActivityData, "portico", isBtnSupportTypeSelected, getContext());
            isBtnAreaTypeSelected = Utils.changeButtonSelectionFromDB(buttonListAreaType, lowVoltageSupportActivity.getStrAreaType(), Globals.mapLowVoltageSupportActivityData, "Province", isBtnAreaTypeSelected, getContext());
            isBtnNetTypeSelected = Utils.changeButtonSelectionFromDB(buttonListNetType, lowVoltageSupportActivity.getStrNetType(), Globals.mapLowVoltageSupportActivityData, "grounding", isBtnNetTypeSelected, getContext());
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(isBtnDetainedSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvDetained, ivInfoDetained, getContext());
            }
            if(isBtnInsulatorTypeSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvInsulatorType, ivInfoInsulatorType, getContext());
            }
            if(isBtnSupportTypeSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvSupportType, ivInfoSupportType, getContext());
            }
            if(isBtnAreaTypeSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvAreaType, ivInfoAreaType, getContext());
            }
            if(isBtnNetTypeSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvNetType, ivInfoNetType, getContext());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){

        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            btnDetainedYes.setEnabled(false);
            btnDetainedPending.setEnabled(false);
            btnDetainedNo.setEnabled(false);

            btnInsulatorTypePorcelain.setEnabled(false);
            btnInsulatorTypePolymeric.setEnabled(false);
            btnInsulatorTypeHybrid.setEnabled(false);

            btnSupportTypeMediumPower.setEnabled(false);
            btnSupportTypeMediumLowPower.setEnabled(false);
            btnSupportTypeLowPower.setEnabled(false);

            btnAreaTypeRural.setEnabled(false);
            btnAreaTypeUrban.setEnabled(false);

            btnCommonNet.setEnabled(false);
            btnBraidedNet.setEnabled(false);
        }
    }
}
