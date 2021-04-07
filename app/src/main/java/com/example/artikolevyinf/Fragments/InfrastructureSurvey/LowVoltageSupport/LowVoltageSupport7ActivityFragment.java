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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.MainActivity;
import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Model.CardGeneralActivity;
import com.example.artikolevyinf.Model.InfrastructureSurvey.LowVoltageSupportActivity;
import com.example.artikolevyinf.Utils.Globals;
import com.example.artikolevyinf.Utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * A simple {@link Fragment} subclass.
 */
public class LowVoltageSupport7ActivityFragment extends Fragment {

    Spinner spnUseResources, spnUUCC;
    ImageView ivUseResources, ivUUCC;
    Button btnPreviousActivity, btnFinishActivity;
    TextView tvUseResources, tvUUCC;

    public LowVoltageSupport7ActivityFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_low_voltage_support7_activity, container, false);

        spnUseResources = rootView.findViewById(R.id.use_resources_spn_lvSupport);
        spnUUCC = rootView.findViewById(R.id.UUCC_spn_lvSupport);

        ivUseResources = rootView.findViewById(R.id.info_use_resources_lvSupport);
        ivUUCC = rootView.findViewById(R.id.info_ic_UUCC_iv_lvSupport);

        btnPreviousActivity = rootView.findViewById(R.id.previous_btn_lvSupport7);
        btnFinishActivity = rootView.findViewById(R.id.finish_btn_lvSupport7);

        tvUseResources = rootView.findViewById(R.id.textView1);
        tvUUCC = rootView.findViewById(R.id.textView2);

        /** Se incluye la información a los Spinners */
        String[] optionUseResources = {"","Uso General (FAER)","Uso General (PRONE)","Uso Exclusivo","Uso General (Municipios)","Uso General (Propios)","Uso General (Particular)","Uso (Por Verificar)","Uso General (FNR)","Uso General (PGN)","Uso General (No contratado)"};
        ArrayAdapter<String> adapterUseResources = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionUseResources);
        spnUseResources.setAdapter(adapterUseResources);

        String[] optionUUCC = {"","Sencillo","Doble","Dos Dobles","Tres Dobles","Doble + Sencillo","No lavable","Tres Dobles + Equipo de Maniobra","Dos Dobles + Equipos de Maniobra","Doble + Sencillo + Equipos de Maniobra","Doble + Equipos de Maniobra","Sencillo + Equipos de Maniobra"};
        ArrayAdapter<String> adapterUUCC = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, optionUUCC);
        spnUUCC.setAdapter(adapterUUCC);

        /** Mensaje toast para los iconos de información */
        ivUseResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });
        ivUUCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.toastMessage("Seleccione una opción.", 0, getContext());
            }
        });

        /** OnClic para los botones de la interfaz, se cambia el color de los mismos al seleccionarlo */
        btnPreviousActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.viewPager.setCurrentItem(Globals.intPageSelected -1,true);
            }
        });

        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState)){
            btnFinishActivity.setText("Salir");
            btnFinishActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getActivity()).onlyBack();
                }
            });
        }else{
            btnFinishActivity.setOnClickListener(new View.OnClickListener() {
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

        String UseResources = spnUseResources.getSelectedItem().toString();
        String UUCC = spnUUCC.getSelectedItem().toString();

        if(!TextUtils.isEmpty(UseResources) && !TextUtils.isEmpty(UUCC)){
            /** Se marca como que la página esta completa */
            Globals.boolArrayCompleteInfrastructurePages.set(6, true);
        }else{
            Globals.boolArrayCompleteInfrastructurePages.set(6, false);
        }

        if(TextUtils.isEmpty(UseResources)){
            TextView errorText = (TextView) spnUseResources.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }
        if(TextUtils.isEmpty(UUCC)){
            TextView errorText = (TextView) spnUUCC.getSelectedView();
            errorText.setPadding(0,0,100,0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Este campo no puede quedar vacío");
        }

        return true;
    }

    private void finishActivity() {
        String id = Globals.cardGeneralActivitySelected.getStrId();
        /** Se reinicia el contador de paginas */
        Globals.intPageSelected = 0;

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
        Globals.mapLowVoltageSupportActivityData.put("idActivity", Globals.cardGeneralActivitySelected.getStrId());

        Utils.updateLowVoltageSupportActivityObjToSQLite(getContext());

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
        Globals.mapLowVoltageSupportActivityData = new HashMap();

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
            LowVoltageSupportActivity lowVoltageSupportActivity = Globals.lowVoltageSupportActivity;
            spnUseResources.setSelection(((ArrayAdapter<String>)spnUseResources.getAdapter()).getPosition(lowVoltageSupportActivity.getStrUseResources()));
            spnUUCC.setSelection(((ArrayAdapter<String>)spnUUCC.getAdapter()).getPosition(lowVoltageSupportActivity.getStrUUCC()));
        }
    }

    private void changeTitleColorIconColor(){
        /** En caso de que el estado de la card sea Finalizada incompleta, al entrar debe aparecer estos campos opcionales vacios en rojo
         //En caso de ser finalizadas incompletas o fallidas, van a aparecer los campos opcionales rojos y podrá editar la información
         //Unicamente las actividades que no estan sincronizadas FALTA LA CONDICIÓN DE SINCRONIZACIÓN */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedStateIncomplete) || Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strFailedState) ){
            if(TextUtils.isEmpty(spnUseResources.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvUseResources, ivUseResources, getContext());
            }

            if(TextUtils.isEmpty(spnUUCC.getSelectedItem().toString())){
                Utils.changeColorTextViewIconInfoWhenEmpty(tvUUCC, ivUUCC, getContext());
            }
        }
    }

    private void saveInstantInformation() {
        /** Se guarda la información recogida */
        Utils.addListenerSpinner(spnUseResources, Globals.mapLowVoltageSupportActivityData, "useResources");
        Utils.addListenerSpinner(spnUUCC, Globals.mapLowVoltageSupportActivityData, "UUCC");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void disableElementsByState(){

        /** Validar el estado de la actividad */
        if(Globals.cardGeneralActivitySelected.getStrState().equals(Globals.strExecuteState) && Globals.cardGeneralActivitySelected.getIsUploadAPI()){
            /** Deshabilitar EditText si la actividad se encuentra finalizada*/
            Utils.changeSpinnerStyleDisable(spnUseResources, getContext());
            Utils.changeSpinnerStyleDisable(spnUUCC, getContext());
        }
    }

}
