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
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;


public class LowVoltageSupport2ActivityFragment extends Fragment {

    Button btnYesShared, btnPendingShared, btnNoShared, btnInServiceState, btnDisconnectedState, btnNextActivity, btnPreviousActivity;
    Spinner spnStructure;
    SearchableSpinner spnStructureBT, spnStructureMT;
    TextView tvShared, tvStateSupport, tvStructure,  tvStructureBT, tvStructureMT;
    ImageView ivInfoShared, ivInfoStateSupport, ivInfoStructure,  ivInfoStructureBT, ivInfoStructureMT;

    int intTextUnselectedFilter, intTextSelectedFilter;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter;

    ArrayList<Button> buttonListShared;
    ArrayList<Button> buttonListState;
    boolean isBtnSharedSelected = false;
    boolean isBtnStateSelected = false;

    public LowVoltageSupport2ActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_support2_activity, container, false);

        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);
        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        btnYesShared = rootView.findViewById(R.id.yesShared_btn_lvSupport);
        btnPendingShared = rootView.findViewById(R.id.pendingShared_btn_lvSupport);
        btnNoShared = rootView.findViewById(R.id.noShared_btn_lvSupport);
        buttonListShared = new ArrayList<Button>(){{ add(btnYesShared); add(btnPendingShared); add(btnNoShared); }};

        btnInServiceState = rootView.findViewById(R.id.inServiceState_btn_lvSupport);
        btnDisconnectedState = rootView.findViewById(R.id.disconnectedState_btn_lvSupport);
        buttonListState = new ArrayList<Button>(){{ add(btnInServiceState); add(btnDisconnectedState); }};

        btnNextActivity = rootView.findViewById(R.id.next_btn_lvSupport2);
        btnPreviousActivity = rootView.findViewById(R.id.previous_btn_lvSupport2);
        spnStructure = rootView.findViewById(R.id.structure_spn_lvSupport);
        spnStructureBT = rootView.findViewById(R.id.structureBT_spn_lvSupport);
        spnStructureMT = rootView.findViewById(R.id.structureMT_spn_lvSupport);
        tvShared = rootView.findViewById(R.id.textView1);
        tvStateSupport = rootView.findViewById(R.id.textView2);
        tvStructure = rootView.findViewById(R.id.textView3);
        tvStructureBT = rootView.findViewById(R.id.textView4);
        tvStructureMT = rootView.findViewById(R.id.textView5);
        ivInfoShared = rootView.findViewById(R.id.info_ic_shared_iv_lvSupport);
        ivInfoStateSupport = rootView.findViewById(R.id.supportState_iv_lvSupport);
        ivInfoStructure = rootView.findViewById(R.id.info_ic_structure_iv_lvSupport);
        ivInfoStructureBT = rootView.findViewById(R.id.info_ic_structureBT_iv_lvSupport);
        ivInfoStructureMT = rootView.findViewById(R.id.info_ic_structureMT_iv_lvSupport);

        /** Retroalimentación de los iconos de información */
        ivInfoShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoStateSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoStructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoStructureBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoStructureMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });

        /** Botones de la página */
        btnYesShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListShared, btnYesShared, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnSharedSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("shared", "Si");
            }
        });
        btnPendingShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListShared, btnPendingShared, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnSharedSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("shared", "Pendiente");
            }
        });
        btnNoShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListShared, btnNoShared, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnSharedSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("shared", "No");
            }
        });
        btnInServiceState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListState, btnInServiceState, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnStateSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("state", "En servicio");
            }
        });
        btnDisconnectedState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(buttonListState, btnDisconnectedState, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isBtnStateSelected = true;
                Globals.mapLowVoltageSupportActivityData.put("state", "Desconectado");
            }
        });
        /** Botones para la siguiente y anterior actividad */
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

        /** Se incluye la información a los Spinners */
        String[] optionStructure = {"","Suspensión","Poste simple - Suspensión","Poste simple - Retención","Postes en H - Retención","Postes en H - Suspensión","Retención"};
        ArrayAdapter<String> adapterStructure = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, optionStructure);
        spnStructure.setAdapter(adapterStructure);
        String[] optionStructureBT = {"","MT 111-Tipo Bandera-Una fase-FL","MT 522-Tipo Compacta-Dos fases-AL Y ANG < 5","MT 521-Tipo Compacta-Dos fases-FL","MT 514-Tipo Compacta-Una fase-ANG 20-60 Y 30-60","MT 513-Tipo Compacta-Una fase-ANG 5-30","MT 512-Tipo Compacta-Una fase-AL Y ANG < 5","MT 511-Tipo Compacta-Una fase-FL","MT 435-Tipo Vertical-Tres fases-ANG 60-90","MT 434-Tipo Vertical-Tres fases-ANG 20-60 Y 30-60","MT 433-Tipo Vertical-Tres fases-ANG 5-30","BT 33_Especial_ANG 60-90","BT 32_Especial_AL Y ANG < 5","BT 31_Especial_FL","BT 26_Grapas Y Pinzas_ANC Doble fin De línea","BT 25_Grapas Y Pinzas_ANG 60-90","BT 22_Grapas Y Pinzas_AL Y ANG < 5","BT 21_Grapas Y Pinzas_AL","BT 16_Aislador Carrete_ANG Doble fin De línea","BT 15_Aislador Carrete_ANG 60-90","PENDIENTE","BT 45_Tipo Acometida_ANG 60-90","BT 42_Tipo Acometida_AL Y ANG < 5","BT 41_Tipo Acometida_FL","BT 36_Especial_ANC Doble fin De linea","BT 12_Aislador Carrete_AL Y ANG < 5","BT 11_Aislador Carrete_FL","MT 534-Tipo Compacta-Tres fases-ANG 20-60 Y 30-60","MT 533-Tipo Compacta-Tres fases-ANG 5-30","MT 532-Tipo Compacta-Tres fases-AL Y ANG < 5","MT 531-Tipo Compacta-Tres fases-FL","MT 524-Tipo Compacta-Dos fases-ANG 20-60 Y 30-60","MT 523-Tipo Compacta-Dos fases-ANG 5-30","MT 432-Tipo Vertical-Tres fases-AL Y ANG < 5","MT 431-Tipo Vertical-Tres fases-FL","MT 425-Tipo Vertical-Dos fases-ANG 60-90","MT 424-Tipo Vertical-Dos fases-ANG 20-60 Y 30-60","MT 423-Tipo Vertical-Dos fases-ANG 5-30","MT 422-Tipo Vertical-Dos fases-AL Y ANG < 5","MT 421-Tipo Vertical-Dos fases-FL","MT 312-Tipo Horizontal-Una fase-AL Y ANG < 5","MT 311-Tipo Horizontal-Una fase-FL","MT 234-Tipo Triangular Vano Largo-Tres fases-ANG 20-60 Y 30-60","MT 233-Tipo Triangular Vano Largo-Tres fases-ANG 5-30","MT 232-Tipo Triangular Vano Largo-Tres fases-AL Y ANG < 5","MT 224-Tipo Triangular Vano Largo-Dos fases-ANG 20-60 Y 30-60","MT 223-Tipo Triangular Vano Largo-Dos fases-ANG 5-30","MT 222-Tipo Triangular Vano Largo-Dos fases-AL Y ANG < 5","MT 214-Tipo Triangular Vano Largo-Una fase-ANG 20-60 Y 30-60","MT 331-Tipo Horizontal-Tres fases-FL","MT 325-Tipo Horizontal-Dos fases-ANG 60-90","MT 324-Tipo Horizontal-Dos fases-ANG 20-60 Y 30-60","MT 323-Tipo Horizontal-Dos fases-ANG 5-30","MT 322-Tipo Horizontal-Dos fases-AL Y ANG < 5","MT 321-Tipo Horizontal-Dos fases-FL","MT 315-Tipo Horizontal-Una fase-ANG 60-90","MT 314-Tipo Horizontal-Una fase-ANG 20-60 Y 30-60","MT 313-Tipo Horizontal-Una fase-ANG 5-30","MT 415-Tipo Vertical-Una fase-ANG 60-90","MT 414-Tipo Vertical-Una fase-ANG 20-60 Y 30-60","MT 413-Tipo Vertical-Una fase-ANG 5-30","MT 412-Tipo Vertical-Una fase-AL Y ANG < 5","MT 411-Tipo Vertical-Una fase-FL","MT 335-Tipo Horizontal-Tres fases-ANG 60-90","MT 334-Tipo Horizontal-Tres fases-ANG 20-60 Y 30-60","MT 333-Tipo Horizontal-Tres fases-ANG 5-30","MT 332-Tipo Horizontal-Tres fases-AL Y ANG < 5","MT 213-Tipo Triangular Vano Largo-Una fase-ANG 5-30","MT 212-Tipo Triangular Vano Largo-Una fase-AL Y ANG < 5","MT 134-Tipo Bandera-Tres fases-ANG 20-60 Y 30-60","MT 133-Tipo Bandera-Tres fases-ANG 5-30","MT 132-Tipo Bandera-Tres fases-AL Y ANG < 5","MT 131-Tipo Bandera-Tres fases-FL","MT 124-Tipo Bandera-Dos fases-ANG 20-60 Y 30-60","MT 123-Tipo Bandera-Dos fases-ANG 5-30","MT 112-Tipo Bandera-Una fase-AL Y ANG < 5","MT 113-Tipo Bandera-Una fase-ANG 5-30","MT 114-Tipo Bandera-Una fase-ANG 20-60 Y 30-60","MT 122-Tipo Bandera-Dos fases-AL Y ANG < 5","MT 121-Tipo Bandera-Dos fases-FL"};
        ArrayAdapter<String> adapterFoundationBT = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionStructureBT);
        spnStructureBT.setTitle("Estructuras BT");
        spnStructureBT.setPositiveButton("Aceptar");
        spnStructureBT.setAdapter(adapterFoundationBT);
        String[] optionStructureMT = {"","TRIPLE POSTE BANDERA","RETENCION TRIPLE POSTE","RETENCION ANGULO TRIPLE POSTE","DOBLE POSTE H DE PASO","DOBLE CIRUITO LATERAL","H DE PASO DOBLE CIRCUITO","SEMIBANDERA DOBLE","SUSPENSION DOBLE BANDERA","RG","RGH","SRGH","SGH","SG","SMB","ETC-AISLADORES-SUSPE Y L POST 13.2KV Y ANG MAX 60?","AISLADORES-SUSPENSION- L POST 13.2KV Y ANG MAX 90?","ECB-AISL-AISDOR L POST ANSI57-2 13.2KV Y ANG 50MAX","PENDIENTE","AG + AC (Angulo + Anclaje) Matriz Doble Circuito - Retención","AC + FL (Anclaje) Matriz Doble Circuito - Retención","AL + AC (Alineación + Anclaje) Matriz Doble Circuito - Retención","AL+FL (Alineación+ Fin línea) Matriz Doble Circuito","AC (Anclaje) Matriz Circuito Sencillo - Retención","Armado (Angulo 60-90) Matriz Circuito Sencillo - Retención","AG + AG (Angulo) Matriz Doble Circuito - Retención","AL (Alineación) Matriz Circuito Sencillo -Suspensión","AL + AL (Alineación󮠩 Matriz Doble Circuito -Suspensión","AG (Angulo) Matriz Circuito Sencillo -Retención","FL+ FL (Fin de Línea) Matriz Doble Circuito - Retención","FL (Fin de Línea) Matriz Circuito Sencillo -Retención","AC + AC (Anclaje) Matriz Doble Circuito - Retención","DPC","EHC103","EHC144","EHC142","EHC141","RGM","SUSPENSION DOBLE","ANGULO FUERTE EN H","RETENIDA DOBLE CIRCUITO"};
        ArrayAdapter<String> adapterFoundationMT = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionStructureMT);
        spnStructureMT.setTitle("Estructuras MT");
        spnStructureMT.setPositiveButton("Aceptar");
        spnStructureMT.setAdapter(adapterFoundationMT);

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
        String structure = spnStructure.getSelectedItem().toString();
        String structureBT = spnStructureBT.getSelectedItem().toString();
        String structureMT = spnStructureMT.getSelectedItem().toString();

        if( !TextUtils.isEmpty(structure) && !TextUtils.isEmpty(structureBT) && !TextUtils.isEmpty(structureMT) && isBtnStateSelected && isBtnSharedSelected){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(1, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(1, false);
        }

        /** Se validan los campos para ofrecer retroalimentación */
        if(TextUtils.isEmpty(structure)){
            TextView errorText = (TextView) spnStructure.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(structureBT)){
            TextView errorText = (TextView) spnStructureBT.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(structureMT)){
            TextView errorText = (TextView) spnStructureMT.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(isBtnSharedSelected == false){
            Utils.toastMessage("Debe de seleccionar una opción compartida.",1,getContext());
        }
        if(isBtnStateSelected == false){
            Utils.toastMessage("Debe de seleccionar un estado del tramo.",1,getContext());
        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSupportActivity lowVoltageSupportActivity = Globals.lowVoltageSupportActivity;
            isBtnSharedSelected = Utils.changeButtonSelectionFromDB(buttonListShared, lowVoltageSupportActivity.getStrShared(), Globals.mapLowVoltageSupportActivityData, "shared", isBtnSharedSelected, getContext());
            isBtnStateSelected = Utils.changeButtonSelectionFromDB(buttonListState, lowVoltageSupportActivity.getStrStateSection(), Globals.mapLowVoltageSupportActivityData, "state", isBtnStateSelected, getContext());
            spnStructure.setSelection(((ArrayAdapter<String>)spnStructure.getAdapter()).getPosition(lowVoltageSupportActivity.getStrStructure()));
            spnStructureBT.setSelection(((ArrayAdapter<String>)spnStructureBT.getAdapter()).getPosition(lowVoltageSupportActivity.getStrStructureBT()));
            spnStructureMT.setSelection(((ArrayAdapter<String>)spnStructureMT.getAdapter()).getPosition(lowVoltageSupportActivity.getStrStructureMT()));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){
        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar spinner si la actividad se encuentra finalizada*/
            Utils.changeSpinnerStyleDisable(spnStructure,getContext());
            Utils.changeSpinnerStyleDisable(spnStructureBT,getContext());
            Utils.changeSpinnerStyleDisable(spnStructureMT,getContext());

            btnNoShared.setEnabled(false);
            btnPendingShared.setEnabled(false);
            btnYesShared.setEnabled(false);
            btnInServiceState.setEnabled(false);
            btnDisconnectedState.setEnabled(false);
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(spnStructure.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvStructure, ivInfoStructure, getContext());
            }
            if(TextUtils.isEmpty(spnStructureBT.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvStructureBT, ivInfoStructureBT, getContext());
            }
            if(TextUtils.isEmpty(spnStructureMT.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvStructureMT, ivInfoStructureMT, getContext());
            }

        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerSpinner(spnStructure, Globals.mapLowVoltageSupportActivityData, "structure");
        Utils.addListenerSpinner(spnStructureBT, Globals.mapLowVoltageSupportActivityData, "structureBT");
        Utils.addListenerSpinner(spnStructureMT, Globals.mapLowVoltageSupportActivityData, "structureMT");
    }
}