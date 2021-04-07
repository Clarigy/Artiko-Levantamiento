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

import com.example.artikolevyinf.MainActivity;
import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSectionActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class LowVoltageSection5ActivityFragment extends Fragment {

    Spinner spnResourcesUseLowVoltage;
    SearchableSpinner spnNeutralDriverLowVoltage;
    ImageView ivInfoNeutralDriverLowVoltage, ivInfoSectionTypeLowVoltage, ivInfoResourcesUseLowVoltage;
    Button btnTernaLowVoltage, btnPhasesLowVoltage, btnPreviousLowVoltage, btnFinishLowVoltage;
    TextView tvNeutralDriverLowVoltage, tvSectionTypeLowVoltage, tvResourcesUseLowVoltage;

    int intTextUnselectedFilter, intTextSelectedFilter;
    Drawable dwBackgroundUnselectedFilter, dwBackgroundSelectedFilter;

    ArrayList<Button> arrayButtons;
    boolean isSectionSelected = false;

    public LowVoltageSection5ActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_section5_activity, container, false);

        intTextSelectedFilter = getResources().getColor(R.color.colorWhite);
        intTextUnselectedFilter = getResources().getColor(R.color.colorBlueDark);

        dwBackgroundSelectedFilter = getResources().getDrawable(R.drawable.button_blue);
        dwBackgroundUnselectedFilter = getResources().getDrawable(R.drawable.unselected_button);

        spnNeutralDriverLowVoltage = rootView.findViewById(R.id.neutralDriver_spn_lowVoltage);
        spnResourcesUseLowVoltage = rootView.findViewById(R.id.resourcesUse_spn_lowVoltage);

        ivInfoNeutralDriverLowVoltage = rootView.findViewById(R.id.info_ic_neutralDriver_iv_lowVoltage);
        ivInfoSectionTypeLowVoltage = rootView.findViewById(R.id.info_ic_sectionType_iv_lowVoltage);
        ivInfoResourcesUseLowVoltage = rootView.findViewById(R.id.info_ic_resourcesUse_iv_lowVoltage);

        btnTernaLowVoltage = rootView.findViewById(R.id.sectionType_btn_terna);
        btnPhasesLowVoltage = rootView.findViewById(R.id.sectionType_btn_phases);
        arrayButtons = new ArrayList<Button>() {{add(btnTernaLowVoltage); add(btnPhasesLowVoltage); }};
        btnPreviousLowVoltage = rootView.findViewById(R.id.previous_btn_lowVoltage5);
        btnFinishLowVoltage = rootView.findViewById(R.id.finish_btn_lowVoltage);

        tvNeutralDriverLowVoltage = rootView.findViewById(R.id.textView1);
        tvSectionTypeLowVoltage = rootView.findViewById(R.id.textView2);
        tvResourcesUseLowVoltage = rootView.findViewById(R.id.textView3);

        /** Se incluye la información a los Spinners */
        String[] optionNeutralDriver = {"","1650 KCM XLPE","3 N 5 ALUMOWELD","7 N 8 ALUMOWELD","1/4 ACERO GALVANIZADO","394.5 ACSR","559.5 AAAC","394.5 AAAC","312.8 AAAC","123.3 AAAC","366.4 ACSR","123 CU","123.3 ACSR","6 CU","2 CU","2 CU XLPE","2/0 CU","3/0 CU","4/0 CU","4/0 CU XLPE","477 AAAC","500 CU XLPE","4 ACSR","2 ACSR","1/0 ACSR","4/0 ACSR","2/0 ACSR","477 ACSR","3/0 ACSR","750 CU XLPE","4 CU","4 CU XLPE","350 MCR","6 CU XLPE","350 CU","1 CU XLPE","250 CU","BARRA CU","336.4 ACSR","336 AAC","1/0 CU","6 ACSR","1/0 AAAC","4/0 AAAC","2/0 AAAC","2 AAAC","1/0 AL","2/0 AL","4/0 AL","2 AL","500 AAC","1000 AAC","6-A CU","250 AAAC","400 CU XLPE","8 ACSR","500 CU","250 CU XLPE","400 CU","2/0 CU XLPE","1/0 CU XLPE","600 CU XLPE","600 CU","3/0 AAAC","4 AL","8 AL","8 CU","6 AL","4 AAAC","247 ACSR","394.8 AAAC","266.8 ACSR","123.3 ACSR","350 CU XLPE","300 ACSR","500 ACSR XLPE","500 AL","750 AL","300 CU XLPE","350 AL XLPE","750 XLPE MCM","750 XLPE MCM AL","1/0 ACSR XLPE","3/0 XLPE AL","4/0 AL XLPE","2 AWG XLPE","2 XLPE","1/0 XLPE","300 XLPE","350 XLPE","500 XLPE","750 XLPE","4/0 XLPE","500 MCM","ACSR 266 MCM","500 XLPE-AL","1000 XLPE-CU","246,9 AAAC","336.4 ACSR/GA","CF-159","CF-200","CF-125","CF-63","477 ACSR XLPE","1/0 AL XLPE","CF-65","1 CU","CABLE ACERO GALVANIZADO 1/2","CABLE ACERO GALVANIZADO 3/8","1/0 AAC","4/0 AAC","2/0 AL XLPE","1000 MCM CU XLPE","500 MCM XLPE-AL","350 CU MCM","500 MCM CU","477 AL","3X70MM2 AL XLPE"};
        ArrayAdapter<String> adapterNeutralDriver = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionNeutralDriver);
        spnNeutralDriverLowVoltage.setAdapter(adapterNeutralDriver);
        spnNeutralDriverLowVoltage.setTitle("Seleccione el tipo de conductor neutro");
        spnNeutralDriverLowVoltage.setPositiveButton("Aceptar");
        String[] optionResourcesUse = {"","Uso General (FAER)","Uso General (PRONE)","Uso Exclusivo","Uso General (Municipios)","Uso General (Propios)","Uso General (Particular)","Uso (Por Verificar)","Uso Exclusivo (Municipios)"};
        ArrayAdapter<String> adapterResourcesUse = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionResourcesUse);
        spnResourcesUseLowVoltage.setAdapter(adapterResourcesUse);

        /** Mensaje toast para los iconos de información */
        ivInfoNeutralDriverLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoSectionTypeLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivInfoResourcesUseLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });

        /** OnClic para los botones de la interfaz, se cambia el color de los mismos al seleccionarlo */
        btnTernaLowVoltage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(arrayButtons, btnTernaLowVoltage, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSectionSelected = true;
                Globals.mapLowVoltageSectionActivityData.put("sectionType", "Por terna");
            }
        });
        btnPhasesLowVoltage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Utils.changeColorButtonGroup(arrayButtons, btnPhasesLowVoltage, intTextUnselectedFilter, dwBackgroundUnselectedFilter, intTextSelectedFilter, dwBackgroundSelectedFilter);
                isSectionSelected = true;
                Globals.mapLowVoltageSectionActivityData.put("sectionType", "Por fases");
            }
        });
        btnPreviousLowVoltage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });

        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState)){
            btnFinishLowVoltage.setText("Salir");
            btnFinishLowVoltage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getActivity()).onlyBack();
                }
            });
        }else{
            btnFinishLowVoltage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(verifyInformation()){
                        /** Se verifica que todas las paginas estan completas o incompletas */
                        if(Globals.boolArrayCompleteInfrastructurePages.contains(false)){
                            Globals.boolIsActivityFinishComplete = false;
                        }else{
                            Globals.boolIsActivityFinishComplete = true;
                        }

                        /** Función que se enviara a la ventana emergente */
                        Callable<Void> function = new Callable<Void>() {
                            public Void call() {
                                /** finaliza la tarea */
                                finishActivity();
                                return null;
                            }
                        };

                        if(Globals.boolIsActivityFinishComplete){
                            /** Se abre la ventana emergente */
                            Utils.alertInformation("Finalizar Orden", "Recuerde que una vez finalizada la orden no podrá modificarla." + "\n" + "¿Seguro que desea finalizarla?", getContext(), getActivity(), function, null);
                        }else{
                            Utils.alertInformation("Finalizar Orden", "Al finalizar la orden, esta quedará marcada como fallida debido a que la información esta incompleta." + "\n" + "¿Seguro que desea finalizar la orden?", getContext(), getActivity(), function, null);
                        }
                    }
                }
            });
        }

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
        String neutralDriver = spnNeutralDriverLowVoltage.getSelectedItem().toString();
        String resourcesUse = spnResourcesUseLowVoltage.getSelectedItem().toString();

        if(!TextUtils.isEmpty(neutralDriver) && !TextUtils.isEmpty(resourcesUse) &&  isSectionSelected){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(4, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(4, false);
        }

        /** En caso de tener los campos necesarios completos, puede pasar a la siguiente */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState)){
            return true;
        }else {
            if (!TextUtils.isEmpty(neutralDriver) && !TextUtils.isEmpty(resourcesUse) && isSectionSelected) {
                return true;
            }
        }

        /** Se validan los campos para ofrecer retroalimentación */
        if(TextUtils.isEmpty(neutralDriver)){
            TextView errorText = (TextView) spnNeutralDriverLowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(resourcesUse)){
            TextView errorText = (TextView) spnResourcesUseLowVoltage.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(isSectionSelected == false){
            Utils.toastMessage("Debe de seleccionar una tipo de área.",1,getContext());
        }

        return false;
    }

    private void finishActivity() {
        String id = Globals.cardGeneralActivitySelected.getStrId();
        /** Se reinicia el contador de paginas */
        Globals.intPageSelected = 0;
        /** Se obtiene el estado con el que inicia la actividad */
        String activityState = Globals.cardGeneralActivitySelected.getStrState();

        /** Se valida si la actividad esta completa o incompleta */
        if(Globals.boolIsActivityFinishComplete){
            /** Estado de finalizada */
            Utils.changeStateActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strExecuteState, id);
            Utils.changeFailedReasonActivity(Globals.dataInfrastructureSurveyActivitiesArray, "", id);
            Globals.mapLowVoltageSectionActivityData.put("isFailed", false);
            Globals.mapLowVoltageSectionActivityData.put("failReason", "");
            Globals.mapLowVoltageSectionActivityData.put("failDetail", "");
            Utils.toastMessage("La actividad ha sido marcada como ejecutada", 1, getContext());
        }else{
            /** Se marca la razon por la que quedo fallida la actividad */
            Utils.changeFailedReasonActivity(Globals.dataInfrastructureSurveyActivitiesArray, "Datos incompletos", id);
            /** Estado de finalizada incompleta */
            Utils.changeStateActivity(Globals.dataInfrastructureSurveyActivitiesArray, Globals.strFailedStateIncomplete, id);
            /** Se asigna el motivo por el cual la actividad queda marcada como fallida "Datos incompletos" */
            Globals.mapLowVoltageSectionActivityData.put("isFailed", true);
            Globals.mapLowVoltageSectionActivityData.put("failReason", "Datos incompletos");
            Globals.mapLowVoltageSectionActivityData.put("failDetail", "Datos incompletos");
            Utils.toastMessage("La actividad ha sido marcada como fallida", 1, getContext());
        }

        /** Asigno el id de la card finalizada */
        Globals.mapLowVoltageSectionActivityData.put("idActivity", Globals.cardGeneralActivitySelected.getStrId());

        Utils.updateLowVoltageSectionActivityObjToSQLite(getContext());

        /** Se actualiza en la base de datos*/
        int position = Utils.getPositionArrayActivitiesById(Globals.dataInfrastructureSurveyActivitiesArray, id);
        CardGeneralActivity generalActivity = Globals.dataInfrastructureSurveyActivitiesArray.get(position);
        Date dateFinishActivity = Utils.currentTime();
        generalActivity.setDateStartExecuted(Globals.dateStartActivity);
        generalActivity.setDateFinishExecuted(dateFinishActivity);
        Utils.updateGeneralActivityToSQLite(getContext(), generalActivity);

        /** Retorna a la lista de cards */
        ((MainActivity)getActivity()).onlyBack();

        /** Se limpia el mapa de información de la actividad */
        Globals.mapLowVoltageSectionActivityData = new HashMap();

        /** Reestablecer valores por defecto */
        Globals.boolArrayCompleteInfrastructurePages = new ArrayList<>();
        Globals.boolIsActivityFinishComplete = true;
        /** Actualiza las cards de inicio*/
        Utils.getActivities();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getInformationFromDB(){
        /** Se trae la información del transformador seleccionado */
        if(!Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strTodoState)){
            LowVoltageSectionActivity lowVoltageSectionActivity = Globals.lowVoltageSectionActivity;
            spnNeutralDriverLowVoltage.setSelection(((ArrayAdapter<String>)spnNeutralDriverLowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrNeutralConductor()));
            isSectionSelected = Utils.changeButtonSelectionFromDB(arrayButtons, lowVoltageSectionActivity.getStrSectionType(), Globals.mapLowVoltageSectionActivityData, "sectionType", isSectionSelected, getContext());
            spnResourcesUseLowVoltage.setSelection(((ArrayAdapter<String>)spnResourcesUseLowVoltage.getAdapter()).getPosition(lowVoltageSectionActivity.getStrResourcesUse()));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){

        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar EditText si la actividad se encuentra finalizada*/
            Utils.changeSpinnerStyleDisable(spnNeutralDriverLowVoltage, getContext());
            Utils.changeSpinnerStyleDisable(spnResourcesUseLowVoltage, getContext());

            btnTernaLowVoltage.setEnabled(false);
            btnPhasesLowVoltage.setEnabled(false);
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(spnNeutralDriverLowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvNeutralDriverLowVoltage, ivInfoNeutralDriverLowVoltage, getContext());
            }
            if(isSectionSelected == false){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvSectionTypeLowVoltage, ivInfoSectionTypeLowVoltage, getContext());
            }
            if(TextUtils.isEmpty(spnResourcesUseLowVoltage.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvResourcesUseLowVoltage, ivInfoResourcesUseLowVoltage, getContext());
            }
        }
    }

    /** Se guarda la información que va diligenciando en la página */
    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerSpinner(spnNeutralDriverLowVoltage, Globals.mapLowVoltageSectionActivityData, "neutralConductor");
        Utils.addListenerSpinner(spnResourcesUseLowVoltage, Globals.mapLowVoltageSectionActivityData, "resourcesUse");
    }
}